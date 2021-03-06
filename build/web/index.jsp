<%-- 
    Document   : index
    Created on : 09/11/2018, 07:02:47 PM
    Author     : USUARIO
--%>
<%
    HttpSession session_actual = request.getSession();
    String n = (String) session_actual.getAttribute("usuario");

    if (n != null) {
        session_actual.setAttribute("usuario", "");
        session_actual.invalidate();
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setDateHeader("Expires", 0);
    }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">

    
        
        <title> Inicio | Sistema Biblioteca</title>
        <link rel="shortcut icon" href="./imagen/ICONO.png" type="image/x-icon">
        <!-- Bootstrap Core CSS -->
        <link href="./vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <!-- MetisMenu CSS -->
        <link href="./vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

        <!-- Custom CSS -->
        <link href="./dist/css/sb-admin-2.css" rel="stylesheet">

        <!-- Custom Fonts -->
        <link href="./vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link href="./css/select2.css" rel="stylesheet" type="text/css">
        <link href="./css/sweetalert2.css" rel="stylesheet" type="text/css">


    </head>

    <body class="container">
        <br><br>
        <div>
            <center>
        <img src="ArchivoReporte/isologo_uc.png" width="300" height="175" align="middle">
    </center>
            
            <div class="row">
                <div class="col-md-4 col-xs-4 col-md-offset-4 col-xs-offset-4">
                    <h4 class="text-center">Biblioteca "Dr. Jesús René Haurón Acuña"</h4>
                    <br>
                    <div class="login-panel panel panel-default">
                        
                        <div class="panel-heading">
                            <h3 class=" text-center">Bibliosoft</h3>
                        </div>
                        
                        <div class="panel-body">
                            <form >

                                <div class="form-group">
                                    <input class="form-control" placeholder="Usuario" name="usuario" id="usuario" type="text" autocomplete="off" autofocus>
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="Contraseña" name="contrasenha" id="contrasenha" type="password" autocomplete="off" >
                                </div>
                                <button  type="button" class='btn  btn-success btn-block' id='iniciarSesion'>ACCEDER</button>

                            </form>
                        </div>
                    </div>
                </div>
            </div>
            
            <center>
        <img src="ArchivoReporte/isologo_vector.png" width="300" height="95" align="middle">
    </center>
            
            <a href="./acerca_de.jsp">
                <p align="right">
                    Acerca de
                </p>
                
            </a>
        </div>

        <!-- jQuery -->
        <script src="./vendor/jquery/jquery.min.js"></script>

        <!-- Bootstrap Core JavaScript -->
        <script src="./vendor/bootstrap/js/bootstrap.min.js"></script>

        <!-- Metis Menu Plugin JavaScript -->
        <script src=./vendor/metisMenu/metisMenu.min.js"></script>

        <!-- Custom Theme JavaScript -->
        <script src="./dist/js/sb-admin-2.js"></script>
        <script src="./js/select2.full.js"></script> 
        <script src="./js/sweetalert2.min.js"></script> 
        <script src="./consultas/Acceso.js"></script> 
    </body>

</html>
