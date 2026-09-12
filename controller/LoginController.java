package controller;

import model.SessaoLogin;
import model.Usuario;

import java.util.HashMap;

public class LoginController {

    private SessaoLogin sessao;

    public LoginController(SessaoLogin sessao) {
        this.sessao = sessao;
    }

    public boolean autenticar(
            String usuario,
            String senha,
            HashMap<Integer, Usuario> usuarios) {

        for (Usuario u : usuarios.values()) {
            if (u.login(usuario, senha)) {
                sessao.login(u);
                return true;
            }
        }

        return false;
    }

    public void logout() {
        sessao.logout();
    }

    public boolean validarSenha(String senha) {
        return senha != null && senha.length() >= 6;
    }
}
