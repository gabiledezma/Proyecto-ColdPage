package Proyecto.ColdPage.repositorios;

import Proyecto.ColdPage.entidades.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioPublicacion extends JpaRepository<Publicacion, String> {
    
}
