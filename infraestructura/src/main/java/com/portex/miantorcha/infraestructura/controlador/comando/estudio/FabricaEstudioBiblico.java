package com.portex.miantorcha.infraestructura.controlador.comando.estudio;

import com.portex.miantorcha.dominio.modelo.entidad.EstudioBiblico;
import org.springframework.stereotype.Component;

@Component
public class FabricaEstudioBiblico {

    public EstudioBiblico crear(ComandoEstudioBiblico comando) {
        return crear(comando, null);
    }

    public EstudioBiblico crear(ComandoEstudioBiblico comando, Long idUsuarioAsignado) {
        return new EstudioBiblico(
                comando.getId(),
                comando.getNombrePersona(),
                comando.getTelefonoPersona(),
                comando.getDireccionPersona(),
                comando.getEstado(),
                idUsuarioAsignado,
                comando.getPersonaQueReporta(),
                comando.getIdGrupo(),
                comando.getFrecuencia(),
                comando.getFechaInicializacion(),
                null
        );
    }
}