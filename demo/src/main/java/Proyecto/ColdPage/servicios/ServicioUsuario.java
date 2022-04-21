package Proyecto.ColdPage.servicios;

import Proyecto.ColdPage.entidades.Usuario;
import Proyecto.ColdPage.enums.Role;
import Proyecto.ColdPage.repositorios.RepositorioUsuario;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class ServicioUsuario implements UserDetailsService {

    @Autowired
    private RepositorioUsuario ru;

    @Transactional
    public Usuario crearUsuario(String email, String pw1, String pw2, String role) throws Exception {

        validar(email, pw1, pw2, role);
        Usuario usuario = new Usuario();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        usuario.setEmail(email);
        usuario.setPassword(encoder.encode(pw1));
        if (role.equalsIgnoreCase("CLIENTE")) {
            usuario.setRole(Role.CLIENTE);
        } else if (role.equalsIgnoreCase("PROFESIONAL")) {
            usuario.setRole(Role.PROFESIONAL);
        }
        return ru.save(usuario);

    }

    @Transactional
    public Usuario modificarUsuario(String id, String email, String pw1, String pw2, String role) throws Exception {
        validarModificacion(email, pw1, pw2, role);
        Usuario u = getOne(id);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (u == null) {
            throw new Exception("No existe un usuario con esa ID");
        }
        u.setEmail(email);
        u.setPassword(encoder.encode(pw1));
        if (role.equalsIgnoreCase("CLIENTE")) {
            u.setRole(Role.CLIENTE);
        } else if (role.equalsIgnoreCase("PROFESIONAL")) {
            u.setRole(Role.PROFESIONAL);
        }
        return ru.save(u);
    }

    @Transactional
    public void eliminarUsuario(String id) {
        Usuario u = getOne(id);
        ru.delete(u);
    }

    @Transactional
    public List<Usuario> findAll() {
        return ru.findAll();
    }

    @Transactional
    public Usuario getOne(String id) {
        return ru.getOne(id);
    }

    @Transactional
    public Usuario buscarPorEmail(String email) {
        return ru.findByEmail(email);
    }

    @Transactional
    public void cambiarRol(String id) throws Exception {

        Optional<Usuario> respuesta = ru.findById(id);

        if (respuesta.isPresent()) {

            Usuario usuario = respuesta.get();

            if (usuario.getRole().equals(Role.CLIENTE)) {

                usuario.setRole(Role.PROFESIONAL);

            } else if (usuario.getRole().equals(Role.PROFESIONAL)) {
                usuario.setRole(Role.CLIENTE);
            }
        }
    }

    @Transactional
    public void hacerAdmin(String id) throws Exception {

        Optional<Usuario> respuesta = ru.findById(id);

        if (respuesta.isPresent()) {

            Usuario usuario = respuesta.get();

            if (usuario.getRole().equals(Role.CLIENTE)) {

                usuario.setRole(Role.ADMIN);

            } else if (usuario.getRole().equals(Role.PROFESIONAL)) {
                usuario.setRole(Role.ADMIN);
            }
        }
    }

    public void validar(String email, String pw1, String pw2, String role) throws Exception {
        if (email == null || email.isEmpty()) {
            throw new Exception("Email no puede estar vacio");
        }
        if (ru.findByEmail(email) != null) {
            throw new Exception("Ya existe un usuario con ese email");
        }
        if (pw1 == null || pw2 == null || pw1.isEmpty() || pw2.isEmpty()) {
            throw new Exception("Las contraseñas no pueden estar vacias");
        }
        if (!pw1.equals(pw2)) {
            throw new Exception("Las contraseñas no coinciden");
        }
        if (role == null || role.trim().isEmpty()) {
            throw new Exception("El role no puede estar vacio");
        }

    }

    public void validarModificacion(String email, String pw1, String pw2, String role) throws Exception {
        if (email == null || email.isEmpty()) {
            throw new Exception("Email no puede estar vacio");
        }
        if (pw1 == null || pw2 == null || pw1.isEmpty() || pw2.isEmpty()) {
            throw new Exception("Las contraseñas no pueden estar vacias");
        }
        if (!pw1.equals(pw2)) {
            throw new Exception("Las contraseñas no coinciden");
        }
        if (role == null || role.trim().isEmpty()) {
            throw new Exception("El role no puede estar vacio");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = ru.findByEmail(email);
        if (usuario != null) {
            List<GrantedAuthority> permisos = new ArrayList<>();

            GrantedAuthority p1 = new SimpleGrantedAuthority("ROLE_" + usuario.getRole());//ROLE_ADMIN O ROLE_USER
            permisos.add(p1);

            //Esto me permite guardar el OBJETO USUARIO LOG, para luego ser utilizado
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession(true);
            session.setAttribute("usuariosession", usuario);

            User user = new User(usuario.getEmail(), usuario.getPassword(), permisos);
            return user;

        } else {
            return null;
        }
    }

}
