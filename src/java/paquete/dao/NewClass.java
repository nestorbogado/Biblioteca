/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete.dao;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import static javax.swing.JOptionPane.showInputDialog;

/**
 *
 * @author USUARIO
 */
public class NewClass {

    public static void main(String args[]) throws Exception {

        

//        Fecha actual en formato completo:
//        Tue Sep 23 01:18:48 CEST 2014
        Date fechaActual = new Date();
        System.out.println(fechaActual);
        System.out.println("---------------------------------------------");
        
        //Formateando la fecha:
        DateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
        DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        Calendar FA=Calendar.getInstance();
        FA.setTime(fechaActual);
        int dia_actual=FA.get(Calendar.DAY_OF_MONTH);
        int mes_actual=FA.get(Calendar.MONTH) + 1;
        System.out.println("Son las: "+formatoHora.format(fechaActual)+" de fecha: "+formatoFecha.format(fechaActual));
        
        Date renovacion = formatoFecha.parse("2019-05-20");
        Calendar cal = Calendar.getInstance();
        cal.setTime(renovacion);
        //Fecha actual desglosada:
        int año = cal.get(Calendar.YEAR);
        int mes = cal.get(Calendar.MONTH) + 1;
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        
        
        Date fecha_actual = formatoFecha.parse("2020-01-10");
        Calendar fecha_a = Calendar.getInstance();
        fecha_a.setTime(fecha_actual);
        //Fecha actual desglosada:
        int años = fecha_a.get(Calendar.YEAR);
        int mess = fecha_a.get(Calendar.MONTH) + 1;
        int diaa = fecha_a.get(Calendar.DAY_OF_MONTH);
        
        
        if ((año<=años) && (mess==1 || mess==2 || mess==3)) {
            System.out.println("Puede prestar Fecha Actual: "+ diaa + "/" + (mess) + "/" + años);
        }else{
            System.out.println("NO Puede prestar Fecha Actual ");
        }
        if (año==años) {
            System.out.println("Puede prestar Fecha Actual: "+ diaa + "/" + (mess) + "/" + años);
        }else{
             System.out.println("NO Puede prestar Fecha Actual ");
        }
    }

}
