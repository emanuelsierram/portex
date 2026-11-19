package com.portex.miantorcha.dominio.servicio.antorcha;

import com.portex.miantorcha.dominio.modelo.dto.DtoAntorcha;
import com.portex.miantorcha.dominio.modelo.entidad.Antorcha;
import com.portex.miantorcha.dominio.puerto.notificador.PublicadorAntorcha;
import com.portex.miantorcha.dominio.puerto.repositorio.RepositorioAntorcha;

public class ServicioActualizarAntorcha {

    private final RepositorioAntorcha repositorioAntorcha;
    private final PublicadorAntorcha publicadorAntorcha;

    public ServicioActualizarAntorcha(
            RepositorioAntorcha repositorioAntorcha,
            PublicadorAntorcha publicadorAntorcha) {
        this.repositorioAntorcha = repositorioAntorcha;
        this.publicadorAntorcha = publicadorAntorcha;
    }

    public void ejecutar(Antorcha antorcha) {
        ejecutar(antorcha, 0, 0, null);
    }

    public void ejecutar(Antorcha antorcha, int totalPoints, int progressPercentage, Integer nextLevelPoints) {
        repositorioAntorcha.actualizar(antorcha);
        publicadorAntorcha.publicar(new DtoAntorcha(
                antorcha.getId(),
                antorcha.getNivel(),
                antorcha.getIdUsuario(),
                totalPoints,
                progressPercentage,
                nextLevelPoints));
    }
}
