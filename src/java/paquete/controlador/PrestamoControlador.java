package paquete.controlador;

import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import paquete.dao.AlumnosDAO;
import paquete.dao.PrestamoDAO;
import paquete.modelo.Prestamo;

/**
 *
 * @author USUARIO
 */
public class PrestamoControlador extends HttpServlet {

    private PrestamoDAO prestamoDAO;

    public PrestamoControlador() {
        super();
        prestamoDAO = new PrestamoDAO();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String accion = "";
            accion = request.getParameter("accion");

            if (accion.equalsIgnoreCase("cargarAlumnos")) {
                out.print(prestamoDAO.ListaAlumnos(0));
            }
            if (accion.equalsIgnoreCase("cargarMaterial")) {
                out.print(prestamoDAO.ListaLibros(0));
            }
            if (accion.equalsIgnoreCase("verificarFechaRenovacion")) {
                Prestamo p = new Prestamo();

                p.setId_alumno(Integer.valueOf(request.getParameter("alumno")));
                p.setFecha_prestamo(request.getParameter("fechaPrestamo"));
                out.print(prestamoDAO.verificarFechaRenovacion(p));
            }

            if (accion.equalsIgnoreCase("insertar")) {
                Prestamo p = new Prestamo();

                p.setId_alumno(Integer.valueOf(request.getParameter("alumno")));
                p.setId_libro(Integer.valueOf(request.getParameter("libro")));
                p.setFecha_prestamo(request.getParameter("fechaPrestamo"));
                if (prestamoDAO.agregarPrestamo(p)>0) {
                     out.print(prestamoDAO.actualizarEstadoMaterial(p.getId_libro(), "No Disponible"));
                }
               
            }

            if (accion.equalsIgnoreCase("eliminar")) {
                //Guarda en una variable auxiliar los parametros que recibe
                int id = Integer.valueOf(request.getParameter("id"));
                if (prestamoDAO.ActualizarEstadoDevolucionMaterial(id,"Disponible")>0) {
                    if (prestamoDAO.eliminarPrestamo(id)>0) {
                        out.print(1);
                    }else{
                       out.print(prestamoDAO.ActualizarEstadoDevolucionMaterial(id,"No Disponible"));  
                    }
                   
                }else{
                    out.print(0);
                }
               
            }
            if (accion.equalsIgnoreCase("cargarDatos")) {
//                Guarda en una variable auxiliar los parametros que recibe
                int id = Integer.valueOf(request.getParameter("id"));

                out.print(prestamoDAO.cargarDatosPrestamo(id));
            }

            if (accion.equalsIgnoreCase("actualizarPrestamo")) {
                //Se crea una variable auxiliar l de tipo Objeto Libro.java(Modelo)
                Prestamo p = new Prestamo();

                //Guarda en el modelo los parametros que recibe
                p.setId_libro(Integer.valueOf(request.getParameter("id_libro")));
                p.setId_prestamo(Integer.valueOf(request.getParameter("id_prestamo")));
                p.setFecha_prestamo(request.getParameter("fechaPrestamo"));
                if (prestamoDAO.actualizarEstadoMaterial(p.getId_libro(),"No Disponible")>0) {
                   out.print(prestamoDAO.actualizarPrestamo(p));  
                }else{
                    out.print(0);
                }
                   
                
            }
            if (accion.equalsIgnoreCase("cargarTablaPENDIENTE")) {
                JsonObject es = new JsonObject();
                es.add("datos", prestamoDAO.listarTablaPrestamosPendientes());
                out.print(es);
            }
            if (accion.equalsIgnoreCase("cargarTablaHECHAS")) {
                JsonObject es = new JsonObject();
                es.add("datos", prestamoDAO.listarTablaPrestamosDevueltos());
                out.print(es);
            }
            if (accion.equalsIgnoreCase("cargarTablaTODOS")) {
                JsonObject es = new JsonObject();
                es.add("datos", prestamoDAO.listarTablaPrestamosTodos());
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
