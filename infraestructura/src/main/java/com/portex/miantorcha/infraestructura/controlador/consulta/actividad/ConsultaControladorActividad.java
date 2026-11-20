package com.portex.miantorcha.infraestructura.controlador.consulta.actividad;

import com.portex.miantorcha.dominio.modelo.dto.DtoActividad;
import com.portex.miantorcha.dominio.modelo.dto.DtoTipoActividad;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/actividades")
public class ConsultaControladorActividad {
    private final ManejadorConsultarActividad manejador;

    public ConsultaControladorActividad(ManejadorConsultarActividad manejador) {
        this.manejador = manejador;
    }

    @GetMapping("/tipos")
    public ResponseEntity<List<DtoTipoActividad>> listarTipos() {
        return ResponseEntity.ok(manejador.listarTipos());
    }

    @GetMapping
    public ResponseEntity<List<DtoActividad>> listar(Authentication authentication) {
        return ResponseEntity.ok(manejador.listarPorUsuario(authentication.getName()));
    }

    @GetMapping("/{id}")
    @PreAuthorize("@seguridadActividad.puedeGestionar(#id, authentication)")
    public ResponseEntity<DtoActividad> consultarPorId(@PathVariable Long id,
                                                       Authentication authentication) {
        DtoActividad actividad = manejador.consultarPorId(id, authentication.getName());
        return actividad == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(actividad);
    }
}
