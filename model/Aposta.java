package model;

public class Aposta {

    private double valor;

    public Aposta(double valor) {
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }

    public void dobrar() {
        valor *= 2;
    }

    @Override
    public String toString() {
        return "Aposta: R$ " + valor;
    }
}