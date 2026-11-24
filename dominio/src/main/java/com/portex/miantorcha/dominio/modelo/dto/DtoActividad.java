package com.portex.miantorcha.dominio.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class DtoActividad {

    private Long id;
    private String idUsuario;
    private Long idTipoActividad;
    private String codigoTipoActividad;
    private String nombreTipoActividad;
    private String descripcionOtro;
    private String estado;
    private LocalDate fechaRealizacion;
    private Boolean informarObra;
    private String observaciones;
}
