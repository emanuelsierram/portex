package com.portex.miexperiencia.infraestructura.controlador.comando.pedido;

import com.portex.compartido.aplicacion.ComandoRespuesta;
import com.portex.compartido.aplicacion.manejador.ManejadorComandoRespuesta;
import com.portex.miexperiencia.dominio.modelo.entidad.Pedido;
import com.portex.miexperiencia.dominio.servicio.pedido.ServicioCrearPedido;
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
