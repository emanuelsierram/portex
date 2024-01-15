package com.portaexperiencia.backend.infraestructura.adaptador.dao.usuario;

import com.portaexperiencia.backend.dominio.modelo.dto.Rol;
import com.portaexperiencia.backend.dominio.modelo.dto.Usuario;
import com.portaexperiencia.backend.dominio.puerto.dao.DaoUsuario;
import com.portaexperiencia.infraestructura.jbdc.CustomNamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DaoUsuarioMysql implements DaoUsuario {

    private static String sqlConsultaUsuarioPorId = "SELECT usuario, contrasena, email, bloqueado, desabilitado FROM user WHERE usuario = :usuario";
    private static String sqlConsultaRolesUsuario = "SELECT usuario, rol, fecha_creacion  FROM user_role WHERE usuario = :usuario";

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    public DaoUsuarioMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }
    
    @Override
    public Usuario consultarUsuarioPorId(String usuario) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("usuario", usuario);

        return customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlConsultaUsuarioPorId,paramSource,new MapeoUsuario());
    }

    @Override
    public List<Rol> consultarRolesUsuarios(String usuario) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("usuario", usuario);

        return customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlConsultaRolesUsuario,parameterSource,new MapeoRoles());
    }
}
