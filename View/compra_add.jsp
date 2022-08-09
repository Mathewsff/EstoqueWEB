<%-- 
    Document   : compra_add
    Author     : Nowll
--%>

<%@page import="App.Compra"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
  <head>
    <title>Adicionar Compra</title>
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
               Compra aux = (Compra)request.getAttribute("purchase");
            %>
    <form action="CompraController" method="post" class="container">
        <div class="form-row">
            <div class="form-group col-md-2">
                <label for="addCompra">ID</label>
                <input type="text" class="form-control" id="id" name="id" hidden value="<%= aux.getId() %>">
                <input type="text" class="form-control" id="id" name="id" disabled value="<%= aux.getId() %>" placeholder="Id da Compra">
            </div>
            <div class="form-group col-md-2">
                <label for="addQuantidade">Quantidade</label>
                <input type="text" class="form-control" id="addQuantidade" name="addQuantidade" required value="<%= aux.getQuantidade_compra() %>">
            </div>
            <div class="form-group col-md-4">
                <label for="addValor">Valor</label>
                <input type="text" class="form-control" id="addValor" name="addValor" required value="<%= aux.getValor_compra() %>">
            </div>
            <div class="form-group col-md-4">
                <label for="addData">Data</label>
                <input type="date" class="form-control" id="addData" name="addData" required value="<%= aux.getData_compra() %>">
            </div>
        </div>

        <div class="form-row">
            <div class="form-group col-md-4">
                <label for="iDFornecedor">ID Fornecedor</label>
                <input type="text" class="form-control" id="iDFornecedor" name="iDFornecedor" required value="<%= aux.getId_fornecedor() %>">
            </div>
            <div class="form-group col-md-4">
                <label for="iDProduto">ID Produto</label>
                <input type="text" class="form-control" id="iDProduto" name="iDProduto" required value="<%= aux.getId_produto() %>">
            </div>
            <div class="form-group col-md-4">
                <label for="iDFuncionario">ID Funcionário</label>
                <input type="text" class="form-control" id="iDFuncionario" name="iDFuncionario" required value="<%= aux.getId_funcionario() %>">
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
