package Proyecto.ColdPage.repositorios;

import Proyecto.ColdPage.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioCliente extends JpaRepository<Cliente, String> {
// buscar cliente por email dentro de usuario usando inner join
    //@Query("SELECT c FROM Cliente c WHERE c.u.email = :email")
    public Cliente findByEmail(@Param("email") String email);
}
