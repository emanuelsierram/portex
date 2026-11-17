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
        repositorioAntorcha.actualizar(antorcha);
        publicadorAntorcha.publicar(new DtoAntorcha(
                antorcha.getId(),
                antorcha.getNivel(),
                antorcha.getIdUsuario()));
    }
}
