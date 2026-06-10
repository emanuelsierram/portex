package com.portex.miantorcha.dominio.modelo.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

import static com.portex.compartido.dominio.ValidadorArgumento.validarObligatorio;
import static com.portex.compartido.dominio.ValidadorArgumento.validarRegex;

@Getter
@AllArgsConstructor
public class DtoMiembro {

    private Long id;
    private String usuarioId;
    private String nombres;
    private String apellidos;
    private String email;
    private String telefono;
    private String perfil;
    private Long idGrupoPequeno;
    private Long idAnciano;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
}
