package Proyecto.ColdPage.entidades;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Trabajo {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @OneToOne
    private Cliente cliente;
    @ManyToOne
    private Profesional profesional;
    private Integer calificacion;
    private String descripcion;
    private String observaciones;
    private String titulo; //trabajo a realizar
    private Integer costo;
    @Temporal(TemporalType.DATE)
    private Date fecha;
    private Boolean estado;
    @OneToMany
    private List<Imagen> fotos;
    // en caso del profesional si necesita ayuda puede generar alertas de trabajo

    public Trabajo() {
    }

    public Trabajo(Cliente cliente, String descripcion, String titulo, Date fecha, Boolean estado) {
        this.cliente = cliente;
        this.descripcion = descripcion;
        this.titulo = titulo;
        this.fecha = fecha;
        this.estado = estado;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public List<Imagen> getFotos() {
        return fotos;
    }

    public void setFotos(List<Imagen> fotos) {
        this.fotos = fotos;
    }

   
}
