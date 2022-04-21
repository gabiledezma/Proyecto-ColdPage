package Proyecto.ColdPage.entidades;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Trabajo {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @ManyToOne
    private Cliente cliente;
    @ManyToOne
    private Profesional profesional;
    private Integer calificacion;
    private String descripcion;
    private String observaciones;
    private String titulo; //trabajo a realizar
    private Integer costo;
    private Date fecha;
    private Boolean estado;
    
    // en caso del profesional si necesita ayuda puede generar alertas de trabajo

    public Trabajo() {
    }

    public Trabajo(Cliente cliente, Profesional profesional, Integer calificacion, String descripcion, String observaciones, String titulo, Integer costo, Date fecha) {
        this.cliente = cliente;
        this.profesional = profesional;
        this.calificacion = calificacion;
        this.descripcion = descripcion;
        this.observaciones = observaciones;
        this.titulo = titulo;
        this.costo = costo;
        this.fecha = fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Profesional getProfesional() {
        return profesional;
    }

    public void setProfesional(Profesional profesional) {
        this.profesional = profesional;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getCosto() {
        return costo;
    }

    public void setCosto(Integer costo) {
        this.costo = costo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

}
