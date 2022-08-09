<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        
    </head>
    <body>
        <div class="container mt-2">

            

            <div class="col-8 mt-5">

                <div class="alert alert-success" role="alert">
                    <h5>
                        <%= request.getAttribute("status") %>
                    </h5>
                </div>

                <p></p>
                <div><button type="button" class="btn btn-info" onclick="window.history.go(-3)">Voltar</button></div>
            </div>
        </div>

        
    </body>
</html>




