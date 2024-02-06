package com.portaexperiencia.portex.infraestructura.controlador.consulta;


import com.portaexperiencia.portex.modelo.dto.DtoServicio;
import com.portaexperiencia.portex.puerto.dao.DaoTrabajador;
import org.springframework.stereotype.Component;

import java.util.List;



@Component
public class ManejadorListarServicio {

    private final DaoTrabajador daoTrabajador;

    public ManejadorListarServicio(DaoTrabajador daoTrabajador) {
        this.daoTrabajador = daoTrabajador;
    }


    public List<DtoServicio> ejecutar(Long id){return this.daoTrabajador.consultarServiciosPorTrabajador(id);}

}
