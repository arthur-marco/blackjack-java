package view;

import controller.PartidaController;
import model.Partida;

public class PartidaView {

    private final PartidaController partidaController;

    public PartidaView(PartidaController partidaController) {
        this.partidaController = partidaController;
    }

    public void exibirInicio(Partida partida) {
        partidaController.iniciarPartida(partida);
        System.out.println("Partida iniciada!");
    }

    public void exibirFim(Partida partida) {
        partidaController.finalizarPartida(partida);
        System.out.println("Partida finalizada!");
    }

    public void exibirControleRodada(Partida partida) {
        System.out.println("Rodada em andamento | Ativa: " + partida.isAtiva());
    }
}
