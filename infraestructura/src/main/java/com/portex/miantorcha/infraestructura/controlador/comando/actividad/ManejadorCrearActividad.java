package com.portex.miantorcha.infraestructura.controlador.comando.actividad;

import com.portex.compartido.aplicacion.ComandoRespuesta;
import com.portex.miantorcha.dominio.servicio.actividad.ServicioGestionarActividad;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;

@Component
public class ManejadorCrearActividad {
    private final ServicioGestionarActividad servicio;

    public ManejadorCrearActividad(ServicioGestionarActividad servicio) {
        this.servicio = servicio;
    }

    @Transactional
    public ComandoRespuesta<Long> ejecutar(ComandoActividad comando, Principal principal) {
        return new ComandoRespuesta<>(servicio.crear(principal.getName(), comando.getIdTipoActividad(),
                comando.getDescripcionOtro(), comando.getFechaRealizacion(), comando.getInformarObra(),
                comando.getObservaciones()));
    }
}
