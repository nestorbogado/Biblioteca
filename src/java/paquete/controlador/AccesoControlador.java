
package paquete.controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import paquete.dao.AccesoDAO;
import paquete.modelo.Usuario;

/**
 *
 * @author USUARIO
 */
public class AccesoControlador extends HttpServlet {

      private AccesoDAO accesoDAO;

    public AccesoControlador() {
        super();
        accesoDAO = new AccesoDAO();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String accion = "";
            accion = request.getParameter("accion");

            String usuario = request.getParameter("usuario");
            String contraseña = request.getParameter("contrasenha");
            Usuario u = new Usuario();
            if (accion.equalsIgnoreCase("insertar")) {
//                String forward = INICIO;

             
                u = accesoDAO.autenticacion(usuario, contraseña);
                if (u.getId_usuario() > 0) {
                    HttpSession session_actual = request.getSession(true);
                    session_actual.setMaxInactiveInterval(-1);
                    session_actual.setAttribute("usuario", usuario);
                    session_actual.setAttribute("nombre", u.getNombre());
                    session_actual.setAttribute("id_usuario", u.getId_usuario());
                    session_actual.setAttribute("tipo", u.getTipo_usuario());
                    out.print(0);
                } else {
                    out.print(1);
                }
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
