package Proyecto.ColdPage.controladores;

import Proyecto.ColdPage.entidades.Comentario;
import Proyecto.ColdPage.entidades.Trabajo;
import Proyecto.ColdPage.entidades.Usuario;
import Proyecto.ColdPage.repositorios.RepositorioTrabajo;
import Proyecto.ColdPage.servicios.ServicioTrabajo;
import Proyecto.ColdPage.servicios.ServicioUsuario;
import java.util.List;
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
    @Autowired
    private RepositorioTrabajo rt;

    @PostMapping("/guardar")
    public String crearTrabajo(ModelMap modelo, HttpSession session, RedirectAttributes redirectAttributes, @RequestParam String id, @RequestParam String titulo, @RequestParam String descripcion, String foto) { // id del usuario
        try {
            Usuario u = (Usuario) session.getAttribute("usuariosession");
            modelo.put("usuario", u);
            Trabajo t = st.crearTrabajo(id, titulo, descripcion, foto);
            List<Trabajo> trabajos = u.getTrabajos();
            trabajos.add(t);
            u.setTrabajos(trabajos);
            modelo.put("exito", "creacion exitosa.");
            redirectAttributes.addFlashAttribute("exito", "Usuario modificado con exito");
            return "redirect:/usuario/";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            modelo.put("error", "Faltó algún dato.");
            return "redirect:/usuario/";
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
        return "redirect:/usuario/";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam String id) {
        try {
            st.eliminarTrabajo(id);
        } catch (Exception e) {

        }
        return "redirect:/usuario/";
    }

    @PostMapping("/aplicar")
    public String postularse(@RequestParam String id, @RequestParam String candidato, @RequestParam String texto, RedirectAttributes redirectAttributes, ModelMap model) {
        try {
            Trabajo t = st.agregarCandidato(id, candidato, texto);
            model.put("exito", "Usuario postulado con exito");
            redirectAttributes.addFlashAttribute("exito", "Usuario postulado con exito");
        } catch (Exception e) {
            model.put("error", e.getMessage());
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/usuario/";
    }

    @GetMapping("/elegirProfesional/{id}")
    public String elegirProfesional(ModelMap model, HttpSession session, @PathVariable String id) { //id del trabajo
        try {
            Usuario u = (Usuario) session.getAttribute("usuariosession");
            model.put("usuario", u);
            Trabajo t = rt.getById(id);
            model.put("trabajo", t);
            List<Comentario> candidatos = t.getCandidatos();
            model.put("candidatos", candidatos);
        } catch (Exception e) {

        }
        return "list-profesionales";
    }

    @PostMapping("/elegirProfesional/{id}")
    public String elegirProfesional(@RequestParam String id, @RequestParam String profesional, RedirectAttributes redirectAttributes, ModelMap model) { // id = id del trabajo; profesional = comentario donde se encuentran el profesional y el costo
        try {
            Trabajo t = st.elegirProfesionalYCosto(id, profesional);
            model.put("exito", "Profesional elegido con exito");
            redirectAttributes.addFlashAttribute("exito", "Trabajo modificado con exito");
        } catch (Exception e) {
            model.put("error", e.getMessage());
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/usuario/";
    }
    
    @PostMapping("/recibirCalificacion")
    public String recibirCalificacion(@RequestParam String id, @RequestParam String estrellas, RedirectAttributes redirectAttributes, ModelMap model) { // id = id del trabajo; 
        try {
            Trabajo t = st.recibirCalificacion(id, estrellas);
            model.put("exito", "Calificacion asignada con exito");
            redirectAttributes.addFlashAttribute("exito", "Trabajo modificado con exito");
        } catch (Exception e) {
            model.put("error", e.getMessage());
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/usuario/";
    }

}
