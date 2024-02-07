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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@WebMvcTest(ConsultaControladorTrabajador.class)
@ContextConfiguration(classes= ApplicationMock.class)
@ActiveProfiles("test")
public class ConsultaControladorTrabajadorTest {

    private final static String TOKEN_PRUEBA="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlzcyI6ImVtYW51ZWxzaWVycmExNyIsImV4cCI6MTcwNzg0NjkyNSwiaWF0IjoxNzA2NTUwOTI1fQ.HrIaSh6cRrl61OLpreKiBaUq25magF3NvdS3iURAA58";
    private final static String CEDULA="12350407178";
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void listarTrabajadoresTest() throws Exception {
         //Momentaneamente hasta que pueda mockear el login de un usuario


        mockMvc.perform(get("/trabajadores")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "+TOKEN_PRUEBA)
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
    public void consultarTrabajadorPorId() throws Exception{
        mockMvc.perform( MockMvcRequestBuilders
                        .get("/trabajadores/{id}", 1L)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + TOKEN_PRUEBA)
                        .accept(MediaType.APPLICATION_JSON))
                        .andDo(print())
                        .andExpect(status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L));


    }

    @Test
    public void consultarServiciosPorTrabajador() throws Exception {
        mockMvc.perform(get("/trabajadores/{id}/servicios",1L)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer "+TOKEN_PRUEBA)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].nombre", is("Mantenimiento de PC")));
    }

}










