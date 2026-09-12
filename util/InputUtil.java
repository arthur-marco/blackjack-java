package util;

import java.util.Scanner;

public class InputUtil {

    private final Scanner scanner;

    public InputUtil(Scanner scanner) {
        this.scanner = scanner;
    }

    public int lerInteiro() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida! Digite um número inteiro.");
            return -1;
        }
    }

    public double lerDouble() {
        try {
            return Double.parseDouble(scanner.nextLine().trim().replace(",", "."));
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida! Digite um valor numérico.");
            return 0;
        }
    }

    public String lerTexto() {
        return scanner.nextLine().trim();
    }
}
