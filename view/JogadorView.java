package view;

import controller.HistoricoController;
import controller.JogadorController;
import model.Jogador;
import util.InputUtil;

public class JogadorView {

    private final JogadorController  jogadorController;
    private final HistoricoController historicoController;
    private final InputUtil           input;

    public JogadorView(JogadorController jogadorController,
                       HistoricoController historicoController,
                       InputUtil input) {
        this.jogadorController   = jogadorController;
        this.historicoController = historicoController;
        this.input               = input;
    }

    public void exibirPerfil(Jogador jogador) {
        jogadorController.exibirPerfil(jogador);
    }

    public void exibirCadastroJogador(Jogador jogador) {
        jogadorController.cadastrarJogador(jogador);
        System.out.println("Jogador registrado: " + jogador.getNome());
    }

    public void exibirRanking() {
        historicoController.ranking(jogadorController.getJogadores());
    }
}
