package com.portaexperiencia.backend.infraestructura.adaptador.repositorio;

import com.portaexperiencia.backend.dominio.modelo.dto.DtoPedido;
import com.portaexperiencia.backend.dominio.modelo.entidad.Pedido;
import com.portaexperiencia.backend.dominio.puerto.repositorio.RepositorioPedido;
import com.portaexperiencia.backend.infraestructura.adaptador.dao.MapeoPedido;
import com.portaexperiencia.infraestructura.jbdc.CustomNamedParameterJdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class RepositorioPedidoMySql implements RepositorioPedido {

    private static String sqlCrear = "INSERT INTO Pedidos (nombre_pedido, descripcion, estado, fecha_creacion, fecha_inicio, fecha_final, cliente_id_fk, trabajador_id_fk, valoracion_id_fk) VALUES (:nombre, :descripcion, :estado, :fechaCreacion, :fechaInicio, :fechaFinal, :idCliente, :idTrabajador, :idValoracion)";
    private static String sqlListarPorIdPedido = "SELECT nombre_pedido, descripcion, estado, fecha_creacion, fecha_inicio, fecha_final, cliente_id_fk, trabajador_id_fk, valoracion_id_fk FROM Pedidos WHERE trabajador_id_fk = :trabajador_id_fk";


    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

   @Autowired
    public RepositorioPedidoMySql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }


    @Override
    public Long crear(Pedido pedido) {
        return this.customNamedParameterJdbcTemplate.crear(pedido, sqlCrear);
    }

    @Override
    public void actualizar(Pedido pedido) {

    }

    @Override
    public boolean existe(Long id) {
        return false;
    }

    @Override
    public List<DtoPedido> listarPedidosPorIdTrabajador(Long idTrabajador) {

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("trabajador_id_fk", idTrabajador);

       return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListarPorIdPedido,parameterSource, new MapeoPedido());
    }
}
