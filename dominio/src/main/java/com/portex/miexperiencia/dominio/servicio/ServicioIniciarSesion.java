package com.portex.miexperiencia.dominio.servicio;

import com.portex.compartido.infraestructura.jwt.JwtTokenManager;
import com.portex.miexperiencia.dominio.modelo.entidad.Login;


public class ServicioIniciarSesion {

    private final JwtTokenManager jwtTokenManager;

    public ServicioIniciarSesion(JwtTokenManager jwtTokenManager) {
        this.jwtTokenManager = jwtTokenManager;
    }

    public String ejecutar(Login dtoLogin){
        return this.jwtTokenManager.crear(dtoLogin.getUsuario());
    }


}
