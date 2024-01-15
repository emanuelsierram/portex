package com.portaexperiencia.backend.dominio.servicio;

import com.portaexperiencia.backend.dominio.modelo.dto.Rol;
import com.portaexperiencia.backend.dominio.modelo.dto.Usuario;
import com.portaexperiencia.backend.dominio.puerto.dao.DaoUsuario;
import com.portaexperiencia.infraestructura.excepcion.ExcepcionTecnica;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ServicioSeguridadUsuario implements UserDetailsService {

    private static final String ERROR_CONSULTA_USUARIO="Error al consultar usuario";


    private final DaoUsuario daoUsuario;

    public ServicioSeguridadUsuario(DaoUsuario daoUsuario) {
        this.daoUsuario = daoUsuario;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

           Usuario usuario = this.daoUsuario.consultarUsuarioPorId(username);
           String[] roles = this.daoUsuario.consultarRolesUsuarios(username).stream().map(Rol::getRol).toArray(String[]::new);
           return User.builder()
                   .username(usuario.getUsuario())
                   .password(usuario.getContrasena())
                   .roles("ADMIN")
                   .accountLocked(usuario.getEstaBloqueado())
                   .disabled(usuario.getEstaDesabilitado())
                   .build();
    }
}
