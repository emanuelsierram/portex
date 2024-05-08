package com.portaexperiencia.portex.infraestructura.configuracion;

import com.portaexperiencia.infraestructura.jwt.JwtTokenManager;
import com.portaexperiencia.portex.puerto.repositorio.RepositorioPedido;
import com.portaexperiencia.portex.puerto.repositorio.RepositorioValoracion;
import com.portaexperiencia.portex.servicio.pedido.ServicioCrearPedido;
import com.portaexperiencia.portex.servicio.valoracion.ServicioCrearValoracion;
import com.portaexperiencia.portex.servicio.ServicioIniciarSesion;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicio {

    @Bean
    public ServicioCrearPedido servicioCrearPedido(RepositorioPedido repositorioPedido, ServicioCrearValoracion servicioCrearValoracion){
        return new ServicioCrearPedido(repositorioPedido, servicioCrearValoracion);
    }

    @Bean
    public ServicioIniciarSesion servicioIniciarSesion(JwtTokenManager jwtTokenManager){
        return new ServicioIniciarSesion(jwtTokenManager);
    }

    @Bean
    public ServicioCrearValoracion servicioCrearValoracion(RepositorioValoracion repositorioValoracion){
        return new ServicioCrearValoracion(repositorioValoracion);
    }

}
