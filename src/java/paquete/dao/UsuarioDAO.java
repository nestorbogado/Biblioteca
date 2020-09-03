package paquete.dao;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import paquete.conexion.BasedeDatos;
import paquete.modelo.Usuario;

/**
 *
 * @author USUARIO
 */
public class UsuarioDAO {

    private Connection connection;

    public UsuarioDAO() {

        connection = BasedeDatos.getConnection();
    }

    public int agregarUsuario(Usuario u) {
        String clave = org.apache.commons.codec.digest.DigestUtils.md5Hex(u.getContraseña());
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO usuarios(nombre_usuario, apellido_usuario, contraseña, usuario, tipo_usuario, estado) VALUES (?,?,?,?,?,?)");

            preparedStatement.setString(1, u.getNombre());
            preparedStatement.setString(2, u.getApellido());
            preparedStatement.setString(3, org.apache.commons.codec.digest.DigestUtils.md5Hex(u.getContraseña()));
            preparedStatement.setString(4, u.getUsuario());
            preparedStatement.setInt(5, u.getTipo_usuario());
            preparedStatement.setInt(6, u.getEstado());
            if (preparedStatement.executeUpdate() > 0) {
                return 1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public String listarEstado(int id) {
        String op = "";
//        System.out.println(id);
        if (id == 1) {
            op = op + "<option selected value='1'>ACTIVO</option><option value='0'>INACTIVO</option>";
        } else {
            op = op + "<option value='1'>Activo</ACTIVO><option selected value='0'>INACTIVO</option>";
        }

        return op;
    }

    public String tipoUsuario(int id) {
        String op = "";
//        System.out.println(id);
        if (id == 1) {
            op = op + "<option selected value='1'>ADMINISTRADOR/A</option><option value='2'>ENCARGADA/O</option>";
        } else {
            op = op + "<option value='1'>ADMINISTRADOR/A</option><option selected value='2'>ENCARGADA/O</option>";
        }

        return op;
    }

    public JsonArray listarTablaUsuario() {

        JsonArray user = new JsonArray();
        String aux = "";
        String aux1 = "";
        try {
            //Ejecuta la sentencia SQL
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM usuarios");
            while (rs.next()) {
                // SE CREA UNA VARIABLE DE TIPO OBJETO DE JSON JsonObject con el nombre de variable c
                JsonObject c = new JsonObject();
                //Agrega las propiedades al objeto
                //Ejemplo nombredelobjeto.addProperty("nombre con el que se guardará los datos", rs.getString("nombre de la columna de la base de datos"));
                c.addProperty("nombre", rs.getString("nombre_usuario") + " " + rs.getString("apellido_usuario"));
                c.addProperty("usuario", rs.getString("usuario"));
                if (rs.getInt("tipo_usuario") == 1) {
                    aux = "ADMINISTRADOR/A";
                } else {
                    aux = "ENCARGADA/O";
                }
                c.addProperty("tipo", aux);

                if (rs.getInt("estado") == 1) {
                    aux1 = "ACTIVO";
                    c.addProperty("anular", "<td><button title='Anular' href='#!' value='" + rs.getInt("id_usuario") + "' class='btn btn-danger anularUsuario'><i class='glyphicon glyphicon-remove'></i></button></td>");
                } else {
                    aux1 = "INACTIVO";
                    c.addProperty("anular", "<td><button disabled href='#!' value='' class='btn btn-danger anularUsuario'><i class='glyphicon glyphicon-remove'></i></button></td>");
                }
                c.addProperty("estado", aux1);
                c.addProperty("editar", "<td><button title='Editar' href='#!' value='" + rs.getInt("id_usuario") + "' class='btn btn-success actualizar' data-toggle='modal' data-target='#editar' data-backdrop='static' data-keyboard='false'><i class='glyphicon glyphicon-edit'></i></button></td>");
                

                //Agrega el objeto a la variable lista
                user.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Retorna la lista
        return user;
    }

    public JsonObject cargarDatos(int id) {
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        //  Crea la variable de tipo Objeto
        JsonObject c = new JsonObject();
        try {

            preparedStatement = connection.prepareStatement("SELECT * FROM usuarios WHERE id_usuario=?");
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            if (rs.absolute(1)) {
                //Agrega las propiedades al objeto
                //Ejemplo nombredelobjeto.addProperty("nombre con el que se guardará los datos", rs.getString("nombre de la columna de la base de datos"));
                c.addProperty("id_usuario", rs.getInt("id_usuario"));
                c.addProperty("nombre_usuario", rs.getString("nombre_usuario"));
                c.addProperty("apellido_usuario", rs.getString("apellido_usuario"));
                c.addProperty("usuario", rs.getString("usuario"));
                c.addProperty("tipo", tipoUsuario(rs.getInt("tipo_usuario")));
                c.addProperty("estado", listarEstado(rs.getInt("estado")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        // retorna el objeto
        return c;
    }

    public int actualizarUsuario(Usuario u) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE usuarios SET nombre_usuario=?, apellido_usuario=?, contraseña=?, usuario=?,tipo_usuario=?,estado=? WHERE id_usuario=?");

            preparedStatement.setString(1, u.getNombre());
            preparedStatement.setString(2, u.getApellido());
            preparedStatement.setString(3, org.apache.commons.codec.digest.DigestUtils.md5Hex(u.getContraseña()));
            preparedStatement.setString(4, u.getUsuario());
            preparedStatement.setInt(5, u.getTipo_usuario());
            preparedStatement.setInt(6, u.getEstado());
            preparedStatement.setInt(7, u.getId_usuario());
            if (preparedStatement.executeUpdate() > 0) {
                return 1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int actualizarUsuarioSinContraseña(Usuario u) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE usuarios SET nombre_usuario=?, apellido_usuario=?, usuario=?,tipo_usuario=?,estado=? WHERE id_usuario=?");

            preparedStatement.setString(1, u.getNombre());
            preparedStatement.setString(2, u.getApellido());
            preparedStatement.setString(3, u.getUsuario());
            preparedStatement.setInt(4, u.getTipo_usuario());
            preparedStatement.setInt(5, u.getEstado());
            preparedStatement.setInt(6, u.getId_usuario());
            if (preparedStatement.executeUpdate() > 0) {
                return 1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
//         public int eliminarUsuario(int id) {
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM usuarios WHERE id_usuario=?");
//
//            preparedStatement.setInt(1, id);
//            if (preparedStatement.executeUpdate() > 0) {
//                return 1;
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return 0;
//    }

    public int anularUsuario(Usuario u) {
        try {                                                                 //UPDATE `usuarios` SET `estado` = '1' WHERE `usuarios`.`id_usuario` = ?;
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE usuarios SET estado = ? WHERE id_usuario = ?");
            preparedStatement.setInt(1, u.getEstado());
            preparedStatement.setInt(2, u.getId_usuario());
            if (preparedStatement.executeUpdate() > 0) {
                return 1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
