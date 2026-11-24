package com.portex.miexperiencia.infraestructura.configuracion;

import com.portex.miexperiencia.dominio.puerto.repositorio.RepositorioPedido;
import com.portex.miexperiencia.dominio.puerto.repositorio.RepositorioValoracion;
import com.portex.miexperiencia.dominio.servicio.pedido.ServicioCrearPedido;
import com.portex.miexperiencia.dominio.servicio.valoracion.ServicioCrearValoracion;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicio {

    @Bean
    public ServicioCrearPedido servicioCrearPedido(RepositorioPedido repositorioPedido, ServicioCrearValoracion servicioCrearValoracion){
        return new ServicioCrearPedido(repositorioPedido, servicioCrearValoracion);
    }

    @Bean
    public ServicioCrearValoracion servicioCrearValoracion(RepositorioValoracion repositorioValoracion){
        return new ServicioCrearValoracion(repositorioValoracion);
    }

}
