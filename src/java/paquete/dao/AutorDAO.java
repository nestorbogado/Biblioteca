
package paquete.dao;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import paquete.conexion.BasedeDatos;


public class AutorDAO {
    private Connection connection;
    public AutorDAO() {
        connection = BasedeDatos.getConnection();
    }
    public int agregarAutor(String autor) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO autores(nombre_autor) VALUES (?)");

            preparedStatement.setString(1, autor);

            if (preparedStatement.executeUpdate() > 0) {
                return 1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
     public int eliminarAutor(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM autores WHERE id_autor=?");

            preparedStatement.setInt(1, id);
            if (preparedStatement.executeUpdate() > 0) {
                return 1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
     
     public int actualizarAutor(int id, String autor) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE autores SET nombre_autor=? WHERE id_autor=?");
            // Parametros  empiezan en  1
            preparedStatement.setString(1, autor);
            preparedStatement.setInt(2, id);
            if (preparedStatement.executeUpdate() > 0) {
                return 1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
     
     public JsonArray listarTablaAutores() {
   
        JsonArray autores = new JsonArray();
        try {
            //Ejecuta la sentencia SQL
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM autores");
            while (rs.next()) {
                // SE CREA UNA VARIABLE DE TIPO OBJETO DE JSON JsonObject con el nombre de variable c
                JsonObject c = new JsonObject();
                //Agrega las propiedades al objeto
                //Ejemplo nombredelobjeto.addProperty("nombre con el que se guardará los datos", rs.getString("nombre de la columna de la base de datos"));
                c.addProperty("id_autor", rs.getInt("id_autor"));
                c.addProperty("nombre_autor", rs.getString("nombre_autor"));
                c.addProperty("editar", "<td><button title='Editar' href='#!' value='" + rs.getInt("id_autor") + "' class='btn btn-success actualizar' data-toggle='modal' data-target='#editar' data-backdrop='static' data-keyboard='false'><i class='glyphicon glyphicon-edit'></i></button></td>");
                c.addProperty("eliminar", "<td><button title='Eliminar' href='#!' value='" + rs.getInt("id_autor") + "' class='btn btn-danger eliminar'><i class='glyphicon glyphicon-remove'></i></button></td>");
                
                //Agrega el objeto a la variable lista
                autores.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Retorna la lista
        return autores;
    }
     
     public JsonObject cargarDatosAutor(int id) {
        //  Crea la variable de tipo Objeto
        JsonObject c = new JsonObject();
        try {

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM autores WHERE id_autor=?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                //Agrega las propiedades al objeto
//                 c.addProperty("id_editorial", rs.getString("id_editorial"));
                c.addProperty("nombre_autor", rs.getString("nombre_autor"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // retorna el objeto
        return c;
    }
    public JsonArray listarAutor() {

        JsonArray autor = new JsonArray();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM autores");
            while (rs.next()) {
                JsonObject c = new JsonObject();

                c.addProperty("id_autor", rs.getString("id_autor"));
                c.addProperty("nombre_autor", rs.getString("nombre_autor"));
                autor.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return autor;
    }
    
}
