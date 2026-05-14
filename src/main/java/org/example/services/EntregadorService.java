package org.example.services;

import org.example.dao.EntregadorDAO;
import org.example.dao.PedidoDAO;
import org.example.models.Entregador;
import org.example.models.Pedido;

import java.util.List;

public class EntregadorService {

    public String buscarEnderecoEntrega(int idRestaurante) {
        return pedidoDAO.buscarEnderecoRestaurante(idRestaurante);
    }

    private EntregadorDAO entregadorDAO = new EntregadorDAO();
    private PedidoDAO pedidoDAO = new PedidoDAO();

    public void cadastrar(Entregador e) {
        entregadorDAO.cadastrar(e);
    }

    public Entregador login(String email, String senha) {
        return entregadorDAO.login(email, senha);
    }

    public List<Pedido> listarPedidosDisponiveis() {
        return pedidoDAO.listarDisponiveis();
    }

    public void aceitarPedido(int idPedido, int idEntregador) {
        pedidoDAO.vincularEntregador(idPedido, idEntregador);
    }

    public void finalizarEntrega(int idPedido) {
        pedidoDAO.atualizarStatus(idPedido, "ENTREGUE");
    }

    public List<Pedido> meusPedidos(int idEntregador) {
        return pedidoDAO.listarPorEntregador(idEntregador);
    }
}