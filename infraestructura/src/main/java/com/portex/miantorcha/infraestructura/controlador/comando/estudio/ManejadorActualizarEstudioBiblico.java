package com.portex.miantorcha.infraestructura.controlador.comando.estudio;

import com.portex.miantorcha.dominio.modelo.entidad.EstudioBiblico;
import com.portex.miantorcha.dominio.modelo.dto.DtoMiembro;
import com.portex.miantorcha.dominio.puerto.dao.DaoMiembro;
import com.portex.compartido.dominio.excepcion.ExcepcionSinDatos;
import com.portex.miantorcha.dominio.servicio.estudio.ServicioActualizarEstudioBiblico;
import org.springframework.stereotype.Component;

@Component
public class ManejadorActualizarEstudioBiblico {

    private final ServicioActualizarEstudioBiblico servicioActualizarEstudioBiblico;
    private final FabricaEstudioBiblico fabricaEstudioBiblico;
    private final DaoMiembro daoMiembro;

    public ManejadorActualizarEstudioBiblico(ServicioActualizarEstudioBiblico servicioActualizarEstudioBiblico, FabricaEstudioBiblico fabricaEstudioBiblico, DaoMiembro daoMiembro) {
        this.servicioActualizarEstudioBiblico = servicioActualizarEstudioBiblico;
        this.fabricaEstudioBiblico = fabricaEstudioBiblico;
        this.daoMiembro = daoMiembro;
    }

    public void ejecutar(ComandoEstudioBiblico comando, Long id) {
        comando.setId(id);
        DtoMiembro miembro = this.daoMiembro.consultarPorUsuarioId(comando.getIdUsuarioAsignado());
        if (miembro == null) {
            throw new ExcepcionSinDatos("No existe un miembro asociado al usuario indicado.");
        }
        Long idUsuarioAsignado = miembro.getId();
        EstudioBiblico estudioBiblico = this.fabricaEstudioBiblico.crear(comando, idUsuarioAsignado);
        this.servicioActualizarEstudioBiblico.ejecutar(estudioBiblico);
    }
}