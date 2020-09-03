/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete.modelo;

/**
 *
 * @author USUARIO
 */
public class Material {

    private int id_material;
    private String isbn;
    private String issn;
    private String titulo;
    private String año_publicacion;
    private String estado;
    private int id_editorial;
    private String fecha_ingreso;
    private String dewey;
    private double numero_entrada;
    private int id_tipo_material;
    
    //OTROOS
    private String titulo_grado;
    private String autores;
    private String carrera;
    private String genero;

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

   

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    //Relacion de muchos a muchos
    private int id_carrera;
    private int id_autor;
    private int id_genero;

    public int getId_tipo_material() {
        return id_tipo_material;
    }

    public void setId_tipo_material(int id_tipo_material) {
        this.id_tipo_material = id_tipo_material;
    }

    public int getId_carrera() {
        return id_carrera;
    }

    public void setId_carrera(int id_carrera) {
        this.id_carrera = id_carrera;
    }

    public int getId_autor() {
        return id_autor;
    }

    public void setId_autor(int id_autor) {
        this.id_autor = id_autor;
    }

    public int getId_genero() {
        return id_genero;
    }

    public void setId_genero(int id_genero) {
        this.id_genero = id_genero;
    }
    
    public int getId_material() {
        return id_material;
    }

    public void setId_material(int id_material) {
        this.id_material = id_material;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getIssn() {
        return issn;
    }

    public void setIssn(String issn) {
        this.issn = issn;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getId_editorial() {
        return id_editorial;
    }

    public void setId_editorial(int id_editorial) {
        this.id_editorial = id_editorial;
    }

    public String getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(String fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public String getDewey() {
        return dewey;
    }

    public void setDewey(String dewey) {
        this.dewey = dewey;
    }

    public double getNumero_entrada() {
        return numero_entrada;
    }

    public void setNumero_entrada(double numero_entrada) {
        this.numero_entrada = numero_entrada;
    }

    public String getTitulo_grado() {
        return titulo_grado;
    }

    public void setTitulo_grado(String titulo_grado) {
        this.titulo_grado = titulo_grado;
    }

    public String getAutores() {
        return autores;
    }

    public void setAutores(String autores) {
        this.autores = autores;
    }
    

}
