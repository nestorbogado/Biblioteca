<%-- 
    Document   : ALUMNO
    Created on : 20/11/2018, 03:28:03 PM
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
        
        <title>ALUMNOS</title>
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
                        <h1 class="page-header">ALUMNOS</h1>
                    </div>
                    <!-- /.col-lg-12 --> 
                </div>
                <!-- /.row -->
                <div class="row">
                    <div class="col-lg-12">
                        <div class="panel panel-default">
                            <div class="panel-heading text-right">
                                <button type="button" class="btn btn-default btn-lg" data-toggle="modal" data-target="#agregar" data-backdrop="static" data-keyboard="false">Agregar Alumno</button>
                            </div>
                            <!-- /.panel-heading -->
                            <div class="panel-body">
                                <table style="width: 100%" class="table table-striped table-bordered table-hover" id="TablaAlumnos">
                                    <thead>
                                        <tr>
                                            <th>NRO DOC.</th>
                                            <th>ALUMNO</th>
                                            <th>CARRERA</th>
                                            <th>TELEFONO</th>
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
                        <h4 class="modal-title">Agregar Alumno</h4>
                    </div>
                    <div class="modal-body">
                        <div class="container-fluid">
                            <div class="row">
                                <div class="form-group col-sm-6">
                                    <label>NOMBRE/S </label><span class = "rojo"> * </span>
                                    <input class="form-control LETRAS MAYUSCULAS" type="text"  id="nombre" autocomplete="off" maxlength="45">
                                </div>
                                <div class="form-group col-sm-6">
                                    <label>APELLIDO/S </label><span class = "rojo"> * </span>
                                    <input class="form-control LETRAS MAYUSCULAS" type="text"  id="apellido" autocomplete="off" maxlength="45">
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group col-sm-6">
                                    <label>TIPO DOCUMENTO IDENTIDAD </label><span class = "rojo"> * </span>
                                    <select class="form-control" id="ti" style="width: 100%">

                                    </select>
                                </div>
                                <div class="form-group col-sm-6">
                                    <label>N° DE DOCUMENTO </label><span class = "rojo"> * </span>
                                    <input class="form-control NUMEROS" type="text"  id="ci" autocomplete="off" maxlength="8">
                                </div>

                            </div>
                            <div class="row">
                                <div class="form-group col-sm-6">
                                    <label>TELEFONO </label><span class = "rojo"> * </span>
                                    <input class="form-control NUMEROS" type="text"  id="te" autocomplete="off" maxlength="10">
                                </div>
                                <div class="form-group col-sm-6">
                                    <label>CARRERA </label><span class = "rojo"> * </span>
                                    <select class="form-control" id="carrera" style="width: 100%">

                                    </select>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group col-sm-6">
                                    <label>FECHA DE INGRESO </label><span class = "rojo"> * </span>
                                    <input class="form-control" type="date" style="width: 100%" id="fechaIngreso">
                                </div>

                                <div class="form-group col-sm-6">
                                    <label>FECHA DE RENOVACIÓN </label><span class = "rojo"> * </span>
                                    <input class="form-control" type="date" style="width: 100%" id="fechaRenovacion">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                        <button type="button" class="btn btn-primary" id="agregarAlumno" >Guardar Datos</button>
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
                        <h4 class="modal-title">Editar Datos del Alumno</h4>
                    </div>
                    <div class="modal-body">
                        <div class="container-fluid">
                            <div class="row">
                                <div class="form-group col-sm-6">
                                    <label>NOMBRE </label><span class = "rojo"> * </span>
                                    <input class="form-control LETRAS MAYUSCULAS" type="text"  id="Enombre" autocomplete="off" maxlength="45">
                                </div>
                                <div class="form-group col-sm-6">
                                    <label>APELLIDO </label><span class = "rojo"> * </span>
                                    <input class="form-control LETRAS MAYUSCULAS" type="text"  id="Eapellido" autocomplete="off" maxlength="45">
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group col-sm-6">
                                    <label>TIPO DOCUMENTO IDENTIDAD </label><span class = "rojo"> * </span>
                                    <select class="form-control" id="Eti" style="width: 100%">

                                    </select>
                                </div>
                                <div class="form-group col-sm-6">
                                    <label>N° DE DOCUMENTO </label><span class = "rojo"> * </span>
                                    <input class="form-control NUMEROS" type="text"  id="Eci" autocomplete="off" maxlength="8">
                                </div>

                            </div>
                            <div class="row">
                                <div class="form-group col-sm-6">
                                    <label>TELEFONO </label><span class = "rojo"> * </span>
                                    <input class="form-control NUMEROS" type="text"  id="Ete" autocomplete="off" maxlength="10">
                                </div>
                                <div class="form-group col-sm-6">
                                    <label>Carrera </label><span class = "rojo"> * </span>
                                    <select class="form-control" id="Ecarrera" style="width: 100%">

                                    </select>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group col-sm-6">
                                    <label>FECHA DE INGRESO </label><span class = "rojo"> * </span>
                                    <input class="form-control" type="date" style="width: 100%" id="EfechaIngreso">
                                </div>
                                <div class="form-group col-sm-6">
                                    <label>FECHA DE RENOVACIÓN </label><span class = "rojo"> * </span>
                                    <input class="form-control" type="date" style="width: 100%" id="EfechaRenovacion">
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

        <script src="./consultas/alumno.js"></script> 

    </body>
</html>
<%}%>