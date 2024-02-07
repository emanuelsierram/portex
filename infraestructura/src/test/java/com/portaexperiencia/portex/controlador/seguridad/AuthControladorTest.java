package com.portaexperiencia.portex.controlador.seguridad;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.portaexperiencia.ApplicationMock;
import com.portaexperiencia.infraestructura.jwt.JwtTokenManager;
import com.portaexperiencia.portex.infraestructura.seguridad.controlador.AuthControloador;
import com.portaexperiencia.portex.modelo.dto.DtoLogin;
import com.portaexperiencia.portex.testdatabuilder.ComandoLoginTestDataBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
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
        DtoLogin dtoLogin = new ComandoLoginTestDataBuilder().buid();
        Authentication authentication = Mockito.mock(Authentication.class);
        AuthenticationManager authenticationManager = Mockito.mock(AuthenticationManager.class);
        JwtTokenManager jwtTokenManager = Mockito.mock(JwtTokenManager.class);
        when(authenticationManager.authenticate(any())).thenReturn(authentication);
        when(jwtTokenManager.crear(any())).thenReturn("fakeToken");
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dtoLogin)))
                        .andExpect(status().isOk())
                        .andExpect(header().string(HttpHeaders.AUTHORIZATION, "fakeToken"));

    }
}
