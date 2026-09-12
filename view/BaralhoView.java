package view;

import controller.BaralhoController;
import model.Baralho;
import model.Carta;

public class BaralhoView {

    private final BaralhoController baralhoController;

    public BaralhoView(BaralhoController baralhoController) {
        this.baralhoController = baralhoController;
    }

    public void exibirEmbaralhamento(Baralho baralho) {
        baralhoController.embaralhar(baralho);
        System.out.println("Baralho embaralhado!");
    }

    public Carta distribuirCarta(Baralho baralho) {
        return baralhoController.distribuirCarta(baralho);
    }

    public void exibirCartas(Baralho baralho) {
        baralhoController.mostrarCartas(baralho);
    }
}
