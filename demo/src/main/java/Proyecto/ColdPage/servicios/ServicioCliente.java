package Proyecto.ColdPage.servicios;

import Proyecto.ColdPage.entidades.Cliente;
import Proyecto.ColdPage.entidades.Usuario;
import Proyecto.ColdPage.repositorios.RepositorioCliente;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServicioCliente {

    @Autowired
    private RepositorioCliente rc;

    @Transactional
    public Cliente crearCliente(String zonaDeResidencia, String nombre, Long contacto, Usuario usuario) throws Exception {

        validar(zonaDeResidencia, nombre, contacto, usuario);
        Cliente cliente = new Cliente();
        cliente.setZonaDeResidencia(zonaDeResidencia);
        cliente.setNombre(nombre);
        cliente.setContacto(contacto);
        cliente.setUsuario(usuario);
        return rc.save(cliente);
    }

    @Transactional
    public Cliente modificarCliente(String id, String zonaDeResidencia, String nombre, Long contacto, Usuario usuario) throws Exception {
        validarModificacion(zonaDeResidencia, nombre, contacto, usuario);
        Cliente c = getOne(id);
        if (c == null) {
            throw new Exception("No existe un cliente con esa ID");
        }
        c.setZonaDeResidencia(zonaDeResidencia);
        c.setNombre(nombre);
        c.setContacto(contacto);
        c.setUsuario(usuario);
        return rc.save(c);
    }

    @Transactional
    public void eliminarCliente(String id) {
        Cliente c = getOne(id);
        rc.delete(c);
    }

    @Transactional
    public List<Cliente> findAll() {
        return rc.findAll();
    }

    @Transactional
    public Cliente getOne(String id) {
        return rc.getOne(id);
    }

    @Transactional
    public Cliente buscarPorUsuario(String id) {
        return rc.buscarClientePorUsuario(id);
    }

    public void validar(String zonaDeResidencia, String nombre, Long contacto, Usuario usuario) throws Exception {
        if (zonaDeResidencia == null || zonaDeResidencia.trim().isEmpty()) {
            throw new Exception("La zona de residencia no puede estar vacia");
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

    public void validarModificacion(String zonaDeResidencia, String nombre, Long contacto, Usuario usuario) throws Exception {
        if (zonaDeResidencia == null || zonaDeResidencia.trim().isEmpty()) {
            throw new Exception("La zona de residencia no puede estar vacia");
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

    /*
    crea publicaciones y trabajos, las modifica, las elimina
     */
}
