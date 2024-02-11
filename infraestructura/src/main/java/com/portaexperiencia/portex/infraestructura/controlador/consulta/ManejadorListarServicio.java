package com.portaexperiencia.portex.infraestructura.controlador.consulta;


import com.portaexperiencia.portex.modelo.dto.DtoServicio;
import com.portaexperiencia.portex.puerto.dao.DaoServicio;
import org.springframework.stereotype.Component;

import java.util.List;



@Component
public class ManejadorListarServicio {

    private  final DaoServicio daoServicio;

    public ManejadorListarServicio(DaoServicio daoServicio) {
        this.daoServicio = daoServicio;
    }


    public List<DtoServicio> ejecutar(Long id){
        return this.daoServicio.consultarServiciosPorTrabajador(id);}

    public DtoServicio ejecutar2(Long id){return this.daoServicio.consultarTrabajadorPorId(id);}

}
