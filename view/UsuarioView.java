package view;

import controller.LoginController;
import model.Usuario;
import service.UsuarioService;
import util.InputUtil;

public class UsuarioView {

    private final UsuarioService  usuarioService;
    private final LoginController loginController;
    private final InputUtil        input;

    public UsuarioView(UsuarioService usuarioService,
                       LoginController loginController,
                       InputUtil input) {
        this.usuarioService  = usuarioService;
        this.loginController = loginController;
        this.input           = input;
    }

    public void exibirCadastro() {
        System.out.println("\n=== CADASTRO ===");

        System.out.print("ID: ");
        int id = input.lerInteiro();

        System.out.print("Nome: ");
        String nome = input.lerTexto();

        System.out.print("Usuário: ");
        String usuario = input.lerTexto();

        String senha;
        do {
            System.out.print("Senha (mínimo 6 caracteres): ");
            senha = input.lerTexto();
            if (!loginController.validarSenha(senha)) {
                System.out.println("Senha muito curta! Tente novamente.");
            }
        } while (!loginController.validarSenha(senha));

        System.out.print("Email: ");
        String email = input.lerTexto();

        usuarioService.registrar(new Usuario(id, nome, usuario, senha, email));
        System.out.println("\nUsuário cadastrado com sucesso!");
    }

    public void exibirEdicaoPerfil(Usuario usuario) {
        System.out.println("\n=== EDITAR PERFIL ===");
        System.out.println("Nome atual: " + usuario.getNome());
        System.out.print("Novo nome: ");
        usuario.setNome(input.lerTexto());
        System.out.println("Perfil atualizado!");
    }
}
