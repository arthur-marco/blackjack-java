package model;

import java.util.ArrayList;

public class Cassino {

    private String nome;
    private ArrayList<Mesa> mesas;   // Agregação

    public Cassino(String nome) {
        this.nome = nome;
        this.mesas = new ArrayList<>();
    }

    public void adicionarMesa(Mesa mesa) {
        mesas.add(mesa);
    }

    public void removerMesa(Mesa mesa) {
        mesas.remove(mesa);
    }

    public ArrayList<Mesa> getMesas() {
        return mesas;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "Cassino: " + nome + " | Mesas: " + mesas.size();
    }
}
