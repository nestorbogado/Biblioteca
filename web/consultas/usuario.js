$(document).ready(function () {

    $('#tipoU').load("UserControlador", {accion: "cargarTipo"});
    $('#estado').load("UserControlador", {accion: "cargarEstado"});

    $('#TablaUsuario').DataTable({
        ajax: {
            method: "POST",
            url: "UserControlador?accion=cargarTabla",
            dataSrc: "datos"
        },
        columns: [
            {data: "nombre"},
            {data: "usuario"},
            {data: "tipo"},
            {data: "estado"},
            {data: "editar"},
            {data: "anular"}
        ]
    });

    $("#agregarUsuario").click(function () {
        if (validarFomularioAgregar()) {
            $.ajax({
                type: "POST",
                url: "UserControlador",
                data: {

                    accion: "insertar",

                    nombre: $("#nombre").val(),
                    apellido: $("#apellido").val(),
                    usuario: $("#usuario").val(),
                    contrasenha: $("#contrasenha").val(),
                    tipoU: $("#tipoU").val(),
                    estado: $("#estado").val()},
                async: true,
                cache: false,
                success: function (resp) {
                    // en resp se guarda lo que el servlet retornó, en este caso retorna true o false
                    if (resp == 1) {
                        swal({
                            position: 'center',
                            type: 'success',
                            title: 'Usuario Agregado!',
                            showConfirmButton: false,
                            timer: 1500
                        });
                        //Vacía el campo de texto
                        $("#nombre").val("");
                        $("#apellido").val("");
                        $("#usuario").val("");
                        $("#contrasenha").val("");
                        $('#tipoU').load("UserControlador", {accion: "cargarTipo"});
                        $('#estado').load("UserControlador", {accion: "cargarEstado"});
                        //Recarga la tabla
                        $('#TablaUsuario').DataTable().ajax.reload();
                    } else {
                        swal({
                            position: 'center',
                            type: 'error',
                            title: 'No se pudo agregar!',
                            showConfirmButton: false,
                            timer: 1500
                        });
                    }

//                console.log("Respuesta al agregar el editorial " + resp);

                }

            });
        }
        ;
    });
    function validarFomularioAgregar() {
        if (($("#nombre").val().length > 3)) {
            if (($("#apellido").val().length > 3)) {
                if (($("#usuario").val().length > 4)) {
                    if (($("#contrasenha").val().length > 7)) {
                        return true;
                    } else {
                        $("#contrasenha").focus();
                        swal({
                            position: 'center',
                            type: 'warning',
                            title: 'La constraseña debe ser de 8 caracteres como mínimo!',
                            showConfirmButton: false,
                            timer: 1500
                        })
                        return false;
                    }
                } else {
                    $("#usuario").focus();
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
                $("#apellido").focus();
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
            $("#nombre").focus();
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

    $(document).on('click', '.actualizar', function () {
        var id = $(this).attr("value");

        $.ajax({
            type: "POST",
            url: "UsuarioControlador",
            data: {

                accion: "cargarDatos",
                // Estructura-> nombre de variable: valor de variable
                //guarda en las variables los datos de los select y los inputs
                id: id},
            async: true,
            cache: false,
            dataType: "json",
            success: function (res) {
                // en resp se guarda lo que el servlet retornó, en este caso retorna true o false
                $("#actualizarDatos").val(res.id_usuario);
                $("#Enombre").val(res.nombre_usuario);
                $("#Eapellido").val(res.apellido_usuario);
                $("#Eusuario").val(res.usuario);
                $("#EtipoU").html(res.tipo);
                $("#Eestado").html(res.estado);
            }

        });
    });

    $("#actualizarDatos").click(function () {
        if (validarFomularioModificar()) {
            $.ajax({
                type: "POST",
                url: "UserControlador",
                data: {
                    accion: "actualizar",
                    id: $("#actualizarDatos").val(),
                    nombre: $("#Enombre").val(),
                    apellido: $("#Eapellido").val(),
                    usuario: $("#Eusuario").val(),
                    contrasenha: $("#Econtrasenha").val(),
                    tipoU: $("#EtipoU").val(),
                    estado: $("#Eestado").val()},
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
//                    $("#actualizarDatosGenero").val(0);
                        //Recarga la tabla
                        $('#TablaUsuario').DataTable().ajax.reload();
                    } else {
                        if (resp == 2) {
                            swal({
                                position: 'center',
                                type: 'warning',
                                title: 'La constraseña debe ser de 8 caracteres como mínimo!',
                                showConfirmButton: false,
                                timer: 1500
                            });
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

//                console.log("Respuesta al actualizar el libro " + resp);
                }

            });
        }
    });
    function validarFomularioModificar() {
        if (($("#Enombre").val().length > 3)) {
            if (($("#Eapellido").val().length > 3)) {
                if (($("#Eusuario").val().length > 4)) {
                    return true;
                } else {
                    $("#Eusuario").focus();
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
                $("#Eapellido").focus();
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
            $("#Enombre").focus();
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
    
    $(document).on('click', '.anularUsuario', function () {
        var id = $(this).attr("value");
        swal({
            title: "Está seguro que desea anular ?",
            type: "warning",
            showCancelButton: true,
            confirmButtonClass: "btn-danger",
            confirmButtonText: "Si, Anular!",
            cancelButtonText: "No, Cancelar",
            closeOnConfirm: false
        },
                function () {
                    $.getJSON('UserControlador', {accion: "anularUsuario", id: id}, function (res) {
                        if (res == 1) {
                            swal({
                                position: 'center',
                                type: 'success',
                                title: 'Usuario Anulado!',
                                showConfirmButton: false,
                                timer: 1500
                            });

                            //Recarga la tabla
                            $('#TablaUsuario').DataTable().ajax.reload();
                        } else {
                            swal({
                                position: 'center',
                                type: 'error',
                                title: 'No se pudo Anular!',
                                showConfirmButton: false,
                                timer: 1500
                            });
                        }
//                console.log(res);
                    });
                }
        );

    });
});

