package com.portex.compartido.dominio.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class DtoRol {

    private String usuario;
    private String rol;
    private LocalDateTime fechaCreacion;
}
