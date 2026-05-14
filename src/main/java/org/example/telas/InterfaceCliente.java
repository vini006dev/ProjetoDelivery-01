package org.example.telas;

import org.example.models.*;
import org.example.services.*;
import org.example.utils.Input;
import org.example.utils.Loading;

import java.util.ArrayList;
import java.util.List;

import static org.example.utils.Loading.limparTela;


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


    public void inicioCliente(){
        boolean rodando = true;
        while (rodando) {

            limparTela();



            System.out.println("+---------------------------------+");
            System.out.println("|            DELIVERY             |");
            System.out.println("+---------------------------------+");
            System.out.println("| Cliente                         |");
            System.out.println("+---------------------------------+");
            System.out.println("|       1 - Entrar                |");
            System.out.println("|       2 - Criar Conta           |");
            System.out.println("|       3 - Sair                  |");
            System.out.println("+---------------------------------+");
            System.out.print("Escolha uma opção: ");

            int opcaoInicialCliente = Input.scanner.nextInt();
            switch (opcaoInicialCliente) {
                case 1:
                    System.out.println("1 Entrar");
                    exibirLogin();
                    break;
                case 2:
                    System.out.println("2 Criar Conta");
                    exibirCadastro();
                    break;
                case 3:
                    System.out.println("3 Sair");
                    rodando = false;
                    break;
                default:
                    System.out.println("Opção invalida");

            }
        }
    }

    public void exibirLogin(){

        limparTela();

        Loading.logoInfDados();

        Input.scanner.nextLine();

        System.out.println("|Email:                           |");
        String email = Input.scanner.nextLine();

        System.out.println("|Senha:                           |");
        String senha = Input.scanner.nextLine();

        Cliente cliente =
                clienteService.login(email, senha);

        if(cliente != null){

            clienteLogado = cliente;

            System.out.println(
                    "Bem vindo, "
                            + cliente.getNome()
            );

            exibirMenu();

        } else {

            System.out.println(
                    "Usuario Não Encontrado !!!"
            );

        }
    }

    public void exibirCadastro(){

        limparTela();

        Input.scanner.nextLine();

        Cliente cliente = new Cliente();

        System.out.println("Nome:");
        cliente.setNome(Input.scanner.nextLine());

        System.out.println("CPF:");
        cliente.setCpf(Input.scanner.nextLine());

        System.out.println("Email:");
        cliente.setEmail(Input.scanner.nextLine());

        System.out.println("Senha:");
        cliente.setSenha(Input.scanner.nextLine());

        System.out.println("Telefone:");
        cliente.setTelefone(Input.scanner.nextLine());

        System.out.println("Rua:");
        cliente.setRua(Input.scanner.nextLine());

        System.out.println("Número:");
        cliente.setNumero(Input.scanner.nextLine());

        System.out.println("Bairro:");
        cliente.setBairro(Input.scanner.nextLine());

        System.out.println("Cidade:");
        cliente.setCidade(Input.scanner.nextLine());

        clienteService.cadastrar(cliente);

        System.out.println("Cadastro realizado!");
    }

    public void exibirMenu(){
        boolean rodando = true;
        while (rodando){

            limparTela();

            System.out.println("+---------------------------------+");
            System.out.println("|            DELIVERY             |");
            System.out.println("+---------------------------------+");
            System.out.println("| Cliente                         |");
            System.out.println("+---------------------------------+");
            System.out.println("|       1 - Restaurantes          |");
            System.out.println("|       2 - Meus pedidos          |");
            System.out.println("|       3 - Sair                  |");
            System.out.println("+---------------------------------+");
            System.out.print("Escolha uma opção: ");

            int opcaoInicial = Input.scanner.nextInt();
            switch (opcaoInicial){
                case 1:
                    listarRestaurantes();
                    break;
                case 2:
                    meusPedidos();
                    break;
                case 3:
                    rodando = false;
                    break;
                default:
                    System.out.println("Opção Inválida");
            }
        }
    }

    public void listarRestaurantes(){

        limparTela();

        List<Restaurante> restaurantes =
                restauranteService.listar();

        System.out.println(
                "+----------------------+"
        );

        for(Restaurante r : restaurantes){

            System.out.println(
                    "ID: " + r.getId()
            );

            System.out.println(
                    "Nome: " + r.getNome()
            );

            System.out.println(
                    "Endereco: "
                            + r.getEndereco()
            );

            System.out.println(
                    "+----------------------+"
            );
        }

        System.out.println("1 - Escolher");
        System.out.println("2 - Voltar");

        int opcao =
                Input.scanner.nextInt();

        switch (opcao){

            case 1:

                System.out.println(
                        "Digite o ID do restaurante:"
                );

                int idRestaurante =
                        Input.scanner.nextInt();

                fazerPedido(
                        idRestaurante
                );

                break;

            case 2:

                return;

            default:

                System.out.println(
                        "Opcao invalida"
                );
        }
    }


    public void fazerPedido(int idRestaurante){

        limparTela();

        List<Produto> produtos =
                produtoService
                        .listarPorRestaurante(
                                idRestaurante
                        );

        boolean adicionando = true;

        List<Produto> carrinho =
                new ArrayList<>();

        double total = 0;

        Loading.LimparTerminal(
                "Carregando Cardapio..."
        );

        System.out.println(
                "+------ CARDAPIO ------+"
        );

        for(Produto p : produtos){

            System.out.println(
                    "ID: " + p.getId()
            );

            System.out.println(
                    "Produto: " + p.getNome()
            );

            System.out.println(
                    "Preco: R$ "
                            + p.getPreco()
            );

            System.out.println(
                    "+----------------------+"
            );
        }

        while (adicionando){

            System.out.println(
                    "Digite o ID do produto:"
            );

            int idProduto =
                    Input.scanner.nextInt();

            Produto produtoEscolhido = null;

            for(Produto p : produtos){

                if(p.getId() == idProduto){

                    produtoEscolhido = p;

                    break;
                }
            }

            if(produtoEscolhido != null){

                carrinho.add(produtoEscolhido);

                System.out.println(
                        produtoEscolhido.getNome()
                                + " adicionado!"
                );

                total +=
                        produtoEscolhido.getPreco();

            } else {

                System.out.println(
                        "Produto nao encontrado!"
                );
            }

            System.out.println(
                    "1 - Adicionar mais"
            );

            System.out.println(
                    "2 - Ver carrinho"
            );

            int opcao =
                    Input.scanner.nextInt();

            if(opcao == 2){

                adicionando = false;
            }
        }

        System.out.println(
                "+------ RESUMO ------+"
        );

        System.out.printf(
                "Subtotal: R$ %.2f%n",
                total
        );

        System.out.printf(
                "Taxa entrega: R$ %.2f%n",
                5.0
        );

        System.out.printf(
                "TOTAL: R$ %.2f%n",
                total + 5.0
        );

        System.out.println(
                "1 - Confirmar"
        );

        System.out.println(
                "2 - Cancelar"
        );

        int confirma =
                Input.scanner.nextInt();

        if(confirma == 1){

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

            for(Produto produto : carrinho){

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

            System.out.println(
                    "Pedido realizado!"
            );

        } else {

            System.out.println(
                    "Pedido cancelado!"
            );
        }
    }

    public void meusPedidos(){

        limparTela();

        List<Pedido> pedidos =
                pedidoService
                        .listarPorCliente(
                                clienteLogado.getId()
                        );

        if(pedidos.isEmpty()){

            System.out.println(
                    "Nenhum pedido encontrado!"
            );

            return;
        }

        System.out.println(
                "+------ MEUS PEDIDOS ------+"
        );

        for(Pedido p : pedidos){

            System.out.println(
                    "Pedido #" + p.getId()
            );

            System.out.println(
                    "Status: "
                            + p.getStatus()
            );

            System.out.println(
                    "Restaurante ID: "
                            + p.getIdRestaurante()
            );

            System.out.println(
                    "+--------------------------+"
            );
        }

        System.out.println(
                "1 - Ver detalhes"
        );

        System.out.println(
                "2 - Voltar"
        );

        int opcao =
                Input.scanner.nextInt();

        switch (opcao){

            case 1:

                System.out.println(
                        "Digite o ID do pedido:"
                );

                int id =
                        Input.scanner.nextInt();

                detalhesPedido(id);

                break;

            case 2:

                return;

            default:

                System.out.println(
                        "Opcao invalida"
                );
        }
    }

    private void detalhesPedido(int id){

        limparTela();

        System.out.println(
                "+------ DETALHES ------+"
        );

        System.out.println(
                "Pedido #" + id
        );

        System.out.println(
                "Funcionalidade em desenvolvimento"
        );

        System.out.println(
                "Pressione ENTER"
        );

        Input.scanner.nextLine();
        Input.scanner.nextLine();
    }


}
