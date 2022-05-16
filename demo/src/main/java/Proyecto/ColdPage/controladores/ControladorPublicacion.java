package Proyecto.ColdPage.controladores;

import Proyecto.ColdPage.entidades.Comentario;
import Proyecto.ColdPage.entidades.Publicacion;
import Proyecto.ColdPage.entidades.Usuario;
import Proyecto.ColdPage.servicios.ServicioPublicacion;
import Proyecto.ColdPage.servicios.ServicioUsuario;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/publicacion")
public class ControladorPublicacion {

    @Autowired
    private ServicioPublicacion sp;
    
    @Autowired
    private ServicioUsuario su;

    @GetMapping("/lista")
    public String listPublicacion(ModelMap model) {
        List<Publicacion> publicaciones = sp.listarPublicaciones();
        model.put("publicaciones", publicaciones);
        return "registro.html";
    }

    @PostMapping("/crear")
    public String crearPublicacion(ModelMap model,HttpSession session,@RequestParam String id,  @RequestParam String texto) throws Exception {
        Usuario u = session.getAttribute('UsuarioSession');
        sp.crearPublicacion(u, texto);
        
        return "index";
    }

    @PostMapping("/crear/imagen")
    public String ingresarImagen(ModelMap model, @RequestParam String imagen, @RequestParam String id) throws Exception {
        sp.recibirImagen(imagen, id);
        return "registro.html";
    }

    @PostMapping("/comentario")
    public String ingresarComentario(ModelMap model, @RequestParam Comentario comentario, String id) throws Exception {
        sp.recibirComentarios(comentario, id);
        return "registro.html";
    }

    @GetMapping("/eliminar")
    public String eliminarPublicacion(ModelMap model, @RequestParam String id) throws Exception {
        sp.eliminarPublicacion(id);
        return "registro.html";
    }

    @PostMapping("/crear/modificar")
    public String modificarPublicacion(ModelMap model, @RequestParam String id, @RequestParam String titulo, @RequestParam String texto, @RequestParam Usuario usuario) throws Exception {
        sp.modificarPublicacion(id, texto, usuario);
        return "registro.html";
    }

}
