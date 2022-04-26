package Proyecto.ColdPage.repositorios;

import Proyecto.ColdPage.entidades.Comentario;
import Proyecto.ColdPage.entidades.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioComentario extends JpaRepository<Comentario, String> {
    
}
