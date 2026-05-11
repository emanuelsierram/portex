package com.portex.miexperiencia.dominio.servicio.pedido;

import com.portex.miexperiencia.dominio.modelo.entidad.Pedido;
import com.portex.miexperiencia.dominio.puerto.repositorio.RepositorioPedido;

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
