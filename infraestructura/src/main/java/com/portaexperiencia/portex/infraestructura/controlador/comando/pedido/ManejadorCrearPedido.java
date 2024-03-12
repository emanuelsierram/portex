package com.portaexperiencia.portex.infraestructura.controlador.comando.pedido;

import com.portaexperiencia.aplicacion.ComandoRespuesta;
import com.portaexperiencia.aplicacion.manejador.ManejadorComandoRespuesta;
import com.portaexperiencia.portex.infraestructura.controlador.comando.pedido.ComandoPedido;
import com.portaexperiencia.portex.modelo.entidad.Pedido;
import com.portaexperiencia.portex.servicio.ServicioCrearPedido;
import org.springframework.stereotype.Component;

@Component
public class ManejadorCrearPedido implements ManejadorComandoRespuesta<ComandoPedido, ComandoRespuesta<Long>> {

    private final ServicioCrearPedido servicioCrearPedido;

    public ManejadorCrearPedido(ServicioCrearPedido servicioCrearPedido) {
        this.servicioCrearPedido = servicioCrearPedido;
    }

    @Override
    public ComandoRespuesta<Long> ejecutar(ComandoPedido comando) {
        Pedido pedido = null;
        return new ComandoRespuesta<>(this.servicioCrearPedido.ejecutar(pedido));
    }
}
