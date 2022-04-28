package Proyecto.ColdPage.repositorios;

import Proyecto.ColdPage.entidades.Cliente;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioCliente extends JpaRepository<Cliente, String> {

    @Query("SELECT c FROM Cliente c WHERE c.usuario.id = :id")
    public Cliente buscarPorUsuarioId(@Param("id") String id);

    @Query("SELECT c FROM Cliente c WHERE c.usuario.email = :email")
    public Cliente buscarPorEmail(@Param("email") String email);

    @Query("SELECT c FROM Cliente c INNER JOIN Domicilio d ON c.domicilio = d.id WHERE d.pais = :pais")
    public List<Cliente> buscarPorPais(@Param("pais") String pais);

    @Query("SELECT c FROM Cliente c INNER JOIN Domicilio d ON c.domicilio = d.id WHERE d.pais = :pais AND d.provincia = :provincia")
    public List<Cliente> buscarPorProvincia(@Param("pais") String pais, @Param("provincia") String provincia);

    @Query("SELECT c FROM Cliente c INNER JOIN Domicilio d ON c.domicilio = d.id WHERE d.pais = :pais AND d.provincia = :provincia AND d.localidad = :localidad")
    public List<Cliente> buscarPorLocalidad(@Param("pais") String pais, @Param("provincia") String provincia, @Param("localidad") String localidad);

}
