package com.portex.miantorcha.testdatabuilder;

import com.portex.miantorcha.dominio.modelo.entidad.Miembro;

public class MiembroTestDataBuilder {

    private Long id;
    private String usuarioId; // Será el teléfono limpio
    private String nombres;
    private String apellidos;
    private String email;
    private String telefono;
    private String perfil;
    private Long idGrupoPequeno;
    private Long idAnciano;

    public MiembroTestDataBuilder() {
        this.id = 1L;
        this.usuarioId = "3001234567";
        this.nombres = "Juan";
        this.apellidos = "Pérez";
        this.email = "juan.perez@email.com";
        this.telefono = "3001234567";
        this.perfil = "MIEMBRO";
        this.idGrupoPequeno = null; // Opcional por defecto
        this.idAnciano = null;
    }

    public MiembroTestDataBuilder conTelefono(String telefono) {
        this.telefono = telefono;
        return this;
    }

    public MiembroTestDataBuilder conNombres(String nombres) {
        this.nombres = nombres;
        return this;
    }

    public Miembro build() {
        return new Miembro(id, usuarioId, nombres, apellidos, email, telefono, perfil, idGrupoPequeno, idAnciano);
    }
}