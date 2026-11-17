package com.portex.miantorcha.dominio.puerto.dao;

import com.portex.miantorcha.dominio.modelo.dto.DtoAntorcha;

public interface DaoAntorcha {

    DtoAntorcha consultarPorId(Long id);

    DtoAntorcha consultarPorUsuarioId(String idUsuario);
}
