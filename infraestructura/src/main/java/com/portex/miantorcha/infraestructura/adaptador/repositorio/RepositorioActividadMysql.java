package com.portex.miantorcha.infraestructura.adaptador.repositorio;

import com.portex.compartido.infraestructura.jbdc.CustomNamedParameterJdbcTemplate;
import com.portex.miantorcha.dominio.modelo.entidad.Actividad;
import com.portex.miantorcha.dominio.puerto.repositorio.RepositorioActividad;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioActividadMysql implements RepositorioActividad {

    private final CustomNamedParameterJdbcTemplate jdbcTemplate;

    public RepositorioActividadMysql(CustomNamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long crear(Actividad actividad) {
        String sql = "INSERT INTO mi_antorcha.actividades " +
                "(id_usuario, id_tipo_actividad, descripcion_otro, estado, fecha_realizacion, " +
                "informar_obra, observaciones, puntos, id_estudio_biblico) " +
                "VALUES (:idUsuario, :idTipoActividad, :descripcionOtro, :estado, :fechaRealizacion, " +
                ":informarObra, :observaciones, :puntos)";
        MapSqlParameterSource parametros = new MapSqlParameterSource()
                .addValue("idUsuario", actividad.getIdUsuario())
                .addValue("idTipoActividad", actividad.getIdTipoActividad())
                .addValue("descripcionOtro", actividad.getDescripcionOtro())
                .addValue("estado", actividad.getEstado().getValor())
                .addValue("fechaRealizacion", actividad.getFechaRealizacion())
                .addValue("informarObra", actividad.getInformarObra())
                .addValue("observaciones", actividad.getObservaciones())
                .addValue("puntos", actividad.getPuntos());
        org.springframework.jdbc.support.KeyHolder keyHolder = new org.springframework.jdbc.support.GeneratedKeyHolder();
        jdbcTemplate.getNamedParameterJdbcTemplate().update(sql, parametros, keyHolder, new String[]{"id"});
        return keyHolder.getKey().longValue();
    }

    @Override
    public void actualizar(Actividad actividad) {
        String sql = "UPDATE mi_antorcha.actividades SET estado = :estado, " +
                "fecha_realizacion = :fechaRealizacion, informar_obra = :informarObra, " +
                "observaciones = :observaciones, puntos = :puntos, descripcion_otro = :descripcionOtro " +
                "WHERE id = :id";
        MapSqlParameterSource parametros = new MapSqlParameterSource()
                .addValue("estado", actividad.getEstado().getValor())
                .addValue("fechaRealizacion", actividad.getFechaRealizacion())
                .addValue("informarObra", actividad.getInformarObra())
                .addValue("observaciones", actividad.getObservaciones())
                .addValue("puntos", actividad.getPuntos())
                .addValue("descripcionOtro", actividad.getDescripcionOtro())
                .addValue("id", actividad.getId());
        jdbcTemplate.getNamedParameterJdbcTemplate().update(sql, parametros);
    }

    @Override
    public void eliminar(Long id) {
        jdbcTemplate.getNamedParameterJdbcTemplate().update(
                "DELETE FROM mi_antorcha.actividades WHERE id = :id",
                new MapSqlParameterSource("id", id));
    }
}
