
<%-- 
    Document   : cliente
    Created on : 02/02/2018, 22:06:40
    Author     : Gabriel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Java Web</title>
        <!-- Compiled and minified CSS -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.0/css/materialize.min.css">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">  
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js" type="text/javascript"></script>

        <style>
            body {
                display: flex;
                min-height: 100vh;
                flex-direction: column;
            }

            main {
                flex: 1 0 auto;
            }

        </style>
        <script type="text/javascript">
            $(document).ready(function () {
                $('select').material_select();
                var msg = "${mensagem}";
                if (msg != "") {
                    Materialize.toast(msg, 4000);
                }
            });

            function updateTextInput(val) {
                document.getElementById('textInput').value = val;
            }
        </script>
    </head>
    <body>
        <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
        <!-- Compiled and minified JavaScript -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>

        <div class="container">
            <div class="row">

                <div class="col s12 m9 l10">

                    <div id="input" class="section scrollspy">
                        <h2 class="header">Listar Pedido</h2>
                        <br>
                        <div class="row">
                            <form class="col s12" method="POST" action='PedidoController' name="frmNovoPedido">
                                <div class="row">

                                    <div class="col s12">
                                        <div class="input-field col s3">
                                            <input type="text" required 
                                                   name="cpf" value="${cpf}" data-length="11" />
                                            <label for="icon_cpf">CPF</label>
                                        </div>
                                        <div class="input-field col s3">
                                            <button class="btn waves-effect waves-light" type="submit" name="buscarPedido">Buscar Pedidos
                                                <i class="material-icons right">search</i>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col s12">
                                        <div class="input-field col s5">
                                            <select name="ddlPedidos" onchange="this.form.submit()" >
                                                <option value="" disabled selected>Selecione...</option>
                                                <c:forEach items="${pedidos}" var="pedido">
                                                    <option value="${pedido.id}">Pedido ${pedido.id}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </div>




                                <div class="row">
                                    <div class="col s12">
                                        <h2 class="header">Produtos do pedido do cliente</h2>
                                        <table class="highlight">
                                            <thead>
                                                <tr>
                                                    <th>Produto</th>
                                                    <th>Quantidade</th>
                                                </tr>
                                            </thead>

                                            <tbody>
                                                <c:forEach items="${pedidosTable}" var="pedido">
                                                    <tr>
                                                        <td><c:out value="${pedido.descricao}" /></td>
                                                        <td><c:out value="${pedido.quantidade}" /></td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>

                                        </table>                                            
                                        <a  href="index.jsp" class="btn waves-effect waves-light" name="voltar">Voltar
                                            <i class="material-icons right">arrow_back</i>
                                        </a>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    <footer class="page-footer" style="background-color: #26a69a;">
        <div class="footer-copyright">
            <div class="container">
                © 2018 Copyright
                <strong>Gabriel Souza</strong>
            </div>
        </div>
    </footer>
</body>
</html>