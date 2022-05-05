package Proyecto.ColdPage.servicios;

import Proyecto.ColdPage.entidades.Imagen;
import Proyecto.ColdPage.repositorios.RepositorioImagen;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServicioImagen {

    @Autowired
    private RepositorioImagen ri;
    
    @Transactional
    public Imagen agregarImagen(String url){
        Imagen i = new Imagen();
        
        i.setNombre(url);
        
        return ri.save(i);   
    }
    
    @Transactional
    public void eliminarImagen(String id){
        
        Imagen i = ri.getById(id);
        
        ri.delete(i);
    }
    
    @Transactional
    public List<Imagen> findAll() {
        return ri.findAll();
    }

            
    public void validar(String id)throws Exception{
        if (id == null || id.trim().isEmpty()) {
            throw new Exception("No existe una imagen con este ID");
        }
    }
}
