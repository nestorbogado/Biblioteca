
package paquete.modelo;


public class Prestamo {
    
    private int id_prestamo;
    private int id_libro;
    private int id_alumno;
    private String fecha_prestamo;
    private int dias_prestados;
    private String fecha_devolucion;
    private int dias_reales;
    private int diferencia;
    private String multa;

    public int getId_prestamo() {
        return id_prestamo;
    }

    public void setId_prestamo(int id_prestamo) {
        this.id_prestamo = id_prestamo;
    }

    public int getId_libro() {
        return id_libro;
    }

    public void setId_libro(int id_libro) {
        this.id_libro = id_libro;
    }

    public int getId_alumno() {
        return id_alumno;
    }

    public void setId_alumno(int id_alumno) {
        this.id_alumno = id_alumno;
    }

    public String getFecha_prestamo() {
        return fecha_prestamo;
    }

    public void setFecha_prestamo(String fecha_prestamo) {
        this.fecha_prestamo = fecha_prestamo;
    }

    public int getDias_prestados() {
        return dias_prestados;
    }

    public void setDias_prestados(int dias_prestados) {
        this.dias_prestados = dias_prestados;
    }

    public String getFecha_devolucion() {
        return fecha_devolucion;
    }

    public void setFecha_devolucion(String fecha_devolucion) {
        this.fecha_devolucion = fecha_devolucion;
    }

    public int getDias_reales() {
        return dias_reales;
    }

    public void setDias_reales(int dias_reales) {
        this.dias_reales = dias_reales;
    }

    public int getDiferencia() {
        return diferencia;
    }

    public void setDiferencia(int diferencia) {
        this.diferencia = diferencia;
    }

    public String getMulta() {
        return multa;
    }

    public void setMulta(String multa) {
        this.multa = multa;
    }
    
}
