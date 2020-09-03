$(document).ready(function () {
    
   

    $(".form-control").keypress(function (e) {
        if (e.which == 13) {
            obtenerDatos();
        }
    });

    $("#iniciarSesion").click(function () {
//        
            obtenerDatos();
    });

    function obtenerDatos() {
//        alert($("#usuario").val());
        $.ajax({
            type: "POST",
            url: "AccesoControlador",
            data: {
                accion: "insertar",

                usuario: $("#usuario").val(),
                contrasenha: $("#contrasenha").val()},
            async: true,
            cache: false,
            success: function (resp) {
//                    alert(resp);
                // en resp se guarda lo que el servlet retornó, en este caso retorna true o false
                if (resp == 1) {
//                        alert(1);
                    
                    swal({
                        position: 'center',
                        type: 'error',
                        title: 'Usuario y/o Contraseña inválida!',
                        showConfirmButton: false,
                        timer: 1500
                    });
                } else {
                    location.href = "PRESTAMO.jsp";
                }
            }

        });
    }
});

