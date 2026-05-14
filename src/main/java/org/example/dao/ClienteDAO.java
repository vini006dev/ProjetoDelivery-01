package org.example.dao;

import org.example.connection.Conexao;
import org.example.models.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ClienteDAO {

    public void salvar(Cliente c) {

        String sql =
                "INSERT INTO Cliente (nome, cpf, email, senha, telefone, rua, numero, bairro, cidade) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getCpf());
            stmt.setString(3, c.getEmail());
            stmt.setString(4, c.getSenha());
            stmt.setString(5, c.getTelefone());
            stmt.setString(6, c.getRua());
            stmt.setString(7, c.getNumero());
            stmt.setString(8, c.getBairro());
            stmt.setString(9, c.getCidade());

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Cliente login(String email, String senha) {

        String sql = "SELECT * FROM Cliente WHERE email = ? AND senha = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Cliente c = new Cliente();
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setCpf(rs.getString("cpf"));
                c.setEmail(rs.getString("email"));
                c.setSenha(rs.getString("senha"));
                c.setTelefone(rs.getString("telefone"));
                c.setRua(rs.getString("rua"));
                c.setNumero(rs.getString("numero"));
                c.setBairro(rs.getString("bairro"));
                c.setCidade(rs.getString("cidade"));
                return c;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public String buscarEnderecoPorCliente(int idCliente) {

        String sql = "SELECT rua, numero, bairro, cidade FROM Cliente WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCliente);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("rua") + ", " +
                        rs.getString("numero") + " - " +
                        rs.getString("bairro") + ", " +
                        rs.getString("cidade");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Endereço não encontrado";
    }

    public String buscarNomePorId(int idCliente) {

        String sql = "SELECT nome FROM Cliente WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCliente);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("nome");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Cliente não encontrado";
    }

}