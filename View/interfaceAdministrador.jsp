<%-- 
    Document   : interfaceAdministrador
    Author     : Nowll
--%>
<!-- String role = (String)request.getSession().getAttribute("papel"); -->
<% if (session.getAttribute("papel").equals(0)) {%>
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
        <a href="FuncionarioController?action=show" class="list-group-item list-group-item-primary list-group-item-action" aria-current="true">Consultar Funcionários</a>
        <a href="funcionarios_add.html" class="list-group-item list-group-item-secondary list-group-item-action">Adicionar Funcionários</a>
        <a href="FuncionarioController?action=showVen" class="list-group-item list-group-item-primary list-group-item-action">Consultar Vendedores</a>
        <a href="FuncionarioController?action=showCom" class="list-group-item list-group-item-secondary list-group-item-action">Consultar Compradores</a>
        <a href="FuncionarioController?action=showAdm" class="list-group-item list-group-item-primary list-group-item-action">Consultar Administradores</a>
        <a href="ProdutoController?action=showAll" class="list-group-item list-group-item-secondary list-group-item-action">Gerar Relatório do Estoque</a>
        <a href="VendaController?action=report" class="list-group-item list-group-item-primary list-group-item-action">Gerar Relatório de Vendas</a>
    </div>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="jquery-3.4.1.min.js"></script>
    <script src="js/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</body>

</html>
<% }%>
