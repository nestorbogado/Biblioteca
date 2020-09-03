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

        <title>REPORTES</title>
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
                                            <td>Alumnos <br>
                                                Ordenados Alfabeticamente</td>
                                            <td><a href="Reportes/AlumnosOrdAlfa.jsp" target="_blank" class="btn btn-default"><i class="glyphicon glyphicon-print"></i></a></td>

                                        </tr>
                                        
                                        <tr>
                                            <td>Alumnos <br>
                                                Por Carreras, Ordenados Alfabeticamente</td>
                                            <td><a href="Reportes/AlumnosAlfaCarreras.jsp" target="_blank" class="btn btn-default"><i class="glyphicon glyphicon-print"></i></a></td>

                                        </tr>
                                        
                                        
                                        
                                        <tr>
                                            <td>Libros <br>
                                                Ordenados Alfabeticamente</td>
                                            <td><a href="Reportes/LibrosOrdAlfaCarreras.jsp" target="_blank" class="btn btn-default"><i class="glyphicon glyphicon-print"></i></a></td>

                                        </tr>
                                        
                                        
                                        <tr>
                                            <td>Libros <br>
                                                Por Carreras, Ordenados Alfabeticamente </td>
                                            <td><a href="Reportes/LibrosOrdAlfa.jsp" target="_blank" class="btn btn-default"><i class="glyphicon glyphicon-print"></i></a></td>

                                        </tr>
                                        
                                        
                                        <tr>
                                            <td>Prestamos <br>
                                                Según el Rango de Fecha</td>
                                            <td>
                                                <form  method="Post" action="ReportePrestamoEntreFecha">
                                                    <label>Fecha Inicio:</label><input type="date" id="fechaInicio" name="fechaInicio"><br>
                                                    <label>Fecha Fin:  </label><input type="date" id="fechaFin" name="fechaFin"><br>
                                                    <button type="submit" class="btn btn-default"><i class="glyphicon glyphicon-print"></i></button>
                                                </form>
                                            </td>

                                        </tr>
                                        <tr>
                                            <td>Prestamos <br>
                                                Pendientes</td>
                                            <td><a href="Reportes/PrestamoPendiente.jsp" target="_blank" class="btn btn-default"><i class="glyphicon glyphicon-print"></i></a></td>

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
        <script src="./js/js/sb-admin-2.js"></script> 
        <script>

        </script> 
    </body>
</html>