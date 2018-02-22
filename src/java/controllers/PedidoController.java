/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.ClienteDao;
import dao.PedidoDao;
import dao.ProdutoDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Cliente;
import models.Pedido;
import models.Produto;
import viewModel.PedidoViewModel;

/**
 *
 * @author Gabriel
 */
public class PedidoController extends HttpServlet {
    
    private static String INSERT = "/pedido.jsp";
    private static String LIST = "/listarPedido.jsp";
    private PedidoDao daoPedido;
    private ProdutoDao daoProduto;
    private ClienteDao daoCliente;
    List<PedidoViewModel> listaPedidoViewModel = new ArrayList<PedidoViewModel>();
    
    public PedidoController() {
        super();
        daoPedido = new PedidoDao();
        daoProduto = new ProdutoDao();
        daoCliente = new ClienteDao();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher view;
        String action = request.getParameter("action");
        if (action.equals("listarPedido")) {
            request.setAttribute("produtos", daoProduto.getAllProdutos());
            view = request.getRequestDispatcher(LIST);
            view.forward(request, response);
        } else if (action.equals("pedido")) {
            request.setAttribute("produtos", daoProduto.getAllProdutos());
            
            view = request.getRequestDispatcher(INSERT);
            view.forward(request, response);
        }
        
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String action = request.getParameter("addPedido");
        String buscarAction = request.getParameter("buscarPedido");
        String listarPedidosAction = request.getParameter("ddlPedidos");
        String cpf = "";
        List<Pedido> pedidos = new ArrayList<Pedido>();
        RequestDispatcher view;
        if (listarPedidosAction != null) {
            daoPedido.getAllPedidosItemsById(Integer.parseInt(listarPedidosAction));
            request.setAttribute("pedidosTable", daoPedido.getAllPedidosItemsById(Integer.parseInt(listarPedidosAction)));
            request.setAttribute("cpf", cpf);
            request.setAttribute("pedidos", pedidos);
            view = request.getRequestDispatcher(LIST);
            view.forward(request, response);
        } else if (buscarAction != null) {
            cpf = request.getParameter("cpf");
            pedidos = daoPedido.getAllPedidosByCpf(cpf);
            if (pedidos != null) {
                request.setAttribute("cpf", cpf);
                request.setAttribute("pedidos", pedidos);
                view = request.getRequestDispatcher(LIST);
                view.forward(request, response);
            } else {
                request.setAttribute("mensagem", "Não existe pedido cadastrado para esse cliente");
                view = request.getRequestDispatcher(LIST);
                view.forward(request, response);
            }
            
        } else if (action != null) {
            PedidoViewModel pedidoViewModel = new PedidoViewModel();
            cpf = request.getParameter("cpf");
            pedidoViewModel.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));
            pedidoViewModel.setProdutoId(Integer.parseInt(request.getParameter("item")));
            pedidoViewModel.setDescricao(daoProduto.getProdutoById(Integer.parseInt(request.getParameter("item"))).getDescricao());
            listaPedidoViewModel.add(pedidoViewModel);
            
            request.setAttribute("cpf", cpf);
            request.setAttribute("produtos", daoProduto.getAllProdutos());
            request.setAttribute("pedidos", listaPedidoViewModel);
            view = request.getRequestDispatcher(INSERT);
            view.forward(request, response);
        } else {
            int clienteId = daoCliente.getClienteIdByCpf(request.getParameter("cpf"));
            if (clienteId == 0) {
                request.setAttribute("mensagem", "Cliente não encontrado!");
                view = request.getRequestDispatcher(INSERT);
                view.forward(request, response);
            }
            daoPedido.RealizarPedido(clienteId, listaPedidoViewModel);
            request.setAttribute("mensagem", "Pedido salvo com sucesso!");
            request.setAttribute("produtos", daoProduto.getAllProdutos());
            
            listaPedidoViewModel = new ArrayList<PedidoViewModel>();
            view = request.getRequestDispatcher(INSERT);
            view.forward(request, response);
        }
    }
    
}
