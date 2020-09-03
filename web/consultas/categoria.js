$(document).ready(function () {

    $('#TablaTipo').DataTable({
        columnDefs: [
            {className: "dt-center", targets: "_all"}
        ],
        ajax: {
            method: "POST",
            url: "TipoControlador?accion=cargarTabla",
            dataSrc: "datos"
        },
        columns: [
            {data: "id_tipo_material"},
            {data: "descripcion_material"},
            {data: "editar"},
            {data: "eliminar"}
        ]
    });


    $("#agregarTipo").click(function () {
        if (validarFomulario()) {
            $.ajax({
                type: "POST",
                url: "TipoControlador",
                data: {

                    accion: "insertar",
                    tipo: $("#nombre").val()},
                async: true,
                cache: false,
                success: function (resp) {
                    // en resp se guarda lo que el servlet retornó, en este caso retorna true o false
                    if (resp == 1) {
                        swal({
                            position: 'center',
                            type: 'success',
                            title: 'Tipo de Material Agregado!',
                            showConfirmButton: false,
                            timer: 1500
                        });
                        $("#nombre").val("");

                        //Recarga la tabla
                        $('#TablaTipo').DataTable().ajax.reload();
                    } else {
                        swal({
                            position: 'center',
                            type: 'error',
                            title: 'No se pudo agregar!',
                            showConfirmButton: false,
                            timer: 1500
                        });
                    }
//                console.log("Respuesta al agregar la categoria " + resp);

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
                    $.getJSON('TipoControlador', {accion: "eliminar", id: id}, function (res) {
                        if (res == 1) {
                            swal({
                                position: 'center',
                                type: 'success',
                                title: 'Tipo de Material Eliminado!',
                                showConfirmButton: false,
                                timer: 1500
                            });
                            //Recarga la tabla
                            $('#TablaTipo').DataTable().ajax.reload();
                        } else {
                            swal({
                                position: 'center',
                                type: 'error',
                                title: 'No se pudo eliminar!',
                                showConfirmButton: false,
                                timer: 1500
                            });
                        }
                        console.log(res);

                    });
                });



    });

    $(document).on('click', '.actualizar', function () {
        var id = $(this).attr("value");

        $.getJSON('TipoControlador', {accion: "cargarDatos", id: id},
                function (res) {
                    $("#actualizarDatos").val(res.id_tipo_material);
                    $("#Enombre").val(res.descripcion_material);
                });
    });

    $("#actualizarDatos").click(function () {
        if (validarFomularioModificar()) {
            $.ajax({
                type: "POST",
                url: "TipoControlador",
                data: {
                    accion: "actualizar",
                    id: $("#actualizarDatos").val(),
                    tipo: $("#Enombre").val()},
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

                        $('#TablaTipo').DataTable().ajax.reload();
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

    function validarFomulario() {
        if ($("#nombre").val().length > 1) {
            return true;
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
        if ($("#Enombre").val().length > 1) {
            return true;
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

