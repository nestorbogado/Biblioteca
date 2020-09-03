<%-- 
    Document   : LIBROS
    Created on : 26/10/2018, 07:28:37 PM
    Author     : USUARIO
--%>
<%
    HttpSession session_actual = request.getSession();
    String n = (String) session_actual.getAttribute("usuario");

    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
    response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
    response.setDateHeader("Expires", 0);
    if (n == null) {
        response.sendRedirect("index.jsp");

    } else {
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>PRESTAMOS</title>
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
                        <h1 class="page-header">PRESTAMO</h1>
                    </div>
                    <!-- /.col-lg-12 --> 
                </div>
                <!-- /.row -->
                <div class="row">
                    <div class="col-lg-12">
                        <div class="panel panel-default">
                            <div class="panel-heading text-right">
                                <div class="row">
                                    <div class="container-fluid">
                                        <div class="col-lg-6 text-left">
                                            <label>ACCIÓN</label>
                                            <select class="form-control" id="accion" >
                                                <option selected value="1">PRESTAMOS PENDIENTES</option>
                                                <option value="2">PRESTAMOS DEVUELTOS</option>
                                                <option value="3">TODOS LOS PRESTAMOS</option>
                                            </select>
                                            <br>
                                        </div>
                                        <div class="col-lg-6">
                                            <br>
                                            <button type="button" class="btn btn-default btn-lg AGREGAR" data-toggle="modal" data-target="#agregar" data-backdrop="static" data-keyboard="false">Agregar Prestamo</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- /.panel-heading -->
                            <div class="panel-body">
                                <div class="">
                                    <table style="width: 100%" class="table table-striped table-bordered table-hover" id="TablaPrestamos">
                                        <thead>
                                            <tr>
                                                <th class="text-center">LIBRO </th>
                                                <th class="text-center">ALUMNO</th>
                                                <th class="text-center">FECHA DE PRESTAMO </th>
                                                <th class="text-center">FECHA DEVOLUCIÓN </th>
                                                <th class="text-center">EDITAR </th>
                                                <th class="text-center">ELIMINAR </th>
                                            </tr>
                                        </thead>
                                        <tbody>

                                        </tbody>
                                    </table>
                                </div>
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

        <!-- Modal Agregar Prestamo -->
        <div id="agregar" class="modal fade" role="dialog">
            <div class="modal-dialog modal-lg"> 

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Agregar Prestamo</h4>
                    </div>
                    <div class="modal-body">
                        <div class="container-fluid">
                            <div class="row">
                                <div class="form-group col-sm-12">
                                    <label>MATERIAL </label> <span class = "rojo"> * </span>
                                    <select class="form-control" id="material" style="width: 100%">

                                    </select>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group col-sm-6">
                                    <label>ALUMNO </label><span class = "rojo"> * </span>
                                    <select class="form-control" id="alumnos" style="width: 100%">

                                    </select>
                                </div>
                                <div class="form-group col-sm-6">
                                    <label>FECHA DE PRESTAMO </label><span class = "rojo"> * </span>
                                    <input class="form-control" type="date" style="width: 100%" id="fechaPrestamo">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                        <button type="button" class="btn btn-primary" id="agregarPrestamo" >Guarda Datos</button>
                    </div>
                </div>
            </div>
        </div>

        <!-- Modal EDITAR PRESTAMO -->
        <div id="editar" class="modal fade" role="dialog">
            <div class="modal-dialog modal-lg"> 

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Editar Datos del Prestamo</h4>
                    </div>
                    <div class="modal-body">
                        <div class="container-fluid">
                            <div class="row">
                                <div class="form-group col-sm-12">
                                    <label>MATERIAL </label><span class = "rojo"> * </span>
                                    <select class="form-control" id="Elibros" style="width: 100%" disabled >

                                    </select>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group col-sm-12">
                                    <label>ALUMNO </label><span class = "rojo"> * </span>
                                    <select class="form-control" id="Ealumnos" style="width: 100%" disabled >

                                    </select>
                                </div>
                            </div>
                            <div class="row">

                                <div class="form-group col-sm-6">
                                    <label>FECHA DE PRESTAMO </label><span class = "rojo"> * </span>
                                    <input class="form-control" type="date" style="width: 100%" id="EfechaPrestamo" >
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                        <button type="button" class="btn btn-primary" id="actualizarDatos" >Guarda Datos</button>
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
        <script src="./js/moment.min.js"></script> 
        <script src="./consultas/prestamo.js"></script> 

    </body>
</html>
<%}%>