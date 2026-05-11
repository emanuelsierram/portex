package com.portex.miantorcha.infraestructura.controlador.comando.miembro;

import com.portex.compartido.aplicacion.manejador.ManejadorComando;
import com.portex.miantorcha.dominio.servicio.miembro.ServicioEliminarMiembro;
import org.springframework.stereotype.Component;

@Component
public class ManejadorEliminarMiembro implements ManejadorComando<Long> {

    private final ServicioEliminarMiembro servicioEliminarMiembro;

    public ManejadorEliminarMiembro(ServicioEliminarMiembro servicioEliminarMiembro) {
        this.servicioEliminarMiembro = servicioEliminarMiembro;
    }

    @Override
    public void ejecutar(Long idMiembro) {
        this.servicioEliminarMiembro.ejecutar(idMiembro);
    }
}