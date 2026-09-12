package view;

import controller.MesaController;
import model.Mesa;
import util.InputUtil;

public class MesaView {

    private final MesaController mesaController;
    private final InputUtil       input;

    public MesaView(MesaController mesaController, InputUtil input) {
        this.mesaController = mesaController;
        this.input          = input;
    }

    public void exibirListagem() {
        System.out.println("\n=== MESAS DISPONÍVEIS ===");
        mesaController.listarMesas();
    }

    public void exibirCriacao() {
        System.out.print("ID da mesa: ");
        int    id     = input.lerInteiro();
        System.out.print("Nome: ");
        String nome   = input.lerTexto();
        System.out.print("Aposta mínima: R$ ");
        double minima = input.lerDouble();
        mesaController.criarMesa(new Mesa(id, nome, minima));
        System.out.println("Mesa criada com sucesso!");
    }

    public void exibirRemocao() {
        System.out.print("ID da mesa a remover: ");
        int id = input.lerInteiro();
        mesaController.removerMesa(id);
        System.out.println("Mesa removida!");
    }

    public void exibirEdicaoLimite() {
        System.out.print("ID da mesa: ");
        int    id     = input.lerInteiro();
        System.out.print("Novo limite: R$ ");
        double limite = input.lerDouble();
        mesaController.editarLimite(id, limite);
        System.out.println("Limite atualizado!");
    }
}
