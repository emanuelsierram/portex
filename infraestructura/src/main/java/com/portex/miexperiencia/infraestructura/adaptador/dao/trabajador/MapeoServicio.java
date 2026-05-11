package com.portex.miexperiencia.infraestructura.adaptador.dao.trabajador;

import com.portex.miexperiencia.dominio.modelo.dto.DtoServicio;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeoServicio implements RowMapper<DtoServicio> {
    @Override
    public DtoServicio mapRow(ResultSet rs, int rowNum) throws SQLException {
         Long id = rs.getLong("id_servicio");
         String nombre = rs.getString("nombre_servicio");
         String descripcion = rs.getString("descripcion_servicio");
         Long idCategoria = rs.getLong("categoria_id_fk");
         Long idTrabajador = rs.getLong("trabajador_id_fk");

        return new DtoServicio(id,nombre,descripcion,idCategoria,idTrabajador);
    }
}
