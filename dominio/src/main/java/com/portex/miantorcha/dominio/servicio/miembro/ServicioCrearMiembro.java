package com.portex.miantorcha.dominio.servicio.miembro;

import com.portex.compartido.dominio.excepcion.ExcepcionDuplicidad;
import com.portex.compartido.dominio.modelo.dto.DtoUsuario;
import com.portex.compartido.dominio.puerto.repositorio.RepositorioUsuario;
import com.portex.miantorcha.dominio.modelo.entidad.Miembro;
import com.portex.miantorcha.dominio.puerto.repositorio.RepositorioMiembro;


public class ServicioCrearMiembro {

    private static final String EL_TELEFONO_YA_EXISTE = "Ya existe un miembro registrado con este telefono";
    private final RepositorioMiembro repositorioMiembro;

    private final RepositorioUsuario repositorioUsuario;

    public ServicioCrearMiembro(RepositorioMiembro repositorioMiembro, RepositorioUsuario repositorioUsuario) {
        this.repositorioMiembro = repositorioMiembro;
        this.repositorioUsuario = repositorioUsuario;
    }

    public Long ejecutar(Miembro miembro, DtoUsuario usuarioSeguridad, String telefonoLimpio) {
        if (this.repositorioMiembro.existeTelefono(miembro.getTelefono())) {
            throw new ExcepcionDuplicidad(EL_TELEFONO_YA_EXISTE);
        }

        this.repositorioUsuario.crear(usuarioSeguridad);
        this.repositorioUsuario.asignarRol(telefonoLimpio, "USER");


        return this.repositorioMiembro.crear(miembro);
    }
}