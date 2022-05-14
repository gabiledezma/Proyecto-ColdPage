package Proyecto.ColdPage.controladores;

import Proyecto.ColdPage.servicios.ServicioImagen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/imagen")
public class ControladorImagen {
    
    @Autowired
    private ServicioImagen si;
    
    @PostMapping("/agregar")
    public String agregarImagen(ModelMap model, @RequestParam String url){
        si.agregarImagen(url);
        return"registro.html";
    }
    
    @GetMapping("/eliminar")
    public String eliminarImagen(ModelMap model, @RequestParam String id){
        si.eliminarImagen(id);
        return"registro.html";
        
    }
}
