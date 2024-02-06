package com.portaexperiencia.portex.infraestructura.controlador.consulta.trabajador;

import com.portaexperiencia.portex.infraestructura.controlador.consulta.ManejadorListarServicio;
import com.portaexperiencia.portex.modelo.dto.DtoServicio;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/servicios")
public class ConsultaControladorServicio {

    private final ManejadorListarServicio manejadorListarServicio;


    public ConsultaControladorServicio(ManejadorListarServicio manejadorListarServicio) {
        this.manejadorListarServicio = manejadorListarServicio;
    }

    @GetMapping("/{id}/servicios")
    public List<DtoServicio> consultarServiciosPorTrabajador(@PathVariable Long id) {
        return this.manejadorListarServicio.ejecutar(id);
    }
}
