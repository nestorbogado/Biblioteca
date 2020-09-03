
package paquete.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import paquete.conexion.BasedeDatos;
import paquete.modelo.Usuario;

/**
 *
 * @author USUARIO
 */
public class AccesoDAO {

    private Connection connection;

    public AccesoDAO() {
         connection = BasedeDatos.getConnection();
    }
    
    public Usuario autenticacion(String usuario, String contraseña){
            Usuario u= new Usuario();
          try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM usuarios WHERE usuario=? AND contraseña=? AND estado=1");
            ps.setString(1, usuario);
            ps.setString(2, org.apache.commons.codec.digest.DigestUtils.md5Hex(contraseña));
            ResultSet rs = ps.executeQuery();
            // Si encuentra almenos un resultado, ingresa el la condicional y retorna verdadero
            if (rs.absolute(1)) // encontrado
            {
                u.setId_usuario(rs.getInt("id_usuario"));
                u.setNombre(rs.getString("nombre_usuario")+" "+rs.getString("apellido_usuario"));
                u.setTipo_usuario(rs.getInt("tipo_usuario"));
            }
        } catch (Exception ex) {
            System.out.println("Error en autenticacion de usuario" + ex.getMessage());
        }
        return u;
    }
    
}
