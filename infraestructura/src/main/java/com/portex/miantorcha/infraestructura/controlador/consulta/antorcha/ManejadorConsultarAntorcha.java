package com.portex.miantorcha.infraestructura.controlador.consulta.antorcha;

import com.portex.miantorcha.dominio.modelo.dto.DtoAntorcha;
import com.portex.miantorcha.dominio.puerto.dao.DaoAntorcha;
import org.springframework.stereotype.Component;

@Component
public class ManejadorConsultarAntorcha {

    private final DaoAntorcha daoAntorcha;

    public ManejadorConsultarAntorcha(DaoAntorcha daoAntorcha) {
        this.daoAntorcha = daoAntorcha;
    }

    public DtoAntorcha consultarPorUsuarioId(String idUsuario) {
        return daoAntorcha.consultarPorUsuarioId(idUsuario);
    }
}
