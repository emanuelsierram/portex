package com.portaexperiencia.backend.infraestructura.adaptador.dao.trabajador;

import com.portaexperiencia.backend.dominio.modelo.dto.DtoServicio;
import com.portaexperiencia.backend.dominio.modelo.dto.DtoTrabajador;
import com.portaexperiencia.backend.dominio.puerto.dao.DaoTrabajador;
import com.portaexperiencia.backend.infraestructura.adaptador.dao.trabajador.MapeoServicio;
import com.portaexperiencia.backend.infraestructura.adaptador.dao.trabajador.MapeoTrabajador;
import com.portaexperiencia.infraestructura.jbdc.CustomNamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class DaoTrabajadorMysql implements DaoTrabajador {

    private static String sqlListar = "SELECT id_trabajador, nombres, apellidos, telefono, email, cedula, profesion, estado, estado_servicio FROM trabajadores WHERE estado = 'activo'";
    private static String sqlConsultarTrabajadorPorCedula = "SELECT id_trabajador, nombres, apellidos, telefono, email, cedula, profesion, estado, estado_servicio FROM trabajadores WHERE cedula = :cedula";
    private static String sqlConsultarServicioPorTrabajador= "SELECT id_servicio, nombre_servicio, descripcion_servicio, categoria_id_fk, trabajador_id_fk FROM servicios WHERE trabajador_id_fk = :trabajador_id_fk";

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
    public List<DtoServicio> consultarServiciosPorTrabajador(Long idTrabajador) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("trabajador_id_fk", idTrabajador);
        return customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlConsultarServicioPorTrabajador,parameterSource, new MapeoServicio());
    }
}
