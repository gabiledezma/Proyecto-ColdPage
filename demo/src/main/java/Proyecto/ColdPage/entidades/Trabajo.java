package Proyecto.ColdPage.entidades;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
    private Usuario cliente;
    @OneToOne
    private Usuario profesional;
    private Integer calificacion; // la da el cliente al finalizar
    private String descripcion;
    private String observaciones; //detalles que ve el cliente al finalizar
    private String titulo; //trabajo a realizar
    private Integer costo;
    @Temporal(TemporalType.DATE)
    private Date fecha;
    private Boolean estado; //terminado o no
    @OneToMany
    private List<Imagen> fotos;
    // en caso del profesional si necesita ayuda puede generar alertas de trabajo

    public Trabajo() {
    }

    public Trabajo(Usuario cliente, String descripcion, String titulo, Date fecha, Boolean estado) {
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

    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public Usuario getProfesional() {
        return profesional;
    }

    public void setProfesional(Usuario profesional) {
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
