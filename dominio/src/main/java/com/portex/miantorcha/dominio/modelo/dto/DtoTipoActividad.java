package com.portex.miantorcha.dominio.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DtoTipoActividad {

    private Long id;
    private String codigo;
    private String nombre;
    private Integer puntos;
    private Boolean activo;
}
