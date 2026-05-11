package com.portex.miantorcha.dominio.servicio.miembro;

import com.portex.miantorcha.dominio.puerto.repositorio.RepositorioMiembro;

public class ServicioEliminarMiembro {

    private final RepositorioMiembro repositorioMiembro;

    public ServicioEliminarMiembro(RepositorioMiembro repositorioMiembro) {
        this.repositorioMiembro = repositorioMiembro;
    }

    public void ejecutar(Long id) {
        this.repositorioMiembro.eliminar(id);
    }
}