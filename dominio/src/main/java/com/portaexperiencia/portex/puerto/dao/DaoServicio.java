package com.portaexperiencia.portex.puerto.dao;

import com.portaexperiencia.portex.modelo.dto.DtoServicio;

import java.util.List;

public interface DaoServicio {


    List<DtoServicio> listarServicios();

    List<DtoServicio> consultarServiciosPorTrabajador(Long idTrabajador);

    DtoServicio consultarTrabajadorPorId(Long id);


}
