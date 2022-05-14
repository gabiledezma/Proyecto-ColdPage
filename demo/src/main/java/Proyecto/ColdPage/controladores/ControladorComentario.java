package Proyecto.ColdPage.controladores;

import Proyecto.ColdPage.servicios.ServicioComentario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/comentario")
public class ControladorComentario {
    
    @Autowired
    private ServicioComentario sc;
    
    @PostMapping("/crear")
    public String crearComentario(ModelMap model, @RequestParam String texto, @RequestParam String usuario) throws Exception{
        sc.crearComentario(texto, usuario);
        return "registro.html";
    }
    
    @GetMapping("/eliminar")
    public String eliminarComentario(ModelMap model, @RequestParam String id) throws Exception{
        sc.eliminarComentario(id);
        return"registro.html";
    }
    
    @PostMapping("/modificar")
    public String modificarComentario(ModelMap model, @RequestParam String id, @RequestParam String texto, @RequestParam String foto) throws Exception{
        sc.modificarComentario(id, texto, foto);
        return"registro.html";
    }
}
