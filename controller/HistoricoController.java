package controller;

import model.HistoricoPartida;
import model.Jogador;
import persistence.JsonPersistencia;
import repository.HistoricoRepository;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class HistoricoController {

    private final HistoricoRepository repository = new HistoricoRepository();
    private ArrayList<HistoricoPartida> historico;

    public HistoricoController() {
        historico = repository.carregar();
    }

    public void salvarPartida(String nomeJogador, String resultado) {
        historico.add(new HistoricoPartida(nomeJogador, resultado));
        repository.salvar(historico);
    }

    public void consultarHistorico() {
        if (historico.isEmpty()) {
            System.out.println("Nenhuma partida registrada.");
            return;
        }
        System.out.println("\n=== HISTÓRICO DE PARTIDAS ===");
        historico.forEach(System.out::println);
    }

    public void consultarHistoricoDoJogador(String nomeJogador) {
        ArrayList<HistoricoPartida> doJogador = historico.stream()
                .filter(p -> p.getNomeJogador().equalsIgnoreCase(nomeJogador))
                .collect(Collectors.toCollection(ArrayList::new));

        if (doJogador.isEmpty()) {
            System.out.println("Nenhuma partida encontrada para " + nomeJogador + ".");
            return;
        }
        System.out.println("\n=== HISTÓRICO DE " + nomeJogador.toUpperCase() + " ===");
        doJogador.forEach(System.out::println);
    }

    public void ranking(ArrayList<Jogador> jogadores) {
        if (jogadores.isEmpty()) {
            System.out.println("Nenhum jogador cadastrado ainda.");
            return;
        }
        ArrayList<Jogador> ordenados = new ArrayList<>(jogadores);
        ordenados.sort((j1, j2) -> Integer.compare(j2.getVitorias(), j1.getVitorias()));

        System.out.println("\n=== RANKING DE JOGADORES ===");
        for (int i = 0; i < ordenados.size(); i++) {
            Jogador j = ordenados.get(i);
            System.out.printf("%2d. %-20s %3d vitórias | %3d derrotas | R$ %.2f%n",
                    i + 1, j.getNome(),
                    j.getVitorias(), j.getDerrotas(),
                    j.getCarteira().getSaldo());
        }
        JsonPersistencia.salvarRanking(ordenados);
        System.out.println("Ranking salvo em data/ranking.json");
    }
}
