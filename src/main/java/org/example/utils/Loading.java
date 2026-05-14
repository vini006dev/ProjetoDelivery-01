package org.example.utils;

public class Loading {

    public static void limparTela() {
        for (int i = 0; i < 35; i++) {
            System.out.println();
        }
    }

    public static void LimparTerminal(String mensagem) {
        limparTela();
        System.out.println(mensagem);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        limparTela();
    }

    public static void logoInfDados(){
        LimparTerminal("->->->->->->->->->->->->->->");
        System.out.println("+---------------------------------+");
        System.out.println("|            DELIVERY             |");
        System.out.println("+---------------------------------+");
        System.out.println("|Informe os Dados                 |");
        System.out.println("+---------------------------------+");

    }

    public static void linhaUm(){
        System.out.println("+---------------------------------+");
    }

}