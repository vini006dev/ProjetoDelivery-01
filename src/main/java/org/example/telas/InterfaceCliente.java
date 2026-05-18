package org.example.telas;

import org.example.models.*;
import org.example.services.*;
import org.example.utils.Input;
import org.example.utils.Loading;

import java.util.ArrayList;
import java.util.List;

import static org.example.utils.Input.lerOpcao;


public class InterfaceCliente {

    private ClienteService clienteService =
            new ClienteService();

    private RestauranteService restauranteService =
            new RestauranteService();

    private ProdutoService produtoService =
            new ProdutoService();

    private PedidoService pedidoService =
            new PedidoService();

    private Cliente clienteLogado;

    private ItemPedidoService itemPedidoService =
            new ItemPedidoService();

    public void inicioCliente() {

        boolean rodando = true;

        while (rodando) {

            Loading.LimparTerminal("Carregando...");

            System.out.println("┌──────────────────────────────────┐");
            System.out.println("│          COMIDINHA               │");
            System.out.println("│             > Cliente            │");
            System.out.println("├──────────────────────────────────┤");
            System.out.println("│                                  │");
            System.out.println("│  [1] Entrar                      │");
            System.out.println("│  [2] Criar Conta                 │");
            System.out.println("│                                  │");
            System.out.println("│  [0] Voltar                      │");
            System.out.println("│                                  │");
            System.out.println("└──────────────────────────────────┘");

            System.out.print("  > ");

            int opcaoInicialCliente = lerOpcao();

            switch (opcaoInicialCliente) {

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


    public void exibirLogin() {

        Loading.LimparTerminal("Carregando login...");

        System.out.println("┌──────────────────────────────────┐");
        System.out.println("│          LOGIN CLIENTE           │");
        System.out.println("├──────────────────────────────────┤");
        System.out.println("│  Informe suas credenciais        │");
        System.out.println("└──────────────────────────────────┘");

        System.out.print("  > Email: ");
        String email = Input.lerTexto("Email");

        System.out.print("  > Senha: ");
        String senha = Input.lerTexto("Senha");

        Loading.LimparTerminal("Autenticando...");

        Cliente cliente =
                clienteService.login(email, senha);

        if (cliente != null) {

            clienteLogado = cliente;

            System.out.println("┌──────────────────────────────────┐");
            System.out.printf ("│  Bem-vindo, %-21s│%n",
                    cliente.getNome() + "!");
            System.out.println("└──────────────────────────────────┘");

            System.out.println("  [ENTER] Continuar...");
            Input.scanner.nextLine();

            exibirMenu();

        } else {

            System.out.println("┌──────────────────────────────────┐");
            System.out.println("│  Usuário não encontrado!         │");
            System.out.println("│  Verifique os dados informados.  │");
            System.out.println("└──────────────────────────────────┘");

            System.out.println("  [ENTER] Voltar...");
            Input.scanner.nextLine();
        }
    }

    public void exibirCadastro() {

        Loading.LimparTerminal("Carregando cadastro...");

        System.out.println("┌──────────────────────────────────┐");
        System.out.println("│         CADASTRO CLIENTE         │");
        System.out.println("├──────────────────────────────────┤");
        System.out.println("│  Preencha os dados abaixo        │");
        System.out.println("└──────────────────────────────────┘");

        Cliente cliente = new Cliente();

        System.out.print("  > Nome: ");
        cliente.setNome(
                Input.lerTexto("Nome")
        );

        System.out.print("  > CPF: ");
        cliente.setCpf(
                Input.lerTexto("CPF")
        );

        System.out.print("  > Email: ");
        cliente.setEmail(
                Input.lerTexto("Email")
        );

        System.out.print("  > Senha: ");
        cliente.setSenha(
                Input.lerTexto("Senha")
        );

        System.out.print("  > Telefone: ");
        cliente.setTelefone(
                Input.lerTexto("Telefone")
        );

        System.out.print("  > Rua: ");
        cliente.setRua(
                Input.lerTexto("Rua")
        );

        System.out.print("  > Número: ");
        cliente.setNumero(
                Input.lerTexto("Número")
        );

        System.out.print("  > Bairro: ");
        cliente.setBairro(
                Input.lerTexto("Bairro")
        );

        System.out.print("  > Cidade: ");
        cliente.setCidade(
                Input.lerTexto("Cidade")
        );

        Loading.LimparTerminal("Salvando dados...");

        clienteService.cadastrar(cliente);

        System.out.println("┌──────────────────────────────────┐");
        System.out.println("│      CADASTRO REALIZADO!         │");
        System.out.println("├──────────────────────────────────┤");
        System.out.printf ("│  Nome: %-26s│%n",
                cliente.getNome());

        System.out.printf ("│  Email: %-25s│%n",
                cliente.getEmail());

        System.out.printf ("│  Cidade: %-24s│%n",
                cliente.getCidade());

        System.out.println("└──────────────────────────────────┘");

        System.out.println("  [ENTER] Continuar...");
        Input.scanner.nextLine();
    }

    public void exibirMenu() {

        boolean rodando = true;

        while (rodando) {

            Loading.LimparTerminal("Carregando menu...");

            System.out.println("┌──────────────────────────────────┐");
            System.out.println("│         COMIDINHA                │");

            System.out.printf (
                    "│ Cliente: %-24s│%n",
                    clienteLogado.getNome()
            );

            System.out.println("├──────────────────────────────────┤");
            System.out.println("│                                  │");
            System.out.println("│  [1] Restaurantes                │");
            System.out.println("│  [2] Meus Pedidos                │");
            System.out.println("│                                  │");
            System.out.println("│  [0] Sair                        │");
            System.out.println("│                                  │");
            System.out.println("└──────────────────────────────────┘");

            System.out.print("  > ");

            int opcaoInicial =
                    Input.lerOpcao();

            switch (opcaoInicial) {

                case 1:

                    listarRestaurantes();
                    break;

                case 2:

                    meusPedidos();
                    break;

                case 0:

                    Loading.LimparTerminal(
                            "Saindo..."
                    );

                    rodando = false;
                    break;

                default:

                    System.out.println(
                            "  > Opção inválida!"
                    );

                    try {

                        Thread.sleep(1200);

                    } catch (InterruptedException e) {

                        e.printStackTrace();
                    }
            }
        }
    }

    public void listarRestaurantes() {

        Loading.LimparTerminal(
                "Buscando restaurantes..."
        );

        List<Restaurante> restaurantes =
                restauranteService.listar();

        System.out.println("┌──────────────────────────────────┐");
        System.out.println("│          RESTAURANTES            │");
        System.out.println("├──────────────────────────────────┤");

        if (restaurantes.isEmpty()) {

            System.out.println("│  Nenhum restaurante encontrado.  │");

        } else {

            for (Restaurante r : restaurantes) {

                System.out.printf (
                        "│  ID: %-28s %n",
                        "#" + r.getId()
                );

                System.out.printf (
                        "│  Nome: %-26s %n",
                        r.getNome()
                );

                System.out.printf (
                        "│  Endereço: %-22s %n",
                        r.getEndereco()
                );

                System.out.println("├──────────────────────────────────┤");
            }
        }

        System.out.println("│                                  │");
        System.out.println("│  [1] Escolher Restaurante        │");
        System.out.println("│  [0] Voltar                      │");
        System.out.println("│                                  │");
        System.out.println("└──────────────────────────────────┘");

        System.out.print("  > ");

        int opcao =
                Input.lerOpcao();

        switch (opcao) {

            case 1:

                System.out.print(
                        "\n  > ID do restaurante: "
                );

                int idRestaurante =
                        Input.lerOpcao();

                fazerPedido(
                        idRestaurante
                );

                break;

            case 0:

                return;

            default:

                System.out.println(
                        "\n  > Opção inválida!"
                );

                try {

                    Thread.sleep(1200);

                } catch (InterruptedException e) {

                    e.printStackTrace();
                }
        }
    }


    public void fazerPedido(int idRestaurante) {

        Loading.LimparTerminal(
                "Carregando cardápio..."
        );

        List<Produto> produtos =
                produtoService
                        .listarPorRestaurante(
                                idRestaurante
                        );

        boolean adicionando = true;

        List<Produto> carrinho =
                new ArrayList<>();

        double total = 0;

        System.out.println("┌──────────────────────────────────┐");
        System.out.println("│             CARDÁPIO             │");
        System.out.println("├──────────────────────────────────┤");

        if (produtos.isEmpty()) {

            System.out.println("│  Nenhum produto encontrado.      │");
            System.out.println("└──────────────────────────────────┘");

            System.out.println("\n  [ENTER] Voltar...");
            Input.scanner.nextLine();

            return;
        }

        for (Produto p : produtos) {

            System.out.printf (
                    "│  ID: %-28s %n",
                    "#" + p.getId()
            );

            System.out.printf (
                    "│  Produto: %-23s %n",
                    p.getNome()
            );

            System.out.printf (
                    "│  Preço: R$ %-20.2f %n",
                    p.getPreco()
            );

            System.out.println("├──────────────────────────────────┤");
        }

        System.out.println("└──────────────────────────────────┘");

        while (adicionando) {

            System.out.print(
                    "\n  > ID do produto: "
            );

            int idProduto =
                    Input.lerOpcao();

            Produto produtoEscolhido =
                    null;

            for (Produto p : produtos) {

                if (p.getId() == idProduto) {

                    produtoEscolhido = p;
                    break;
                }
            }

            if (produtoEscolhido != null) {

                carrinho.add(
                        produtoEscolhido
                );

                total +=
                        produtoEscolhido.getPreco();

                System.out.println(
                        "\n  > Produto adicionado!"
                );

                System.out.println(
                        "  > " +
                                produtoEscolhido.getNome()
                );

            } else {

                System.out.println(
                        "\n  > Produto não encontrado!"
                );

                continue;
            }

            System.out.println("\n┌──────────────────────────────────┐");
            System.out.println("│  [1] Adicionar mais produtos     │");
            System.out.println("│  [2] Finalizar pedido            │");
            System.out.println("└──────────────────────────────────┘");

            System.out.print("  > ");

            int opcao =
                    Input.lerOpcao();

            if (opcao == 2) {

                adicionando = false;
            }
        }

        Loading.LimparTerminal(
                "Gerando resumo..."
        );

        System.out.println("┌──────────────────────────────────┐");
        System.out.println("│          RESUMO PEDIDO           │");
        System.out.println("├──────────────────────────────────┤");

        for (Produto produto : carrinho) {

            System.out.printf (
                    "│  %-30s│%n",
                    produto.getNome()
            );
        }

        System.out.println("├──────────────────────────────────┤");

        System.out.printf (
                "│  Subtotal: R$ %-16.2f│%n",
                total
        );

        System.out.printf (
                "│  Taxa Entrega: R$ %-12.2f│%n",
                5.0
        );

        System.out.printf (
                "│  TOTAL: R$ %-19.2f│%n",
                total + 5.0
        );

        System.out.println("├──────────────────────────────────┤");
        System.out.println("│  [1] Confirmar Pedido            │");
        System.out.println("│  [0] Cancelar                    │");
        System.out.println("└──────────────────────────────────┘");

        System.out.print("  > ");

        int confirma =
                Input.lerOpcao();

        if (confirma == 1) {

            Pedido pedido =
                    new Pedido();

            pedido.setStatus(
                    "Aguardando"
            );

            pedido.setIdCliente(
                    clienteLogado.getId()
            );

            pedido.setIdRestaurante(
                    idRestaurante
            );

            int idPedido =
                    pedidoService
                            .salvarPedido(
                                    pedido
                            );

            for (Produto produto : carrinho) {

                ItemPedido item =
                        new ItemPedido();

                item.setQuantidade(1);

                item.setPrecoUnit(
                        produto.getPreco()
                );

                item.setIdPedido(
                        idPedido
                );

                item.setIdProduto(
                        produto.getId()
                );

                itemPedidoService
                        .salvarItem(item);
            }

            Loading.LimparTerminal(
                    "Finalizando pedido..."
            );

            System.out.println("┌──────────────────────────────────┐");
            System.out.println("│        PEDIDO REALIZADO!         │");

            System.out.printf (
                    "│  Pedido #%04d criado com sucesso │%n",
                    idPedido
            );

            System.out.println("│  Aguarde a confirmação do        │");
            System.out.println("│  restaurante.                    │");
            System.out.println("└──────────────────────────────────┘");

        } else {

            System.out.println("\n  > Pedido cancelado!");
        }

        System.out.println("\n  [ENTER] Continuar...");
        Input.scanner.nextLine();
    }

    public void meusPedidos() {

        Loading.LimparTerminal(
                "Buscando pedidos..."
        );

        List<Pedido> pedidos =
                pedidoService
                        .listarPorCliente(
                                clienteLogado.getId()
                        );

        System.out.println("┌──────────────────────────────────┐");
        System.out.println("│           MEUS PEDIDOS           │");
        System.out.println("├──────────────────────────────────┤");

        if (pedidos.isEmpty()) {

            System.out.println("│  Nenhum pedido encontrado.       │");
            System.out.println("└──────────────────────────────────┘");

            System.out.println("\n  [ENTER] Voltar...");
            Input.scanner.nextLine();

            return;
        }

        for (Pedido p : pedidos) {

            System.out.printf (
                    "│  Pedido: #%04d                  │%n",
                    p.getId()
            );

            System.out.printf (
                    "│  Status: %-24s│%n",
                    p.getStatus()
            );

            System.out.printf (
                    "│  Restaurante ID: %-16s│%n",
                    "#" + p.getIdRestaurante()
            );

            System.out.println("├──────────────────────────────────┤");
        }

        System.out.println("│                                  │");
        System.out.println("│  [1] Ver detalhes                │");
        System.out.println("│  [0] Voltar                      │");
        System.out.println("│                                  │");
        System.out.println("└──────────────────────────────────┘");

        System.out.print("  > ");

        int opcao =
                Input.lerOpcao();

        switch (opcao) {

            case 1:

                System.out.print(
                        "\n  > ID do pedido: "
                );

                int id =
                        Input.lerOpcao();

                detalhesPedido(id);

                break;

            case 0:

                return;

            default:

                System.out.println(
                        "\n  > Opção inválida!"
                );

                try {

                    Thread.sleep(1200);

                } catch (InterruptedException e) {

                    e.printStackTrace();
                }
        }
    }

    private void detalhesPedido(int id) {

        Loading.LimparTerminal(
                "Carregando detalhes..."
        );

        System.out.println("┌──────────────────────────────────┐");
        System.out.println("│         DETALHES PEDIDO          │");
        System.out.println("├──────────────────────────────────┤");

        System.out.printf (
                "│  Pedido: #%04d                  │%n",
                id
        );

        System.out.println("├──────────────────────────────────┤");
        System.out.println("│                                  │");
        System.out.println("│  Funcionalidade em               │");
        System.out.println("│  desenvolvimento.                │");
        System.out.println("│                                  │");
        System.out.println("└──────────────────────────────────┘");

        System.out.println("\n  [ENTER] Voltar...");
        Input.scanner.nextLine();
    }


}
