<%-- 
    Document   : categoria_add

    Author     : Nowll
--%>

<%@page import="App.Categoria"%>
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

    <%
               Categoria aux = (Categoria)request.getAttribute("category");
            %>
    <form action="CategoriaController" method="post" class="container">
        <div class="form-row">
            <div class="form-group col-md-4">
                <label for="addCategoria">ID</label>
                <input type="text" class="form-control" id="id" name="id" hidden
                    placeholder="Id da Categoria" value="<%= aux.getId() %>">
                <input type="text" class="form-control" id="id" name="id" disabled
                    placeholder="Id da Categoria" value="<%= aux.getId() %>">
            </div>
            <div class="form-group col-md-8">
                <label for="addNomeCategoria">Nome da Categoria</label>
                <input type="text" class="form-control" id="addNomeCategoria" name="addNomeCategoria" required
                    placeholder="Nome da Categoria" value="<%= aux.getNome_categoria() %>">
            </div>
            <button type="submit" class="btn btn-primary">Adicionar</button>
            <button type="button" class="btn btn-danger" onclick="history.back()">Cancelar</button>
    </form>
    </div>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="jquery-3.4.1.min.js"></script>
    <script src="js/popper.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</body>

</html>
