package org.example.telas;

import org.example.models.Pedido;
import org.example.models.Restaurante;
import org.example.services.RestauranteService;
import org.example.utils.Input;
import java.util.List;
import org.example.services.PedidoService;
import org.example.utils.StatusPedido;

import static org.example.utils.Loading.limparTela;

public class InterfaceRestaurante {

    private PedidoService pedidoService = new PedidoService();

    private RestauranteService restauranteService =
            new RestauranteService();

    private Restaurante restauranteLogado;

    private InterfaceProdutoRestaurante produtoInterface =
            new InterfaceProdutoRestaurante();


    public void inicioRestaurante(){
        boolean rodando = true;
        while (rodando) {

            limparTela();

            System.out.println("+---------------------------------+");
            System.out.println("|            DELIVERY             |");
            System.out.println("+---------------------------------+");
            System.out.println("| Restaurante                     |");
            System.out.println("+---------------------------------+");
            System.out.println("|       1 - Entrar                |");
            System.out.println("|       2 - Criar Conta           |");
            System.out.println("|       3 - Sair                  |");
            System.out.println("+---------------------------------+");
            System.out.print("Escolha uma opção: ");


            int opcaoInicialRestaurante = Input.scanner.nextInt();
            switch (opcaoInicialRestaurante) {
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

        Input.scanner.nextLine();

        System.out.println("Email:");
        String email =
                Input.scanner.nextLine();

        System.out.println("Senha:");
        String senha =
                Input.scanner.nextLine();

        Restaurante restaurante =
                restauranteService.login(
                        email,
                        senha
                );

        if(restaurante != null){

            restauranteLogado = restaurante;

            System.out.println(
                    "Bem vindo "
                            + restaurante.getNome()
            );

            exibirMenu();

        } else {

            System.out.println(
                    "Login invalido!"
            );
        }
    }

    public void exibirCadastro(){

        limparTela();

        Input.scanner.nextLine();

        Restaurante restaurante =
                new Restaurante();

        System.out.println("Nome:");
        restaurante.setNome(
                Input.scanner.nextLine()
        );

        System.out.println("Endereco:");
        restaurante.setEndereco(
                Input.scanner.nextLine()
        );

        System.out.println("CNPJ:");
        restaurante.setCnpj(
                Input.scanner.nextLine()
        );

        System.out.println("Telefone:");
        restaurante.setTelefone(
                Input.scanner.nextLine()
        );

        System.out.println("Categoria:");
        restaurante.setCategoria(
                Input.scanner.nextLine()
        );

        System.out.println("Email:");
        restaurante.setEmail(
                Input.scanner.nextLine()
        );

        System.out.println("Senha:");
        restaurante.setSenha(
                Input.scanner.nextLine()
        );

        restauranteService.cadastrar(
                restaurante
        );

        System.out.println(
                "Cadastro realizado!"
        );
    }

    public void exibirMenu(){

        limparTela();

        if (restauranteLogado == null) {
            System.out.println("Faça login primeiro!");
            return;
        }

        boolean rodando = true;

        while (rodando){

            System.out.println("+------ MENU RESTAURANTE ------+");
            System.out.println("1 - Ver pedidos");
            System.out.println("2 - Gerenciar pedidos");
            System.out.println("3 - Gerenciar cardápio");
            System.out.println("4 - Sair");
            System.out.print("Escolha: ");

            int opcao = Input.scanner.nextInt();

            switch (opcao){

                case 1:
                    visualizarPedidos();
                    break;

                case 2:
                    menuPedidos();
                    break;

                case 3:
                    produtoInterface.menu(restauranteLogado);
                    break;

                case 4:
                    rodando = false;
                    break;

                default:
                    System.out.println("Opção inválida");
            }
        }
    }



    public void menuPedidos() {

        limparTela();

        if (restauranteLogado == null) {
            System.out.println("Faça login primeiro!");
            return;
        }

        boolean rodando = true;

        while (rodando) {

            System.out.println("+------ MENU PEDIDOS ------+");
            System.out.println("1 - Ver pedidos");
            System.out.println("2 - Aceitar pedido");
            System.out.println("3 - Finalizar pedido");
            System.out.println("4 - Cancelar pedido");
            System.out.println("5 - Voltar");
            System.out.print("Escolha: ");

            int opcao = Input.scanner.nextInt();

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

                case 5:
                    rodando = false;
                    break;

                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    public void visualizarPedidos() {

        limparTela();

        if (restauranteLogado == null) {
            System.out.println("Faça login primeiro!");
            return;
        }

        List<Pedido> pedidos =
                pedidoService.listarPorRestaurante(restauranteLogado.getId());

        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido encontrado.");
            return;
        }

        System.out.println("+------ PEDIDOS ------+");

        for (Pedido p : pedidos) {
            System.out.println("ID: " + p.getId());
            System.out.println("Cliente: " + p.getIdCliente());
            System.out.println("Status: " + p.getStatus());
            System.out.println("+---------------------+");
        }
    }

    public void aceitarPedido() {

        limparTela();

        System.out.println("ID do pedido:");

        int id = Input.scanner.nextInt();

        pedidoService.atualizarStatus(id, StatusPedido.PRONTO);

        System.out.println("Pedido em preparo!");
    }

    public void cancelarPedido() {

        limparTela();

        System.out.println("ID do pedido:");

        int id = Input.scanner.nextInt();

        pedidoService.atualizarStatus(id, StatusPedido.CANCELADO);

        System.out.println("Pedido cancelado com sucesso!");
    }

    public void finalizarPedido() {

        limparTela();

        System.out.println("ID do pedido:");

        int id = Input.scanner.nextInt();

        pedidoService.atualizarStatus(id, StatusPedido.PRONTO);

        System.out.println("Pedido finalizado e disponível para entrega!");
    }




}
