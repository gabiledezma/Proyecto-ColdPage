package Proyecto.ColdPage.entidades;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
    @OneToOne
    private Usuario usuario;
    @OneToMany
    private List<Publicacion> publicaciones;
    @OneToMany
    private List<Trabajo> trabajos;
    private String foto;

    public Cliente() {

    }

    public Cliente(String zonaDeResidencia, String nombre, Long contacto, Usuario usuario) {
        this.zonaDeResidencia = zonaDeResidencia;
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

}
