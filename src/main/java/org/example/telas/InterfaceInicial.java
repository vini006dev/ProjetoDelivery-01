package org.example.telas;

import org.example.utils.Input;
import org.example.utils.Loading;

import static org.example.utils.Input.lerOpcao;

public class InterfaceInicial {

    // =========================================================
    // MENU INICIAL
    // =========================================================
    public void opcoesInicial() {
        boolean rodando = true;

        while (rodando) {
            Loading.LimparTerminal("Inicializando sistema...");

            System.out.println("┌──────────────────────────────────┐");
            System.out.println("│           COMIDINHA              │");
            System.out.println("│   Delivery rápido e simples      │");
            System.out.println("├──────────────────────────────────┤");
            System.out.println("│                                  │");
            System.out.println("│  [1] Restaurante                 │");
            System.out.println("│  [2] Entregador                  │");
            System.out.println("│  [3] Cliente                     │");
            System.out.println("│                                  │");
            System.out.println("│  [0] Sair                        │");
            System.out.println("│                                  │");
            System.out.println("└──────────────────────────────────┘");
            System.out.print("  > ");

            int opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    Loading.LimparTerminal("Carregando...");
                    InterfaceRestaurante restaurante = new InterfaceRestaurante();
                    restaurante.inicioRestaurante();
                    break;
                case 2:
                    Loading.LimparTerminal("Carregando...");
                    InterfaceEntregador entregador = new InterfaceEntregador();
                    entregador.inicioEntregador();
                    break;
                case 3:
                    Loading.LimparTerminal("Carregando...");
                    InterfaceCliente cliente = new InterfaceCliente();
                    cliente.inicioCliente();
                    break;
                case 0:
                    Loading.LimparTerminal("Encerrando sistema...");
                    System.out.println("┌──────────────────────────────────┐");
                    System.out.println("│            Até logo!             │");
                    System.out.println("│  Obrigado por usar o Comidinha   │");
                    System.out.println("└──────────────────────────────────┘");
                    rodando = false;
                    break;
                default:
                    System.out.println("  > Opção inválida! Escolha entre 0 e 3.");
            }
        }
    }
}