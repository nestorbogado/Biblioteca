package paquete.dao;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import paquete.conexion.BasedeDatos;
import paquete.modelo.Libro;

/**
 *
 * @author USUARIO
 */
public class LibroDAO {

    private Connection connection;

    // Constructor del DAO
    public LibroDAO() {
        connection = BasedeDatos.getConnection();
    }

    /*====================================================================
    //FUNCION PARA AGREGAR LIBROS
    //====================================================================
    Esta funcion recibe como parametro los datos del libro en formato Objeto del tipo MODELO Libros.java
    y retorna un valor de tipo booolean(true/false)
    RETORNA VERDADERO SI SE INSERTÓ EL REGISTRO para eso debe entrar en la condicional de preparedStatement.executeUpdate()
    P.D.:preparedStatement.executeUpdate() retorna un valor mayor a 0 (cero) en el caso de que se haya insertado el registro,
    caso contrario retorna cero
     */
    public boolean agregarLibro(Libro l) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO libros(ISBN, titulo, año_publicacion, estado, EDITORIALES_id_editorial, AUTORES_id_autor, GENEROS_id_genero,categoria) VALUES (?,?,?,?,?,?,?,?)");

            preparedStatement.setString(1, l.getIsbn());
            preparedStatement.setString(2, l.getTitulo());
            preparedStatement.setString(3, l.getAño_publicacion());
            preparedStatement.setString(4, l.getEstado());
            preparedStatement.setInt(5, l.getId_editorial());
            preparedStatement.setInt(6, l.getId_autor());
            preparedStatement.setInt(7, l.getId_genero());
            preparedStatement.setInt(8, l.getCategoria());

            if (preparedStatement.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    /*====================================================================
    //FUNCION PARA ELIMINAR LIBROS
    //====================================================================
    Esta funcion recibe como parametro el ID del libro en formato INTEGER(int)
    y retorna un valor de tipo booolean(true/false)
    RETORNA VERDADERO SI SE PUDO ELIMINAR EL REGISTRO para eso debe entrar en la condicional de preparedStatement.executeUpdate()
    P.D.:preparedStatement.executeUpdate() retorna un valor mayor a 0 (cero) en el caso de que se haya realizado la accion en
    la base de datos el registro, caso contrario retorna cero
     */

    public boolean eliminarLibro(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM libros WHERE id_libro=?");

            preparedStatement.setInt(1, id);
            if (preparedStatement.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /*====================================================================
    //FUNCION PARA ACTUALIZAR LIBROS
    //====================================================================
    Esta funcion recibe como parametro los datos del libro en formato Objeto del tipo MODELO Libros.java
    y retorna un valor de tipo booolean(true/false)
    RETORNA VERDADERO SI SE PUDO ELIMINAR EL REGISTRO para eso debe entrar en la condicional de preparedStatement.executeUpdate()
    P.D.:preparedStatement.executeUpdate() retorna un valor mayor a 0 (cero) en el caso de que se haya realizado la accion en
    la base de datos el registro, caso contrario retorna cero
     */
    public boolean actualizarLibro(Libro l) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE libros SET ISBN=?, titulo=?, año_publicacion=?, estado=?,EDITORIALES_id_editorial=?, AUTORES_id_autor=?, GENEROS_id_genero=?,categoria=? WHERE id_libro=?");
            // Parametros  empiezan en  1
            preparedStatement.setString(1, l.getIsbn());
            preparedStatement.setString(2, l.getTitulo());
            preparedStatement.setString(3, l.getAño_publicacion());
            preparedStatement.setString(4, l.getEstado());
            preparedStatement.setInt(5, l.getId_editorial());
            preparedStatement.setInt(6, l.getId_autor());
            preparedStatement.setInt(7, l.getId_genero());
              preparedStatement.setInt(8, l.getCategoria());
            preparedStatement.setInt(9, l.getId_libro());
            if (preparedStatement.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    /*====================================================================
    //FUNCION LISTAR LOS LIBROS QUE ESTAN REGISTRADOS EN LA BASE DE DATOS
    //====================================================================
    Esta funcion NO recibe datos como parametro 
    y retorna un valor de de tipo ARRAY(LISTA) En formato JSON
     */

    public JsonArray listarTablaLibros() {
        // SE CREA LA VARIABLE libros de tipo JsonArray(sería lo mismo que arrayList de java)
        JsonArray libros = new JsonArray();
        try {
            //Ejecuta la sentencia SQL
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM libros,editoriales,autores,generos WHERE EDITORIALES_id_editorial=id_editorial AND AUTORES_id_autor=id_autor AND GENEROS_id_genero=id_genero");
            while (rs.next()) {
                // SE CREA UNA VARIABLE DE TIPO OBJETO DE JSON JsonObject con el nombre de variable c
                JsonObject c = new JsonObject();
                //Agrega las propiedades al objeto
                //Ejemplo nombredelobjeto.addProperty("nombre con el que se guardará los datos", rs.getString("nombre de la columna de la base de datos"));
                c.addProperty("ISBN", rs.getString("ISBN"));
                c.addProperty("titulo", rs.getString("titulo"));
                c.addProperty("año_publicacion", rs.getString("año_publicacion"));
                c.addProperty("estado", rs.getString("estado"));
                c.addProperty("nombre_editorial", rs.getString("nombre_editorial"));
                c.addProperty("nombre_genero", rs.getString("nombre_genero"));
                c.addProperty("nombre_autor", rs.getString("nombre_autor"));
                c.addProperty("editar", "<td><button href='#!' value='" + rs.getInt("id_libro") + "' class='btn btn-success actualizar' data-toggle='modal' data-target='#editar' data-backdrop='static' data-keyboard='false'><i class='glyphicon glyphicon-edit'></i></button></td>");
                c.addProperty("eliminar", "<td><button href='#!' value='" + rs.getInt("id_libro") + "' class='btn btn-danger eliminar'><i class='glyphicon glyphicon-remove'></i></button></td>");
                
                //Agrega el objeto a la variable lista
                libros.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Retorna la lista
        return libros;
    }
    
    /*====================================================================
    //FUNCION CARGAR LOS DATOS DEL LIBROS QUE ESTAN REGISTRADOS EN LA BASE DE DATOS
    SEGUN EL ID
    //====================================================================
    Esta funcion recibe datos como parametro una variable de tipo INTEGER int(para el id)
    y retorna un valor de de tipo JsonObjec En formato JSON, sería lo mismo que en Java
     */

    public JsonObject cargarDatosLibro(int id) {
        //  Crea la variable de tipo Objeto
        JsonObject c = new JsonObject();
        try {

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM libros,editoriales,autores,generos WHERE EDITORIALES_id_editorial=id_editorial AND AUTORES_id_autor=id_autor AND GENEROS_id_genero=id_genero AND id_libro=?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                //Agrega las propiedades al objeto
                //Ejemplo nombredelobjeto.addProperty("nombre con el que se guardará los datos", rs.getString("nombre de la columna de la base de datos"));
                c.addProperty("ISBN", rs.getString("ISBN"));
                c.addProperty("titulo", rs.getString("titulo"));
                c.addProperty("año_publicacion", rs.getString("año_publicacion"));
                c.addProperty("estado", rs.getString("estado"));
                c.addProperty("id_editorial", rs.getString("id_editorial"));
                c.addProperty("id_genero", rs.getString("id_genero"));
                c.addProperty("id_autor", rs.getString("id_autor"));
                c.addProperty("categoria", rs.getString("categoria"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // retorna el objeto
        return c;
    }
    
    public JsonArray listarLibros() {
        // SE CREA LA VARIABLE libros de tipo JsonArray(sería lo mismo que arrayList de java)
        JsonArray libros = new JsonArray();
        try {
            //Ejecuta la sentencia SQL
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM libros,editoriales,autores,generos WHERE EDITORIALES_id_editorial=id_editorial AND AUTORES_id_autor=id_autor AND GENEROS_id_genero=id_genero");
            while (rs.next()) {
                // SE CREA UNA VARIABLE DE TIPO OBJETO DE JSON JsonObject con el nombre de variable c
                JsonObject c = new JsonObject();
                //Ejemplo nombredelobjeto.addProperty("nombre con el que se guardará los datos", rs.getString("nombre de la columna de la base de datos"));
                 c.addProperty("id_libro", rs.getInt("id_libro"));
                c.addProperty("libro","TITULO: "+rs.getString("titulo")+" | "+"ISBN: "+rs.getString("ISBN")+" | EDITORIAL: "+rs.getString("nombre_editorial")+" | GENERO: "+rs.getString("nombre_genero")+" | AUTOR: "+rs.getString("nombre_autor"));
                
                libros.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Retorna la lista
        return libros;
    }
}
