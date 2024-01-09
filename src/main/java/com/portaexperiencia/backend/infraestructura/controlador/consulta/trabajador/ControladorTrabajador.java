package com.portaexperiencia.backend.infraestructura.controlador.consulta.trabajador;


import com.portaexperiencia.backend.dominio.modelo.dto.DtoServicio;
import com.portaexperiencia.backend.dominio.modelo.dto.DtoTrabajador;
import com.portaexperiencia.backend.dominio.puerto.dao.DaoTrabajador;
import com.portaexperiencia.backend.infraestructura.controlador.consulta.ManejadorListarTrabajadores;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trabajadores")
public class ControladorTrabajador {

    private final ManejadorListarTrabajadores manejadorListarTrabajadores;


    public ControladorTrabajador(ManejadorListarTrabajadores manejadorListarTrabajadores) {
        this.manejadorListarTrabajadores = manejadorListarTrabajadores;
    }
    @GetMapping
    public List<DtoTrabajador> listar(){
    return this.manejadorListarTrabajadores.ejecutar();
   }

    @GetMapping("/datos")
    public DtoTrabajador consultarPorCedulaTrabajador(@RequestParam("cedula") String cedula) {
        return this.manejadorListarTrabajadores.ejecutar(cedula);
    }

    @GetMapping("/{id}/servicios")
    public List<DtoServicio> consultarServiciosPorTrabajador(@PathVariable Long id) {
        return this.manejadorListarTrabajadores.ejecutar(id);
    }






}
