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

        <title>Novo Cliente</title>

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
                var msg = "${mensagem}";
                if (msg != "") {
                    Materialize.toast(msg, 4000);
                }
            });
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
                        <h2 class="header">Cadastro de Cliente</h2>
                        <br>
                        <div class="row">
                            <form class="col s12" method="POST" action='ClientesController' name="frmNovoCliente">
                                <div class="row">
                                    <div class="input-field col s1">
                                        <input type="text" readonly="readonly" name="id"
                                               value="${cliente.id}" />
                                        <label for="icon_nome">Código</label>
                                    </div>

                                    <div class="input-field col s3">
                                        <i class="material-icons prefix">face</i>
                                        <input required id="icon_nome" type="text" class="validate" name="nome"
                                               value="${cliente.nome}" data-length="30">
                                        <label for="icon_nome">Nome</label>
                                    </div>
                                    <div   class="input-field col s5">
                                        <i class="material-icons prefix">account_circle</i>
                                        <input required id="icon_sobrenome" type="text" class="validate" name="sobreNome"
                                               value="${cliente.sobreNome}" data-length="50">
                                        <label for="icon_sobrenome">Sobrenome</label>
                                    </div>

                                    <div  class="input-field col s3">
                                        <i class="material-icons prefix">perm_identity</i>
                                        <input required id="icon_cpf" type="text" class="validate" name="cpf"
                                               value="${cliente.cpf}" data-length="11">
                                        <label for="icon_cpf">CPF</label>
                                    </div>
                                </div>
                                <button class="btn waves-effect waves-light" type="submit">Salvar
                                    <i class="material-icons right">send</i>
                                </button>
                                <a  href="index.jsp" class="btn waves-effect waves-light" name="voltar">Voltar
                                    <i class="material-icons right">arrow_back</i>
                                </a>

                                <br>
                                <br>

                                <h2 class="header">Clientes cadastrados</h2>
                                <table class="highlight">
                                    <thead>
                                        <tr>
                                            <th>Código</th>
                                            <th>Nome</th>
                                            <th>Sobrenome</th>
                                            <th>CPF</th>
                                            <th class="center-align">Ação</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${clientes}" var="cliente">
                                            <tr>
                                                <td><c:out value="${cliente.id}" /></td>
                                                <td><c:out value="${cliente.nome}" /></td>
                                                <td><c:out value="${cliente.sobreNome}" /></td>
                                                <td><c:out value="${cliente.cpf}" /></td>
                                                <td>
                                                    <a class="tooltipped" data-position="top" data-delay="50" data-tooltip="Editar" 
                                                       href="ClientesController?action=edit&id=<c:out value="${cliente.id}"/>">
                                                        <i class="material-icons prefix">edit</i>
                                                    </a>
                                                </td>
                                                <td>
                                                    <a class="tooltipped" data-position="top" data-delay="50" data-tooltip="Excluir" 
                                                       href="ClientesController?action=delete&id=<c:out value="${cliente.id}"/>">
                                                        <i class="material-icons prefix">delete</i>
                                                    </a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
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