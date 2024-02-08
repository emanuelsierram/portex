package com.portaexperiencia.portex.infraestructura.configuracion;

import com.portaexperiencia.infraestructura.jwt.JwtTokenManager;
import com.portaexperiencia.portex.puerto.repositorio.RepositorioPedido;
import com.portaexperiencia.portex.servicio.ServicioCrearPedido;
import com.portaexperiencia.portex.servicio.ServicioIniciarSesion;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicio {

    @Bean
    public ServicioCrearPedido servicioCrearPedido(RepositorioPedido repositorioPedido){
        return new ServicioCrearPedido(repositorioPedido);
    }

    @Bean
    public ServicioIniciarSesion servicioIniciarSesion(JwtTokenManager jwtTokenManager){
        return new ServicioIniciarSesion(jwtTokenManager);
    }


}
