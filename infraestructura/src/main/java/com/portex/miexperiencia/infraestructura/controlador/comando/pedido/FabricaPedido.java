package com.portex.miexperiencia.infraestructura.controlador.comando.pedido;


import com.portex.miexperiencia.dominio.modelo.entidad.Pedido;
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
