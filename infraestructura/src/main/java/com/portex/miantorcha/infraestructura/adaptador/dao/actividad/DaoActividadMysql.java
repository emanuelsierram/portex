package com.portex.miantorcha.infraestructura.adaptador.dao.actividad;

import com.portex.compartido.infraestructura.jbdc.CustomNamedParameterJdbcTemplate;
import com.portex.miantorcha.dominio.modelo.dto.DtoActividad;
import com.portex.miantorcha.dominio.modelo.dto.DtoTipoActividad;
import com.portex.miantorcha.dominio.puerto.dao.DaoActividad;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DaoActividadMysql implements DaoActividad {

    private final CustomNamedParameterJdbcTemplate jdbcTemplate;

    public DaoActividadMysql(CustomNamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<DtoTipoActividad> listarTiposActivos() {
        return jdbcTemplate.getNamedParameterJdbcTemplate().query(
                "SELECT id, codigo, nombre, puntos, activo FROM mi_antorcha.tipos_actividad WHERE activo = TRUE ORDER BY id",
                new MapeoTipoActividad());
    }

    @Override
    public DtoTipoActividad consultarTipoPorId(Long id) {
        List<DtoTipoActividad> tipos = jdbcTemplate.getNamedParameterJdbcTemplate().query(
                "SELECT id, codigo, nombre, puntos, activo FROM mi_antorcha.tipos_actividad WHERE id = :id",
                new MapSqlParameterSource("id", id), new MapeoTipoActividad());
        return tipos.isEmpty() ? null : tipos.get(0);
    }

    @Override
    public DtoActividad consultarPorId(Long id) {
        List<DtoActividad> actividades = jdbcTemplate.getNamedParameterJdbcTemplate().query(sqlBase()
                        + " WHERE a.id = :id",
                new MapSqlParameterSource("id", id), new MapeoActividad());
        return actividades.isEmpty() ? null : actividades.get(0);
    }

    @Override
    public List<DtoActividad> listarPorUsuario(String idUsuario) {
        return jdbcTemplate.getNamedParameterJdbcTemplate().query(sqlBase()
                        + " WHERE a.id_usuario = :idUsuario ORDER BY a.fecha_realizacion DESC, a.id DESC",
                new MapSqlParameterSource("idUsuario", idUsuario), new MapeoActividad());
    }

    @Override
    public int totalPuntosHechos(String idUsuario) {
        Integer total = jdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(
                "SELECT COALESCE(SUM(puntos), 0) FROM mi_antorcha.actividades " +
                        "WHERE id_usuario = :idUsuario AND estado = 'HECHO'",
                new MapSqlParameterSource("idUsuario", idUsuario), Integer.class);
        return total == null ? 0 : total;
    }

    @Override
    public int nivelPorPuntos(int totalPuntos) {
        Integer nivel = jdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(
                "SELECT nivel FROM mi_antorcha.niveles_antorcha " +
                        "WHERE puntos_minimos <= :totalPuntos " +
                        "ORDER BY puntos_minimos DESC LIMIT 1",
                new MapSqlParameterSource("totalPuntos", totalPuntos), Integer.class);
        return nivel == null ? 0 : nivel;
    }

    @Override
    public int puntosMinimosNivel(int nivel) {
        Integer puntos = jdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(
                "SELECT puntos_minimos FROM mi_antorcha.niveles_antorcha WHERE nivel = :nivel",
                new MapSqlParameterSource("nivel", nivel), Integer.class);
        return puntos == null ? 0 : puntos;
    }

    @Override
    public Integer siguienteNivelPuntos(int nivel) {
        List<Integer> puntos = jdbcTemplate.getNamedParameterJdbcTemplate().queryForList(
                "SELECT puntos_minimos FROM mi_antorcha.niveles_antorcha " +
                        "WHERE nivel = :nivel + 1",
                new MapSqlParameterSource("nivel", nivel), Integer.class);
        return puntos.isEmpty() ? null : puntos.get(0);
    }

    private String sqlBase() {
        return "SELECT a.id, a.id_usuario, a.id_tipo_actividad, t.codigo, t.nombre, " +
                "a.descripcion_otro, a.estado, a.fecha_realizacion, a.informar_obra, " +
                "a.observaciones, a.puntos " +
                "FROM mi_antorcha.actividades a " +
                "JOIN mi_antorcha.tipos_actividad t ON t.id = a.id_tipo_actividad";
    }
}
