package Proyecto.ColdPage.repositorios;

import Proyecto.ColdPage.entidades.Trabajo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioTrabajo extends JpaRepository<Trabajo, String> {

}
