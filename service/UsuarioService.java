package service;

import controller.JogadorController;
import controller.UsuarioController;
import model.Usuario;

public class UsuarioService {

    private final UsuarioController usuarioController;
    private final JogadorController jogadorController;

    public UsuarioService(UsuarioController usuarioController,
                          JogadorController jogadorController) {
        this.usuarioController = usuarioController;
        this.jogadorController = jogadorController;
    }

    public void registrar(Usuario usuario) {
        usuarioController.cadastrarUsuario(usuario);
        jogadorController.cadastrarJogador(usuario.getJogador());
    }

    public void sincronizarJogadores() {
        jogadorController.limparJogadores();
        for (Usuario u : usuarioController.getUsuarios().values()) {
            jogadorController.cadastrarJogador(u.getJogador());
        }
    }
}
