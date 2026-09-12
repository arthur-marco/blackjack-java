import controller.*;
import model.*;
import service.UsuarioService;
import util.InputUtil;
import view.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        InputUtil input = new InputUtil(new Scanner(System.in));

        UsuarioController   usuarioController   = new UsuarioController();
        SessaoLogin         sessao              = new SessaoLogin();
        LoginController     loginController     = new LoginController(sessao);
        HistoricoController historicoController = new HistoricoController();
        CarteiraController  carteiraController  = new CarteiraController();
        MesaController      mesaController      = new MesaController();
        PartidaController   partidaController   = new PartidaController();
        BaralhoController   baralhoController   = new BaralhoController();
        AdminController     adminController     = new AdminController();
        JogadorController   jogadorController   = new JogadorController();
        ApostaController    apostaController    = new ApostaController();

        mesaController.criarMesa(new Mesa(1, "Mesa Iniciante",     10));
        mesaController.criarMesa(new Mesa(2, "Mesa Intermediária", 50));
        mesaController.criarMesa(new Mesa(3, "Mesa High Roller",  200));

        Administrador administrador = new Administrador(0, "Admin", "admin", "admin123");

        UsuarioService usuarioService = new UsuarioService(usuarioController, jogadorController);
        usuarioService.sincronizarJogadores();

        LoginView     loginView     = new LoginView(loginController, usuarioController, input);
        UsuarioView   usuarioView   = new UsuarioView(usuarioService, loginController, input);
        CarteiraView  carteiraView  = new CarteiraView(carteiraController, input);
        AdminView     adminView     = new AdminView(adminController, jogadorController, input);
        JogadorView   jogadorView   = new JogadorView(jogadorController, historicoController, input);
        HistoricoView historicoView = new HistoricoView(historicoController, jogadorController);

        BlackjackView blackjackView = new BlackjackView(
                baralhoController, mesaController, partidaController,
                apostaController, historicoController, usuarioController, input);

        MenuView menuView = new MenuView(
                sessao, loginController, usuarioController,
                historicoController, jogadorController,
                loginView, usuarioView, carteiraView,
                blackjackView, adminView, jogadorView, historicoView,
                administrador, input);

        menuView.exibirTelaInicial();
    }
}
