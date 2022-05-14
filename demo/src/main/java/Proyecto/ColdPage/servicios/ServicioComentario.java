package Proyecto.ColdPage.servicios;

import Proyecto.ColdPage.entidades.Comentario;
import Proyecto.ColdPage.repositorios.RepositorioComentario;
import Proyecto.ColdPage.repositorios.RepositorioUsuario;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServicioComentario {

    @Autowired
    private RepositorioComentario rc;

    @Autowired
    private RepositorioUsuario ru;

    @Transactional
    public Comentario crearComentario(String texto, String usuario) throws Exception {
        validar(texto);
        Comentario c = new Comentario();
        c.setTexto(texto);
        c.setUsuario(ru.getById(usuario));
        Date fecha = new Date();
        c.setFecha(fecha);

        return rc.save(c);
    }

    @Transactional
    public Comentario modificarComentario(String id, String texto, String foto) throws Exception {
        Comentario c = validarID(id);
        c.setTexto(texto);
        Date fecha = new Date();
        c.setFecha(fecha);
        if (foto == null || foto.trim().isEmpty()) {
            c.setFoto(null);
        } else {
            c.setFoto(foto);
        }
        return rc.save(c);
    }

    @Transactional
    public void eliminarComentario(String id) throws Exception {
        validarID(id);
        Comentario c = rc.getById(id);
        rc.delete(c);

    }

    public void validar(String texto) throws Exception {
        if (texto == null || texto.trim().isEmpty()) {
            throw new Exception("Debe introducir un comentario");
        }  
    }

    public Comentario validarID(String id) throws Exception {
        Comentario c = rc.getById(id);
        if (c == null) {
            throw new Exception("No existe un trabajo con ese ID");
        }
        return c;
    }
}
