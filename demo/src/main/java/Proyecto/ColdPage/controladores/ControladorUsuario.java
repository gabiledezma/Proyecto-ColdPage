package Proyecto.ColdPage.controladores;

import Proyecto.ColdPage.entidades.Usuario;
import Proyecto.ColdPage.servicios.ServicioUsuario;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/usuario")
public class ControladorUsuario {

    @Autowired
    private ServicioUsuario su;

    @GetMapping("/")
    public String index(@RequestParam(required = false) String login, ModelMap model, HttpSession session) {
        try {
            Usuario u = (Usuario) session.getAttribute("usuariosession");
            model.put("usuario", u);
            model.put("exito", "Logueado con exito");
        } catch (Exception e) {
            model.put("error", e.getMessage());
        }
        return "index";
    }

    @GetMapping("/registro")
    public String registro() {
        return "registro";
    }

    @PostMapping("/registro")
    public String registro(ModelMap modelo, @RequestParam String email, @RequestParam String pw1, @RequestParam String pw2, @RequestParam String role, @RequestParam String nombre, @RequestParam String contacto, @RequestParam String fechaDeNacimiento, @RequestParam String pais, @RequestParam String provincia, @RequestParam String localidad) {
        try {
            Usuario u = su.crearUsuario(email, pw1, pw2, role, nombre, contacto, fechaDeNacimiento, pais, provincia, localidad);
            modelo.put("exito", "Registro exitoso.");
            return "redirect:/";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            modelo.put("error", "Faltó algún dato.");
            return "registro";
        }
    }

    @GetMapping("/editar")
    public String editar(ModelMap model, HttpSession session) {
        try {
            Usuario u = (Usuario) session.getAttribute("usuariosession");
            model.put("usuario", u);
        } catch (Exception e) {
        }
        return "editar-perfil";
    }

    @PostMapping("/editar")
    public String editarPerfil(@RequestParam String id, @RequestParam String email, @RequestParam String pw1, @RequestParam String role, RedirectAttributes redirectAttributes, ModelMap model, @RequestParam String profesion, @RequestParam String pais, @RequestParam String provincia, @RequestParam String localidad, @RequestParam String nombre, @RequestParam String contacto, @RequestParam String fechaDeNacimiento, @RequestParam String foto, @RequestParam String perfil) {
        try {
            Usuario u = su.modificarUsuario(id, email, pw1, role, profesion, pais, provincia, localidad, nombre, contacto, fechaDeNacimiento, foto, perfil);
            model.put("exito", "Usuario modificado con exito");
            redirectAttributes.addFlashAttribute("exito", "Usuario modificado con exito");
        } catch (Exception e) {
            model.put("error", e.getMessage());
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/usuario/perfil";
    }

    @GetMapping("/perfil")
    public String perfil(ModelMap model, HttpSession session) {
        try {
            Usuario u = (Usuario) session.getAttribute("usuariosession");
            model.put("usuario", u);
            u.setPromedioCalificacion(su.obtenerCalificacion(u));
            model.put("publicaciones", u.getPublicaciones());
            model.put("trabajos", u.getTrabajos());
        } catch (Exception e) {

        }
        return "perfil";
    }

    @PostMapping("/editarFoto")
    public String editarFoto(@RequestParam String id, @RequestParam String foto, RedirectAttributes redirectAttributes, ModelMap model) {
        try {
            Usuario u = su.subirFoto(id, foto);
            model.put("exito", "Foto modificada con exito");
            redirectAttributes.addFlashAttribute("exito", "Usuario modificado con exito");
        } catch (Exception e) {
            model.put("error", e.getMessage());
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/usuario/perfil";
    }

    @PostMapping("/eliminarFoto")
    public String eliminarFoto(@RequestParam String id, @RequestParam String foto, RedirectAttributes redirectAttributes, ModelMap model) {
        try {
            Usuario u = su.eliminarFoto(id);
            model.put("exito", "Foto eliminada con exito");
            redirectAttributes.addFlashAttribute("exito", "Usuario modificado con exito");
        } catch (Exception e) {
            model.put("error", e.getMessage());
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/usuario/perfil";
    }

    @GetMapping("/cambiarPassword")
    public String cambiarPassword(ModelMap model, HttpSession session) {
        try {
            Usuario u = (Usuario) session.getAttribute("usuariosession");
            model.put("usuario", u);
        } catch (Exception e) {

        }
        return "edit-password";
    }

    @PostMapping("/cambiarPassword")
    public String cambiarPassword(@RequestParam String id, @RequestParam String nueva1, @RequestParam String nueva2, @RequestParam String anterior, RedirectAttributes redirectAttributes, ModelMap model) {
        try {
            Usuario u = su.cambiarPassword(id, nueva1, nueva2, anterior);
            model.put("exito", "Contraseña modificada con exito");
            redirectAttributes.addFlashAttribute("exito", "Usuario modificado con exito");
        } catch (Exception e) {
            model.put("error", e.getMessage());
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/usuario/perfil";
    }
    /*
    este metodo se debe hacer en conjunto con alerta de modificar privacidad
    
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
        return "index";
    }
     */
}
