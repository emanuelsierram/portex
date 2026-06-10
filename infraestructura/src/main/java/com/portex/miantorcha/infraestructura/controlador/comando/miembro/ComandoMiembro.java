package com.portex.miantorcha.infraestructura.controlador.comando.miembro;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoMiembro {

    private Long id;
    private String contrasena;
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
