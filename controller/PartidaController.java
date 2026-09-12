package controller;

import model.Carta;
import model.Partida;

import java.util.ArrayList;

public class PartidaController {

    public void iniciarPartida(Partida partida) {
        partida.iniciar();
        System.out.println("\nPartida iniciada!");
    }

    public void finalizarPartida(Partida partida) {
        partida.finalizar();
        System.out.println("Partida encerrada!");
    }

    public int calcularPontos(ArrayList<Carta> mao) {
        int total = 0;
        int ases = 0;

        for (Carta carta : mao) {
            String valor = carta.getValor();

            switch (valor) {
                case "J":
                case "Q":
                case "K":
                    total += 10;
                    break;

                case "A":
                    total += 11;
                    ases++;
                    break;

                default:
                    total += Integer.parseInt(valor);
            }
        }

        while (total > 21 && ases > 0) {
            total -= 10;
            ases--;
        }

        return total;
    }

    public void mostrarMao(ArrayList<Carta> mao) {
        for (Carta carta : mao) {
            System.out.println("  " + carta);
        }
    }

    public String definirResultado(int pontosJogador, int pontosDealer) {
        if (pontosDealer > 21) {
            return "VITORIA";
        } else if (pontosJogador > pontosDealer) {
            return "VITORIA";
        } else if (pontosJogador == pontosDealer) {
            return "EMPATE";
        } else {
            return "DERROTA";
        }
    }
}
