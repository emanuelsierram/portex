package com.portex.compartido.dominio.modelo.entidad;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Login {

    private String usuario;
    private String contrasena;
}
