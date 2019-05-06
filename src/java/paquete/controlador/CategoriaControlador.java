
package paquete.controlador;

import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import paquete.dao.CategoriaDAO;
import paquete.modelo.Categoria;

/**
 *
 * @author USUARIO
 */
public class CategoriaControlador extends HttpServlet {

    private CategoriaDAO categoriaDAO;
    public CategoriaControlador() {
        super();
        categoriaDAO= new CategoriaDAO();
    }

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String accion="";
            accion=request.getParameter("accion");
            
            if (accion.equalsIgnoreCase("cargarTabla")) {
                JsonObject es = new JsonObject();
                es.add("datos", categoriaDAO.listarTablaCategoria());
                out.print(es);
            }
            if (accion.equalsIgnoreCase("cargarBusqueta")) {
                JsonObject es = new JsonObject();
                String nombre=request.getParameter("aux");
                es.add("datos", categoriaDAO.listarTablaCategoriaExacta(nombre));
                out.print(es);
            }
            
            if (accion.equalsIgnoreCase("insertar")) {
                Categoria c= new Categoria();
                String nombre=request.getParameter("autor");
                c.setCategoria(nombre);
                out.print(categoriaDAO.agregarCategoria(c));
            }
            if (accion.equalsIgnoreCase("eliminar")) {
                int id=Integer.valueOf(request.getParameter("id"));
                out.print(categoriaDAO.eliminarCategoria(id));
            }
            if (accion.equalsIgnoreCase("cargarDatos")) {
                int id=Integer.valueOf(request.getParameter("id"));
                out.print(categoriaDAO.cargarDatosCategoria(id));
            }
            if (accion.equalsIgnoreCase("actualizar")) {
                Categoria c= new Categoria();
                int id=Integer.valueOf(request.getParameter("id"));
                String nombre=request.getParameter("categoria");
                c.setCategoria(nombre);
                c.setId_categoria(id);
                out.print(categoriaDAO.actualizarCategoria(c));
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
