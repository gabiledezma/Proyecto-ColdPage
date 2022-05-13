package Proyecto.ColdPage.servicios;

import Proyecto.ColdPage.entidades.Trabajo;
import Proyecto.ColdPage.entidades.Usuario;
import Proyecto.ColdPage.repositorios.RepositorioTrabajo;
import Proyecto.ColdPage.repositorios.RepositorioUsuario;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServicioTrabajo {
    
    @Autowired
    private RepositorioTrabajo rt;
    @Autowired
    private RepositorioUsuario ru;
    
    @Transactional
    public Trabajo crearTrabajo(String id, String titulo, String descripcion, String foto) throws Exception {
        validar(titulo, descripcion);
        Trabajo t = new Trabajo(titulo, descripcion);
        Usuario u = ru.getById(id);
        t.setCliente(u);
        Date fechaD = new Date();
        t.setFecha(fechaD);
        t.setEstado(false);// true terminado, false sin terminar
        if (foto == null || foto.trim().isEmpty()) {
            t.setFoto(null);
        } else {
            t.setFoto(foto);
        }
        return rt.save(t);
    }

    // modificacion del cliente
    @Transactional
    public Trabajo modificarTrabajo(String id, String titulo, String descripcion, String estado, String calificacion, String observaciones, String foto) throws Exception {
        if (estado.equals("TERMINADO")) {
            Trabajo t = validarID(id);
            Date fechaD = new Date();
            t.setFecha(fechaD);
            t.setEstado(true);
            if (descripcion == null || descripcion.trim().isEmpty()) {
                t.setDescripcion(t.getDescripcion());
            } else {
                t.setDescripcion(descripcion);
            }
            if (titulo == null || titulo.trim().isEmpty()) {
                t.setTitulo(t.getTitulo());
            } else {
                t.setTitulo(titulo);
            }
            if (calificacion == null || calificacion.trim().isEmpty()) {
                t.setCalificacion(t.getCalificacion());
            } else {
                t.setCalificacion(Integer.parseInt(calificacion));
            }
            if (observaciones == null || observaciones.trim().isEmpty()) {
                t.setObservaciones(t.getObservaciones());
            } else {
                t.setObservaciones(observaciones);
            }
            return rt.save(t);
        } else {
            Trabajo t = validarID(id);
            Date fechaD = new Date();
            t.setFecha(fechaD);
            t.setEstado(false);
            if (descripcion == null || descripcion.trim().isEmpty()) {
                t.setDescripcion(t.getDescripcion());
            } else {
                t.setDescripcion(descripcion);
            }
            if (titulo == null || titulo.trim().isEmpty()) {
                t.setTitulo(t.getTitulo());
            } else {
                t.setTitulo(titulo);
            }
            return rt.save(t);
        }
    }
    
    @Transactional
    public void eliminarTrabajo(String id) throws Exception {
        Trabajo t = validarID(id);
        rt.delete(t);
    }
    
    @Transactional
    public Trabajo elegirProfesionalYCosto(String id, String profesional, String costo) throws Exception {
        Trabajo t = validarID(id);
        t.setProfesional(ru.getById(profesional));
        t.setCosto(Integer.parseInt(costo));
        return rt.save(t);
    }
    
    @Transactional
    public Trabajo darObservacion(String id, String observacion) throws Exception {
        Trabajo t = validarID(id);
        t.setObservaciones(observacion);
        return rt.save(t);
    }
    
    @Transactional
    public Trabajo recibirCalificacion(String id, Integer calificacion) throws Exception {
        Trabajo t = validarID(id);
        t.setCalificacion(calificacion);
        return rt.save(t);
    }
    
    @Transactional
    public Trabajo terminado(String id) throws Exception {
        Trabajo t = validarID(id);
        t.setEstado(true);
        return rt.save(t);
    }
    
    @Transactional
    public List<Trabajo> findByCalificacion(String calificacion) {
        return rt.findByCalificacion(calificacion);
    }
    
    @Transactional
    public List<Trabajo> listarTrabajos() {
        return rt.findAll();
    }
    
    public Trabajo validarID(String id) throws Exception {
        Trabajo t = rt.getById(id);
        if (t == null) {
            throw new Exception("No existe un trabajo con ese ID");
        }
        return t;
    }
    
    public void validar(String titulo, String descripcion) throws Exception {
        
        if (descripcion == null || descripcion.trim().isEmpty()) {
            throw new Exception("Debe ingresar una descripcion");
        }
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new Exception("Debe ingresar un titulo");
        }
    }
    
}
