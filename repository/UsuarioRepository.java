package repository;

import model.Usuario;
import persistence.JsonPersistencia;

import java.util.ArrayList;

public class UsuarioRepository {

    public void salvar(ArrayList<Usuario> usuarios) {
        JsonPersistencia.salvarUsuarios(usuarios);
    }

    public ArrayList<Usuario> carregar() {
        return JsonPersistencia.carregarUsuarios();
    }
}
