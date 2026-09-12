package view;

import controller.AdminController;
import controller.JogadorController;
import model.Administrador;
import util.InputUtil;

public class AdminView {

    private final AdminController adminController;
    private final JogadorController jogadorController;
    private final InputUtil input;

    public AdminView(
            AdminController adminController,
            JogadorController jogadorController,
            InputUtil input) {
        this.adminController = adminController;
        this.jogadorController = jogadorController;
        this.input = input;
    }

    public boolean exibirLogin(Administrador administrador) {
        System.out.println("\n=== LOGIN ADMINISTRADOR ===");
        System.out.print("Usuário: ");
        String usuario = input.lerTexto();
        System.out.print("Senha: ");
        String senha = input.lerTexto();

        if (administrador.login(usuario, senha)) {
            System.out.println("Acesso concedido!");
            return true;
        }

        System.out.println("Credenciais inválidas!");
        return false;
    }

    public void exibirMenu() {
        int opcao;

        do {
            System.out.println("\n=== ADMINISTRAÇÃO ===");
            System.out.println("1 - Listar jogadores");
            System.out.println("2 - Remover jogador");
            System.out.println("3 - Estatísticas");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            opcao = input.lerInteiro();

            switch (opcao) {
                case 1:
                    adminController.listarJogadores(jogadorController.getJogadores());
                    break;

                case 2:
                    System.out.print("ID do jogador: ");
                    adminController.removerJogador(jogadorController.getJogadores(), input.lerInteiro());
                    System.out.println("Jogador removido!");
                    break;

                case 3:
                    adminController.visualizarEstatisticas(jogadorController.getJogadores());
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);
    }
}
