/*package com.portaexperiencia.portex.infraestructura.controlador.consulta.trabajador;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/img/servicios")
public class ConsultaControladorImagenServicio {


    private final ManejadorImagenServicio manejadorImagenServicio;

    public ConsultaControladorImagenServicio(ManejadorImagenServicio manejadorImagenServicio) {
        this.manejadorImagenServicio = manejadorImagenServicio;
    }

    @GetMapping("/{nombre}/{blob}")
    public String listarServicios(@PathVariable String nombre, @PathVariable String blob) {
        return this.manejadorImagenServicio.ejecutar(nombre,blob);
    }
}
*/