<%-- 
    Document   : USUARIOS
    Created on : 21/05/2019, 01:53:36 AM
    Author     : USUARIO
--%>
<%
HttpSession session_actual = request.getSession();
String n= (String)session_actual.getAttribute("usuario");
Integer t= (Integer)session_actual.getAttribute("tipo");
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
response.setDateHeader("Expires", 0);
if(n == null)
    {
    response.sendRedirect("index.jsp");
    
    }else{
        if (t==1) {
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>USUARIO</title>
        <link rel="shortcut icon" href="./imagen/ICONO.png" type="image/x-icon">
        <!-- Bootstrap Core CSS -->
        <link href="./vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <!-- MetisMenu CSS -->
        <link href="./vendor/metisMenu/metisMenu.min.css" rel="stylesheet"> 

        <!-- DataTables CSS -->
        <link href="./vendor/datatables-plugins/dataTables.bootstrap.css" rel="stylesheet">

        <!-- DataTables Responsive CSS -->
        <link href="./vendor/datatables-responsive/dataTables.responsive.css" rel="stylesheet">

        <!-- Custom CSS -->
        <link href="./css/sb-admin-2.css" rel="stylesheet">

        <!-- Custom Fonts -->
        <link href="./vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link href="./css/select2.css" rel="stylesheet" type="text/css">
        <link href="./css/sweetalert2.css" rel="stylesheet" type="text/css">
    </head>

    <body>
        <div id="container"> 

            <%@include file="BARRANAV.jsp" %>
            
            <div id="page-wrapper">
                <div class="row">
                    <div class="col-lg-12 text-center">
                        <h1 class="page-header">USUARIOS DEL SISTEMA</h1>
                    </div>
                    <!-- /.col-lg-12 --> 
                </div>
                <!-- /.row -->
                <div class="row">
                    <div class="col-lg-12">
                        <div class="panel panel-default">
                            <div class="panel-heading text-right">
                                <button type="button" class="btn btn-default btn-lg" data-toggle="modal" data-target="#agregar" data-backdrop="static" data-keyboard="false">Agregar Usuario</button>
                            </div>
                            <!-- /.panel-heading -->
                            <div class="panel-body">
                                <table style="width: 100%" class="table table-striped table-bordered table-hover" id="TablaUsuario">
                                    <thead>
                                        <tr>
                                            <th>Nombre Usuario</th>
                                            <th>Usuario</th>
                                            <th>Tipo Usuario</th>
                                            <th>Estado</th>
                                            <th>EDITAR </th>
                                            <th>ANULAR</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    </tbody>
                                </table>
                            </div>
                            <!-- /.panel-body --> 
                        </div>
                        <!-- /.panel --> 
                    </div>
                    <!-- /.col-lg-12 --> 
                </div>
            </div>
            <!-- /#page-wrapper --> 

        </div>
        <!-- /#wrapper --> 

        <!-- Modal Agregar  -->
        <div id="agregar" class="modal fade" role="dialog">
            <div class="modal-dialog"> 

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Agregar Usuario</h4>
                    </div>
                    <div class="modal-body">
                        <div class="container-fluid">
                            <div class="row">
                                <div class="form-group col-sm-6">
                                    <label>NOMBRE </label><span class = "rojo"> * </span>
                                    <input class="form-control LETRAS MAYUSCULAS" type="text"  id="nombre" maxlength="45" autocomplete="off">
                                </div>
                                <div class="form-group col-sm-6">
                                    <label>APELLIDO </label><span class = "rojo"> * </span>
                                    <input class="form-control LETRAS MAYUSCULAS" type="text"  id="apellido" maxlength="45" autocomplete="off">
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group col-sm-6">
                                    <label>USUARIO </label><span class = "rojo"> * </span>
                                    <input class="form-control" type="text"  id="usuario" maxlength="45" autocomplete="off">
                                </div>
                                <div class="form-group col-sm-6">
                                    <label>CONTRASEÑA </label><span class = "rojo"> * </span>
                                    <input class="form-control" type="password"  id="contrasenha" maxlength="15" autocomplete="off">
                                </div>
                            </div>
                            <div class="row"> 
                                <div class="form-group col-sm-6">
                                    <label>TIPO USUARIO </label><span class = "rojo"> * </span>
                                    <select class="form-control" id="tipoU" style="width: 100%" >

                                    </select>
                                </div>
                                <div class="form-group col-sm-6">
                                    <label>ESTADO </label><span class = "rojo"> * </span>
                                    <select class="form-control" id="estado" style="width: 100%" >

                                    </select>
                                </div>
                            </div>  
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                        <button type="button" class="btn btn-primary" id="agregarUsuario" >Guardar Datos</button>
                    </div>
                </div>
            </div>
        </div>

        <!-- Modal EDITAR Libro -->
        <div id="editar" class="modal fade" role="dialog">
            <div class="modal-dialog"> 

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Editar Datos del Usuario</h4>
                    </div>
                    <div class="modal-body">
                        <div class="container-fluid">
                            <div class="row">
                                <div class="form-group col-sm-6">
                                    <label>NOMBRE </label><span class = "rojo"> * </span>
                                    <input class="form-control LETRAS MAYUSCULAS" type="text"  id="Enombre" maxlength="45" autocomplete="off">
                                </div>
                                <div class="form-group col-sm-6">
                                    <label>APELLIDO </label><span class = "rojo"> * </span>
                                    <input class="form-control LETRAS MAYUSCULAS" type="text"  id="Eapellido" maxlength="45" autocomplete="off">
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group col-sm-6">
                                    <label>USUARIO </label><span class = "rojo"> * </span>
                                    <input class="form-control" type="text"  id="Eusuario" maxlength="45" autocomplete="off">
                                </div>
                                <div class="form-group col-sm-6">
                                    <label>CONTRASEÑA </label><span class = "rojo"> * </span>
                                    <input class="form-control" type="password"  id="Econtrasenha" maxlength="15" autocomplete="off">
                                </div>
                            </div>
                            <div class="row"> 
                                <div class="form-group col-sm-6">
                                    <label>TIPO USUARIO </label><span class = "rojo"> * </span>
                                    <select class="form-control" id="EtipoU" style="width: 100%" >

                                    </select>
                                </div>
                                <div class="form-group col-sm-6">
                                    <label>ESTADO </label><span class = "rojo"> * </span>
                                    <select class="form-control" id="Eestado" style="width: 100%" >

                                    </select>
                                </div>
                            </div> 
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                        <button type="button" class="btn btn-primary" id="actualizarDatos" >Guardar Datos</button>
                    </div>
                </div>
            </div>
        </div>

        <!-- jQuery --> 
        <script src="./vendor/jquery/jquery.min.js"></script> 

        <!-- Bootstrap Core JavaScript --> 
        <script src="./vendor/bootstrap/js/bootstrap.min.js"></script> 

        <!-- Metis Menu Plugin JavaScript --> 
        <script src="./vendor/metisMenu/metisMenu.min.js"></script> 

        <!-- DataTables JavaScript --> 
        <script src="./vendor/datatables/js/jquery.dataTables.js"></script> 
        <script src="./vendor/datatables-plugins/dataTables.bootstrap.min.js"></script> 
        <script src="./vendor/datatables-responsive/dataTables.responsive.js"></script> 
        <script src="./js/select2.full.js"></script> 
        <script src="./js/sweetalert2.min.js"></script> 
        <!-- Custom Theme JavaScript --> 
        <script src="./js/sb-admin-2.js"></script> 
        
        <script src="./consultas/usuario.js"></script> 

    </body>
</html>

<% }
}%>