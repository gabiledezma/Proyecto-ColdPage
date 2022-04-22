package Proyecto.ColdPage.controladores;

import Proyecto.ColdPage.entidades.Usuario;
import Proyecto.ColdPage.servicios.ServicioCliente;
import Proyecto.ColdPage.servicios.ServicioUsuario;
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

    @GetMapping("/registro")
    public String formulario(ModelMap modelo) {
        return "form-cliente";
    }

    @PostMapping("/registro")
    public String guardar(ModelMap modelo, @RequestParam String zonaDeResidencia, @RequestParam String nombre, @RequestParam Long contacto, @RequestParam Usuario usuario) {
        try {
            sc.crearCliente(zonaDeResidencia, nombre, contacto, usuario);
            modelo.put("exito", "Registro exitoso.");
            return "form-cliente";
        } catch (Exception e) {
            modelo.put("error", "Faltó algún dato.");
            return "form-cliente";
        }
    }

    @GetMapping("/modificar/{id}") //PATHVARIABLE
    public String modificar(@PathVariable String id, ModelMap modelo) {
        modelo.put("cliente", sc.getOne(id));
        return "form-cliente-modif";
    }

    @PostMapping("/modificar/{id}")
    public String modificar(ModelMap modelo, @PathVariable String id, @RequestParam String zonaDeResidencia, @RequestParam String nombre, @RequestParam Long contacto, @RequestParam Usuario usuario) {
        try {
            sc.modificarCliente(id, zonaDeResidencia, nombre, contacto, usuario);
            return "redirect:/cliente";
        } catch (Exception e) {
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
