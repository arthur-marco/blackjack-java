package controller;

import model.Baralho;
import model.Carta;

import java.util.Collections;

public class BaralhoController {

    public void criarBaralho(Baralho baralho) {

        String[] naipes = {
                "Copas",
                "Espadas",
                "Ouros",
                "Paus"
        };

        String[] valores = {
                "A",
                "2",
                "3",
                "4",
                "5",
                "6",
                "7",
                "8",
                "9",
                "10",
                "J",
                "Q",
                "K"
        };

        for(String naipe : naipes) {

            for(String valor : valores) {

                baralho.getCartas().add(
                        new Carta(
                                naipe,
                                valor
                        )
                );
            }
        }
    }

    public void embaralhar(
            Baralho baralho) {

        Collections.shuffle(
                baralho.getCartas()
        );
    }

    public Carta distribuirCarta(
            Baralho baralho) {

        if(baralho.getCartas().isEmpty()) {
            return null;
        }

        return baralho
                .getCartas()
                .remove(0);
    }

    public void mostrarCartas(
            Baralho baralho) {

        for(Carta carta :
                baralho.getCartas()) {

            System.out.println(carta);
        }
    }
}