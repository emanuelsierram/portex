package com.portex.miexperiencia.infraestructura.configuracion;

import com.portex.compartido.infraestructura.seguridad.jwt.JwtTokenManager;
import com.portex.miexperiencia.dominio.puerto.repositorio.RepositorioPedido;
import com.portex.miexperiencia.dominio.puerto.repositorio.RepositorioValoracion;
import com.portex.miexperiencia.dominio.servicio.pedido.ServicioCrearPedido;
import com.portex.miexperiencia.dominio.servicio.valoracion.ServicioCrearValoracion;
import com.portex.compartido.dominio.servicio.ServicioIniciarSesion;
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
