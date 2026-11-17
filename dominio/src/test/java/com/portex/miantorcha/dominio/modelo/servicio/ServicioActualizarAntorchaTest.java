package com.portex.miantorcha.dominio.modelo.servicio;

import com.portex.miantorcha.dominio.modelo.entidad.Antorcha;
import com.portex.miantorcha.dominio.puerto.notificador.PublicadorAntorcha;
import com.portex.miantorcha.dominio.puerto.repositorio.RepositorioAntorcha;
import com.portex.miantorcha.dominio.servicio.antorcha.ServicioActualizarAntorcha;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ServicioActualizarAntorchaTest {

    @Test
    void actualizaYPublicaElNuevoEstado() {
        RepositorioAntorcha repositorio = mock(RepositorioAntorcha.class);
        PublicadorAntorcha publicador = mock(PublicadorAntorcha.class);
        ServicioActualizarAntorcha servicio = new ServicioActualizarAntorcha(repositorio, publicador);
        Antorcha antorcha = new Antorcha(1L, 2, "usuario-1");

        servicio.ejecutar(antorcha);

        verify(repositorio).actualizar(antorcha);
        verify(publicador).publicar(any());
    }
}
