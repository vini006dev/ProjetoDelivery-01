package org.example.telas;

import org.example.models.Entregador;
import org.example.models.Pedido;
import org.example.services.ClienteService;
import org.example.services.EntregadorService;
import org.example.utils.Input;

import java.util.List;

import static org.example.utils.Loading.limparTela;

public class InterfaceEntregador {

    private ClienteService clienteService = new ClienteService();
    private EntregadorService entregadorService = new EntregadorService();
    private Entregador entregadorLogado;

    public void inicioEntregador() {
        boolean rodando = true;
        while (rodando) {

            limparTela();

            System.out.println("+---------------------------------+");
            System.out.println("|            DELIVERY             |");
            System.out.println("+---------------------------------+");
            System.out.println("| Entregador                      |");
            System.out.println("+---------------------------------+");
            System.out.println("|       1 - Entrar                |");
            System.out.println("|       2 - Criar Conta           |");
            System.out.println("|       3 - Sair                  |");
            System.out.println("+---------------------------------+");
            System.out.print("Escolha uma opção: ");

            int opcao = Input.scanner.nextInt();
            switch (opcao) {
                case 1:
                    exibirLogin();
                    break;
                case 2:
                    exibirCadastro();
                    break;
                case 3:
                    rodando = false;
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    public void exibirLogin() {

        limparTela();

        Input.scanner.nextLine();

        System.out.println("Email:");
        String email = Input.scanner.nextLine();

        System.out.println("Senha:");
        String senha = Input.scanner.nextLine();

        Entregador entregador = entregadorService.login(email, senha);

        if (entregador != null) {
            entregadorLogado = entregador;
            System.out.println("Bem vindo, " + entregador.getNome());
            exibirMenu();
        } else {
            System.out.println("Login inválido!");
        }
    }

    public void exibirCadastro() {

        limparTela();

        Input.scanner.nextLine();

        Entregador e = new Entregador();

        System.out.println("Nome:");
        e.setNome(Input.scanner.nextLine());

        System.out.println("CPF:");
        e.setCpf(Input.scanner.nextLine());

        System.out.println("Email:");
        e.setEmail(Input.scanner.nextLine());

        System.out.println("Senha:");
        e.setSenha(Input.scanner.nextLine());

        System.out.println("Telefone:");
        e.setTelefone(Input.scanner.nextLine());

        System.out.println("Veículo (ex: Moto, Bicicleta, Carro):");
        e.setVeiculo(Input.scanner.nextLine());

        entregadorService.cadastrar(e);

        System.out.println("Cadastro realizado!");
    }

    public void exibirMenu() {

        boolean rodando = true;

        while (rodando) {

            limparTela();

            System.out.println("+---------------------------------+");
            System.out.println("| MENU ENTREGADOR                 |");
            System.out.println("+---------------------------------+");
            System.out.println("| 1 - Pedidos disponíveis         |");
            System.out.println("| 2 - Meus pedidos                |");
            System.out.println("| 3 - Sair                        |");
            System.out.println("+---------------------------------+");
            System.out.print("Escolha: ");

            int opcao = Input.scanner.nextInt();

            switch (opcao) {
                case 1:
                    pedidosDisponiveis();
                    break;
                case 2:
                    meusPedidos();
                    break;
                case 3:
                    rodando = false;
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    public void pedidosDisponiveis() {

        limparTela();

        List<Pedido> pedidos = entregadorService.listarPedidosDisponiveis();

        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido disponível no momento.");
            System.out.println("Pressione ENTER para voltar.");
            Input.scanner.nextLine();
            Input.scanner.nextLine();
            return;
        }

        System.out.println("+------ PEDIDOS DISPONÍVEIS ------+");

        for (Pedido p : pedidos) {

            String enderecoRetirada = entregadorService.buscarEnderecoEntrega(p.getIdRestaurante());
            String enderecoEntrega = clienteService.buscarEnderecoPorCliente(p.getIdCliente());
            String nomeCliente = clienteService.buscarNomePorId(p.getIdCliente());

            System.out.println("+------ NOTA DE ENTREGA ------+");
            System.out.println("Pedido #" + p.getId());
            System.out.println("Cliente:     " + nomeCliente);
            System.out.println("RETIRAR EM:  " + enderecoRetirada);
            System.out.println("ENTREGAR EM: " + enderecoEntrega);
            System.out.println("+-----------------------------+");
        }

        System.out.println("1 - Aceitar um pedido");
        System.out.println("2 - Voltar");

        int opcao = Input.scanner.nextInt();

        if (opcao == 1) {
            System.out.println("Digite o ID do pedido:");
            int idPedido = Input.scanner.nextInt();
            entregadorService.aceitarPedido(idPedido, entregadorLogado.getId());
            System.out.println("Pedido aceito! Boa entrega!");
            Input.scanner.nextLine();
            Input.scanner.nextLine();
        }
    }

    public void meusPedidos() {

        limparTela();

        List<Pedido> pedidos = entregadorService.meusPedidos(entregadorLogado.getId());

        if (pedidos.isEmpty()) {
            System.out.println("Você não tem pedidos ativos.");
            System.out.println("Pressione ENTER para voltar.");
            Input.scanner.nextLine();
            Input.scanner.nextLine();
            return;
        }

        System.out.println("+------ MEUS PEDIDOS ------+");

        for (Pedido p : pedidos) {
            System.out.println("Pedido #" + p.getId());
            System.out.println("Status: " + p.getStatus());
            System.out.println("+--------------------------+");
        }

        System.out.println("1 - Finalizar entrega");
        System.out.println("2 - Voltar");

        int opcao = Input.scanner.nextInt();

        if (opcao == 1) {
            System.out.println("Digite o ID do pedido:");
            int idPedido = Input.scanner.nextInt();
            entregadorService.finalizarEntrega(idPedido);
            System.out.println("Entrega finalizada!");
            Input.scanner.nextLine();
            Input.scanner.nextLine();
        }
    }
}