package com.portex.miantorcha.infraestructura.adaptador.cliente.usuario;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ClienteUsuario {

    private final RestTemplate restTemplate;

    @Autowired
    public ClienteUsuario(@Value("${portex-usuarios.base-url}") String baseUrl) {
        this.restTemplate = new RestTemplate();
        this.restTemplate.setUriTemplateHandler(
                new org.springframework.web.util.DefaultUriBuilderFactory(baseUrl));
    }

    public ClienteUsuario() {
        this.restTemplate = new RestTemplate();
        this.restTemplate.setUriTemplateHandler(
                new org.springframework.web.util.DefaultUriBuilderFactory(
                        System.getenv().getOrDefault("PORTEX_USUARIOS_BASE_URL", "")));
    }

    ClienteUsuario(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void crear(ComandoCrearUsuario usuario) {
        restTemplate.postForEntity("/usuarios", usuario, Void.class);
    }

    public void asignarRol(String usuario, String rol) {
        restTemplate.postForEntity(
                "/usuarios/{usuario}/roles/{rol}",
                null,
                Void.class,
                usuario,
                rol
        );
    }
}
