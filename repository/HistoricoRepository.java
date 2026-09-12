package repository;

import model.HistoricoPartida;
import persistence.JsonPersistencia;

import java.util.ArrayList;

public class HistoricoRepository {

    public void salvar(ArrayList<HistoricoPartida> historico) {
        JsonPersistencia.salvarPartidas(historico);
    }

    public ArrayList<HistoricoPartida> carregar() {
        return JsonPersistencia.carregarPartidas();
    }
}
