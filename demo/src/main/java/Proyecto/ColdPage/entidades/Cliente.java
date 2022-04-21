package Proyecto.ColdPage.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
    private Integer contacto;
    @OneToOne
    private Usuario usuario;

    public Cliente() {

    }

    public Cliente(String id, String zonaDeResidencia, String nombre, Integer contacto, Usuario usuario) {
        this.id = id;
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

    public Integer getContacto() {
        return contacto;
    }

    public void setContacto(Integer contacto) {
        this.contacto = contacto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
