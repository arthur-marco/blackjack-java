package controller;

import model.Mesa;
import java.util.ArrayList;

public class MesaController {

    private ArrayList<Mesa> mesas = new ArrayList<>();

    public void criarMesa(Mesa mesa) {
        mesas.add(mesa);
    }

    public void listarMesas() {

        for(Mesa mesa : mesas) {
            System.out.println(mesa);
        }
    }

    public void removerMesa(int id) {

        mesas.removeIf(
                mesa -> mesa.getId() == id
        );
    }

    public void editarLimite(
            int id,
            double novoLimite) {

        for(Mesa mesa : mesas) {

            if(mesa.getId() == id) {
                mesa.setApostaMinima(novoLimite);
                break;
            }
        }
    }

    public ArrayList<Mesa> getMesas() {
        return mesas;
    }
}