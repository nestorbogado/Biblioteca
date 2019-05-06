package paquete.dao;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import paquete.conexion.BasedeDatos;
import paquete.modelo.Alumno;

/**
 *
 * @author USUARIO
 */
public class AlumnosDAO {

    private Connection connection;

    public AlumnosDAO() {
        connection = BasedeDatos.getConnection();
    }

    public boolean agregarAlumno(Alumno a) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO alumnos(cedula, nombre_alumno, apellido_alumno, CARRERAS_id_carrera) VALUES (?,?,?,?)");

            preparedStatement.setString(1, a.getCi());
            preparedStatement.setString(2, a.getNombre());
            preparedStatement.setString(3, a.getApellido());
            preparedStatement.setInt(4, a.getId_carrera());

            if (preparedStatement.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean eliminarAlumno(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM alumnos WHERE id_alumno=?");

            preparedStatement.setInt(1, id);
            if (preparedStatement.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean actualizarAlumno(Alumno a) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE alumnos SET cedula=?, nombre_alumno=?, apellido_alumno=?, CARRERAS_id_carrera=? WHERE id_alumno=?");
            // Parametros  empiezan en  1
            preparedStatement.setString(1, a.getCi());
            preparedStatement.setString(2, a.getNombre());
            preparedStatement.setString(3, a.getApellido());
            preparedStatement.setInt(4, a.getId_carrera());
            preparedStatement.setInt(5, a.getId_alumno());
            if (preparedStatement.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public JsonArray listarTablaAlumnos() {

        JsonArray alumnos = new JsonArray();
        try {
            //Ejecuta la sentencia SQL
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM alumnos, carreras WHERE CARRERAS_id_carrera=id_carrera");
            while (rs.next()) {
                // SE CREA UNA VARIABLE DE TIPO OBJETO DE JSON JsonObject con el nombre de variable c
                JsonObject c = new JsonObject();
                //Agrega las propiedades al objeto
                c.addProperty("cedula", rs.getString("cedula"));
                c.addProperty("alumno", rs.getString("nombre_alumno") + " " + rs.getString("apellido_alumno"));
                c.addProperty("carrera", rs.getString("nombre_carrera"));
                c.addProperty("editar", "<td><button href='#!' value='" + rs.getInt("id_alumno") + "' class='btn btn-success actualizar' data-toggle='modal' data-target='#editar' data-backdrop='static' data-keyboard='false'><i class='glyphicon glyphicon-edit'></i></button></td>");
                c.addProperty("eliminar", "<td><button href='#!' value='" + rs.getInt("id_alumno") + "' class='btn btn-danger eliminar'><i class='glyphicon glyphicon-remove'></i></button></td>");

                //Agrega el objeto a la variable lista
                alumnos.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Retorna la lista
        return alumnos;
    }

    public JsonObject cargarDatosAlumno(int id) {
        //  Crea la variable de tipo Objeto
        JsonObject c = new JsonObject();
        try {

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM alumnos WHERE id_alumno=?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                //Agrega las propiedades al objeto
//                 c.addProperty("id_editorial", rs.getString("id_editorial"));
                c.addProperty("cedula", rs.getString("cedula"));
                c.addProperty("nombre_alumno", rs.getString("nombre_alumno"));
                c.addProperty("apellido_alumno", rs.getString("apellido_alumno"));
                c.addProperty("id_carrera", rs.getString("CARRERAS_id_carrera"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // retorna el objeto
        return c;
    }

    public JsonArray listarAlumnos() {

        JsonArray alumnos = new JsonArray();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM alumnos,carreras WHERE CARRERAS_id_carrera=id_carrera");
            while (rs.next()) {
                JsonObject c = new JsonObject();

                c.addProperty("id_alumno", rs.getString("id_alumno"));
                c.addProperty("alumno", rs.getString("nombre_alumno") + " " + rs.getString("apellido_alumno") + " | C.I.: " + rs.getString("cedula") + " | CARRERA: " + rs.getString("nombre_carrera"));
                alumnos.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return alumnos;
    }
}
