package com.portaexperiencia.portex.infraestructura.adaptador.repositorio;

import com.portaexperiencia.infraestructura.jbdc.CustomNamedParameterJdbcTemplate;
import com.portaexperiencia.portex.modelo.entidad.Valoracion;
import com.portaexperiencia.portex.puerto.repositorio.RepositorioValoracion;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioValoracionMysql implements RepositorioValoracion {


    private static String sqlCrear = "INSERT INTO portafolio.valoracion (comentario, valoracion_numerica) VALUES (:comentario, :valoracion)";
    private static String sqlActualizar = "UPDATE portafolio.valoracion SET comentario = :comentario, valoracion_numerica = :valoracion WHERE id_valoracion = :id";



    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    public RepositorioValoracionMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public Long crear(Valoracion valoracion) {
        return this.customNamedParameterJdbcTemplate.crear(valoracion, sqlCrear);
    }

    @Override
    public void actualizar(Valoracion valoracion) {
        this.customNamedParameterJdbcTemplate.actualizar(valoracion,sqlActualizar);
    }
}
