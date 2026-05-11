package com.portex.miantorcha.dominio.servicio.miembro;

import com.portex.compartido.dominio.excepcion.ExcepcionDuplicidad;
import com.portex.miantorcha.dominio.modelo.entidad.Miembro;
import com.portex.miantorcha.dominio.puerto.repositorio.RepositorioMiembro;

public class ServicioCrearMiembro {

    private static final String EL_EMAIL_YA_EXISTE = "Ya existe un miembro registrado con este correo electrónico";
    private final RepositorioMiembro repositorioMiembro;

    public ServicioCrearMiembro(RepositorioMiembro repositorioMiembro) {
        this.repositorioMiembro = repositorioMiembro;
    }

    public Long ejecutar(Miembro miembro) {
        if (this.repositorioMiembro.existeEmail(miembro.getEmail())) {
            throw new ExcepcionDuplicidad(EL_EMAIL_YA_EXISTE);
        }
        return this.repositorioMiembro.crear(miembro);
    }
}