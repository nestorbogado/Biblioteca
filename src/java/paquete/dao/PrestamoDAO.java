package paquete.dao;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import paquete.conexion.BasedeDatos;
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

    public String ListaAlumnos(int id) {

        String op = "<option selected value=''>Selecciona</option> ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM alumnos");
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                if (id == rs.getInt("id_alumno")) {
                    op = op + "<option selected value='" + rs.getInt("id_alumno") + "'>N° Doc:" + rs.getString("cedula") + " - Alumno/a:" + rs.getString("nombre_alumno") + " " + rs.getString("apellido_alumno") + "</option>";
                } else {
                    op = op + "<option value='" + rs.getInt("id_alumno") + "'>N° Doc:" + rs.getString("cedula") + " - Alumno/a:" + rs.getString("nombre_alumno") + " " + rs.getString("apellido_alumno") + "</option>";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return op;
    }

    public String ListaLibros(int id) {

        String op = "<option selected value=''>Selecciona</option> ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM materiales");
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                if (id == rs.getInt("id_material")) {
                    op = op + "<option selected value='" + rs.getInt("id_material") + "'>ISBN:" + rs.getString("ISBN") + " - N° Entrada:" + rs.getString("numero_entrada") + " - Titulo:" + rs.getString("titulo") + " - Estado:" + rs.getString("estado") + "</option>";
                } else {
                    if (rs.getString("estado").equalsIgnoreCase("Disponible")) {
                        op = op + "<option value='" + rs.getInt("id_material") + "'>ISBN:" + rs.getString("ISBN") + " - N° Entrada:" + rs.getString("numero_entrada") + " - Titulo:" + rs.getString("titulo") + " - Estado:" + rs.getString("estado") + "</option>";
                    }

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return op;
    }

    public int agregarPrestamo(Prestamo p) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO prestamos(MATERIALES_id_material, ALUMNOS_id_alumno, fecha_prestamo) VALUES (?,?,?)");

            preparedStatement.setInt(1, p.getId_libro());
            preparedStatement.setInt(2, p.getId_alumno());
            preparedStatement.setString(3, p.getFecha_prestamo());

            if (preparedStatement.executeUpdate() > 0) {
                return 1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int eliminarPrestamo(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM prestamos WHERE  id_prestamo=?");

            preparedStatement.setInt(1, id);
            if (preparedStatement.executeUpdate() > 0) {
                return 1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int actualizarPrestamo(Prestamo p) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE prestamos SET fecha_prestamo=? WHERE id_prestamo=?");
            // Parametros  empiezan en  1
            preparedStatement.setString(1, p.getFecha_prestamo());
            preparedStatement.setInt(2, p.getId_prestamo());
            if (preparedStatement.executeUpdate() > 0) {
                return 1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public int actualizarDevolucion(Prestamo p) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE prestamos SET fecha_devolucion=? WHERE id_prestamo=?");
            // Parametros  empiezan en  1
            preparedStatement.setString(1, p.getFecha_devolucion());
            preparedStatement.setInt(2, p.getId_prestamo());
            if (preparedStatement.executeUpdate() > 0) {
                return 1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public JsonArray listarTablaPrestamosTodos() {

        JsonArray prestamos = new JsonArray();
        try {
            //Ejecuta la sentencia SQL
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * , DATE_FORMAT(fecha_prestamo,'%d/%m/%Y') AS fecha_p, DATE_FORMAT(fecha_devolucion,'%d/%m/%Y') AS fecha_d FROM prestamos,alumnos,materiales WHERE MATERIALES_id_material=id_material AND ALUMNOS_id_alumno=id_alumno");
            while (rs.next()) {
                // SE CREA UNA VARIABLE DE TIPO OBJETO DE JSON JsonObject con el nombre de variable c
                JsonObject c = new JsonObject();
                //Agrega las propiedades al objeto
                //Ejemplo nombredelobjeto.addProperty("nombre con el que se guardará los datos", rs.getString("nombre de la columna de la base de datos"));
                c.addProperty("titulo", rs.getString("titulo"));
                c.addProperty("alumno", rs.getString("nombre_alumno") + " " + rs.getString("apellido_alumno"));
                c.addProperty("fecha_p", rs.getString("fecha_p"));
                c.addProperty("fecha_d", rs.getString("fecha_d"));
                c.addProperty("editar", "<td><button title='Editar' href='#!' value='" + rs.getInt("id_prestamo") + "' class='btn btn-success actualizar' data-toggle='modal' data-target='#editar' data-backdrop='static' data-keyboard='false'><i class='glyphicon glyphicon-edit'></i></button></td>");
                c.addProperty("eliminar", "<td><button title='Eliminar' href='#!' value='" + rs.getInt("id_prestamo") + "' class='btn btn-danger eliminar'><i class='glyphicon glyphicon-remove'></i></button></td>");

                //Agrega el objeto a la variable lista
                prestamos.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Retorna la lista
        return prestamos;
    }
    public JsonArray listarTablaPrestamosPendientes() {

        JsonArray prestamos = new JsonArray();
        try {
            //Ejecuta la sentencia SQL
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * , DATE_FORMAT(fecha_prestamo,'%d/%m/%Y') AS fecha_p, DATE_FORMAT(fecha_devolucion,'%d/%m/%Y') AS fecha_d FROM prestamos,alumnos,materiales WHERE MATERIALES_id_material=id_material AND ALUMNOS_id_alumno=id_alumno AND fecha_devolucion IS null");
            while (rs.next()) {
                // SE CREA UNA VARIABLE DE TIPO OBJETO DE JSON JsonObject con el nombre de variable c
                JsonObject c = new JsonObject();
                //Agrega las propiedades al objeto
                //Ejemplo nombredelobjeto.addProperty("nombre con el que se guardará los datos", rs.getString("nombre de la columna de la base de datos"));
                c.addProperty("titulo", rs.getString("titulo"));
                c.addProperty("alumno", rs.getString("nombre_alumno") + " " + rs.getString("apellido_alumno"));
                c.addProperty("fecha_p", rs.getString("fecha_p"));
                c.addProperty("fecha_d", rs.getString("fecha_d"));
                c.addProperty("editar", "<td><button title='Editar' href='#!' value='" + rs.getInt("id_prestamo") + "' class='btn btn-success actualizar' data-toggle='modal' data-target='#editar' data-backdrop='static' data-keyboard='false'><i class='glyphicon glyphicon-edit'></i></button></td>");
                c.addProperty("eliminar", "<td><button title='Eliminar' href='#!' value='" + rs.getInt("id_prestamo") + "' class='btn btn-danger eliminar'><i class='glyphicon glyphicon-remove'></i></button></td>");

                //Agrega el objeto a la variable lista
                prestamos.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Retorna la lista
        return prestamos;
    }
    
    public JsonArray listarTablaPrestamosDevueltos() {

        JsonArray prestamos = new JsonArray();
        try {
            //Ejecuta la sentencia SQL
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * , DATE_FORMAT(fecha_prestamo,'%d/%m/%Y') AS fecha_p, DATE_FORMAT(fecha_devolucion,'%d/%m/%Y') AS fecha_d FROM prestamos,alumnos,materiales WHERE MATERIALES_id_material=id_material AND ALUMNOS_id_alumno=id_alumno AND fecha_devolucion IS NOT null");
            while (rs.next()) {
                // SE CREA UNA VARIABLE DE TIPO OBJETO DE JSON JsonObject con el nombre de variable c
                JsonObject c = new JsonObject();
                //Agrega las propiedades al objeto
                //Ejemplo nombredelobjeto.addProperty("nombre con el que se guardará los datos", rs.getString("nombre de la columna de la base de datos"));
                c.addProperty("titulo", rs.getString("titulo"));
                c.addProperty("alumno", rs.getString("nombre_alumno") + " " + rs.getString("apellido_alumno"));
                c.addProperty("fecha_p", rs.getString("fecha_p"));
                c.addProperty("fecha_d", rs.getString("fecha_d"));
                c.addProperty("editar", "<td><button title='Editar' href='#!' value='" + rs.getInt("id_prestamo") + "' class='btn btn-success actualizar' data-toggle='modal' data-target='#editar' data-backdrop='static' data-keyboard='false'><i class='glyphicon glyphicon-edit'></i></button></td>");
                c.addProperty("eliminar", "<td><button title='Eliminar' href='#!' value='" + rs.getInt("id_prestamo") + "' class='btn btn-danger eliminar'><i class='glyphicon glyphicon-remove'></i></button></td>");

                //Agrega el objeto a la variable lista
                prestamos.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Retorna la lista
        return prestamos;
    }

    public JsonArray listarTablaDevolucionTodos() {

        JsonArray prestamos = new JsonArray();
        try {
            //Ejecuta la sentencia SQL
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * , DATE_FORMAT(fecha_prestamo,'%d/%m/%Y') AS fecha_p, DATE_FORMAT(fecha_devolucion,'%d/%m/%Y') AS fecha_d FROM prestamos,alumnos,materiales WHERE MATERIALES_id_material=id_material AND ALUMNOS_id_alumno=id_alumno");
            while (rs.next()) {
                // SE CREA UNA VARIABLE DE TIPO OBJETO DE JSON JsonObject con el nombre de variable c
                JsonObject c = new JsonObject();
                //Agrega las propiedades al objeto
                //Ejemplo nombredelobjeto.addProperty("nombre con el que se guardará los datos", rs.getString("nombre de la columna de la base de datos"));
                c.addProperty("titulo", rs.getString("titulo"));
                c.addProperty("alumno", rs.getString("nombre_alumno") + " " + rs.getString("apellido_alumno"));
                c.addProperty("fecha_p", rs.getString("fecha_p"));
                c.addProperty("fecha_d", rs.getString("fecha_d"));
                c.addProperty("editar", "<td><button title='Editar' href='#!' value='" + rs.getInt("id_prestamo") + "' class='btn btn-success actualizar' data-toggle='modal' data-target='#editar' data-backdrop='static' data-keyboard='false'><i class='glyphicon glyphicon-edit'></i></button></td>");
//                System.out.println(rs.getString("fecha_d").length());
                String aux = rs.getString("fecha_d");
                if (aux == null) {
                    c.addProperty("devolver", "<td><button title='Devolver' href='#!' value='" + rs.getInt("id_prestamo") + "' class='btn btn-warning devolver'><i class='glyphicon glyphicon-remove'></i></button></td>");
                } else {
                    c.addProperty("devolver", "<td><button title='Devolver' disabled href='#!' value='' class='btn btn-warning devolver'><i class='glyphicon glyphicon-remove'></i></button></td>");
                }

                //Agrega el objeto a la variable lista
                prestamos.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Retorna la lista
        return prestamos;
    }

    public JsonArray listarTablaDevolucionPendientes() {

        JsonArray prestamos = new JsonArray();
        try {
            //Ejecuta la sentencia SQL
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * , DATE_FORMAT(fecha_prestamo,'%d/%m/%Y') AS fecha_p, DATE_FORMAT(fecha_devolucion,'%d/%m/%Y') AS fecha_d FROM prestamos,alumnos,materiales WHERE MATERIALES_id_material=id_material AND ALUMNOS_id_alumno=id_alumno AND fecha_devolucion IS null");
            while (rs.next()) {
                // SE CREA UNA VARIABLE DE TIPO OBJETO DE JSON JsonObject con el nombre de variable c
                JsonObject c = new JsonObject();

                c.addProperty("titulo", rs.getString("titulo"));
                c.addProperty("alumno", rs.getString("nombre_alumno") + " " + rs.getString("apellido_alumno"));
                c.addProperty("fecha_p", rs.getString("fecha_p"));
                c.addProperty("fecha_d", rs.getString("fecha_d"));
                c.addProperty("editar", "<td><button title='Editar' href='#!' value='" + rs.getInt("id_prestamo") + "' class='btn btn-success actualizar' data-toggle='modal' data-target='#editar' data-backdrop='static' data-keyboard='false'><i class='glyphicon glyphicon-edit'></i></button></td>");           
                c.addProperty("devolver", "<td><button title='Devolver' href='#!' value='" + rs.getInt("id_prestamo") + "' class='btn btn-warning devolver'><i class='glyphicon glyphicon-remove'></i></button></td>");

                //Agrega el objeto a la variable lista
                prestamos.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Retorna la lista
        return prestamos;
    }
    public JsonArray listarTablaDevolucionHechas() {

        JsonArray prestamos = new JsonArray();
        try {
            //Ejecuta la sentencia SQL
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * , DATE_FORMAT(fecha_prestamo,'%d/%m/%Y') AS fecha_p, DATE_FORMAT(fecha_devolucion,'%d/%m/%Y') AS fecha_d FROM prestamos,alumnos,materiales WHERE MATERIALES_id_material=id_material AND ALUMNOS_id_alumno=id_alumno AND fecha_devolucion IS NOT null");
            while (rs.next()) {
                // SE CREA UNA VARIABLE DE TIPO OBJETO DE JSON JsonObject con el nombre de variable c
                JsonObject c = new JsonObject();

                c.addProperty("titulo", rs.getString("titulo"));
                c.addProperty("alumno", rs.getString("nombre_alumno") + " " + rs.getString("apellido_alumno"));
                c.addProperty("fecha_p", rs.getString("fecha_p"));
                c.addProperty("fecha_d", rs.getString("fecha_d"));
                c.addProperty("editar", "<td><button title='Editar' href='#!' value='" + rs.getInt("id_prestamo") + "' class='btn btn-success actualizar' data-toggle='modal' data-target='#editar' data-backdrop='static' data-keyboard='false'><i class='glyphicon glyphicon-edit'></i></button></td>");           
                c.addProperty("devolver", "<td><button disabled title='Devolver' href='#!' value='' class='btn btn-warning devolver'><i class='glyphicon glyphicon-remove'></i></button></td>");

                //Agrega el objeto a la variable lista
                prestamos.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Retorna la lista
        return prestamos;
    }

    public int verificarFechaRenovacion(Prestamo p) {
        try {
            Date fechaActual = new Date();
            System.out.println(fechaActual);
            Calendar FA = Calendar.getInstance();
            FA.setTime(fechaActual);
            int dia_actual = FA.get(Calendar.DAY_OF_MONTH);
            int mes_actual = FA.get(Calendar.MONTH) + 1;

            DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
            Date renovacion = formatoFecha.parse(FechaRenovacion(p));
//            System.out.println("Fecha RENOVACION" + FechaRenovacion(p));
//            System.out.println("Fecha PRESTAMO" + p.getFecha_prestamo());
            Calendar cal = Calendar.getInstance();
            cal.setTime(renovacion);
            //Fecha actual desglosada:
            int año = cal.get(Calendar.YEAR);
            int mes = cal.get(Calendar.MONTH) + 1;
            int dia = cal.get(Calendar.DAY_OF_MONTH);

            Date fecha_actual = formatoFecha.parse(p.getFecha_prestamo());
            Calendar fecha_a = Calendar.getInstance();
            fecha_a.setTime(fecha_actual);
            //Fecha actual desglosada:
            int años = fecha_a.get(Calendar.YEAR);
            int mess = fecha_a.get(Calendar.MONTH) + 1;
            int diaa = fecha_a.get(Calendar.DAY_OF_MONTH);

            if ((año <= años) && (mess == 1 || mess == 2 || mess == 3)) {
//                System.out.println("Puede prestar Fecha Actual: " + diaa + "/" + (mess) + "/" + años);
                return 0;
            } else {
//                System.out.println("NO Puede prestar Fecha Actual ");
                if (año == años) {
//                    System.out.println("Puede prestar Fecha Actual: " + diaa + "/" + (mess) + "/" + años);
                    return 0;
                } else {
//                    System.out.println("NO Puede prestar Fecha Actual ");
                }
            }
        } catch (ParseException ex) {
            Logger.getLogger(PrestamoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 1;
    }

    public String FechaRenovacion(Prestamo p) {
        String fecha_renovacion = "";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM alumnos WHERE id_alumno=? ");
            preparedStatement.setInt(1, p.getId_alumno());
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                fecha_renovacion = rs.getString("fecha_renovacion_alumno");

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fecha_renovacion;
    }

    public int actualizarEstadoMaterial(int id, String estado) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE materiales SET estado=? WHERE id_material=? ");

            preparedStatement.setString(1, estado);
            preparedStatement.setInt(2, id);

            if (preparedStatement.executeUpdate() > 0) {
                return 1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public JsonObject cargarDatosPrestamo(int id) {
        //  Crea la variable de tipo Objeto
        JsonObject c = new JsonObject();
        try {

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM prestamos WHERE id_prestamo=?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {

                c.addProperty("material", ListaLibros(rs.getInt("MATERIALES_id_material")));
                c.addProperty("alumno", ListaAlumnos(rs.getInt("ALUMNOS_id_alumno")));
                c.addProperty("fecha_p", rs.getString("fecha_prestamo"));
                c.addProperty("fecha_d", rs.getString("fecha_devolucion"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // retorna el objeto
        return c;
    }

    public int ActualizarEstadoDevolucionMaterial(int id_prestamo, String estado) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM prestamos WHERE id_prestamo=? ");
            preparedStatement.setInt(1, id_prestamo);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.absolute(1)) {
                int id = rs.getInt("MATERIALES_id_material");
                return actualizarEstadoMaterial(id, estado);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
