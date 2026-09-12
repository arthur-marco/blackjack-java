package enums;

public enum ResultadoPartida {
    VITORIA("Vitória"),
    DERROTA("Derrota"),
    EMPATE("Empate");

    private final String descricao;

    ResultadoPartida(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return name();
    }
}
