package view;

import controller.ApostaController;
import exception.ApostaInvalidaException;
import exception.SaldoInsuficienteException;
import model.Aposta;
import model.Carteira;
import model.Mesa;
import util.InputUtil;

public class ApostaView {

    private final ApostaController apostaController;
    private final InputUtil         input;

    public ApostaView(ApostaController apostaController, InputUtil input) {
        this.apostaController = apostaController;
        this.input            = input;
    }

    public Aposta exibirAposta(Carteira carteira, Mesa mesa) {
        System.out.print("Valor da aposta: R$ ");
        double valor = input.lerDouble();
        try {
            Aposta aposta = apostaController.apostar(carteira, valor, mesa);
            System.out.println("Aposta de R$ " + valor + " realizada!");
            return aposta;
        } catch (SaldoInsuficienteException | ApostaInvalidaException e) {
            System.out.println("Erro: " + e.getMessage());
            return null;
        }
    }

    public void exibirDobragem(Aposta aposta, Carteira carteira) {
        try {
            apostaController.dobrarAposta(aposta, carteira);
            System.out.println("Aposta dobrada! Novo valor: R$ " + aposta.getValor());
        } catch (SaldoInsuficienteException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void exibirResultadoAposta(String resultado, Aposta aposta, Carteira carteira) {
        switch (resultado) {
            case "VITORIA":
                apostaController.pagarVitoria(carteira, aposta);
                break;
            case "DERROTA":
                apostaController.registrarDerrota(aposta);
                break;
            case "EMPATE":
                System.out.println("Empate! Aposta devolvida.");
                break;
        }
    }
}
