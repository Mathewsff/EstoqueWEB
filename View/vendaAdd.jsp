<%-- 
    Document   : vendaAdd
    Created on : 10/07/2022, 17:00:56
    Author     : Nowll
--%>

<%@page import="App.Venda"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<<!doctype html>
<html lang="en">

    <head>
        <title>Adicionar Venda</title>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="css/bootstrap.min.css">

        <style>
            body {
                background-image: url('bgimg.webp');
            }
        </style>
    </head>

    <body>
        <%
               Venda aux = (Venda)request.getAttribute("sale");
            %>
        <form action="VendaController" method="post" class="container">
            <div class="form-row">
                <div class="form-group col-md-2">
                    <label for="addVenda">ID</label>
                    <input type="text" class="form-control" id="id" name="id" placeholder="Id do Cliente" hidden value="<%= aux.getId() %>">
                    <input type="text" class="form-control" id="id" name="id" placeholder="Id do Cliente" disabled value="<%= aux.getId() %>">
                </div>
                <div class="form-group col-md-2">
                    <label for="addQuantidade">Quantidade</label>
                    <input type="text" class="form-control" id="addQuantidade" name="addQuantidade" required
                           value="<%= aux.getQuantidade_venda() %>">
                </div>
                <div class="form-group col-md-4">
                    <label for="addValor">Valor</label>
                    <input type="text" class="form-control" id="addValor" name="addValor" required
                           value="<%= aux.getValor_venda() %>">
                </div>
                <div class="form-group col-md-4">
                    <label for="addData">Data</label>
                    <input type="date" class="form-control" id="addData" name="addData" required value="<%= aux.getData() %>">
                </div>
            </div>

            <div class="form-row">
                <div class="form-group col-md-4">
                    <label for="iDCliente">Id Cliente</label>
                    <input type="text" class="form-control" id="iDCliente" name="iDCliente" required
                           value="<%= aux.getId_cliente() %>">
                </div>
                <div class="form-group col-md-4">
                    <label for="iDProduto">Id Produto</label>
                    <input type="text" class="form-control" id="iDProduto" name="iDProduto" required
                           value="<%= aux.getId_produto() %>">
                </div>
                <div class="form-group col-md-4">
                    <label for="iDVendedor">Id Vendedor</label>
                    <input type="text" class="form-control" id="iDVendedor" name="iDVendedor" required
                           value="<%= aux.getId_funcionario() %>">
                </div>
            </div>
            <button type="submit" class="btn btn-primary">Adicionar</button>
            <button type="button" class="btn btn-danger" onclick="history.back()">Cancelar</button>
        </form>
        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="jquery-3.4.1.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </body>

</html>
