package com.portex.miantorcha.infraestructura.controlador.comando.miembro;

import com.portex.compartido.aplicacion.ComandoRespuesta;
import com.portex.compartido.aplicacion.manejador.ManejadorComandoRespuesta;
import com.portex.compartido.dominio.modelo.dto.DtoUsuario;
import com.portex.compartido.dominio.puerto.repositorio.RepositorioUsuario;
import com.portex.miantorcha.dominio.modelo.entidad.Miembro;
import com.portex.miantorcha.dominio.servicio.miembro.ServicioCrearMiembro;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ManejadorCrearMiembro implements ManejadorComandoRespuesta<ComandoMiembro, ComandoRespuesta<Long>> {

    private final ServicioCrearMiembro servicioCrearMiembro;
    private final RepositorioUsuario repositorioUsuario;
    private final PasswordEncoder passwordEncoder;
    private final FabricaMiembro fabricaMiembro;

    public ManejadorCrearMiembro(ServicioCrearMiembro servicioCrearMiembro,
                                 RepositorioUsuario repositorioUsuario,
                                 PasswordEncoder passwordEncoder,
                                 FabricaMiembro fabricaMiembro) {
        this.servicioCrearMiembro = servicioCrearMiembro;
        this.repositorioUsuario = repositorioUsuario;
        this.passwordEncoder = passwordEncoder;
        this.fabricaMiembro = fabricaMiembro;
    }

    @Override
    @Transactional
    public ComandoRespuesta<Long> ejecutar(ComandoMiembro comando) {

        String telefonoLimpio = comando.getTelefono().replaceAll("[\\s-]", "");
        comando.setTelefono(telefonoLimpio);

        String passCifrada = passwordEncoder.encode(comando.getContrasena());
        DtoUsuario usuarioSeguridad = new DtoUsuario(telefonoLimpio, passCifrada, comando.getEmail(), false, false);

        this.repositorioUsuario.crear(usuarioSeguridad);
        this.repositorioUsuario.asignarRol(telefonoLimpio, "USER"); // Asignamos el rol al teléfono

        Miembro miembro = this.fabricaMiembro.crear(comando);
        return new ComandoRespuesta<>(this.servicioCrearMiembro.ejecutar(miembro));
    }
}