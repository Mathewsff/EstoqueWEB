<%-- 
    Document   : interfaceVendedor
    Author     : Nowll
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">

<head>
    <title>Title</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <style>
        body {
            background-image:url('bgimg.webp');
        }
    </style>
</head>

<body>

    <div class="list-group container">
        <a href="ClienteController?action=show" class="list-group-item list-group-item-primary list-group-item-action" aria-current="true">Consultar Clientes</a>
        <a href="client_add.html" class="list-group-item list-group-item-secondary list-group-item-action">Adicionar Cliente</a>
        <a href="VendaController?action=show" class="list-group-item list-group-item-primary list-group-item-action">Consultar Vendas</a>
        <a href="vendaAdd.html" class="list-group-item list-group-item-secondary list-group-item-action">Realizar Vendas</a>
    </div>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="jquery-3.4.1.min.js"></script>
    <script src="js/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</body>

</html>
