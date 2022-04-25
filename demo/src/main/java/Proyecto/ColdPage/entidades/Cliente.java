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
public class Cliente {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String zonaDeResidencia;
    private String nombre;
    private Long contacto;
    @Temporal(TemporalType.DATE)
    private Date fechaDeNacimiento;
    @OneToOne
    private Usuario usuario;
    @OneToMany
    private List<Publicacion> publicaciones;
    @OneToMany
    private List<Trabajo> trabajos;
    private String foto;
    private Boolean perfil; // publico true; privado false; por defecto publico;

    public Cliente() {

    }

    public Cliente(String zonaDeResidencia, String nombre, Long contacto, Date fechaDeNacimiento, String foto) {
        this.zonaDeResidencia = zonaDeResidencia;
        this.nombre = nombre;
        this.contacto = contacto;
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.foto = foto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getZonaDeResidencia() {
        return zonaDeResidencia;
    }

    public void setZonaDeResidencia(String zonaDeResidencia) {
        this.zonaDeResidencia = zonaDeResidencia;
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

    public List<Publicacion> getPublicaciones() {
        return publicaciones;
    }

    public void setPublicaciones(List<Publicacion> publicaciones) {
        this.publicaciones = publicaciones;
    }

    public List<Trabajo> getTrabajos() {
        return trabajos;
    }

    public void setTrabajos(List<Trabajo> trabajos) {
        this.trabajos = trabajos;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Date getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(Date fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public Boolean getPerfil() {
        return perfil;
    }

    public void setPerfil(Boolean perfil) {
        this.perfil = perfil;
    }

}
