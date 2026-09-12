package view;

import controller.*;
import model.*;
import service.UsuarioService;
import util.InputUtil;


public class MenuView {

    private final SessaoLogin         sessao;
    private final LoginController     loginController;
    private final UsuarioController   usuarioController;
    private final HistoricoController historicoController;
    private final JogadorController   jogadorController;
    private final LoginView           loginView;
    private final UsuarioView         usuarioView;
    private final CarteiraView        carteiraView;
    private final BlackjackView       blackjackView;
    private final AdminView           adminView;
    private final JogadorView         jogadorView;
    private final HistoricoView       historicoView;
    private final Administrador       administrador;
    private final InputUtil            input;

    public MenuView(
            SessaoLogin sessao,
            LoginController loginController,
            UsuarioController usuarioController,
            HistoricoController historicoController,
            JogadorController jogadorController,
            LoginView loginView,
            UsuarioView usuarioView,
            CarteiraView carteiraView,
            BlackjackView blackjackView,
            AdminView adminView,
            JogadorView jogadorView,
            HistoricoView historicoView,
            Administrador administrador,
            InputUtil input) {
        this.sessao              = sessao;
        this.loginController     = loginController;
        this.usuarioController   = usuarioController;
        this.historicoController = historicoController;
        this.jogadorController   = jogadorController;
        this.loginView           = loginView;
        this.usuarioView         = usuarioView;
        this.carteiraView        = carteiraView;
        this.blackjackView       = blackjackView;
        this.adminView           = adminView;
        this.jogadorView         = jogadorView;
        this.historicoView       = historicoView;
        this.administrador       = administrador;
        this.input               = input;
    }

    // =========================================================
    // TELA INICIAL
    // =========================================================

    public void exibirTelaInicial() {
        int opcao;
        do {
            System.out.println("\n=================================");
            System.out.println("           BLACKJACK");
            System.out.println("=================================");
            System.out.println("1 - Login");
            System.out.println("2 - Cadastrar");
            System.out.println("3 - Login Administrador");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            opcao = input.lerInteiro();

            switch (opcao) {
                case 1:
                    if (loginView.exibirLogin()) exibirMenuPrincipal();
                    break;
                case 2:
                    usuarioView.exibirCadastro();
                    break;
                case 3:
                    if (adminView.exibirLogin(administrador)) adminView.exibirMenu();
                    break;
                case 0:
                    System.out.println("Encerrando sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    // =========================================================
    // MENU PRINCIPAL (jogador logado)
    // =========================================================

    private void exibirMenuPrincipal() {
        int opcao;
        do {
            Usuario logado = sessao.getUsuarioLogado();

            System.out.println("\n=================================");
            System.out.println("         MENU PRINCIPAL");
            System.out.println("=================================");
            System.out.println("Logado: " + logado.getNome());
            System.out.println("\n1 - Jogar Blackjack");
            System.out.println("2 - Depositar na Carteira");
            System.out.println("3 - Consultar Saldo");
            System.out.println("4 - Meu Histórico");
            System.out.println("5 - Ranking");
            System.out.println("6 - Meu Perfil");
            System.out.println("7 - Editar Perfil");
            System.out.println("8 - Logout");
            System.out.print("\nEscolha: ");

            opcao = input.lerInteiro();

            switch (opcao) {
                case 1:
                    blackjackView.iniciarJogo(logado.getJogador());
                    break;
                case 2:
                    carteiraView.exibirDeposito(logado.getJogador().getCarteira());
                    break;
                case 3:
                    carteiraView.exibirSaldo(logado.getJogador().getCarteira());
                    break;
                case 4:
                    historicoView.exibirHistoricoJogador(logado.getNome());
                    break;
                case 5:
                    historicoView.exibirRanking();
                    break;
                case 6:
                    jogadorView.exibirPerfil(logado.getJogador());
                    break;
                case 7:
                    usuarioView.exibirEdicaoPerfil(logado);
                    usuarioController.salvar();
                    break;
                case 8:
                    usuarioController.salvar();
                    loginController.logout();
                    System.out.println("\nLogout realizado!");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 8);
    }
}
