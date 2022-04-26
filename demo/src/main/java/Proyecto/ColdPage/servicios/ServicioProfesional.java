package Proyecto.ColdPage.servicios;

import Proyecto.ColdPage.entidades.Imagen;
import Proyecto.ColdPage.entidades.Profesional;
import Proyecto.ColdPage.entidades.Trabajo;
import Proyecto.ColdPage.entidades.Usuario;
import Proyecto.ColdPage.repositorios.RepositorioProfesional;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServicioProfesional {
    
    @Autowired
    private RepositorioProfesional rp;
    
    @Transactional
    public Profesional crearProfesional(String profesion, String zonaDeTrabajo, String nombre, Long contacto, Date fechaDeNacimiento, Imagen foto, Usuario usuario) throws Exception {
        validar(profesion, zonaDeTrabajo, nombre, contacto, fechaDeNacimiento, foto, usuario);
        Profesional profesional = new Profesional(profesion, zonaDeTrabajo, nombre, contacto, fechaDeNacimiento, foto);
        profesional.setUsuario(usuario);
        profesional.setPerfil(true);
        return rp.save(profesional);
    }
    
    @Transactional
    public Profesional modificarProfesional(String id, String profesion, String zonaDeTrabajo, String nombre, Long contacto, Date fechaDeNacimiento, Imagen foto, Boolean perfil, Usuario usuario) throws Exception {
        validarModificacion(profesion, zonaDeTrabajo, nombre, contacto, fechaDeNacimiento, foto, perfil, usuario);
        Profesional p = getOne(id);
        if (p == null) {
            throw new Exception("No existe un profesional con ese ID");
        }
        p.setProfesion(profesion);
        p.setZonaDeTrabajo(zonaDeTrabajo);
        p.setNombre(nombre);
        p.setContacto(contacto);
        p.setFechaDeNacimiento(fechaDeNacimiento);
        p.setFoto(foto);
        p.setPerfil(perfil);
        p.setUsuario(usuario);
        return rp.save(p);
    }
    
    @Transactional
    public void eliminarProfesional(String id) {
        Profesional p = getOne(id);
        rp.delete(p);
    }
    
    @Transactional
    public List<Profesional> findAll() {
        return rp.findAll();
    }
    
    @Transactional
    public Profesional getOne(String id) {
        return rp.getOne(id);
    }
    
    @Transactional
    public Profesional buscarPorUsuario(String id) {
        return rp.buscarPorUsuarioId(id);
    }


    public void validar(String profesion, String zonaDeTrabajo, String nombre, Long contacto, Date fechaDeNacimiento, Imagen foto, Usuario usuario) throws Exception {

        if (profesion == null || profesion.trim().isEmpty()) {
            throw new Exception("La profesion no puede estar vacía");
        }
        if (zonaDeTrabajo == null || zonaDeTrabajo.trim().isEmpty()) {
            throw new Exception("La zona de trabajo no puede estar vacia");
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new Exception("El nombre no puede estar vacio");
        }
        if (contacto == null || contacto <= 0) {
            throw new Exception("El contacto no puede estar vacio");
        }
        if (fechaDeNacimiento == null) {
            throw new Exception("La fecha de nacimiento no puede estar vacía");
        }
        if (foto == null) {
            throw new Exception("La foto no puede estar vacía");
        }
        if (usuario == null) {
            throw new Exception("El usuario no puede estar vacio");
        }
    }

    public void validarModificacion(String profesion, String zonaDeTrabajo, String nombre, Long contacto, Date fechaDeNacimiento, Imagen foto, Boolean perfil, Usuario usuario) throws Exception {

        if (profesion == null || profesion.trim().isEmpty()) {
            throw new Exception("La profesion no puede estar vacía");
        }
        if (zonaDeTrabajo == null || zonaDeTrabajo.trim().isEmpty()) {
            throw new Exception("La zona de trabajo no puede estar vacia");
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new Exception("El nombre no puede estar vacio");
        }
        if (contacto == null || contacto <= 0) {
            throw new Exception("El contacto no puede estar vacio");
        }
        if (fechaDeNacimiento == null) {
            throw new Exception("La fecha de nacimiento no puede estar vacía");
        }
        if (foto == null) {
            throw new Exception("La foto no puede estar vacía");
        }
        if (perfil == null) {
            throw new Exception("El perfil no puede ser nulo");
        }
        if (usuario == null) {
            throw new Exception("El usuario no puede estar vacio");
        }
    }
    
    public List<Profesional> buscarPorProfesion(String profesion) {
        return rp.buscarPorProfesion(profesion);
    }
    
    public void obtenerCalificacion(Profesional p) {
        int sumatoria = 0;
        for (Trabajo trabajo : p.getTrabajos()) {
            sumatoria = sumatoria + trabajo.getCalificacion();
        }
        double promCalificacion = (int) sumatoria * 1000 / p.getTrabajos().size();
        promCalificacion = (double) promCalificacion / 1000;
        p.setPromedioCalificacion(promCalificacion);
    }
}
