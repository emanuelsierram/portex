package com.portaexperiencia.backend.dominio.servicio;

import com.portaexperiencia.backend.dominio.modelo.entidad.Pedido;
import com.portaexperiencia.backend.dominio.puerto.repositorio.RepositorioPedido;
import com.portaexperiencia.dominio.excepcion.ExcepcionDuplicidad;

public class ServicioActualizarPedido {

    private final RepositorioPedido repositorioPedido;

    public ServicioActualizarPedido(RepositorioPedido repositorioPedido) {
        this.repositorioPedido = repositorioPedido;
    }

    public void ejecutar(Pedido pedido){
        pedido.validarFechaAplazo(pedido.getFechaInicio());
        this.repositorioPedido.actualizar(pedido);

    }




}
