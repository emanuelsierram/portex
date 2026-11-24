package com.portex.miantorcha.dominio.modelo;

import com.portex.miantorcha.dominio.modelo.entidad.Antorcha;
import lombok.Getter;

@Getter
public class AntorchaActualizada {

    private final Antorcha antorcha;
    private final Integer totalPuntos;
    private final Integer porcentajeProgreso;
    private final Integer puntosSiguienteNivel;

    public AntorchaActualizada(Antorcha antorcha, Integer totalPuntos,
                               Integer porcentajeProgreso, Integer puntosSiguienteNivel) {
        this.antorcha = antorcha;
        this.totalPuntos = totalPuntos;
        this.porcentajeProgreso = porcentajeProgreso;
        this.puntosSiguienteNivel = puntosSiguienteNivel;
    }
}
