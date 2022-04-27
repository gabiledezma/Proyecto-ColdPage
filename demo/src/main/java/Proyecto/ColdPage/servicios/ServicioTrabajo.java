/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proyecto.ColdPage.servicios;

import Proyecto.ColdPage.repositorios.RepositorioTrabajo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Fran
 */
@Service
public class ServicioTrabajo {
    
    @Autowired
    private RepositorioTrabajo rt;
    
    
    
}
