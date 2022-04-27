package Proyecto.ColdPage.repositorios;

import Proyecto.ColdPage.entidades.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioImagen extends JpaRepository<Imagen, String> {
    
}
