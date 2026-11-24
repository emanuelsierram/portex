package com.portex;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import com.portex.compartido.infraestructura.seguridad.FiltroIntrospeccionJwt;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import org.mockito.Mockito;

import javax.sql.DataSource;
import java.util.List;

@ComponentScan("com.portex")
@Profile("test")
public class ApplicationMock {


    @Bean
    public DataSource dataSourceH2() {
        // Configura y crea una base de datos H2 en memoria
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .build();
    }

    @Bean(initMethod = "migrate")
    public Flyway flyway(DataSource dataSource) {
        // Configura Flyway para usar la base de datos H2 y los scripts de migración
        return Flyway.configure().locations("filesystem:../src/main/resources","filesystem:src/test/resources").baselineOnMigrate(true)
                .dataSource(dataSource).load();
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean
    @Primary
    @Profile("test")
    public FiltroIntrospeccionJwt filtroIntrospeccionJwt() {
        FiltroIntrospeccionJwt filtro = Mockito.mock(FiltroIntrospeccionJwt.class);
        try {
            Mockito.doAnswer(invocacion -> {
                ServletRequest request = invocacion.getArgument(0);
                ServletResponse response = invocacion.getArgument(1);
                FilterChain chain = invocacion.getArgument(2);

                String authorization = ((jakarta.servlet.http.HttpServletRequest) request)
                        .getHeader("Authorization");
                if ("Bearer token-prueba".equals(authorization)) {
                    SecurityContextHolder.getContext().setAuthentication(
                            new UsernamePasswordAuthenticationToken(
                                    "admin",
                                    null,
                                    List.of(new SimpleGrantedAuthority("ROLE_ADMIN"),
                                            new SimpleGrantedAuthority("ROLE_USER"))
                            )
                    );
                } else if ("Bearer token-usuario-prueba".equals(authorization)) {
                    SecurityContextHolder.getContext().setAuthentication(
                            new UsernamePasswordAuthenticationToken(
                                    "usuario-1",
                                    null,
                                    List.of(new SimpleGrantedAuthority("ROLE_USER"))
                            )
                    );
                } else {
                    SecurityContextHolder.clearContext();
                }

                chain.doFilter(request, response);
                return null;
            }).when(filtro).doFilter(
                    Mockito.any(ServletRequest.class),
                    Mockito.any(ServletResponse.class),
                    Mockito.any(FilterChain.class)
            );
        } catch (Exception ignored) {
            throw new IllegalStateException("No fue posible configurar el filtro de prueba", ignored);
        }
        return filtro;
    }
}
