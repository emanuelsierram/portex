package com.portex.miantorcha.infraestructura.controlador.comando.actividad;

import com.portex.miantorcha.dominio.modelo.entidad.EstadoActividad;
import com.portex.miantorcha.dominio.servicio.actividad.ServicioGestionarActividad;
import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;

@Component
public class ManejadorActualizarActividad {

    private final FabricaActividad fabricaActividad;
    private final ServicioGestionarActividad servicioGestionarActividad;

    public ManejadorActualizarActividad(FabricaActividad fabrica, ServicioGestionarActividad servicio) {
        this.fabricaActividad = fabrica;
        this.servicioGestionarActividad = servicio;
    }

    public void actualizar(Long id, ComandoActividad comando, Authentication authentication) {
        servicioGestionarActividad.actualizar(fabricaActividad.actualizar(comando, id, authentication.getName()));
    }

    public void cambiarEstado(Long id, String estado, Authentication authentication) {
        EstadoActividad estadoActividad = EstadoActividad.desdeValor(estado);
        servicioGestionarActividad.cambiarEstado(fabricaActividad.cambiarEstado(id, authentication.getName(), estadoActividad));
    }

    public void eliminar(Long id, Authentication authentication) {
        servicioGestionarActividad.eliminar(fabricaActividad.consultar(id, authentication.getName()));
    }
}
