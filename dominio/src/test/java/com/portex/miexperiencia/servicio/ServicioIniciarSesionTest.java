package com.portex.miexperiencia.servicio;

import com.portex.compartido.infraestructura.jwt.JwtTokenManager;
import com.portex.miexperiencia.dominio.modelo.entidad.Login;
import com.portex.miexperiencia.dominio.servicio.ServicioIniciarSesion;
import com.portex.miexperiencia.testdatabuilder.LoginTestDataBuilder;
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
