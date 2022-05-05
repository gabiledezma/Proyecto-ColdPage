package Proyecto.ColdPage.servicios;

import Proyecto.ColdPage.entidades.Cliente;
import Proyecto.ColdPage.entidades.Domicilio;
import Proyecto.ColdPage.entidades.Imagen;
import Proyecto.ColdPage.entidades.Usuario;
import Proyecto.ColdPage.repositorios.RepositorioCliente;
import Proyecto.ColdPage.repositorios.RepositorioUsuario;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServicioCliente {

    @Autowired
    private RepositorioCliente rc;
    @Autowired
    private RepositorioUsuario ru;

    @Transactional
    public Cliente crearCliente(String pais, String provincia, String localidad, String nombre, Long contacto, Date fechaDeNacimiento, String foto, Usuario usuario) throws Exception {
        Imagen imagen = new Imagen(foto);
        Domicilio domicilio = new Domicilio(pais, provincia, localidad);
        validar(domicilio, nombre, contacto, fechaDeNacimiento, imagen, usuario);
        Cliente cliente = new Cliente(domicilio, nombre, contacto, fechaDeNacimiento, imagen);
        cliente.setUsuario(usuario);
        cliente.setPerfil(true);
        return rc.save(cliente);
    }

    @Transactional
    public Cliente modificarCliente(String id, String pais, String provincia, String localidad, String nombre, Long contacto, Date fechaDeNacimiento, String foto, Boolean perfil, Usuario usuario) throws Exception {
        Imagen imagen = new Imagen(foto);
        Domicilio domicilio = new Domicilio(pais, provincia, localidad);
        validarModificacion(domicilio, nombre, contacto, fechaDeNacimiento, imagen, perfil, usuario);
        Cliente c = getOne(id);
        if (c == null) {
            throw new Exception("No existe un cliente con esa ID");
        }
        c.setDomicilio(domicilio);
        c.setNombre(nombre);
        c.setContacto(contacto);
        c.setFechaDeNacimiento(fechaDeNacimiento);
        c.setFoto(imagen);
        c.setUsuario(usuario);
        c.setPerfil(perfil);
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
        return rc.buscarPorUsuarioId(id);
    }

    public void validar(Domicilio domicilio, String nombre, Long contacto, Date fechaDeNacimiento, Imagen foto, Usuario usuario) throws Exception {
        if (domicilio == null) {
            throw new Exception("La zona de residencia no puede estar vacia");
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

    public void validarModificacion(Domicilio domicilio, String nombre, Long contacto, Date fechaDeNacimiento, Imagen foto, Boolean perfil, Usuario usuario) throws Exception {
        if (domicilio == null) {
            throw new Exception("La zona de residencia no puede estar vacia");
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

    public List<Cliente> buscarPorPais(String pais) {
        return rc.buscarPorPais(pais);
    }

    public List<Cliente> buscarPorProvincia(String pais, String provincia) {
        return rc.buscarPorProvincia(pais, provincia);
    }

    public List<Cliente> buscarPorLocalidad(String pais, String provincia, String localidad) {
        return rc.buscarPorLocalidad(pais, provincia, localidad);
    }
}
