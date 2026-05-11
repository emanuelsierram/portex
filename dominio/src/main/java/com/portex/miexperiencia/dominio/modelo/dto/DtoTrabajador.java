package com.portex.miexperiencia.dominio.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DtoTrabajador {

    private Long id;
    private String nombres;
    private String apellidos;
    private String telefono;
    private String email;
    private String cedula;
    private String profesion;
    private String estado;
    private String estadoServicio;


    public DtoTrabajador(String nombres, String apellidos, String profesion){
        this.nombres=nombres;
        this.apellidos=apellidos;
        this.profesion=profesion;
    }

}
