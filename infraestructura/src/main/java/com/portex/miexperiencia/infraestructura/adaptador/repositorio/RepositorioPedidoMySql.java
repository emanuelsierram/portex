package com.portex.miexperiencia.infraestructura.adaptador.repositorio;

import com.portex.miexperiencia.dominio.modelo.dto.DtoPedido;
import com.portex.miexperiencia.dominio.modelo.entidad.Pedido;
import com.portex.miexperiencia.dominio.puerto.repositorio.RepositorioPedido;
import com.portex.miexperiencia.infraestructura.adaptador.dao.MapeoPedido;
import com.portex.compartido.infraestructura.jbdc.CustomNamedParameterJdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class RepositorioPedidoMySql implements RepositorioPedido {

    private static String sqlCrear = "INSERT INTO portafolio.pedidos (nombre_pedido, descripcion, estado, fecha_creacion, fecha_inicio, fecha_final, presupuesto, cliente_id_fk, trabajador_id_fk, valoracion_id_fk) VALUES (:nombre, :descripcion, :estado, :fechaCreacion, :fechaInicio, :fechaFinal, :presupuesto, :idCliente, :idTrabajador, :idValoracion)";
    private static String sqlListarPorIdPedido = "SELECT id_pedido, nombre_pedido, descripcion, estado, fecha_creacion, fecha_inicio, fecha_final, presupuesto, cliente_id_fk, trabajador_id_fk, valoracion_id_fk FROM portafolio.pedidos WHERE trabajador_id_fk = :trabajador_id_fk";


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
