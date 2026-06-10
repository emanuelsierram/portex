package com.portex.compartido.dominio.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class DtoUsuario {

        private String usuario;
        private String contrasena;
        private String email;
        private Boolean estaDesabilitado;
        private Boolean estaBloqueado;

}





