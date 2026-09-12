package controller;

import exception.SaldoInsuficienteException;
import model.Carteira;

public class CarteiraController {

    public void depositar(Carteira carteira, double valor) {
        carteira.depositar(valor);
        System.out.println("Depósito de R$ " + valor + " realizado com sucesso!");
    }

    public void sacar(Carteira carteira, double valor) {
        try {
            carteira.sacar(valor);
            System.out.println("Saque de R$ " + valor + " realizado com sucesso!");
        } catch (SaldoInsuficienteException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void consultarSaldo(Carteira carteira) {
        System.out.println("Saldo: R$ " + carteira.getSaldo());
    }
}
