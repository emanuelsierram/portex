package com.portaexperiencia.backend.infraestructura.adaptador.dao;

import com.portaexperiencia.backend.dominio.modelo.dto.DtoTrabajador;
import com.portaexperiencia.backend.dominio.puerto.dao.DaoTrabajador;
import com.portaexperiencia.infraestructura.jbdc.CustomNamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class DaoTrabajadorMysql implements DaoTrabajador {

    private static String sqlListar = "SELECT nombres, apellidos, profesion FROM trabajadores WHERE estado = 'activo'";
    private static String sqlConsultarTrabajadorPorCedula = "SELECT id_trabajador, nombres, apellidos, telefono, email, cedula, profesion, estado, estado_servico FROM trabajadores WHERE cedula = :cedula";

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    public DaoTrabajadorMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public List<DtoTrabajador> listar() {
        return customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListar, new MapeoTrabajadorLista());
    }

    @Override
    public DtoTrabajador consultarPorCedulaTrabajador(String cedula) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("cedula", cedula);
        return customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlConsultarTrabajadorPorCedula,paramSource, new MapeoTrabajador());
    }
}
