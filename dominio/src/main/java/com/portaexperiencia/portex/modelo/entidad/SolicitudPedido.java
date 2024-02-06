package com.portaexperiencia.portex.modelo.entidad;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.Period;


@Getter
@Setter
public class SolicitudPedido{


    private static final String CERRADO="Cerrado";
    private static final int MES_PERDIDO_NO_CONTADO=1;


    private Long id;
    private String descripcion;
    private String estado;
    private Double valor;
    private LocalDateTime fechaFinalizacion;
    private Long idPedidos;


    public SolicitudPedido(String descripcion, String estado, LocalDateTime fechaFinalEstipulada, Long idPedidos) {
        this.descripcion = descripcion;
        this.estado = estado;
        this.valor = descuentoPorTardanza(fechaFinalEstipulada);
        if (estado.equals(CERRADO)) {
            this.fechaFinalizacion = LocalDateTime.now();
        }
        this.idPedidos = idPedidos;
    }

    private Double descuentoPorTardanza(LocalDateTime fechaFinalEstipulada){
        double valorDescuento=0.0;
        if (fechaFinalEstipulada.toLocalDate().isAfter(this.fechaFinalizacion.toLocalDate())) {
            Period periodo = Period.between(
                    fechaFinalEstipulada.toLocalDate(),
                    this.fechaFinalizacion.toLocalDate()
            );
            int meses= periodo.getMonths()+MES_PERDIDO_NO_CONTADO;
            valorDescuento -= ((valorDescuento * 0.05) * meses);
        }
        return valorDescuento;

    }

}
