$(document).ready(function () {
    //Selecciona los select de la pagina LIBROS.JSP en inicializa con la libreria select2.full.js

    $('#editorial').select2();
    $('#Eeditorial').select2();
    $('#autor').select2();
    $('#Eautor').select2();
    $('#genero').select2();
    $('#Egenero').select2();
    $('#categoria').select2();
    

    /*====================================================================
     //INICIALIZA LA TABLA DE LIBROS CON LA LIBRERIA dataTable.js y a su vez 
    carga los datos que recibe del Servlet LibroTablaControlador
     //====================================================================
     */
    $('#TablaLibros').DataTable({
      

        ajax: {
            method: "POST",
            url: "LibroControlador?accion=cargarTabla",
            dataSrc: "datos"
        },
        columns: [
            // Debe ser la misma cantidad de datos con la cantidad de columnas de la tabla
            //data: nombre del dato que recibe del Servlet
            // ISBM|TITULO|AÑO|ESTADO|EDITORIAL|GENERO|AUTOR|EDITAR|ELIMINAR
            {data: "ISBN"},
            {data: "titulo"},
            {data: "año_publicacion"},
            {data: "estado"},
            {data: "nombre_editorial"},
            {data: "nombre_genero"},
            {data: "nombre_autor"},
            {data: "editar"},
            {data: "eliminar"}
        ]
    });

    // Esta es una funcion autoejecutable, se ejecuta solo una vez al momento de cargar la pagina LIBROS.JSP
    (function cargar() {
        //Llama a las funciones para cargar las editoriales, generos y autores en los select
        // envia como parametro a esas funciones el valor cero
        cargarEditorial(0);
        cargarGenero(0);
        cargarAutor(0);
        cargarCategoria(0);
    })();

   
    /*====================================================================
     //FUNCIONES PARA CARGAR EN LOS SELECT LAS EDITORIALES, AUTORES Y GENEROS
     //====================================================================
    
    Cada funcion recibe un parametro que seria el id
     */
    function cargarEditorial(id) {
        //Funcion de AJAX Y JSON 
        // $.getJSON('nombre del controlador(SERVLET)', {NOMBRE DE LA VARIABLE QUE VA A RECIBIR EL SERVLET: VALOR DE LA VARIABLE},
        //  function (RESPUESTA DEL SERVLET) { aciones con los datos recibidos del servlet});
        
        $.getJSON('LibroControlador', {accion: "cargarEditorial"}, function (res) {
            //muestra en consola la respuesta del servlet(sirve para control nada más)
            console.log(res);
            //variable de tipo String donde se concatena todo el contenido html
            var contenidoHTML = "<option value=''>Selecciona</option>";
            // recorre la lista de lista o ARRAY que retorno el servlet
            // res.length es para saber la longitud de la lista
            for (var i = 0; i < res.length; i++) {
                // ESTA CONDICIONAL ES PARA SABER SI COINCIDE EL ID DEL ARRAY CON EL ID QUE RECIBE COMO PARAMETRO LA FUNCION
                // AL MOMENTO DE CARGAR LA PAGINA POR PRIMERA VEZ ESTA FUNCION RECIBE COMO PARAMETRO EL VALOR 0 ES DECIR, NO 
                // ENCONTRARÁ NINGUNA COICIDENCIA CON res[i].id_editorial E IRÁ SIEMPRE POR EL ELSE
                if (id === res[i].id_editorial) {
                    // EJEMPLO DE COMO QUEDARIA contenidoHTML += "<option selected value="1" La Nacion "</option>";
                    contenidoHTML += "<option selected value=" + res[i].id_editorial + ">" + res[i].nombre_editorial + "</option>";
                } else {
                    contenidoHTML += "<option value=" + res[i].id_editorial + ">" + res[i].nombre_editorial + "</option>";
                }
            }
            //una vez que haya recorrido todo el servlet agrega el variable string como contendo html a los select del JSP
            //agrega el contendio html
            $("#editorial").html(contenidoHTML);
            $("#Eeditorial").html(contenidoHTML);
        });
    }
    function cargarGenero(id) {
        $.getJSON('LibroControlador', {accion: "cargarGenero"}, function (res) {
            console.log(res);
            //variable de tipo String donde se concatena todo el contenido html
            var contenidoHTML = "<option value=''>Selecciona</option>";
            for (var i = 0; i < res.length; i++) {
                if (id === res[i].id_genero) {
                    contenidoHTML += "<option selected value=" + res[i].id_genero + ">" + res[i].nombre_genero + "</option>";
                } else {
                    contenidoHTML += "<option value=" + res[i].id_genero + ">" + res[i].nombre_genero + "</option>";
                }
            }
            //agrega el contendio html
            $("#genero").html(contenidoHTML);
            $("#Egenero").html(contenidoHTML);
        });
    }
     function cargarCategoria(id) {
        $.getJSON('LibroControlador', {accion: "cargarCategoria"}, function (res) {
            console.log(res);
            //variable de tipo String donde se concatena todo el contenido html
            var contenidoHTML = "<option value=''>Selecciona</option>";
            for (var i = 0; i < res.length; i++) {
                if (id === res[i].id_categoria) {
                    contenidoHTML += "<option selected value=" + res[i].id_categoria + ">" + res[i].categoria + "</option>";
                } else {
                    contenidoHTML += "<option value=" + res[i].id_categoria + ">" + res[i].categoria + "</option>";
                }
            }
            //agrega el contendio html
            $("#categoria").html(contenidoHTML);
            $("#Ecategoria").html(contenidoHTML);
        });
    }

    function cargarAutor(id) {
        $.getJSON('LibroControlador', {accion: "cargarAutor"}, function (res) {
            console.log(res);
            //variable de tipo String donde se concatena todo el contenido html
            var contenidoHTML = "<option value=''>Selecciona</option>";
            for (var i = 0; i < res.length; i++) {
                if (id === res[i].id_autor) {
                    contenidoHTML += "<option selected value=" + res[i].id_autor + ">" + res[i].nombre_autor + "</option>";
                } else {
                    contenidoHTML += "<option value=" + res[i].id_autor + ">" + res[i].nombre_autor + "</option>";
                }
            }
            //agrega el contendio html
            $("#autor").html(contenidoHTML);
            $("#Eautor").html(contenidoHTML);
        });
    }

    /*====================================================================
     //FUNCION QUE SE EJECUTA AL HACER CLICK EN EL BOTON GUARDAR DATOS DEL MODAL
        REGISTRAR LIBROS QUE TIENE COMO ID agregarLibro
     //====================================================================
    
     */
//
//    $("#agregarLibro").click(function () {
//
//        $.ajax({
//            type: "POST",
//            url: "LibroControlador",
//            data: {
//                
//                accion: "insertar",
//                // Estructura-> nombre de variable: valor de variable
//                //guarda en las variables los datos de los select y los inputs
//                isbn: $("#isbn").val(),
//                anho: $("#anho").val(),
//                titulo: $("#titulo").val(),
//                editorial: $("#editorial").val(),
//                autor: $("#autor").val(),
//                genero: $("#genero").val(),
//                categoria: $("#categoria").val(),
//                estado: $("#estado").val()},
//            async: true,
//            cache: false,
//            success: function (resp) {
//                // en resp se guarda lo que el servlet retornó, en este caso retorna true o false
//                if (resp) {
//                    swal({
//                        position: 'center',
//                        type: 'success',
//                        title: 'Libro Agregado!',
//                        showConfirmButton: false,
//                        timer: 1500
//                    });
//                } else {
//                    swal({
//                        position: 'center',
//                        type: 'error',
//                        title: 'No se pudo agregar!',
//                        showConfirmButton: false,
//                        timer: 1500
//                    });
//                }
//
//                console.log("Respuesta al agregar el libro " + resp);
//                //Vacia los inputs
//                $("#isbn").val("");
//                $("#anho").val("");
//                $("#titulo").val("");
//                $("#estado").val("");
//                //Vuelve a cargar los select con todos los datos
//                cargarEditorial(0);
//                cargarGenero(0);
//                cargarAutor(0);
//                cargarCategoria(0);
//                //Recarga la tabla
//                $('#TablaLibros').DataTable().ajax.reload();
//            }
//
//        });
//
//    });

    /* =======================================================================
     *  ELIMINAR LIBRO
     * =======================================================================
     */

//    $(document).on('click', '.eliminar', function () {
//        var id = $(this).attr("value");
//        var opcion = confirm("Está seguro que desea eliminar.?");
//        if (opcion == true) {
//            $.getJSON('LibroControlador', {accion: "eliminar", id: id}, function (res) {
//                if (res) {
//                    swal({
//                        position: 'center',
//                        type: 'success',
//                        title: 'Libro Eliminado!',
//                        showConfirmButton: false,
//                        timer: 1500
//                    });
//                } else {
//                    swal({
//                        position: 'center',
//                        type: 'error',
//                        title: 'No se pudo eliminar!',
//                        showConfirmButton: false,
//                        timer: 1500
//                    });
//                }
//                console.log(res);
//                //Recarga la tabla
//                $('#TablaLibros').DataTable().ajax.reload();
//            });
//        }
//
//    });

    /* =======================================================================
     *  CARGAR DATOS DEL LIBRO
     * =======================================================================
     */
//    $(document).on('click', '.actualizar', function () {
//        var id = $(this).attr("value");
//        
//        $.getJSON('LibroControlador', {accion: "cargarDatos", id: id},
//                function (res) {
//                    $("#actualizarDatos").val(id);
//                    $("#Eisbn").val(res.ISBN);
//                    $("#Eanho").val(res.año_publicacion);
//                    $("#Etitulo").val(res.titulo);
//                    $("#Eestado").val(res.estado);
//                    cargarEditorial(res.id_editorial);
//                    cargarGenero(res.id_genero);
//                    cargarAutor(res.id_autor);
//                    cargarCategoria(res.categoria);
//                });
//    });

    /* =======================================================================
     *  ACTUALIZAR DATOS DEL LIBRO
     * =======================================================================
     */

//    $("#actualizarDatos").click(function () {
//
//        $.ajax({
//            type: "POST",
//            url: "LibroControlador",
//            data: {
//                accion: "actualizar",
//                id: $("#actualizarDatos").val(),
//                isbn: $("#Eisbn").val(),
//                anho: $("#Eanho").val(),
//                titulo: $("#Etitulo").val(),
//                editorial: $("#Eeditorial").val(),
//                autor: $("#Eautor").val(),
//                genero: $("#Egenero").val(),
//                categoria: $("#Ecategoria").val(),
//                estado: $("#Eestado").val()},
//            async: true,
//            cache: false,
//            success: function (resp) {
//                // Si la longitud de resp es igual a 6 es verdadero
//                if (resp) {
//                    swal({
//                        position: 'center',
//                        type: 'success',
//                        title: 'Se han actualizado los datos!',
//                        showConfirmButton: false,
//                        timer: 1500
//                    });
//                } else {
//                    swal({
//                        position: 'center',
//                        type: 'error',
//                        title: 'No se pudo actualizar!',
//                        showConfirmButton: false,
//                        timer: 1500
//                    });
//                }
//
//                console.log("Respuesta al actualizar el libro " + resp);
//                $("#Eisbn").val("");
//                $("#Eanho").val("");
//                $("#Etitulo").val("");
//                $("#Eestado").val("");
//                cargarEditorial(0);
//                cargarGenero(0);
//                cargarAutor(0);
//                //Recarga la tabla
//                $('#TablaLibros').DataTable().ajax.reload();
//            }
//
//        });
//
//    });
});


