package paquete.controlador;

import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import paquete.dao.AlumnosDAO;
import paquete.dao.LibroDAO;
import paquete.dao.PrestamoDAO;
import paquete.modelo.Libro;
import paquete.modelo.Prestamo;

/**
 *
 * @author USUARIO
 */
public class PrestamoControlador extends HttpServlet {

    private PrestamoDAO prestamoDAO;
    private AlumnosDAO alumnoDAO;
    private LibroDAO libroDAO;

    public PrestamoControlador() {
        super();
        prestamoDAO = new PrestamoDAO();
        alumnoDAO = new AlumnosDAO();
        libroDAO = new LibroDAO();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String accion = "";
            accion = request.getParameter("accion");

            if (accion.equalsIgnoreCase("cargarAlumnos")) {
                out.print(alumnoDAO.listarAlumnos());
            }
            if (accion.equalsIgnoreCase("cargarLibros")) {
                out.print(libroDAO.listarLibros());
            }

            if (accion.equalsIgnoreCase("insertar")) {
                Prestamo p = new Prestamo();

                p.setId_alumno(Integer.valueOf(request.getParameter("alumno")));
                p.setId_libro(Integer.valueOf(request.getParameter("libro")));
                p.setDias_prestados(Integer.valueOf(request.getParameter("cantidadDias")));
                p.setFecha_prestamo(request.getParameter("fechaPrestamo"));

                out.print(prestamoDAO.agregarPrestamo(p));
            }

            if (accion.equalsIgnoreCase("eliminar")) {
                //Guarda en una variable auxiliar los parametros que recibe
                int id = Integer.valueOf(request.getParameter("id"));

                out.print(prestamoDAO.eliminarPrestamo(id));
            }
            if (accion.equalsIgnoreCase("cargarDatos")) {
                //Guarda en una variable auxiliar los parametros que recibe
                int id = Integer.valueOf(request.getParameter("id"));

                out.print(prestamoDAO.cargarDatosLibro(id));
            }

            if (accion.equalsIgnoreCase("actualizar")) {
                //Se crea una variable auxiliar l de tipo Objeto Libro.java(Modelo)
                Prestamo p = new Prestamo();

                //Guarda en el modelo los parametros que recibe
                p.setId_prestamo(Integer.valueOf(request.getParameter("id_prestamo")));
                p.setFecha_devolucion(request.getParameter("fechaDevolucion"));
                p.setDias_reales(Integer.valueOf(request.getParameter("cantidadDiasReales")));
                p.setDiferencia(Integer.valueOf(request.getParameter("diferencia")));
                p.setMulta(request.getParameter("multa"));

                out.print(prestamoDAO.actualizarPrestamo(p));
            }
            if (accion.equalsIgnoreCase("cargarTabla")) {
                JsonObject es = new JsonObject();
                es.add("datos", prestamoDAO.listarTablaPrestamos());
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
