package com.portex.miantorcha.infraestructura.controlador.comando.estudio;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComandoEstudioBiblico {
    private Long id;
    private String nombrePersona;
    private String telefonoPersona;
    private String direccionPersona;
    private String estado;
    private String idUsuarioAsignado;
    private String personaQueReporta;
    private Long idGrupo;

}