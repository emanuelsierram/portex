package com.portex.miexperiencia.dominio.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
@Getter
@AllArgsConstructor
public class DtoPedido {

    private Long id;
    private String nombre;
    private String descripcion;
    private String estado;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFinal;
    private Double presupuesto;
    private Long idCliente;
    private Long idTrabajador;
    private Long idValoracion;
}
