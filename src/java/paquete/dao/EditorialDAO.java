
package paquete.dao;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import paquete.conexion.BasedeDatos;
import paquete.modelo.Libro;

/**
 *
 * @author USUARIO
 */
public class EditorialDAO {
    private Connection connection;
    
    public EditorialDAO() {
        connection = BasedeDatos.getConnection();
    }
    
      public boolean agregarEditorial(String editorial) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO editoriales(nombre_editorial) VALUES (?)");

            preparedStatement.setString(1, editorial);

            if (preparedStatement.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
      
    public boolean eliminarEditorial(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM editoriales WHERE id_editorial=?");

            preparedStatement.setInt(1, id);
            if (preparedStatement.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean actualizarEditorial(int id, String editorial) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE editoriales SET nombre_editorial=? WHERE id_editorial=?");
            // Parametros  empiezan en  1
            preparedStatement.setString(1, editorial);
            preparedStatement.setInt(2, id);
            if (preparedStatement.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
     public JsonArray listarTablaEditoriales() {
   
        JsonArray editoriales = new JsonArray();
        try {
            //Ejecuta la sentencia SQL
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM editoriales");
            while (rs.next()) {
                // SE CREA UNA VARIABLE DE TIPO OBJETO DE JSON JsonObject con el nombre de variable c
                JsonObject c = new JsonObject();
                //Agrega las propiedades al objeto
                //Ejemplo nombredelobjeto.addProperty("nombre con el que se guardará los datos", rs.getString("nombre de la columna de la base de datos"));
                c.addProperty("id_editorial", rs.getInt("id_editorial"));
                c.addProperty("nombre_editorial", rs.getString("nombre_editorial"));
                c.addProperty("editar", "<td><button href='#!' value='" + rs.getInt("id_editorial") + "' class='btn btn-success actualizar' data-toggle='modal' data-target='#editar' data-backdrop='static' data-keyboard='false'><i class='glyphicon glyphicon-edit'></i></button></td>");
                c.addProperty("eliminar", "<td><button href='#!' value='" + rs.getInt("id_editorial") + "' class='btn btn-danger eliminar'><i class='glyphicon glyphicon-remove'></i></button></td>");
                
                //Agrega el objeto a la variable lista
                editoriales.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Retorna la lista
        return editoriales;
    }
     public JsonObject cargarDatosEditorial(int id) {
        //  Crea la variable de tipo Objeto
        JsonObject c = new JsonObject();
        try {

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM editoriales WHERE id_editorial=?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                //Agrega las propiedades al objeto
//                 c.addProperty("id_editorial", rs.getString("id_editorial"));
                c.addProperty("nombre_editorial", rs.getString("nombre_editorial"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // retorna el objeto
        return c;
    }
    
    public JsonArray listarEditorial() {

        JsonArray editorial = new JsonArray();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM editoriales");
            while (rs.next()) {
                JsonObject c = new JsonObject();

                c.addProperty("id_editorial", rs.getString("id_editorial"));
                c.addProperty("nombre_editorial", rs.getString("nombre_editorial"));
                editorial.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return editorial;
    }
}
