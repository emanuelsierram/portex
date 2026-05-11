package com.portex.miexperiencia.dominio.puerto.dao;

import com.portex.miexperiencia.dominio.modelo.dto.DtoTrabajador;

import java.util.List;

public interface DaoTrabajador {

    List<DtoTrabajador> listar();

    DtoTrabajador consultarPorCedulaTrabajador(String cedula);

    DtoTrabajador consultarPorIdTrabajador(Long id);
}
