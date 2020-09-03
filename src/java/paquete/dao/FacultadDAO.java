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
 * @author AB
 */
public class FacultadDAO {
    
    private Connection connection;
    
    public FacultadDAO()
    {
        connection = BasedeDatos.getConnection();
    }
    
    
    
    
    public int agregarFacultad(String descripcion) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO facultad (descripcion) VALUES (?)");

            preparedStatement.setString(1, descripcion);

            if (preparedStatement.executeUpdate() > 0) {
                return 1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    
    
    public int eliminarFacultad(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM facultad WHERE idfacultad=?");

            preparedStatement.setInt(1, id);
            if (preparedStatement.executeUpdate() > 0) {
                return 1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    
    
    
    
    
    public int actualizarFacultad(int id, String descripcion) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE facultad SET descripcion=? WHERE idfacultad=?");
            // Parametros  empiezan en  1
            preparedStatement.setString(1, descripcion);
            preparedStatement.setInt(2, id);
            if (preparedStatement.executeUpdate() > 0) {
                return 1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    
    
    
    
    
    
    
    public JsonArray listarTablaFacultad() {
   
        JsonArray facultad = new JsonArray();
        try {
            //Ejecuta la sentencia SQL
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM facultad");
            while (rs.next()) {
                // SE CREA UNA VARIABLE DE TIPO OBJETO DE JSON JsonObject con el nombre de variable c
                JsonObject c = new JsonObject();
                //Agrega las propiedades al objeto
                //Ejemplo nombredelobjeto.addProperty("nombre con el que se guardará los datos", rs.getString("nombre de la columna de la base de datos"));
                c.addProperty("idfacultad", rs.getInt("idfacultad"));
                c.addProperty("descripcion", rs.getString("descripcion"));
                c.addProperty("editar", "<td><button title='Editar' href='#!' value='" + rs.getInt("idfacultad") + "' class='btn btn-success actualizar' data-toggle='modal' data-target='#editar' data-backdrop='static' data-keyboard='false'><i class='glyphicon glyphicon-edit'></i></button></td>");
                c.addProperty("eliminar", "<td><button title='Eliminar' href='#!' value='" + rs.getInt("idfacultad") + "' class='btn btn-danger eliminar'><i class='glyphicon glyphicon-remove'></i></button></td>");
                
                //Agrega el objeto a la variable lista
                facultad.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Retorna la lista
        return facultad;
    }
    
    
    
    
    
    
    
    
    public JsonObject cargarDatosFacultad(int id) {
        //  Crea la variable de tipo Objeto
        JsonObject c = new JsonObject();
        try {

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM facultad WHERE idfacultad=?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                
                c.addProperty("descripcion", rs.getString("descripcion"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // retorna el objeto
        return c;
    }
    
    
    
    
    public JsonArray listarFacultad() {

        JsonArray facultad = new JsonArray();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM facultad");
            while (rs.next()) {
                JsonObject c = new JsonObject();

                c.addProperty("idfacultad", rs.getString("idfacultad"));
                c.addProperty("descripcion", rs.getString("descripcion"));
                facultad.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return facultad;
    }
    
    
    
}

