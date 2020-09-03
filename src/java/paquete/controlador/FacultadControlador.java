
package paquete.controlador;

import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import paquete.dao.FacultadDAO;

/**
 *
 * @author USUARIO
 */
public class FacultadControlador extends HttpServlet {

    private FacultadDAO facultadDAO;
    public FacultadControlador() {
        super();
        facultadDAO = new FacultadDAO();
    }

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
             String accion="";
            accion=request.getParameter("accion");
            
            if (accion.equalsIgnoreCase("cargarTabla")) {
                JsonObject es = new JsonObject();
                es.add("datos", facultadDAO.listarTablaFacultad());
                out.print(es);
            }
            
            if (accion.equalsIgnoreCase("insertar")) {
                String descripcion=request.getParameter("descripcion");
                out.print(facultadDAO.agregarFacultad(descripcion));
            }
            if (accion.equalsIgnoreCase("eliminar")) {
                int id=Integer.valueOf(request.getParameter("id"));
                out.print(facultadDAO.eliminarFacultad(id));
            }
            if (accion.equalsIgnoreCase("cargarDatos")) {
                int id=Integer.valueOf(request.getParameter("id"));
                out.print(facultadDAO.cargarDatosFacultad(id));
            }
            if (accion.equalsIgnoreCase("actualizar")) {
                int id=Integer.valueOf(request.getParameter("id"));
                String facultad=request.getParameter("facultad");
                out.print(facultadDAO.actualizarFacultad(id, facultad));
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
