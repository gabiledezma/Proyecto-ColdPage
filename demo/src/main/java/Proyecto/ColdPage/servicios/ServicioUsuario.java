package Proyecto.ColdPage.servicios;

import Proyecto.ColdPage.entidades.Trabajo;
import Proyecto.ColdPage.entidades.Usuario;
import Proyecto.ColdPage.enums.Role;
import Proyecto.ColdPage.repositorios.RepositorioUsuario;
import java.util.ArrayList;
import java.util.Date;
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
    public Usuario crearUsuario(String email, String pw1, String pw2, String role, String nombre, String contacto, String fechaDeNacimiento, String pais, String provincia, String localidad) throws Exception {
        try {
            validar(email, pw1, pw2, role, nombre, contacto, fechaDeNacimiento, pais, provincia, localidad);
            Usuario usuario = new Usuario();
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            usuario.setEmail(email);
            usuario.setPassword(encoder.encode(pw1));
            if (role.equals("CLIENTE")) {
                usuario.setRole(Role.CLIENTE);
            } else if (role.equalsIgnoreCase("PROFESIONAL")) {
                usuario.setRole(Role.PROFESIONAL);
            }
            usuario.setNombre(nombre);
            usuario.setContacto(contacto);
            String anio = fechaDeNacimiento.substring(0, 4);
            String mes = fechaDeNacimiento.substring(5, 7);
            String dia = fechaDeNacimiento.substring(8, 10);
            int anioInt = Integer.parseInt(anio);
            int mesInt = Integer.parseInt(mes);
            int diaInt = Integer.parseInt(dia);
            Date fecha = new Date(anioInt - 1900, mesInt - 1, diaInt);
            usuario.setFechaDeNacimiento(fecha);
            String domicilio = localidad + ", " + provincia + ", " + pais;
            usuario.setDomicilio(domicilio);
            usuario.setPerfil(true); // publico
            return ru.save(usuario);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    @Transactional
    public Usuario modificarUsuario(String id, String email, String pw1, String pw2, String role, String profesion, String pais, String provincia, String localidad, String nombre, String contacto, String fechaDeNacimiento, String foto, String perfil) throws Exception {
        Usuario u = getOne(id);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (u == null) {
            throw new Exception("No existe un usuario con esa ID");
        }
        if (role.equals("CLIENTE")) {
            validarModificacionCliente(email, pw1, pw2, pais, provincia, localidad, nombre, contacto, fechaDeNacimiento, foto, perfil);
            u.setEmail(email);
            u.setPassword(encoder.encode(pw1));
            u.setRole(u.getRole());
            u.setProfesion(u.getProfesion());
            String domicilio = localidad + ", " + provincia + ", " + pais;
            u.setDomicilio(domicilio);
            u.setNombre(nombre);
            u.setContacto(contacto);
            String anio = fechaDeNacimiento.substring(0, 4);
            String mes = fechaDeNacimiento.substring(5, 7);
            String dia = fechaDeNacimiento.substring(8, 10);
            int anioInt = Integer.parseInt(anio);
            int mesInt = Integer.parseInt(mes);
            int diaInt = Integer.parseInt(dia);
            Date fecha = new Date(anioInt - 1900, mesInt - 1, diaInt);
            u.setFechaDeNacimiento(fecha);
            u.setFoto(foto);
            if (perfil.equals("PUBLICO")) {
                u.setPerfil(true);
            } else if (perfil.equals("PRIVADO")) {
                u.setPerfil(false);
            }
            return ru.save(u);
        } else if (role.equalsIgnoreCase("PROFESIONAL")) {
            validarModificacionProfesional(email, pw1, pw2, profesion, pais, provincia, localidad, nombre, contacto, fechaDeNacimiento, foto, perfil);
            u.setEmail(email);
            u.setPassword(encoder.encode(pw1));
            u.setRole(u.getRole());
            u.setProfesion(profesion);
            String domicilio = localidad + ", " + provincia + ", " + pais;
            u.setDomicilio(domicilio);
            u.setNombre(nombre);
            u.setContacto(contacto);
            String anio = fechaDeNacimiento.substring(0, 4);
            String mes = fechaDeNacimiento.substring(5, 7);
            String dia = fechaDeNacimiento.substring(8, 10);
            int anioInt = Integer.parseInt(anio);
            int mesInt = Integer.parseInt(mes);
            int diaInt = Integer.parseInt(dia);
            Date fecha = new Date(anioInt - 1900, mesInt - 1, diaInt);
            u.setFechaDeNacimiento(fecha);
            u.setFoto(foto);
            if (perfil.equals("PUBLICO")) {
                u.setPerfil(true);
            } else if (perfil.equals("PRIVADO")) {
                u.setPerfil(false);
            }
            return ru.save(u);
        } else {
            return null;
        }

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

    public void validar(String email, String pw1, String pw2, String role, String nombre, String contacto, String fechaDeNacimiento, String pais, String provincia, String localidad) throws Exception {
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
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new Exception("El nombre no puede estar vacio");
        }
        if (contacto == null || contacto.trim().isEmpty()) {
            throw new Exception("El contacto no puede estar vacio");
        }
        if (fechaDeNacimiento == null || fechaDeNacimiento.trim().isEmpty()) {
            throw new Exception("La fecha de nacimiento no puede estar vacia");
        }
        if (pais == null || pais.trim().isEmpty()) {
            throw new Exception("El pais no puede estar vacio");
        }
        if (provincia == null || provincia.trim().isEmpty()) {
            throw new Exception("La provincia no puede estar vacia");
        }
        if (localidad == null || localidad.trim().isEmpty()) {
            throw new Exception("La localidad no puede estar vacia");
        }

    }

    public void validarModificacionProfesional(String email, String pw1, String pw2, String profesion, String pais, String provincia, String localidad, String nombre, String contacto, String fechaDeNacimiento, String foto, String perfil) throws Exception {
        if (email == null || email.isEmpty()) {
            throw new Exception("Email no puede estar vacio");
        }
        if (pw1 == null || pw2 == null || pw1.isEmpty() || pw2.isEmpty()) {
            throw new Exception("Las contraseñas no pueden estar vacias");
        }
        if (!pw1.equals(pw2)) {
            throw new Exception("Las contraseñas no coinciden");
        }
        if (profesion == null || profesion.trim().isEmpty()) {
            throw new Exception("La profesion no puede estar vacia");
        }
        if (pais == null || pais.trim().isEmpty()) {
            throw new Exception("El pais no puede estar vacio");
        }
        if (provincia == null || provincia.trim().isEmpty()) {
            throw new Exception("La provincia no puede estar vacia");
        }
        if (localidad == null || localidad.trim().isEmpty()) {
            throw new Exception("La localidad no puede estar vacia");
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new Exception("El nombre no puede estar vacio");
        }
        if (contacto == null || contacto.trim().isEmpty()) {
            throw new Exception("El contacto no puede estar vacio");
        }
        if (fechaDeNacimiento == null || fechaDeNacimiento.trim().isEmpty()) {
            throw new Exception("La fecha de nacimiento no puede estar vacia");
        }
        if (foto == null || foto.trim().isEmpty()) {
            throw new Exception("La foto no puede estar vacia");
        }
        if (perfil == null || perfil.trim().isEmpty()) {
            throw new Exception("El perfil no puede estar vacio");
        }
    }

    public void validarModificacionCliente(String email, String pw1, String pw2, String pais, String provincia, String localidad, String nombre, String contacto, String fechaDeNacimiento, String foto, String perfil) throws Exception {
        if (email == null || email.isEmpty()) {
            throw new Exception("Email no puede estar vacio");
        }
        if (pw1 == null || pw2 == null || pw1.isEmpty() || pw2.isEmpty()) {
            throw new Exception("Las contraseñas no pueden estar vacias");
        }
        if (!pw1.equals(pw2)) {
            throw new Exception("Las contraseñas no coinciden");
        }
        if (pais == null || pais.trim().isEmpty()) {
            throw new Exception("El pais no puede estar vacio");
        }
        if (provincia == null || provincia.trim().isEmpty()) {
            throw new Exception("La provincia no puede estar vacia");
        }
        if (localidad == null || localidad.trim().isEmpty()) {
            throw new Exception("La localidad no puede estar vacia");
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new Exception("El nombre no puede estar vacio");
        }
        if (contacto == null || contacto.trim().isEmpty()) {
            throw new Exception("El contacto no puede estar vacio");
        }
        if (fechaDeNacimiento == null || fechaDeNacimiento.trim().isEmpty()) {
            throw new Exception("La fecha de nacimiento no puede estar vacia");
        }
        if (foto == null || foto.trim().isEmpty()) {
            throw new Exception("La foto no puede estar vacia");
        }
        if (perfil == null || perfil.trim().isEmpty()) {
            throw new Exception("El perfil no puede estar vacio");
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

    public List<Usuario> buscarPorProfesion(String profesion) {
        return ru.buscarPorProfesion(profesion);
    }

    public void obtenerCalificacion(Usuario u) {
        int sumatoria = 0;
        for (Trabajo trabajo : u.getTrabajos()) {
            sumatoria = sumatoria + trabajo.getCalificacion();
        }
        double promCalificacion = (int) sumatoria * 1000 / u.getTrabajos().size();
        promCalificacion = (double) promCalificacion / 1000;
        u.setPromedioCalificacion(promCalificacion);
    }

}
