package org.example.telas;

import org.example.models.Entregador;
import org.example.models.Pedido;
import org.example.services.ClienteService;
import org.example.services.EntregadorService;
import org.example.utils.Input;
import org.example.utils.Loading;

import java.util.List;

import static org.example.utils.Loading.limparTela;

public class InterfaceEntregador {

    private ClienteService clienteService = new ClienteService();
    private EntregadorService entregadorService = new EntregadorService();
    private Entregador entregadorLogado;

    public void inicioEntregador() {

        boolean rodando = true;

        while (rodando) {

            Loading.LimparTerminal(
                    "Carregando..."
            );

            System.out.println("┌──────────────────────────────────┐");
            System.out.println("│         COMIDINHA                │");
            System.out.println("│            > Entregador          │");
            System.out.println("├──────────────────────────────────┤");
            System.out.println("│                                  │");
            System.out.println("│  [1] Entrar                      │");
            System.out.println("│  [2] Criar Conta                 │");
            System.out.println("│                                  │");
            System.out.println("│  [0] Voltar                      │");
            System.out.println("│                                  │");
            System.out.println("└──────────────────────────────────┘");

            System.out.print("  > ");

            int opcao =
                    Input.lerOpcao();

            switch (opcao) {

                case 1:

                    exibirLogin();
                    break;

                case 2:

                    exibirCadastro();
                    break;

                case 0:

                    Loading.LimparTerminal(
                            "Voltando..."
                    );

                    rodando = false;
                    break;

                default:

                    System.out.println(
                            "\n  > Opção inválida!"
                    );

                    Loading.esperar(1200);
            }
        }
    }

    public void exibirLogin() {

        Loading.LimparTerminal(
                "Carregando login..."
        );

        System.out.println("┌──────────────────────────────────┐");
        System.out.println("│        LOGIN ENTREGADOR          │");
        System.out.println("├──────────────────────────────────┤");
        System.out.println("│  Informe suas credenciais        │");
        System.out.println("└──────────────────────────────────┘");

        System.out.print("  > Email: ");

        String email =
                Input.lerTexto("Email");

        System.out.print("  > Senha: ");

        String senha =
                Input.lerTexto("Senha");

        Loading.LimparTerminal(
                "Autenticando..."
        );

        Entregador entregador =
                entregadorService.login(
                        email,
                        senha
                );

        if (entregador != null) {

            entregadorLogado =
                    entregador;

            System.out.println("┌──────────────────────────────────┐");

            System.out.printf (
                    "│  Bem-vindo, %-21s│%n",
                    entregador.getNome() + "!"
            );

            System.out.println("└──────────────────────────────────┘");

            System.out.println(
                    "\n  [ENTER] Continuar..."
            );

            Input.scanner.nextLine();

            exibirMenu();

        } else {

            System.out.println("┌──────────────────────────────────┐");
            System.out.println("│  Login inválido!                 │");
            System.out.println("│  Verifique email e senha.        │");
            System.out.println("└──────────────────────────────────┘");

            System.out.println(
                    "\n  [ENTER] Voltar..."
            );

            Input.scanner.nextLine();
        }
    }

    public void exibirCadastro() {

        Loading.LimparTerminal(
                "Carregando cadastro..."
        );

        System.out.println("┌──────────────────────────────────┐");
        System.out.println("│       CADASTRO ENTREGADOR        │");
        System.out.println("├──────────────────────────────────┤");
        System.out.println("│  Preencha os dados abaixo        │");
        System.out.println("└──────────────────────────────────┘");

        Entregador e = new Entregador();

        System.out.print("  > Nome: ");

        e.setNome(
                Input.lerTexto("Nome")
        );

        System.out.print("  > CPF: ");

        e.setCpf(
                Input.lerTexto("CPF")
        );

        System.out.print("  > Email: ");

        e.setEmail(
                Input.lerTexto("Email")
        );

        System.out.print("  > Senha: ");

        e.setSenha(
                Input.lerTexto("Senha")
        );

        System.out.print("  > Telefone: ");

        e.setTelefone(
                Input.lerTexto("Telefone")
        );

        System.out.print(
                "  > Veículo (Moto/Bicicleta/Carro): "
        );

        e.setVeiculo(
                Input.lerTexto("Veículo")
        );

        Loading.LimparTerminal(
                "Salvando dados..."
        );

        entregadorService.cadastrar(e);

        System.out.println("┌──────────────────────────────────┐");
        System.out.println("│      CADASTRO REALIZADO!         │");
        System.out.println("├──────────────────────────────────┤");

        System.out.printf (
                "│  Nome: %-26s│%n",
                e.getNome()
        );

        System.out.printf (
                "│  Email: %-25s│%n",
                e.getEmail()
        );

        System.out.printf (
                "│  Veículo: %-22s│%n",
                e.getVeiculo()
        );

        System.out.println("└──────────────────────────────────┘");

        System.out.println(
                "\n  [ENTER] Continuar..."
        );

        Input.scanner.nextLine();
    }

    public void exibirMenu() {

        boolean rodando = true;

        while (rodando) {

            Loading.LimparTerminal(
                    "Carregando menu..."
            );

            System.out.println("┌──────────────────────────────────┐");
            System.out.println("│         MENU ENTREGADOR          │");

            System.out.printf (
                    "│  Olá, %-26s│%n",
                    entregadorLogado.getNome() + "!"
            );

            System.out.println("├──────────────────────────────────┤");
            System.out.println("│                                  │");
            System.out.println("│  [1] Pedidos Disponíveis         │");
            System.out.println("│  [2] Meus Pedidos                │");
            System.out.println("│                                  │");
            System.out.println("│  [0] Sair                        │");
            System.out.println("│                                  │");
            System.out.println("└──────────────────────────────────┘");

            System.out.print("  > ");

            int opcao =
                    Input.lerOpcao();

            switch (opcao) {

                case 1:

                    pedidosDisponiveis();
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
                            "\n  > Opção inválida!"
                    );

                    Loading.esperar(1200);
            }
        }
    }

    public void pedidosDisponiveis() {

        Loading.LimparTerminal(
                "Buscando pedidos..."
        );

        List<Pedido> pedidos =
                entregadorService
                        .listarPedidosDisponiveis();

        System.out.println("┌──────────────────────────────────┐");
        System.out.println("│       PEDIDOS DISPONÍVEIS        │");
        System.out.println("├──────────────────────────────────┤");

        if (pedidos.isEmpty()) {

            System.out.println(
                    "│  Nenhum pedido disponível.       │"
            );

            System.out.println(
                    "└──────────────────────────────────┘"
            );

            System.out.println(
                    "\n  [ENTER] Voltar..."
            );

            Input.scanner.nextLine();

            return;
        }

        for (Pedido p : pedidos) {

            String enderecoRetirada =
                    entregadorService
                            .buscarEnderecoEntrega(
                                    p.getIdRestaurante()
                            );

            String enderecoEntrega =
                    clienteService
                            .buscarEnderecoPorCliente(
                                    p.getIdCliente()
                            );

            String nomeCliente =
                    clienteService
                            .buscarNomePorId(
                                    p.getIdCliente()
                            );

            System.out.printf (
                    "│  Pedido: #%04d                  │%n",
                    p.getId()
            );

            System.out.printf (
                    "│  Cliente: %-23s│%n",
                    nomeCliente
            );

            System.out.println(
                    "├──────────────────────────────────┤"
            );

            System.out.printf (
                    "│  Retirar: %-23s│%n",
                    enderecoRetirada
            );

            System.out.printf (
                    "│  Entregar: %-22s│%n",
                    enderecoEntrega
            );

            System.out.println(
                    "├──────────────────────────────────┤"
            );
        }

        System.out.println("│                                  │");
        System.out.println("│  [1] Aceitar Pedido              │");
        System.out.println("│  [0] Voltar                      │");
        System.out.println("│                                  │");
        System.out.println("└──────────────────────────────────┘");

        System.out.print("  > ");

        int opcao =
                Input.lerOpcao();

        if (opcao == 1) {

            System.out.print(
                    "\n  > ID do pedido: "
            );

            int idPedido =
                    Input.lerOpcao();

            entregadorService.aceitarPedido(
                    idPedido,
                    entregadorLogado.getId()
            );

            Loading.LimparTerminal(
                    "Aceitando pedido..."
            );

            System.out.println("┌──────────────────────────────────┐");
            System.out.println("│        PEDIDO ACEITO!            │");

            System.out.printf (
                    "│  Pedido #%04d vinculado.         │%n",
                    idPedido
            );

            System.out.println("│  Boa entrega!                    │");
            System.out.println("└──────────────────────────────────┘");

            System.out.println(
                    "\n  [ENTER] Continuar..."
            );

            Input.scanner.nextLine();
        }
    }

    public void meusPedidos() {

        Loading.LimparTerminal(
                "Buscando pedidos..."
        );

        List<Pedido> pedidos =
                entregadorService.meusPedidos(
                        entregadorLogado.getId()
                );

        System.out.println("┌──────────────────────────────────┐");
        System.out.println("│          MEUS PEDIDOS            │");
        System.out.println("├──────────────────────────────────┤");

        if (pedidos.isEmpty()) {

            System.out.println(
                    "│  Você não possui entregas.       │"
            );

            System.out.println(
                    "└──────────────────────────────────┘"
            );

            System.out.println(
                    "\n  [ENTER] Voltar..."
            );

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

            System.out.println(
                    "├──────────────────────────────────┤"
            );
        }

        System.out.println("│                                  │");
        System.out.println("│  [1] Finalizar Entrega           │");
        System.out.println("│  [0] Voltar                      │");
        System.out.println("│                                  │");
        System.out.println("└──────────────────────────────────┘");

        System.out.print("  > ");

        int opcao =
                Input.lerOpcao();

        if (opcao == 1) {

            System.out.print(
                    "\n  > ID do pedido: "
            );

            int idPedido =
                    Input.lerOpcao();

            entregadorService.finalizarEntrega(
                    idPedido
            );

            Loading.LimparTerminal(
                    "Finalizando entrega..."
            );

            System.out.println("┌──────────────────────────────────┐");
            System.out.println("│       ENTREGA FINALIZADA!        │");

            System.out.printf (
                    "│  Pedido #%04d entregue.          │%n",
                    idPedido
            );

            System.out.println("│  Obrigado pelo trabalho!         │");
            System.out.println("└──────────────────────────────────┘");

            System.out.println(
                    "\n  [ENTER] Continuar..."
            );

            Input.scanner.nextLine();
        }
    }
}