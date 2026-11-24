package com.portex.miantorcha.dominio.puerto.dao;

import com.portex.miantorcha.dominio.modelo.dto.DtoActividad;
import com.portex.miantorcha.dominio.modelo.dto.DtoTipoActividad;

import java.util.List;

public interface DaoActividad {

    List<DtoTipoActividad> listarTiposActivos();

    DtoTipoActividad consultarTipoPorId(Long id);

    DtoActividad consultarPorId(Long id);

    List<DtoActividad> listarPorUsuario(String idUsuario);

}
