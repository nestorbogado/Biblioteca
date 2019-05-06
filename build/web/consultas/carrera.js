$(document).ready(function () {
    
    $('#TablaCarreras').DataTable({
        ajax: {
            method: "POST",
            url: "CarreraControlador?accion=cargarTabla",
            dataSrc: "datos"
        },
        columns: [
            {data: "id_carrera"},
            {data: "nombre_carrera"},
            {data: "editar"},
            {data: "eliminar"}
        ]
    });
    
    
    $("#agregarCarrera").click(function () {

        $.ajax({
            type: "POST",
            url: "CarreraControlador",
            data: {
                
                accion: "insertar",
                carrera: $("#nombre").val()},
            async: true,
            cache: false,
            success: function (resp) {
                // en resp se guarda lo que el servlet retornó, en este caso retorna true o false
                if (resp) {
                    swal({
                        position: 'center',
                        type: 'success',
                        title: 'Editorial Agregado!',
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

                console.log("Respuesta al agregar el editorial " + resp);
               
               
                //Recarga la tabla
                $('#TablaCarreras').DataTable().ajax.reload();
            }

        });

    });
    
    
    $(document).on('click', '.eliminar', function () {
        var id = $(this).attr("value");
        var opcion = confirm("Está seguro que desea eliminar.?");
        if (opcion == true) {
            $.getJSON('CarreraControlador', {accion: "eliminar", id: id}, function (res) {
                if (res) {
                    swal({
                        position: 'center',
                        type: 'success',
                        title: 'Editorial Eliminada!',
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
                $('#TablaCarreras').DataTable().ajax.reload();
            });
        }

    });
    
     $(document).on('click', '.actualizar', function () {
        var id = $(this).attr("value");
        
        $.getJSON('CarreraControlador', {accion: "cargarDatos", id: id},
                function (res) {
                    $("#actualizarDatos").val(id);
                    $("#Enombre").val(res.nombre_carrera);
                });
    });
    
    $("#actualizarDatos").click(function () {

        $.ajax({
            type: "POST",
            url: "CarreraControlador",
            data: {
                accion: "actualizar",
                id: $("#actualizarDatos").val(),
               carrera: $("#Enombre").val()},
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

                console.log("Respuesta al actualizar el libro " + resp);
                //Recarga la tabla
                $('#TablaCarreras').DataTable().ajax.reload();
            }

        });

    });
});


