package com.portaexperiencia.backend.dominio.puerto.dao;

import com.portaexperiencia.backend.dominio.modelo.dto.Rol;
import com.portaexperiencia.backend.dominio.modelo.dto.Usuario;

import java.util.List;

public interface DaoUsuario {

    Usuario consultarUsuarioPorId(String usuarii);

    List<Rol> consultarRolesUsuarios(String usuario);

}
