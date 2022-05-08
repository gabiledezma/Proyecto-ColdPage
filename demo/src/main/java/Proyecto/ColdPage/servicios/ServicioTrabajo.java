package Proyecto.ColdPage.servicios;

import Proyecto.ColdPage.repositorios.RepositorioTrabajo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioTrabajo {

    @Autowired
    private RepositorioTrabajo rt;

}
