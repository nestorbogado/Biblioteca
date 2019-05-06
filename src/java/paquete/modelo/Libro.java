
package paquete.modelo;

/**
 *
 * @author USUARIO
 */
public class Libro {
    
    // Variable de tipo entero para el id del libro
    private int id_libro;
    
    // Variable de tipo cadena para el ISBN del libro
    private String isbn;
    
    // Variable de tipo cadena para el titulo del libro
    private String titulo;
    
    // Variable de tipo cadena para el año de publicación del libro
    private String año_publicacion;
    
    // Variable de tipo entero para el id editorial que corresponde al libro
    private int id_editorial;
    
    // Variable de tipo entero para el id del autor que corresponde al libro
    private int id_autor;
    
    // Variable de tipo entero para el id del genero que corresponde al libro
    private int id_genero;
    
    // Variable de tipo cadena para el estado del libro
    private String estado;

    private int categoria;

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }
    public int getId_genero() {
        return id_genero;
    }

    public void setId_genero(int id_genero) {
        this.id_genero = id_genero;
    }

    public int getId_libro() {
        return id_libro;
    }

    public void setId_libro(int id_libro) {
        this.id_libro = id_libro;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAño_publicacion() {
        return año_publicacion;
    }

    public void setAño_publicacion(String año_publicacion) {
        this.año_publicacion = año_publicacion;
    }

    public int getId_editorial() {
        return id_editorial;
    }

    public void setId_editorial(int id_editorial) {
        this.id_editorial = id_editorial;
    }

    public int getId_autor() {
        return id_autor;
    }

    public void setId_autor(int id_autor) {
        this.id_autor = id_autor;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
