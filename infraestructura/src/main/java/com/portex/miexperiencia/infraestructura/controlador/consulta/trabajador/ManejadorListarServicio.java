package com.portex.miexperiencia.infraestructura.controlador.consulta.trabajador;


import com.portex.miexperiencia.dominio.modelo.dto.DtoServicio;
import com.portex.miexperiencia.dominio.puerto.dao.DaoServicio;
import org.springframework.stereotype.Component;

import java.util.List;



@Component
public class ManejadorListarServicio {

    private  final DaoServicio daoServicio;

    public ManejadorListarServicio(DaoServicio daoServicio) {
        this.daoServicio = daoServicio;
    }

    public List<DtoServicio> ejecutar(){
        return this.daoServicio.listarServicios();}


    public List<DtoServicio> ejecutar(Long id){
        return this.daoServicio.consultarServiciosPorTrabajador(id);}

    public DtoServicio ejecutar2(Long id){return this.daoServicio.consultarTrabajadorPorId(id);}

}
