package com.portaexperiencia.backend.dominio.puerto.dao;

import com.portaexperiencia.backend.dominio.modelo.dto.DtoRol;
import com.portaexperiencia.backend.dominio.modelo.dto.DtoUsuario;

import java.util.List;

public interface DaoUsuario {

    DtoUsuario consultarUsuarioPorId(String usuarii);

    List<DtoRol> consultarRolesUsuarios(String usuario);

}
