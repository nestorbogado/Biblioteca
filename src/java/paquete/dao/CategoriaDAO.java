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
import paquete.modelo.Categoria;

/**
 *
 * @author USUARIO
 */
public class CategoriaDAO {
    private Connection connection;
    public CategoriaDAO() {
        connection = BasedeDatos.getConnection();
    }
    
    public boolean agregarCategoria(Categoria categoria) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO categoria(descripcion_categoria) VALUES (?)");

            preparedStatement.setString(1, categoria.getCategoria());

            if (preparedStatement.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean eliminarCategoria(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM categoria WHERE id_categoria=?");

            preparedStatement.setInt(1, id);
            if (preparedStatement.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
     public boolean actualizarCategoria(Categoria categoria) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE categoria SET descripcion_categoria=? WHERE id_categoria=?");
            // Parametros  empiezan en  1
            preparedStatement.setString(1, categoria.getCategoria());
            preparedStatement.setInt(2, categoria.getId_categoria());
            if (preparedStatement.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
      public JsonArray listarTablaCategoria() {
   
        JsonArray categorias = new JsonArray();
        try {
            //Ejecuta la sentencia SQL
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM categoria");
            while (rs.next()) {
                // SE CREA UNA VARIABLE DE TIPO OBJETO DE JSON JsonObject con el nombre de variable c
                JsonObject c = new JsonObject();
                //Agrega las propiedades al objeto
                //Ejemplo nombredelobjeto.addProperty("nombre con el que se guardará los datos", rs.getString("nombre de la columna de la base de datos"));
                c.addProperty("id_categoria", rs.getInt("id_categoria"));
                c.addProperty("descripcion_categoria", rs.getString("descripcion_categoria"));
                c.addProperty("editar", "<td><button href='#!' value='" + rs.getInt("id_categoria") + "' class='btn btn-success actualizar' data-toggle='modal' data-target='#editar' data-backdrop='static' data-keyboard='false'><i class='glyphicon glyphicon-edit'></i></button></td>");
                c.addProperty("eliminar", "<td><button href='#!' value='" + rs.getInt("id_categoria") + "' class='btn btn-danger eliminar'><i class='glyphicon glyphicon-remove'></i></button></td>");
                
                //Agrega el objeto a la variable lista
                categorias.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Retorna la lista
        return categorias;
    }
     
     public JsonObject cargarDatosCategoria(int id) {
        //  Crea la variable de tipo Objeto
        JsonObject c = new JsonObject();
        try {

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM categoria WHERE id_categoria=?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                //Agrega las propiedades al objeto
//                 c.addProperty("id_editorial", rs.getString("id_editorial"));
                c.addProperty("descripcion_categoria", rs.getString("descripcion_categoria"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // retorna el objeto
        return c;
    }
     
     public JsonArray listarTablaCategoriaExacta(String categoria) {
   
        JsonArray categorias = new JsonArray();
        try {
            //Ejecuta la sentencia SQL
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM categoria  WHERE descripcion_categoria='"+categoria+"'");
            while (rs.next()) {
                // SE CREA UNA VARIABLE DE TIPO OBJETO DE JSON JsonObject con el nombre de variable c
                JsonObject c = new JsonObject();
                //Agrega las propiedades al objeto
                //Ejemplo nombredelobjeto.addProperty("nombre con el que se guardará los datos", rs.getString("nombre de la columna de la base de datos"));
                c.addProperty("id_categoria", rs.getInt("id_categoria"));
                c.addProperty("descripcion_categoria", rs.getString("descripcion_categoria"));
                c.addProperty("editar", "<td><button href='#!' value='" + rs.getInt("id_categoria") + "' class='btn btn-success actualizar' data-toggle='modal' data-target='#editar' data-backdrop='static' data-keyboard='false'><i class='glyphicon glyphicon-edit'></i></button></td>");
                c.addProperty("eliminar", "<td><button href='#!' value='" + rs.getInt("id_categoria") + "' class='btn btn-danger eliminar'><i class='glyphicon glyphicon-remove'></i></button></td>");
                
                //Agrega el objeto a la variable lista
                categorias.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Retorna la lista
        return categorias;
    }
     
       public JsonArray listarCategoria() {

        JsonArray autor = new JsonArray();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM categoria");
            while (rs.next()) {
                JsonObject c = new JsonObject();

                c.addProperty("id_categoria", rs.getString("id_categoria"));
                c.addProperty("categoria", rs.getString("descripcion_categoria"));
                autor.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return autor;
    }
     
}
