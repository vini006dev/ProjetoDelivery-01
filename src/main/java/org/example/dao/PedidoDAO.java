package org.example.dao;

import org.example.connection.Conexao;
import org.example.models.Pedido;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {

    public List<Pedido> listarPorCliente(int idCliente){

        List<Pedido> pedidos = new ArrayList<>();

        String sql = "SELECT * FROM Pedido WHERE id_cliente = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCliente);

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){

                Pedido pedido = new Pedido();

                pedido.setId(rs.getInt("id"));
                pedido.setStatus(rs.getString("status"));
                pedido.setIdCliente(rs.getInt("id_cliente"));
                pedido.setIdRestaurante(rs.getInt("id_restaurante"));
                pedido.setIdEntregador(rs.getInt("id_entregador"));

                pedidos.add(pedido);
            }

        } catch (Exception e){
            e.printStackTrace();
        }

        return pedidos;
    }

    public int salvarPedido(Pedido pedido){

        String sql =
                "INSERT INTO Pedido (status, id_cliente, id_restaurante, id_entregador) " +
                        "VALUES (?, ?, ?, ?) RETURNING id";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, pedido.getStatus());
            stmt.setInt(2, pedido.getIdCliente());
            stmt.setInt(3, pedido.getIdRestaurante());

            if (pedido.getIdEntregador() == 0) {
                stmt.setNull(4, Types.INTEGER);
            } else {
                stmt.setInt(4, pedido.getIdEntregador());
            }

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e){
            e.printStackTrace();
        }

        return -1;
    }

    public List<Pedido> listarPorRestaurante(int idRestaurante){

        List<Pedido> lista = new ArrayList<>();

        String sql = "SELECT * FROM Pedido WHERE id_restaurante = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idRestaurante);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Pedido pedido = new Pedido();

                pedido.setId(rs.getInt("id"));
                pedido.setStatus(rs.getString("status"));
                pedido.setIdCliente(rs.getInt("id_cliente"));
                pedido.setIdRestaurante(rs.getInt("id_restaurante"));
                pedido.setIdEntregador(rs.getInt("id_entregador"));

                lista.add(pedido);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public void atualizarStatus(int id, String status) {

        String sql = "UPDATE Pedido SET status = ? WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, status);
            stmt.setInt(2, id);

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Pedido> listarDisponiveis() {

        List<Pedido> lista = new ArrayList<>();

        String sql = "SELECT * FROM Pedido WHERE status = 'PRONTO' AND id_entregador IS NULL";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setId(rs.getInt("id"));
                pedido.setStatus(rs.getString("status"));
                pedido.setIdCliente(rs.getInt("id_cliente"));
                pedido.setIdRestaurante(rs.getInt("id_restaurante"));
                lista.add(pedido);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public void vincularEntregador(int idPedido, int idEntregador) {

        String sql = "UPDATE Pedido SET id_entregador = ?, status = 'EM_ENTREGA' WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idEntregador);
            stmt.setInt(2, idPedido);

            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Pedido> listarPorEntregador(int idEntregador) {

        List<Pedido> lista = new ArrayList<>();

        String sql = "SELECT * FROM Pedido WHERE id_entregador = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idEntregador);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setId(rs.getInt("id"));
                pedido.setStatus(rs.getString("status"));
                pedido.setIdCliente(rs.getInt("id_cliente"));
                pedido.setIdRestaurante(rs.getInt("id_restaurante"));
                pedido.setIdEntregador(rs.getInt("id_entregador"));
                lista.add(pedido);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    public String buscarEnderecoRestaurante(int idRestaurante) {

        String sql = "SELECT endereco FROM Restaurante WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idRestaurante);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("endereco");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Endereço não encontrado";
    }
}