package Proyecto.ColdPage.controladores;

import Proyecto.ColdPage.entidades.Comentario;
import Proyecto.ColdPage.entidades.Imagen;
import Proyecto.ColdPage.entidades.Publicacion;
import Proyecto.ColdPage.entidades.Usuario;
import Proyecto.ColdPage.servicios.ServicioPublicacion;
import java.util.List;
import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;
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
    
    @GetMapping("/lista")
    public String listPublicacion(ModelMap model){
        List<Publicacion> publicaciones = sp.listarPublicaciones();
        model.put("publicaciones", publicaciones);
        return "registro.html";
    }
    
    @PostMapping("/crear")
    public String crearPublicacion(ModelMap model, @RequestParam Usuario usuario, @RequestParam String titulo, @RequestParam String texto) throws Exception{
        sp.crearPublicacion(usuario, titulo, texto);
        return "registro.html";
    }
    
    @PostMapping("/crear/imagen")
    public String ingresarImagen(ModelMap model, @RequestParam Imagen imagen, @RequestParam String id)throws Exception{
        sp.recibirImagen(imagen, id);
        return "registro.html";
    }
    
    @PostMapping("/comentario")
    public String ingresarComentario(ModelMap model, @RequestParam Comentario comentario, String id)throws Exception{
        sp.recibirComentarios(comentario, id);
        return "registro.html";
    }
    
    @GetMapping("/eliminar")
    public String eliminarPublicacion(ModelMap model, @RequestParam String id) throws Exception{
        sp.eliminarPublicacion(id);
        return "registro.html";
    }
    
    @PostMapping("/crear/modificar")
    public String modificarPublicacion(ModelMap model, @RequestParam String id, @RequestParam String titulo,@RequestParam String texto, @RequestParam Usuario usuario) throws Exception{
     sp.modificarPublicacion(id, titulo, texto, usuario);
     return "registro.html";
    }


    
}
