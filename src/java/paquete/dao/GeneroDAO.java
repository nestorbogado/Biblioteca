
package paquete.dao;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import paquete.conexion.BasedeDatos;


public class GeneroDAO {

    private Connection connection;
    public GeneroDAO() {
        connection = BasedeDatos.getConnection();
    }
    
     public boolean agregarGenero(String genero) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO generos(nombre_genero) VALUES (?)");

            preparedStatement.setString(1, genero);

            if (preparedStatement.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
     public boolean eliminarGenero(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM generos WHERE id_genero=?");

            preparedStatement.setInt(1, id);
            if (preparedStatement.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
     
     public boolean actualizarGenero(int id, String genero) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE generos SET nombre_genero=? WHERE id_genero=?");
            // Parametros  empiezan en  1
            preparedStatement.setString(1, genero);
            preparedStatement.setInt(2, id);
            if (preparedStatement.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
     
     public JsonArray listarTablaGeneros() {
   
        JsonArray generos = new JsonArray();
        try {
            //Ejecuta la sentencia SQL
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM generos");
            while (rs.next()) {
                // SE CREA UNA VARIABLE DE TIPO OBJETO DE JSON JsonObject con el nombre de variable c
                JsonObject c = new JsonObject();
                //Agrega las propiedades al objeto
                //Ejemplo nombredelobjeto.addProperty("nombre con el que se guardará los datos", rs.getString("nombre de la columna de la base de datos"));
                c.addProperty("id_genero", rs.getInt("id_genero"));
                c.addProperty("nombre_genero", rs.getString("nombre_genero"));
                c.addProperty("editar", "<td><button href='#!' value='" + rs.getInt("id_genero") + "' class='btn btn-success actualizar' data-toggle='modal' data-target='#editar' data-backdrop='static' data-keyboard='false'><i class='glyphicon glyphicon-edit'></i></button></td>");
                c.addProperty("eliminar", "<td><button href='#!' value='" + rs.getInt("id_genero") + "' class='btn btn-danger eliminar'><i class='glyphicon glyphicon-remove'></i></button></td>");
                
                //Agrega el objeto a la variable lista
                generos.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Retorna la lista
        return generos;
    }
     
     public JsonObject cargarDatosGeneros(int id) {
        //  Crea la variable de tipo Objeto
        JsonObject c = new JsonObject();
        try {

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM generos WHERE id_genero=?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                //Agrega las propiedades al objeto
//                 c.addProperty("id_editorial", rs.getString("id_editorial"));
                c.addProperty("nombre_genero", rs.getString("nombre_genero"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // retorna el objeto
        return c;
    }
    
    public JsonArray listarGenero() {

        JsonArray genero = new JsonArray();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM generos");
            while (rs.next()) {
                JsonObject c = new JsonObject();

                c.addProperty("id_genero", rs.getString("id_genero"));
                c.addProperty("nombre_genero", rs.getString("nombre_genero"));
                genero.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return genero;
    }
    
}
