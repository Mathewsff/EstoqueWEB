<%-- 
    Document   : interfaceComprador
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
        <a href="FornecedorController?action=show" class="list-group-item list-group-item-primary list-group-item-action" aria-current="true">Consultar Fornecedores</a>
        <a href="fornecedor_add.html" class="list-group-item list-group-item-secondary list-group-item-action">Adicionar Fornecedor</a>
        <a href="CompraController?action=show" class="list-group-item list-group-item-primary list-group-item-action">Consultar Compras</a>
        <a href="compra_add.html" class="list-group-item list-group-item-secondary list-group-item-action">Efetuar Compra</a>
        <a href="CategoriaController?action=show" class="list-group-item list-group-item-primary list-group-item-action">Consultar Categorias</a>
        <a href="categoria_add.html" class="list-group-item list-group-item-secondary list-group-item-action">Adicionar Categoria</a>
        <a href="modifyStatus.jsp" class="list-group-item list-group-item-primary list-group-item-action">Liberar Produto</a>
    </div>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="jquery-3.4.1.min.js"></script>
    <script src="js/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</body>

</html>
