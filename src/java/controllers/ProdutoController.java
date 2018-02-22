/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.ProdutoDao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Produto;

/**
 *
 * @author Gabriel
 */
public class ProdutoController extends HttpServlet {

    private static String INSERT_OR_EDIT_OR_LIST = "/produto.jsp";
    private ProdutoDao dao;

    public ProdutoController() {
        super();
        dao = new ProdutoDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "";
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")) {
            int produtoId = Integer.parseInt(request.getParameter("id"));
            if (dao.verificaVinculo(produtoId)) {
                request.setAttribute("produtos", dao.getAllProdutos());
                request.setAttribute("mensagem", "Produto nao pode ser apagado pois ele ja possui um pedido!");
                RequestDispatcher dis = request.getRequestDispatcher(INSERT_OR_EDIT_OR_LIST);
                dis.forward(request, response);
            } else {
                dao.deleteProduto(produtoId);
                request.setAttribute("mensagem", "Produto apagado com sucesso!");
                forward = INSERT_OR_EDIT_OR_LIST;
                request.setAttribute("produtos", dao.getAllProdutos());
            }
        } else if (action.equalsIgnoreCase("edit")) {
            forward = INSERT_OR_EDIT_OR_LIST;
            int produtoId = Integer.parseInt(request.getParameter("id"));
            Produto produto = dao.getProdutoById(produtoId);
            request.setAttribute("produto", produto);
            request.setAttribute("produtos", dao.getAllProdutos());
        } else if (action.equalsIgnoreCase("listProdutos")) {
            forward = INSERT_OR_EDIT_OR_LIST;
            request.setAttribute("produtos", dao.getAllProdutos());
        } else {
            forward = INSERT_OR_EDIT_OR_LIST;
        }

        RequestDispatcher view;
        view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("voltar") != null) {
            RequestDispatcher view = request.getRequestDispatcher("index.jsp");
            view.forward(request, response);
        } else {
            Produto produto = new Produto();
            produto.setDescricao(request.getParameter("descricao"));
            String produtoId = request.getParameter("id");
            if (produtoId == null || produtoId.isEmpty()) {
                dao.addProduto(produto);
                request.setAttribute("mensagem", "Produto salvo com sucesso!");
            } else {
                produto.setId(Integer.parseInt(produtoId));
                dao.updateProduto(produto);
                request.setAttribute("mensagem", "Produto alterado com sucesso!");
            }
            RequestDispatcher view = request.getRequestDispatcher(INSERT_OR_EDIT_OR_LIST);
            request.setAttribute("produtos", dao.getAllProdutos());
            view.forward(request, response);
        }
    }
}
