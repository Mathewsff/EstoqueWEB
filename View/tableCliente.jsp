<%-- 
    Document   : tableCliente
    
    Author     : Nowll
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="App.Cliente"%>
<%@ page import="java.util.*,App.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
  <head>
    <title>Clientes</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap & DataTables CSS -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="datatables.min.css"/>
    <style>
      body {
        background-image: url('bgimg.webp');
      }
  </style>
  </head>
  <body>

      
    <table id="table_Cliente" class="table table-bordered">

        <thead class="table-primary">
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>CPF</th>
                <th>Endereço</th>
                <th>Bairro</th>
                <th>Cidade</th>
                <th>UF</th>
                <th>CEP</th>
                <th>Telefone</th>
                <th>Email</th>
            </tr>
        </thead>

        <tbody>
            <%
                            ArrayList<Cliente> ListaCliente =(ArrayList<Cliente>) 
                            request.getAttribute("clientList");
                            for (int i = 0; i < ListaCliente.size(); i++) {
                                Cliente aux = ListaCliente.get(i);
                                String link_editar = "ClienteController?action=edit&id="+aux.getId();
                                String link_excluir = "ClienteController?action=delete&id="+aux.getId();
                        %>
                        <tr>
                            <td><%=aux.getId()%></td>
                            <td><%=aux.getNome()%></td> 
                            <td><%=aux.getCpf()%></td>
                            <td><%=aux.getEndereco()%></td> 
                            <td><%=aux.getBairro()%></td> 
                            <td><%=aux.getCidade()%></td> 
                            <td><%=aux.getUf()%></td> 
                            <td><%=aux.getCep()%></td> 
                            <td><%=aux.getTelefone()%></td> 
                            <td><%=aux.getEmail()%></td> 
                            <td><a href="<%=link_excluir%>" class="btn btn-outline-danger float-right">Excluir</a> <a href="<%=link_editar%>" class="btn btn-outline-secondary float-right">Editar</a> 
                            </td> 
                           
                        </tr>
                        <%
                            }
                        %>
        </tbody>

    </table>

    <button type="button" class="btn btn-info" onclick="history.back(2)">Voltar</button>
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <script type="text/javascript" src="datatables.min.js"></script>


    <script>
        $(document).ready( function () {
            $('#table_Cliente').DataTable();
        } );
    </script>
  </body>
</html>