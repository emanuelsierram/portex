package com.portex.login;

import com.portex.compartido.infraestructura.seguridad.jwt.JwtTokenManager;
import com.portex.compartido.dominio.modelo.entidad.Login;
import com.portex.compartido.dominio.servicio.ServicioIniciarSesion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ServicioIniciarSesionTest {


    @Test
    public void validarInicioDeSesionTest(){

        Login login = new LoginTestDataBuilder().buid();
        JwtTokenManager jwtTokenManager = Mockito.mock(JwtTokenManager.class);
        Mockito.when(jwtTokenManager.crear(Mockito.anyString())).thenReturn("admin");
        ServicioIniciarSesion servicioIniciarSesion = new ServicioIniciarSesion(jwtTokenManager);
        Assertions.assertEquals(servicioIniciarSesion.ejecutar(login),"admin");


    }
}
