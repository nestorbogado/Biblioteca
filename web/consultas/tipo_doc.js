$(document).ready(function () {
    $('#TablaTipoDoc').DataTable({
        columnDefs: [
            {className: "dt-center", targets: "_all"}
        ],
        ajax: {
            method: "POST",
            url: "TipoDControlador?accion=cargarTabla",
            dataSrc: "datos"
        },
        columns: [
            {data: "idtipodocumento"},
            {data: "descripcion"},
            {data: "editar"},
            {data: "eliminar"}
        ]
    });


    $("#agregarDoc").click(function () {
//        console.log("llegue al insertar del JS");
        if (validarFomulario()) {
            $.ajax({
                type: "POST",
                url: "TipoDControlador",
                data: {

                    accion: "insertar",
                    tipo_doc: $("#nombre").val()},
                async: true,
                cache: false,
                success: function (resp) {
                    // en resp se guarda lo que el servlet retornó, en este caso retorna true o false
                    if (resp == 1) {
                        swal({
                            position: 'center',
                            type: 'success',
                            title: 'Tipo de Documento Agregado!',
                            showConfirmButton: false,
                            timer: 1500
                        });
                        $("#nombre").val("");

                        //Recarga la tabla
                        $('#TablaTipoDoc').DataTable().ajax.reload();
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
                    $.getJSON('TipoDControlador', {accion: "eliminar", id: id}, function (res) {
                        if (res == 1) {
                            swal({
                                position: 'center',
                                type: 'success',
                                title: 'Tipo de Documento Eliminado!',
                                showConfirmButton: false,
                                timer: 1500
                            });

                            //Recarga la tabla
                            $('#TablaTipoDoc').DataTable().ajax.reload();
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

        $.getJSON('TipoDControlador', {accion: "cargarDatos", id: id},
                function (res) {
                    $("#actualizarDatos").val(id);
                    $("#Enombre").val(res.descripcion_tipo_doc);
                });
    });

    $("#actualizarDatos").click(function () {
        if (validarFomulario()) {
            $.ajax({
                type: "POST",
                url: "TipoDControlador",
                data: {
                    accion: "actualizar",
                    id: $("#actualizarDatos").val(),
                    tipo_doc: $("#Enombre").val()},
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
                    } else {
                        swal({
                            position: 'center',
                            type: 'error',
                            title: 'No se pudo actualizar!',
                            showConfirmButton: false,
                            timer: 1500
                        });
                    }

                    console.log("Respuesta al actualizar el libro " + resp);
                    //Recarga la tabla
                    $('#TablaTipoDoc').DataTable().ajax.reload();
                }

            });
        }
    });

    function validarFomulario() {
        if (($("#nombre").val().length > 1) || $("#Enombre").val().length > 1) {
            return true;
        } else {
            $("#nombre").focus();
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


