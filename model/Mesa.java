package model;

public class Mesa {

    private int id;
    private String nome;
    private double apostaMinima;

    public Mesa(int id, String nome, double apostaMinima) {
        this.id = id;
        this.nome = nome;
        this.apostaMinima = apostaMinima;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getApostaMinima() {
        return apostaMinima;
    }

    public void setApostaMinima(double apostaMinima) {
        this.apostaMinima = apostaMinima;
    }

    @Override
    public String toString() {
        return id + " - " + nome +
                " (Aposta mínima: R$" + apostaMinima + ")";
    }
}