package Proyecto.ColdPage.servicios;

import Proyecto.ColdPage.entidades.Profesional;
import Proyecto.ColdPage.entidades.Usuario;
import Proyecto.ColdPage.repositorios.RepositorioProfesional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServicioProfesional {

    @Autowired
    private RepositorioProfesional rp;

    @Transactional
    public Profesional crearProfesional(String profesion, String zonaDeTrabajo, String nombre, Long contacto, Usuario usuario) throws Exception {
        validar(profesion, zonaDeTrabajo, nombre, contacto, usuario);
        Profesional profesional = new Profesional();
        profesional.setProfesion(profesion);
        profesional.setZonaDeTrabajo(zonaDeTrabajo);
        profesional.setNombre(nombre);
        profesional.setContacto(contacto);
        profesional.setUsuario(usuario);
        return rp.save(profesional);
    }

    @Transactional
    public Profesional modificarProfesional(String id, String profesion, String zonaDeTrabajo, String nombre, Long contacto, Usuario usuario) throws Exception {
        validarModificacion(profesion, zonaDeTrabajo, nombre, contacto, usuario);
        Profesional p = getOne(id);
        if (p == null) {
            throw new Exception("No existe un profesional con esa ID");
        }
        p.setProfesion(profesion);
        p.setZonaDeTrabajo(zonaDeTrabajo);
        p.setNombre(nombre);
        p.setContacto(contacto);
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
        return rp.buscarPorUsuario(id);
    }

    public void validar(String profesion, String zonaDeTrabajo, String nombre, Long contacto, Usuario usuario) throws Exception {
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
        if (usuario == null) {
            throw new Exception("El usuario no puede estar vacio");
        }
    }

    public void validarModificacion(String profesion, String zonaDeTrabajo, String nombre, Long contacto, Usuario usuario) throws Exception {
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
        if (usuario == null) {
            throw new Exception("El usuario no puede estar vacio");
        }
    }

}
