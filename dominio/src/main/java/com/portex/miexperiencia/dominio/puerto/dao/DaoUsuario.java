package com.portex.miexperiencia.dominio.puerto.dao;

import com.portex.miexperiencia.dominio.modelo.dto.DtoRol;
import com.portex.miexperiencia.dominio.modelo.dto.DtoUsuario;

import java.util.List;

public interface DaoUsuario {

    DtoUsuario consultarUsuarioPorId(String usuarii);

    List<DtoRol> consultarRolesUsuarios(String usuario);

}
