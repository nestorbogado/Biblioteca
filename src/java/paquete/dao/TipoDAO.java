/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete.dao;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import paquete.conexion.BasedeDatos;

/**
 *
 * @author USUARIO
 */
public class TipoDAO {
    private Connection connection;
    public TipoDAO() {
        connection = BasedeDatos.getConnection();
    }
    
    public int agregarTipo(String categoria) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO tipo_material(descripcion_material) VALUES (?)");

            preparedStatement.setString(1, categoria);

            if (preparedStatement.executeUpdate() > 0) {
                return 1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public int eliminarTipo(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM tipo_material WHERE id_tipo_material=?");

            preparedStatement.setInt(1, id);
            if (preparedStatement.executeUpdate() > 0) {
                return 1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
     public int actualizarTipo(int id, String tipo) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE tipo_material SET descripcion_material=? WHERE id_tipo_material=?");
            // Parametros  empiezan en  1
            preparedStatement.setString(1, tipo);
            preparedStatement.setInt(2, id);
            if (preparedStatement.executeUpdate() > 0) {
                return 1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
      public JsonArray listarTablaTipo() {
   
        JsonArray categorias = new JsonArray();
        try {
            //Ejecuta la sentencia SQL
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM tipo_material");
            while (rs.next()) {
                JsonObject c = new JsonObject();
                if ( rs.getInt("id_tipo_material")==1) {
                    //Agrega las propiedades al objeto
                //Ejemplo nombredelobjeto.addProperty("nombre con el que se guardará los datos", rs.getString("nombre de la columna de la base de datos"));
                c.addProperty("id_tipo_material", rs.getInt("id_tipo_material"));
                c.addProperty("descripcion_material", rs.getString("descripcion_material"));
                c.addProperty("editar", "<td><button title='Editar' href='#!' value='' class='btn btn-success  disabled' data-toggle='modal' data-target='#' data-backdrop='static' data-keyboard='false'><i class='glyphicon glyphicon-edit'></i></button></td>");
                c.addProperty("eliminar", "<td><button title='Eliminar' href='#!' value='' class='btn btn-danger disabled'><i class='glyphicon glyphicon-remove'></i></button></td>");
                
                }else{
                    //Agrega las propiedades al objeto
                //Ejemplo nombredelobjeto.addProperty("nombre con el que se guardará los datos", rs.getString("nombre de la columna de la base de datos"));
                c.addProperty("id_tipo_material", rs.getInt("id_tipo_material"));
                c.addProperty("descripcion_material", rs.getString("descripcion_material"));
                c.addProperty("editar", "<td><button title='Editar' href='#!' value='" + rs.getInt("id_tipo_material") + "' class='btn btn-success actualizar' data-toggle='modal' data-target='#editar' data-backdrop='static' data-keyboard='false'><i class='glyphicon glyphicon-edit'></i></button></td>");
                c.addProperty("eliminar", "<td><button title='Eliminar' href='#!' value='" + rs.getInt("id_tipo_material") + "' class='btn btn-danger eliminar'><i class='glyphicon glyphicon-remove'></i></button></td>");
                
                }
                //Agrega el objeto a la variable lista
                categorias.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Retorna la lista
        return categorias;
    }
     
     public JsonObject cargarDatosTipo(int id) {
        //  Crea la variable de tipo Objeto
        JsonObject c = new JsonObject();
        try {

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM tipo_material WHERE id_tipo_material=?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                //Agrega las propiedades al objeto
                 c.addProperty("id_tipo_material", rs.getString("id_tipo_material"));
                c.addProperty("descripcion_material", rs.getString("descripcion_material"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // retorna el objeto
        return c;
    }
     
   
     
     
}
