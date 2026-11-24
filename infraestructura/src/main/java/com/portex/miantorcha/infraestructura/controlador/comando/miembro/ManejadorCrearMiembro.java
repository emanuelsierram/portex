package com.portex.miantorcha.infraestructura.controlador.comando.miembro;

import com.portex.compartido.aplicacion.ComandoRespuesta;
import com.portex.compartido.aplicacion.manejador.ManejadorComandoRespuesta;
import com.portex.miantorcha.infraestructura.adaptador.cliente.usuario.ClienteUsuario;
import com.portex.miantorcha.infraestructura.adaptador.cliente.usuario.ComandoCrearUsuario;
import com.portex.miantorcha.dominio.modelo.entidad.Miembro;
import com.portex.miantorcha.dominio.modelo.fabrica.FabricaAntorcha;
import com.portex.miantorcha.dominio.puerto.repositorio.RepositorioAntorcha;
import com.portex.miantorcha.dominio.servicio.miembro.ServicioCrearMiembro;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ManejadorCrearMiembro implements ManejadorComandoRespuesta<ComandoMiembro, ComandoRespuesta<Long>> {

    private final ServicioCrearMiembro servicioCrearMiembro;
    private final FabricaMiembro fabricaMiembro;
    private final ClienteUsuario clienteUsuario;

    private final RepositorioAntorcha repositorioAntorcha;
    private final FabricaAntorcha fabricaAntorcha;


    public ManejadorCrearMiembro(ServicioCrearMiembro servicioCrearMiembro,
                                 FabricaMiembro fabricaMiembro,
                                 ClienteUsuario clienteUsuario,
                                 RepositorioAntorcha repositorioAntorcha,
                                 FabricaAntorcha fabricaAntorcha) {
        this.servicioCrearMiembro = servicioCrearMiembro;
        this.fabricaMiembro = fabricaMiembro;
        this.clienteUsuario = clienteUsuario;
        this.repositorioAntorcha = repositorioAntorcha;
        this.fabricaAntorcha = fabricaAntorcha;
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

        try {
            this.clienteUsuario.asignarRol(telefonoLimpio, "USER");

            Miembro miembro = this.fabricaMiembro.crear(comando);
            this.repositorioAntorcha.crear(fabricaAntorcha.crear(null, 0, telefonoLimpio));
            return new ComandoRespuesta<>(this.servicioCrearMiembro.ejecutar(miembro));
        } catch (RuntimeException errorCreacionMiembro) {
            try {
                this.clienteUsuario.eliminarRol(telefonoLimpio, "USER");
                this.clienteUsuario.eliminar(telefonoLimpio);
            } catch (RuntimeException errorEliminacionUsuario) {
                errorCreacionMiembro.addSuppressed(errorEliminacionUsuario);
            }
            throw errorCreacionMiembro;
        }
    }
}