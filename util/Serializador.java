package util;

import java.io.*;
import java.util.ArrayList;

public class Serializador {

    private Serializador() {

    }

    public static <T extends Serializable> void salvar(ArrayList<T> lista, String caminho) {
        File arquivo = new File(caminho);
        arquivo.getParentFile().mkdirs();

        try (ObjectOutputStream oos = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(arquivo)))) {
            oos.writeObject(lista);
        } catch (IOException e) {
            System.out.println("Erro ao salvar arquivo '" + caminho + "': " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static <T extends Serializable> ArrayList<T> carregar(String caminho) {
        File arquivo = new File(caminho);

        if (!arquivo.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream(arquivo)))) {
            return (ArrayList<T>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar arquivo '" + caminho + "': " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
