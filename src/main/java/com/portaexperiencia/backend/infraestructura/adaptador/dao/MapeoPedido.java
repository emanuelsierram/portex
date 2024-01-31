package com.portaexperiencia.backend.infraestructura.adaptador.dao;
import com.portaexperiencia.backend.dominio.modelo.dto.DtoPedido;
import org.springframework.jdbc.core.RowMapper;
import  com.portaexperiencia.infraestructura.jbdc.MapperResult;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class MapeoPedido implements RowMapper<DtoPedido>, MapperResult {
    @Override
    public DtoPedido mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("id_pedido");
        String nombre = rs.getString("nombre_pedido");
        String descripcion = rs.getString("descripcion") ;
        String estado = rs.getString("estado");
        LocalDateTime fechaCreacion = extraerLocalDateTime(rs, "fecha_creacion");
        LocalDateTime fechaInicio = extraerLocalDateTime(rs, "fecha_inicio");
        LocalDateTime fechaFinal = extraerLocalDateTime(rs, "fecha_final");
        Long idCliente = rs.getLong("cliente_id_fk");
        Long idTrabajador = rs.getLong("trabajador_id_fk");
        Long idValoracion = rs.getLong("valoracion_id_fk");

        return new DtoPedido(id,nombre,descripcion,estado,fechaCreacion,fechaInicio,fechaFinal,idCliente,idTrabajador,idValoracion);
    }
}
