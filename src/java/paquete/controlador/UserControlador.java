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
import paquete.dao.UsuarioDAO;
import paquete.modelo.Usuario;

/**
 *
 * @author USUARIO
 */
public class UserControlador extends HttpServlet {

    private UsuarioDAO usuarioDAO;

    public UserControlador() {
        super();
        usuarioDAO = new UsuarioDAO();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String accion = "";
            accion = request.getParameter("accion");

            if (accion.equalsIgnoreCase("insertar")) {
                Usuario u = new Usuario();

                u.setNombre(request.getParameter("nombre"));
                u.setApellido(request.getParameter("apellido"));
                u.setUsuario(request.getParameter("usuario"));
                u.setContraseña(request.getParameter("contrasenha"));
                u.setTipo_usuario(Integer.valueOf(request.getParameter("tipoU")));
                u.setEstado(Integer.valueOf(request.getParameter("estado")));
                out.print(usuarioDAO.agregarUsuario(u));
            }

            if (accion.equalsIgnoreCase("cargarEstado")) {
                out.print(usuarioDAO.listarEstado(1));
            }
            if (accion.equalsIgnoreCase("cargarTipo")) {
                out.print(usuarioDAO.tipoUsuario(1));
            }
            if (accion.equalsIgnoreCase("cargarTabla")) {
                JsonObject es = new JsonObject();
                es.add("datos", usuarioDAO.listarTablaUsuario());
                out.print(es);
            }
            if (accion.equalsIgnoreCase("cargarDatos")) {
                int id = Integer.valueOf(request.getParameter("id"));
                out.print(usuarioDAO.cargarDatos(id));
            }
            if (accion.equalsIgnoreCase("actualizar")) {
                Usuario u = new Usuario();
                u.setId_usuario(Integer.valueOf(request.getParameter("id")));
                u.setNombre(request.getParameter("nombre"));
                u.setApellido(request.getParameter("apellido"));
                u.setUsuario(request.getParameter("usuario"));
                u.setContraseña(request.getParameter("contrasenha"));
                u.setTipo_usuario(Integer.valueOf(request.getParameter("tipoU")));
                u.setEstado(Integer.valueOf(request.getParameter("estado")));
                if (u.getContraseña().length() > 0) {
                    //actualiza datos y contraseña
                    if (u.getContraseña().length() > 7) {
                        System.out.println("ACTUALIZAR CONTRASEÑA");
                        out.print(usuarioDAO.actualizarUsuario(u));
                    }else{
                         out.print(2);
                    }

                } else {
                    System.out.println("ACTUALIZAR SIN CONTRASEÑA");
                    // actualiza solo datos
                    out.print(usuarioDAO.actualizarUsuarioSinContraseña(u));
                }

            }
            if (accion.equalsIgnoreCase("anularUsuario")) {
                Usuario u = new Usuario();
                //int id=Integer.valueOf(request.getParameter("id"));
                u.setId_usuario(Integer.valueOf(request.getParameter("id")));
                u.setEstado(0);
                out.print(usuarioDAO.anularUsuario(u));
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
