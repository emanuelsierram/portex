package com.portex.miexperiencia.dominio.puerto.dao;

import com.portex.miexperiencia.dominio.modelo.dto.DtoServicio;

import java.util.List;

public interface DaoServicio {


    List<DtoServicio> listarServicios();

    List<DtoServicio> consultarServiciosPorTrabajador(Long idTrabajador);

    DtoServicio consultarTrabajadorPorId(Long id);


}
