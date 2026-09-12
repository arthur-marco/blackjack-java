package view;

import controller.CarteiraController;
import model.Carteira;
import util.InputUtil;

public class CarteiraView {

    private final CarteiraController carteiraController;
    private final InputUtil input;

    public CarteiraView(CarteiraController carteiraController, InputUtil input) {
        this.carteiraController = carteiraController;
        this.input = input;
    }

    public void exibirDeposito(Carteira carteira) {
        System.out.print("\nValor para depósito: R$ ");
        double valor = input.lerDouble();
        carteiraController.depositar(carteira, valor);
    }

    public void exibirSaldo(Carteira carteira) {
        carteiraController.consultarSaldo(carteira);
    }
}
