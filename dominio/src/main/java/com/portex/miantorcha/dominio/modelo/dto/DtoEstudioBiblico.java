package com.portex.miantorcha.dominio.modelo.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoEstudioBiblico {

    private Long id;
    private String nombrePersona;
    private String telefonoPersona;
    private String direccionPersona;
    private String estado;
    private Integer leccion;
    private Long idUsuarioAsignado;
    private String personaQueReporta;
    private Long idGrupo;
    private String frecuencia;
    private LocalDate fechaInicializacion;
    private LocalDateTime fechaRegistro;

}