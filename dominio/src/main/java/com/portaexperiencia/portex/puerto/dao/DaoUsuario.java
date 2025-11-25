package com.portaexperiencia.portex.puerto.dao;

import com.portaexperiencia.portex.modelo.dto.DtoRol;
import com.portaexperiencia.portex.modelo.dto.DtoUsuario;

import java.util.List;

public interface DaoUsuario {

    DtoUsuario consultarUsuarioPorId(String usuarii);

    List<DtoRol> consultarRolesUsuarios(String usuario);

}
