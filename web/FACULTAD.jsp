<%-- 
    Document   : facultad
    Created on : 20/11/2018, 12:26:02 PM
    Author     : USUARIO
--%>
<%
HttpSession session_actual = request.getSession();
String n= (String)session_actual.getAttribute("usuario");

response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
response.setDateHeader("Expires", 0);
if(n == null)
    {
    response.sendRedirect("index.jsp");
    
    }else{
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>FACULTAD</title>
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
                        <h1 class="page-header">FACULTAD</h1>
                    </div>
                    <!-- /.col-lg-12 --> 
                </div>
                <!-- /.row -->
                <div class="row">
                    <div class="col-lg-12">
                        <div class="panel panel-default">
                            <div class="panel-heading text-right">
                                <button type="button" class="btn btn-default btn-lg" data-toggle="modal" data-target="#agregar" data-backdrop="static" data-keyboard="false">Agregar Facultad</button>
                            </div>
                            <!-- /.panel-heading -->
                            <div class="panel-body">
                                <table style="width: 100%" class="table table-striped table-bordered table-hover" id="TablaCarreras">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>FACULTAD</th>
                                            <th>EDITAR </th>
                                            <th>ELIMINAR </th>
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
                        <h4 class="modal-title">Agregar Facultad</h4>
                    </div>
                    <div class="modal-body">
                        <div class="container-fluid">
                            <div class="row">
                                <div class="form-group col-sm-12">
                                    <label>Descripcion </label><span class = "rojo"> * </span>
                                    <input class="form-control LETRAS MAYUSCULAS" type="text"  id="descripcion_facultad" maxlength="45" autocomplete="off">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                        <button type="button" class="btn btn-primary" id="agregarCarrera" >Guardar Datos</button>
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
                        <h4 class="modal-title">Editar Datos de la Facultad</h4>
                    </div>
                    <div class="modal-body">
                        <div class="container-fluid">
                            <div class="row">
                                <div class="form-group col-sm-12">
                                    <label>Descripcion </label><span class = "rojo"> * </span>
                                    <input class="form-control LETRAS MAYUSCULAS" type="text" id="Efacultad" maxlength="45"  autocomplete="off">
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

        <script src="./consultas/facultad.js"></script> 

    </body>
</html>
<%}%>
