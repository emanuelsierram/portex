package com.portaexperiencia.backend.dominio.puerto.repositorio;

import com.portaexperiencia.backend.dominio.modelo.dto.DtoPedido;
import com.portaexperiencia.backend.dominio.modelo.entidad.Pedido;

import java.util.List;

public interface RepositorioPedido {

    Long crear(Pedido pedido);

    void actualizar(Pedido pedido);

    boolean existe(Long id);

    List<DtoPedido> listarPedidosPorIdTrabajador(Long id);


}
