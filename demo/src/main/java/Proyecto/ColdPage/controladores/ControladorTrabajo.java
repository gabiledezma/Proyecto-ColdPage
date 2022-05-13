package Proyecto.ColdPage.controladores;

import Proyecto.ColdPage.entidades.Trabajo;
import Proyecto.ColdPage.entidades.Usuario;
import Proyecto.ColdPage.servicios.ServicioTrabajo;
import Proyecto.ColdPage.servicios.ServicioUsuario;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/trabajo")
public class ControladorTrabajo {

    @Autowired
    private ServicioTrabajo st;
    @Autowired
    private ServicioUsuario su;

    @PostMapping("/guardar")
    public String crearTrabajo(ModelMap modelo, @RequestParam String id, @RequestParam String titulo, @RequestParam String descripcion, String foto) {
        try {
            Trabajo t = st.crearTrabajo(id, titulo, descripcion, foto);
            modelo.put("exito", "creacion exitosa.");
            return "redirect:/";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            modelo.put("error", "Faltó algún dato.");
            return "redirect:/";
        }
    }

    @PostMapping("/editar")
    public String modificarTrabajo(RedirectAttributes redirectAttributes, ModelMap model, @PathVariable String id, @RequestParam String descripcion, @RequestParam String titulo, @RequestParam String estado, @RequestParam String calificacion, @RequestParam String observaciones, @RequestParam String foto) {
        try {
            Trabajo t = st.modificarTrabajo(id, titulo, descripcion, estado, calificacion, observaciones, foto);
            model.put("exito", "Trabajo modificado con exito");
            redirectAttributes.addFlashAttribute("exito", "Usuario modificado con exito");
        } catch (Exception e) {
            model.put("error", e.getMessage());
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/";
    }

    @PostMapping("/cambiarPrivacidad")
    public String cambiarPrivacidad(@RequestParam String id, RedirectAttributes redirectAttributes, ModelMap model) {
        try {
            Usuario u = su.cambiarPrivacidad(id);
            model.put("exito", "Usuario modificado con exito");
            redirectAttributes.addFlashAttribute("exito", "Usuario modificado con exito");
        } catch (Exception e) {
            model.put("error", e.getMessage());
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/usuario/perfil";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam String id) {
        try {
            st.eliminarTrabajo(id);
        } catch (Exception e) {

        }
        return "redirect:/";
    }
}
