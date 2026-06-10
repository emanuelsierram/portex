package com.portex.compartido.dominio.servicio;

import com.portex.compartido.dominio.modelo.entidad.Login;
import com.portex.compartido.infraestructura.seguridad.jwt.JwtTokenManager;


public class ServicioIniciarSesion {

    private final JwtTokenManager jwtTokenManager;

    public ServicioIniciarSesion(JwtTokenManager jwtTokenManager) {
        this.jwtTokenManager = jwtTokenManager;
    }

    public String ejecutar(Login dtoLogin){
        return this.jwtTokenManager.crear(dtoLogin.getUsuario());
    }


}
