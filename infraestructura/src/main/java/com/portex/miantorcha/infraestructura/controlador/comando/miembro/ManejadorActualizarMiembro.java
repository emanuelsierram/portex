package com.portex.miantorcha.infraestructura.controlador.comando.miembro;

import com.portex.compartido.aplicacion.manejador.ManejadorComando;
import com.portex.miantorcha.dominio.modelo.entidad.Miembro;
import com.portex.miantorcha.dominio.servicio.miembro.ServicioActualizarMiembro;
import org.springframework.stereotype.Component;

@Component
public class ManejadorActualizarMiembro implements ManejadorComando<ComandoMiembro> {

    private final ServicioActualizarMiembro servicioActualizarMiembro;
    private final FabricaMiembro fabricaMiembro;

    public ManejadorActualizarMiembro(ServicioActualizarMiembro servicioActualizarMiembro, FabricaMiembro fabricaMiembro) {
        this.servicioActualizarMiembro = servicioActualizarMiembro;
        this.fabricaMiembro = fabricaMiembro;
    }

    @Override
    public void ejecutar(ComandoMiembro comando) {
        // La fábrica construye la entidad Miembro con los datos del comando para ser actualizada
        Miembro miembro = this.fabricaMiembro.crear(comando);
        this.servicioActualizarMiembro.ejecutar(miembro);
    }
}