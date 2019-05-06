$(document).ready(function () {
    $("#Buscar").click(function () {
        //destruye la tabla
        var aux = $("#nombreBuscar").val();
        $("#TablaCategorias").dataTable().fnDestroy();
        $('#TablaCategorias').DataTable({
            ajax: {
                method: "POST",
                url: "CategoriaControlador?accion=cargarBusqueta&aux=" + aux,
                dataSrc: "datos"
            },
            columns: [
                {data: "id_categoria"},
                {data: "descripcion_categoria"},
                {data: "editar"},
                {data: "eliminar"}
            ]
        });
    });
    $("#TODOS").click(function () {
        $("#TablaCategorias").dataTable().fnDestroy();
        $('#TablaCategorias').DataTable({
            ajax: {
                method: "POST",
                url: "CategoriaControlador?accion=cargarTabla",
                dataSrc: "datos"
            },
            columns: [
                {data: "id_categoria"},
                {data: "descripcion_categoria"},
                {data: "editar"},
                {data: "eliminar"}
            ]
        });
    });

    $('#TablaCategorias').DataTable({
        ajax: {
            method: "POST",
            url: "CategoriaControlador?accion=cargarTabla",
            dataSrc: "datos"
        },
        columns: [
            {data: "id_categoria"},
            {data: "descripcion_categoria"},
            {data: "editar"},
            {data: "eliminar"}
        ]
    });


    $("#agregarCategoria").click(function () {

        $.ajax({
            type: "POST",
            url: "CategoriaControlador",
            data: {

                accion: "insertar",
                autor: $("#nombre").val()},
            async: true,
            cache: false,
            success: function (resp) {
                // en resp se guarda lo que el servlet retornó, en este caso retorna true o false
                if (resp) {
                    swal({
                        position: 'center',
                        type: 'success',
                        title: 'Categoria Agregada!',
                        showConfirmButton: false,
                        timer: 1500
                    });
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

                console.log("Respuesta al agregar la categoria " + resp);


                //Recarga la tabla
                $('#TablaCategorias').DataTable().ajax.reload();
            }

        });

    });


    $(document).on('click', '.eliminar', function () {
        var id = $(this).attr("value");
        var opcion = confirm("Está seguro que desea eliminar.?");
        if (opcion == true) {
            $.getJSON('CategoriaControlador', {accion: "eliminar", id: id}, function (res) {
                if (res) {
                    swal({
                        position: 'center',
                        type: 'success',
                        title: 'Categoria Eliminada!',
                        showConfirmButton: false,
                        timer: 1500
                    });
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
                //Recarga la tabla
                $('#TablaCategorias').DataTable().ajax.reload();
            });
        }

    });

    $(document).on('click', '.actualizar', function () {
        var id = $(this).attr("value");

        $.getJSON('CategoriaControlador', {accion: "cargarDatos", id: id},
                function (res) {
                    $("#actualizarDatos").val(id);
                    $("#Enombre").val(res.descripcion_categoria);
                });
    });

    $("#actualizarDatos").click(function () {

        $.ajax({
            type: "POST",
            url: "CategoriaControlador",
            data: {
                accion: "actualizar",
                id: $("#actualizarDatos").val(),
                categoria: $("#Enombre").val()},
            async: true,
            cache: false,
            success: function (resp) {
                // Si la longitud de resp es igual a 6 es verdadero
                if (resp) {
                    swal({
                        position: 'center',
                        type: 'success',
                        title: 'Se han actualizado los datos!',
                        showConfirmButton: false,
                        timer: 1500
                    });
                    $("#Enombre").val("");
                } else {
                    swal({
                        position: 'center',
                        type: 'error',
                        title: 'No se pudo actualizar!',
                        showConfirmButton: false,
                        timer: 1500
                    });
                }

                console.log("Respuesta al actualizar la categoria " + resp);
                //Recarga la tabla
                $('#TablaCategorias').DataTable().ajax.reload();
            }

        });

    });
});

