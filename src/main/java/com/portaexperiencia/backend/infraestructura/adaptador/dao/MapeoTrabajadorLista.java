package com.portaexperiencia.backend.infraestructura.adaptador.dao;

import com.portaexperiencia.backend.dominio.modelo.dto.DtoTrabajador;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeoTrabajadorLista implements RowMapper<DtoTrabajador> {
    @Override
    public DtoTrabajador mapRow(ResultSet rs, int rowNum) throws SQLException {
         String nombres = rs.getString("nombres");
         String apellidos = rs.getString("apellidos");
         String profesion = rs.getString("profesion");
        return new DtoTrabajador(nombres,apellidos,profesion);
    }
}
