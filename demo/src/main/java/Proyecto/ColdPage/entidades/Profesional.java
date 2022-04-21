package Proyecto.ColdPage.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Profesional {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String profesion;
    private String zonaDeTrabajo;
    private Double promedioCalificacion;
    private String nombre;
    private Long contacto;
    @OneToOne
    private Usuario usuario;

    public Profesional() {
    }

    public Profesional(String id, String profesion, String zonaDeTrabajo, Double promedioCalificacion, String nombre, Long contacto, Usuario usuario) {
        this.id = id;
        this.profesion = profesion;
        this.zonaDeTrabajo = zonaDeTrabajo;
        this.promedioCalificacion = promedioCalificacion;
        this.nombre = nombre;
        this.contacto = contacto;
        this.usuario = usuario;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public String getZonaDeTrabajo() {
        return zonaDeTrabajo;
    }

    public void setZonaDeTrabajo(String zonaDeTrabajo) {
        this.zonaDeTrabajo = zonaDeTrabajo;
    }

    public Double getPromedioCalificacion() {
        return promedioCalificacion;
    }

    public void setPromedioCalificacion(Double promedioCalificacion) {
        this.promedioCalificacion = promedioCalificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getContacto() {
        return contacto;
    }

    public void setContacto(Long contacto) {
        this.contacto = contacto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
