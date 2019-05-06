<%-- 
    Document   : REPORTES
    Created on : 28/11/2018, 10:34:31 AM
    Author     : USUARIO
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Reportes</title>

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
                        <h1 class="page-header">REPORTES</h1>
                    </div>
                    <!-- /.col-lg-12 --> 
                </div>
                <!-- /.row -->
                <div class="row">
                    <div class="col-lg-12">
                        <div class="panel panel-default">
                            <div class="panel-heading text-right">

                            </div>
                            <!-- /.panel-heading -->
                            <div class="panel-body">
                                <table style="width: 100%" class="table table-striped table-bordered table-hover" id="TablaAlumnos">
                                    <thead>
                                        <tr>
                                            <th>Reportes</th>
                                            <th>Imprimir</th>

                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>Reportes de Alumnos Ordenados Alfabeticamente</td>
                                            <td><a href="Reportes/AlumnosOrdAlfa.jsp" target="_blank" class="btn btn-default"><i class="glyphicon glyphicon-print"></i></a></td>

                                        </tr>
                                        <tr>
                                            <td>Reportes de Libros Según el Género y Autor</td>
                                            <td><a href="Reportes/LibrosGeneroAutor.jsp" target="_blank" class="btn btn-default"><i class="glyphicon glyphicon-print"></i></a></td>

                                        </tr>
                                        <tr>
                                            <td>Reportes de Libros Ordenados Alfabeticamente</td>
                                            <td><a href="Reportes/LibrosOrdAlfa.jsp" target="_blank" class="btn btn-default"><i class="glyphicon glyphicon-print"></i></a></td>

                                        </tr>
                                        <tr>
                                            <td>Reportes de Prestamos según el rango de fecha</td>
                                            <td>
                                                <form  method="Post" action="ReportePrestamoEntreFecha">
                                                    <label>Fecha Inicio:</label><input type="date" id="fechaInicio" name="fechaInicio"><br>
                                                    <label>Fecha Fin:  </label><input type="date" id="fechaFin" name="fechaFin"><br>
                                                    <button type="submit" class="btn btn-default"><i class="glyphicon glyphicon-print"></i></button>
                                                </form>
                                            </td>

                                        </tr>
                                        <tr>
                                            <td>Reportes de Prestamos Pendientes</td>
                                            <td><a href="Reportes/PrestamoPendiente.jsp" target="_blank" class="btn btn-default"><i class="glyphicon glyphicon-print"></i></a></td>

                                        </tr>
                                        <tr>
                                            <td>Reportes de Libros según la Categoria</td>
                                            <td><a href="Reportes/LibrosCategoria.jsp" target="_blank" class="btn btn-default"><i class="glyphicon glyphicon-print"></i></a></td>

                                        </tr>
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
        <script>

        </script> 
    </body>
</html>
