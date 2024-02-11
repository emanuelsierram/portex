package com.portaexperiencia.portex.infraestructura.adaptador.dao.trabajador;

import com.portaexperiencia.portex.modelo.dto.DtoServicio;
import com.portaexperiencia.portex.modelo.dto.DtoTrabajador;
import com.portaexperiencia.portex.puerto.dao.DaoTrabajador;
import com.portaexperiencia.infraestructura.jbdc.CustomNamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class DaoTrabajadorMysql implements DaoTrabajador {

    private static String sqlListar = "SELECT id_trabajador, nombres, apellidos, telefono, email, cedula, profesion, estado, estado_servicio FROM portafolio.trabajadores WHERE estado = 'activo'";
    private static String sqlConsultarTrabajadorPorCedula = "SELECT id_trabajador, nombres, apellidos, telefono, email, cedula, profesion, estado, estado_servicio FROM portafolio.trabajadores WHERE cedula = :cedula";
    private static String sqlConsultarTrabajadorPorId = "SELECT id_trabajador, nombres, apellidos, telefono, email, cedula, profesion, estado, estado_servicio FROM portafolio.trabajadores WHERE id_trabajador = :id_trabajador";

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    public DaoTrabajadorMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public List<DtoTrabajador> listar() {
        return customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListar, new MapeoTrabajador());
    }

    @Override
    public DtoTrabajador consultarPorCedulaTrabajador(String cedula) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("cedula", cedula);
        return customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlConsultarTrabajadorPorCedula,paramSource, new MapeoTrabajador());
    }

    @Override
    public DtoTrabajador consultarPorIdTrabajador(Long id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id_trabajador", id);

        return customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlConsultarTrabajadorPorId,parameterSource, new MapeoTrabajador());
    }
}
