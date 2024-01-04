package com.portaexperiencia.backend.infraestructura.controlador.consulta.trabajador;


import com.portaexperiencia.backend.dominio.modelo.dto.DtoTrabajador;
import com.portaexperiencia.backend.dominio.puerto.dao.DaoTrabajador;
import com.portaexperiencia.backend.infraestructura.controlador.consulta.ManejadorListarTrabajadores;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/{cedula}")
    public DtoTrabajador consultarPorCedulaTrabajador(@PathVariable String cedula) {
        return this.manejadorListarTrabajadores.ejecutar(cedula);
    }




}
