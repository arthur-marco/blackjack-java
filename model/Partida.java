package model;

import java.util.ArrayList;

public class Partida extends Jogo {

    private Jogador jogador;            // Associação
    private Mesa mesa;
    private boolean ativa;
    private ArrayList<Carta> maoJogador;  // Composição
    private ArrayList<Carta> maoDealer;   // Composição
    private ArrayList<Aposta> apostas;    // Composição — ArrayList<Aposta>

    public Partida(Jogador jogador, Mesa mesa) {
        super("Blackjack");
        this.jogador    = jogador;
        this.mesa       = mesa;
        this.ativa      = false;
        this.maoJogador = new ArrayList<>();
        this.maoDealer  = new ArrayList<>();
        this.apostas    = new ArrayList<>();
    }

    @Override
    public void iniciarPartida() {
        ativa = true;
    }

    @Override
    public void finalizarPartida() {
        ativa = false;
    }

    public void iniciar()   { iniciarPartida(); }
    public void finalizar() { finalizarPartida(); }

    public void adicionarAposta(Aposta aposta) {
        apostas.add(aposta);
    }

    public void setAposta(Aposta aposta) {
        apostas.clear();
        apostas.add(aposta);
    }

    public Aposta getAposta() {
        return apostas.isEmpty() ? null : apostas.get(0);
    }

    public ArrayList<Aposta> getApostas()    { return apostas; }
    public ArrayList<Carta>  getMaoJogador() { return maoJogador; }
    public ArrayList<Carta>  getMaoDealer()  { return maoDealer; }
    public Jogador           getJogador()    { return jogador; }
    public Mesa              getMesa()       { return mesa; }
    public boolean           isAtiva()       { return ativa; }
}
