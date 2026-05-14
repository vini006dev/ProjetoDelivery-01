package org.example.dao;

import org.example.connection.Conexao;
import org.example.models.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    public List<Produto> listarPorRestaurante(
            int idRestaurante
    ){

        List<Produto> produtos =
                new ArrayList<>();

        String sql =
                "SELECT * FROM Produto " +
                        "WHERE id_restaurante = ?";

        try {

            Connection conn =
                    Conexao.conectar();

            PreparedStatement stmt =
                    conn.prepareStatement(sql);

            stmt.setInt(1, idRestaurante);

            ResultSet rs =
                    stmt.executeQuery();

            while(rs.next()){

                Produto p = new Produto();

                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("Nome"));
                p.setDescricao(
                        rs.getString("Descricao")
                );

                p.setPreco(
                        rs.getDouble("PRECO")
                );

                produtos.add(p);
            }

            conn.close();

        } catch (Exception e){

            e.printStackTrace();

        }

        return produtos;
    }

    public void cadastrar(Produto p) {

        String sql = "INSERT INTO Produto (nome, categoria, preco, id_restaurante) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getCategoria());
            stmt.setDouble(3, p.getPreco());
            stmt.setInt(4, p.getId_restaurante());

            stmt.executeUpdate();

        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Erro ao cadastrar produto");
        }
    }

    public void deletarPorId(int idProduto, int idRestaurante) {

        String sql = "DELETE FROM produto WHERE id = ? AND id_restaurante = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idProduto);
            stmt.setInt(2, idRestaurante);

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}