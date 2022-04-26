package Proyecto.ColdPage.repositorios;

import Proyecto.ColdPage.entidades.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioPublicacion extends JpaRepository<Publicacion, String> {
    
    @Query("SELECT p.comentario FROM Publicacion p WHERE p.id = :id")
    public Publicacion listarComentarios(@Param("id") String id);
}
