package com.portaexperiencia.portex.infraestructura.adaptador.dao.trabajador;

import com.portaexperiencia.portex.modelo.dto.DtoTrabajador;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeoTrabajador implements RowMapper<DtoTrabajador> {
    @Override
    public DtoTrabajador mapRow(ResultSet rs, int rowNum) throws SQLException {
         Long id = rs.getLong("id_trabajador");
         String nombres = rs.getString("nombres");
         String apellidos = rs.getString("apellidos");
         String telefono = rs.getString("telefono");
         String email = rs.getString("email");
         String cedula = rs.getString("cedula");
         String profesion = rs.getString("profesion");
         String estado = rs.getString("estado");
         String estadoServicio = rs.getString("estado_servicio");
        return new DtoTrabajador(id,nombres,apellidos,telefono,email,cedula,profesion,estado,estadoServicio);
    }
}
