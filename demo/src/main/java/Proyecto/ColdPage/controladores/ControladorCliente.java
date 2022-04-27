package Proyecto.ColdPage.controladores;

import Proyecto.ColdPage.entidades.Usuario;
import Proyecto.ColdPage.servicios.ServicioCliente;
import Proyecto.ColdPage.servicios.ServicioDomicilio;
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
@RequestMapping("/cliente")
public class ControladorCliente {

    @Autowired
    private ServicioCliente sc;
    @Autowired
    private ServicioUsuario su;
    @Autowired
    private ServicioDomicilio sd;

    @GetMapping("/registro")
    public String formulario(ModelMap modelo) {
        modelo.put("domicilios", sd.findAll());
        modelo.put("usuarios", su.findAll());
        return "form-cliente";
    }

    @PostMapping("/registro")
    public String guardar(ModelMap modelo, @RequestParam String pais, @RequestParam String provincia, @RequestParam String localidad, @RequestParam String nombre, @RequestParam Long contacto, @RequestParam String foto, @RequestParam Date fechaDeNacimiento, @RequestParam Usuario usuario) {
        try {
            sc.crearCliente(pais, provincia, localidad, nombre, contacto, fechaDeNacimiento, foto, usuario);
            modelo.put("domicilios", sd.findAll());
            modelo.put("usuarios", su.findAll());
            modelo.put("exito", "Registro exitoso.");
            return "redirect:/";
        } catch (Exception e) {
            modelo.put("domicilios", sd.findAll());
            modelo.put("usuarios", su.findAll());
            modelo.put("error", "Faltó algún dato.");
            return "form-cliente";
        }
    }

    @GetMapping("/modificar/{id}") //PATHVARIABLE
    public String modificar(@PathVariable String id, ModelMap modelo) {
        modelo.put("domicilios", sd.findAll());
        modelo.put("usuarios", su.findAll());
        modelo.put("cliente", sc.getOne(id));
        return "form-cliente-modif";
    }

    @PostMapping("/modificar/{id}")
    public String modificar(ModelMap modelo, @PathVariable String id, @RequestParam String pais, @RequestParam String provincia, @RequestParam String localidad, @RequestParam String nombre, @RequestParam Long contacto, @RequestParam String foto, @RequestParam Date fechaDeNacimiento, @RequestParam Boolean perfil, @RequestParam Usuario usuario) {
        try {
            sc.modificarCliente(id, pais, provincia, localidad, nombre, contacto, fechaDeNacimiento, foto, perfil, usuario);
            modelo.put("domicilios", sd.findAll());
            modelo.put("usuarios", su.findAll());
            return "redirect:/cliente";
        } catch (Exception e) {
            modelo.put("domicilios", sd.findAll());
            modelo.put("usuarios", su.findAll());
            modelo.put("cliente", sc.getOne(id));
            modelo.put("error", "Faltó algún dato.");
            return "form-cliente-modif";
        }
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id) {
        try {
            sc.eliminarCliente(id);
            return "redirect:/index";
        } catch (Exception e) {
            return "redirect:/";
        }

    }

}
