package com.portex.compartido.dominio.puerto.repositorio;

import com.portex.compartido.dominio.modelo.dto.DtoUsuario;

public interface RepositorioUsuario {
    void crear(DtoUsuario usuario);
    void asignarRol(String usuario, String rol);
}
