
package controller;

import java.util.ArrayList;
import model.Jogador;

public class AdminController {

    public void listarJogadores(ArrayList<Jogador> jogadores){
        for(Jogador j : jogadores){
            System.out.println(j);
        }
    }


    public Jogador buscar(ArrayList<Jogador> jogadores, int id){
        for(Jogador j : jogadores){
            if(j.getId() == id){
                return j;
            }
        }
        return null;
    }


    public Jogador buscar(ArrayList<Jogador> jogadores, String nome){
        for(Jogador j : jogadores){
            if(j.getNome().equalsIgnoreCase(nome)){
                return j;
            }
        }
        return null;
    }

    public void removerJogador(ArrayList<Jogador> jogadores, int id){
        Jogador j = buscar(jogadores, id);

        if(j != null){
            jogadores.remove(j);
        }
    }
    public void visualizarEstatisticas(
            ArrayList<Jogador> jogadores){

        System.out.println("\n=== ESTATÍSTICAS ===");

        System.out.println(
                "Total de jogadores: "
                        + jogadores.size()
        );

        int totalVitorias = 0;

        for(Jogador j : jogadores){

            totalVitorias +=
                    j.getVitorias();
        }

        System.out.println(
                "Total de vitórias: "
                        + totalVitorias
        );
    }
}
