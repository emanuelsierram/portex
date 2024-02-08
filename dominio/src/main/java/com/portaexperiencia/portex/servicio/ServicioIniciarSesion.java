package com.portaexperiencia.portex.servicio;

import com.portaexperiencia.infraestructura.jwt.JwtTokenManager;
import com.portaexperiencia.portex.modelo.entidad.Login;


public class ServicioIniciarSesion {

    private final JwtTokenManager jwtTokenManager;

    public ServicioIniciarSesion(JwtTokenManager jwtTokenManager) {
        this.jwtTokenManager = jwtTokenManager;
    }

    public String ejecutar(Login dtoLogin){
        return this.jwtTokenManager.crear(dtoLogin.getUsuario());
    }


}
