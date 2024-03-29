package com.portaexperiencia.portex.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DtoValoracion {
    private Long id;
    private String comentario;
    private Integer valoracion;
}
