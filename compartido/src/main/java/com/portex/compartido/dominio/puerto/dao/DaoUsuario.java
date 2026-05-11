package com.portex.compartido.dominio.puerto.dao;

import com.portex.compartido.dominio.modelo.dto.DtoRol;
import com.portex.compartido.dominio.modelo.dto.DtoUsuario;

import java.util.List;

public interface DaoUsuario {

    DtoUsuario consultarUsuarioPorId(String usuarii);

    List<DtoRol> consultarRolesUsuarios(String usuario);

}
