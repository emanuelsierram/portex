package com.portaexperiencia.portex.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DtoCategoria {

    private Long id;
    private String nombre;
    private String descripcion;
    private Long idIcono;

}
