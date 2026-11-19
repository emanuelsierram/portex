package com.portex.miantorcha.dominio.modelo.entidad;

import lombok.Getter;

import java.time.LocalDate;

import static com.portex.compartido.dominio.ValidadorArgumento.validarObligatorio;

@Getter
public class Actividad {

    private Long id;
    private String idUsuario;
    private Long idTipoActividad;
    private String codigoTipoActividad;
    private String descripcionOtro;
    private EstadoActividad estado;
    private LocalDate fechaRealizacion;
    private Boolean informarObra;
    private String observaciones;
    private Integer puntos;

    public Actividad(
            Long id,
            String idUsuario,
            Long idTipoActividad,
            String codigoTipoActividad,
            String descripcionOtro,
            EstadoActividad estado,
            LocalDate fechaRealizacion,
            Boolean informarObra,
            String observaciones,
            Integer puntos) {
        validarObligatorio(idUsuario, "El usuario de la actividad es obligatorio");
        validarObligatorio(idTipoActividad, "El tipo de actividad es obligatorio");
        validarObligatorio(estado, "El estado de la actividad es obligatorio");
        validarObligatorio(fechaRealizacion, "La fecha de realización es obligatoria");
        validarObligatorio(informarObra, "Debe informar si se comunicó a obra misionera");
        if (Boolean.TRUE.equals(informarObra) && (observaciones == null || observaciones.trim().isEmpty())) {
            throw new IllegalArgumentException("Las observaciones son obligatorias cuando se informa a obra misionera");
        }
        if ("OTRO".equalsIgnoreCase(codigoTipoActividad)
                && (descripcionOtro == null || descripcionOtro.trim().isEmpty())) {
            throw new IllegalArgumentException("La descripción es obligatoria para la actividad OTRO");
        }

        this.id = id;
        this.idUsuario = idUsuario;
        this.idTipoActividad = idTipoActividad;
        this.codigoTipoActividad = codigoTipoActividad;
        this.descripcionOtro = descripcionOtro;
        this.estado = estado;
        this.fechaRealizacion = fechaRealizacion;
        this.informarObra = informarObra;
        this.observaciones = observaciones;
        this.puntos = puntos;
    }
}
