package Proyecto.ColdPage.repositorios;

import Proyecto.ColdPage.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
//a
@Repository
public interface RepositorioCliente extends JpaRepository<Cliente, String> {

    @Query("SELECT c FROM Cliente c WHERE c.usuario.id = :id")
    public Cliente buscarPorUsuarioId(@Param("id") String id);

    @Query("SELECT c FROM Cliente c INNER JOIN Usuario u ON c.usuario = u WHERE u.email = :email")
    public Cliente buscarPorEmail(@Param("email") String email);
    
    @Query("SELECT c FROM Cliente c INNER JOIN Usuario u ON c.usuario = u WHERE u.id = :id")
    public Cliente buscarPorIdUsuario(@Param("id") String id);
}
