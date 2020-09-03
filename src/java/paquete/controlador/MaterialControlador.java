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
import paquete.dao.MaterialDAO;
import paquete.modelo.Material;

/**
 *
 * @author USUARIO
 */
public class MaterialControlador extends HttpServlet {

    private MaterialDAO MaterialDAO;

    public MaterialControlador() {
        super();
        MaterialDAO = new MaterialDAO();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String accion = "";
            accion = request.getParameter("accion");

            if (accion.equalsIgnoreCase("verificar")) {
//                out.print(MaterialDAO.verificarNumeroEntrada(Double.valueOf(request.getParameter("numero"))));
                    out.print(0);
            }
            if (accion.equalsIgnoreCase("cargarTabla")) {
                JsonObject es = new JsonObject();
                es.add("datos", MaterialDAO.listarTablaMaterial());
                out.print(es);
            }

            if (accion.equalsIgnoreCase("insertarL")) {

                Material l = new Material();
                //Guarda en el modelo los parametros que recibe
                l.setIsbn(request.getParameter("isbn"));
                l.setAño_publicacion(request.getParameter("anho"));
                l.setTitulo(request.getParameter("titulo"));
                l.setDewey(request.getParameter("dewey"));
                l.setId_editorial(Integer.valueOf(request.getParameter("editorial")));
                l.setNumero_entrada(Double.valueOf(request.getParameter("numeroEntrada")));
                l.setEstado(request.getParameter("estado"));
                l.setFecha_ingreso(request.getParameter("fechaIngreso"));
                l.setId_tipo_material(1);
                //****
                l.setAutores(request.getParameter("autor") + ",");
                System.out.println("AUTORES--" + request.getParameter("autor"));
                l.setGenero(request.getParameter("genero") + ",");
                System.out.println("GEBERO--" + request.getParameter("genero"));
                l.setCarrera(request.getParameter("carrera") + ",");
                System.out.println("CARRERA--" + request.getParameter("carrera"));
                //Agrega Material
                MaterialDAO.InsertarTipoLibro();
                l.setId_material(MaterialDAO.agregarMaterial(l));
                int aux1 = 0, aux2 = 0, aux3 = 0;
//                AUTORES
                if (l.getId_material() > 0) {

                    String B = "";
                    char[] autor = l.getAutores().toCharArray();
                    for (int i = 0; i < autor.length; i++) {
                        String a = String.valueOf(autor[i]);
                        System.out.println("Final AUTORES------" + a);
                        if (!a.equalsIgnoreCase(",")) {
                            B = B + a;
                        } else {
                            l.setId_autor(Integer.valueOf(B));
                            if (MaterialDAO.agregarMaterial_Autor(l) > 0) {
                                aux1++;
                            }
                            System.out.println("Final AUTORES--" + B);
                            B = "";

                        }
                    }

                    B = "";
                    char[] genero = l.getGenero().toCharArray();
                    for (int i = 0; i < genero.length; i++) {

                        String a = String.valueOf(genero[i]);
                        System.out.println("Final GENEROS------" + a);
                        if (!a.equalsIgnoreCase(",")) {
                            B = B + a;
                        } else {
                            l.setId_genero(Integer.valueOf(B));

                            if (MaterialDAO.agregarMaterial_Genero(l) > 0) {
                                aux2++;
                            }
                            System.out.println("Final GENERO--" + B);
                            B = "";

                        }
                    }

                    B = "";
                    char[] carrera = l.getCarrera().toCharArray();
                    for (int i = 0; i < carrera.length; i++) {

                        String a = String.valueOf(carrera[i]);

                        if (!a.equalsIgnoreCase(",")) {
                            B = B + a;
                        } else {
                            l.setId_carrera(Integer.valueOf(B));

                            if (MaterialDAO.agregarMaterial_Carrera(l) > 0) {
                                aux3++;
                            }
                            System.out.println("Final CARRERA--" + B);
                            B = "";

                        }
                    }
                    out.print(1);

//                       
                } else {
                    out.print(0);
                }
            }

            if (accion.equalsIgnoreCase("cargarDatosLibro")) {
                //Guarda en una variable auxiliar los parametros que recibe
                int id = Integer.valueOf(request.getParameter("id"));
                //libroDAO.cargarDatosLibro(id) esta funcion retorna un Objeto con los datos del libro
                // se envia como parametro la variable auxiliar id
                out.print(MaterialDAO.cargarDatosMaterial(id));
            }
            if (accion.equalsIgnoreCase("eliminarLibro")) {
                int id = Integer.valueOf(request.getParameter("id"));
                if (MaterialDAO.eliminarMaterial_Autor(id) > 0) {
                    if (MaterialDAO.eliminarMaterial_Carrera(id) > 0) {
                        if (MaterialDAO.eliminarMaterial_Genero(id) > 0) {
                            if (MaterialDAO.eliminarMaterial(id) > 0) {
                                out.print(1);
                            } else {
                                System.out.println("ERROR ELIMINAR MATERIAL");
                                out.print(0);
                            }
                        } else {
                            System.out.println("ERROR ELIMINAR GENERO");
                            out.print(0);
                        }
                    } else {
                        System.out.println("ERROR ELIMINAR CARRERA");
                        out.print(0);
                    }
                } else {
                    System.out.println("ERROR ELIMINAR AUTOR");
                    out.print(0);
                }
            }
            if (accion.equalsIgnoreCase("cargarCarrera")) {
                out.print(MaterialDAO.listarCarrera(0));
            }
            if (accion.equalsIgnoreCase("cargarGenero")) {
                out.print(MaterialDAO.listarGenero(0));
            }
            if (accion.equalsIgnoreCase("cargarAutor")) {
                out.print(MaterialDAO.listarAutor(0));
            }
            if (accion.equalsIgnoreCase("cargarEditorial")) {
                out.print(MaterialDAO.listarEditorial(0));
            }
            if (accion.equalsIgnoreCase("cargarTipoMaterial")) {
                out.print(MaterialDAO.listarTipoMaterial(0));
            }
            if (accion.equalsIgnoreCase("cargarTituloGrado")) {
                out.print(MaterialDAO.listarNivelGrado("Grado"));
            }
            if (accion.equalsIgnoreCase("cargarEstado")) {
                out.print(MaterialDAO.listarEstadoMaterial("Permanente"));
            }

            if (accion.equalsIgnoreCase("modificar")) {
                Material l = new Material();

                int aux1 = 0, aux2 = 0, aux3 = 0;
                //Guarda en el modelo los parametros que recibe
                l.setId_material(Integer.valueOf(request.getParameter("id")));
                l.setIsbn(request.getParameter("isbn"));
                l.setAño_publicacion(request.getParameter("anho"));
                l.setTitulo(request.getParameter("titulo"));
                l.setDewey(request.getParameter("dewey"));
                l.setId_editorial(Integer.valueOf(request.getParameter("editorial")));
                l.setNumero_entrada(Double.valueOf(request.getParameter("numeroEntrada")));
                l.setEstado(request.getParameter("estado"));
                l.setFecha_ingreso(request.getParameter("fechaIngreso"));
//                l.setId_tipo_material(1);
                //****
                l.setAutores(request.getParameter("autor") + ",");
                System.out.println("AUTORES--" + request.getParameter("autor"));
                l.setGenero(request.getParameter("genero") + ",");
                System.out.println("GEBERO--" + request.getParameter("genero"));
                l.setCarrera(request.getParameter("carrera") + ",");
                System.out.println("CARRERA--" + request.getParameter("carrera"));
                if (MaterialDAO.actualizarMaterial(l) > 0) {

                    MaterialDAO.eliminarMaterial_Autor(l.getId_material());
                    MaterialDAO.eliminarMaterial_Carrera(l.getId_material());
                    MaterialDAO.eliminarMaterial_Genero(l.getId_material());

                    String B = "";
                    char[] autor = l.getAutores().toCharArray();
                    for (int i = 0; i < autor.length; i++) {
                        String a = String.valueOf(autor[i]);
                        System.out.println("Final AUTORES------" + a);
                        if (!a.equalsIgnoreCase(",")) {
                            B = B + a;
                        } else {
                            l.setId_autor(Integer.valueOf(B));
                            if (MaterialDAO.agregarMaterial_Autor(l) > 0) {
                                aux1++;
                            }
                            System.out.println("Final AUTORES--" + B);
                            B = "";

                        }
                    }

                    B = "";
                    char[] genero = l.getGenero().toCharArray();
                    for (int i = 0; i < genero.length; i++) {

                        String a = String.valueOf(genero[i]);
                        System.out.println("Final GENEROS------" + a);
                        if (!a.equalsIgnoreCase(",")) {
                            B = B + a;
                        } else {
                            l.setId_genero(Integer.valueOf(B));

                            if (MaterialDAO.agregarMaterial_Genero(l) > 0) {
                                aux2++;
                            }
                            System.out.println("Final GENERO--" + B);
                            B = "";

                        }
                    }

                    B = "";
                    char[] carrera = l.getCarrera().toCharArray();
                    for (int i = 0; i < carrera.length; i++) {

                        String a = String.valueOf(carrera[i]);

                        if (!a.equalsIgnoreCase(",")) {
                            B = B + a;
                        } else {
                            l.setId_carrera(Integer.valueOf(B));

                            if (MaterialDAO.agregarMaterial_Carrera(l) > 0) {
                                aux3++;
                            }
                            System.out.println("Final CARRERA--" + B);
                            B = "";

                        }
                    }
                    out.print(1);
                } else {
                    out.print(0);
                }
            }
//
//            //*************************************************************************************
//            //
//            //
//            //
//            //
//            //*************************************************************************************
            if (accion.equalsIgnoreCase("cargarDatosTI")) {
                //Guarda en una variable auxiliar los parametros que recibe
                int id = Integer.valueOf(request.getParameter("id"));
                //libroDAO.cargarDatosLibro(id) esta funcion retorna un Objeto con los datos del libro
                // se envia como parametro la variable auxiliar id
                out.print(MaterialDAO.cargarDatosTI(id));
            }
            if (accion.equalsIgnoreCase("insertarTI")) {

                Material l = new Material();
                //Guarda en el modelo los parametros que recibe
                l.setTitulo(request.getParameter("titulo"));
                l.setTitulo_grado(request.getParameter("gradoTI"));
                l.setAutores(request.getParameter("autor"));
                l.setId_carrera(Integer.valueOf(request.getParameter("carrera")));
                l.setAño_publicacion(request.getParameter("anhoTI"));
                l.setEstado(request.getParameter("estado"));
                l.setNumero_entrada(Double.valueOf(request.getParameter("numeroEntrada")));
                l.setFecha_ingreso(request.getParameter("fechaIngreso"));
                l.setId_tipo_material(Integer.valueOf(request.getParameter("tipoTI")));
                l.setId_material(MaterialDAO.agregarTI(l));
                if (l.getId_material() > 0) {
                    out.print(MaterialDAO.agregarMaterial_Carrera(l));
                } else {
                    out.print(0);
                }
            }
//
            if (accion.equalsIgnoreCase("modificarTI")) {
                Material l = new Material();
                //Guarda en el modelo los parametros que recibe
                l.setId_material(Integer.valueOf(request.getParameter("id")));
                l.setTitulo(request.getParameter("titulo"));
                l.setTitulo_grado(request.getParameter("gradoTI"));
                l.setAutores(request.getParameter("autor"));
                l.setId_carrera(Integer.valueOf(request.getParameter("carrera")));
                l.setAño_publicacion(request.getParameter("anhoTI"));
                l.setEstado(request.getParameter("estado"));
                l.setNumero_entrada(Double.valueOf(request.getParameter("numeroEntrada")));
                l.setFecha_ingreso(request.getParameter("fechaIngreso"));
                l.setId_tipo_material(Integer.valueOf(request.getParameter("tipoTI")));

                if (l.getId_material() > 0) {
                    if (MaterialDAO.actualizarTI(l) > 0) {
                        if (MaterialDAO.eliminarMaterial_Carrera(l.getId_material()) > 0) {
                            out.print(MaterialDAO.agregarMaterial_Carrera(l));
                        } else {
                            out.print(0);
                        }
                    } else {
                        out.print(0);
                    }
                } else {
                    out.print(0);
                }
            }
            if (accion.equalsIgnoreCase("eliminarTI")) {
                int id = Integer.valueOf(request.getParameter("id"));

                if (MaterialDAO.eliminarMaterial_Carrera(id) > 0) {
                    if (MaterialDAO.eliminarMaterial(id) > 0) {
                        out.print(1);
                    } else {
                        System.out.println("ERROR ELIMINAR MATERIAL");
                        out.print(0);
                    }

                } else {
                    System.out.println("ERROR ELIMINAR CARRERA");
                    out.print(0);
                }

            }

//
//            //*************************************************************************************
//            //
//            //
//            //
//            //
//            //*************************************************************************************
            if (accion.equalsIgnoreCase("insertarR")) {

                Material l = new Material();
                //Guarda en el modelo los parametros que recibe
                l.setIssn(request.getParameter("ISSN"));
                l.setTitulo(request.getParameter("titulo"));
                l.setAño_publicacion(request.getParameter("anhoR"));
                l.setEstado(request.getParameter("estado"));
                l.setNumero_entrada(Double.valueOf(request.getParameter("numeroEntrada")));
                l.setFecha_ingreso(request.getParameter("fechaIngreso"));
                l.setId_tipo_material(Integer.valueOf(request.getParameter("tipo")));
                if (MaterialDAO.agregarR(l) > 0) {
                    out.print(1);
                } else {
                    out.print(0);
                }
            }
            if (accion.equalsIgnoreCase("cargarDatosR")) {
                //Guarda en una variable auxiliar los parametros que recibe
                int id = Integer.valueOf(request.getParameter("id"));
                //libroDAO.cargarDatosLibro(id) esta funcion retorna un Objeto con los datos del libro
                // se envia como parametro la variable auxiliar id
                out.print(MaterialDAO.cargarDatosRevista(id));
            }
            if (accion.equalsIgnoreCase("modificarR")) {
                Material l = new Material();
                //Guarda en el modelo los parametros que recibe
                l.setId_material(Integer.valueOf(request.getParameter("id")));
                l.setIssn(request.getParameter("ISSN"));
                l.setTitulo(request.getParameter("titulo"));
                l.setAño_publicacion(request.getParameter("anhoR"));
                l.setEstado(request.getParameter("estado"));
                l.setNumero_entrada(Double.valueOf(request.getParameter("numeroEntrada")));
                l.setFecha_ingreso(request.getParameter("fechaIngreso"));
                l.setId_tipo_material(Integer.valueOf(request.getParameter("tipo")));

                if (MaterialDAO.actualizarR(l) > 0) {
                    out.print(1);
                } else {
                    out.print(0);
                }
            }
            if (accion.equalsIgnoreCase("eliminarR")) {
                int id = Integer.valueOf(request.getParameter("id"));

                if (MaterialDAO.eliminarMaterial(id) > 0) {
                    out.print(1);
                } else {
                    System.out.println("ERROR ELIMINAR MATERIAL");
                    out.print(0);
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
