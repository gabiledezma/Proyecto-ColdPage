package Proyecto.ColdPage.repositorios;

import Proyecto.ColdPage.entidades.Profesional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioProfesional extends JpaRepository<Profesional, String> {

    @Query("SELECT p FROM Profesional p WHERE p.usuario.id = :id")
    public Profesional buscarPorUsuario(@Param("id") String id);

    @Query("SELECT p FROM Profesional p INNER JOIN Usuario u ON p.usuario = u WHERE u.email = :email")
    public Profesional buscarPorEmail(@Param("email") String email);
}
