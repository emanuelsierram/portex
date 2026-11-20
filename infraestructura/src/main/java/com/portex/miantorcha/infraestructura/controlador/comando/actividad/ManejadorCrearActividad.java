package com.portex.miantorcha.infraestructura.controlador.comando.actividad;

import com.portex.compartido.aplicacion.ComandoRespuesta;
import com.portex.miantorcha.dominio.servicio.actividad.ServicioGestionarActividad;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.Authentication;

@Component
public class ManejadorCrearActividad {
    private final ServicioGestionarActividad servicio;
    private final FabricaActividad fabrica;

    public ManejadorCrearActividad(ServicioGestionarActividad servicio, FabricaActividad fabrica) {
        this.servicio = servicio;
        this.fabrica = fabrica;
    }

    @Transactional
    public ComandoRespuesta<Long> ejecutar(ComandoActividad comando, Authentication authentication) {
        return new ComandoRespuesta<>(servicio.crear(fabrica.crear(comando, authentication.getName())));
    }
}
