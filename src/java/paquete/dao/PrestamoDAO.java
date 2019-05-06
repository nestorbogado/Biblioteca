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
import paquete.modelo.Prestamo;

/**
 *
 * @author USUARIO
 */
public class PrestamoDAO {

    private Connection connection;

    public PrestamoDAO() {
        connection = BasedeDatos.getConnection();
    }

    public boolean agregarPrestamo(Prestamo p) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO prestamos(LIBROS_id_libro, ALUMNOS_id_alumno, fecha_prestamo, dias_prestado) VALUES (?,?,?,?)");

            preparedStatement.setInt(1, p.getId_libro());
            preparedStatement.setInt(2, p.getId_alumno());
            preparedStatement.setString(3, p.getFecha_prestamo());
            preparedStatement.setInt(4, p.getDias_prestados());

            if (preparedStatement.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean eliminarPrestamo(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM prestamos WHERE  id_prestamo=?");

            preparedStatement.setInt(1, id);
            if (preparedStatement.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean actualizarPrestamo(Prestamo p) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE prestamos SET fecha_devolucion=?, dias_reales=?, diferencia=?, multa=? WHERE id_prestamo=?");
            // Parametros  empiezan en  1
            preparedStatement.setString(1, p.getFecha_devolucion());
            preparedStatement.setInt(2, p.getDias_reales());
            preparedStatement.setInt(3, p.getDiferencia());
            preparedStatement.setString(4, p.getMulta());
            preparedStatement.setInt(5, p.getId_prestamo());
            if (preparedStatement.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public JsonArray listarTablaPrestamos() {
        
        JsonArray prestamos = new JsonArray();
        try {
            //Ejecuta la sentencia SQL
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * , DATE_FORMAT(fecha_prestamo,'%d/%m/%Y') AS fecha_p, DATE_FORMAT(fecha_devolucion,'%d/%m/%Y') AS fecha_d FROM prestamos,alumnos,libros WHERE LIBROS_id_libro=id_libro AND ALUMNOS_id_alumno=id_alumno");
            while (rs.next()) {
                // SE CREA UNA VARIABLE DE TIPO OBJETO DE JSON JsonObject con el nombre de variable c
                JsonObject c = new JsonObject();
                //Agrega las propiedades al objeto
                //Ejemplo nombredelobjeto.addProperty("nombre con el que se guardará los datos", rs.getString("nombre de la columna de la base de datos"));
                c.addProperty("titulo", rs.getString("titulo"));
                c.addProperty("alumno", rs.getString("nombre_alumno")+" "+rs.getString("apellido_alumno"));
                c.addProperty("fecha_p", rs.getString("fecha_p"));
                c.addProperty("dias_prestado", rs.getString("dias_prestado"));
                c.addProperty("fecha_d", rs.getString("fecha_d"));
                c.addProperty("dias_reales", rs.getString("dias_reales"));
                c.addProperty("diferencia", rs.getString("diferencia"));
                c.addProperty("multa", rs.getString("multa"));
                c.addProperty("editar", "<td><button href='#!' value='" + rs.getInt("id_prestamo") + "' class='btn btn-success actualizar' data-toggle='modal' data-target='#editar' data-backdrop='static' data-keyboard='false'><i class='glyphicon glyphicon-edit'></i></button></td>");
                c.addProperty("eliminar", "<td><button href='#!' value='" + rs.getInt("id_prestamo") + "' class='btn btn-danger eliminar'><i class='glyphicon glyphicon-remove'></i></button></td>");
                
                //Agrega el objeto a la variable lista
                prestamos.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Retorna la lista
        return prestamos;
    }
    
     public JsonObject cargarDatosLibro(int id) {
        // SE CREA UNA VARIABLE DE TIPO OBJETO DE JSON JsonObject con el nombre de variable c
                JsonObject c = new JsonObject();
        try {
            //Ejecuta la sentencia SQL
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * , DATE_FORMAT(fecha_prestamo,'%d/%m/%Y') AS fecha_p, DATE_FORMAT(fecha_devolucion,'%d/%m/%Y') AS fecha_d FROM prestamos,alumnos,libros WHERE LIBROS_id_libro=id_libro AND ALUMNOS_id_alumno=id_alumno AND id_prestamo="+id);
            while (rs.next()) {
                
                //Agrega las propiedades al objeto
                //Ejemplo nombredelobjeto.addProperty("nombre con el que se guardará los datos", rs.getString("nombre de la columna de la base de datos"));
                c.addProperty("titulo", rs.getString("titulo"));
                c.addProperty("alumno", rs.getString("nombre_alumno")+" "+rs.getString("apellido_alumno"));
                c.addProperty("fecha_p", rs.getString("fecha_prestamo"));
                c.addProperty("dias_prestado", rs.getString("dias_prestado"));
                c.addProperty("fecha_d", rs.getString("fecha_devolucion"));
                c.addProperty("dias_reales", rs.getString("dias_reales"));
                c.addProperty("diferencia", rs.getString("diferencia"));
                c.addProperty("multa", rs.getString("multa"));
             
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Retorna la lista
        return c;
     }

}
