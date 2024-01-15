package com.portaexperiencia.backend.infraestructura.adaptador.dao.usuario;

import com.portaexperiencia.backend.dominio.modelo.dto.Usuario;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeoUsuario implements RowMapper<Usuario>{


    @Override
    public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
        String usuario = rs.getString("usuario");
        String contrasena=rs.getString("contrasena");
        String email=rs.getString("email");
        Boolean desabilitado=rs.getBoolean("desabilitado");
        Boolean bloqueado=rs.getBoolean("bloqueado");
        return new Usuario(usuario,contrasena,email,desabilitado,bloqueado);
    }
}



