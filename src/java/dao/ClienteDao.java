package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.Cliente;
import utils.ConnectionFactory;

public class ClienteDao {

    private Connection connection;

    public ClienteDao() {
        connection = ConnectionFactory.getConnection();
    }

    public void addCliente(Cliente cliente) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into cliente(nome,sobrenome,cpf) values (?, ?, ? )");
            // Parameters start with 1
            preparedStatement.setString(1, cliente.getNome());
            preparedStatement.setString(2, cliente.getSobreNome());
            preparedStatement.setString(3, cliente.getCpf());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCliente(int clienteId) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from cliente where id=?");
            // Parameters start with 1
            preparedStatement.setInt(1, clienteId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCliente(Cliente cliente) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update cliente set nome=?, sobrenome=?, cpf=?"
                            + "where id=?");
            // Parameters start with 1
            preparedStatement.setString(1, cliente.getNome());
            preparedStatement.setString(2, cliente.getSobreNome());
            preparedStatement.setString(3, cliente.getCpf());
            preparedStatement.setInt(4, cliente.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Cliente> getAllClientes() {
        List<Cliente> clientes = new ArrayList<Cliente>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from cliente");
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setSobreNome(rs.getString("sobrenome"));
                cliente.setCpf(rs.getString("cpf"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clientes;
    }

    public Cliente getClienteById(int clienteId) {
        Cliente cliente = new Cliente();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from cliente where id=?");
            preparedStatement.setInt(1, clienteId);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                cliente.setId(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setSobreNome(rs.getString("sobreNome"));
                cliente.setCpf(rs.getString("cpf"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cliente;
    }

    public Integer getClienteIdByCpf(String cpf) {
        Cliente cliente = new Cliente();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select id from cliente where cpf=?");
            preparedStatement.setString(1, cpf);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                cliente.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cliente.getId() == null ? 0 : cliente.getId();
    }

    public boolean verificaVinculo(int clienteId) {
        boolean hasVinculo = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select count(*)  AS total from pedido where pedido.id_cliente = ?");
            preparedStatement.setInt(1, clienteId);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                int result = rs.getInt("total");
                if (result > 0) {
                    hasVinculo = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hasVinculo;
    }
    
     public boolean verificaSeJaExisteCpf(String cpf) {
        boolean existe = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select count(*)  AS existe from cliente where cpf = ?");
            preparedStatement.setString(1, cpf);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                int result = rs.getInt("existe");
                if (result > 0) {
                    existe = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return existe;
    }
}
