package com.portex.miantorcha.infraestructura.adaptador.cliente.usuario;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

class ClienteUsuarioTest {

    private ClienteUsuario clienteUsuario;
    private MockRestServiceServer servidor;

    @BeforeEach
    void configurar() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setUriTemplateHandler(
                new DefaultUriBuilderFactory("http://usuarios.test"));
        servidor = MockRestServiceServer.bindTo(restTemplate).build();
        clienteUsuario = new ClienteUsuario(restTemplate);
    }

    @Test
    void crearUsuarioEnviaPeticionPost() {
        servidor.expect(requestTo("http://usuarios.test/usuarios"))
                .andExpect(method(HttpMethod.POST))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.usuario").value("3010000000"))
                .andExpect(jsonPath("$.contrasena").value("secreta"))
                .andExpect(jsonPath("$.email").value("correo@test.com"))
                .andRespond(withSuccess());

        clienteUsuario.crear(new ComandoCrearUsuario(
                "3010000000",
                "secreta",
                "correo@test.com"
        ));

        servidor.verify();
    }

    @Test
    void asignarRolEnviaPeticionPost() {
        servidor.expect(requestTo("http://usuarios.test/usuarios/3010000000/roles/USER"))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess());

        clienteUsuario.asignarRol("3010000000", "USER");

        servidor.verify();
    }
}
