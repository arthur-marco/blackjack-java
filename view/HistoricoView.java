package view;

import controller.HistoricoController;
import controller.JogadorController;

public class HistoricoView {

    private final HistoricoController  historicoController;
    private final JogadorController    jogadorController;

    public HistoricoView(HistoricoController historicoController,
                         JogadorController jogadorController) {
        this.historicoController = historicoController;
        this.jogadorController   = jogadorController;
    }

    public void exibirHistoricoCompleto() {
        historicoController.consultarHistorico();
    }

    public void exibirHistoricoJogador(String nomeJogador) {
        historicoController.consultarHistoricoDoJogador(nomeJogador);
    }

    public void exibirRanking() {
        historicoController.ranking(jogadorController.getJogadores());
    }
}
