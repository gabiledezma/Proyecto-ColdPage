package Proyecto.ColdPage.repositorios;

import Proyecto.ColdPage.entidades.Comentario;
import Proyecto.ColdPage.entidades.Imagen;
import Proyecto.ColdPage.entidades.Publicacion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioPublicacion extends JpaRepository<Publicacion, String> {

    @Query("SELECT p.comentarios FROM Publicacion p WHERE p.id = :id")
    public List<Comentario> listarComentarios(@Param("id") String id);
}
