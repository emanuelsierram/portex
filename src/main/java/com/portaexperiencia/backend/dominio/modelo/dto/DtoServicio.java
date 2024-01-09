package com.portaexperiencia.backend.dominio.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DtoServicio {

    private Long id;
    private String nombre;
    private String descripcion;
    private Long idCategoria;
    private Long idTrabajador;
}
