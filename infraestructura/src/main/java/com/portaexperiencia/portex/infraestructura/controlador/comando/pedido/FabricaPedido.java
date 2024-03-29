package com.portaexperiencia.portex.infraestructura.controlador.comando.pedido;


import com.portaexperiencia.aplicacion.ComandoRespuesta;
import com.portaexperiencia.aplicacion.manejador.ManejadorComandoRespuesta;
import com.portaexperiencia.portex.modelo.entidad.Pedido;
import org.springframework.stereotype.Component;

@Component
public class FabricaPedido{

    public Pedido crear(ComandoPedido comandoPedido){
        return new Pedido(comandoPedido.getId(),
                comandoPedido.getNombre(),
                comandoPedido.getDescripcion(),
                comandoPedido.getFechaInicio(),
                comandoPedido.getFechaFinal(),
                comandoPedido.getPresupuesto(),
                comandoPedido.getIdCliente(),
                comandoPedido.getIdTrabajador(),
                comandoPedido.getIdValoracion());
    }


}
