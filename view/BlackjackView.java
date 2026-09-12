package view;

import controller.*;
import exception.ApostaInvalidaException;
import exception.SaldoInsuficienteException;
import model.*;
import util.InputUtil;

public class BlackjackView {

    private final BaralhoController   baralhoController;
    private final MesaController      mesaController;
    private final PartidaController   partidaController;
    private final ApostaController    apostaController;
    private final HistoricoController historicoController;
    private final UsuarioController   usuarioController;
    private final InputUtil            input;

    public BlackjackView(
            BaralhoController baralhoController,
            MesaController mesaController,
            PartidaController partidaController,
            ApostaController apostaController,
            HistoricoController historicoController,
            UsuarioController usuarioController,
            InputUtil input) {
        this.baralhoController   = baralhoController;
        this.mesaController      = mesaController;
        this.partidaController   = partidaController;
        this.apostaController    = apostaController;
        this.historicoController = historicoController;
        this.usuarioController   = usuarioController;
        this.input               = input;
    }

    public void iniciarJogo(Jogador jogador) {
        Baralho baralho = prepararBaralho();
        Mesa    mesa    = escolherMesa();
        if (mesa == null) return;

        Aposta aposta = realizarAposta(jogador, mesa);
        if (aposta == null) return;

        Partida partida = new Partida(jogador, mesa);
        partida.setAposta(aposta);
        partidaController.iniciarPartida(partida);

        distribuirCartasIniciais(partida, baralho);

        boolean estourou = turnoJogador(partida, baralho);

        if (estourou) {
            encerrarPartida(partida, "DERROTA", jogador, aposta);
            return;
        }

        turnoDealer(partida, baralho);

        int pontosJogador = partidaController.calcularPontos(partida.getMaoJogador());
        int pontosDealer  = partidaController.calcularPontos(partida.getMaoDealer());

        System.out.println("\n=== PLACAR FINAL ===");
        System.out.println("Seus pontos  : " + pontosJogador);
        System.out.println("Pontos dealer: " + pontosDealer);

        String resultado = partidaController.definirResultado(pontosJogador, pontosDealer);
        encerrarPartida(partida, resultado, jogador, aposta);
    }

    // -------------------------------------------------------

    private Baralho prepararBaralho() {
        Baralho baralho = new Baralho();
        baralhoController.criarBaralho(baralho);
        baralhoController.embaralhar(baralho);
        return baralho;
    }

    private Mesa escolherMesa() {
        System.out.println("\n=== MESAS DISPONÍVEIS ===");
        mesaController.listarMesas();
        System.out.print("\nEscolha uma mesa: ");
        int opcao = input.lerInteiro();

        if (opcao < 1 || opcao > mesaController.getMesas().size()) {
            System.out.println("Mesa inválida!");
            return null;
        }
        return mesaController.getMesas().get(opcao - 1);
    }

    private Aposta realizarAposta(Jogador jogador, Mesa mesa) {
        System.out.println("\nMesa: " + mesa.getNome()
                + " | Mínimo: R$ " + mesa.getApostaMinima());
        System.out.print("Valor da aposta: R$ ");
        double valor = input.lerDouble();
        try {
            Aposta aposta = apostaController.apostar(jogador.getCarteira(), valor, mesa);
            System.out.println("Aposta de R$ " + valor + " realizada!");
            return aposta;
        } catch (SaldoInsuficienteException | ApostaInvalidaException e) {
            System.out.println("Erro: " + e.getMessage());
            return null;
        }
    }

    private void distribuirCartasIniciais(Partida partida, Baralho baralho) {
        partida.getMaoJogador().add(baralhoController.distribuirCarta(baralho));
        partida.getMaoJogador().add(baralhoController.distribuirCarta(baralho));
        partida.getMaoDealer().add(baralhoController.distribuirCarta(baralho));
        partida.getMaoDealer().add(baralhoController.distribuirCarta(baralho));
    }

    private boolean turnoJogador(Partida partida, Baralho baralho) {
        while (true) {
            System.out.println("\n=== SUA MÃO ===");
            partidaController.mostrarMao(partida.getMaoJogador());
            int pontos = partidaController.calcularPontos(partida.getMaoJogador());
            System.out.println("Pontos: " + pontos);

            if (pontos > 21) { System.out.println("Você estourou!"); return true; }
            if (pontos == 21) { System.out.println("Blackjack!"); return false; }

            System.out.println("\n1 - Comprar carta  |  2 - Parar");
            System.out.print("Escolha: ");
            if (input.lerInteiro() == 1) {
                partida.getMaoJogador().add(baralhoController.distribuirCarta(baralho));
            } else {
                return false;
            }
        }
    }

    private void turnoDealer(Partida partida, Baralho baralho) {
        System.out.println("\n=== MÃO DO DEALER ===");
        partidaController.mostrarMao(partida.getMaoDealer());

        while (partidaController.calcularPontos(partida.getMaoDealer()) < 17) {
            partida.getMaoDealer().add(baralhoController.distribuirCarta(baralho));
            System.out.println("Dealer comprou uma carta.");
        }

        System.out.println("\n=== MÃO FINAL DO DEALER ===");
        partidaController.mostrarMao(partida.getMaoDealer());
        int pontos = partidaController.calcularPontos(partida.getMaoDealer());
        System.out.println("Pontos do dealer: " + pontos);
        if (pontos > 21) System.out.println("Dealer estourou!");
    }

    private void encerrarPartida(Partida partida, String resultado,
                                 Jogador jogador, Aposta aposta) {
        switch (resultado) {
            case "VITORIA":
                System.out.println("\n*** VOCÊ VENCEU! ***");
                apostaController.pagarVitoria(jogador.getCarteira(), aposta);
                jogador.adicionarVitoria();
                break;
            case "DERROTA":
                System.out.println("\n*** VOCÊ PERDEU! ***");
                apostaController.registrarDerrota(aposta);
                jogador.adicionarDerrota();
                break;
            case "EMPATE":
                System.out.println("\n*** EMPATE! Aposta devolvida. ***");
                jogador.getCarteira().depositar(aposta.getValor());
                break;
        }

        historicoController.salvarPartida(jogador.getNome(), resultado);
        partidaController.finalizarPartida(partida);

        // Persiste stats atualizadas do usuário no JSON
        usuarioController.salvar();

        System.out.println("Saldo atual: R$ "
                + String.format("%.2f", jogador.getCarteira().getSaldo()));
    }
}
