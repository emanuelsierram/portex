package com.portex.miantorcha.infraestructura.controlador.comando.actividad;

import com.portex.compartido.aplicacion.ComandoRespuesta;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/actividades")
public class ComandoControladorActividad {
    private final ManejadorCrearActividad manejadorCrearActividad;
    private final ManejadorActualizarActividad manejadorActualizarActividad;

    public ComandoControladorActividad(ManejadorCrearActividad manejadorCrearActividad,
                                       ManejadorActualizarActividad manejadorActualizarActividad) {
        this.manejadorCrearActividad = manejadorCrearActividad;
        this.manejadorActualizarActividad = manejadorActualizarActividad;
    }

    @PostMapping
    public ComandoRespuesta<Long> crear(@RequestBody ComandoActividad comando,
                                        Authentication authentication) {
        return manejadorCrearActividad.ejecutar(comando, authentication);
    }

    @PutMapping("/{id}")
    @PreAuthorize("@seguridadActividad.puedeGestionar(#id, authentication)")
    public void actualizar(@PathVariable Long id, @RequestBody ComandoActividad comando,
                            Authentication authentication) {
        manejadorActualizarActividad.actualizar(id, comando, authentication);
    }

    @PutMapping("/{id}/estado")
    @PreAuthorize("@seguridadActividad.puedeGestionar(#id, authentication)")
    public void cambiarEstado(@PathVariable Long id, @RequestBody ComandoActividad comando,
                               Authentication authentication) {
        manejadorActualizarActividad.cambiarEstado(id, comando.getEstado(), authentication);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@seguridadActividad.puedeGestionar(#id, authentication)")
    public void eliminar(@PathVariable Long id, Authentication authentication) {
        manejadorActualizarActividad.eliminar(id, authentication);
    }
}
