package com.portaexperiencia.portex.infraestructura.adaptador.dao.usuario;

import com.portaexperiencia.portex.modelo.dto.DtoRol;
import com.portaexperiencia.portex.modelo.dto.DtoUsuario;
import com.portaexperiencia.portex.puerto.dao.DaoUsuario;
import com.portaexperiencia.infraestructura.jbdc.CustomNamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DaoUsuarioMysql implements DaoUsuario {

    private static String sqlConsultaUsuarioPorId = "SELECT usuario, contrasena, email, bloqueado, desabilitado FROM portafolio.users WHERE usuario = :usuario";
    private static String sqlConsultaRolesUsuario = "SELECT usuario, rol, fecha_creacion  FROM portafolio.user_role WHERE usuario = :usuario";

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    public DaoUsuarioMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }
    
    @Override
    public DtoUsuario consultarUsuarioPorId(String usuario) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("usuario", usuario);

        return customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlConsultaUsuarioPorId,paramSource,new MapeoUsuario());
    }

    @Override
    public List<DtoRol> consultarRolesUsuarios(String usuario) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("usuario", usuario);

        return customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlConsultaRolesUsuario,parameterSource,new MapeoRoles());
    }
}
