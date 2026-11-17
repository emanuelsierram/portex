package com.portex.miantorcha.infraestructura.controlador.consulta.antorcha;

import com.portex.miantorcha.dominio.modelo.dto.DtoAntorcha;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/antorcha")
public class ConsultaControladorAntorcha {

    private final ManejadorConsultarAntorcha manejadorConsultarAntorcha;

    public ConsultaControladorAntorcha(ManejadorConsultarAntorcha manejadorConsultarAntorcha) {
        this.manejadorConsultarAntorcha = manejadorConsultarAntorcha;
    }

    @GetMapping
    public ResponseEntity<DtoAntorcha> consultar(Authentication authentication) {
        DtoAntorcha antorcha = manejadorConsultarAntorcha
                .consultarPorUsuarioId(authentication.getName());
        return antorcha == null
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(antorcha);
    }
}
