package Proyecto.ColdPage.controladores;

import Proyecto.ColdPage.entidades.Usuario;
import Proyecto.ColdPage.servicios.ServicioDomicilio;
import Proyecto.ColdPage.servicios.ServicioProfesional;
import Proyecto.ColdPage.servicios.ServicioUsuario;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/profesional")
public class ControladorProfesional {

    @Autowired
    private ServicioProfesional sp;
    @Autowired
    private ServicioUsuario su;
    @Autowired
    private ServicioDomicilio sd;

    @GetMapping("/registro/{id}")
    public String registro(ModelMap modelo, @PathVariable String id) {
        modelo.put("usuario", su.getOne(id));
        modelo.put("domicilios", sd.findAll());
        return "rprofesional";
    }

    @PostMapping("/registro/{id}")
    public String guardar(ModelMap modelo, @PathVariable String id, @RequestParam String profesion, @RequestParam String pais, @RequestParam String provincia, @RequestParam String localidad, @RequestParam String nombre, @RequestParam Long contacto, @RequestParam String foto, @RequestParam Date fechaDeNacimiento, @RequestParam Usuario usuario) {
        try {
            sp.crearProfesional(profesion, pais, provincia, localidad, nombre, contacto, fechaDeNacimiento, foto, usuario);
            modelo.put("usuario", su.getOne(id));
            modelo.put("domicilios", sd.findAll());
            modelo.put("exito", "Registro exitoso.");
            return "form-profesional";
        } catch (Exception e) {
            modelo.put("domicilios", sd.findAll());
            modelo.put("usuario", su.getOne(id));
            modelo.put("error", "Faltó algún dato.");
            return "rprofesional";
        }
    }

    @GetMapping("/modificar/{id}") //PATHVARIABLE
    public String modificar(@PathVariable String id, ModelMap modelo) {
        modelo.put("domicilios", sd.findAll());
        modelo.put("usuarios", su.findAll());
        modelo.put("profesional", sp.getOne(id));
        return "form-profesional-modif";
    }

    @PostMapping("/modificar/{id}")
    public String modificar(ModelMap modelo, @PathVariable String id, @RequestParam String profesion, @RequestParam String pais, @RequestParam String provincia, @RequestParam String localidad, @RequestParam String nombre, @RequestParam Long contacto, @RequestParam String foto, @RequestParam Date fechaDeNacimiento, @RequestParam Boolean perfil, @RequestParam Usuario usuario) {
        try {
            sp.modificarProfesional(id, profesion, pais, provincia, localidad, nombre, contacto, fechaDeNacimiento, foto, perfil, usuario);
            modelo.put("domicilios", sd.findAll());
            modelo.put("usuarios", su.findAll());
            return "redirect:/profesional";
        } catch (Exception e) {
            modelo.put("domicilios", sd.findAll());
            modelo.put("usuarios", su.findAll());
            modelo.put("profesional", sp.getOne(id));
            modelo.put("error", "Faltó algún dato.");
            return "form-profesional-modif";
        }
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id) {
        try {
            sp.eliminarProfesional(id);
            return "redirect:/index";
        } catch (Exception e) {
            return "redirect:/";
        }

    }
}
