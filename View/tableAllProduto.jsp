<%-- 
    Document   : tableAllProduto
    Created on : 21/07/2022, 03:48:32
    Author     : Nowll
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="App.Produto"%>

<!doctype html>
<html lang="en">
  <head>
    <title>Produtos</title>
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

      
    <table id="table_Produto" class="table table-bordered">

        <thead class="table-primary">
            <tr>
                <th>ID</th>
                <th>Nome_Produto</th>
                <th>Descrição</th>
                <th>Preço_Compra</th>
                <th>Preço_Venda</th>
                <th>Quantidade</th>
                <th>Liberado</th>
                <th>Categoria</th>
            </tr>
        </thead>

        <tbody>
            
            <%
                            ArrayList<Produto> ListaProduto =(ArrayList<Produto>) 
                            request.getAttribute("productList");
                            for (int i = 0; i < ListaProduto.size(); i++) {
                                Produto aux = ListaProduto.get(i);
                               
                        %>
                        <tr>
                            <td><%=aux.getId()%></td>
                            <td><%=aux.getNome_produto()%></td> 
                            <td><%=aux.getDescricao()%></td>
                            <td><%=aux.getPreco_compra()%></td> 
                            <td><%=aux.getPreco_venda()%></td> 
                            <td><%=aux.getQuantidade_disponivel()%></td> 
                            <td><%=aux.getLiberado_venda()%></td> 
                            <td><%=aux.getFk()%></td> 
                  
                            
                             
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
            $('#table_Produto').DataTable();
        } );
    </script>
  </body>
</html>

