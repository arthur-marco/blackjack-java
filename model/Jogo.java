package model;

public abstract class Jogo {

    protected String nome;

    public Jogo(String nome) {
        this.nome = nome;
    }

    public abstract void iniciarPartida();

    public abstract void finalizarPartida();

    public String getNome() {
        return nome;
    }
}
