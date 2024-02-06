package com.portaexperiencia.portex.puerto.dao;

import com.portaexperiencia.portex.modelo.dto.DtoServicio;
import com.portaexperiencia.portex.modelo.dto.DtoTrabajador;

import java.util.List;

public interface DaoTrabajador {

    List<DtoTrabajador> listar();

    DtoTrabajador consultarPorCedulaTrabajador(String cedula);

    List<DtoServicio>  consultarServiciosPorTrabajador(Long idTrabajador);

    DtoTrabajador consultarPorIdTrabajador(Long id);
}
