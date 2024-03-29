package com.portaexperiencia.portex.servicio.pedido;

import com.portaexperiencia.dominio.excepcion.ExcepcionDuplicidad;
import com.portaexperiencia.portex.modelo.dto.DtoPedido;
import com.portaexperiencia.portex.modelo.entidad.Pedido;
import com.portaexperiencia.portex.modelo.entidad.Valoracion;
import com.portaexperiencia.portex.puerto.repositorio.RepositorioPedido;
import com.portaexperiencia.portex.servicio.valoracion.ServicioCrearValoracion;

import java.time.LocalDateTime;
import java.util.List;

public class ServicioCrearPedido {

    private static final String YA_EXISTE_PEDIDO_EN_LA_FECHA_EXTABLECIDA = "Ya existe pedido en la fecha establecida";

    private final RepositorioPedido repositorioPedido;
    private final ServicioCrearValoracion servicioCrearValoracion;

    public ServicioCrearPedido(RepositorioPedido repositorioPedido, ServicioCrearValoracion servicioCrearValoracion) {
        this.repositorioPedido = repositorioPedido;
        this.servicioCrearValoracion = servicioCrearValoracion;
    }


    public Long ejecutar(Pedido pedido){
        validarExistenciaPrevia(pedido);
       Valoracion valoracion = new Valoracion("No valoracion",0);
       pedido.setIdValoracion(servicioCrearValoracion.ejecutar(valoracion));
        return this.repositorioPedido.crear(pedido);

    }


    private void validarExistenciaPrevia(Pedido pedido){

        List<DtoPedido> pedidosExistentes = this.repositorioPedido.listarPedidosPorIdTrabajador(pedido.getIdTrabajador());
        pedidosExistentes.stream()
                .filter(pedidoExistente-> (validarFechasIguales(pedido.getFechaInicio(),pedido.getFechaFinal(),pedidoExistente))||
                                (validarFechasFueraDelRango(pedido.getFechaInicio(),pedido.getFechaFinal(),pedidoExistente))||
                                (validarFechasDentroDelRango(pedido.getFechaInicio(),pedido.getFechaFinal(),pedidoExistente))||
                                (validarFechaInicialDentroDelRango(pedido.getFechaInicio(),pedidoExistente))||
                                (validarFechaFinalDentroDelRango(pedido.getFechaFinal(),pedidoExistente)))
                .forEach(pedidoExistente -> {throw new ExcepcionDuplicidad(YA_EXISTE_PEDIDO_EN_LA_FECHA_EXTABLECIDA);
                });
    }

    private boolean validarFechasIguales(LocalDateTime fechaInicioIngresada, LocalDateTime fechaFinalIngresada, DtoPedido pedidoExistente){
        return fechaInicioIngresada.equals(pedidoExistente.getFechaInicio()) && fechaFinalIngresada.equals(pedidoExistente.getFechaFinal());
    }

    private boolean validarFechasFueraDelRango(LocalDateTime fechaInicioIngresada, LocalDateTime fechaFinalIngresada, DtoPedido pedidoExistente){

        return fechaInicioIngresada.isBefore(pedidoExistente.getFechaInicio()) &&
                fechaFinalIngresada.isAfter(pedidoExistente.getFechaFinal());
    }

    private boolean validarFechasDentroDelRango(LocalDateTime fechaInicioIngresada, LocalDateTime fechaFinalIngresada, DtoPedido pedidoExistente){

        return fechaInicioIngresada.isAfter(pedidoExistente.getFechaInicio()) &&
                fechaFinalIngresada.isBefore(pedidoExistente.getFechaFinal());
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
