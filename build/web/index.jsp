<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Java Web</title>
        <!-- Compiled and minified CSS -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.0/css/materialize.min.css">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <style>
            a {
                text-decoration: none !important;
                color: white;
            } 
        </style>
    </head>
    <body>  
        <!--Import jQuery before materialize.js-->
        <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
        <script type="text/javascript" src="js/materialize.min.js"></script>

        <div class="row">


            <div class="col s3 m3">
                <div class="card-panel hoverable teal">
                    <h1 class="card-title white-text" style="text-align: center;"><a href="ClientesController?action=listCliente"><strong>Clientes</strong></a></h1>
                </div>
            </div>
            <div class="col s3 m3">
                <div class="card-panel hoverable teal">
                    <h1 class="card-title white-text" style="text-align: center;" ><a href="ProdutoController?action=listProdutos"><strong>Produtos</strong></a></h1>
                </div>
            </div>

            <div class="col s3 m3">
                <div class="card-panel hoverable teal">
                    <h1 class="card-title white-text" style="text-align: center;"><a href="PedidoController?action=pedido"><strong>Realizar Pedido</strong></a></h1>
                </div>
            </div>
            
            <div class="col s3 m3">
                <div class="card-panel hoverable teal">
                    <h1 class="card-title white-text" style="text-align: center;"><a href="PedidoController?action=listarPedido"><strong>Listar Pedido</strong></a></h1>
                </div>
            </div>
        </div>

    </body>
</html>


