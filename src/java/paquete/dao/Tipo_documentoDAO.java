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
public class Tipo_documentoDAO {

    private Connection connection;

    public Tipo_documentoDAO() {
        connection = BasedeDatos.getConnection();
    }

    public int agregarTipoDocumento(String tipo_doc) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO tipo_documento(descripcion_tipo_doc) VALUES (?)");

            preparedStatement.setString(1, tipo_doc);

            if (preparedStatement.executeUpdate() > 0) {
                return 1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int eliminarTipoDocumento(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM tipo_documento WHERE id_tipo_documento=?");

            preparedStatement.setInt(1, id);
            if (preparedStatement.executeUpdate() > 0) {
                return 1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int actualizarTipoDocumento(int id, String tipo_doc) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE tipo_documento SET descripcion_tipo_doc=? WHERE id_tipo_documento=?");
            // Parametros  empiezan en  1
            preparedStatement.setString(1, tipo_doc);
            preparedStatement.setInt(2, id);
            if (preparedStatement.executeUpdate() > 0) {
                return 1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public JsonArray listarTablaTipoDocumento() {

        JsonArray tipo_doc = new JsonArray();
        try {
            //Ejecuta la sentencia SQL
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM tipo_documento");
            while (rs.next()) {
                // SE CREA UNA VARIABLE DE TIPO OBJETO DE JSON JsonObject con el nombre de variable c
                JsonObject c = new JsonObject();
                //Agrega las propiedades al objeto
                //Ejemplo nombredelobjeto.addProperty("nombre con el que se guardará los datos", rs.getString("nombre de la columna de la base de datos"));
                                //este es una variable      // este es de la base de datos
                c.addProperty("idtipodocumento", rs.getInt("id_tipo_documento"));
                c.addProperty("descripcion", rs.getString("descripcion_tipo_doc"));
                c.addProperty("editar", "<td><button title='Eliminar' href='#!' value='" + rs.getInt("id_tipo_documento") + "' class='btn btn-success actualizar' data-toggle='modal' data-target='#editar' data-backdrop='static' data-keyboard='false'><i class='glyphicon glyphicon-edit'></i></button></td>");
                c.addProperty("eliminar", "<td><button title='Eliminar' href='#!' value='" + rs.getInt("id_tipo_documento") + "' class='btn btn-danger eliminar'><i class='glyphicon glyphicon-remove'></i></button></td>");
              
                //Agrega el objeto a la variable lista
                tipo_doc.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Retorna la lista
        return tipo_doc;
    }

    public JsonObject cargarDatosTipoDocumento(int id) {
        //  Crea la variable de tipo Objeto
        JsonObject c = new JsonObject();
        try {

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM tipo_documento WHERE id_tipo_documento=?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {

                c.addProperty("descripcion_tipo_doc", rs.getString("descripcion_tipo_doc"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // retorna el objeto
        return c;
    }

    public JsonArray listarTipoDocumento() {

        JsonArray tipo_doc = new JsonArray();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM tipo_documento");
            while (rs.next()) {
                JsonObject c = new JsonObject();

                c.addProperty("id_tipo_documento", rs.getString("id_tipo_documento"));
                c.addProperty("descripcion_tipo_doc", rs.getString("descripcion_tipo_doc"));
                tipo_doc.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tipo_doc;
    }

}
