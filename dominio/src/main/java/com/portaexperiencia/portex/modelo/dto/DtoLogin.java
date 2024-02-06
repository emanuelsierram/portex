package com.portaexperiencia.portex.modelo.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DtoLogin {

    private String usuario;
    private String contrasena;
}
