package com.portaexperiencia.backend.infraestructura.adaptador.dao.usuario;

import com.portaexperiencia.backend.dominio.modelo.dto.Rol;
import com.portaexperiencia.infraestructura.jbdc.MapperResult;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class MapeoRoles implements RowMapper<Rol>, MapperResult{


    @Override
    public Rol mapRow(ResultSet rs, int rowNum) throws SQLException {
        String usuario = rs.getString("usuario");
        String rol = rs.getString("rol");
        LocalDateTime fechaCreacion = extraerLocalDateTime(rs, "fecha_creacion");

        return new Rol(usuario,rol,fechaCreacion);
    }
}
