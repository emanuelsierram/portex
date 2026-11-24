package com.portex.miantorcha.infraestructura.adaptador.dao.actividad;

import com.portex.miantorcha.dominio.modelo.dto.DtoTipoActividad;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeoTipoActividad implements RowMapper<DtoTipoActividad> {

    @Override
    public DtoTipoActividad mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new DtoTipoActividad(
                rs.getLong("id"),
                rs.getString("codigo"),
                rs.getString("nombre"),
                rs.getInt("puntos"),
                rs.getBoolean("activo"));
    }
}
