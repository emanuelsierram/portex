package com.portaexperiencia.backend.dominio.servicio;

import com.portaexperiencia.backend.dominio.modelo.dto.DtoPedido;
import com.portaexperiencia.backend.dominio.modelo.entidad.Pedido;
import com.portaexperiencia.backend.dominio.puerto.repositorio.RepositorioPedido;
import com.portaexperiencia.dominio.excepcion.ExcepcionDuplicidad;

import java.time.LocalDateTime;
import java.util.List;

public class ServicioCrearPedido {

    private static final String YA_EXISTE_PEDIDO_EN_LA_FECHA_EXTABLECIDA = "Ya existe pedido en la fecha0 establecida";

    private final RepositorioPedido repositorioPedido;

    public ServicioCrearPedido(RepositorioPedido repositorioPedido) {
        this.repositorioPedido = repositorioPedido;
    }

    public Long ejecutar(Pedido pedido){
        validarExistenciaPrevia(pedido);
        return this.repositorioPedido.crear(pedido);

    }


    private void validarExistenciaPrevia(Pedido pedido){

        List<DtoPedido> pedidosExistentes = this.repositorioPedido.listarPedidosPorIdTrabajador(pedido.getIdTrabajador());
        pedidosExistentes.stream()
                .filter(pedidoExistente-> (validarFechasFueraDelRango(pedido.getFechaInicio(),pedido.getFechaFinal(),pedidoExistente))||
                                (validarFechaInicialDentroDelRango(pedido.getFechaInicio(),pedidoExistente))||
                                (validarFechaFinalDentroDelRango(pedido.getFechaInicio(),pedidoExistente)))
                .forEach(pedidoExistente -> {throw new ExcepcionDuplicidad(YA_EXISTE_PEDIDO_EN_LA_FECHA_EXTABLECIDA);
                });
    }

    private boolean validarFechasFueraDelRango(LocalDateTime fechaInicioIngresada, LocalDateTime fechaFinalIngresada, DtoPedido pedidoExistente){

        return fechaInicioIngresada.isBefore(pedidoExistente.getFechaInicio()) &&
                fechaFinalIngresada.isAfter(pedidoExistente.getFechaFinal());
    }

    private boolean validarFechaInicialDentroDelRango(LocalDateTime fechaInicioIngresada, DtoPedido pedidoExistente){

        return fechaInicioIngresada.isAfter(pedidoExistente.getFechaInicio()) &&
                fechaInicioIngresada.isBefore(pedidoExistente.getFechaFinal());
    }

    private boolean validarFechaFinalDentroDelRango(LocalDateTime fechaFinalIngresada, DtoPedido pedidoExistente){

        return fechaFinalIngresada.isAfter(pedidoExistente.getFechaInicio()) &&
                fechaFinalIngresada.isBefore(pedidoExistente.getFechaFinal());

    }




}
