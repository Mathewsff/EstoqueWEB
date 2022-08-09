<%-- 
    Document   : funcionario_add
    Created on : 21/07/2022, 02:51:09
    Author     : Nowll
--%>
<% if (session.getAttribute("papel").equals(0)) {%>
<%@page import="App.Funcionario"%>
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
                background-image: url('bgimg.webp');
            }
        </style>
    </head>

    <body>

        <%
            Funcionario aux = (Funcionario) request.getAttribute("employee");
        %>

        <form action="FuncionarioController" method="post" class="container">
            <div class="form-row">
                <div class="form-group col-md-2">
                    <label for="addFuncionario">ID</label>
                    <input type="text" class="form-control" id="id" name="id" hidden placeholder="Id do Funcionário" value="<%= aux.getId()%>">
                    <input type="text" class="form-control" id="id" name="id" disabled placeholder="Id do Funcionário" value="<%= aux.getId()%>">
                </div>
                <div class="form-group col-md-8">
                    <label for="addNome">Nome</label>
                    <input type="text" class="form-control" id="addName" name="addName" required placeholder="Nome do Funcionário" value="<%= aux.getNome()%>">
                </div>
                <div class="form-group col-md-2">
                    <label for="addPapel">Papel</label>
                    <select class="form-control" id="addPapel" name="addPapel" required placeholder="Papel">
                        <% switch (aux.getPapel()) {
                            case 0:%>
                        <option selected="selected" value="0" >Administrador</option>
                        <option value="1">Vendedor</option>
                        <option value="2">Comprador</option>
                        <%break;
                            case 1:%>
                        <option value="0" >Administrador</option>
                        <option selected="selected" value="1">Vendedor</option>
                        <option value="2">Comprador</option>
                        <%break;
                            case 2:%>
                        <option value="0" >Administrador</option>
                        <option value="1">Vendedor</option>
                        <option selected="selected" value="2">Comprador</option>
                        <%break;
                            }
                        %>

                    </select>
                </div>
            </div>

            <div class="form-row">
                <div class="form-group col-md-6">
                    <label for="addCPF">CPF</label>
                    <input type="text" class="form-control" id="addCPF" name="addCPF" required placeholder="CPF" value="<%= aux.getCpf()%>">
                </div>
                <div class="form-group col-md-6">
                    <label for="addSenha">Senha</label>
                    <input type="text" class="form-control" id="addSenha" name="addSenha" required placeholder="Senha" value="<%= aux.getSenha()%>">
                </div>
            </div>
            <div class="form-row"> 
                <!-- <div class="form-group col-md-12">
                    <label for="addEmail">Email</label>
                    <input type="text" class="form-control" id="addEmail" name="addEmail" required placeholder="Email">
                </div> -->
            </div> 
            <button type="submit" class="btn btn-primary">Atualizar</button>
            <button type="button" class="btn btn-danger" onclick="history.back()">Cancelar</button>
        </form>
        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="jquery-3.4.1.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </body>

</html>
<% }%>