package com.portex.miantorcha.infraestructura.controlador.comando.actividad;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ComandoActividad {
    private Long idTipoActividad;
    private String descripcionOtro;
    private LocalDate fechaRealizacion;
    private Boolean informarObra;
    private String observaciones;
}
