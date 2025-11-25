package com.portaexperiencia.portex.infraestructura.controlador.consulta.trabajador;

import com.portaexperiencia.portex.modelo.dto.DtoServicio;
import com.portaexperiencia.portex.modelo.dto.DtoTrabajador;
import com.portaexperiencia.portex.puerto.dao.DaoTrabajador;
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

    public DtoTrabajador ejecutar(Long id){return this.daoTrabajador.consultarPorIdTrabajador(id);}




}
