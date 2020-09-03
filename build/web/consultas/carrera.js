$(document).ready(function () {

    $('#facultad').select2();
    $('#Efacultad').select2();
    $('#facultad').load("CarreraControlador", {accion: "cargarFacultad"});
    $('#TablaCarreras').DataTable({
        columnDefs: [
            {className: "dt-center", targets: "_all"}
        ],
        ajax: {
            method: "POST",
            url: "CarreraControlador?accion=cargarTabla",
            dataSrc: "datos"
        },
        columns: [
            {data: "id_carrera"},
            {data: "nombre_carrera"},
            {data: "facultad"},
            {data: "editar"},
            {data: "eliminar"}
        ]
    });


    $("#agregarCarrera").click(function () {
        if (validarFomulario()) {
            $.ajax({
                type: "POST",
                url: "CarreraControlador",
                data: {

                    accion: "insertar",
                    carrera: $("#nombre").val(),
                    facultad: $("#facultad").val()},
                async: true,
                cache: false,
                success: function (resp) {
                    // en resp se guarda lo que el servlet retornó, en este caso retorna true o false
                    if (resp == 1) {
                        swal({
                            position: 'center',
                            type: 'success',
                            title: 'Carrera Agregada!',
                            showConfirmButton: false,
                            timer: 1500
                        });
                        $("#nombre").val("");
                        //Recarga la tabla
                        $('#facultad').load("CarreraControlador", {accion: "cargarFacultad"});
                        $('#TablaCarreras').DataTable().ajax.reload();
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
                    $.getJSON('CarreraControlador', {accion: "eliminar", id: id}, function (res) {
                        if (res == 1) {
                            swal({
                                position: 'center',
                                type: 'success',
                                title: 'Carrera Eliminada!',
                                showConfirmButton: false,
                                timer: 1500
                            });

                            //Recarga la tabla
                            $('#TablaCarreras').DataTable().ajax.reload();
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

        $.getJSON('CarreraControlador', {accion: "cargarDatos", id: id},
                function (res) {
                    $("#actualizarDatos").val(id);
                    $("#Enombre").val(res.nombre_carrera);
                    $("#Efacultad").html(res.facultad);

                });

    });

    $("#actualizarDatos").click(function () {
        if (validarFomularioModificar()) {
            $.ajax({
                type: "POST",
                url: "CarreraControlador",
                data: {
                    accion: "actualizar",
                    id: $("#actualizarDatos").val(),
                    facultad: $("#Efacultad").val(),
                    carrera: $("#Enombre").val()},
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
                        $("#Enombre").val("");
                        $("#actualizarDatos").val(0);
                        //Recarga la tabla
                        $('#TablaCarreras').DataTable().ajax.reload();
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
    function validarFomulario() {
        if ($("#nombre").val().length > 5) {
            if ($("#facultad").val().length > 0) {
                return true;
            }else{
                $("#facultad").focus();
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
    function validarFomularioModificar() {
         if ($("#Enombre").val().length > 5) {
            if ($("#Efacultad").val().length > 0) {
                return true;
            }else{
                $("#Efacultad").focus();
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


