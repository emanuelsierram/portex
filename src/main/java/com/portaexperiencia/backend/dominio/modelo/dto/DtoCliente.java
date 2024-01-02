package com.portaexperiencia.backend.dominio.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DtoCliente {

    private Long id;
    private String nombre;
    private String apellido;
    private String telefono;
    private String direccion;
    private String email;
    private String ciudad;
    private String barrio;

}
