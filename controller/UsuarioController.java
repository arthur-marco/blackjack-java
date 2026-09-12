package controller;

import model.Usuario;
import repository.UsuarioRepository;

import java.util.ArrayList;
import java.util.HashMap;

public class UsuarioController {

    private final UsuarioRepository repository = new UsuarioRepository();
    private HashMap<Integer, Usuario> usuarios = new HashMap<>();

    public UsuarioController() {
        carregarUsuarios();
    }

    public void cadastrarUsuario(Usuario usuario) {
        usuarios.put(usuario.getId(), usuario);
        salvar();
    }

    public Usuario buscarUsuario(int id) {
        return usuarios.get(id);
    }

    public void editarUsuario(int id, String novoNome) {
        Usuario usuario = usuarios.get(id);
        if (usuario != null) {
            usuario.setNome(novoNome);
            salvar();
        }
    }

    public void excluirUsuario(int id) {
        usuarios.remove(id);
        salvar();
    }

    public void listarUsuarios() {
        usuarios.values().forEach(System.out::println);
    }

    public void salvar() {
        repository.salvar(new ArrayList<>(usuarios.values()));
    }

    public HashMap<Integer, Usuario> getUsuarios() {
        return usuarios;
    }

    private void carregarUsuarios() {
        for (Usuario u : repository.carregar()) {
            usuarios.put(u.getId(), u);
        }
    }
}
