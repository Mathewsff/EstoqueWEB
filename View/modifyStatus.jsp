<%-- 
    Document   : modifyStatus
    Author     : Nowll
--%>

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

    <form action="ProcessaStatus" method="post" class="container" onsubmit="return validation()">
        <div class="form-row">
            <div class="form-group col-md-4">
                <label for="addCategoria">ID a ser Modificado</label>
                <input type="text" class="form-control" id="id" name="id" required
                    placeholder="Id da Categoria" value="0">
                
            </div>
            <div class="form-group col-md-8">
                <label for="addNomeCategoria">Status</label>
                <input type="text" class="form-control" id="status" name="status" required
                    placeholder="Nome da Categoria">
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
    
    <script>
            function validation() {
                var id = document.getElementById("id").value;
                var status = document.getElementById("status").value;
                if (id !== "" && status !== "" && (status === "S" || status === "N")) {
                    return true;
                } else {
                    window.alert("Dados inválidos.");
                    return false;
                }
            }
        </script>
</body>

</html>
