package com.portaexperiencia.backend.dominio.puerto.dao;

import com.portaexperiencia.backend.dominio.modelo.dto.DtoServicio;
import com.portaexperiencia.backend.dominio.modelo.dto.DtoTrabajador;

import java.util.List;

public interface DaoTrabajador {

    List<DtoTrabajador> listar();

    DtoTrabajador consultarPorCedulaTrabajador(String cedula);

    List<DtoServicio>  consultarServiciosPorTrabajador(Long idTrabajador);
}
