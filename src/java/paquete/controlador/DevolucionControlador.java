/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete.controlador;

import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import paquete.dao.PrestamoDAO;
import paquete.modelo.Prestamo;

/**
 *
 * @author USUARIO
 */
public class DevolucionControlador extends HttpServlet {

    private PrestamoDAO prestamoDAO;
    public DevolucionControlador() {
        super();
        prestamoDAO = new PrestamoDAO();
    }

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String accion = "";
            accion = request.getParameter("accion");

            if (accion.equalsIgnoreCase("cargarAlumnos")) {
                out.print(prestamoDAO.ListaAlumnos(0));
            }
            if (accion.equalsIgnoreCase("cargarMaterial")) {
                out.print(prestamoDAO.ListaLibros(0));
            }
            
            if (accion.equalsIgnoreCase("cargarDatos")) {
//                Guarda en una variable auxiliar los parametros que recibe
                int id = Integer.valueOf(request.getParameter("id"));

                out.print(prestamoDAO.cargarDatosPrestamo(id));
            }
            if (accion.equalsIgnoreCase("devolver")) {
                //Se crea una variable auxiliar l de tipo Objeto Libro.java(Modelo)
                Prestamo p = new Prestamo();
                p.setId_prestamo(Integer.valueOf(request.getParameter("id_prestamo")));
                p.setFecha_devolucion(request.getParameter("fechaDevolucion"));
                if (prestamoDAO.ActualizarEstadoDevolucionMaterial(p.getId_prestamo(),"Disponible")>0) {
                    if (prestamoDAO.actualizarDevolucion(p)>0) {
                         out.print(1);
                    }else{
                        prestamoDAO.ActualizarEstadoDevolucionMaterial(p.getId_prestamo(),"No Disponible");
                         out.print(0);
                    }
                } else{
                    out.print(0);
                }
                
            }

            if (accion.equalsIgnoreCase("actualizar")) {
                //Se crea una variable auxiliar l de tipo Objeto Libro.java(Modelo)
                Prestamo p = new Prestamo();

                //Guarda en el modelo los parametros que recibe
                p.setId_libro(Integer.valueOf(request.getParameter("id_libro")));
                p.setId_prestamo(Integer.valueOf(request.getParameter("id_prestamo")));
                p.setFecha_devolucion(request.getParameter("fechaDevolucion"));
                if (prestamoDAO.actualizarDevolucion(p)>0) {
                   out.print(prestamoDAO.actualizarEstadoMaterial(p.getId_libro(), "Disponible")); 
                }
            }
            if (accion.equalsIgnoreCase("cargarTablaDevolucionPENDIENTE")) {
                JsonObject es = new JsonObject();
                es.add("datos", prestamoDAO.listarTablaDevolucionPendientes());
                out.print(es);
            }
            if (accion.equalsIgnoreCase("cargarTablaDevolucionHECHAS")) {
                JsonObject es = new JsonObject();
                es.add("datos", prestamoDAO.listarTablaDevolucionHechas());
                out.print(es);
            }
            if (accion.equalsIgnoreCase("cargarTablaDevolucionTODAS")) {
                JsonObject es = new JsonObject();
                es.add("datos", prestamoDAO.listarTablaDevolucionTodos());
                out.print(es);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
