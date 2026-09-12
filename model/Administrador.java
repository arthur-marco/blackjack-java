package model;

import interfaces.Autenticavel;

public class Administrador extends Pessoa implements Autenticavel {

    private String usuario;
    private String senha;

    public Administrador(int id, String nome, String usuario, String senha) {
        super(id, nome);
        this.usuario = usuario;
        this.senha = senha;
    }

    @Override
    public boolean login(String usuario, String senha) {
        return this.usuario.equals(usuario) && this.senha.equals(senha);
    }
}
