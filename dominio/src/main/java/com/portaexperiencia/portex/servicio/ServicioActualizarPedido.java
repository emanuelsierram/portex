package com.portaexperiencia.portex.servicio;

import com.portaexperiencia.portex.modelo.entidad.Pedido;
import com.portaexperiencia.portex.puerto.repositorio.RepositorioPedido;

public class ServicioActualizarPedido {

    private final RepositorioPedido repositorioPedido;

    public ServicioActualizarPedido(RepositorioPedido repositorioPedido) {
        this.repositorioPedido = repositorioPedido;
    }



    public void apalazarFecha(Pedido pedido){
        pedido.validarFechaAplazado(pedido.getFechaInicio());
        this.repositorioPedido.actualizar(pedido);

    }




}
