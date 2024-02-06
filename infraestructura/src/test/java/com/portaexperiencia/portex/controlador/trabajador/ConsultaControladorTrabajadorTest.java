package com.portaexperiencia.portex.controlador.trabajador;


import com.portaexperiencia.ApplicationMock;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


@RunWith(SpringRunner.class)
@WebMvcTest(ConsultaControladorTrabajadorTest.class)
@ContextConfiguration(classes= ApplicationMock.class)
public class ConsultaControladorTrabajadorTest {

    @Autowired
    private MockMvc mockMvc;







}
