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
import paquete.modelo.Material;

/**
 *
 * @author USUARIO
 */
public class MaterialDAO {

    private Connection connection;

    public MaterialDAO() {
        connection = BasedeDatos.getConnection();
    }

    public JsonArray listarTablaMaterial() {

        JsonArray material = new JsonArray();
        try {
            //Ejecuta la sentencia SQL
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM materiales,tipo_material WHERE tipo_material_id_tipo_material=id_tipo_material");
            while (rs.next()) {
                // SE CREA UNA VARIABLE DE TIPO OBJETO DE JSON JsonObject con el nombre de variable c
                JsonObject c = new JsonObject();
                //Agrega las propiedades al objeto
                //Ejemplo nombredelobjeto.addProperty("nombre con el que se guardará los datos", rs.getString("nombre de la columna de la base de datos"));
                c.addProperty("ISBN", rs.getString("ISBN"));
                c.addProperty("ISSN", rs.getString("ISSN"));
                c.addProperty("titulo", rs.getString("titulo"));
                c.addProperty("año_publicacion", rs.getString("año_publicacion"));
                c.addProperty("estado", rs.getString("estado"));
                c.addProperty("numero_entrada", rs.getDouble("numero_entrada"));
                c.addProperty("tipo_material", rs.getString("descripcion_material"));
                if (rs.getInt("categoria") == 1) {
                    c.addProperty("accion", "<td><button title='Modificar' href='#!' value='" + rs.getInt("id_material") + "' class='btn btn-success actualizarL' data-toggle='modal' data-target='#editar' data-backdrop='static' data-keyboard='false'><i class='glyphicon glyphicon-edit'></i></button> <button title='Eliminar' href='#!' value='" + rs.getInt("id_material") + "' class='btn btn-danger eliminarL'><i class='glyphicon glyphicon-remove'></i></button> <button title='Duplicar' href='#!' value='" + rs.getInt("id_material") + "' class='btn btn-warning duplicarL' data-toggle='modal' data-target='#agregarLibros' data-backdrop='static' data-keyboard='false'><i class='fa fa-plus'></i></button></td>");
//                      c.addProperty("duplicar", "<td><button title='Duplicar' href='#!' value='" + rs.getInt("id_material") + "' class='btn btn-warning duplicarL' data-toggle='modal' data-target='#agregarLibros' data-backdrop='static' data-keyboard='false'><i class='fa fa-plus'></i></button></td>");
                   
                }
                if (rs.getInt("categoria") == 2) {
                    c.addProperty("accion", "<td><button title='Modificar' href='#!' value='" + rs.getInt("id_material") + "' class='btn btn-success actualizarTI' data-toggle='modal' data-target='#ModificarTI' data-backdrop='static' data-keyboard='false'><i class='glyphicon glyphicon-edit'></i></button> <button title='Eliminar' href='#!' value='" + rs.getInt("id_material") + "' class='btn btn-danger eliminarTI'><i class='glyphicon glyphicon-remove'></i></button> <button title='Duplicar' href='#!' value='" + rs.getInt("id_material") + "' class='btn btn-warning duplicarTI' data-toggle='modal' data-target='#agregarTI' data-backdrop='static' data-keyboard='false'><i class='fa fa-plus'></i></button></td>");
//                      c.addProperty("duplicar", "<td><button title='Duplicar' href='#!' value='" + rs.getInt("id_material") + "' class='btn btn-warning duplicarTI' data-toggle='modal' data-target='#agregarTI' data-backdrop='static' data-keyboard='false'><i class='fa fa-plus'></i></button></td>");
                }
                if (rs.getInt("categoria") == 3) {
                    c.addProperty("accion", "<td><button title='Modificar' href='#!' value='" + rs.getInt("id_material") + "' class='btn btn-success actualizarR' data-toggle='modal' data-target='#ModificarR' data-backdrop='static' data-keyboard='false'><i class='glyphicon glyphicon-edit'></i></button> <button title='Eliminar' href='#!' value='" + rs.getInt("id_material") + "' class='btn btn-danger eliminarR'><i class='glyphicon glyphicon-remove'></i></button> <button title='Duplicar' href='#!' value='" + rs.getInt("id_material") + "' class='btn btn-warning duplicarR' data-toggle='modal' data-target='#agregarRevista' data-backdrop='static' data-keyboard='false'><i class='fa fa-plus'></i></button></td>");
//                      c.addProperty("duplicar", "<td><button title='Duplicar' href='#!' value='" + rs.getInt("id_material") + "' class='btn btn-warning duplicarR' data-toggle='modal' data-target='#agregarRevista' data-backdrop='static' data-keyboard='false'><i class='fa fa-plus'></i></button></td>");
                }
                //Agrega el objeto a la variable lista
                material.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Retorna la lista
        return material;
    }

    public int agregarMaterial(Material m) {
        int id = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO materiales(ISBN, titulo, año_publicacion, estado, editoriales_id_editorial, fecha_ingreso, numeracion_dewey, numero_entrada, tipo_material_id_tipo_material,categoria) VALUES (?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, m.getIsbn());
            preparedStatement.setString(2, m.getTitulo());
            preparedStatement.setString(3, m.getAño_publicacion());
            preparedStatement.setString(4, m.getEstado());
            preparedStatement.setInt(5, m.getId_editorial());
            preparedStatement.setString(6, m.getFecha_ingreso());
            preparedStatement.setString(7, m.getDewey());
            preparedStatement.setDouble(8, m.getNumero_entrada());
            preparedStatement.setInt(9, m.getId_tipo_material());
            preparedStatement.setInt(10, 1);
            if (preparedStatement.executeUpdate() > 0) {
                ResultSet rs = preparedStatement.getGeneratedKeys();
                if (rs.next()) {
                    id = rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public int agregarMaterial_Autor(Material m) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO materiales_y_autores(materiales_id_material, autores_id_autor) VALUES (?,?)");

            preparedStatement.setInt(1, m.getId_material());
            preparedStatement.setInt(2, m.getId_autor());

            if (preparedStatement.executeUpdate() > 0) {
                return 1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int agregarMaterial_Carrera(Material m) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO materiales_y_carreras(materiales_id_material, carreras_id_carrera) VALUES (?,?)");

            preparedStatement.setInt(1, m.getId_material());
            preparedStatement.setInt(2, m.getId_carrera());

            if (preparedStatement.executeUpdate() > 0) {
                return 1;
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    public int agregarMaterial_Genero(Material m) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO materiales_y_generos(materiales_id_material, generos_id_genero) VALUES (?,?)");

            preparedStatement.setInt(1, m.getId_material());
            preparedStatement.setInt(2, m.getId_genero());

            if (preparedStatement.executeUpdate() > 0) {
                return 1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int eliminarMaterial(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM materiales WHERE id_material=?");

            preparedStatement.setInt(1, id);
            if (preparedStatement.executeUpdate() > 0) {
                return 1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int eliminarMaterial_Autor(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM materiales_y_autores WHERE materiales_id_material=?");

            preparedStatement.setInt(1, id);
            if (preparedStatement.executeUpdate() > 0) {
                return 1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int eliminarMaterial_Carrera(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM materiales_y_carreras WHERE  materiales_id_material=?");

            preparedStatement.setInt(1, id);
            if (preparedStatement.executeUpdate() > 0) {
                return 1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int eliminarMaterial_Genero(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM materiales_y_generos WHERE materiales_id_material=?");

            preparedStatement.setInt(1, id);
            if (preparedStatement.executeUpdate() > 0) {
                return 1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int actualizarMaterial(Material m) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("UPDATE materiales SET ISBN=?,titulo=?,año_publicacion=?,estado=?,editoriales_id_editorial=?,fecha_ingreso=?,numeracion_dewey=?,numero_entrada=? WHERE id_material=?");

            preparedStatement.setString(1, m.getIsbn());
            preparedStatement.setString(2, m.getTitulo());
            preparedStatement.setString(3, m.getAño_publicacion());
            preparedStatement.setString(4, m.getEstado());
            preparedStatement.setInt(5, m.getId_editorial());
            preparedStatement.setString(6, m.getFecha_ingreso());
            preparedStatement.setString(7, m.getDewey());
            preparedStatement.setDouble(8, m.getNumero_entrada());
//            preparedStatement.setInt(9, m.getId_tipo_material());
            preparedStatement.setInt(9, m.getId_material());

            if (preparedStatement.executeUpdate() > 0) {
                return 1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public JsonObject cargarDatosMaterial(int id) {
        //  Crea la variable de tipo Objeto
        JsonObject c = new JsonObject();
        try {

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM materiales WHERE id_material=?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {

                //Agrega las propiedades al objeto
                //Ejemplo nombredelobjeto.addProperty("nombre con el que se guardará los datos", rs.getString("nombre de la columna de la base de datos"));
                c.addProperty("id_material", rs.getString("id_material"));
//                System.out.println("1" + rs.getString("id_material"));

                c.addProperty("ISBN", rs.getString("ISBN"));
//                System.out.println("2" + rs.getString("ISBN"));

                c.addProperty("titulo", rs.getString("titulo"));
//                System.out.println("3" + rs.getString("titulo"));

                c.addProperty("año_publicacion", rs.getString("año_publicacion"));
//                System.out.println("4" + rs.getString("año_publicacion"));

                c.addProperty("estado", listarEstadoMaterial(rs.getString("estado")));
//                System.out.println("5" + listarEstadoMaterial(rs.getString("estado")));

                c.addProperty("editoriales", listarEditorial(rs.getInt("editoriales_id_editorial")));
//                System.out.println("6" + listarEditorial(rs.getInt("editoriales_id_editorial")));

                c.addProperty("fecha_ingreso", rs.getString("fecha_ingreso"));
//                System.out.println("7" + rs.getString("fecha_ingreso"));

                c.addProperty("numeracion_dewey", rs.getString("numeracion_dewey"));
//                System.out.println("8" + rs.getString("numeracion_dewey"));

                c.addProperty("numero_entrada", rs.getDouble("numero_entrada"));
//                System.out.println("9" + rs.getDouble("numero_entrada"));

                c.addProperty("autores", rs.getString("autores"));
//                System.out.println("11" + rs.getString("autores"));

                c.addProperty("tipo_material", listarTipoMaterial(rs.getInt("tipo_material_id_tipo_material")));
//                System.out.println("12" + listarTipoMaterial(rs.getInt("tipo_material_id_tipo_material")));

                c.addProperty("genero", listarGenero(id));
//                System.out.println("13" + listarGenero(id));

                c.addProperty("autor", listarAutor(id));
//                System.out.println("14" + listarAutor(id));

                c.addProperty("carrera", listarCarrera(id));
//                System.out.println("15" + listarCarrera(id));
//                
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        // retorna el objeto
        return c;
    }

    //***************************************************************************
    //  CARGA LOS NIVEL DE GRADO
    //
    //***************************************************************************
    public String listarNivelGrado(String grado) {
        String op = "";
//        System.out.println(id);
        if (grado.equalsIgnoreCase("Grado")) {
            op = op + "<option selected value='Grado'>Grado</option><option value='Post Grado'>Post Grado</option>";
        } else {
            op = op + "<option value='Grado'>Grado</option><option selected value='Post Grado'>Post Grado</option>";
        }

        return op;
    }

    //***************************************************************************
    //  CARGA LOS ESTADOS
    //
    //***************************************************************************
    public String listarEstadoMaterial(String estado) {
        String op = "";
//        System.out.println(id);
        if (estado.equalsIgnoreCase("Permanente")) {
            op = op + "<option selected value='Permanente'>Permanente</option> <option value='Disponible'>Disponible</option> <option value='No Disponible'>No Disponible</option>";
        }
        if (estado.equalsIgnoreCase("Disponible")) {
             op = op + "<option value='Permanente'>Permanente</option> <option  selected value='Disponible'>Disponible</option> <option value='No Disponible'>No Disponible</option>";
        }
        if (estado.equalsIgnoreCase("No Disponible")){
            op = op + "<option value='Permanente'>Permanente</option> <option  value='Disponible'>Disponible</option> <option selected value='No Disponible'>No Disponible</option>";
        }

        return op;
    }

    //***************************************************************************
    //  CARGA LOS CARRERAS
    //
    //***************************************************************************
    public String listarTipoMaterial(int id) {
        String op = "";
//        System.out.println(id);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM tipo_material");
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id_tipo = rs.getInt("id_tipo_material");
                if (id_tipo != 1) {
                    if (id_tipo == id) {

                        op = op + "<option selected value='" + rs.getInt("id_tipo_material") + "'>" + rs.getString("descripcion_material") + "</option>";

                    } else {
                        op = op + "<option  value='" + rs.getInt("id_tipo_material") + "'>" + rs.getString("descripcion_material") + "</option>";
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e + " LISTAR GENERO");
        }

        return op;
    }

    //***************************************************************************
    //  CARGA LOS GENEROS
    //
    //***************************************************************************
    public String listarGenero(int id) {
        String op = "";
//        System.out.println(id);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM generos");
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id_genero = rs.getInt("id_genero");
                if (verificarGeneroMaterial(id, id_genero)) {

                    op = op + "<option selected value='" + rs.getInt("id_genero") + "'>" + rs.getString("nombre_genero") + "</option>";

                } else {
                    op = op + "<option  value='" + rs.getInt("id_genero") + "'>" + rs.getString("nombre_genero") + "</option>";
                }

            }
        } catch (SQLException e) {
            System.out.println(e + " LISTAR GENERO");
        }

        return op;
    }

    public boolean verificarGeneroMaterial(int id_material, int id_genero) {

        try {

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM materiales_y_generos WHERE materiales_id_material=? AND generos_id_genero=?");
            preparedStatement.setInt(1, id_material);
            preparedStatement.setInt(2, id_genero);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.absolute(1)) {
//                System.out.println(true+" GENERO Y MATERIAL");
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e + " GENERO Y MATERIAL");
        }
//        System.out.println(false+" GENERO Y MATERIAL");
        return false;
    }

    //***************************************************************************
    //  CARGA LOS AUTORES
    //
    //***************************************************************************
    public String listarAutor(int id) {
        String op = "";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM autores");
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id_autor = rs.getInt("id_autor");
                if (verificarAutorMaterial(id, id_autor)) {

                    op = op + "<option selected value='" + rs.getInt("id_autor") + "'>" + rs.getString("nombre_autor") + "</option>";

                } else {
                    op = op + "<option  value='" + rs.getInt("id_autor") + "'>" + rs.getString("nombre_autor") + "</option>";
                }

            }
        } catch (SQLException e) {
            System.out.println(e + " LISTAR AUTORES");
        }

        return op;
    }

    public boolean verificarAutorMaterial(int id_material, int id_autor) {

        try {

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM materiales_y_autores WHERE materiales_id_material=? AND autores_id_autor=?");
            preparedStatement.setInt(1, id_material);
            preparedStatement.setInt(2, id_autor);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.absolute(1)) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e + " AUTOR Y MATERIAL");
        }
        return false;
    }

    //***************************************************************************
    //  CARGA LAS CARRERAS
    //
    //***************************************************************************
    public String listarCarrera(int id) {
        String op = "";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM carreras");
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id_carrera = rs.getInt("id_carrera");
                if (verificarCarreraMaterial(id, id_carrera)) {

                    op = op + "<option selected value='" + rs.getInt("id_carrera") + "'>" + rs.getString("nombre_carrera") + "</option>";

                } else {
                    op = op + "<option  value='" + rs.getInt("id_carrera") + "'>" + rs.getString("nombre_carrera") + "</option>";
                }

            }
        } catch (SQLException e) {
            System.out.println(e + " LISTAR CARRERAS");
        }

        return op;
    }

    public boolean verificarCarreraMaterial(int id_material, int id_carrera) {
//        System.out.println(id_material+" LISTAR CARRERAS"+id_material);
        try {

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM materiales_y_carreras WHERE materiales_id_material=? AND carreras_id_carrera=?");
            preparedStatement.setInt(1, id_material);
            preparedStatement.setInt(2, id_carrera);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e + " CARRERAS Y MATERIAL");
        }
        return false;
    }

    public String listarEditorial(int id) {

        String op = "<option selected value=''>Selecciona</option> ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM editoriales");
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                if (id == rs.getInt("id_editorial")) {
                    op = op + "<option selected value='" + rs.getInt("id_editorial") + "'>" + rs.getString("nombre_editorial") + "</option>";
                } else {
                    op = op + "<option value='" + rs.getInt("id_editorial") + "'>" + rs.getString("nombre_editorial") + "</option>";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return op;
    }

    //*****************************************************************************
    //
    //
    //
    //******************************************************************************
    public JsonObject cargarDatosTI(int id) {
        //  Crea la variable de tipo Objeto
        JsonObject c = new JsonObject();
        try {

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM materiales WHERE id_material=?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {

                //Agrega las propiedades al objeto
                //Ejemplo nombredelobjeto.addProperty("nombre con el que se guardará los datos", rs.getString("nombre de la columna de la base de datos"));
                c.addProperty("id_material", rs.getInt("id_material"));
//                System.out.println("1" + rs.getString("id_material"));

                c.addProperty("titulo", rs.getString("titulo"));
//                System.out.println("3" + rs.getString("titulo"));

                c.addProperty("año_publicacion", rs.getString("año_publicacion"));
//                System.out.println("4" + rs.getString("año_publicacion"));

                c.addProperty("estado", listarEstadoMaterial(rs.getString("estado")));
//                System.out.println("5" + listarEstadoMaterial(rs.getString("estado")));

                c.addProperty("fecha_ingreso", rs.getString("fecha_ingreso"));
//                System.out.println("7" + rs.getString("fecha_ingreso"));

                c.addProperty("numero_entrada", rs.getDouble("numero_entrada"));
//                System.out.println("9" + rs.getDouble("numero_entrada"));

                c.addProperty("autores", rs.getString("autores"));
//                System.out.println("11" + rs.getString("autores"));

                c.addProperty("tipo_material", listarTipoMaterial(rs.getInt("tipo_material_id_tipo_material")));
//                System.out.println("12" + listarTipoMaterial(rs.getInt("tipo_material_id_tipo_material")));

//                
                c.addProperty("carrera", listarCarrera(rs.getInt("id_material")));
//                System.out.println("15" + listarCarrera(rs.getInt("id_material")));

                c.addProperty("grado", listarNivelGrado(rs.getString("titulo_grado")));
//                System.out.println("10" + rs.getString("titulo_grado"));

            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        // retorna el objeto
        return c;
    }

    public int agregarTI(Material m) {
        int id = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO materiales(titulo, año_publicacion, estado, fecha_ingreso, numero_entrada, titulo_grado, autores, tipo_material_id_tipo_material, categoria) VALUES (?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, m.getTitulo());
            preparedStatement.setString(2, m.getAño_publicacion());
            preparedStatement.setString(3, m.getEstado());
            preparedStatement.setString(4, m.getFecha_ingreso());
            preparedStatement.setDouble(5, m.getNumero_entrada());
            preparedStatement.setString(6, m.getTitulo_grado());
            preparedStatement.setString(7, m.getAutores());
            preparedStatement.setInt(8, m.getId_tipo_material());
            preparedStatement.setInt(9, 2);
            if (preparedStatement.executeUpdate() > 0) {
                ResultSet rs = preparedStatement.getGeneratedKeys();
                if (rs.next()) {
                    id = rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public int actualizarTI(Material m) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("UPDATE materiales SET titulo=?, año_publicacion=?, estado=?, fecha_ingreso=?, numero_entrada=?, titulo_grado=?, autores=?, tipo_material_id_tipo_material=? WHERE id_material=?");

            preparedStatement.setString(1, m.getTitulo());
            preparedStatement.setString(2, m.getAño_publicacion());
            preparedStatement.setString(3, m.getEstado());
            preparedStatement.setString(4, m.getFecha_ingreso());
            preparedStatement.setDouble(5, m.getNumero_entrada());
            preparedStatement.setString(6, m.getTitulo_grado());
            preparedStatement.setString(7, m.getAutores());
            preparedStatement.setInt(8, m.getId_tipo_material());
            preparedStatement.setInt(9, m.getId_material());
            if (preparedStatement.executeUpdate() > 0) {
                return 1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //*****************************************************************************
    //
    //
    //
    //******************************************************************************
    public int agregarR(Material m) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO materiales(ISSN, titulo, año_publicacion, estado, fecha_ingreso, numero_entrada, tipo_material_id_tipo_material, categoria,editoriales_id_editorial) VALUES (?,?,?,?,?,?,?,?,?)");
            
            preparedStatement.setString(1, m.getIssn());
            System.out.println("ISSN"+m.getIssn());
            preparedStatement.setString(2, m.getTitulo());
            preparedStatement.setString(3, m.getAño_publicacion());
            preparedStatement.setString(4, m.getEstado());
            preparedStatement.setString(5, m.getFecha_ingreso());
            preparedStatement.setDouble(6, m.getNumero_entrada());
            preparedStatement.setInt(7, m.getId_tipo_material());
            preparedStatement.setInt(8, 3);
            preparedStatement.setInt(9, 1);
            if (preparedStatement.executeUpdate() > 0) {
                return 1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public JsonObject cargarDatosRevista(int id) {
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        //  Crea la variable de tipo Objeto
        JsonObject c = new JsonObject();
        try {

            preparedStatement = connection.prepareStatement("SELECT * FROM materiales WHERE id_material=?");
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            if (rs.absolute(1)) {
                //Agrega las propiedades al objeto
                //Ejemplo nombredelobjeto.addProperty("nombre con el que se guardará los datos", rs.getString("nombre de la columna de la base de datos"));
                c.addProperty("id_material", rs.getString("id_material"));
                c.addProperty("ISSN", rs.getString("ISSN"));
                c.addProperty("titulo", rs.getString("titulo"));
                c.addProperty("año_publicacion", rs.getString("año_publicacion"));
                c.addProperty("estado", listarEstadoMaterial(rs.getString("estado")));
                c.addProperty("fecha_ingreso", rs.getString("fecha_ingreso"));
                c.addProperty("numero_entrada", rs.getDouble("numero_entrada"));
                c.addProperty("tipo_material", listarTipoMaterial(rs.getInt("tipo_material_id_tipo_material")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        // retorna el objeto
        return c;
    }

    public int actualizarR(Material m) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("UPDATE materiales SET ISSN=?, titulo=?, año_publicacion=?, estado=?, fecha_ingreso=?, numero_entrada=?, tipo_material_id_tipo_material=? WHERE id_material=?");
            
            preparedStatement.setString(1, m.getIssn());
            preparedStatement.setString(2, m.getTitulo());
            preparedStatement.setString(3, m.getAño_publicacion());
            preparedStatement.setString(4, m.getEstado());
            preparedStatement.setString(5, m.getFecha_ingreso());
            preparedStatement.setDouble(6, m.getNumero_entrada());
            preparedStatement.setDouble(7, m.getId_tipo_material());
            preparedStatement.setInt(8, m.getId_material());
            if (preparedStatement.executeUpdate() > 0) {
                return 1;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int verificarNumeroEntrada(Double numero) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM materiales WHERE numero_entrada=?");
            preparedStatement.setDouble(1, numero);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.absolute(1)) {
                return 1;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return 0;
    }

    public int InsertarTipoLibro() {
//        System.out.println(id);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM tipo_material WHERE id_tipo_material=1 AND descripcion_material='LIBROS'");
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.absolute(1)) {
                return 1;
            } else {
                preparedStatement = connection.prepareStatement("SELECT * FROM tipo_material ");
                rs = preparedStatement.executeQuery();
                if (!rs.absolute(1)) {
                    preparedStatement = connection.prepareStatement("INSERT INTO tipo_material (descripcion_material) VALUES(?)");
                    preparedStatement.setString(1, "LIBRO");
                    if (preparedStatement.executeUpdate() > 0) {
                        return 1;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e + " LISTAR GENERO");
        }

        return 0;
    }
}
