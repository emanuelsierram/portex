package com.portex.miantorcha.infraestructura.adaptador.dao.antorcha;

import com.portex.miantorcha.dominio.modelo.dto.DtoAntorcha;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeoAntorcha implements RowMapper<DtoAntorcha> {

    @Override
    public DtoAntorcha mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new DtoAntorcha(
                rs.getLong("id"),
                rs.getInt("nivel"),
                rs.getString("id_usuario")
        );
    }
}
