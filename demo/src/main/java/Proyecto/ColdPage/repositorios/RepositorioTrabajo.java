package Proyecto.ColdPage.repositorios;

import Proyecto.ColdPage.entidades.Trabajo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioTrabajo extends JpaRepository<Trabajo, String>{
    
    @Query("SELECT t FROM Trabajo t WHERE t.calificacion = :calificacion")
    public List<Trabajo> findByCalificacion(@Param("calificacion") Integer calificacion);

}
