package model;

import interfaces.Autenticavel;

import java.io.Serializable;

public class Usuario extends Pessoa implements Autenticavel, Serializable {

    private static final long serialVersionUID = 1L;

    private String usuario;
    private String senha;
    private String email;
    private Jogador jogador;

    public Usuario(int id, String nome, String usuario, String senha, String email) {
        super(id, nome);
        this.usuario = usuario;
        this.senha = senha;
        this.email = email;
        this.jogador = new Jogador(id, nome);
    }

    @Override
    public boolean login(String usuario, String senha) {
        return this.usuario.equals(usuario) && this.senha.equals(senha);
    }

    public Jogador getJogador() { return jogador; }
    public String getUsuario() { return usuario; }
    public String getSenha() { return senha; }
    public String getEmail() { return email; }
    public void setSenha(String senha) { this.senha = senha; }

    @Override
    public String toString() {
        return "ID: " + id +
                " | Nome: " + nome +
                " | Usuario: " + usuario +
                " | Email: " + email;
    }
}
