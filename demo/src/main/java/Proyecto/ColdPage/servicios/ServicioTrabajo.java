package Proyecto.ColdPage.servicios;

import Proyecto.ColdPage.entidades.Imagen;
import Proyecto.ColdPage.entidades.Trabajo;
import Proyecto.ColdPage.entidades.Usuario;
import Proyecto.ColdPage.repositorios.RepositorioTrabajo;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServicioTrabajo {

    @Autowired
    private RepositorioTrabajo rt;


    @Transactional
    public Trabajo crearTrabajo(Usuario cliente, String descripcion, String titulo, String fecha) throws Exception {
        validar(descripcion, titulo, fecha);
        Trabajo t = new Trabajo();
        t.setCliente(cliente);
        t.setDescripcion(descripcion);
        t.setTitulo(titulo);
        String anio = fecha.substring(0, 4);
        String mes = fecha.substring(5, 7);
        String dia = fecha.substring(8, 10);
        int anioInt = Integer.parseInt(anio);
        int mesInt = Integer.parseInt(mes);
        int diaInt = Integer.parseInt(dia);
        Date fechaD = new Date(anioInt - 1900, mesInt - 1, diaInt);
        t.setFecha(fechaD);
        t.setEstado(true);
        return rt.save(t);
    }

    @Transactional
    public Trabajo modificarTrabajo(String id,Usuario cliente, String descripcion, String titulo, String fecha) throws Exception {
        validarID(id);
        validar(descripcion, titulo, fecha);
        Trabajo t = rt.getById(id);
        t.setTitulo(titulo);
        t.setDescripcion(descripcion);
        String anio = fecha.substring(0, 4);
        String mes = fecha.substring(5, 7);
        String dia = fecha.substring(8, 10);
        int anioInt = Integer.parseInt(anio);
        int mesInt = Integer.parseInt(mes);
        int diaInt = Integer.parseInt(dia);
        Date fechaD = new Date(anioInt - 1900, mesInt - 1, diaInt);
        t.setFecha(fechaD);
        t.setEstado(Boolean.TRUE);
        return rt.save(t);
    }
    
    @Transactional
    public void eliminarTrabajo(String id) throws Exception {
        validarID(id);
        Trabajo t = rt.getById(id);
        rt.delete(t);
    }
    
    @Transactional
    public Trabajo darProfesionalYCosto(String id, Usuario profesional, Integer costo) throws Exception{
        validarID(id);
        Trabajo t = rt.getById(id);
        t.setProfesional(profesional);
        t.setCosto(costo);
        return rt.save(t);
    }
    
    @Transactional
    public Trabajo darObservacion(String id, String observacion) throws Exception{
        validarID(id);
        Trabajo t = rt.getById(id);
        t.setObservaciones(observacion);
        return rt.save(t);
    }
    
    @Transactional
    public Trabajo darImagen(String id, Imagen imagen) throws Exception{
        validarID(id);
        Trabajo t = rt.getById(id);
        List<Imagen> i = t.getFotos();
        i.add(imagen);
        t.setFotos(i);
        return rt.save(t);
    }
    
    @Transactional
    public Trabajo recibirCalificacion(String id, Integer calificacion) throws Exception {
        validarID(id);
        Trabajo t = rt.getById(id);
        t.setCalificacion(calificacion);
        return rt.save(t);
    }

    @Transactional
    public Trabajo baja(String id) throws Exception {
        validarID(id);
        Trabajo t = rt.getById(id);
        t.setEstado(false);
        return rt.save(t);
    }

    @Transactional
    public List<Trabajo> findByCalificacion(Integer calificacion) {
        return rt.findByCalificacion(calificacion);
    }

    @Transactional
    public List<Trabajo> listarTrabajo(){
        return rt.findAll();
    }
    
    public void validarID(String id) throws Exception {
        if (id == null || id.trim().isEmpty()) {
            throw new Exception("No existe un trabajo con ese ID");
        }
    }

    public void validar(String descripcion, String titulo, String fecha) throws Exception {

        if (descripcion == null || descripcion.trim().isEmpty()) {
            throw new Exception("Debe ingresar una descripcion");
        }
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new Exception("Debe ingresar un titulo");
        }
        if (fecha == null || fecha.trim().isEmpty()) {
            throw new Exception("Debe ingresar una fecha");
        }
    }

}
