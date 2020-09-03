$(document).ready(function () {

    $('#TablaCarreras').DataTable({
        columnDefs: [
            {className: "dt-center", targets: "_all"}
        ],
        ajax: {
            method: "POST",
            url: "FacultadControlador?accion=cargarTabla",
            dataSrc: "datos"
        },
        columns: [
            {data: "idfacultad"},
            {data: "descripcion"},
            {data: "editar"},
            {data: "eliminar"}
        ]
    });


    $("#agregarCarrera").click(function () {
        if (validarFomulario()) {
            $.ajax({
                type: "POST",
                url: "FacultadControlador",
                data: {

                    accion: "insertar",
                    descripcion: $("#descripcion_facultad").val()},
                async: true,
                cache: false,
                success: function (resp) {
                    // en resp se guarda lo que el servlet retornó, en este caso retorna true o false
                    if (resp == 1) {
                        swal({
                            position: 'center',
                            type: 'success',
                            title: 'Facultad Agregada!',
                            showConfirmButton: false,
                            timer: 1500
                        });
                        $("#descripcion_facultad").val("");
                        //Recarga la tabla
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
                    $.getJSON('FacultadControlador', {accion: "eliminar", id: id}, function (res) {
                        if (res == 1) {
                            swal({
                                position: 'center',
                                type: 'success',
                                title: 'Facultad Eliminada!',
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

        $.getJSON('FacultadControlador', {accion: "cargarDatos", id: id},
                function (res) {
                    $("#actualizarDatos").val(id);
                    $("#Efacultad").val(res.descripcion);

                });

    });

    $("#actualizarDatos").click(function () {
        if (validarFomularioModificar()) {
            $.ajax({
                type: "POST",
                url: "FacultadControlador",
                data: {
                    accion: "actualizar",
                    id: $("#actualizarDatos").val(),
                    facultad: $("#Efacultad").val()},
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
                        $("#Efacultad").val("");
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
        if ($("#descripcion_facultad").val().length > 5) {
            return true;
        } else {
            $("#descripcion_facultad").focus();
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
        if ($("#Efacultad").val().length > 5) {
            return true;
        } else {
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
    }
});


