package view;

import controller.LoginController;
import controller.UsuarioController;
import util.InputUtil;

public class LoginView {

    private final LoginController loginController;
    private final UsuarioController usuarioController;
    private final InputUtil input;

    public LoginView(
            LoginController loginController,
            UsuarioController usuarioController,
            InputUtil input) {
        this.loginController = loginController;
        this.usuarioController = usuarioController;
        this.input = input;
    }

    public boolean exibirLogin() {
        System.out.println("\n=== LOGIN ===");
        System.out.print("Usuário: ");
        String usuario = input.lerTexto();
        System.out.print("Senha: ");
        String senha = input.lerTexto();

        boolean sucesso = loginController.autenticar(usuario, senha, usuarioController.getUsuarios());

        System.out.println(sucesso ? "\nLogin realizado com sucesso!" : "\nUsuário ou senha inválidos!");
        return sucesso;
    }
}
