
package paquete.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import paquete.conexion.BasedeDatos;

/**
 *
 * @author USUARIO
 */
public class AccesoDAO {

    private Connection connection;

    public AccesoDAO() {
         connection = BasedeDatos.getConnection();
    }
    
    public boolean autenticacion(String usuario, String contraseña){
        
          try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM usuarios WHERE nombre_usuario=? AND contraseña=?");
            ps.setString(1, usuario);
            ps.setString(2, contraseña);
            ResultSet rs = ps.executeQuery();
            // Si encuentra almenos un resultado, ingresa el la condicional y retorna verdadero
            if (rs.absolute(1)) // encontrado
            {
                return true;
            }
        } catch (Exception ex) {
            System.out.println("Error en autenticacion de usuario" + ex.getMessage());
        }
        return false;
    }
    
}
