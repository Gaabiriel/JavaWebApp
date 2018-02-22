/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.ClienteDao;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Cliente;

/**
 *
 * @author Gabriel
 */
public class ClientesController extends HttpServlet {

    private static String INSERT_OR_EDIT_OR_LIST = "/cliente.jsp";

    private ClienteDao dao;

    public ClientesController() {
        super();
        dao = new ClienteDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "";
        String action = request.getParameter("action");
        String mensagem = "";
        RequestDispatcher view;

        if (action.equalsIgnoreCase("delete")) {
            int clienteId = Integer.parseInt(request.getParameter("id"));
            if (dao.verificaVinculo(clienteId)) {
                mensagem = "Cliente nao pode ser apagado pois ele ja possui um pedido!";
                request.setAttribute("mensagem", mensagem);
                request.setAttribute("clientes", dao.getAllClientes());

                forward = INSERT_OR_EDIT_OR_LIST;
                view = request.getRequestDispatcher(forward);
                view.forward(request, response);
            } else {
                dao.deleteCliente(clienteId);
                mensagem = "Cliente apagado com sucesso!";
                request.setAttribute("mensagem", mensagem);
                request.setAttribute("clientes", dao.getAllClientes());

                forward = INSERT_OR_EDIT_OR_LIST;
                view = request.getRequestDispatcher(forward);
                view.forward(request, response);
            }
        } else if (action.equalsIgnoreCase("edit")) {
            int clienteId = Integer.parseInt(request.getParameter("id"));
            Cliente cliente = dao.getClienteById(clienteId);
            request.setAttribute("cliente", cliente);
            request.setAttribute("clientes", dao.getAllClientes());

            forward = INSERT_OR_EDIT_OR_LIST;
            view = request.getRequestDispatcher(forward);
            view.forward(request, response);
        } else if (action.equalsIgnoreCase("listCliente")) {
            mensagem = "";
            request.setAttribute("mensagem", mensagem);
            request.setAttribute("clientes", dao.getAllClientes());

            forward = INSERT_OR_EDIT_OR_LIST;
            view = request.getRequestDispatcher(forward);
            view.forward(request, response);
        } else {
            forward = INSERT_OR_EDIT_OR_LIST;
            mensagem = "";
            request.setAttribute("mensagem", mensagem);
        }

        view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("voltar");

        if (action != null) {
            RequestDispatcher view = request.getRequestDispatcher("index.jsp");
            view.forward(request, response);
        } else {
            Cliente cliente = new Cliente();
            cliente.setNome(request.getParameter("nome"));
            cliente.setSobreNome(request.getParameter("sobreNome"));
            cliente.setCpf(request.getParameter("cpf"));
            String clienteId = request.getParameter("id");
            if (clienteId == null || clienteId.isEmpty()) {
                if (dao.verificaSeJaExisteCpf(cliente.getCpf())) {
                    request.setAttribute("mensagem", "Cpf já cadastrado para outro cliente, favor informar outro!");
                    request.setAttribute("cliente", cliente);
                    request.setAttribute("clientes", dao.getAllClientes());

                    RequestDispatcher view = request.getRequestDispatcher(INSERT_OR_EDIT_OR_LIST);
                    view.forward(request, response);
                } else {
                    dao.addCliente(cliente);
                    request.setAttribute("mensagem", "Cliente salvo com sucesso!");
                }

            } else {
                cliente.setId(Integer.parseInt(clienteId));
                dao.updateCliente(cliente);
                request.setAttribute("mensagem", "Cliente alterado com sucesso!");
            }
            RequestDispatcher view = request.getRequestDispatcher(INSERT_OR_EDIT_OR_LIST);
            request.setAttribute("clientes", dao.getAllClientes());
            view.forward(request, response);
        }

    }
}
