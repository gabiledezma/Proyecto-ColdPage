/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proyecto.ColdPage.repositorios;

import Proyecto.ColdPage.entidades.Trabajo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Fran
 */
@Repository
public interface RepositorioTrabajo extends JpaRepository<Trabajo, String>{
    
    @Query("SELECT u FROM Trabajo u WHERE u.calificacion = :calificacion")
    public List<Trabajo> findByCalificacion(@Param("calificacion") Double calificacion);
}
