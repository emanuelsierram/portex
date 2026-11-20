package com.portex.miantorcha.dominio.puerto.dao;

import com.portex.miantorcha.dominio.modelo.dto.DtoAntorcha;
import com.portex.miantorcha.dominio.modelo.dto.DtoProgresoAntorcha;

public interface DaoAntorcha {

    DtoAntorcha consultarPorId(Long id);

    DtoAntorcha consultarPorUsuarioId(String idUsuario);

    DtoProgresoAntorcha consultarProgresoAntorcha(String idUsuario);

}
