package Proyecto.ColdPage.repositorios;

import Proyecto.ColdPage.entidades.Profesional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioProfesional extends JpaRepository<Profesional, String> {

    @Query("SELECT p FROM Profesional p WHERE p.usuario.id = :id")
    public Profesional buscarPorUsuarioId(@Param("id") String id);

    @Query("SELECT p FROM Profesional p INNER JOIN Usuario u ON p.usuario = u WHERE u.email = :email")
    public Profesional buscarPorEmail(@Param("email") String email);

    @Query("SELECT p FROM Profesional p INNER JOIN Usuario u ON p.usuario = u WHERE u.id = :id")
    public Profesional buscarPorIdUsuario(@Param("id") String id);

    @Query("SELECT p FROM Profesional p WHERE p.profesion = :profesion")
    public List<Profesional> buscarPorProfesion(@Param("profesion") String profesion);

    @Query("SELECT p FROM Profesional p INNER JOIN Domicilio d ON p.domicilio = d WHERE d.pais = :pais")
    public List<Profesional> buscarPorPais(@Param("pais") String pais);

    @Query("SELECT p FROM Profesional p INNER JOIN Domicilio d ON p.domicilio = d WHERE d.pais = :pais AND d.provincia = :provincia")
    public List<Profesional> buscarPorProvincia(@Param("pais") String pais, @Param("provincia") String provincia);

    @Query("SELECT p FROM Profesional p INNER JOIN Domicilio d ON p.domicilio = d WHERE d.pais = :pais AND d.provincia = :provincia AND d.localidad = :localidad")
    public List<Profesional> buscarPorLocalidad(@Param("pais") String pais, @Param("provincia") String provincia, @Param("localidad") String localidad);
}
