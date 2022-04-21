package Proyecto.ColdPage.repositorios;

import Proyecto.ColdPage.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioCliente extends JpaRepository<Usuario, String> {
// buscar cliente por email dentro de usuario usando inner join
//    @Query("SELECT c FROM Cliente c WHERE c.u.email = :email")
//    public Usuario findByEmail(@Param("email") String email);
}
