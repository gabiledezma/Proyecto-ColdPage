package Proyecto.ColdPage.servicios;

import Proyecto.ColdPage.entidades.Comentario;
import Proyecto.ColdPage.entidades.Imagen;
import Proyecto.ColdPage.entidades.Publicacion;
import Proyecto.ColdPage.entidades.Usuario;
import Proyecto.ColdPage.repositorios.RepositorioPublicacion;
import Proyecto.ColdPage.repositorios.RepositorioUsuario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServicioPublicacion {

    @Autowired
    private RepositorioPublicacion rp;

    @Autowired
    private RepositorioUsuario ru;

    @Transactional
    public Publicacion crearPublicacion(Usuario usuario, String titulo, String texto) throws Exception {
        validar(titulo, texto, usuario);
        Publicacion p = new Publicacion();
        p.setTitulo(titulo);
        p.setTexto(texto);
        ru.findByEmail(usuario.getEmail());
        return rp.save(p);
    }

    @Transactional
    public Publicacion modificarPublicacion(String id, String titulo, String texto, Usuario usuario) throws Exception {
        validarID(id);
        validar(titulo, texto, usuario);
        Publicacion p = rp.getOne(id);
        p.setTexto(texto);
        p.setTitulo(titulo);
        return rp.save(p);
    }

    @Transactional
    public void eliminarPublicacion(String id) throws Exception {
        validarID(id);
        Publicacion p = rp.getOne(id);
        rp.delete(p);
    }

    @Transactional
    public Publicacion recibirComentarios(Comentario comentario, String id) throws Exception {
        validarID(id);
        Publicacion p = rp.getById(id);
        List<Comentario> c = p.getComentarios();
        c.add(comentario);
        p.setComentarios(c);
        return rp.save(p);
    }
    
    @Transactional
    public Publicacion recibirImagen(Imagen imagen, String id)throws Exception{
        validarID(id);
        Publicacion p = rp.getById(id);
        List<Imagen> i = p.getImagen();
        i.add(imagen);
        p.setImagen(i);
        return rp.save(p);
    }
    
    @Transactional
    public void listarComentarios(String id){
        rp.listarComentarios(id);
    }
    
    @Transactional
    public List<Publicacion> listarPublicaciones(){
        return rp.findAll();
    }

    public void validarID(String id) throws Exception {
        if (id == null || id.trim().isEmpty()) {
            throw new Exception("No existe una publicacion con ese ID");
        }
    }

    public void validar(String titulo, String texto, Usuario usuario) throws Exception {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new Exception("Debe ingresar un titulo");
        }
        if (texto == null || texto.trim().isEmpty()) {
            throw new Exception("Debe ingresar una descripcion");
        }
        if (usuario == null) {
            throw new Exception("Error!. Usuario no encontrado");
        }
    }
}
