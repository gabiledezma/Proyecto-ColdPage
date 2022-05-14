package Proyecto.ColdPage.controladores;

import Proyecto.ColdPage.entidades.Trabajo;
import Proyecto.ColdPage.entidades.Usuario;
import Proyecto.ColdPage.repositorios.RepositorioPublicacion;
import Proyecto.ColdPage.repositorios.RepositorioTrabajo;
import Proyecto.ColdPage.servicios.ServicioUsuario;
import java.util.ArrayList;
import java.util.Date;
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
@RequestMapping("/usuario")
public class ControladorUsuario {

    @Autowired
    private ServicioUsuario su;
    @Autowired
    private RepositorioTrabajo rt;
    @Autowired
    private RepositorioPublicacion rp;

    @GetMapping("/")
    public String index(@RequestParam(required = false) String login, ModelMap model, HttpSession session) {
        try {
            Usuario u = (Usuario) session.getAttribute("usuariosession");
            List<Trabajo> trabajos = rt.findAll();
            if (trabajos.size() == 0) {
                Trabajo t = new Trabajo();
                Usuario aux = new Usuario();
                aux.setNombre("Ejemplo");
                t.setCliente(aux);
                t.setTitulo("Ejemplo titulo");
                Date fecha = new Date();
                t.setFecha(fecha);
                t.setDescripcion("Ejemplo descripcion");
            }
            List<Usuario> clientes = su.findAll();
            model.put("clientes", clientes);
            model.put("trabajos", trabajos);
            //List<Publicacion> publicaciones = rp.findAll();
            //model.put("publicaciones", publicaciones);
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
    public String editarPerfil(@RequestParam String id, @RequestParam String email, @RequestParam String pw1, @RequestParam String role, RedirectAttributes redirectAttributes, ModelMap model, @RequestParam String profesion, @RequestParam String pais, @RequestParam String provincia, @RequestParam String localidad, @RequestParam String nombre, @RequestParam String contacto, @RequestParam String fechaDeNacimiento, @RequestParam String fotourl, @RequestParam String perfil) {
        try {
            Usuario u = su.modificarUsuario(id, email, pw1, role, profesion, pais, provincia, localidad, nombre, contacto, fechaDeNacimiento, fotourl, perfil);
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
            List<Trabajo> trabajos = rt.findAll();
            List<Trabajo> trabajosCliente = new ArrayList();
            List<Trabajo> trabajosProfesional = new ArrayList();
            for (Trabajo trabajo : trabajos) {
                if (trabajo.getCliente().getId() == u.getId()) {
                    trabajosCliente.add(trabajo);
                }
                if (trabajo.getProfesional().getId() == u.getId()) {
                    trabajosProfesional.add(trabajo);
                }
            }
            model.put("trabajosCliente", trabajosCliente);
            model.put("trabajosProfesional", trabajosProfesional);
        } catch (Exception e) {

        }
        return "perfil";
    }

    @GetMapping("/perfil/{id}")
    public String perfil(ModelMap model, HttpSession session, @PathVariable String id) {
        try {
            Usuario sesion = (Usuario) session.getAttribute("usuariosession");
            Usuario u = su.getOne(id);
            model.put("usuario", u);
            model.put("sesion", sesion);
            u.setPromedioCalificacion(su.obtenerCalificacion(u));
            model.put("publicaciones", u.getPublicaciones());
            model.put("trabajos", u.getTrabajos());
        } catch (Exception e) {

        }
        return "perfil2";
    }

    @GetMapping("/editarFoto")
    public String editarFoto(ModelMap model, HttpSession session) {
        try {
            Usuario u = (Usuario) session.getAttribute("usuariosession");
            model.put("usuario", u);
        } catch (Exception e) {

        }
        return "edit-foto";
    }

    @PostMapping("/editarFoto")
    public String editarFoto(@RequestParam String id, @RequestParam String fotourl, RedirectAttributes redirectAttributes, ModelMap model) {
        try {
            Usuario u = su.subirFoto(id, fotourl);
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
     */
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

}
