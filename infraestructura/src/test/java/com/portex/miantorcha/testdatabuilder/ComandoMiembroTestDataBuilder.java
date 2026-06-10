package com.portex.miantorcha.testdatabuilder;

import com.portex.miantorcha.infraestructura.controlador.comando.miembro.ComandoMiembro;

public class ComandoMiembroTestDataBuilder {

    private String nombres;
    private String apellidos;
    private String email;
    private String telefono;
    private String contrasena;
    private String perfil;

    public ComandoMiembroTestDataBuilder() {
        this.nombres = "María";
        this.apellidos = "Gómez";
        this.email = "maria.gomez@email.com";
        this.telefono = "310 987 6543"; // Con espacios para simular el frontend
        this.contrasena = "secreta123";
        this.perfil = "MIEMBRO";
    }

    public ComandoMiembro build() {
        ComandoMiembro comando = new ComandoMiembro();
        comando.setNombres(this.nombres);
        comando.setApellidos(this.apellidos);
        comando.setEmail(this.email);
        comando.setTelefono(this.telefono);
        comando.setContrasena(this.contrasena);
        comando.setPerfil(this.perfil);
        return comando;
    }
}