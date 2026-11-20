package com.portex.miantorcha.infraestructura.adaptador.dao.antorcha;

import com.portex.compartido.infraestructura.jbdc.CustomNamedParameterJdbcTemplate;
import com.portex.miantorcha.dominio.modelo.dto.DtoAntorcha;
import com.portex.miantorcha.dominio.modelo.dto.DtoProgresoAntorcha;
import com.portex.miantorcha.dominio.puerto.dao.DaoAntorcha;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DaoAntorchaMysql implements DaoAntorcha {

    private static final String SQL_CONSULTAR_POR_ID =
            "SELECT id, nivel, id_usuario FROM mi_antorcha.antorcha WHERE id = :id";
    private static final String SQL_CONSULTAR_POR_USUARIO_ID =
            "SELECT id, nivel, id_usuario FROM mi_antorcha.antorcha WHERE id_usuario = :idUsuario";

    private final CustomNamedParameterJdbcTemplate jdbcTemplate;

    public DaoAntorchaMysql(CustomNamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public DtoAntorcha consultarPorId(Long id) {
        MapSqlParameterSource parametros = new MapSqlParameterSource("id", id);
        List<DtoAntorcha> resultados = jdbcTemplate.getNamedParameterJdbcTemplate()
                .query(SQL_CONSULTAR_POR_ID, parametros, new MapeoAntorcha());
        return resultados.isEmpty() ? null : resultados.get(0);
    }

    @Override
    public DtoAntorcha consultarPorUsuarioId(String idUsuario) {
        MapSqlParameterSource parametros = new MapSqlParameterSource("idUsuario", idUsuario);
        List<DtoAntorcha> resultados = jdbcTemplate.getNamedParameterJdbcTemplate()
                .query(SQL_CONSULTAR_POR_USUARIO_ID, parametros, new MapeoAntorcha());
        if (resultados.isEmpty()) {
            return null;
        }
        DtoAntorcha antorcha = resultados.get(0);
        DtoProgresoAntorcha progresoAntorcha = consultarProgresoAntorcha(idUsuario);
        int total = progresoAntorcha.getTotalPuntos();
        int nivel = progresoAntorcha.getNivel();
        Integer siguiente = progresoAntorcha.getPuntosSiguienteNivel();
        int progreso = siguiente == null ? 100 : Math.min(100, Math.max(0,
                (total - progresoAntorcha.getPuntosMinimosNivel()) * 100
                        / (siguiente - progresoAntorcha.getPuntosMinimosNivel())));
        return new DtoAntorcha(antorcha.getId(), nivel, idUsuario, total, progreso, siguiente);
    }

    @Override
    public DtoProgresoAntorcha consultarProgresoAntorcha(String idUsuario) {
        String sql = "SELECT puntos.total_puntos, " +
                "COALESCE((SELECT nivel FROM mi_antorcha.niveles_antorcha " +
                "WHERE puntos_minimos <= puntos.total_puntos ORDER BY puntos_minimos DESC LIMIT 1), 0) AS nivel, " +
                "COALESCE((SELECT puntos_minimos FROM mi_antorcha.niveles_antorcha " +
                "WHERE puntos_minimos <= puntos.total_puntos ORDER BY puntos_minimos DESC LIMIT 1), 0) " +
                "AS puntos_minimos_nivel, " +
                "(SELECT puntos_minimos FROM mi_antorcha.niveles_antorcha " +
                "WHERE puntos_minimos > puntos.total_puntos ORDER BY puntos_minimos ASC LIMIT 1) " +
                "AS puntos_siguiente_nivel " +
                "FROM (SELECT COALESCE(SUM(puntos), 0) AS total_puntos " +
                "FROM mi_antorcha.actividades " +
                "WHERE id_usuario = :idUsuario AND estado = 'HECHO') puntos";
        MapSqlParameterSource parametros = new MapSqlParameterSource("idUsuario", idUsuario);
        return jdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sql, parametros,
                (rs, rowNum) -> new DtoProgresoAntorcha(
                        rs.getInt("total_puntos"),
                        rs.getInt("nivel"),
                        rs.getInt("puntos_minimos_nivel"),
                        rs.getObject("puntos_siguiente_nivel", Integer.class)));
    }
}
