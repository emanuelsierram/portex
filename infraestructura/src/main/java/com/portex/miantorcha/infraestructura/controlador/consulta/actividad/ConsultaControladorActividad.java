package com.portex.miantorcha.infraestructura.controlador.consulta.actividad;

import com.portex.miantorcha.dominio.modelo.dto.DtoActividad;
import com.portex.miantorcha.dominio.modelo.dto.DtoTipoActividad;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/actividades")
public class ConsultaControladorActividad {
    private final ManejadorConsultarActividad manejador;

    public ConsultaControladorActividad(ManejadorConsultarActividad manejador) {
        this.manejador = manejador;
    }

    @GetMapping("/tipos")
    public List<DtoTipoActividad> listarTipos() {
        return manejador.listarTipos();
    }

    @GetMapping
    public List<DtoActividad> listar(Principal principal) {
        return manejador.listarPorUsuario(principal.getName());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DtoActividad> consultarPorId(@PathVariable Long id, Principal principal) {
        DtoActividad actividad = manejador.consultarPorId(id, principal.getName());
        return actividad == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(actividad);
    }
}
