package com.portex.miantorcha.dominio.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DtoProgresoAntorcha {

    private Integer totalPuntos;
    private Integer nivel;
    private Integer puntosMinimosNivel;
    private Integer puntosSiguienteNivel;
}
