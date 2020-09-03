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
import java.util.logging.Level;
import java.util.logging.Logger;
import paquete.conexion.BasedeDatos;
import paquete.modelo.Alumno;

/**
 *
 * @author hp
 */
public class AlumnosDAO {

    private Connection connection;

    public AlumnosDAO() {
        connection = BasedeDatos.getConnection();
    }

    public String listarCarrera(int id) {

        String op = "<option selected value=''>Selecciona</option> ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM carreras ");
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                if (id == rs.getInt("id_carrera")) {
                    op = op + "<option selected value='" + rs.getInt("id_carrera") + "'>" + rs.getString("nombre_carrera") + "</option>";
                } else {
                    op = op + "<option value='" + rs.getInt("id_carrera") + "'>" + rs.getString("nombre_carrera") + "</option>";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return op;
    }

    public String listarTipoDoc(int id) {

        String op = "";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM tipo_documento ");
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                if (id == rs.getInt("id_tipo_documento")) {
                    op = op + "<option selected value='" + rs.getInt("id_tipo_documento") + "'>" + rs.getString("descripcion_tipo_doc") + "</option>";
                } else {
                    op = op + "<option value='" + rs.getInt("id_tipo_documento") + "'>" + rs.getString("descripcion_tipo_doc") + "</option>";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return op;
    }

    public JsonArray listarTablaAlumnos() {

        JsonArray autores = new JsonArray();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM alumnos a INNER JOIN carreras c ON a.CARRERAS_id_carrera = c.id_carrera");
            while (rs.next()) {
                // SE CREA UNA VARIABLE DE TIPO OBJETO DE JSON JsonObject con el nombre de variable c
                JsonObject c = new JsonObject();
                //Agrega las propiedades al objeto
                //Ejemplo nombredelobjeto.addProperty("nombre con el que se guardará los datos", rs.getString("nombre de la columna de la base de datos"));

                c.addProperty("cedula", rs.getString("cedula"));
                c.addProperty("alumno", rs.getString("nombre_alumno") + " " + rs.getString("apellido_alumno"));
                c.addProperty("carrera", rs.getString("nombre_carrera"));
                c.addProperty("telefono", rs.getString("telefono_alumno"));
                c.addProperty("editar", "<td><button title='Editar' href='#!' value='" + rs.getInt("id_alumno") + "' class='btn btn-success actualizar' data-toggle='modal' data-target='#editar' data-backdrop='static' data-keyboard='false'><i class='glyphicon glyphicon-edit'></i></button></td>");
                c.addProperty("eliminar", "<td><button title='Eliminar' href='#!' value='" + rs.getInt("id_alumno") + "' class='btn btn-danger eliminar'><i class='glyphicon glyphicon-remove'></i></button></td>");

                //Agrega el objeto a la variable lista
                autores.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Retorna la lista
        return autores;
    }

    public JsonObject cargarDatosAlumno(int id) {
        //  Crea la variable de tipo Objeto
        JsonObject c = new JsonObject();
        try {

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM alumnos a INNER JOIN carreras c ON a.CARRERAS_id_carrera = c.id_carrera WHERE id_alumno = ?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                //Agrega las propiedades al objeto
                c.addProperty("id_alumno", rs.getInt("id_alumno"));
                c.addProperty("cedula", rs.getString("cedula"));
                c.addProperty("nombre_alumno", rs.getString("nombre_alumno"));
                c.addProperty("apellido_alumno", rs.getString("apellido_alumno"));
                c.addProperty("telefono_alumno", rs.getString("telefono_alumno"));
                c.addProperty("fecha_ingreso", rs.getString("fecha_ingreso_alumno"));
                c.addProperty("fecha_renovacion", rs.getString("fecha_renovacion_alumno"));
                c.addProperty("carrera", listarCarrera(Integer.valueOf(rs.getString("CARRERAS_id_carrera"))));
                c.addProperty("tipo", listarTipoDoc(Integer.valueOf(rs.getString("tipo_documento_id_tipo_documento"))));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // retorna el objeto
        return c;
    }

    public int AgregarAlumno(Alumno a) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO alumnos(cedula, nombre_alumno, apellido_alumno, telefono_alumno, fecha_ingreso_alumno, fecha_renovacion_alumno, CARRERAS_id_carrera, tipo_documento_id_tipo_documento) VALUES (?,?,?,?,?,?,?,?)");

            ps.setString(1, a.getCi());
            ps.setString(2, a.getNombre());
            ps.setString(3, a.getApellido());
            ps.setString(4, a.getTelefono());
            ps.setString(5, a.getFecha_ingreso());
            ps.setString(6, a.getFecha_renovacion());
            ps.setInt(7, a.getId_carrera());
            ps.setInt(8, a.getId_tipo_doc());

            if (ps.executeUpdate() > 0) {
                return 1;
            }

        } catch (SQLException e) {
            System.err.println(e);
        }
        return 0;
    }

    public int actualizarAlumno(Alumno a) {
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE alumnos SET cedula=?,nombre_alumno=?,apellido_alumno=?,telefono_alumno=?,fecha_ingreso_alumno=?,fecha_renovacion_alumno=?,CARRERAS_id_carrera=?,tipo_documento_id_tipo_documento=? WHERE id_alumno=?");
            ps.setString(1, a.getCi());
            ps.setString(2, a.getNombre());
            ps.setString(3, a.getApellido());
            ps.setString(4, a.getTelefono());
            ps.setString(5, a.getFecha_ingreso());
            ps.setString(6, a.getFecha_renovacion());
            ps.setInt(7, a.getId_carrera());
            ps.setInt(8, a.getId_tipo_doc());
            ps.setInt(9, a.getId_alumno());
            if (ps.executeUpdate()>0) {
                return 1;
            }

        } catch (SQLException ex) {
            Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return 0;
    }

    public int eliminarAlumno(int id) {
        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM alumnos WHERE id_alumno = ?");
            ps.setInt(1, id);
            if (ps.executeUpdate()>0) {
                return 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AlumnosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 1;
    }
}
