package com.portex.miexperiencia.infraestructura.adaptador.dao.usuario;

import com.portex.miexperiencia.dominio.modelo.dto.DtoUsuario;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeoUsuario implements RowMapper<DtoUsuario>{


    @Override
    public DtoUsuario mapRow(ResultSet rs, int rowNum) throws SQLException {
        String usuario = rs.getString("usuario");
        String contrasena=rs.getString("contrasena");
        String email=rs.getString("email");
        Boolean desabilitado=rs.getBoolean("desabilitado");
        Boolean bloqueado=rs.getBoolean("bloqueado");
        return new DtoUsuario(usuario,contrasena,email,desabilitado,bloqueado);
    }
}



