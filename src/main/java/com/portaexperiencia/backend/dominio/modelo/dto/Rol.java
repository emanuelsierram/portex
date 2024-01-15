package com.portaexperiencia.backend.dominio.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Rol {

    private String usuario;
    private String rol;
    private LocalDateTime fechaCreacion;
}
