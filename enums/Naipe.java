package enums;

public enum Naipe {
    COPAS("Copas"),
    ESPADAS("Espadas"),
    OUROS("Ouros"),
    PAUS("Paus");

    private final String nome;

    Naipe(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return nome;
    }
}
