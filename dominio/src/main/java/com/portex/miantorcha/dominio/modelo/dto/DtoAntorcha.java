package com.portex.miantorcha.dominio.modelo.dto;

import lombok.Getter;

@Getter
public class DtoAntorcha {

    private Long id;
    private Integer nivel;
    private String idUsuario;
    private Integer totalPuntos;
    private Integer porcentajeProgreso;
    private Integer puntosSiguienteNivel;

    public DtoAntorcha(Long id, Integer nivel, String idUsuario) {
        this(id, nivel, idUsuario, 0, 0, null);
    }

    public DtoAntorcha(Long id, Integer nivel, String idUsuario, Integer totalPuntos,
                       Integer porcentajeProgreso, Integer puntosSiguienteNivel) {
        this.id = id;
        this.nivel = nivel;
        this.idUsuario = idUsuario;
        this.totalPuntos = totalPuntos;
        this.porcentajeProgreso = porcentajeProgreso;
        this.puntosSiguienteNivel = puntosSiguienteNivel;
    }
}
