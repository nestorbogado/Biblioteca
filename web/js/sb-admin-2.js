/*!
 * Start Bootstrap - SB Admin 2 v3.3.7+1 (http://startbootstrap.com/template-overviews/sb-admin-2)
 * Copyright 2013-2016 Start Bootstrap
 * Licensed under MIT (https://github.com/BlackrockDigital/startbootstrap/blob/gh-pages/LICENSE)
 */
$(function () {
    $('#side-menu').metisMenu();


    $(".NUMEROS").keypress(function validarSiNumero(e) {
        var key = window.event ? e.keyCode : e.which;
        if ((48 <= key && key <= 57) || (key == 0) || (key == 8))
        {
            return true;
        } else {
            return false;
        } // -

    });

    $(".LETRAS").keypress(function validarSiLetras(e) {
        var key = window.event ? e.keyCode : e.which;
//        console.log(key.charCode);
        if ((key >= 65 && key <= 90)  //letras minusculas
                || (key >= 97 && key <= 122)//letras mayusculas
                || (key == 32) //espacio
                || (key == 8) //retroceso
                || (key == 241) //ñ
                || (key == 209) //Ñ
                || (key == 225) //á
                || (key == 233) //é
                || (key == 237) //í
                || (key == 243) //ó
                || (key == 250) //ú
                || (key == 193) //Á
                || (key == 201) //É
                || (key == 205) //Í
                || (key == 211) //Ó
                || (key == 218) //Ú
                )
        {
            return true;
        } else {
            return false;
        } // -

    });

    $(".SIMBOLOS").keypress(function validarSiSimbolos(e) {
        var key = window.event ? e.keyCode : e.which;
//        console.log(key.charCode);
        if ((key >= 32 && key <= 47)  //letras minusculas
                || (key >= 58 && key <= 64)//letras mayusculas
                || (key >= 91 && key <= 96)
                || (key == 32) //espacio
                || (key == 8) //retroceso
                || (key >= 123 && key <= 126)
                || (key == 218) //¿
                || (key == 218) //¡
                )
        {
            return true;
        } else {
            return false;
        } // -

    });

    $(".MAYUSCULAS").on("keypress", function () {
        $input = $(this);
        setTimeout(function () {
            $input.val($input.val().toUpperCase());
        }, 50);
    })
});

//Loads the correct sidebar on window load,
//collapses the sidebar on window resize.
// Sets the min-height of #page-wrapper to window size
$(function () {
    $(window).bind("load resize", function () {
        var topOffset = 50;
        var width = (this.window.innerWidth > 0) ? this.window.innerWidth : this.screen.width;
        if (width < 768) {
            $('div.navbar-collapse').addClass('collapse');
            topOffset = 100; // 2-row-menu
        } else {
            $('div.navbar-collapse').removeClass('collapse');
        }

        var height = ((this.window.innerHeight > 0) ? this.window.innerHeight : this.screen.height) - 1;
        height = height - topOffset;
        if (height < 1)
            height = 1;
        if (height > topOffset) {
            $("#page-wrapper").css("min-height", (height) + "px");
        }
    });

    var url = window.location;
    // var element = $('ul.nav a').filter(function() {
    //     return this.href == url;
    // }).addClass('active').parent().parent().addClass('in').parent();
    var element = $('ul.nav a').filter(function () {
        return this.href == url;
    }).addClass('active').parent();

    while (true) {
        if (element.is('li')) {
            element = element.parent().addClass('in').parent();
        } else {
            break;
        }
    }
});
