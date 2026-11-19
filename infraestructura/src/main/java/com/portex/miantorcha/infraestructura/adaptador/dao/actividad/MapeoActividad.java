package com.portex.miantorcha.infraestructura.adaptador.dao.actividad;

import com.portex.miantorcha.dominio.modelo.dto.DtoActividad;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeoActividad implements RowMapper<DtoActividad> {

    @Override
    public DtoActividad mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new DtoActividad(
                rs.getLong("id"),
                rs.getString("id_usuario"),
                rs.getLong("id_tipo_actividad"),
                rs.getString("codigo"),
                rs.getString("nombre"),
                rs.getString("descripcion_otro"),
                rs.getString("estado"),
                rs.getObject("fecha_realizacion", java.time.LocalDate.class),
                rs.getBoolean("informar_obra"),
                rs.getString("observaciones"));
    }
}
