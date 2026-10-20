package com.portex.miantorcha.dominio.servicio.estudio;

import com.portex.compartido.dominio.excepcion.ExcepcionDuplicidad;
import com.portex.miantorcha.dominio.modelo.entidad.EstudioBiblico;
import com.portex.miantorcha.dominio.modelo.dto.DtoMiembro;
import com.portex.miantorcha.dominio.puerto.dao.DaoEstudioBiblico;
import com.portex.miantorcha.dominio.puerto.dao.DaoMiembro;
import com.portex.miantorcha.dominio.puerto.repositorio.RepositorioEstudioBiblico;
import com.portex.compartido.dominio.excepcion.ExcepcionSinDatos;

public class ServicioCrearEstudioBiblico {

    private static final String EL_TELEFONO_YA_EXISTE = "Ya existe un estudio bíblico registrado con este telefono";

    private final DaoEstudioBiblico daoEstudioBiblico;
    private final DaoMiembro daoMiembro;
    private final RepositorioEstudioBiblico repositorioEstudioBiblico;

    public ServicioCrearEstudioBiblico(DaoEstudioBiblico daoEstudioBiblico, DaoMiembro daoMiembro, RepositorioEstudioBiblico repositorioEstudioBiblico) {
        this.daoEstudioBiblico = daoEstudioBiblico;
        this.daoMiembro = daoMiembro;
        this.repositorioEstudioBiblico = repositorioEstudioBiblico;
    }

    public Long ejecutar(EstudioBiblico estudioBiblico, String usuarioId) {
        if (this.daoEstudioBiblico.existeTelefono(estudioBiblico.getTelefonoPersona())) {
            throw new ExcepcionDuplicidad(EL_TELEFONO_YA_EXISTE);
        }

        DtoMiembro miembro = this.daoMiembro.consultarPorUsuarioId(usuarioId);
        if (miembro == null) {
            throw new ExcepcionSinDatos("No existe un miembro asociado al usuario indicado.");
        }
        estudioBiblico.setIdUsuarioAsignado(miembro.getId());
        estudioBiblico.asignarLeccionCalculada(0);

        return this.repositorioEstudioBiblico.crear(estudioBiblico);
    }
}