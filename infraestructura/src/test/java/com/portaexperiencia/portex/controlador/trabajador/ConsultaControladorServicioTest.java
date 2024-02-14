package com.portaexperiencia.portex.controlador.trabajador;

import com.portaexperiencia.ApplicationMock;
import com.portaexperiencia.portex.infraestructura.controlador.consulta.trabajador.ConsultaControladorTrabajador;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(ConsultaControladorTrabajador.class)
@ContextConfiguration(classes= ApplicationMock.class)
@ActiveProfiles("test")
public class ConsultaControladorServicioTest {

    private final static String TOKEN_PRUEBA="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlzcyI6ImVtYW51ZWxzaWVycmExNyIsImV4cCI6MTcwOTI0NTAxMiwiaWF0IjoxNzA3OTQ5MDEyfQ.tMOYJ23yTjWCqkjQaW4-7c5ENWuXajBkk9Z8h2mrUHY";
    private final static String CEDULA="12350407178";
    @Autowired
    private MockMvc mockMvc;


    @Test
    public void listarServiciosTest() throws Exception {

        mockMvc.perform(get("/servicios")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + TOKEN_PRUEBA)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].nombre", is("Mantenimiento de PC")));
    }





    @Test
    public void consultarServiciosPorTrabajador() throws Exception {
        mockMvc.perform(get("/servicios/trabajador/{id}",1L)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "+TOKEN_PRUEBA)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].nombre", is("Mantenimiento de PC")));
    }

    @Test
    public void consultarServiciosPorId() throws Exception {
        mockMvc.perform(get("/servicios/{id}",1L)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "+TOKEN_PRUEBA)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L));

    }

}










