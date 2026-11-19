package com.portex.miantorcha.infraestructura.controlador.comando.actividad;

import com.portex.compartido.aplicacion.ComandoRespuesta;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;

@RestController
@RequestMapping("/api/actividades")
public class ComandoControladorActividad {
    private final ManejadorCrearActividad manejadorCrearActividad;
    private final com.portex.miantorcha.dominio.servicio.actividad.ServicioGestionarActividad servicio;

    public ComandoControladorActividad(ManejadorCrearActividad manejadorCrearActividad,
                                       com.portex.miantorcha.dominio.servicio.actividad.ServicioGestionarActividad servicio) {
        this.manejadorCrearActividad = manejadorCrearActividad;
        this.servicio = servicio;
    }

    @PostMapping
    public ComandoRespuesta<Long> crear(@RequestBody ComandoActividad comando, Principal principal) {
        return manejadorCrearActividad.ejecutar(comando, principal);
    }

    @PutMapping("/{id}/hecha")
    @Transactional
    public void marcarHecha(@PathVariable Long id, Principal principal) {
        servicio.marcarHecha(id, principal.getName());
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void eliminar(@PathVariable Long id, Principal principal) {
        servicio.eliminar(id, principal.getName());
    }
}
