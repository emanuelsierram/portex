package com.portaexperiencia.portex.infraestructura.controlador.comando.pedido;

import com.portaexperiencia.compartido.aplicacion.ComandoRespuesta;
import com.portaexperiencia.compartido.aplicacion.manejador.ManejadorComandoRespuesta;
import com.portaexperiencia.portex.modelo.entidad.Pedido;
import com.portaexperiencia.portex.servicio.pedido.ServicioCrearPedido;
import org.springframework.stereotype.Component;

@Component
public class ManejadorCrearPedido implements ManejadorComandoRespuesta<ComandoPedido, ComandoRespuesta<Long>> {

    private final ServicioCrearPedido servicioCrearPedido;
    private final FabricaPedido fabricaPedido;

    public ManejadorCrearPedido(ServicioCrearPedido servicioCrearPedido, FabricaPedido fabricaPedido) {
        this.servicioCrearPedido = servicioCrearPedido;
        this.fabricaPedido = fabricaPedido;
    }

    @Override
    public ComandoRespuesta<Long> ejecutar(ComandoPedido comando) {
        Pedido pedido = this.fabricaPedido.crear(comando);
        return new ComandoRespuesta<>(this.servicioCrearPedido.ejecutar(pedido));
    }
}
