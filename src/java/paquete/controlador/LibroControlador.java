package paquete.controlador;

import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import paquete.dao.AutorDAO;
import paquete.dao.CategoriaDAO;
import paquete.dao.EditorialDAO;
import paquete.dao.GeneroDAO;
import paquete.dao.LibroDAO;
import paquete.modelo.Libro;

public class LibroControlador extends HttpServlet {

    private LibroDAO libroDAO;
    private GeneroDAO generoDAO;
    private AutorDAO autorDAO;
    private EditorialDAO editorialDAO;
    private CategoriaDAO categoriaDAO;
    public LibroControlador() {
        super();
        libroDAO = new LibroDAO();
        generoDAO = new GeneroDAO();
        autorDAO = new AutorDAO();
        editorialDAO = new EditorialDAO();
        categoriaDAO= new CategoriaDAO();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String accion = "";
            accion = request.getParameter("accion");
            
            if (accion.equalsIgnoreCase("cargarCategoria")) {
                //editorialDAO.listarEditorial() esta funcion retorna una lista de editoriales 
                // id_editoria|nombre_editorial
                out.print(categoriaDAO.listarCategoria());
            }

            /*====================================================================
            //ACCION DE CARGAR LISTAR LAS EDITORIALES EN LA VISTA LIBROS.JSP
            //====================================================================
             */
            if (accion.equalsIgnoreCase("cargarEditorial")) {
                //editorialDAO.listarEditorial() esta funcion retorna una lista de editoriales 
                // id_editoria|nombre_editorial
                out.print(editorialDAO.listarEditorial());
            }

            /*====================================================================
            //ACCION DE CARGAR LISTAR LOS GENEROS EN LA VISTA LIBROS.JSP
            //====================================================================
             */
            if (accion.equalsIgnoreCase("cargarGenero")) {
                //generoDAO.listarGenero() esta funcion retorna una lista de GENEROS 
                // id_genero|nombre_genero
                out.print(generoDAO.listarGenero());
            }

            /*====================================================================
            //ACCION DE CARGAR LISTAR LOS AUTORES EN LA VISTA LIBROS.JSP
            //====================================================================
             */
            if (accion.equalsIgnoreCase("cargarAutor")) {
                //autorDAO.listarAutor() esta funcion retorna una lista de GENEROS 
                // id_autor|nombre_autor
                out.print(autorDAO.listarAutor());
            }

            /*====================================================================
            //ACCION DE PARA INSERTAR LOS DATOS DEL LIBRO
            //====================================================================
             */
            if (accion.equalsIgnoreCase("insertar")) {
                //Se crea una variable auxiliar l de tipo Objeto Libro.java(Modelo)
                Libro l = new Libro();
                //Guarda en el modelo los parametros que recibe
                l.setIsbn(request.getParameter("isbn"));
                l.setAño_publicacion(request.getParameter("anho"));
                l.setTitulo(request.getParameter("titulo"));
                l.setId_editorial(Integer.valueOf(request.getParameter("editorial")));
                l.setId_autor(Integer.valueOf(request.getParameter("autor")));
                l.setId_genero(Integer.valueOf(request.getParameter("genero")));
                l.setEstado(request.getParameter("estado"));
                l.setCategoria(Integer.valueOf(request.getParameter("categoria")));
                //libroDAO.agregarLibro(l) esta funcion retorna una valor booleano
                // se envia como parametro la variable Objeto l
                out.print(libroDAO.agregarLibro(l));
            }

            /*====================================================================
            //ACCION DE PARA ELIMINAR LOS DATOS DEL LIBRO
            //====================================================================
             */
            if (accion.equalsIgnoreCase("eliminar")) {
                //Guarda en una variable auxiliar los parametros que recibe
                int id = Integer.valueOf(request.getParameter("id"));
                //libroDAO.eliminarLibro(id) esta funcion retorna una valor booleano
                // se envia como parametro la variable auxiliar id
                out.print(libroDAO.eliminarLibro(id));
            }

            /*====================================================================
            //ACCION DE PARA CARGAR LOS DATOS DEL LIBRO
            //====================================================================
             */
            if (accion.equalsIgnoreCase("cargarDatos")) {
                //Guarda en una variable auxiliar los parametros que recibe
                int id = Integer.valueOf(request.getParameter("id"));
                //libroDAO.cargarDatosLibro(id) esta funcion retorna un Objeto con los datos del libro
                // se envia como parametro la variable auxiliar id
                out.print(libroDAO.cargarDatosLibro(id));
            }

            /*====================================================================
            //ACCION DE PARA ACTUALIZAR LOS DATOS DEL LIBRO
            //====================================================================
             */
            if (accion.equalsIgnoreCase("actualizar")) {
                //Se crea una variable auxiliar l de tipo Objeto Libro.java(Modelo)
                Libro l = new Libro();
                //Guarda en el modelo los parametros que recibe
                l.setId_libro(Integer.valueOf(request.getParameter("id")));
                l.setIsbn(request.getParameter("isbn"));
                l.setAño_publicacion(request.getParameter("anho"));
                l.setTitulo(request.getParameter("titulo"));
                l.setId_editorial(Integer.valueOf(request.getParameter("editorial")));
                l.setId_autor(Integer.valueOf(request.getParameter("autor")));
                l.setId_genero(Integer.valueOf(request.getParameter("genero")));
                l.setEstado(request.getParameter("estado"));
                l.setCategoria(Integer.valueOf(request.getParameter("categoria")));
                //libroDAO.actualizarLibro(l) esta funcion retorna una valor booleano
                // se envia como parametro la variable Objeto l
                out.print(libroDAO.actualizarLibro(l));
            }
            /*====================================================================
            //ACCION DE PARA CARGAR TABLA
            //====================================================================
             */
            if (accion.equalsIgnoreCase("cargarTabla")) {
                JsonObject es = new JsonObject();
                es.add("datos", libroDAO.listarTablaLibros());
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
