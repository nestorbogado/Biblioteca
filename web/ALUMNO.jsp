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

        <title>AUTORES</title>

        <!-- Bootstrap Core CSS -->
        <link href="./vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <!-- MetisMenu CSS -->
        <link href="./vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

        <!-- DataTables CSS -->
        <link href="./vendor/datatables-plugins/dataTables.bootstrap.css" rel="stylesheet">

        <!-- DataTables Responsive CSS -->
        <link href="./vendor/datatables-responsive/dataTables.responsive.css" rel="stylesheet">

        <!-- Custom CSS -->
        <link href="./dist/css/sb-admin-2.css" rel="stylesheet">

        <!-- Custom Fonts -->
        <link href="./vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link href="./css/select2.css" rel="stylesheet" type="text/css">
        <link href="./css/sweetalert2.css" rel="stylesheet" type="text/css">
    </head>

    <body>
        <div id="container"> 

            <!-- Navigation -->
            <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse"> <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
                    <a class="navbar-brand" href="index.html">Biblioteca</a> </div>
                <!-- /.navbar-header -->

                <ul class="nav navbar-top-links navbar-right">
                    <!-- /.dropdown -->
                    <li class="dropdown"> <a class="dropdown-toggle" data-toggle="dropdown" href="#"> <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i> </a>
                        <ul class="dropdown-menu dropdown-user">
                            <li><a href="index.jsp"><i class="fa fa-sign-out fa-fw"></i> SALIR</a> </li>
                        </ul>
                        <!-- /.dropdown-user --> 
                    </li>
                    <!-- /.dropdown -->
                </ul>
                <!-- /.navbar-top-links -->

                <div class="navbar-default sidebar" role="navigation">
                    <div class="sidebar-nav navbar-collapse">
                        <ul class="nav" id="side-menu">
                            <li> <a href="LIBROS.jsp"><i class="fa fa-book"></i> Libros</a> </li>
                            <li> <a href="PRESTAMO.jsp"><i class="fa fa-book"></i> Prestamos</a> </li>
                            <li> <a href="EDITORIAL.jsp"><i class="fa fa-book"></i> Editoriales</a> </li>
                            <li> <a href="AUTOR.jsp"><i class="fa fa-book"></i> Autores</a> </li>
                            <li> <a href="GENERO.jsp"><i class="fa fa-book"></i> Generos</a> </li>
                            <li> <a href="CARRERA.jsp"><i class="fa fa-book"></i> Carreras</a> </li>
                            <li> <a href="ALUMNO.jsp"><i class="fa fa-book"></i> Alumnos</a> </li>
                            <li> <a href="REPORTES.jsp"><i class="fa fa-book"></i>Reportes</a> </li>
                            <li> <a href="CATEGORIA.jsp"><i class="fa fa-book"></i>Categoria</a> </li>
                            
                        </ul>
                    </div>
                    <!-- /.sidebar-collapse --> 
                </div>
                <!-- /.navbar-static-side --> 
            </nav>
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
                                            <th>C.I.</th>
                                            <th>ALUMNO</th>
                                            <th>CARRERA</th>
                                            <th>EDITAR </th>
                                            <th>ELIMINAR </th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <td>1</td>
                                    <td>ASDAS</td>
                                    <td><button class="btn btn-success" data-toggle="modal" data-target="#editar" data-backdrop="static" data-keyboard="false"><i class="glyphicon glyphicon-edit" ></i></button></td>
                                    <td><button class="btn btn-danger"><i class="glyphicon glyphicon-remove"></i></button></td>
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
                                    <label>Nombre:</label>
                                    <input class="form-control" type="text"  id="nombre">
                                </div>
                                <div class="form-group col-sm-6">
                                    <label>Apellido:</label>
                                    <input class="form-control" type="text"  id="apellido">
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group col-sm-6">
                                    <label>C.I.:</label>
                                    <input class="form-control" type="text"  id="ci">
                                </div>
                                <div class="form-group col-sm-6">
                                    <label>Carrera:</label>
                                    <select class="form-control" id="carrera" style="width: 100%">
                                        
                                    </select>
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
                                    <label>Nombre:</label>
                                    <input class="form-control" type="text"  id="Enombre">
                                </div>
                                <div class="form-group col-sm-6">
                                    <label>Apellido:</label>
                                    <input class="form-control" type="text"  id="Eapellido">
                                </div>
                            </div>
                            <div class="row">
                                <div class="form-group col-sm-6">
                                    <label>C.I.:</label>
                                    <input class="form-control" type="text"  id="Eci">
                                </div>
                                <div class="form-group col-sm-6">
                                    <label>Carrera:</label>
                                    <select class="form-control" id="Ecarrera" style="width: 100%">
                                        
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
        <script src="./dist/js/sb-admin-2.js"></script> 

        <script src="./consultas/alumno.js"></script> 

    </body>
</html>
<%}%>