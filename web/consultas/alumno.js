$(document).ready(function () {

    (function () {
        var now = new Date();
        var day = ("0" + now.getDate()).slice(-2);
        var month = ("0" + (now.getMonth() + 1)).slice(-2);
        var today = now.getFullYear() + "-" + (month) + "-" + (day);
        $("#fechaIngreso").val(today);
        $("#fechaRenovacion").val(today);
        $('#carrera').load("AlumnoControlador", {accion: "cargarCarrera"});
        $('#ti').load("AlumnoControlador", {accion: "cargarTipoDoc"});
    })();


    $('#TablaAlumnos').DataTable({
         columnDefs: [
            {className: "dt-center", targets: "_all"}
        ],
        ajax: {
            method: "POST",
            url: "AlumnoControlador?accion=cargarTabla",
            dataSrc: "datos"
        },
        columns: [
            {data: "cedula"},
            {data: "alumno"},
            {data: "carrera"},
            {data: "telefono"},
            {data: "editar"},
            {data: "eliminar"}
        ]
       
    });

    $("#agregarAlumno").click(function () {
        if (validarFomularioAgregar()) {
            $.ajax({
                type: "POST",
                url: "AlumnoControlador",
                data: {
                    accion: "insertar",

                    nombre: $("#nombre").val(),
                    apellido: $("#apellido").val(),
                    tipo_doc: $("#ti").val(),
                    ci: $("#ci").val(),
                    carrera: $("#carrera").val(),
                    telefono: $("#te").val(),
                    fechaIngreso: $("#fechaIngreso").val(),
                    fechaRenovacion: $("#fechaRenovacion").val()},
                async: true,
                cache: false,
                success: function (resp) {
                    // en resp se guarda lo que el servlet retornó, en este caso retorna true o false
                    if (resp == 1) {
                        swal({
                            position: 'center',
                            type: 'success',
                            title: 'Alumno Agregado!',
                            showConfirmButton: false,
                            timer: 1500
                        });
                        $("#nombre").val("");
                        $("#apellido").val("");
                        $("#ti").val("");
                        $("#ci").val("");
                        $("#te").val("");
//                        $("#fechaIngreso").val("");
//                        $("#fechaRenovacion").val("");
                        $('#carrera').load("AlumnoControlador", {accion: "cargarCarrera"});
                        $('#ti').load("AlumnoControlador", {accion: "cargarTipoDoc"});


                        //Recarga la tabla
                        $('#TablaAlumnos').DataTable().ajax.reload();
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
    $(document).on('click', '.eliminar', function () {
        var id = $(this).attr("value");
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
                    $.getJSON('AlumnoControlador', {accion: "eliminar", id: id}, function (res) {
                        if (res == 1) {
                            swal({
                                position: 'center',
                                type: 'success',
                                title: 'Alumno Eliminado!',
                                showConfirmButton: false,
                                timer: 1500
                            });

                            //Recarga la tabla
                            $('#TablaAlumnos').DataTable().ajax.reload();
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
    $(document).on('click', '.actualizar', function () {
        var id = $(this).attr("value");
        $("#actualizarDatos").val(id);
        $.getJSON('AlumnoControlador', {accion: "cargarDatos", id: id},
                function (res) {
                    $("#Enombre").val(res.nombre_alumno);
                    $("#Eapellido").val(res.apellido_alumno);
                    $("#Eci").val(res.cedula);
                    $("#Ete").val(res.telefono_alumno);
                    $("#EfechaIngreso").val(res.fecha_ingreso);
                    $("#EfechaRenovacion").val(res.fecha_renovacion);
                    $('#Ecarrera').html(res.carrera);
                    $('#Eti').html(res.tipo);
                });
    });

    $("#actualizarDatos").click(function () {
        if (validarFomularioActualizar()) {
            $.ajax({
                type: "POST",
                url: "AlumnoControlador",
                data: {
                    accion: "actualizar",

                    id: $("#actualizarDatos").val(),
                    nombre: $("#Enombre").val(),
                    apellido: $("#Eapellido").val(),
                    tipo_doc: $("#Eti").val(),
                    ci: $("#Eci").val(),
                    telefono: $("#Ete").val(),
                    carrera: $("#Ecarrera").val(),
                    fechaIngreso: $("#EfechaIngreso").val(),
                    fechaRenovacion: $("#EfechaRenovacion").val()},
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
                    } else {
                        swal({
                            position: 'center',
                            type: 'error',
                            title: 'No se pudo actualizar!',
                            showConfirmButton: false,
                            timer: 1500
                        });
                    }

                    //Recarga la tabla
                    $('#TablaAlumnos').DataTable().ajax.reload();
                }

            });
        }
    });

    //VALIDACION 
    function validarFomularioAgregar() {
        if ($("#nombre").val().length > 3) {
            if ($("#apellido").val().length > 3) {
                if ($("#ci").val().length > 5) {
                    if ($("#te").val().length > 9) {
                        if ($("#carrera").val().length > 0) {
                            if ($("#fechaIngreso").val().length > 0) {
                                if ($("#fechaRenovacion").val().length > 0) {
                                    return true;
                                } else {
                                    $("#fechaRenovacion").focus();
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
                        $("#te").focus();
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
                    $("#ci").focus();
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

    function validarFomularioActualizar() {
        if ($("#Enombre").val().length > 3) {
            if ($("#Eapellido").val().length > 3) {
                if ($("#Eci").val().length > 5) {
                    if ($("#Ete").val().length > 9) {
                        if ($("#Ecarrera").val().length > 0) {
                            if ($("#EfechaIngreso").val().length > 0) {
                                if ($("#EfechaRenovacion").val().length > 0) {
                                    return true;
                                } else {
                                    $("#EfechaRenovacion").focus();
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
                        $("#Ete").focus();
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
                    $("#Eci").focus();
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
});


