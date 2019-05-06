$(document).ready(function () {
    
    (function (){
        cargarCarrera(0);
    })();
     function cargarCarrera(id) {
        $.getJSON('AlumnoControlador', {accion: "cargarCarrera"}, function (res) {
            console.log(res);
            //variable de tipo String donde se concatena todo el contenido html
            var contenidoHTML = "<option value=''>Selecciona</option>";
            for (var i = 0; i < res.length; i++) {
                if (id === res[i].id_carrera) {
                    contenidoHTML += "<option selected value=" + res[i].id_carrera + ">" + res[i].nombre_carrera + "</option>";
                } else {
                    contenidoHTML += "<option value=" + res[i].id_carrera + ">" + res[i].nombre_carrera + "</option>";
                }
            }
            //agrega el contendio html
            $("#carrera").html(contenidoHTML);
            $("#Ecarrera").html(contenidoHTML);
        });
    }
   

    $('#TablaAlumnos').DataTable({
        ajax: {
            method: "POST",
            url: "AlumnoControlador?accion=cargarTabla",
            dataSrc: "datos"
        },
        columns: [
            {data: "cedula"},
            {data: "alumno"},
            {data: "carrera"},
            {data: "editar"},
            {data: "eliminar"}
        ]
    });


    $("#agregarAlumno").click(function () {

        $.ajax({
            type: "POST",
            url: "AlumnoControlador",
            data: {

                accion: "insertar",
                nombre: $("#nombre").val(),
                apellido: $("#apellido").val(),
                ci: $("#ci").val(),
                carrera: $("#carrera").val()},
            async: true,
            cache: false,
            success: function (resp) {
                // en resp se guarda lo que el servlet retornó, en este caso retorna true o false
                if (resp) {
                    swal({
                        position: 'center',
                        type: 'success',
                        title: 'Alumno Agregado!',
                        showConfirmButton: false,
                        timer: 1500
                    });
                    $("#nombre").val("");
                    $("#apellido").val("");
                    $("#ci").val("");
                    cargarCarrera(0);
                } else {
                    swal({
                        position: 'center',
                        type: 'error',
                        title: 'No se pudo agregar!',
                        showConfirmButton: false,
                        timer: 1500
                    });
                }

                //Recarga la tabla
                $('#TablaAlumnos').DataTable().ajax.reload();
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
                $('#TablaAlumnos').DataTable().ajax.reload();
            });
        }

    });

    $(document).on('click', '.actualizar', function () {
        var id = $(this).attr("value");
        $("#actualizarDatos").val(id);
        $.getJSON('AlumnoControlador', {accion: "cargarDatos", id: id},
                function (res) {
                    $("#Enombre").val(res.nombre_alumno);
                    $("#Eapellido").val(res.apellido_alumno);
                    $("#Eci").val(res.cedula);
                    cargarCarrera(res.id_carrera);
                });
    });

    $("#actualizarDatos").click(function () {

        $.ajax({
            type: "POST",
            url: "AlumnoControlador",
            data: {
                accion: "actualizar",
                id:$("#actualizarDatos").val(),
                nombre: $("#Enombre").val(),
                apellido: $("#Eapellido").val(),
                ci: $("#Eci").val(),
                carrera: $("#Ecarrera").val()},
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

    });
});


