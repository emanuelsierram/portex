package com.portex.miantorcha.dominio.modelo.fabrica;

import com.portex.miantorcha.dominio.modelo.AntorchaActualizada;
import com.portex.miantorcha.dominio.modelo.dto.DtoAntorcha;
import com.portex.miantorcha.dominio.modelo.entidad.Antorcha;

public class FabricaAntorcha {

    public Antorcha crear(Long id, Integer nivel, String idUsuario) {
        return new Antorcha(id, nivel, idUsuario);
    }

    public AntorchaActualizada crearActualizacion(DtoAntorcha actual, Integer nivel,
                                                  Integer totalPuntos, Integer porcentajeProgreso,
                                                  Integer puntosSiguienteNivel) {
        Antorcha antorcha = crear(actual.getId(), nivel, actual.getIdUsuario());
        return new AntorchaActualizada(antorcha, totalPuntos, porcentajeProgreso,
                puntosSiguienteNivel);
    }
}
