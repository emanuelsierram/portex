package com.portex.miantorcha.infraestructura.controlador.consulta.estudio;

import com.portex.miantorcha.dominio.modelo.dto.DtoEstudioBiblico;
import com.portex.miantorcha.dominio.modelo.dto.DtoHistoricoLeccion;
import com.portex.miantorcha.infraestructura.configuracion.SeguridadEstudio;
import com.portex.miantorcha.infraestructura.configuracion.SeguridadMiembro;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estudios-biblicos")
public class ConsultaControladorEstudioBiblico {

    private final ManejadorListarEstudioBiblico manejadorListarEstudioBiblico;
    private final SeguridadMiembro seguridadMiembro;
    private final SeguridadEstudio seguridadEstudio;

    public ConsultaControladorEstudioBiblico(ManejadorListarEstudioBiblico manejadorListarEstudioBiblico, SeguridadMiembro seguridadMiembro, SeguridadEstudio seguridadEstudio) {
        this.manejadorListarEstudioBiblico = manejadorListarEstudioBiblico;
        this.seguridadMiembro = seguridadMiembro;
        this.seguridadEstudio = seguridadEstudio;
    }

    @GetMapping("/actuales/{usuarioId}")
    @PreAuthorize("@seguridadMiembro.puedeConsultarPorUsuarioId(#usuarioId, authentication)")
    public List<DtoEstudioBiblico> consultarActualesPorMiembro(@PathVariable String usuarioId) {
        return this.manejadorListarEstudioBiblico.consultarActualesPorMiembro(usuarioId);
    }
    @GetMapping("/disponibles")
    public List<DtoEstudioBiblico> consultarDisponiblesPorDar() {
        return this.manejadorListarEstudioBiblico.consultarDisponiblesPorDar();
    }
    @GetMapping("/{idEstudioBiblico}/lecciones")
    @PreAuthorize("@seguridadEstudio.puedeConsultarLeccionesPorEstudio(#idEstudioBiblico, authentication)")
    public List<DtoHistoricoLeccion> consultarHistoricoLecciones(@PathVariable Long idEstudioBiblico) {
        return this.manejadorListarEstudioBiblico.consultarHistoricoLecciones(idEstudioBiblico);
    }

}