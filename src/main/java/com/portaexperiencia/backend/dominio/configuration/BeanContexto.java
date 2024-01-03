package com.portaexperiencia.backend.dominio.configuration;

import com.portaexperiencia.backend.dominio.puerto.repositorio.RepositorioPedido;
import com.portaexperiencia.backend.dominio.servicio.ServicioCrearPedido;
import com.portaexperiencia.infraestructura.jbdc.CustomNamedParameterJdbcTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class BeanContexto {

    @Bean
    public ServicioCrearPedido servicioCrearPedido(RepositorioPedido repositorioPedido){
        return new ServicioCrearPedido(repositorioPedido);
    }
    @Bean
    public CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
            return new CustomNamedParameterJdbcTemplate(namedParameterJdbcTemplate);
    }

}
