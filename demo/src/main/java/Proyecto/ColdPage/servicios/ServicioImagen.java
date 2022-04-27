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
    public List<Imagen> findAll() {
        return ri.findAll();
    }
}
