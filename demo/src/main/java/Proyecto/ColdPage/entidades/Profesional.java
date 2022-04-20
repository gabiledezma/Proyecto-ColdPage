package Proyecto.ColdPage.entidades;

import Proyecto.ColdPage.enums.Role;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Profesional extends Usuario {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String profesion;
    private String zonaDeTrabajo;
    private Double promedioCalificacion;

    public Profesional() {
        super();
    }

    public Profesional(String profesion, String zonaDeTrabajo, Double promedioCalificacion, String email, String password, Role role, String nombre, Integer contacto) {
        super(email, password, role, nombre, contacto);
        this.profesion = profesion;
        this.zonaDeTrabajo = zonaDeTrabajo;
        this.promedioCalificacion = promedioCalificacion;
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

}
