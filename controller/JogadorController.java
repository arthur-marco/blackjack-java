package controller;

import model.Jogador;

import java.util.ArrayList;

public class JogadorController {

    private ArrayList<Jogador> jogadores = new ArrayList<>();

    public void cadastrarJogador(Jogador jogador) {
        jogadores.add(jogador);
    }

    public void listarJogadores() {
        jogadores.forEach(System.out::println);
    }

    public void exibirPerfil(Jogador jogador) {
        System.out.println("\n=== PERFIL ===");
        System.out.println(jogador);
    }

    public Jogador buscarJogador(int id) {
        return jogadores.stream()
                .filter(j -> j.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void limparJogadores() {
        jogadores.clear();
    }

    public ArrayList<Jogador> getJogadores() {
        return jogadores;
    }
}
