$(document).ready(function () {
//Selecciona los select de la pagina LIBROS.JSP en inicializa con la libreria select2.full.js


    (function cargar() {
        $('#estado').load("MaterialControlador", {accion: "cargarEstado"});
        $('#carrera').load("MaterialControlador", {accion: "cargarCarrera"});
        $('#genero').load("MaterialControlador", {accion: "cargarGenero"});
        $('#autor').load("MaterialControlador", {accion: "cargarAutor"});
        $('#editorial').load("MaterialControlador", {accion: "cargarEditorial"});
        $('#tipoTI').load("MaterialControlador", {accion: "cargarTipoMaterial"});
        $('#carreraTI').load("MaterialControlador", {accion: "cargarCarrera"});
        $('#gradoTI').load("MaterialControlador", {accion: "cargarTituloGrado"});
        $('#estadoTI').load("MaterialControlador", {accion: "cargarEstado"});
        $('#estadoR').load("MaterialControlador", {accion: "cargarEstado"});
        $('#tipoR').load("MaterialControlador", {accion: "cargarTipoMaterial"});
    })();
    $('#editorial').select2();
    $('#Eeditorial').select2();
    $('#autor').select2();
    $('#Eautor').select2();
    $('#genero').select2();
    $('#Egenero').select2();
    $('#categoria').select2();
    $('#Ecategoria').select2();
    $('#carrera').select2();
    $('#Ecarrera').select2();
    $('#carreraTI').select2();
    $('#EcarreraTI').select2();
    $('#tipoTI').select2();
    $('#EtipoTI').select2();
    $('#tipoR').select2();
    $('#EtipoR').select2();

    /*====================================================================
     //INICIALIZA LA TABLA DE LIBROS CON LA LIBRERIA dataTable.js y a su vez 
     carga los datos que recibe del Servlet LibroTablaControlador
     //====================================================================
     */
    $('#TablaLibros').DataTable({
//        columns: [
//            {"width": "20%"},
//            {"width": "20%"},
//            null,
//            null,
//            null,
//            null,
//            null,
//            null,
//            null
//        ],
        columnDefs: [
            {className: "dt-center", targets: "_all"},
            { width: 50, targets: 0 }
        ],
        ajax: {
            method: "POST",
            url: "MaterialControlador?accion=cargarTabla",
            dataSrc: "datos"
        },
        columns: [
            // Debe ser la misma cantidad de datos con la cantidad de columnas de la tabla
            //data: nombre del dato que recibe del Servlet
            // ISBM|TITULO|AÑO|ESTADO|EDITORIAL|GENERO|AUTOR|EDITAR|ELIMINAR
            {data: "ISBN"},
            {data: "ISSN"},
            {data: "titulo"},
            {data: "año_publicacion"},
            {data: "estado"},
            {data: "numero_entrada"},
            {data: "tipo_material"},
            {data: "accion"}
        ]
    });
    /*====================================================================
     //FUNCION QUE SE EJECUTA AL HACER CLICK EN EL BOTON GUARDAR DATOS DEL MODAL
     REGISTRAR LIBROS QUE TIENE COMO ID agregarLibro
     //====================================================================
     
     */

    $("#agregarLibro").click(function () {
//        alert($("#genero").val());
        /*        if (validarFomularioLibroAgregar()) {
         $.ajax({
         type: "POST",
         url: "MaterialControlador",
         data: {
         
         accion: "verificar",
         numero: $("#numeroEntrada").val()},
         async: true,
         cache: false,
         success: function (resp) {
         //                alert(resp);
         if (resp == 1) {
         $("#numeroEntrada").focus();
         swal({
         position: 'center',
         type: 'warning',
         title: 'El Número de entrada ya existe!',
         showConfirmButton: false,
         timer: 1500
         });
         
         }*/  if (validarFomularioLibroAgregar()) {
            $.ajax({
                type: "POST",
                url: "MaterialControlador",
                data: {

                    accion: "insertarL",
                    // Estructura-> nombre de variable: valor de variable
                    //guarda en las variables los datos de los select y los inputs
                    isbn: $("#isbn").val(),
                    anho: $("#anho").val(),
                    titulo: $("#titulo").val(),
                    dewey: $("#dewey").val(),
                    editorial: $("#editorial").val(),
                    numeroEntrada: $("#numeroEntrada").val(),
                    estado: $("#estado").val(),
                    fechaIngreso: $("#fechaIngreso").val(),
                    autor: $("#autor").val().toString(),
                    carrera: $("#carrera").val().toString(),
                    genero: $("#genero").val().toString()},
                async: true,
                cache: false,
                success: function (resp) {
//                alert(resp);
                    if (resp == 1) {
                        swal({
                            position: 'center',
                            type: 'success',
                            title: 'Libro Agregado!',
                            showConfirmButton: false,
                            timer: 1500
                        });
                        $("#isbn").val("");
                        $("#anho").val("");
                        $("#titulo").val("");
                        $("#dewey").val("");
                        $("#numeroEntrada").val("");
                        $("#fechaIngreso").val("");
                        $('#estado').load("MaterialControlador", {accion: "cargarEstado"});
                        $('#carrera').load("MaterialControlador", {accion: "cargarCarrera"});
                        $('#genero').load("MaterialControlador", {accion: "cargarGenero"});
                        $('#autor').load("MaterialControlador", {accion: "cargarAutor"});
                        $('#editorial').load("MaterialControlador", {accion: "cargarEditorial"});
                        //Recarga la tabla
                        $('#TablaLibros').DataTable().ajax.reload();
                    } else {
                        swal({
                            position: 'center',
                            type: 'error',
                            title: 'No se pudo agregar!',
                            showConfirmButton: false,
                            timer: 1500
                        });
                    }

                }

            });
        }

    });
    /* =======================================================================
     *  ELIMINAR LIBRO
     * =======================================================================
     */

    $(document).on('click', '.eliminarL', function () {
        var id = $(this).attr("value");
//        alert(id);
        swal({
            title: "Está seguro que desea eliminar ?",
            type: "warning",
            showCancelButton: true,
            confirmButtonClass: "btn-danger",
            confirmButtonText: "Si, Eliminar!",
            cancelButtonText: "No, Cancelar",
            closeOnConfirm: false
        },
                function () {
                    $.getJSON('MaterialControlador', {accion: "eliminarLibro", id: id}, function (res) {
                        if (res == 1) {
                            swal({
                                position: 'center',
                                type: 'success',
                                title: 'Libro Eliminado!',
                                showConfirmButton: false,
                                timer: 1500
                            });
                            //Recarga la tabla
                            $('#TablaLibros').DataTable().ajax.reload();
                        } else {
                            swal({
                                position: 'center',
                                type: 'error',
                                title: 'No se pudo eliminar!',
                                showConfirmButton: false,
                                timer: 1500
                            });
                        }
//                console.log(res);

                    });
                });
    });
    /* =======================================================================
     *  CARGAR DATOS DEL LIBRO
     * =======================================================================
     */
    $(document).on('click', '.actualizarL', function () {

        var id = $(this).attr("value");
//        alert(id);
        $.ajax({
            type: "POST",
            url: "MaterialControlador",
            data: {

                accion: "cargarDatosLibro",
                // Estructura-> nombre de variable: valor de variable
                //guarda en las variables los datos de los select y los inputs
                id: id},
            async: true,
            cache: false,
            dataType: "JSON",
            success: function (res) {
//                alert(res);
                // en resp se guarda lo que el servlet retornó, en este caso retorna true o false
                $("#modificarLibro").val(res.id_material);
                $("#Eisbn").val(res.ISBN);
                $("#Eanho").val(res.año_publicacion);
                $("#Etitulo").val(res.titulo);
                $("#Edewey").val(res.numeracion_dewey);
                $("#Eeditorial").html(res.editoriales);
                $("#EnumeroEntrada").val(res.numero_entrada);
                $("#Eestado").html(res.estado);
                $("#EfechaIngreso").val(res.fecha_ingreso);
                $("#Eautor").html(res.autor);
                $("#Ecarrera").html(res.carrera);
                $("#Egenero").html(res.genero);
            }

        });
    });
    /* =======================================================================
     *  CARGAR DATOS DEL LIBRO PARA DUPLICAR
     * =======================================================================
     */
    $(document).on('click', '.duplicarL', function () {

        var id = $(this).attr("value");
//        alert(id);
        $.ajax({
            type: "POST",
            url: "MaterialControlador",
            data: {

                accion: "cargarDatosLibro",
                // Estructura-> nombre de variable: valor de variable
                //guarda en las variables los datos de los select y los inputs
                id: id},
            async: true,
            cache: false,
            dataType: "JSON",
            success: function (res) {
//                alert(res);
                // en resp se guarda lo que el servlet retornó, en este caso retorna true o false
                $("#isbn").val(res.ISBN);
                $("#anho").val(res.año_publicacion);
                $("#titulo").val(res.titulo);
                $("#dewey").val(res.numeracion_dewey);
                $("#editorial").html(res.editoriales);
                $("#numeroEntrada").val(res.numero_entrada);
                $("#estado").html("<option value='Permanente'>Permanente</option> <option  selected value='Disponible'>Disponible</option> <option value='No Disponible'>No Disponible</option>");
                $("#fechaIngreso").val(res.fecha_ingreso);
                $("#autor").html(res.autor);
                $("#carrera").html(res.carrera);
                $("#genero").html(res.genero);
            }

        });
    });
    /* =======================================================================
     *  ACTUALIZAR DATOS DEL LIBRO
     * =======================================================================
     */

    $("#modificarLibro").click(function () {
//        alert($("#modificarLibro").val());
        if (validarFomularioLibroModificar()) {
            $.ajax({
                type: "POST",
                url: "MaterialControlador",
                data: {
                    accion: "modificar",
                    id: $("#modificarLibro").val(),
                    isbn: $("#Eisbn").val(),
                    anho: $("#Eanho").val(),
                    titulo: $("#Etitulo").val(),
                    dewey: $("#Edewey").val(),
                    editorial: $("#Eeditorial").val(),
                    numeroEntrada: $("#EnumeroEntrada").val(),
                    estado: $("#Eestado").val(),
                    fechaIngreso: $("#EfechaIngreso").val(),
                    autor: $("#Eautor").val().toString(),
                    carrera: $("#Ecarrera").val().toString(),
                    genero: $("#Egenero").val().toString()},

                async: true,
                cache: false,
                success: function (resp) {

                    if (resp == 1) {
                        swal({
                            position: 'center',
                            type: 'success',
                            title: 'Se han actualizado los datos!',
                            showConfirmButton: false,
                            timer: 1500
                        });
                        //Recarga la tabla
                        $('#TablaLibros').DataTable().ajax.reload();
                    } else {
                        swal({
                            position: 'center',
                            type: 'error',
                            title: 'No se pudo actualizar!',
                            showConfirmButton: false,
                            timer: 1500
                        });
                    }

                }

            });
        }
    });

    //VALIDACION 
    function validarFomularioLibroAgregar() {
        if (($("#isbn").val().length > 4)) {
            if (($("#anho").val().length > 3)) {
                if (($("#titulo").val().length > 4)) {
                    if (($("#dewey").val().length > 0)) {
                        if ($("#editorial").val().trim() !== "") {
//                            if (($("#numeroEntrada").val().length > 0)) {
                            if (($("#fechaIngreso").val().length > 0)) {
                                if ($("#autor").val().length > 0) {
                                    if ($("#carrera").val().length > 0) {
                                        if ($("#genero").val().length > 0) {

                                            return true;

                                        } else {

                                            $("#genero").focus();
                                            swal({
                                                position: 'center',
                                                type: 'warning',
                                                title: 'Complete los campos requeridos!',
                                                showConfirmButton: false,
                                                timer: 1500
                                            })

                                            return false;
                                        }
                                    } else {
                                        $("#carrera").focus();
                                        swal({
                                            position: 'center',
                                            type: 'warning',
                                            title: 'Complete los campos requeridos!',
                                            showConfirmButton: false,
                                            timer: 1500
                                        })

                                        return false;
                                    }
                                } else {
                                    $("#autor").focus();
                                    swal({
                                        position: 'center',
                                        type: 'warning',
                                        title: 'Complete los campos requeridos!',
                                        showConfirmButton: false,
                                        timer: 1500
                                    })

                                    return false;
                                }
                            } else {

                                $("#fechaIngreso").focus();
                                swal({
                                    position: 'center',
                                    type: 'warning',
                                    title: 'Complete los campos requeridos!',
                                    showConfirmButton: false,
                                    timer: 1500
                                })

                                return false;
                            }
//                            } else {
//                                $("#numeroEntrada").focus();
//                                swal({
//                                    position: 'center',
//                                    type: 'warning',
//                                    title: 'Complete los campos requeridos!',
//                                    showConfirmButton: false,
//                                    timer: 1500
//                                })
//
//                                return false;
//                            }
                        } else {
                            $("#editorial").focus();
                            swal({
                                position: 'center',
                                type: 'warning',
                                title: 'Complete los campos requeridos!',
                                showConfirmButton: false,
                                timer: 1500
                            })

                            return false;
                        }
                    } else {
                        $("#dewey").focus();
                        swal({
                            position: 'center',
                            type: 'warning',
                            title: 'Complete los campos requeridos!',
                            showConfirmButton: false,
                            timer: 1500
                        })

                        return false;
                    }
                } else {
                    $("#titulo").focus();
                    swal({
                        position: 'center',
                        type: 'warning',
                        title: 'Complete los campos requeridos!',
                        showConfirmButton: false,
                        timer: 1500
                    })

                    return false;
                }

            } else {
                $("#anho").focus();
                swal({
                    position: 'center',
                    type: 'warning',
                    title: 'Complete los campos requeridos!',
                    showConfirmButton: false,
                    timer: 1500
                })

                return false;
            }
        } else {
            $("#isbn").focus();
            swal({
                position: 'center',
                type: 'warning',
                title: 'Complete los campos requeridos!',
                showConfirmButton: false,
                timer: 1500
            })
            return false;
        }
    }

    //VALIDACION 
    function validarFomularioLibroModificar() {
        // if (($("#Eisbn").val().length > 4)) {
        if (($("#Eanho").val().length > 3)) {
            if (($("#Etitulo").val().length > 4)) {
                if (($("#Edewey").val().length > 0)) {
                    if ($("#Eeditorial").val().trim() !== "") {
//                            if (($("#EnumeroEntrada").val().length > 0)) {
                        if (($("#EfechaIngreso").val().length > 0)) {
                            if ($("#Eautor").val().length > 0) {
                                if ($("#Ecarrera").val().length > 0) {
                                    if ($("#Egenero").val().length > 0) {

                                        return true;

                                    } else {

                                        $("#Egenero").focus();
                                        swal({
                                            position: 'center',
                                            type: 'warning',
                                            title: 'Complete los campos requeridos!',
                                            showConfirmButton: false,
                                            timer: 1500
                                        })

                                        return false;
                                    }
                                } else {
                                    $("#Ecarrera").focus();
                                    swal({
                                        position: 'center',
                                        type: 'warning',
                                        title: 'Complete los campos requeridos!',
                                        showConfirmButton: false,
                                        timer: 1500
                                    })

                                    return false;
                                }
                            } else {
                                $("#Eautor").focus();
                                swal({
                                    position: 'center',
                                    type: 'warning',
                                    title: 'Complete los campos requeridos!',
                                    showConfirmButton: false,
                                    timer: 1500
                                })

                                return false;
                            }
                        } else {

                            $("#EfechaIngreso").focus();
                            swal({
                                position: 'center',
                                type: 'warning',
                                title: 'Complete los campos requeridos!',
                                showConfirmButton: false,
                                timer: 1500
                            })

                            return false;
                        }
//                            } else {
//                                $("#EnumeroEntrada").focus();
//                                swal({
//                                    position: 'center',
//                                    type: 'warning',
//                                    title: 'Complete los campos requeridos!',
//                                    showConfirmButton: false,
//                                    timer: 1500
//                                })
//
//                                return false;
//                            }
                    } else {
                        $("#Eeditorial").focus();
                        swal({
                            position: 'center',
                            type: 'warning',
                            title: 'Complete los campos requeridos!',
                            showConfirmButton: false,
                            timer: 1500
                        })

                        return false;
                    }
                } else {
                    $("#Edewey").focus();
                    swal({
                        position: 'center',
                        type: 'warning',
                        title: 'Complete los campos requeridos!',
                        showConfirmButton: false,
                        timer: 1500
                    })

                    return false;
                }
            } else {
                $("#Etitulo").focus();
                swal({
                    position: 'center',
                    type: 'warning',
                    title: 'Complete los campos requeridos!',
                    showConfirmButton: false,
                    timer: 1500
                })

                return false;
            }

        } else {
            $("#Eanho").focus();
            swal({
                position: 'center',
                type: 'warning',
                title: 'Complete los campos requeridos!',
                showConfirmButton: false,
                timer: 1500
            })

            return false;
        }
//        } else {
//            $("#Eisbn").focus();
//            swal({
//                position: 'center',
//                type: 'warning',
//                title: 'Complete los campos requeridos!',
//                showConfirmButton: false,
//                timer: 1500
//            })
//            return false;
//        }
    }
    //************************************************************************************
    //
    //
    //
    //
    //************************************************************************************

    $("#agregardatosTI").click(function () {
//        alert($("#tituloTI").val());
        if (validarFomularioAgregarTI()) {
            $.ajax({
                type: "POST",
                url: "MaterialControlador",
                data: {

                    accion: "verificar",
                    numero: $("#numeroEntradaTI").val()},
                async: true,
                cache: false,
                success: function (resp) {
//                alert(resp);
                    if (resp == 1) {
                        $("#numeroEntradaTI").focus();
                        swal({
                            position: 'center',
                            type: 'warning',
                            title: 'El Número de entrada ya existe!',
                            showConfirmButton: false,
                            timer: 1500
                        });

                    } else {
                        $.ajax({
                            type: "POST",
                            url: "MaterialControlador",
                            data: {

                                accion: "insertarTI",
                                // Estructura-> nombre de variable: valor de variable
                                //guarda en las variables los datos de los select y los inputs
                                titulo: $("#tituloTI").val(),
                                gradoTI: $("#gradoTI").val(),
                                autor: $("#autorTI").val(),
                                carrera: $("#carreraTI").val(),
                                anhoTI: $("#anhoTI").val(),
                                estado: $("#estadoTI").val(),
                                fechaIngreso: $("#fechaIngresoTI").val(),
                                numeroEntrada: $("#numeroEntradaTI").val(),
                                tipoTI: $("#tipoTI").val()},
                            async: true,
                            cache: false,
                            success: function (resp) {
//                alert(resp);
                                if (resp == 1) {
                                    swal({
                                        position: 'center',
                                        type: 'success',
                                        title: 'Trabajo de Investigación Agregado!',
                                        showConfirmButton: false,
                                        timer: 1500
                                    });
                                    //Vacía el campo de texto
                                    $("#tituloTI").val("");
                                    $("#gradoTI").val();
                                    $("#autorTI").val("");
                                    $("#anhoTI").val("");
                                    $("#fechaIngresoTI").val("");
                                    $("#numeroEntradaTI").val("");
                                    $('#tipoTI').load("MaterialControlador", {accion: "cargarTipoMaterial"});
                                    $('#carreraTI').load("MaterialControlador", {accion: "cargarCarrera"});
                                    $('#gradoTI').load("MaterialControlador", {accion: "cargarTituloGrado"});
                                    $('#estadoTI').load("MaterialControlador", {accion: "cargarEstado"});

                                    $('#TablaLibros').DataTable().ajax.reload();
                                } else {
                                    swal({
                                        position: 'center',
                                        type: 'error',
                                        title: 'No se pudo agregar!',
                                        showConfirmButton: false,
                                        timer: 1500
                                    });
                                }
                            }

                        });
                    }
                }
            });

        }

    });
    $(document).on('click', '.actualizarTI', function () {

        var id = $(this).attr("value");
//        alert(id);
        $.ajax({
            type: "POST",
            url: "MaterialControlador",
            data: {

                accion: "cargarDatosTI",
                // Estructura-> nombre de variable: valor de variable
                //guarda en las variables los datos de los select y los inputs
                id: id},
            async: true,
            cache: false,
            dataType: "JSON",
            success: function (res) {
//                alert(res);
                // en resp se guarda lo que el servlet retornó, en este caso retorna true o false
                $("#actualizarDatosTI").val(res.id_material);
                $("#EtituloTI").val(res.titulo);
//                var op = "";
//                if (res.grado==="Grado") {
//                    op = op + "<option selected value='Grado'>Grado</option> <option value='Post Grado'>Post Grado</option>";
//                } else {
//                    op = op + "<option value='Grado'>Grado</option><option selected value='Post Grado'>Post Grado</option>";
//                }
                $("#EgradoTI").html(res.grado);
                $("#EautorTI").val(res.autores);
                $("#EcarreraTI").html(res.carrera);
                $("#EanhoTI").val(res.año_publicacion);
                $("#EestadoTI").html(res.estado);
                $("#EfechaIngresoTI").val(res.fecha_ingreso);
                $("#EnumeroEntradaTI").val(res.numero_entrada);
                $("#EtipoTI").html(res.tipo_material);
            }

        });
    });

    $(document).on('click', '.duplicarTI', function () {

        var id = $(this).attr("value");
//        alert(id);
        $.ajax({
            type: "POST",
            url: "MaterialControlador",
            data: {

                accion: "cargarDatosTI",
                // Estructura-> nombre de variable: valor de variable
                //guarda en las variables los datos de los select y los inputs
                id: id},
            async: true,
            cache: false,
            dataType: "JSON",
            success: function (res) {
//                alert(res);
                // en resp se guarda lo que el servlet retornó, en este caso retorna true o false
                $("#tituloTI").val(res.titulo);
//                var op = "";
//                if (res.grado==="Grado") {
//                    op = op + "<option selected value='Grado'>Grado</option> <option value='Post Grado'>Post Grado</option>";
//                } else {
//                    op = op + "<option value='Grado'>Grado</option><option selected value='Post Grado'>Post Grado</option>";
//                }
                $("#gradoTI").html(res.grado);
                $("#autorTI").val(res.autores);
                $("#carreraTI").html(res.carrera);
                $("#anhoTI").val(res.año_publicacion);
                $("#estadoTI").html("<option value='Permanente'>Permanente</option> <option  selected value='Disponible'>Disponible</option> <option value='No Disponible'>No Disponible</option>");
                $("#fechaIngresoTI").val(res.fecha_ingreso);
                $("#numeroEntradaTI").val(res.numero_entrada);
                $("#tipoTI").html(res.tipo_material);
            }

        });
    });

    $("#actualizarDatosTI").click(function () {
//        alert($("#actualizarDatosTI").val());
        if (validarFomularioModificarTI()) {
            $.ajax({
                type: "POST",
                url: "MaterialControlador",
                data: {

                    accion: "modificarTI",
                    // Estructura-> nombre de variable: valor de variable
                    //guarda en las variables los datos de los select y los inputs
                    id: $("#actualizarDatosTI").val(),
                    titulo: $("#EtituloTI").val(),
                    gradoTI: $("#EgradoTI").val(),
                    autor: $("#EautorTI").val(),
                    carrera: $("#EcarreraTI").val(),
                    anhoTI: $("#EanhoTI").val(),
                    estado: $("#EestadoTI").val(),
                    fechaIngreso: $("#EfechaIngresoTI").val(),
                    numeroEntrada: $("#EnumeroEntradaTI").val(),
                    tipoTI: $("#EtipoTI").val()},
                async: true,
                cache: false,
                success: function (resp) {
//                alert(resp);
                    // en resp se guarda lo que el servlet retornó, en este caso retorna true o false
                    if (resp == 1) {
                        swal({
                            position: 'center',
                            type: 'success',
                            title: 'Se han actualizado los datos!',
                            showConfirmButton: false,
                            timer: 1500
                        });
                        //Recarga la tabla
                        $('#TablaLibros').DataTable().ajax.reload();
                    } else {
                        swal({
                            position: 'center',
                            type: 'error',
                            title: 'No se pudo actualizar!',
                            showConfirmButton: false,
                            timer: 1500
                        });
                    }


                }

            });
        }
    });

    $(document).on('click', '.eliminarTI', function () {
        var id = $(this).attr("value");
//        alert(id);
        swal({
            title: "Está seguro que desea eliminar ?",
            type: "warning",
            showCancelButton: true,
            confirmButtonClass: "btn-danger",
            confirmButtonText: "Si, Eliminar!",
            cancelButtonText: "No, Cancelar",
            closeOnConfirm: false
        },
                function () {
                    $.getJSON('MaterialControlador', {accion: "eliminarTI", id: id}, function (res) {
                        if (res == 1) {
                            swal({
                                position: 'center',
                                type: 'success',
                                title: 'Trabajo de Investigación Eliminado!',
                                showConfirmButton: false,
                                timer: 1500
                            });
                            //Recarga la tabla
                            $('#TablaLibros').DataTable().ajax.reload();
                        } else {
                            swal({
                                position: 'center',
                                type: 'error',
                                title: 'No se pudo eliminar!',
                                showConfirmButton: false,
                                timer: 1500
                            });
                        }
//                console.log(res);

                    });
                });
    });

    function validarFomularioAgregarTI() {
        if ($("#tituloTI").val().length > 4) {
            if ($("#autorTI").val().length > 4) {
                if ($("#anhoTI").val().length > 3) {
                    if ($("#fechaIngresoTI").val().length > 0) {
                        if ($("#numeroEntradaTI").val().length > 0) {
                            return true;
                        } else {
                            $("#numeroEntradaTI").focus();
                            swal({
                                position: 'center',
                                type: 'warning',
                                title: 'Complete los campos requeridos!',
                                showConfirmButton: false,
                                timer: 1500
                            })
                            return false;
                        }
                    } else {
                        $("#fechaIngresoTI").focus();
                        swal({
                            position: 'center',
                            type: 'warning',
                            title: 'Complete los campos requeridos!',
                            showConfirmButton: false,
                            timer: 1500
                        })
                        return false;
                    }
                } else {
                    $("#anhoTI").focus();
                    swal({
                        position: 'center',
                        type: 'warning',
                        title: 'Complete los campos requeridos!',
                        showConfirmButton: false,
                        timer: 1500
                    })
                    return false;
                }
            } else {
                $("#autorTI").focus();
                swal({
                    position: 'center',
                    type: 'warning',
                    title: 'Complete los campos requeridos!',
                    showConfirmButton: false,
                    timer: 1500
                })
                return false;
            }
        } else {
            $("#tituloTI").focus();
            swal({
                position: 'center',
                type: 'warning',
                title: 'Complete los campos requeridos!',
                showConfirmButton: false,
                timer: 1500
            })
            return false;
        }
    }

    function validarFomularioModificarTI() {
        if ($("#EtituloTI").val().length > 4) {
            if ($("#EautorTI").val().length > 4) {
                if ($("#EanhoTI").val().length > 3) {
                    if ($("#EfechaIngresoTI").val().length > 0) {
                        if ($("#EnumeroEntradaTI").val().length > 0) {
                            return true;
                        } else {
                            $("#EnumeroEntradaTI").focus();
                            swal({
                                position: 'center',
                                type: 'warning',
                                title: 'Complete los campos requeridos!',
                                showConfirmButton: false,
                                timer: 1500
                            })
                            return false;
                        }
                    } else {
                        $("#EfechaIngresoTI").focus();
                        swal({
                            position: 'center',
                            type: 'warning',
                            title: 'Complete los campos requeridos!',
                            showConfirmButton: false,
                            timer: 1500
                        })
                        return false;
                    }
                } else {
                    $("#EanhoTI").focus();
                    swal({
                        position: 'center',
                        type: 'warning',
                        title: 'Complete los campos requeridos!',
                        showConfirmButton: false,
                        timer: 1500
                    })
                    return false;
                }
            } else {
                $("#EautorTI").focus();
                swal({
                    position: 'center',
                    type: 'warning',
                    title: 'Complete los campos requeridos!',
                    showConfirmButton: false,
                    timer: 1500
                })
                return false;
            }
        } else {
            $("#EtituloTI").focus();
            swal({
                position: 'center',
                type: 'warning',
                title: 'Complete los campos requeridos!',
                showConfirmButton: false,
                timer: 1500
            })
            return false;
        }
    }
    //************************************************************************************
    //
    //
    //
    //
    //************************************************************************************

    $("#agregarDatosRevista").click(function () {
//        alert($("#tituloR").val());
        if (validarFomularioAgregarR()) {

            $.ajax({
                type: "POST",
                url: "MaterialControlador",
                data: {

                    accion: "verificar",
                    numero: $("#numeroEntradaR").val()},
                async: true,
                cache: false,
                success: function (resp) {
//                alert(resp);
                    if (resp == 1) {
                        $("#numeroEntradaR").focus();
                        swal({
                            position: 'center',
                            type: 'warning',
                            title: 'El Número de entrada ya existe!',
                            showConfirmButton: false,
                            timer: 1500
                        });

                    } else {
                        $.ajax({
                            type: "POST",
                            url: "MaterialControlador",
                            data: {

                                accion: "insertarR",
                                // Estructura-> nombre de variable: valor de variable
                                //guarda en las variables los datos de los select y los inputs
                                ISSN: $("#issn").val(),
                                titulo: $("#tituloR").val(),
                                anhoR: $("#anhoR").val(),
                                estado: $("#estadoR").val(),
                                fechaIngreso: $("#fechaIngresoR").val(),
                                numeroEntrada: $("#numeroEntradaR").val(),
                                tipo: $("#tipoR").val()},
                            async: true,
                            cache: false,
                            success: function (resp) {
//                alert(resp);
                                if (resp == 1) {
                                    swal({
                                        position: 'center',
                                        type: 'success',
                                        title: 'Se han agregado los datos!',
                                        showConfirmButton: false,
                                        timer: 1500
                                    });
                                    $("#issn").val("");
                                    $("#tituloR").val("");
                                    $("#anhoR").val("");
                                    $("#fechaIngresoR").val("");
                                    $("#numeroEntradaR").val("");
                                    //Recarga la tabla
                                    $('#TablaLibros').DataTable().ajax.reload();
                                } else {
                                    swal({
                                        position: 'center',
                                        type: 'error',
                                        title: 'No se pudo agregar!',
                                        showConfirmButton: false,
                                        timer: 1500
                                    });
                                }


                            }

                        });
                    }
                }
            });

        }

    });
    $(document).on('click', '.actualizarR', function () {
        var id = $(this).attr("value");
//        alert(id);
        $.ajax({
            type: "POST",
            url: "MaterialControlador",
            data: {

                accion: "cargarDatosR",
                // Estructura-> nombre de variable: valor de variable
                //guarda en las variables los datos de los select y los inputs
                id: id},
            async: true,
            cache: false,
            dataType: "json",
            success: function (res) {
//                alert(res);
                // en resp se guarda lo que el servlet retornó, en este caso retorna true o false
                $("#modificarDatosRevista").val(res.id_material);
                $("#Eissn").val(res.ISSN);
                $("#EtituloR").val(res.titulo);
                $("#EanhoR").val(res.año_publicacion);
                $("#EestadoR").html(res.estado);
                $("#EfechaIngresoR").val(res.fecha_ingreso);
                $("#EnumeroEntradaR").val(res.numero_entrada);
                $("#EtipoR").html(res.tipo_material);
            }

        });
    });

    $(document).on('click', '.duplicarR', function () {
        var id = $(this).attr("value");
//        alert(id);
        $.ajax({
            type: "POST",
            url: "MaterialControlador",
            data: {

                accion: "cargarDatosR",
                // Estructura-> nombre de variable: valor de variable
                //guarda en las variables los datos de los select y los inputs
                id: id},
            async: true,
            cache: false,
            dataType: "json",
            success: function (res) {
//                alert(res);
                // en resp se guarda lo que el servlet retornó, en este caso retorna true o false
                $("#issn").val(res.ISSN);
                $("#tituloR").val(res.titulo);
                $("#anhoR").val(res.año_publicacion);
                $("#estadoR").html("<option value='Permanente'>Permanente</option> <option  selected value='Disponible'>Disponible</option> <option value='No Disponible'>No Disponible</option>");
                $("#fechaIngresoR").val(res.fecha_ingreso);
                $("#numeroEntradaR").val(res.numero_entrada);
                $("#tipoR").html(res.tipo_material);
            }

        });
    });
    $("#modificarDatosRevista").click(function () {
//        alert($("#modificarDatosRevista").val());
        if (validarFomularioModificarR()) {
            $.ajax({
                type: "POST",
                url: "MaterialControlador",
                data: {

                    accion: "modificarR",
                    // Estructura-> nombre de variable: valor de variable
                    //guarda en las variables los datos de los select y los inputs
                    id: $("#modificarDatosRevista").val(),
                    ISSN: $("#Eissn").val(),
                    titulo: $("#EtituloR").val(),
                    anhoR: $("#EanhoR").val(),
                    estado: $("#EestadoR").val(),
                    fechaIngreso: $("#EfechaIngresoR").val(),
                    numeroEntrada: $("#EnumeroEntradaR").val(),
                    tipo: $("#EtipoR").val()},
                async: true,
                cache: false,
                success: function (resp) {
//                alert(resp);
                    if (resp == 1) {
                        swal({
                            position: 'center',
                            type: 'success',
                            title: 'Se han actualizado los datos!',
                            showConfirmButton: false,
                            timer: 1500
                        });
                        //Recarga la tabla
                        $('#TablaLibros').DataTable().ajax.reload();
                    } else {
                        swal({
                            position: 'center',
                            type: 'error',
                            title: 'No se pudo actualizar!',
                            showConfirmButton: false,
                            timer: 1500
                        });
                    }


                }

            });
        }
    });

    $(document).on('click', '.eliminarR', function () {
        var id = $(this).attr("value");
//        alert(id);
        swal({
            title: "Está seguro que desea eliminar ?",
            type: "warning",
            showCancelButton: true,
            confirmButtonClass: "btn-danger",
            confirmButtonText: "Si, Eliminar!",
            cancelButtonText: "No, Cancelar",
            closeOnConfirm: false
        },
                function () {
                    $.getJSON('MaterialControlador', {accion: "eliminarR", id: id}, function (res) {
                        if (res == 1) {
                            swal({
                                position: 'center',
                                type: 'success',
                                title: 'Material Eliminado!',
                                showConfirmButton: false,
                                timer: 1500
                            });
                            //Recarga la tabla
                            $('#TablaLibros').DataTable().ajax.reload();
                        } else {
                            swal({
                                position: 'center',
                                type: 'error',
                                title: 'No se pudo eliminar!',
                                showConfirmButton: false,
                                timer: 1500
                            });
                        }
//                console.log(res);

                    });
                });
    });

    //VALIDACION 
    function validarFomularioAgregarR() {
        if ($("#tituloR").val().length > 4) {
            if ($("#anhoR").val().length > 3) {
                if ($("#numeroEntradaR").val().length > 0) {
                    if ($("#fechaIngresoR").val().length > 0) {
                        return true;
                    } else {
                        $("#fechaIngresoR").focus();
                        swal({
                            position: 'center',
                            type: 'warning',
                            title: 'Complete los campos requeridos!',
                            showConfirmButton: false,
                            timer: 1500
                        })
                        return false;
                    }
                } else {
                    $("#numeroEntradaR").focus();
                    swal({
                        position: 'center',
                        type: 'warning',
                        title: 'Complete los campos requeridos!',
                        showConfirmButton: false,
                        timer: 1500
                    })
                    return false;
                }
            } else {
                $("#anhoR").focus();
                swal({
                    position: 'center',
                    type: 'warning',
                    title: 'Complete los campos requeridos!',
                    showConfirmButton: false,
                    timer: 1500
                })
                return false;
            }
        } else {
            $("#tituloR").focus();
            swal({
                position: 'center',
                type: 'warning',
                title: 'Complete los campos requeridos!',
                showConfirmButton: false,
                timer: 1500
            })
            return false;
        }
    }

    function validarFomularioModificarR() {
        if ($("#EtituloR").val().length > 4) {
            if ($("#EanhoR").val().length > 3) {
                if ($("#EnumeroEntradaR").val().length > 0) {
                    if ($("#EfechaIngresoR").val().length > 0) {
                        return true;
                    } else {
                        $("#EfechaIngresoR").focus();
                        swal({
                            position: 'center',
                            type: 'warning',
                            title: 'Complete los campos requeridos!',
                            showConfirmButton: false,
                            timer: 1500
                        })
                        return false;
                    }
                } else {
                    $("#EnumeroEntradaR").focus();
                    swal({
                        position: 'center',
                        type: 'warning',
                        title: 'Complete los campos requeridos!',
                        showConfirmButton: false,
                        timer: 1500
                    })
                    return false;
                }
            } else {
                $("#EanhoR").focus();
                swal({
                    position: 'center',
                    type: 'warning',
                    title: 'Complete los campos requeridos!',
                    showConfirmButton: false,
                    timer: 1500
                })
                return false;
            }
        } else {
            $("#EtituloR").focus();
            swal({
                position: 'center',
                type: 'warning',
                title: 'Complete los campos requeridos!',
                showConfirmButton: false,
                timer: 1500
            })
            return false;
        }
    }


    $(document).on('click', '.BOTONAGREGAR', function () {
//        alert();
        var now = new Date();

        var day = ("0" + now.getDate()).slice(-2);
        var month = ("0" + (now.getMonth() + 1)).slice(-2);

        var today = now.getFullYear() + "-" + (month) + "-" + (day);
        $("#isbn").val("");
        $("#anho").val("");
        $("#titulo").val("");
        $("#dewey").val("");
        $("#numeroEntrada").val("");
        $("#fechaIngreso").val(today);
        $('#estado').load("MaterialControlador", {accion: "cargarEstado"});
        $('#carrera').load("MaterialControlador", {accion: "cargarCarrera"});
        $('#genero').load("MaterialControlador", {accion: "cargarGenero"});
        $('#autor').load("MaterialControlador", {accion: "cargarAutor"});
        $('#editorial').load("MaterialControlador", {accion: "cargarEditorial"});


        $("#tituloTI").val("");
        $("#gradoTI").val();
        $("#autorTI").val("");
        $("#anhoTI").val("");
        $("#fechaIngresoTI").val(today);
        $("#numeroEntradaTI").val("");
        $('#tipoTI').load("MaterialControlador", {accion: "cargarTipoMaterial"});
        $('#carreraTI').load("MaterialControlador", {accion: "cargarCarrera"});
        $('#gradoTI').load("MaterialControlador", {accion: "cargarTituloGrado"});
        $('#estadoTI').load("MaterialControlador", {accion: "cargarEstado"});


        $("#issn").val("");
        $("#tituloR").val("");
        $("#anhoR").val("");
        $("#fechaIngresoR").val(today);
        $("#numeroEntradaR").val("");
    });

});


