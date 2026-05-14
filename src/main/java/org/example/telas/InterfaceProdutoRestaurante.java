package org.example.telas;

import org.example.models.Produto;
import org.example.models.Restaurante;
import org.example.services.ProdutoService;
import org.example.utils.Input;

import java.util.List;

import static org.example.utils.Loading.limparTela;

public class InterfaceProdutoRestaurante {

    private ProdutoService produtoService = new ProdutoService();

    public void menu(Restaurante restauranteLogado) {

        limparTela();

        boolean rodando = true;

        while (rodando) {

            System.out.println("1 - Criar produto");
            System.out.println("2 - Listar produtos");
            System.out.println("3 - Deletar produto");
            System.out.println("4 - Voltar");

            int opcao = Input.scanner.nextInt();

            switch (opcao) {

                case 1:
                    criarProduto(restauranteLogado);
                    break;

                case 2:
                    listarProdutos(restauranteLogado);
                    break;

                case 3:
                    deletarProduto(restauranteLogado);
                    break;

                case 4:
                    rodando = false;
                    break;

                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    public void criarProduto(Restaurante restauranteLogado) {

        limparTela();

        Produto p = new Produto();

        Input.scanner.nextLine();

        System.out.println("Nome:");
        p.setNome(Input.scanner.nextLine());

        System.out.println("Preço:");
        p.setPreco(Input.scanner.nextDouble());

        Input.scanner.nextLine();

        System.out.println("Categoria:");
        p.setCategoria(Input.scanner.nextLine());

        p.setIdRestaurante(restauranteLogado.getId());

        produtoService.cadastrar(p);

        System.out.println("Produto criado!");

        limparTela();
    }

    public void listarProdutos(Restaurante restauranteLogado) {

        limparTela();

        List<Produto> produtos =
                produtoService.listarPorRestaurante(restauranteLogado.getId());

        System.out.println("+------ CARDÁPIO ------+");

        for (Produto p : produtos) {
            System.out.println("ID: " + p.getId());
            System.out.println("Nome: " + p.getNome());
            System.out.println("Categoria: " + p.getCategoria());
            System.out.println("Preço: R$ " + p.getPreco());
            System.out.println("+----------------------+");
        }
    }

    public void deletarProduto(Restaurante restauranteLogado) {

        limparTela();

        System.out.println("ID do produto para deletar:");
        int idProduto = Input.scanner.nextInt();

        produtoService.deletarProduto(idProduto, restauranteLogado.getId());

        System.out.println("Produto deletado com sucesso!");
    }
}