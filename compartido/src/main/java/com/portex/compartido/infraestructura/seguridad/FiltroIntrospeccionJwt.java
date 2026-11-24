package com.portex.compartido.infraestructura.seguridad;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

@Component
@Profile("!test")
public class FiltroIntrospeccionJwt extends org.springframework.web.filter.OncePerRequestFilter {

    private final RestTemplate restTemplate;
    private final String apiKeyInterna;

    @Autowired
    public FiltroIntrospeccionJwt(
            @Value("${portex-usuarios.base-url}") String baseUrl,
            @Value("${portex-usuarios.internal-api-key}") String apiKeyInterna) {
        this.restTemplate = new RestTemplate();
        this.restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(baseUrl));
        this.apiKeyInterna = apiKeyInterna;
    }

    public FiltroIntrospeccionJwt() {
        this.restTemplate = new RestTemplate();
        this.restTemplate.setUriTemplateHandler(
                new DefaultUriBuilderFactory(
                        System.getenv().getOrDefault("PORTA_USUARIOS_BASE_URL", "")));
        this.apiKeyInterna = System.getenv().getOrDefault(
                "PORTA_USUARIOS_INTERNAL_API_KEY",
                "");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, authorization);
        headers.set("X-Internal-Api-Key", apiKeyInterna);

        try {
            ResponseEntity<ResultadoIntrospeccion> respuesta =
                    restTemplate.exchange(
                            "/internal/v1/auth/introspect",
                            HttpMethod.POST,
                            new HttpEntity<>(headers),
                            ResultadoIntrospeccion.class
                    );

            ResultadoIntrospeccion resultado = respuesta.getBody();
            if (respuesta.getStatusCode().is2xxSuccessful()
                    && resultado != null
                    && resultado.active()
                    && resultado.usuario() != null) {
                List<SimpleGrantedAuthority> authorities = (resultado.roles() == null
                        ? List.<String>of()
                        : resultado.roles()).stream()
                        .filter(rol -> rol != null && !rol.isBlank())
                        .map(rol -> rol.toUpperCase(Locale.ROOT).replace("ROLE_", ""))
                        .map(rol -> new SimpleGrantedAuthority("ROLE_" + rol))
                        .distinct()
                        .toList();

                SecurityContextHolder.getContext().setAuthentication(
                        new UsernamePasswordAuthenticationToken(
                                resultado.usuario(),
                                null,
                                authorities
                        )
                );
            }
        } catch (RestClientException ignored) {
            SecurityContextHolder.clearContext();
        }

        filterChain.doFilter(request, response);
    }
}
