package com.portex.miantorcha.infraestructura.controlador.comando.miembro;

import com.portex.compartido.aplicacion.ComandoRespuesta;
import com.portex.compartido.aplicacion.manejador.ManejadorComandoRespuesta;
import com.portex.miantorcha.infraestructura.adaptador.cliente.usuario.ClienteUsuario;
import com.portex.miantorcha.infraestructura.adaptador.cliente.usuario.ComandoCrearUsuario;
import com.portex.miantorcha.dominio.modelo.entidad.Miembro;
import com.portex.miantorcha.dominio.servicio.miembro.ServicioCrearMiembro;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ManejadorCrearMiembro implements ManejadorComandoRespuesta<ComandoMiembro, ComandoRespuesta<Long>> {

    private final ServicioCrearMiembro servicioCrearMiembro;
    private final FabricaMiembro fabricaMiembro;
    private final ClienteUsuario clienteUsuario;


    public ManejadorCrearMiembro(ServicioCrearMiembro servicioCrearMiembro,
                                 FabricaMiembro fabricaMiembro,
                                 ClienteUsuario clienteUsuario) {
        this.servicioCrearMiembro = servicioCrearMiembro;
        this.fabricaMiembro = fabricaMiembro;
        this.clienteUsuario = clienteUsuario;
    }

    @Override
    @Transactional
    public ComandoRespuesta<Long> ejecutar(ComandoMiembro comando) {

        String telefonoLimpio = comando.getTelefono().replaceAll("[\\s-]", "");
        comando.setTelefono(telefonoLimpio);

        ComandoCrearUsuario usuarioSeguridad = new ComandoCrearUsuario(
                telefonoLimpio,
                comando.getContrasena(),
                comando.getEmail()
        );

        this.clienteUsuario.crear(usuarioSeguridad);
        this.clienteUsuario.asignarRol(telefonoLimpio, "USER");

        Miembro miembro = this.fabricaMiembro.crear(comando);
        return new ComandoRespuesta<>(this.servicioCrearMiembro.ejecutar(miembro));
    }
}