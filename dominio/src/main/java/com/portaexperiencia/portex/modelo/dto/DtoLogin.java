package com.portaexperiencia.portex.modelo.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DtoLogin {

    private String usuario;
    private String contrasena;
}
