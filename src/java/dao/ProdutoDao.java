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
import models.Cliente;
import models.Produto;
import utils.ConnectionFactory;

/**
 *
 * @author Gabriel
 */
public class ProdutoDao {

    private Connection connection;

    public ProdutoDao() {
        connection = ConnectionFactory.getConnection();
    }

    public void addProduto(Produto produto) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into produto(descricao) values (?)");
            // Parameters start with 1
            preparedStatement.setString(1, produto.getDescricao());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProduto(int produtoId) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from produto where id=?");
            // Parameters start with 1
            preparedStatement.setInt(1, produtoId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateProduto(Produto produto) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update produto set descricao=?"
                            + "where id=?");
            // Parameters start with 1
            preparedStatement.setString(1, produto.getDescricao());
            preparedStatement.setInt(2, produto.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Produto> getAllProdutos() {
        List<Produto> produtos = new ArrayList<Produto>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from produto");
            while (rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setDescricao(rs.getString("descricao"));
                produtos.add(produto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produtos;
    }

    public Produto getProdutoById(int produtoId) {
        Produto produto = new Produto();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from produto where id=?");
            preparedStatement.setInt(1, produtoId);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                produto.setId(rs.getInt("id"));
                produto.setDescricao(rs.getString("descricao"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return produto;
    }

    public boolean verificaVinculo(int produtoId) {
        boolean hasVinculo = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select count(*)  AS total from item_do_pedido where id_produto = ?");
            preparedStatement.setInt(1, produtoId);
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
}
