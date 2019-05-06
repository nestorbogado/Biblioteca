
package paquete.controlador;

import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import paquete.dao.AlumnosDAO;
import paquete.dao.CarreraDAO;
import paquete.modelo.Alumno;

/**
 *
 * @author USUARIO
 */
public class AlumnoControlador extends HttpServlet {

    private AlumnosDAO alumnoDAO;
    private CarreraDAO carreraDAO;
    public AlumnoControlador() {
        super();
        alumnoDAO= new AlumnosDAO();
        carreraDAO= new CarreraDAO();
    }

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String accion="";
            accion=request.getParameter("accion");
            
            if (accion.equalsIgnoreCase("cargarTabla")) {
                JsonObject es = new JsonObject();
                es.add("datos", alumnoDAO.listarTablaAlumnos());
                out.print(es);
            }
            if (accion.equalsIgnoreCase("cargarCarrera")) {
                
                out.print(carreraDAO.listarCarrera());
            }
            if (accion.equalsIgnoreCase("insertar")) {
                Alumno a=new Alumno();
                a.setNombre(request.getParameter("nombre"));
                a.setApellido(request.getParameter("apellido"));
                a.setCi(request.getParameter("ci"));
                a.setId_carrera(Integer.valueOf(request.getParameter("carrera")));
                out.print(alumnoDAO.agregarAlumno(a));
            }
            if (accion.equalsIgnoreCase("eliminar")) {
                int id=Integer.valueOf(request.getParameter("id"));
                out.print(alumnoDAO.eliminarAlumno(id));
            }
            if (accion.equalsIgnoreCase("cargarDatos")) {
                int id=Integer.valueOf(request.getParameter("id"));
                out.print(alumnoDAO.cargarDatosAlumno(id));
            }
            if (accion.equalsIgnoreCase("actualizar")) {
                Alumno a=new Alumno();
                a.setNombre(request.getParameter("nombre"));
                a.setApellido(request.getParameter("apellido"));
                a.setCi(request.getParameter("ci"));
                a.setId_carrera(Integer.valueOf(request.getParameter("carrera")));
                a.setId_alumno(Integer.valueOf(request.getParameter("id")));
                out.print(alumnoDAO.actualizarAlumno(a));
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
