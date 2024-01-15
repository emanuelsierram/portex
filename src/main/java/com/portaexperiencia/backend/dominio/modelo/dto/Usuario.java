package com.portaexperiencia.backend.dominio.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class Usuario {

        private String usuario;
        private String contrasena;
        private String email;
        private Boolean estaDesabilitado;
        private Boolean estaBloqueado;

}





