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

        <title>MATERIALES</title>
        <link rel="shortcut icon" href="./imagen/ICONO.png" type="image/x-icon">
        <!-- Bootstrap Core CSS -->
        <link href="./vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <!-- MetisMenu CSS -->
        <link href="./vendor/metisMenu/metisMenu.min.css" rel="stylesheet"> 

        <!-- DataTables CSS -->
        <link href="./vendor/datatables-plugins/dataTables.bootstrap.css" rel="stylesheet">

        <!-- DataTables Responsive CSS 
        <link href="./vendor/datatables-responsive/dataTables.responsive.css" rel="stylesheet">-->

        <!-- Custom CSS -->
        <link href="./css/sb-admin-2.css" rel="stylesheet">

        <!-- Custom Fonts -->
        <link href="./vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link href="./css/select2.css" rel="stylesheet" type="text/css">
        <link href="./css/sweetalert2.css" rel="stylesheet" type="text/css">
    </head>

    <body class="">
        <div id="container"> 

            <%@include file="BARRANAV.jsp" %>

            <div id="page-wrapper">
                <div class="row">
                    <div class="col-lg-12 text-center">
                        <h1 class="page-header">MATERIALES</h1>
                    </div>
                    <!-- /.col-lg-12 --> 
                </div>
                <div class="">
                    <!-- /.row -->
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <div class="row">
                                        <div class="col-lg-4 text-center">
                                            <button  type="button" class="btn btn-default btn-lg btn-block BOTONAGREGAR" data-toggle="modal" data-target="#agregarLibros" data-backdrop="static" data-keyboard="false">Libros</button>
                                        </div>
                                        <div class="col-lg-4 text-center">
                                            <button   type="button" class="btn btn-default btn-lg btn-block BOTONAGREGAR" data-toggle="modal" data-target="#agregarTI" data-backdrop="static" data-keyboard="false">Trabajo de Investigación</button>
                                        </div>
                                        <div class="col-lg-4 text-center">
                                            <button  type="button" class="btn btn-default btn-lg btn-block BOTONAGREGAR" data-toggle="modal" data-target="#agregarRevista" data-backdrop="static" data-keyboard="false">Otros Materiales</button>
                                        </div>
                                    </div>
                                </div>

                                <!-- /.panel-heading -->
                                <div class="panel-body">
                                    <div class="table-responsive">
                                        <table style="width: 100%" class="table table-striped table-bordered table-hover table-responsive" id="TablaLibros">
                                            <thead>
                                                <tr>
                                                    <th>ISBN </th>
                                                    <th>ISSN </th>
                                                    <th>TITULO</th>
                                                    <th>AÑO DE PUBLICACIÓN </th>
                                                    <th>ESTADO </th>
                                                    <th>N° ENTRADA </th>
                                                    <th>TIPO </th>
                                                    <th>ACCIONES(M/E/D)</th>
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
            </div>
            <!-- /#page-wrapper --> 

        </div>
        <!-- /#wrapper --> 

        <!-- Modal Agregar Libro -->
        <div id="agregarLibros" class="modal fade " role="dialog">
            <div class="modal-dialog modal-lg"> 

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Agregar Libro</h4>
                    </div>
                    <div class="modal-body">
                        <div class="container-fluid">
                            <div class="row">
                                <div class="form-group col-sm-3">
                                    <label>ISBN </label><span class = "rojo"></span>
                                    <input class="form-control MAYUSCULAS" type="text" style="width: 100%" id="isbn" maxlength="45" autocomplete="off">
                                </div>
                                <div class="form-group col-sm-3">
                                    <label>AÑO DE PUBLICACIÓN </label><span class = "rojo"> * </span>
                                    <input class="form-control NUMEROS" type="text" id="anho" maxlength="4" autocomplete="off">
                                </div>
                                <div class="form-group col-sm-3">
                                    <label>TITULO </label><span class = "rojo"> * </span>
                                    <input class="form-control MAYUSCULAS" type="text"  id="titulo" maxlength="100" autocomplete="off">
                                </div>
                                <div class="form-group col-sm-3">
                                    <label>NUMERACIÓN DEWEY </label><span class = "rojo"> * </span>
                                    <input class="form-control NUMEROS" type="text"  id="dewey" maxlength="40" autocomplete="off">
                                </div>
                            </div>

                            <div class="row">
                                <div class="form-group col-sm-3">
                                    <label>EDITORIAL </label><span class = "rojo"> * </span>
                                    <select class="js-states form-control" id="editorial" style="width: 100%">
                                    </select>
                                </div>
                                <div class="form-group col-sm-3">
                                    <label>NÚMERO DE ENTRADA </label><span class = "rojo"> * </span>
                                    <input class="form-control NUMEROS" type="text"  id="numeroEntrada" maxlength="7" autocomplete="off">
                                </div>
                                <div class="form-group col-sm-3">
                                    <label>ESTADO </label><span class = "rojo"> * </span>
                                    <select class="form-control" id="estado" style="width: 100%">
                                    </select>
                                </div>
                                <div class="form-group col-sm-3">
                                    <label>FECHA INGRESO </label> <span class = "rojo"> * </span>
                                    <input class="form-control" type="date"  id="fechaIngreso">
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group col-sm-4">
                                    <label>AUTOR/S </label><span class = "rojo"> * </span>
                                    <select class="js-states form-control" multiple="multiple" id="autor" style="width: 100%">

                                    </select>
                                </div>
                                <div class="form-group col-sm-4">
                                    <label>CARRERA/S </label><span class = "rojo"> * </span>
                                    <select class="form-control" id="carrera"  multiple="multiple" style="width: 100%">

                                    </select>
                                </div>
                                <div class="form-group col-sm-4">
                                    <label>GENERO/S </label><span class = "rojo"> * </span>
                                    <select class="form-control" id="genero"  multiple="multiple" style="width: 100%">

                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                        <button type="button" class="btn btn-primary" id="agregarLibro" >Guardar Datos</button>
                    </div>
                </div>
            </div>
        </div>

        <!-- Modal EDITAR Libro -->
        <div id="editar" class="modal fade " role="dialog">
            <div class="modal-dialog modal-lg"> 

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Modificar Libro</h4>
                    </div>
                    <div class="modal-body">
                        <div class="container-fluid">
                            <div class="row">
                                <div class="form-group col-sm-3">
                                    <label>ISBN </label><span class = "rojo"></span>
                                    <input class="form-control MAYUSCULAS" type="text" style="width: 100%" id="Eisbn" autocomplete="off" maxlength="45" >
                                </div>
                                <div class="form-group col-sm-3">
                                    <label>AÑO DE PUBLICACIÓN </label><span class = "rojo"> * </span>
                                    <input class="form-control NUMEROS" type="text" id="Eanho" autocomplete="off" maxlength="4" >
                                </div>
                                <div class="form-group col-sm-3">
                                    <label>TITULO </label><span class = "rojo"> * </span>
                                    <input class="form-control MAYUSCULAS" type="text"  id="Etitulo" autocomplete="off" maxlength="100" >
                                </div>
                                <div class="form-group col-sm-3">
                                    <label>NUMERACIÓN DEWEY </label><span class = "rojo"> * </span>
                                    <input class="form-control NUMEROS" type="text"  id="Edewey" autocomplete="off" maxlength="40" >
                                </div>
                            </div>

                            <div class="row">
                                <div class="form-group col-sm-3">
                                    <label>EDITORIAL </label><span class = "rojo"> * </span>
                                    <select class="form-control" id="Eeditorial" style="width: 100%">

                                    </select>
                                </div>
                                <div class="form-group col-sm-3">
                                    <label>NÚMERO DE ENTRADA </label><span class = "rojo"> * </span>
                                    <input class="form-control NUMEROS" type="text"  id="EnumeroEntrada" autocomplete="off" maxlength="7" disabled >
                                </div>
                                <div class="form-group col-sm-3">
                                    <label>ESTADO </label><span class = "rojo"> * </span>
                                    <select class="form-control" id="Eestado" style="width: 100%">
                                    </select>
                                </div>
                                <div class="form-group col-sm-3">
                                    <label>FECHA INGRESO </label><span class = "rojo"> * </span>
                                    <input class="form-control" type="date"  id="EfechaIngreso" >
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group col-sm-4">
                                    <label>AUTOR/S </label><span class = "rojo"> * </span>
                                    <select class="form-control" multiple="multiple" id="Eautor" style="width: 100%">
                                        <option Value="1"  selected>1</option>
                                        <option Value="2">2</option>
                                        <option>3</option>
                                        <option>4</option>
                                        <option>5</option>
                                    </select>
                                </div>
                                <div class="form-group col-sm-4">
                                    <label>CARRERA </label><span class = "rojo"> * </span>
                                    <select class="form-control" id="Ecarrera"  multiple="multiple" style="width: 100%">
                                        <option value="1">1</option>
                                        <option value="2">2</option>
                                        <option>3</option>
                                        <option>4</option>
                                        <option>5</option>
                                    </select>
                                </div>
                                <div class="form-group col-sm-4">
                                    <label>GENERO </label><span class = "rojo"> * </span>
                                    <select class="form-control" id="Egenero"  multiple="multiple" style="width: 100%">
                                        <option value="1">1</option>
                                        <option value="2">2</option>
                                        <option>3</option>
                                        <option>4</option>
                                        <option>5</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                        <button type="button" class="btn btn-primary" id="modificarLibro" >Guardar Datos</button>
                    </div>
                </div>
            </div>
        </div>

        <!-- MODAL DE AGREGAR TRABAJO DE INVESIGACION-->

        <div id="agregarTI" class="modal fade " role="dialog">
            <div class="modal-dialog modal-lg"> 

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Agregar Trabajo de Investigación</h4>
                    </div>
                    <div class="modal-body">
                        <div class="container-fluid">
                            <div class="row">
                                <div class="form-group col-sm-4">
                                    <label>TITULO-DESCRIPCIÓN </label><span class = "rojo"> * </span>
                                    <input class="form-control MAYUSCULAS" type="text" style="width: 100%" id="tituloTI" maxlength="100" autocomplete="off">
                                </div>
                                <div class="form-group col-sm-4">
                                    <label>TITULO DE GRADO </label><span class = "rojo"> * </span>
                                    <select class="form-control" id="gradoTI" style="width: 100%">
                                    </select>
                                </div>
                                <div class="form-group col-sm-4">
                                    <label>AUTOR/S </label><span class = "rojo"> * </span>
                                    <input class="form-control MAYUSCULAS" type="text"  id="autorTI" maxlength="45" autocomplete="off">
                                </div>
                            </div>

                            <div class="row">
                                <div class="form-group col-sm-4">
                                    <label>CARRERA </label><span class = "rojo"> * </span>
                                    <select class="form-control" id="carreraTI" style="width: 100%">

                                    </select>
                                </div>
                                <div class="form-group col-sm-4">
                                    <label>AÑO DE ELABORACIÓN </label><span class = "rojo"> * </span>
                                    <input class="form-control NUMEROS" type="text"  id="anhoTI" maxlength="4" autocomplete="off">
                                </div>
                                <div class="form-group col-sm-4">
                                    <label>ESTADO </label><span class = "rojo"> * </span>
                                    <select class="form-control" id="estadoTI" style="width: 100%">

                                    </select>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group col-sm-4">
                                    <label>FECHA INGRESO </label><span class = "rojo"> * </span>
                                    <input class="form-control" type="date"  id="fechaIngresoTI">
                                </div>
                                <div class="form-group col-sm-4">
                                    <label>NÚMERO DE ENTRADA </label><span class = "rojo"> * </span>
                                    <input class="form-control NUMEROS" type="text"  id="numeroEntradaTI" maxlength="7" autocomplete="off">
                                </div>
                                <div class="form-group col-sm-4">
                                    <label>TIPO DE TRABAJO </label><span class = "rojo"> * </span>
                                    <select class="form-control" id="tipoTI" style="width: 100%">

                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                        <button type="button" class="btn btn-primary" id="agregardatosTI" >Guardar Datos</button>
                    </div>
                </div>
            </div>
        </div>

        <div id="ModificarTI" class="modal fade " role="dialog">
            <div class="modal-dialog modal-lg"> 

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Modificar Trabajo de Investigación</h4>
                    </div>
                    <div class="modal-body">
                        <div class="container-fluid">
                            <div class="row">
                                <div class="form-group col-sm-4">
                                    <label>TITULO-DESCRIPCIÓN </label> <span class = "rojo"> * </span>
                                    <input class="form-control MAYUSCULAS" type="text" style="width: 100%" id="EtituloTI" maxlength="100" autocomplete="off">
                                </div>
                                <div class="form-group col-sm-4">
                                    <label>TITULO DE GRADO </label><span class = "rojo"> * </span>
                                    <select class="form-control" id="EgradoTI" style="width: 100%">
                                    </select>
                                </div>
                                <div class="form-group col-sm-4">
                                    <label>AUTOR/S </label><span class = "rojo"> * </span>
                                    <input class="form-control MAYUSCULAS" type="text"  id="EautorTI" maxlength="45" autocomplete="off">
                                </div>
                            </div>

                            <div class="row">
                                <div class="form-group col-sm-4">
                                    <label>CARRERA </label><span class = "rojo"> * </span>
                                    <select class="form-control" id="EcarreraTI" style="width: 100%">
                                        <option value="1">1</option>
                                        <option value="2">2</option>
                                        <option>3</option>
                                        <option>4</option>
                                        <option>5</option>
                                    </select>
                                </div>
                                <div class="form-group col-sm-4">
                                    <label>AÑO DE ELABORACIÓN </label><span class = "rojo"> * </span>
                                    <input class="form-control NUMEROS" type="text"  id="EanhoTI" maxlength="4" autocomplete="off">
                                </div>
                                <div class="form-group col-sm-4">
                                    <label>ESTADO </label><span class = "rojo"> * </span>
                                    <select class="form-control" id="EestadoTI" style="width: 100%">
                                    </select>
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group col-sm-4">
                                    <label>FECHA INGRESO </label><span class = "rojo"> * </span>
                                    <input class="form-control" type="date"  id="EfechaIngresoTI">
                                </div>
                                <div class="form-group col-sm-4">
                                    <label>NÚMERO DE ENTRADA </label><span class = "rojo"> * </span>
                                    <input class="form-control NUMEROS" type="text"  id="EnumeroEntradaTI" maxlength="7" autocomplete="off" disabled>
                                </div>
                                <div class="form-group col-sm-4">
                                    <label>TIPO DE TRABAJO </label><span class = "rojo"> * </span>
                                    <select class="form-control" id="EtipoTI" style="width: 100%">

                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                        <button type="button" class="btn btn-primary" id="actualizarDatosTI" >Guardar Datos</button>
                    </div>
                </div>
            </div>
        </div>

        <!-- MODAL DE REVISTAS -->

        <div id="agregarRevista" class="modal fade " role="dialog">
            <div class="modal-dialog modal-lg"> 

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Agregar Revista/Obras de Soporte Alternativo</h4>
                    </div>
                    <div class="modal-body">
                        <div class="container-fluid">
                            <div class="row">
                                <div class="form-group col-sm-4">
                                    <label>ISSN</label><span class = "rojo"></span>
                                    <input class="form-control MAYUSCULAS" type="text" id="issn" autocomplete="off" maxlength="45" >
                                </div>
                                <div class="form-group col-sm-4">
                                    <label>TITULO-DESCRIPCIÓN </label><span class = "rojo"> * </span>
                                    <input class="form-control MAYUSCULAS" type="text"  id="tituloR" autocomplete="off" maxlength="100" >
                                </div>
                                <div class="form-group col-sm-4">
                                    <label>AÑO DE PUBLICACIÓN </label><span class = "rojo"> * </span>
                                    <input class="form-control NUMEROS" type="text" id="anhoR" autocomplete="off" maxlength="4" >
                                </div>
                            </div>

                            <div class="row">
                                <div class="form-group col-sm-3">
                                    <label>NÚMERO DE ENTRADA </label><span class = "rojo"> * </span>
                                    <input class="form-control NUMEROS" type="text"  id="numeroEntradaR" autocomplete="off" maxlength="7" >
                                </div>
                                <div class="form-group col-sm-3">
                                    <label>ESTADO </label> <span class = "rojo"> * </span>
                                    <select class="form-control" id="estadoR" style="width: 100%" >
                                    </select>
                                </div>
                                <div class="form-group col-sm-3">
                                    <label>FECHA INGRESO </label><span class = "rojo"> * </span>
                                    <input class="form-control" type="date"  id="fechaIngresoR">
                                </div>
                                <div class="form-group col-sm-3">
                                    <label>TIPO DE MATERIAL </label><span class = "rojo"> * </span>
                                    <select class="form-control" id="tipoR" style="width: 100%">

                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                        <button type="button" class="btn btn-primary" id="agregarDatosRevista" >Guardar Datos</button>
                    </div>
                </div>
            </div>
        </div>

        <div id="ModificarR" class="modal fade " role="dialog">
            <div class="modal-dialog modal-lg"> 

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Agregar Revista/Obras de Soporte Alternativo</h4>
                    </div>
                    <div class="modal-body">
                        <div class="container-fluid">
                            <div class="row">
                                <div class="form-group col-sm-4">
                                    <label>ISSN</label><span class = "rojo"></span>
                                    <input class="form-control MAYUSCULAS" type="text" id="Eissn" autocomplete="off" maxlength="45" >
                                </div>
                                <div class="form-group col-sm-4">
                                    <label>TITULO </label><span class = "rojo"> * </span>
                                    <input class="form-control MAYUSCULAS" type="text"  id="EtituloR" autocomplete="off" maxlength="100" >
                                </div>
                                <div class="form-group col-sm-4">
                                    <label>AÑO DE PUBLICACIÓN </label><span class = "rojo"> * </span>
                                    <input class="form-control NUMEROS" type="text" id="EanhoR" autocomplete="off" maxlength="4" >
                                </div>
                            </div>

                            <div class="row">
                                <div class="form-group col-sm-3">
                                    <label>NÚMERO DE ENTRADA </label><span class = "rojo"> * </span>
                                    <input class="form-control NUMEROS" type="text"  id="EnumeroEntradaR" autocomplete="off" maxlength="7" disabled >
                                </div>
                                <div class="form-group col-sm-3">
                                    <label>ESTADO </label><span class = "rojo"> * </span>
                                    <select class="form-control" id="EestadoR" style="width: 100%">
                                    </select>
                                </div>
                                <div class="form-group col-sm-3">
                                    <label>FECHA INGRESO </label><span class = "rojo"> * </span>
                                    <input class="form-control" type="date"  id="EfechaIngresoR">
                                </div>
                                <div class="form-group col-sm-3">
                                    <label>TIPO DE MATERIAL </label><span class = "rojo"> * </span>
                                    <select class="form-control" id="EtipoR" style="width: 100%">

                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                        <button type="button" class="btn btn-primary" id="modificarDatosRevista" >Guardar Datos</button>
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

        <script src="./consultas/libros.js"></script> 

    </body>
</html>
<%}%>