$(document).ready(function () {
    //Selecciona los select de la pagina LIBROS.JSP en inicializa con la libreria select2.full.js
////
//    $('#libros').select2();
//    $('#alumnos').select2();

    $('#TablaPrestamos').DataTable({
        ajax: {
            method: "POST",
            url: "PrestamoControlador?accion=cargarTabla",
            dataSrc: "datos"
        },
        columns: [
            {data: "titulo"},
            {data: "alumno"},
            {data: "fecha_p"},
            {data: "dias_prestado"},
            {data: "fecha_d"},
            {data: "dias_reales"},
            {data: "diferencia"},
            {data: "multa"},
            {data: "editar"},
            {data: "eliminar"}
        ]
    });

    (function () {
        var fecha1 = moment('2018-11-10');
        var fecha2 = moment('2018-11-09');

        console.log(fecha2.diff(fecha1, 'days'), ' dias de diferencia');
        cargarAlumnos();
        cargarLibros();
    })();

    function cargarAlumnos() {
        $.getJSON('PrestamoControlador', {accion: "cargarAlumnos"}, function (res) {
            console.log(res);
            var contenidoHTML = "<option value=''>Selecciona</option>";
            for (var i = 0; i < res.length; i++) {

                contenidoHTML += "<option value=" + res[i].id_alumno + ">" + res[i].alumno + "</option>";

            }
            //agrega el contendio html
            $("#alumnos").html(contenidoHTML);
        });
    }

    function cargarLibros() {
        $.getJSON('PrestamoControlador', {accion: "cargarLibros"}, function (res) {
            console.log(res);
            var contenidoHTML = "<option value=''>Selecciona</option>";
            for (var i = 0; i < res.length; i++) {

                contenidoHTML += "<option value=" + res[i].id_libro + ">" + res[i].libro + "</option>";

            }
            //agrega el contendio html
            $("#libros").html(contenidoHTML);
        });
    }


    $("#agregarPrestamo").click(function () {
        $.ajax({
            type: "POST",
            url: "PrestamoControlador",
            data: {

                accion: "insertar",
                // Estructura-> nombre de variable: valor de variable
                //guarda en las variables los datos de los select y los inputs
                libro: $("#libros").val(),
                alumno: $("#alumnos").val(),
                fechaPrestamo: $("#fechaPrestamo").val(),
                cantidadDias: $("#cantidadDias").val()},
            async: true,
            cache: false,
            success: function (resp) {
                // en resp se guarda lo que el servlet retornó, en este caso retorna true o false
                if (resp) {
                    swal({
                        position: 'center',
                        type: 'success',
                        title: 'Prestamo Agregado!',
                        showConfirmButton: false,
                        timer: 1500
                    });
                    //Vacia los inputs
                    $("#cantidadDias").val("");
                     $("#fechaPrestamo").val("");
                    cargarAlumnos();
                    cargarLibros();
                } else {
                    swal({
                        position: 'center',
                        type: 'error',
                        title: 'No se pudo agregar!',
                        showConfirmButton: false,
                        timer: 1500
                    });
                }

                console.log("Respuesta al agregar el libro " + resp);

                //Recarga la tabla
                $('#TablaPrestamos').DataTable().ajax.reload();
            }

        });

    });

    $(document).on('click', '.eliminar', function () {
        var id = $(this).attr("value");
        
        var opcion = confirm("Está seguro que desea eliminar.?");
        if (opcion == true) {
            $.getJSON('PrestamoControlador', {accion: "eliminar", id: id}, function (res) {
                if (res) {
                    swal({
                        position: 'center',
                        type: 'success',
                        title: 'Libro Eliminado!',
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
                $('#TablaPrestamos').DataTable().ajax.reload();
            });
        }

    });

    $(document).on('click', '.actualizar', function () {
        var id = $(this).attr("value");
        $("#actualizarDatos").val(id);
        $.getJSON('PrestamoControlador', {accion: "cargarDatos", id: id},
                function (res) {
                    $("#Elibros").val(res.titulo);
                    $("#Ealumnos").val(res.alumno);
                    $("#EfechaPrestamo").val(res.fecha_p);
                    $("#EcantidadDias").val(res.dias_prestado);
                    $("#EfechaDevolucion").val(res.fecha_d);
                    $("#EcantidadDiasReales").val(res.dias_reales);
                    $("#Ediferencia").val(res.diferencia);
                    $("#Emulta").val(res.multa);
                });
        $("#EfechaDevolucion").change(function () {
            
           var fecha = moment($('#EfechaPrestamo').val());
           
            var fecha1 = fecha;
        var fecha2 = moment($('#EfechaDevolucion').val());
         $("#EcantidadDiasReales").val(fecha2.diff(fecha1, 'days'));
        console.log(fecha2.diff(fecha1, 'days'), ' dias de diferencia');
        //Calculo de cantidades
        var aux1= $("#EcantidadDiasReales").val();
        var aux2=$("#EcantidadDias").val();
        var aux3=(aux2-aux1);
            if (aux3<0) {
                swal({
                        position: 'center',
                        type: 'error',
                        title: 'Dias Superados!',
                        showConfirmButton: false,
                        timer: 1500
                    });
            }
        $("#Ediferencia").val(aux3);
        });
    });
    
    $("#actualizarDatos").click(function () {

        $.ajax({
            type: "POST",
            url: "PrestamoControlador",
            data: {
                accion: "actualizar",
                id_prestamo: $("#actualizarDatos").val(),
                fechaDevolucion: $("#EfechaDevolucion").val(),
                cantidadDiasReales: $("#EcantidadDiasReales").val(),
                diferencia: $("#Ediferencia").val(),
                multa: $("#Emulta").val()},
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

                console.log("Respuesta al actualizar el libro " + resp);
                
                //Recarga la tabla
                $('#TablaPrestamos').DataTable().ajax.reload();
            }

        });

    });
});

