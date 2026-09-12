package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HistoricoPartida implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final DateTimeFormatter FORMATO =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private String nomeJogador;
    private String resultado;
    private String dataHora;

    public HistoricoPartida(String nomeJogador, String resultado) {
        this.nomeJogador = nomeJogador;
        this.resultado   = resultado;
        this.dataHora    = LocalDateTime.now().format(FORMATO);
    }

    public HistoricoPartida(String nomeJogador, String resultado, String dataHora) {
        this.nomeJogador = nomeJogador;
        this.resultado   = resultado;
        this.dataHora    = dataHora;
    }

    public String getNomeJogador() { return nomeJogador; }
    public String getResultado()   { return resultado; }
    public String getDataHora()    { return dataHora; }

    @Override
    public String toString() {
        return "[" + dataHora + "] " + nomeJogador + " - " + resultado;
    }
}
