<%-- 
    Document   : tableFuncionario
    Created on : 21/07/2022, 02:35:43
    Author     : Nowll
--%>

<%@page import="App.Funcionario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
    <head>
        <title>Funcionários</title>
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


        <table id="table_Funcionario" class="table table-bordered">

            <thead class="table-primary">
                <tr>
                    <th>ID</th>
                    <th>Nome</th>
                    <th>CPF</th>
                    <th>Senha</th>
                    <th>Papel</th>
                </tr>
            </thead>

            <tbody>

                <%
                    ArrayList<Funcionario> ListaFuncionario = (ArrayList<Funcionario>) request.getAttribute("employeeList");
                    for (int i = 0; i < ListaFuncionario.size(); i++) {
                        Funcionario aux = ListaFuncionario.get(i);
                        String link_editar = "FuncionarioController?action=edit&id=" + aux.getId();
                        String link_excluir = "FuncionarioController?action=delete&id=" + aux.getId();
                %>
                <tr>
                    <td><%=aux.getId()%></td>
                    <td><%=aux.getNome()%></td> 
                    <td><%=aux.getCpf()%></td>
                    <td><%=aux.getSenha()%></td> 
                    <% switch (aux.getPapel()) {
                            case 0:
                    %>
                    <td>Administrador</td>
                    <% break;
                        case 1:%>
                    <td>Vendedor</td>
                    <% break;
                        case 2:%>
                    <td>Comprador</td>
                    <% break;
                        }%>
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
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        <script type="text/javascript" src="datatables.min.js"></script>



        <script>
            $(document).ready(function () {
                $('#table_Funcionario').DataTable();
            });
        </script>
    </body>
</html>