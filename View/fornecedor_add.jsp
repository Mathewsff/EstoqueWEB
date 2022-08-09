<%-- 
    Document   : fornecedor_add
    Author     : Nowll
--%>

<%@page import="App.Fornecedor"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
  <head>
    <title>Cadastrar Cliente</title>
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
               Fornecedor aux = (Fornecedor)request.getAttribute("supplier");
            %>
      
        <form action="FornecedorController" method="post" class="container">
            <div class="form-row">
                <div class="form-group col-md-2">
                    <label for="add_name">ID</label>
                    <input type="text" class="form-control" id="id" name="id" hidden placeholder="Id do Cliente" value="<%= aux.getId() %>">
                    <input type="text" class="form-control" id="id" name="id" disabled placeholder="Id do Cliente" value="<%= aux.getId() %>">
                </div>
                <div class="form-group col-md-6">
                    <label for="add_client">Razão Social</label>
                    <input type="text" class="form-control" id="addRazao" name="addRazao" required value="<%= aux.getRazao_social() %>">
                </div>
                <div class="form-group col-md-4">
                    <label for="addCpf">CNPJ</label>
                    <input type="text" class="form-control" id="addCNPJ" name="addCNPJ" required value="<%= aux.getCnpj() %>">
                </div>
            </div>

            <div class="form-row">
                <div class="form-group col-md-8">
                    <label for="addEndereco">Endereço</label>
                    <input type="text" class="form-control" id="addEndereco" name="addEndereco" required value="<%= aux.getEndereco() %>">
                </div>
                 <div class="form-group col-md-4">
                    <label for="addEndereco">CEP</label>
                    <input type="text" class="form-control" id="addCEP" name="addCEP" required value="<%= aux.getCep() %>">
                </div>
            </div>

            <div class="form-row">
                <div class="form-group col-md-6">
                    <label for="cidade">Cidade</label>
                    <input type="text" class="form-control" id="addCidade" name="addCidade" required value="<%= aux.getCidade() %>">
                </div>
                <div class="form-group col-md-4">
                    <label for="bairro">Bairro</label>
                    <input type="text" id="addBairro" name="addBairro" class="form-control" required value="<%= aux.getBairro() %>">
                </div>
                <div class="form-group col-md-2">
                    <label for="addUF">UF</label>
                    <input type="text" class="form-control" id="addUF" name="addUF" required value="<%= aux.getUf() %>">
                </div>
            </div>

            <div class="form-row">
                <div class="form-group col-md-6">
                    <label for="addTelefone">Telefone</label>
                    <input type="text" class="form-control" id="addTelefone" name="addTelefone" value="<%= aux.getTelefone() %>">
                </div>
                <div class="form-group col-md-6">
                    <label for="addEmail">Email</label>
                    <input type="text" class="form-control" id="addEmail" name="addEmail" value="<%= aux.getEmail() %>">
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
