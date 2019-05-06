
package paquete.controlador;

import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import paquete.dao.GeneroDAO;

/**
 *
 * @author USUARIO
 */
public class GeneroControlador extends HttpServlet {

    private GeneroDAO generoDAO;
    public GeneroControlador() {
        super();
        generoDAO = new GeneroDAO();
    }

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String accion="";
            accion=request.getParameter("accion");
            
            if (accion.equalsIgnoreCase("cargarTabla")) {
                JsonObject es = new JsonObject();
                es.add("datos", generoDAO.listarTablaGeneros());
                out.print(es);
            }
            
            if (accion.equalsIgnoreCase("insertar")) {
                String nombre=request.getParameter("genero");
                out.print(generoDAO.agregarGenero(nombre));
            }
            if (accion.equalsIgnoreCase("eliminar")) {
                int id=Integer.valueOf(request.getParameter("id"));
                out.print(generoDAO.eliminarGenero(id));
            }
            if (accion.equalsIgnoreCase("cargarDatos")) {
                int id=Integer.valueOf(request.getParameter("id"));
                out.print(generoDAO.cargarDatosGeneros(id));
            }
            if (accion.equalsIgnoreCase("actualizar")) {
                int id=Integer.valueOf(request.getParameter("id"));
                String nombre=request.getParameter("genero");
                out.print(generoDAO.actualizarGenero(id, nombre));
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
