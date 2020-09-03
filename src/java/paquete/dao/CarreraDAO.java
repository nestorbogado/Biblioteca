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
public class CarreraDAO {
private Connection connection;
    public CarreraDAO() {
        connection = BasedeDatos.getConnection();
    }
    
     public int agregarCarrera(String carrera, int facultad) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO carreras(nombre_carrera,facultad_idfacultad) VALUES (?,?)");

            preparedStatement.setString(1, carrera);
            preparedStatement.setInt(2, facultad);
            if (preparedStatement.executeUpdate() > 0) {
                return 1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
     public int eliminarCarrera(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM carreras WHERE id_carrera=?");

            preparedStatement.setInt(1, id);
            if (preparedStatement.executeUpdate() > 0) {
                return 1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
     
     public int actualizarCarrera(int id, String carrera,int facultad) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE carreras SET nombre_carrera=? ,facultad_idfacultad=? WHERE id_carrera=?");
            // Parametros  empiezan en  1
            preparedStatement.setString(1, carrera);
            preparedStatement.setInt(2, facultad);
            preparedStatement.setInt(3, id);
            if (preparedStatement.executeUpdate() > 0) {
                return 1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
     
     public JsonArray listarTablaCarreras() {
   
        JsonArray autores = new JsonArray();
        try {
            //Ejecuta la sentencia SQL
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM carreras,facultad WHERE facultad_idfacultad=idfacultad");
            while (rs.next()) {
                // SE CREA UNA VARIABLE DE TIPO OBJETO DE JSON JsonObject con el nombre de variable c
                JsonObject c = new JsonObject();
                //Agrega las propiedades al objeto
                //Ejemplo nombredelobjeto.addProperty("nombre con el que se guardará los datos", rs.getString("nombre de la columna de la base de datos"));
                c.addProperty("id_carrera", rs.getInt("id_carrera"));
                c.addProperty("nombre_carrera", rs.getString("nombre_carrera"));
                c.addProperty("facultad", rs.getString("descripcion"));
                c.addProperty("editar", "<td><button title='Editar' href='#!' value='" + rs.getInt("id_carrera") + "' class='btn btn-success actualizar' data-toggle='modal' data-target='#editar' data-backdrop='static' data-keyboard='false'><i class='glyphicon glyphicon-edit'></i></button></td>");
                c.addProperty("eliminar", "<td><button  title='Eliminar' href='#!' value='" + rs.getInt("id_carrera") + "' class='btn btn-danger eliminar'><i class='glyphicon glyphicon-remove'></i></button></td>");
                
                //Agrega el objeto a la variable lista
                autores.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Retorna la lista
        return autores;
    }
     
     public JsonObject cargarDatosCarreras(int id) {
        //  Crea la variable de tipo Objeto
        JsonObject c = new JsonObject();
        try {

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM carreras WHERE id_carrera=?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                
                c.addProperty("nombre_carrera", rs.getString("nombre_carrera"));
                c.addProperty("facultad", listarFacultad(rs.getInt("facultad_idfacultad")));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // retorna el objeto
        return c;
    }
    
    public JsonArray listarCarrera() {

        JsonArray genero = new JsonArray();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM carreras");
            while (rs.next()) {
                JsonObject c = new JsonObject();

                c.addProperty("id_carrera", rs.getString("id_carrera"));
                c.addProperty("nombre_carrera", rs.getString("nombre_carrera"));
                genero.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return genero;
    }
    
    public String listarFacultad(int id) {
        String op = "<option selected value=''>Selecciona</option>";
//        System.out.println(id);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM facultad");
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id_tipo = rs.getInt("idfacultad");
                
                    if (id_tipo == id) {

                        op = op + "<option selected value='" + rs.getInt("idfacultad") + "'>" + rs.getString("descripcion") + "</option>";

                    } else {
                        op = op + "<option  value='" + rs.getInt("idfacultad") + "'>" + rs.getString("descripcion") + "</option>";
                    }
                
            }
        } catch (SQLException e) {
            System.out.println(e + " LISTAR GENERO");
        }

        return op;
    }
}
