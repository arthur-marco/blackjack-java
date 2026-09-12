package persistence;

import model.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.regex.*;

public class JsonPersistencia {

    public static final String DIR           = "data";
    public static final String USUARIOS_JSON = DIR + "/usuarios.json";
    public static final String PARTIDAS_JSON = DIR + "/partidas.json";
    public static final String RANKING_JSON  = DIR + "/ranking.json";

    private JsonPersistencia() {}

    public static void salvarUsuarios(ArrayList<Usuario> lista) {
        StringBuilder sb = new StringBuilder("[\n");
        for (int i = 0; i < lista.size(); i++) {
            Usuario u = lista.get(i);
            Jogador j = u.getJogador();
            sb.append("  {\n");
            sb.append("    \"id\": ").append(u.getId()).append(",\n");
            sb.append("    \"nome\": \"").append(esc(u.getNome())).append("\",\n");
            sb.append("    \"usuario\": \"").append(esc(u.getUsuario())).append("\",\n");
            sb.append("    \"senha\": \"").append(esc(u.getSenha())).append("\",\n");
            sb.append("    \"email\": \"").append(esc(u.getEmail())).append("\",\n");
            sb.append("    \"vitorias\": ").append(j.getVitorias()).append(",\n");
            sb.append("    \"derrotas\": ").append(j.getDerrotas()).append(",\n");
            sb.append("    \"saldo\": ").append(j.getCarteira().getSaldo()).append("\n");
            sb.append("  }");
            if (i < lista.size() - 1) sb.append(",");
            sb.append("\n");
        }
        sb.append("]");
        escrever(USUARIOS_JSON, sb.toString());
    }

    public static ArrayList<Usuario> carregarUsuarios() {
        ArrayList<Usuario> lista = new ArrayList<>();
        String conteudo = ler(USUARIOS_JSON);
        if (conteudo == null) return lista;

        for (String bloco : dividirBlocos(conteudo)) {
            try {
                int    id       = Integer.parseInt(extrair(bloco, "id"));
                String nome     = extrair(bloco, "nome");
                String usuario  = extrair(bloco, "usuario");
                String senha    = extrair(bloco, "senha");
                String email    = extrair(bloco, "email");
                int    vitorias = Integer.parseInt(extrair(bloco, "vitorias"));
                int    derrotas = Integer.parseInt(extrair(bloco, "derrotas"));
                double saldo    = Double.parseDouble(extrair(bloco, "saldo"));

                Usuario u = new Usuario(id, nome, usuario, senha, email);
                u.getJogador().setVitorias(vitorias);
                u.getJogador().setDerrotas(derrotas);
                u.getJogador().getCarteira().setSaldo(saldo);
                lista.add(u);
            } catch (Exception e) {
                System.out.println("Aviso: entrada malformada ignorada em usuarios.json");
            }
        }
        return lista;
    }

    // ================================================================
    // PARTIDAS (HISTÓRICO)
    // ================================================================

    public static void salvarPartidas(ArrayList<HistoricoPartida> lista) {
        StringBuilder sb = new StringBuilder("[\n");
        for (int i = 0; i < lista.size(); i++) {
            HistoricoPartida p = lista.get(i);
            sb.append("  {\n");
            sb.append("    \"nomeJogador\": \"").append(esc(p.getNomeJogador())).append("\",\n");
            sb.append("    \"resultado\": \"").append(p.getResultado()).append("\",\n");
            sb.append("    \"dataHora\": \"").append(p.getDataHora()).append("\"\n");
            sb.append("  }");
            if (i < lista.size() - 1) sb.append(",");
            sb.append("\n");
        }
        sb.append("]");
        escrever(PARTIDAS_JSON, sb.toString());
    }

    public static ArrayList<HistoricoPartida> carregarPartidas() {
        ArrayList<HistoricoPartida> lista = new ArrayList<>();
        String conteudo = ler(PARTIDAS_JSON);
        if (conteudo == null) return lista;

        for (String bloco : dividirBlocos(conteudo)) {
            try {
                String nomeJogador = extrair(bloco, "nomeJogador");
                String resultado   = extrair(bloco, "resultado");
                String dataHora    = extrair(bloco, "dataHora");
                lista.add(new HistoricoPartida(nomeJogador, resultado, dataHora));
            } catch (Exception e) {
                System.out.println("Aviso: entrada malformada ignorada em partidas.json");
            }
        }
        return lista;
    }

    public static void salvarRanking(ArrayList<Jogador> lista) {
        StringBuilder sb = new StringBuilder("[\n");
        for (int i = 0; i < lista.size(); i++) {
            Jogador j = lista.get(i);
            sb.append("  {\n");
            sb.append("    \"posicao\": ").append(i + 1).append(",\n");
            sb.append("    \"nome\": \"").append(esc(j.getNome())).append("\",\n");
            sb.append("    \"vitorias\": ").append(j.getVitorias()).append(",\n");
            sb.append("    \"derrotas\": ").append(j.getDerrotas()).append(",\n");
            sb.append("    \"saldo\": ").append(j.getCarteira().getSaldo()).append("\n");
            sb.append("  }");
            if (i < lista.size() - 1) sb.append(",");
            sb.append("\n");
        }
        sb.append("]");
        escrever(RANKING_JSON, sb.toString());
    }

    static String extrair(String bloco, String chave) {
        Pattern p = Pattern.compile(
            "\"" + Pattern.quote(chave) + "\"\\s*:\\s*\"?([^,}\\n\"]+)\"?"
        );
        Matcher m = p.matcher(bloco);
        return m.find() ? m.group(1).trim() : "";
    }

    static String[] dividirBlocos(String json) {
        ArrayList<String> blocos = new ArrayList<>();
        int profundidade = 0;
        int inicio = -1;
        boolean emString = false;

        for (int i = 0; i < json.length(); i++) {
            char c = json.charAt(i);
            if (emString) {
                if (c == '\\') { i++; continue; }
                if (c == '"')  { emString = false; }
                continue;
            }
            if (c == '"')  { emString = true; continue; }
            if (c == '{')  {
                profundidade++;
                if (profundidade == 1) inicio = i;
            } else if (c == '}') {
                profundidade--;
                if (profundidade == 0 && inicio != -1) {
                    blocos.add(json.substring(inicio, i + 1));
                    inicio = -1;
                }
            }
        }
        return blocos.toArray(new String[0]);
    }

    public static void escrever(String caminho, String conteudo) {
        try {
            Files.createDirectories(Paths.get(DIR));
            Files.write(Paths.get(caminho),
                        conteudo.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            System.out.println("Erro ao salvar '" + caminho + "': " + e.getMessage());
        }
    }

    public static String ler(String caminho) {
        try {
            return new String(
                Files.readAllBytes(Paths.get(caminho)),
                StandardCharsets.UTF_8
            );
        } catch (IOException e) {
            return null;
        }
    }

    private static String esc(String s) {
        if (s == null) return "";
        return s.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}
