package com.portex.miantorcha.infraestructura.adaptador.dao.antorcha;

import com.portex.compartido.infraestructura.jbdc.CustomNamedParameterJdbcTemplate;
import com.portex.miantorcha.dominio.modelo.dto.DtoAntorcha;
import com.portex.miantorcha.dominio.puerto.dao.DaoAntorcha;
import com.portex.miantorcha.dominio.puerto.dao.DaoActividad;
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
    private final DaoActividad daoActividad;

    public DaoAntorchaMysql(CustomNamedParameterJdbcTemplate jdbcTemplate, DaoActividad daoActividad) {
        this.jdbcTemplate = jdbcTemplate;
        this.daoActividad = daoActividad;
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
        int total = daoActividad.totalPuntosHechos(idUsuario);
        int nivel = daoActividad.nivelPorPuntos(total);
        Integer siguiente = daoActividad.siguienteNivelPuntos(nivel);
        int progreso = siguiente == null ? 100 : Math.min(100, Math.max(0,
                (total - daoActividad.puntosMinimosNivel(nivel)) * 100
                        / (siguiente - daoActividad.puntosMinimosNivel(nivel))));
        return new DtoAntorcha(antorcha.getId(), nivel, idUsuario, total, progreso, siguiente);
    }
}
