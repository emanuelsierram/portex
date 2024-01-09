package com.portaexperiencia.backend.infraestructura.controlador.consulta;

import com.portaexperiencia.backend.dominio.modelo.dto.DtoServicio;
import com.portaexperiencia.backend.dominio.modelo.dto.DtoTrabajador;
import com.portaexperiencia.backend.dominio.puerto.dao.DaoTrabajador;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class ManejadorListarTrabajadores {

    private final DaoTrabajador daoTrabajador;

    public ManejadorListarTrabajadores(DaoTrabajador daoTrabajador) {
        this.daoTrabajador = daoTrabajador;
    }

    public List<DtoTrabajador> ejecutar(){ return this.daoTrabajador.listar(); }
    public DtoTrabajador ejecutar(String cedula){return this.daoTrabajador.consultarPorCedulaTrabajador(cedula);}

    public List<DtoServicio> ejecutar(Long id){return this.daoTrabajador.consultarServiciosPorTrabajador(id);}

}
