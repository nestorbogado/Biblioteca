$(document).ready(function () {

    $('#TablaAutores').DataTable({
        columnDefs: [
            {className: "dt-center", targets: "_all"}
        ],
        ajax: {
            method: "POST",
            url: "AutorControlador?accion=cargarTabla",
            dataSrc: "datos"
        },
        columns: [
            {data: "id_autor"},
            {data: "nombre_autor"},
            {data: "editar"},
            {data: "eliminar"}
        ]
    });


    $("#agregarAutor").click(function () {
        if (validarFomularioAgregar()) {
            $.ajax({
                type: "POST",
                url: "AutorControlador",
                data: {

                    accion: "insertar",
                    autor: $("#nombre").val()},
                async: true,
                cache: false,
                success: function (resp) {
                    // en resp se guarda lo que el servlet retornó, en este caso retorna true o false
                    if (resp == 1) {
                        swal({
                            position: 'center',
                            type: 'success',
                            title: 'Autor Agregado!',
                            showConfirmButton: false,
                            timer: 1500
                        });
                        //Vacía el campo de texto
                        $("#nombre").val("");
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


                    //Recarga la tabla
                    $('#TablaAutores').DataTable().ajax.reload();
                }

            });
        }
        ;
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
                    $.getJSON('AutorControlador', {accion: "eliminar", id: id}, function (res) {
                        if (res == 1) {
                            swal({
                                position: 'center',
                                type: 'success',
                                title: 'Autor Eliminado!',
                                showConfirmButton: false,
                                timer: 1500
                            });

                            //Recarga la tabla
                            $('#TablaAutores').DataTable().ajax.reload();
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

        $.getJSON('AutorControlador', {accion: "cargarDatos", id: id},
                function (res) {
                    $("#actualizarDatos").val(id);
                    $("#Enombre").val(res.nombre_autor);
                });
    });

    $("#actualizarDatos").click(function () {
        if (validarFomularioModificar()) {
            $.ajax({
                type: "POST",
                url: "AutorControlador",
                data: {
                    accion: "actualizar",
                    id: $("#actualizarDatos").val(),
                    autor: $("#Enombre").val()},
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
                        $("#actualizarDatos").val(0);
                        $("#Enombre").val("");
                        //Recarga la tabla
                        $('#TablaAutores').DataTable().ajax.reload();
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
        ;
    });

    //VALIDACION 
    function validarFomularioAgregar() {
        if (($("#nombre").val().length > 5)) {
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
        if (($("#Enombre").val().length > 5)) {
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




