package com.portaexperiencia.portex.servicio;

import com.portaexperiencia.compartido.infraestructura.jwt.JwtTokenManager;
import com.portaexperiencia.portex.modelo.entidad.Login;
import com.portaexperiencia.portex.testdatabuilder.LoginTestDataBuilder;
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
