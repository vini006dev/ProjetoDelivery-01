package org.example.utils;

import java.util.Scanner;

public class Input {

    public static Scanner scanner =
            new Scanner(System.in);

    // =========================================================
    // INTEIROS
    // =========================================================
    public static int lerOpcao() {

        while (true) {

            try {

                return Integer.parseInt(
                        scanner.nextLine()
                );

            } catch (Exception e) {

                System.out.println(
                        "  > Digite apenas números!"
                );

                System.out.print("  > ");
            }
        }
    }

    // =========================================================
    // DOUBLE
    // =========================================================
    public static double lerDouble() {

        while (true) {

            try {

                return Double.parseDouble(
                        scanner.nextLine()
                                .replace(",", ".")
                );

            } catch (Exception e) {

                System.out.println(
                        "  > Digite um valor válido!"
                );

                System.out.print("  > ");
            }
        }
    }

    // =========================================================
    // TEXTO
    // =========================================================
    public static String lerTexto(String campo) {

        while (true) {

            String valor =
                    scanner.nextLine();

            if (!valor.trim().isEmpty()) {

                return valor;
            }

            System.out.print(
                    "  > " + campo +
                            " não pode ser vazio: "
            );
        }
    }
}