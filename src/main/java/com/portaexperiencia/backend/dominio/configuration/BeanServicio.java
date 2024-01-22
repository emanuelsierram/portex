package com.portaexperiencia.backend.dominio.configuration;

import com.portaexperiencia.backend.dominio.puerto.repositorio.RepositorioPedido;
import com.portaexperiencia.backend.dominio.servicio.ServicioCrearPedido;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicio {

    @Bean
    public ServicioCrearPedido servicioCrearPedido(RepositorioPedido repositorioPedido){
        return new ServicioCrearPedido(repositorioPedido);
    }


}
