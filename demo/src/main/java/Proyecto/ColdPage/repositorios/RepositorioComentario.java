package Proyecto.ColdPage.repositorios;

import Proyecto.ColdPage.entidades.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioComentario extends JpaRepository<Comentario, String> {
    
}
