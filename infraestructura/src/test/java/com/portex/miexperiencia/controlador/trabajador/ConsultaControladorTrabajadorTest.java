package com.portex.miexperiencia.controlador.trabajador;

import com.portex.ApplicationMock;
import com.portex.miexperiencia.infraestructura.controlador.consulta.trabajador.ConsultaControladorTrabajador;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@WebMvcTest(ConsultaControladorTrabajador.class)
@ContextConfiguration(classes= ApplicationMock.class)
@ActiveProfiles("test")
public class ConsultaControladorTrabajadorTest {

    private final static String TOKEN_PRUEBA = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlzcyI6ImVtYW51ZWxzaWVycmExNyIsImV4cCI6MTczODc3MjMxOCwiaWF0IjoxNzM3NDc2MzE4fQ._K1yF6uMbzBiDCoGnhMdcgCTsO67HwV1W1duZ0v6S7Q";
    private final static String CEDULA = "12350407178";
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void listarTrabajadoresTest() throws Exception {
        //Momentaneamente hasta que pueda mockear el login de un usuario


        mockMvc.perform(get("/trabajadores")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + TOKEN_PRUEBA)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].nombres", is("KaZia")));
    }

    @Test
    public void consultarPorCedulaTrabajador() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/trabajadores/perfil")
                        .param("cedula", CEDULA)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + TOKEN_PRUEBA)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.cedula").value(CEDULA));

    }

    @Test
    public void consultarTrabajadorPorId() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/trabajadores/{id}", 1L)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + TOKEN_PRUEBA)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L));


    }
}





