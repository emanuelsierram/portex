package com.portex.miantorcha.dominio.servicio.miembro;

import com.portex.miantorcha.dominio.modelo.entidad.Miembro;
import com.portex.miantorcha.dominio.puerto.repositorio.RepositorioMiembro;

public class ServicioActualizarMiembro {

    private final RepositorioMiembro repositorioMiembro;

    public ServicioActualizarMiembro(RepositorioMiembro repositorioMiembro) {
        this.repositorioMiembro = repositorioMiembro;
    }

    public void ejecutar(Miembro miembro) {
        // Aquí podrías añadir validaciones adicionales antes de actualizar
        this.repositorioMiembro.actualizar(miembro);
    }
}