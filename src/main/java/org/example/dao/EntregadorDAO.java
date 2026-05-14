package org.example.dao;

import org.example.connection.Conexao;
import org.example.models.Entregador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EntregadorDAO {

    public void cadastrar(Entregador e) {

        String sql =
                "INSERT INTO Entregador (nome, cpf, email, senha, telefone, veiculo) " +
                        "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, e.getNome());
            stmt.setString(2, e.getCpf());
            stmt.setString(3, e.getEmail());
            stmt.setString(4, e.getSenha());
            stmt.setString(5, e.getTelefone());
            stmt.setString(6, e.getVeiculo());

            stmt.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Entregador login(String email, String senha) {

        String sql =
                "SELECT * FROM Entregador WHERE email = ? AND senha = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Entregador e = new Entregador();
                e.setId(rs.getInt("id"));
                e.setNome(rs.getString("nome"));
                e.setCpf(rs.getString("cpf"));
                e.setEmail(rs.getString("email"));
                e.setSenha(rs.getString("senha"));
                e.setTelefone(rs.getString("telefone"));
                e.setVeiculo(rs.getString("veiculo"));
                return e;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
}