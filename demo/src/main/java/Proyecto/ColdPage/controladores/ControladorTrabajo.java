package Proyecto.ColdPage.controladores;

import Proyecto.ColdPage.repositorios.RepositorioTrabajo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/trabajo")
public class ControladorTrabajo {
    
    @Autowired
    private RepositorioTrabajo rt;
    
    
}
