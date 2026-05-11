package com.portex.miexperiencia.infraestructura.controlador.consulta.trabajador;


import com.portex.miexperiencia.dominio.modelo.dto.DtoTrabajador;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trabajadores")
//@CrossOrigin(origins = "http://localhost:61400")
public class ConsultaControladorTrabajador {

    private final ManejadorListarTrabajadores manejadorListarTrabajadores;



    public ConsultaControladorTrabajador(ManejadorListarTrabajadores manejadorListarTrabajadores) {
        this.manejadorListarTrabajadores = manejadorListarTrabajadores;
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

}
