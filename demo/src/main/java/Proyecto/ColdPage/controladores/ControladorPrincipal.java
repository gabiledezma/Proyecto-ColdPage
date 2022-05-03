package Proyecto.ColdPage.controladores;

import Proyecto.ColdPage.entidades.Usuario;
import Proyecto.ColdPage.enums.Role;
import Proyecto.ColdPage.servicios.ServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")//http://localhost:8080
public class ControladorPrincipal {

    @Autowired
    private ServicioUsuario su;

    @GetMapping("/")//localhost:8080/
    public String index(@RequestParam(required = false) String login, ModelMap model) {
        if (login != null) {
            model.put("exito", "Logueado con exito");
        }
        return "index";
    }

    @PostMapping("/")
    public String registro(@RequestParam String email, @RequestParam String pw1, @RequestParam String pw2, @RequestParam String role) {
        Usuario u = new Usuario();
        try {
            u = su.crearUsuario(email, pw1, pw2, role);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (u.getRole().equals(Role.CLIENTE)) {
            String id = "redirect/cliente/registro/" + u.getId();
            return "´" + id + "´"; // 
        } else if (u.getRole().equals(Role.PROFESIONAL)) {
            String id = "redirect/cliente/registro/" + u.getId();
            return "´" + id + "´"; // 
        } else {
            return "redirect:/index";
        }

    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, @RequestParam(required = false) String logout, ModelMap model) {
        if (error != null) {
            model.put("error", "Usuario o Contraseña incorrectos");
        }
        if (logout != null) {
            model.put("logout", "Desconectado correctamente");
        }
        return "index";
    }
}
