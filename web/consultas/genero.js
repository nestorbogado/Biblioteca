$(document).ready(function () {

    $('#TablaGeneros').DataTable({
        columnDefs: [
            {className: "dt-center", targets: "_all"}
        ],
        ajax: {
            method: "POST",
            url: "GeneroControlador?accion=cargarTabla",
            dataSrc: "datos"
        },
        columns: [
            {data: "id_genero"},
            {data: "nombre_genero"},
            {data: "editar"},
            {data: "eliminar"}
        ]
    });


    $("#agregarGenero").click(function () {
        if (validarFomularioAgregar()) {
            $.ajax({
                type: "POST",
                url: "GeneroControlador",
                data: {

                    accion: "insertar",
                    genero: $("#nombreG").val()},
                async: true,
                cache: false,
                success: function (resp) {
                    // en resp se guarda lo que el servlet retornó, en este caso retorna true o false
                    if (resp == 1) {
                        swal({
                            position: 'center',
                            type: 'success',
                            title: 'Genero Agregado!',
                            showConfirmButton: false,
                            timer: 1500
                        });
                        $("#nombreG").val("");

                        //Recarga la tabla
                        $('#TablaGeneros').DataTable().ajax.reload();
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
                    $.getJSON('GeneroControlador', {accion: "eliminar", id: id}, function (res) {
                        if (res == 1) {
                            swal({
                                position: 'center',
                                type: 'success',
                                title: 'Genero Eliminado!',
                                showConfirmButton: false,
                                timer: 1500
                            });

                            //Recarga la tabla
                            $('#TablaGeneros').DataTable().ajax.reload();
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
                }
        );

    });
    $(document).on('click', '.actualizarG', function () {
        var id = $(this).attr("value");

        $.ajax({
            type: "POST",
            url: "GeneroControlador",
            data: {

                accion: "cargarDatosGenero",
                // Estructura-> nombre de variable: valor de variable
                //guarda en las variables los datos de los select y los inputs
                id: id},
            async: true,
            cache: false,
            dataType: "json",
            success: function (res) {
                // en resp se guarda lo que el servlet retornó, en este caso retorna true o false
                $("#actualizarDatosGenero").val(res.id_genero);
                $("#EnombreG").val(res.nombre_genero);
            }

        });
    });


    $("#actualizarDatosGenero").click(function () {
        if (validarFomularioModificar()) {
            $.ajax({
                type: "POST",
                url: "GeneroControlador",
                data: {
                    accion: "actualizar",
                    id: $("#actualizarDatosGenero").val(),
                    genero: $("#EnombreG").val()},
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
                        $("#EnombreG").val("");
//                    $("#actualizarDatosGenero").val(0);
                        //Recarga la tabla
                        $('#TablaGeneros').DataTable().ajax.reload();
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
        if (($("#nombreG").val().length > 5)) {
            return true;
        } else {
            $("#nombreG").focus();
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
        if ($("#EnombreG").val().length > 5) {
            return true;
        } else {
            $("#EnombreG").focus();
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

