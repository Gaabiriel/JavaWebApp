/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.Pedido;
import models.Produto;
import utils.ConnectionFactory;
import viewModel.PedidoViewModel;

/**
 *
 * @author Gabriel
 */
public class PedidoDao {

    private Connection connection;

    public PedidoDao() {
        connection = ConnectionFactory.getConnection();
    }

    public void RealizarPedido(int clienteId, List<PedidoViewModel> listaPedidoViewModel) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into pedido(data,id_cliente ) values (NOW(),?)", Statement.RETURN_GENERATED_KEYS);
            // Parameters start with 1
            preparedStatement.setInt(1, clienteId);
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                int last_inserted_id = rs.getInt(1);
                for (PedidoViewModel pedidoViewModel : listaPedidoViewModel) {
                    preparedStatement = connection
                            .prepareStatement("insert into item_do_pedido(id_pedido, id_produto,qtdade) values (?,?,?)");

                    preparedStatement.setInt(1, last_inserted_id);
                    preparedStatement.setInt(2, pedidoViewModel.ProdutoId);
                    preparedStatement.setInt(3, pedidoViewModel.Quantidade);
                    preparedStatement.executeUpdate();
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Pedido> getAllPedidosByCpf(String cpf) {
        List<Pedido> pedidos = new ArrayList<Pedido>();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select p.id from pedido as p\n"
                            + "join cliente as c on c.id = p.id_cliente\n"
                            + "where c.cpf = ?");
            // Parameters start with 1
            preparedStatement.setString(1, cpf);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setId(rs.getInt("id"));
                pedidos.add(pedido);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pedidos == null ? null : pedidos;
    }

    public List<PedidoViewModel> getAllPedidosItemsById(int id) {
        List<PedidoViewModel> pedidos = new ArrayList<PedidoViewModel>();
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select pd.descricao, ip.qtdade from produto as pd\n"
                            + "join item_do_pedido as ip on ip.id_produto = pd.id\n"
                            + "where ip.id_pedido = ?");
            // Parameters start with 1
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                PedidoViewModel pedido = new PedidoViewModel();
                pedido.setDescricao(rs.getString("descricao"));
                pedido.setQuantidade(rs.getInt("qtdade"));
                pedidos.add(pedido);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pedidos == null ? null : pedidos;
    }
}
