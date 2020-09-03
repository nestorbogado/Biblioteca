<%-- 
    Document   : BARRANAV
    Created on : 18/05/2019, 07:20:11 PM
    Author     : USUARIO
--%>
<%
    int tipo = (Integer) session.getAttribute("tipo");
%>
<!-- Navigation -->
<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse"> <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
        <div class="col-lg-3">
            <img src="imagen/logo2.png" width="150" height="45">
        </div>
    </div>
    <!-- /.navbar-header -->

    <ul class="nav navbar-top-links navbar-right">
        <!-- /.dropdown -->
        <li class="dropdown"> <a class="dropdown-toggle" data-toggle="dropdown" href="#"> <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i> </a>
            <ul class="dropdown-menu dropdown-user">
                <%if (tipo == 1) {%>
                <li><a href="#"> <strong><%=session.getAttribute("nombre")%></strong><p>Administrador/a</p></a> </li>
                <%}%>
                <%if (tipo == 2) {%>
                <li><a href="#"> <strong><%=session.getAttribute("nombre")%></strong><p>Encargado/a</p></a> </li>
                <%}%>
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
                <li> <a href="PRESTAMO.jsp"><i class="fa fa-share-alt"></i> Prestamos</a> </li>
                 <li> <a href="DEVOLUCION.jsp"><i class="fa fa-download"></i> Devolución</a> </li>
                <li>
                    <a href="#"><i class="fa fa-edit fa-fw"></i> Materiales<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">

                        <li> <a href="MATERIAL.jsp"><i class="fa fa-book"></i> Materiales</a> </li>


                        <li> <a href="EDITORIAL.jsp"><i class="glyphicon glyphicon-tag"></i> Editoriales</a> </li>

                        <li> <a href="AUTOR.jsp"><i class="glyphicon glyphicon-edit"></i> Autores</a> </li>

                        <li> <a href="GENERO.jsp"><i class="fa fa-stack-overflow"></i> Géneros</a> </li>

                        <li> <a href="CATEGORIA.jsp"><i class="glyphicon glyphicon-pushpin  "></i> Tipo de Material</a> </li>

                    </ul>
                    <!-- /.nav-second-level -->
                </li>

                 
                <li>
                    <a href="#"><i class="glyphicon glyphicon-bookmark"></i> Carreras<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">

                        <li> <a href="CARRERA.jsp"><i class="glyphicon glyphicon-bookmark"></i> Carrera</a> </li>


                        <li> <a href="FACULTAD.jsp"><i class="glyphicon glyphicon-inbox"></i> Facultad</a> </li>

                    </ul>
                    <!-- /.nav-second-level -->
                </li>
                
                
                
                <li>
                    <a href="#"><i class="glyphicon glyphicon-user"></i> Alumnos<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">

                        <li> <a href="ALUMNO.jsp"><i class="glyphicon glyphicon-user"></i> Alumnos</a> </li>


                        <li> <a href="TIPODOC.jsp"><i class="glyphicon glyphicon-tag"></i> Tipo de Documento</a> </li>

                    </ul>
                    <!-- /.nav-second-level -->
                </li>
                <li> <a href="REPORTES.jsp"><i class="glyphicon glyphicon-file"></i>Reportes</a> </li>
                    <%if (tipo == 1) {%>
                <li> <a href="USUARIOS.jsp"><i class="glyphicon glyphicon-user"></i>Usuarios</a> </li>
                    <%}%>
            </ul>
        </div>
        <!-- /.sidebar-collapse --> 
    </div>
    <!-- /.navbar-static-side --> 
</nav>
