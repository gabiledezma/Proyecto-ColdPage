package Proyecto.ColdPage.controladores;

import Proyecto.ColdPage.servicios.ServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/admin")
public class ControladorAdmin {

    @Autowired
    private ServicioUsuario su;

    @GetMapping("/")
    public String listarUsuarios(ModelMap model) {
        model.put("usuarios", su.findAll());
        return "list-usuarios";
    }

    @GetMapping("/role/{id}")
    public String cambiarRol(ModelMap modelo, @PathVariable String id) {
        try {
            su.cambiarRol(id);
            modelo.put("usuario", id);
        } catch (Exception e) {
        }
        return "redirect:/admin/";
    }

    @GetMapping("/role/{id}")
    public String hacerAdmin(ModelMap modelo, @PathVariable String id) {
        try {
            su.hacerAdmin(id);
            modelo.put("admin", id);
        } catch (Exception e) {
        }
        return "redirect:/admin/";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable String id) {
        try {
            su.eliminarUsuario(id);
        } catch (Exception e) {

        }
        return "redirect:/admin/";
    }
}
