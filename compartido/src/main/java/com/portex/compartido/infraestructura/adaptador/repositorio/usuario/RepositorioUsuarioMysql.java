package com.portex.compartido.infraestructura.adaptador.repositorio.usuario;

import com.portex.compartido.dominio.modelo.dto.DtoUsuario;
import com.portex.compartido.dominio.puerto.repositorio.RepositorioUsuario;
import com.portex.compartido.infraestructura.jbdc.CustomNamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class RepositorioUsuarioMysql implements RepositorioUsuario {
    private static final String SQL_CREAR = "INSERT INTO portafolio.users (usuario, contrasena, email, bloqueado, desabilitado) VALUES (:usuario, :contrasena, :email, 0, 0)";
    private static final String SQL_ASIGNAR_ROL = "INSERT INTO portafolio.user_role (usuario, rol, fecha_creacion) VALUES (:usuario, :rol, :fecha)";

    private final CustomNamedParameterJdbcTemplate customJdbc;

    public RepositorioUsuarioMysql(CustomNamedParameterJdbcTemplate customJdbc) {
        this.customJdbc = customJdbc;
    }

    @Override
    public void crear(DtoUsuario usuario) {
        this.customJdbc.actualizar(usuario, SQL_CREAR);
    }

    @Override
    public void asignarRol(String usuario, String rol) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("usuario", usuario)
                .addValue("rol", rol)
                .addValue("fecha", LocalDateTime.now());
        this.customJdbc.getNamedParameterJdbcTemplate().update(SQL_ASIGNAR_ROL, params);
    }
}
