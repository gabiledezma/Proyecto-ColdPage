package Proyecto.ColdPage.servicios;

import Proyecto.ColdPage.entidades.Cliente;
import Proyecto.ColdPage.entidades.Imagen;
import Proyecto.ColdPage.entidades.Profesional;
import Proyecto.ColdPage.entidades.Trabajo;
import Proyecto.ColdPage.repositorios.RepositorioTrabajo;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServicioTrabajo {

    @Autowired
    private RepositorioTrabajo rt;


    @Transactional
    public Trabajo crearTrabajo(Cliente cliente, Profesional profesional, String descripcion, String observaciones, String titulo, Integer costo, Date fecha, Boolean estado, List<Imagen> imagenes) throws Exception {
        validar(descripcion, observaciones, titulo, costo, fecha, estado);
        Trabajo t = new Trabajo();
        t.setTitulo(titulo);
        t.setDescripcion(descripcion);
        t.setObservaciones(observaciones);
        t.setCosto(costo);
        t.setFecha(fecha);
        if (profesional != null) {
            t.setProfesional(profesional);
        }
        if (cliente != null) {
            t.setCliente(cliente);
        }
        t.setEstado(estado);
        t.setFotos(imagenes);
        return rt.save(t);
    }

    @Transactional
    public Trabajo modificarTrabajo(String id, Cliente cliente, Profesional profesional, String descripcion, String observaciones, String titulo, Integer costo, Date fecha, Boolean estado, List<Imagen> imagenes) throws Exception {
        validarID(id);
        validar(descripcion, observaciones, titulo, costo, fecha, estado);
        Trabajo t = rt.getOne(id);
        t.setTitulo(titulo);
        t.setDescripcion(descripcion);
        t.setObservaciones(observaciones);
        t.setCosto(costo);
        t.setFecha(fecha);
        if (profesional != null) {
            t.setProfesional(profesional);
        }
        if (cliente != null) {
            t.setCliente(cliente);
        }
        t.setEstado(estado);
        t.setFotos(imagenes);
        return rt.save(t);
    }

    @Transactional
    public void eliminarTrabajo(String id) throws Exception {
        validarID(id);
        Trabajo t = rt.getOne(id);
        rt.delete(t);
    }

    @Transactional
    public Trabajo recibirCalificacion(String id, Integer calificacion) throws Exception {
        validarID(id);
        Trabajo t = rt.getById(id);
        t.setCalificacion(calificacion);
        return rt.save(t);
    }

    @Transactional
    public Trabajo baja(String id) throws Exception {
        validarID(id);
        Trabajo t = rt.getById(id);
        t.setEstado(false);
        return rt.save(t);
    }
    
    @Transactional
    public List<Trabajo> findByCalificacion(Integer calificacion) {
        return rt.findByCalificacion(calificacion);
    }

    public void validarID(String id) throws Exception {
        if (id == null || id.trim().isEmpty()) {
            throw new Exception("No existe una publicacion con ese ID");
        }
    }

    public void validar(String descripcion, String observaciones, String titulo, Integer costo, Date fecha, Boolean estado) throws Exception {

        if (descripcion == null || descripcion.trim().isEmpty()) {
            throw new Exception("Debe ingresar una descripcion");
        }
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new Exception("Debe ingresar un titulo");
        }
        if (costo == null) {
            throw new Exception("Debe ingersar un costo");
        }
        if (fecha == null) {
            throw new Exception("Debe ingresar una fecha");
        }
        if (estado == null) {
            throw new Exception("El estado esta vacio");
        }
    }

}
