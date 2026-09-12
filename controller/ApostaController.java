package controller;

import exception.ApostaInvalidaException;
import exception.SaldoInsuficienteException;
import model.Aposta;
import model.Carteira;
import model.Mesa;

public class ApostaController {

    public Aposta apostar(
            Carteira carteira,
            double valor,
            Mesa mesa)
            throws SaldoInsuficienteException,
            ApostaInvalidaException {

        if (valor <= 0) {
            throw new ApostaInvalidaException("Valor da aposta deve ser maior que zero!");
        }

        if (valor < mesa.getApostaMinima()) {
            throw new ApostaInvalidaException(
                    "Aposta mínima nesta mesa é R$ " + mesa.getApostaMinima()
            );
        }

        if (valor > carteira.getSaldo()) {
            throw new SaldoInsuficienteException("Saldo insuficiente!");
        }

        try {
            carteira.sacar(valor);
        } catch (SaldoInsuficienteException e) {

            throw e;
        }

        return new Aposta(valor);
    }

    public void dobrarAposta(
            Aposta aposta,
            Carteira carteira)
            throws SaldoInsuficienteException {

        if (aposta.getValor() > carteira.getSaldo()) {
            throw new SaldoInsuficienteException("Saldo insuficiente para dobrar!");
        }

        try {
            carteira.sacar(aposta.getValor());
        } catch (SaldoInsuficienteException e) {
            throw e;
        }

        aposta.dobrar();
    }

    public void pagarVitoria(Carteira carteira, Aposta aposta) {
        carteira.depositar(aposta.getValor() * 2);
        System.out.println("Você ganhou R$ " + (aposta.getValor() * 2) + "!");
    }

    public void registrarDerrota(Aposta aposta) {
        System.out.println("Aposta perdida: R$ " + aposta.getValor());
    }
}
