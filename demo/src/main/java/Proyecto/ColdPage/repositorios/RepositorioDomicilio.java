package Proyecto.ColdPage.repositorios;

import Proyecto.ColdPage.entidades.Domicilio;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioDomicilio extends JpaRepository<Domicilio, String> {

    @Query("SELECT d FROM Domicilio d WHERE d.pais = :pais")
    public List<Domicilio> buscarPorPais(@Param("pais") String pais);

    @Query("SELECT d FROM Domicilio d WHERE d.provincia = :provincia")
    public List<Domicilio> buscarPorProvincia(@Param("provincia") String provincia);

    @Query("SELECT d FROM Domicilio d WHERE d.localidad = :localidad")
    public List<Domicilio> buscarPorLocalidad(@Param("localidad") String localidad);
}
