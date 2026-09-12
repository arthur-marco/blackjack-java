package model;

import exception.SaldoInsuficienteException;

import java.io.Serializable;

public class Carteira implements Serializable {

    private static final long serialVersionUID = 1L;

    private double saldo;

    public Carteira(double saldoInicial) {
        this.saldo = saldoInicial;
    }

    public double getSaldo() { return saldo; }

    public void depositar(double valor) {
        saldo += valor;
    }

    public void sacar(double valor) throws SaldoInsuficienteException {
        if (valor > saldo) {
            throw new SaldoInsuficienteException("Saldo insuficiente para saque!");
        }
        saldo -= valor;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
