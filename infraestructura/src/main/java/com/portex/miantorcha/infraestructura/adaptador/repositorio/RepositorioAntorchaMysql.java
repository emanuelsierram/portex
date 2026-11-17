package com.portex.miantorcha.infraestructura.adaptador.repositorio;

import com.portex.compartido.infraestructura.jbdc.CustomNamedParameterJdbcTemplate;
import com.portex.miantorcha.dominio.modelo.entidad.Antorcha;
import com.portex.miantorcha.dominio.puerto.repositorio.RepositorioAntorcha;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioAntorchaMysql implements RepositorioAntorcha {

    private static final String SQL_CREAR =
            "INSERT INTO mi_antorcha.antorcha (nivel, id_usuario) " +
                    "VALUES (:nivel, :idUsuario)";
    private static final String SQL_ACTUALIZAR =
            "UPDATE mi_antorcha.antorcha SET nivel = :nivel, id_usuario = :idUsuario " +
                    "WHERE id = :id";

    private final CustomNamedParameterJdbcTemplate jdbcTemplate;

    public RepositorioAntorchaMysql(CustomNamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long crear(Antorcha antorcha) {
        return jdbcTemplate.crear(antorcha, SQL_CREAR);
    }

    @Override
    public void actualizar(Antorcha antorcha) {
        jdbcTemplate.actualizar(antorcha, SQL_ACTUALIZAR);
    }
}
