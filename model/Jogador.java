package model;

import java.io.Serializable;

public class Jogador extends Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;

    private Carteira carteira;
    private int vitorias;
    private int derrotas;

    public Jogador(int id, String nome) {
        super(id, nome);
        this.carteira = new Carteira(0);
        this.vitorias = 0;
        this.derrotas = 0;
    }

    public void adicionarVitoria() { vitorias++; }
    public void adicionarDerrota() { derrotas++; }

    public void setVitorias(int vitorias) { this.vitorias = vitorias; }
    public void setDerrotas(int derrotas) { this.derrotas = derrotas; }

    public Carteira getCarteira()  { return carteira; }
    public int      getVitorias()  { return vitorias; }
    public int      getDerrotas()  { return derrotas; }

    @Override
    public String toString() {
        return "Nome: " + nome
                + " | Saldo: R$ " + String.format("%.2f", carteira.getSaldo())
                + " | Vitórias: " + vitorias
                + " | Derrotas: " + derrotas;
    }
}
