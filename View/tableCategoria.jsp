<%-- 
    Document   : tableCategoria
    Author     : Nowll
--%>

<%@page import="App.Categoria"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
  <head>
    <title>Categoria</title>
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

      
    <table id="table_category" class="table table-bordered">

        <thead class="table-primary">
            <tr>
                <th>id</th>
                <th>nome_categoria</th>
            </tr>
        </thead>

        <tbody>
            
             <%
                            ArrayList<Categoria> ListaCliente =(ArrayList<Categoria>) 
                            request.getAttribute("categoryList");
                            for (int i = 0; i < ListaCliente.size(); i++) {
                                Categoria aux = ListaCliente.get(i);
                                String link_editar = "CategoriaController?action=edit&id="+aux.getId();
                                String link_excluir = "CategoriaController?action=delete&id="+aux.getId();
                        %>
          <tr>
                            <td><%=aux.getId()%></td>
                            <td><%=aux.getNome_categoria()%></td> 
                            <td><a href="<%=link_excluir%>" class="btn btn-outline-danger float-right">Excluir</a> <a href="<%=link_editar%>" class="btn btn-outline-secondary float-right">Editar</a> 
                            </td> 
                           
                        </tr>
                        <%
                            }
                        %>
        </tbody>

    </table>

    <button type="button" class="btn btn-info" onclick="history.back()">Voltar</button>
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="jquery-3.4.1.min.js"></script>
    <script src="js/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="datatables.min.js"></script>


    <script>
        $(document).ready( function () {
            $('#table_category').DataTable();
        } );
    </script>
  </body>
</html>

