package Proyecto.ColdPage.entidades;

import Proyecto.ColdPage.enums.Role;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Cliente extends Usuario {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String zonaDeResidencia;

    public Cliente() {
        super();
    }

    public Cliente(String zonaDeResidencia, String email, String password, Role role, String nombre, Integer contacto) {
        super(email, password, role, nombre, contacto);
        this.zonaDeResidencia = zonaDeResidencia;
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

    
}
