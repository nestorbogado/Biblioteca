$(document).ready(function () {
    //Selecciona los select de la pagina LIBROS.JSP en inicializa con la libreria select2.full.js
////
    $('#material').select2();
    $('#alumnos').select2();

    (function cargar() {
        $('#alumnos').load("DevolucionControlador", {accion: "cargarAlumnos"});
        $('#material').load("DevolucionControlador", {accion: "cargarMaterial"});
    })();

    $('#TablaDevolucion').DataTable({
        columnDefs: [
            {className: "dt-center", targets: "_all"}
        ],
        ajax: {
            method: "POST",
            url: "DevolucionControlador?accion=cargarTablaDevolucionPENDIENTE",
            dataSrc: "datos"
        },
        columns: [
            {data: "titulo"},
            {data: "alumno"},
            {data: "fecha_p"},
            {data: "fecha_d"},
            {data: "editar"},
            {data: "devolver"}
        ]
    });

    $("#accion").change(function () {

        var accion = $('#accion').val();
        if (accion == 1) {
            //destruye la tabla
            $("#TablaDevolucion").dataTable().fnDestroy();

            $('#TablaDevolucion').DataTable({
                columnDefs: [
                    {className: "dt-center", targets: "_all"}
                ],
                ajax: {
                    method: "POST",
                    url: "DevolucionControlador?accion=cargarTablaDevolucionPENDIENTE",
                    dataSrc: "datos"
                },
                columns: [
                    {data: "titulo"},
                    {data: "alumno"},
                    {data: "fecha_p"},
                    {data: "fecha_d"},
                    {data: "editar"},
                    {data: "devolver"}
                ]
            });
        }
        if (accion == 2) {
            //destruye la tabla
            $("#TablaDevolucion").dataTable().fnDestroy();

            $('#TablaDevolucion').DataTable({
                columnDefs: [
                    {className: "dt-center", targets: "_all"}
                ],
                ajax: {
                    method: "POST",
                    url: "DevolucionControlador?accion=cargarTablaDevolucionHECHAS",
                    dataSrc: "datos"
                },
                columns: [
                    {data: "titulo"},
                    {data: "alumno"},
                    {data: "fecha_p"},
                    {data: "fecha_d"},
                    {data: "editar"},
                    {data: "devolver"}
                ]
            });
        }
        if (accion == 3) {
            //destruye la tabla
            $("#TablaDevolucion").dataTable().fnDestroy();

            $('#TablaDevolucion').DataTable({
                columnDefs: [
                    {className: "dt-center", targets: "_all"}
                ],
                ajax: {
                    method: "POST",
                    url: "DevolucionControlador?accion=cargarTablaDevolucionTODAS",
                    dataSrc: "datos"
                },
                columns: [
                    {data: "titulo"},
                    {data: "alumno"},
                    {data: "fecha_p"},
                    {data: "fecha_d"},
                    {data: "editar"},
                    {data: "devolver"}
                ]
            });
        }


    });




//    $("#agregarPrestamo").click(function () {
//        if (validarFomularioAgregar()) {
//            $.ajax({
//                type: "POST",
//                url: "PrestamoControlador",
//                data: {
//
//                    accion: "verificarFechaRenovacion",
//
//                    alumno: $("#alumnos").val(),
//                    fechaPrestamo: $("#fechaPrestamo").val()},
//                async: true,
//                cache: false,
//                success: function (resp) {
//                    if (resp == 1) {
//                        swal({
//                            position: 'center',
//                            type: 'warning',
//                            title: 'Debe Actualizar la fecha de renovación!',
//                            showConfirmButton: false,
//                            timer: 1800
//                        })
//                    } else {
//                        $.ajax({
//                            type: "POST",
//                            url: "PrestamoControlador",
//                            data: {
//
//                                accion: "insertar",
//                                // Estructura-> nombre de variable: valor de variable
//                                //guarda en las variables los datos de los select y los inputs
//                                libro: $("#material").val(),
//                                alumno: $("#alumnos").val(),
//                                fechaPrestamo: $("#fechaPrestamo").val()},
//                            async: true,
//                            cache: false,
//                            success: function (resp) {
//                                // en resp se guarda lo que el servlet retornó, en este caso retorna true o false
//                                if (resp == 1) {
//                                    swal({
//                                        position: 'center',
//                                        type: 'success',
//                                        title: 'Prestamo Agregado!',
//                                        showConfirmButton: false,
//                                        timer: 1500
//                                    });
//                                    $('#alumnos').load("PrestamoControlador", {accion: "cargarAlumnos"});
//                                    $('#material').load("PrestamoControlador", {accion: "cargarMaterial"});
//                                    //Recarga la tabla
//                                    $('#TablaPrestamos').DataTable().ajax.reload();
//                                } else {
//                                    swal({
//                                        position: 'center',
//                                        type: 'error',
//                                        title: 'No se pudo agregar!',
//                                        showConfirmButton: false,
//                                        timer: 1500
//                                    });
//                                }
////
////                console.log("Respuesta al agregar el libro " + resp);
//
//                            }
//
//                        });
//                    }
//                }
//            });
//        }
//    });

    $(document).on('click', '.devolver', function () {
        var id = $(this).attr("value");
        var now = new Date();
        var day = ("0" + now.getDate()).slice(-2);
        var month = ("0" + (now.getMonth() + 1)).slice(-2);
        var today = now.getFullYear() + "-" + (month) + "-" + (day);
//        alert();

        $.ajax({
            type: "POST",
            url: "DevolucionControlador",
            data: {
                accion: "devolver",
                id_prestamo: id,
                fechaDevolucion: today},
            async: true,
            cache: false,
            success: function (resp) {
                // Si la longitud de resp es igual a 6 es verdadero
                if (resp == 1) {
                    swal({
                        position: 'center',
                        type: 'success',
                        title: 'Exito!',
                        showConfirmButton: false,
                        timer: 1500
                    });
//                        $('#material').load("DevolucionControlador", {accion: "cargarMaterial"});
                    //Recarga la tabla
                    $('#TablaDevolucion').DataTable().ajax.reload();
                } else {
                    swal({
                        position: 'center',
                        type: 'error',
                        title: 'Error!',
                        showConfirmButton: false,
                        timer: 1500
                    });
                }

//                console.log("Respuesta al actualizar el libro " + resp);

            }

        });

    });


    $(document).on('click', '.actualizar', function () {
        var id = $(this).attr("value");
        $("#actualizarDatos").val(id);
        $.getJSON('DevolucionControlador', {accion: "cargarDatos", id: id},
                function (res) {
                    $("#Elibros").html(res.material);
                    $("#Ealumnos").html(res.alumno);
                    $("#EfechaPrestamo").val(res.fecha_p);
                    $("#EfechaDevolucion").val(res.fecha_d);
                });

    });
//
    $("#actualizarDatos").click(function () {
//        alert($("#actualizarDatos").val());
        if (validarFomularioModificar()) {
            $.ajax({
                type: "POST",
                url: "DevolucionControlador",
                data: {
                    accion: "actualizar",
                    id_prestamo: $("#actualizarDatos").val(),
                    id_libro: $("#Elibros").val(),
                    fechaDevolucion: $("#EfechaDevolucion").val()},
                async: true,
                cache: false,
                success: function (resp) {
                    // Si la longitud de resp es igual a 6 es verdadero
                    if (resp == 1) {
                        swal({
                            position: 'center',
                            type: 'success',
                            title: 'Se han actualizado los datos!',
                            showConfirmButton: false,
                            timer: 1500
                        });
//                        $('#material').load("DevolucionControlador", {accion: "cargarMaterial"});
                        //Recarga la tabla
                        $('#TablaDevolucion').DataTable().ajax.reload();
                    } else {
                        swal({
                            position: 'center',
                            type: 'error',
                            title: 'No se pudo actualizar!',
                            showConfirmButton: false,
                            timer: 1500
                        });
                    }

//                console.log("Respuesta al actualizar el libro " + resp);

                }

            });
        }
    });

    //VALIDACION 
    function validarFomularioAgregar() {
        if (($("#material").val().length > 0)) {
            if (($("#alumnos").val().length > 0)) {
                if (($("#fechaPrestamo").val().length > 0)) {
                    return true;
                } else {
                    $("#fechaPrestamo").focus();
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
                $("#alumnos").focus();
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
            $("#material").focus();
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

    function validarFomularioModificar() {
        if (($("#EfechaDevolucion").val().length > 0)) {
            return true;
        } else {
            $("#EfechaDevolucion").focus();
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
});


