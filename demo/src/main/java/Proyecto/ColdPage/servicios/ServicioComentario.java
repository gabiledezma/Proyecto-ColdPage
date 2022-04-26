package Proyecto.ColdPage.servicios;

import Proyecto.ColdPage.entidades.Comentario;
import Proyecto.ColdPage.entidades.Usuario;
import Proyecto.ColdPage.repositorios.RepositorioComentario;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServicioComentario {
    
    @Autowired
    private RepositorioComentario rc;
    
    @Transactional
    public Comentario crearComentario(String texto, Usuario usuario, Date fecha) throws Exception{
        validar(texto, usuario, fecha);
        
        Comentario c = new Comentario();
        c.setTexto(texto);
        c.setUsuario(usuario);
        c.setFecha(fecha);
        
        return rc.save(c);
    }
    
    @Transactional
    public Comentario modificarComentario(String id, String texto) throws Exception{
        validarID(id);
        Comentario c = rc.getById(id);
        
        c.setTexto(texto);
        return rc.save(c);
    }
    
    @Transactional
    public void eliminarComentario(String id) throws Exception{
        validarID(id);
        Comentario c = rc.getById(id);
        rc.delete(c);
        
    }
    
    public void validar(String texto, Usuario usuario, Date fecha)throws Exception{
        if(texto == null || texto.trim().isEmpty()){
            throw new Exception("Debe introducir un comentario");
        }
        if(usuario == null){
            throw new Exception("El usuario existe");
        }
        if(fecha == null){
        throw new Exception("ERROR!. fecha no recibida");
        }
    }
    
    public void validarID(String id)throws Exception{
        if(id == null || id.trim().isEmpty()){
            throw new Exception("ID invalido");
        }
        
    }
}
