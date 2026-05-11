package com.portex.miexperiencia.infraestructura.adaptador.dao.trabajador;

import com.portex.compartido.infraestructura.jbdc.CustomNamedParameterJdbcTemplate;
import com.portex.miexperiencia.dominio.modelo.dto.DtoServicio;
import com.portex.miexperiencia.dominio.puerto.dao.DaoServicio;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class DaoServicioMysql implements DaoServicio {

    private static String sqlListarServicios= "SELECT id_servicio, nombre_servicio, descripcion_servicio, categoria_id_fk, trabajador_id_fk FROM portafolio.servicios";

    private static String sqlConsultarServicioPorTrabajador= "SELECT id_servicio, nombre_servicio, descripcion_servicio, categoria_id_fk, trabajador_id_fk FROM portafolio.servicios WHERE trabajador_id_fk = :trabajador_id_fk";
    private static String sqlConsultarServicioPorId= "SELECT id_servicio, nombre_servicio, descripcion_servicio, categoria_id_fk, trabajador_id_fk FROM portafolio.servicios WHERE id_servicio = :id_servicio";


    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    public DaoServicioMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public List<DtoServicio> listarServicios() {
        return customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListarServicios,new MapeoServicio());
    }

    @Override
    public List<DtoServicio> consultarServiciosPorTrabajador(Long idTrabajador) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("trabajador_id_fk", idTrabajador);
        return customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlConsultarServicioPorTrabajador,parameterSource, new MapeoServicio());
    }
    @Override
    public DtoServicio consultarTrabajadorPorId(Long id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id_servicio", id);
        return customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlConsultarServicioPorId,parameterSource, new MapeoServicio());
    }
}
