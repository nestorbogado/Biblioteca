
package paquete.controlador;

import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import paquete.dao.CarreraDAO;

/**
 *
 * @author USUARIO
 */
public class CarreraControlador extends HttpServlet {

    private CarreraDAO carreraDAO;
    public CarreraControlador() {
        super();
        carreraDAO = new CarreraDAO();
    }

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
             String accion="";
            accion=request.getParameter("accion");
            
            if (accion.equalsIgnoreCase("cargarTabla")) {
                JsonObject es = new JsonObject();
                es.add("datos", carreraDAO.listarTablaCarreras());
                out.print(es);
            }
            if (accion.equalsIgnoreCase("cargarFacultad")) {
                out.print(carreraDAO.listarFacultad(0));
            }
            
            if (accion.equalsIgnoreCase("insertar")) {
                String nombre=request.getParameter("carrera");
                int facultad=Integer.valueOf(request.getParameter("facultad"));
                out.print(carreraDAO.agregarCarrera(nombre,facultad));
            }
            if (accion.equalsIgnoreCase("eliminar")) {
                int id=Integer.valueOf(request.getParameter("id"));
                out.print(carreraDAO.eliminarCarrera(id));
            }
            if (accion.equalsIgnoreCase("cargarDatos")) {
                int id=Integer.valueOf(request.getParameter("id"));
                out.print(carreraDAO.cargarDatosCarreras(id));
            }
            if (accion.equalsIgnoreCase("actualizar")) {
                int id=Integer.valueOf(request.getParameter("id"));
                String nombre=request.getParameter("carrera");
                int facultad=Integer.valueOf(request.getParameter("facultad"));
                out.print(carreraDAO.actualizarCarrera(id, nombre,facultad));
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
