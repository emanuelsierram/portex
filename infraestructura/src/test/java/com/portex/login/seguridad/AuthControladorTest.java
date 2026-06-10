package com.portex.login.seguridad;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.portex.ApplicationMock;
import com.portex.compartido.infraestructura.seguridad.controlador.AuthControloador;
import com.portex.compartido.dominio.modelo.entidad.Login;
import com.portex.login.seguridad.testdatabuilder.ComandoLoginTestDataBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AuthControloador.class)
@ContextConfiguration(classes= ApplicationMock.class)
@ActiveProfiles("test")
public class AuthControladorTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void loginTest() throws Exception {
        Login dtoLogin = new ComandoLoginTestDataBuilder().buid();
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dtoLogin)))
                        .andExpect(status().isOk())
                        .andExpect(header().exists(HttpHeaders.AUTHORIZATION));
                        //.andExpect(header().string(HttpHeaders.AUTHORIZATION, "fakeToken"));

    }
}
