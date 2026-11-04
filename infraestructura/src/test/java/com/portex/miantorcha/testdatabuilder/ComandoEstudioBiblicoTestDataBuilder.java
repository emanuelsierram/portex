package com.portex.miantorcha.testdatabuilder;

import com.portex.miantorcha.infraestructura.controlador.comando.estudio.ComandoEstudioBiblico;

import java.time.LocalDate;
import java.util.UUID;

public class ComandoEstudioBiblicoTestDataBuilder {

    private Long id;
    private String nombrePersona;
    private String telefonoPersona;
    private String direccionPersona;
    private String estado;
    private String idUsuarioAsignado;
    private String personaQueReporta;
    private Long idGrupo;
    private String frecuencia;
    private LocalDate fechaInicializacion;

    public ComandoEstudioBiblicoTestDataBuilder() {
        this.id = 1L;
        this.nombrePersona = "Pedro Integracion";
        this.telefonoPersona = "3009876543";
        this.direccionPersona = "Avenida Siempre Viva 742";
        this.estado = "por dar";
        this.idUsuarioAsignado = "20";
        this.personaQueReporta = "Reportador Test";
        this.idGrupo = 5L;
        this.frecuencia = "Semanal";
        this.fechaInicializacion = LocalDate.of(2026, 10, 20);
    }

    public ComandoEstudioBiblicoTestDataBuilder conDireccionPersona(String direccionPersona) {
        this.direccionPersona = direccionPersona;
        return this;
    }

    public ComandoEstudioBiblico build() {
        ComandoEstudioBiblico comando = new ComandoEstudioBiblico();
        comando.setId(this.id);
        comando.setNombrePersona(this.nombrePersona);
        comando.setTelefonoPersona(this.telefonoPersona);
        comando.setDireccionPersona(this.direccionPersona);
        comando.setEstado(this.estado);
        comando.setIdUsuarioAsignado(this.idUsuarioAsignado);
        comando.setPersonaQueReporta(this.personaQueReporta);
        comando.setIdGrupo(this.idGrupo);
        comando.setFrecuencia(this.frecuencia);
        comando.setFechaInicializacion(this.fechaInicializacion);
        return comando;
    }
}