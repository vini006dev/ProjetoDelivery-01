package org.example.telas;

import org.example.models.Pedido;
import org.example.models.Restaurante;
import org.example.services.RestauranteService;
import org.example.utils.Input;
import java.util.List;
import org.example.services.PedidoService;
import org.example.utils.Loading;
import org.example.utils.StatusPedido;

import static org.example.utils.Input.lerOpcao;
import static org.example.utils.Loading.limparTela;

public class InterfaceRestaurante {

    private PedidoService pedidoService = new PedidoService();
    private RestauranteService restauranteService = new RestauranteService();
    private Restaurante restauranteLogado;
    private InterfaceProdutoRestaurante produtoInterface = new InterfaceProdutoRestaurante();

    // =========================================================
    // UTILITARIO — leitura segura de texto nao vazio
    // evita campos em branco no cadastro
    // =========================================================
    private String lerTexto(String campo) {
        while (true) {
            String valor = Input.scanner.nextLine();
            if (!valor.trim().isEmpty()) {
                return valor;
            }
            System.out.print("  > " + campo + " não pode ser vazio: ");
        }
    }

    // =========================================================
    // INICIO
    // =========================================================
    public void inicioRestaurante() {
        boolean rodando = true;

        while (rodando) {
            Loading.LimparTerminal("Carregando...");

            System.out.println("┌──────────────────────────────────┐");
            System.out.println("│         COMIDINHA                │");
            System.out.println("│           > Restaurante          │");
            System.out.println("├──────────────────────────────────┤");
            System.out.println("│                                  │");
            System.out.println("│  [1] Entrar                      │");
            System.out.println("│  [2] Criar Conta                 │");
            System.out.println("│                                  │");
            System.out.println("│  [0] Voltar                      │");
            System.out.println("│                                  │");
            System.out.println("└──────────────────────────────────┘");
            System.out.print("  > ");

            int opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    exibirLogin();
                    break;
                case 2:
                    exibirCadastro();
                    break;
                case 0:
                    Loading.LimparTerminal("Voltando...");
                    rodando = false;
                    break;
                default:
                    System.out.println("  > Opção inválida! Escolha 0, 1 ou 2.");
            }
        }
    }

    // =========================================================
    // LOGIN
    // =========================================================
    public void exibirLogin() {
        Loading.LimparTerminal("Carregando login...");

        System.out.println("┌──────────────────────────────────┐");
        System.out.println("│        LOGIN - RESTAURANTE       │");
        System.out.println("├──────────────────────────────────┤");
        System.out.println("│  Informe suas credenciais:       │");
        System.out.println("└──────────────────────────────────┘");

        Input.scanner.nextLine();

        System.out.print("  > Email: ");
        String email = lerTexto("Email");

        System.out.print("  > Senha: ");
        String senha = lerTexto("Senha");

        Loading.LimparTerminal("Autenticando...");

        Restaurante restaurante = restauranteService.login(email, senha);

        if (restaurante != null) {
            restauranteLogado = restaurante;

            System.out.println("┌──────────────────────────────────┐");
            System.out.printf ("│  Bem-vindo, %-21s│%n", restaurante.getNome() + "!");
            System.out.println("└──────────────────────────────────┘");
            System.out.println("  [ENTER] Continuar...");
            Input.scanner.nextLine();

            exibirMenu();
        } else {
            System.out.println("┌──────────────────────────────────┐");
            System.out.println("│  > Email ou senha incorretos!    │");
            System.out.println("│  > Verifique e tente novamente.  │");
            System.out.println("└──────────────────────────────────┘");
            System.out.println("  [ENTER] Voltar...");
            Input.scanner.nextLine();
        }
    }

    // =========================================================
    // CADASTRO
    // =========================================================
    public void exibirCadastro() {
        Loading.LimparTerminal("Carregando cadastro...");

        System.out.println("┌──────────────────────────────────┐");
        System.out.println("│       CADASTRO - RESTAURANTE     │");
        System.out.println("├──────────────────────────────────┤");
        System.out.println("│  Preencha os dados abaixo:       │");
        System.out.println("└──────────────────────────────────┘");

        Input.scanner.nextLine();

        Restaurante restaurante = new Restaurante();

        System.out.print("  > Nome do restaurante: ");
        restaurante.setNome(lerTexto("Nome"));

        System.out.print("  > Endereço: ");
        restaurante.setEndereco(lerTexto("Endereço"));

        System.out.print("  > CNPJ: ");
        restaurante.setCnpj(lerTexto("CNPJ"));

        System.out.print("  > Telefone: ");
        restaurante.setTelefone(lerTexto("Telefone"));

        System.out.print("  > Categoria: ");
        restaurante.setCategoria(lerTexto("Categoria"));

        System.out.print("  > Email: ");
        restaurante.setEmail(lerTexto("Email"));

        System.out.print("  > Senha: ");
        restaurante.setSenha(lerTexto("Senha"));

        restauranteService.cadastrar(restaurante);

        Loading.LimparTerminal("Salvando dados...");

        System.out.println("┌──────────────────────────────────┐");
        System.out.println("│       CADASTRO REALIZADO!        │");
        System.out.println("├──────────────────────────────────┤");
        System.out.printf ("│  Nome:      %-21s│%n", restaurante.getNome());
        System.out.printf ("│  CNPJ:      %-21s│%n", restaurante.getCnpj());
        System.out.printf ("│  Email:     %-21s│%n", restaurante.getEmail());
        System.out.printf ("│  Telefone:  %-21s│%n", restaurante.getTelefone());
        System.out.printf ("│  Categoria: %-21s│%n", restaurante.getCategoria());
        System.out.println("└──────────────────────────────────┘");
        System.out.println("  [ENTER] Continuar...");
        Input.scanner.nextLine();
    }

    // =========================================================
    // MENU PRINCIPAL
    // =========================================================
    public void exibirMenu() {
        if (restauranteLogado == null) {
            System.out.println("┌──────────────────────────────────┐");
            System.out.println("│  > Faça login primeiro!          │");
            System.out.println("└──────────────────────────────────┘");
            return;
        }

        boolean rodando = true;

        while (rodando) {
            Loading.LimparTerminal("Carregando menu...");

            System.out.println("┌──────────────────────────────────┐");
            System.out.println("│         MENU - RESTAURANTE       │");
            System.out.printf ("│  Olá, %-27s│%n", restauranteLogado.getNome() + "!");
            System.out.println("├──────────────────────────────────┤");
            System.out.println("│                                  │");
            System.out.println("│  [1] Ver pedidos                 │");
            System.out.println("│  [2] Gerenciar pedidos           │");
            System.out.println("│  [3] Gerenciar cardápio          │");
            System.out.println("│                                  │");
            System.out.println("│  [0] Sair                        │");
            System.out.println("│                                  │");
            System.out.println("└──────────────────────────────────┘");
            System.out.print("  > ");

            int opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    visualizarPedidos();
                    break;
                case 2:
                    menuPedidos();
                    break;
                case 3:
                    produtoInterface.menu(restauranteLogado);
                    break;
                case 0:
                    Loading.LimparTerminal("Saindo...");
                    rodando = false;
                    break;
                default:
                    System.out.println("  > Opção inválida! Escolha entre 0 e 3.");
            }
        }
    }

    // =========================================================
    // MENU PEDIDOS
    // =========================================================
    public void menuPedidos() {
        if (restauranteLogado == null) {
            System.out.println("┌──────────────────────────────────┐");
            System.out.println("│  > Faça login primeiro!          │");
            System.out.println("└──────────────────────────────────┘");
            return;
        }

        boolean rodando = true;

        while (rodando) {
            Loading.LimparTerminal("Carregando pedidos...");

            System.out.println("┌──────────────────────────────────┐");
            System.out.println("│         GERENCIAR PEDIDOS        │");
            System.out.println("├──────────────────────────────────┤");
            System.out.println("│                                  │");
            System.out.println("│  [1] Ver pedidos                 │");
            System.out.println("│  [2] Aceitar pedido              │");
            System.out.println("│  [3] Finalizar pedido            │");
            System.out.println("│  [4] Cancelar pedido             │");
            System.out.println("│                                  │");
            System.out.println("│  [0] Voltar                      │");
            System.out.println("│                                  │");
            System.out.println("└──────────────────────────────────┘");
            System.out.print("  > ");

            int opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    visualizarPedidos();
                    break;
                case 2:
                    aceitarPedido();
                    break;
                case 3:
                    finalizarPedido();
                    break;
                case 4:
                    cancelarPedido();
                    break;
                case 0:
                    Loading.LimparTerminal("Voltando...");
                    rodando = false;
                    break;
                default:
                    System.out.println("  > Opção inválida! Escolha entre 0 e 4.");
            }
        }
    }

    // =========================================================
    // VISUALIZAR PEDIDOS
    // =========================================================
    public void visualizarPedidos() {
        if (restauranteLogado == null) {
            System.out.println("┌──────────────────────────────────┐");
            System.out.println("│  > Faça login primeiro!          │");
            System.out.println("└──────────────────────────────────┘");
            return;
        }

        Loading.LimparTerminal("Buscando pedidos...");

        List<Pedido> pedidos = pedidoService.listarPorRestaurante(restauranteLogado.getId());

        System.out.println("┌──────────────────────────────────┐");
        System.out.println("│           SEUS PEDIDOS           │");
        System.out.println("├──────────────────────────────────┤");

        if (pedidos.isEmpty()) {
            System.out.println("│  > Nenhum pedido encontrado.     │");
            System.out.println("└──────────────────────────────────┘");
        } else {
            for (Pedido p : pedidos) {
                System.out.printf("│  ID:      %-23s│%n", "#" + String.format("%04d", p.getId()));
                System.out.printf("│  Cliente: %-23s│%n", p.getIdCliente());
                System.out.printf("│  Status:  %-23s│%n", p.getStatus());
                System.out.println("├──────────────────────────────────┤");
            }
            System.out.println("└──────────────────────────────────┘");
        }

        System.out.println("  [ENTER] Voltar...");
        Input.scanner.nextLine();
        Input.scanner.nextLine();
    }

    // =========================================================
    // ACEITAR PEDIDO
    // =========================================================
    public void aceitarPedido() {
        Loading.LimparTerminal("Carregando...");

        System.out.println("┌──────────────────────────────────┐");
        System.out.println("│          ACEITAR PEDIDO          │");
        System.out.println("│  Digite 0 para voltar.           │");
        System.out.println("└──────────────────────────────────┘");
        System.out.print("  > ID do pedido: #");

        int id = lerOpcao();

        if (id == 0) {
            System.out.println("  > Operação cancelada.");
            return;
        }

        pedidoService.atualizarStatus(id, StatusPedido.EM_PREPARO);
        Loading.LimparTerminal("Processando...");

        System.out.println("┌──────────────────────────────────┐");
        System.out.printf ("│  Pedido #%04d aceito!            │%n", id);
        System.out.println("│  > Status: Em preparo            │");
        System.out.println("└──────────────────────────────────┘");
        System.out.println("  [ENTER] Continuar...");
        Input.scanner.nextLine();
        Input.scanner.nextLine();
    }

    // =========================================================
    // CANCELAR PEDIDO
    // =========================================================
    public void cancelarPedido() {
        Loading.LimparTerminal("Carregando...");

        System.out.println("┌──────────────────────────────────┐");
        System.out.println("│          CANCELAR PEDIDO         │");
        System.out.println("│  Digite 0 para voltar.           │");
        System.out.println("└──────────────────────────────────┘");
        System.out.print("  > ID do pedido: #");

        int id = lerOpcao();

        if (id == 0) {
            System.out.println("  > Operação cancelada.");
            return;
        }

        pedidoService.atualizarStatus(id, StatusPedido.CANCELADO);
        Loading.LimparTerminal("Processando...");

        System.out.println("┌──────────────────────────────────┐");
        System.out.printf ("│  Pedido #%04d cancelado!         │%n", id);
        System.out.println("│  > Status: Cancelado             │");
        System.out.println("└──────────────────────────────────┘");
        System.out.println("  [ENTER] Continuar...");
        Input.scanner.nextLine();
        Input.scanner.nextLine();
    }

    // =========================================================
    // FINALIZAR PEDIDO
    // =========================================================
    public void finalizarPedido() {
        Loading.LimparTerminal("Carregando...");

        System.out.println("┌──────────────────────────────────┐");
        System.out.println("│         FINALIZAR PEDIDO         │");
        System.out.println("│  Digite 0 para voltar.           │");
        System.out.println("└──────────────────────────────────┘");
        System.out.print("  > ID do pedido: #");

        int id = lerOpcao();

        if (id == 0) {
            System.out.println("  > Operação cancelada.");
            return;
        }

        pedidoService.atualizarStatus(id, StatusPedido.PRONTO);
        Loading.LimparTerminal("Processando...");

        System.out.println("┌──────────────────────────────────┐");
        System.out.printf ("│  Pedido #%04d finalizado!        │%n", id);
        System.out.println("│  > Disponivel para entrega!      │");
        System.out.println("└──────────────────────────────────┘");
        System.out.println("  [ENTER] Continuar...");
        Input.scanner.nextLine();
        Input.scanner.nextLine();
    }
}