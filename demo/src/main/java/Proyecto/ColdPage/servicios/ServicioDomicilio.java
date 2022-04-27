package Proyecto.ColdPage.servicios;

import Proyecto.ColdPage.entidades.Domicilio;
import Proyecto.ColdPage.repositorios.RepositorioDomicilio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServicioDomicilio {

    @Autowired
    private RepositorioDomicilio rd;

    @Transactional
    public Domicilio crearDomicilio(String pais, String provincia, String localidad) throws Exception {
        validar(pais, provincia, localidad);
        Domicilio domicilio = new Domicilio(pais, provincia, localidad);
        return rd.save(domicilio);
    }

    @Transactional
    public Domicilio modificarDomicilio(String id, String pais, String provincia, String localidad) throws Exception {
        validarModificacion(pais, provincia, localidad);
        Domicilio d = getOne(id);
        if (d == null) {
            throw new Exception("No existe un profesional con ese ID");
        }
        d.setPais(pais);
        d.setProvincia(provincia);
        d.setLocalidad(localidad);
        return rd.save(d);
    }

    @Transactional
    public void eliminarDomicilio(String id) {
        Domicilio d = getOne(id);
        rd.delete(d);
    }

    @Transactional
    public List<Domicilio> findAll() {
        return rd.findAll();
    }

    @Transactional
    public Domicilio getOne(String id) {
        return rd.getOne(id);
    }

    public void validar(String pais, String provincia, String localidad) throws Exception {

        if (pais == null || pais.trim().isEmpty()) {
            throw new Exception("El pais no puede estar vacío");
        }
        if (provincia == null || provincia.trim().isEmpty()) {
            throw new Exception("La provincia no puede estar vacía");
        }
        if (localidad == null || localidad.trim().isEmpty()) {
            throw new Exception("La localidad no puede estar vacía");
        }

    }

    public void validarModificacion(String pais, String provincia, String localidad) throws Exception {

        if (pais == null || pais.trim().isEmpty()) {
            throw new Exception("El pais no puede estar vacío");
        }
        if (provincia == null || provincia.trim().isEmpty()) {
            throw new Exception("La provincia no puede estar vacía");
        }
        if (localidad == null || localidad.trim().isEmpty()) {
            throw new Exception("La localidad no puede estar vacía");
        }
    }

    public List<Domicilio> buscarPorPais(String pais) {
        return rd.buscarPorPais(pais);
    }

    public List<Domicilio> buscarPorProvincia(String provincia) {
        return rd.buscarPorProvincia(provincia);
    }
    
    public List<Domicilio> buscarPorLocalidad(String localidad) {
        return rd.buscarPorLocalidad(localidad);
    }
    
}
