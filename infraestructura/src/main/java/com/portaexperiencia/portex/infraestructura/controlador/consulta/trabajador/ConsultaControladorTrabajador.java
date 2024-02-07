package com.portaexperiencia.portex.infraestructura.controlador.consulta.trabajador;


import com.portaexperiencia.portex.infraestructura.controlador.consulta.ManejadorListarServicio;
import com.portaexperiencia.portex.modelo.dto.DtoServicio;
import com.portaexperiencia.portex.modelo.dto.DtoTrabajador;
import com.portaexperiencia.portex.infraestructura.controlador.consulta.ManejadorListarTrabajadores;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trabajadores")
public class ConsultaControladorTrabajador {

    private final ManejadorListarTrabajadores manejadorListarTrabajadores;
    private final ManejadorListarServicio manejadorListarServicio;



    public ConsultaControladorTrabajador(ManejadorListarTrabajadores manejadorListarTrabajadores, ManejadorListarServicio manejadorListarServicio) {
        this.manejadorListarTrabajadores = manejadorListarTrabajadores;
        this.manejadorListarServicio = manejadorListarServicio;
    }
    @GetMapping
    public List<DtoTrabajador> listar(){
    return this.manejadorListarTrabajadores.ejecutar();
   }

    @GetMapping("/perfil")
    public DtoTrabajador consultarPorCedulaTrabajador(@RequestParam("cedula") String cedula) {
        return this.manejadorListarTrabajadores.ejecutar(cedula);
    }

    @GetMapping("/{id}")
    public DtoTrabajador consultarTrabajadorPorId(@PathVariable Long id) {
        return this.manejadorListarTrabajadores.ejecutar(id);
    }
    @GetMapping("/{id}/servicios")
    public List<DtoServicio> consultarServiciosPorTrabajador(@PathVariable Long id) {
        return this.manejadorListarServicio.ejecutar(id);
    }
}
