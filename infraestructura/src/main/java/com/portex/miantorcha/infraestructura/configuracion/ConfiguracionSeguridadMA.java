package com.portex.miantorcha.infraestructura.configuracion;

import com.portex.compartido.infraestructura.seguridad.FiltroIntrospeccionJwt;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class ConfiguracionSeguridadMA {

    private final FiltroIntrospeccionJwt filtroIntrospeccionJwt;

    public ConfiguracionSeguridadMA(FiltroIntrospeccionJwt filtroIntrospeccionJwt) {
        this.filtroIntrospeccionJwt = filtroIntrospeccionJwt;
    }

    @Bean
    @Order(1) // Prioridad alta para que evalúe sus rutas primero
    public SecurityFilterChain filterChainAntorcha(HttpSecurity http) throws Exception {
        http
                // Indicamos que esta cadena SOLO aplica para las rutas de Mi Antorcha
                .securityMatcher("/miembros/**", "/api/estudios-biblicos/**")
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/miembros").permitAll()
                        .requestMatchers(HttpMethod.GET, "/miembros").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/miembros/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/estudios-biblicos/**").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/estudios-biblicos/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/estudios-biblicos/lecciones").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/api/estudios-biblicos/**").authenticated()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(filtroIntrospeccionJwt, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}