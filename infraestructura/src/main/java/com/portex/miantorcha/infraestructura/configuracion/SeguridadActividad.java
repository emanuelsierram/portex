package com.portex.miantorcha.infraestructura.configuracion;

import com.portex.miantorcha.dominio.modelo.dto.DtoActividad;
import com.portex.miantorcha.dominio.puerto.dao.DaoActividad;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class SeguridadActividad {

    private final DaoActividad daoActividad;

    public SeguridadActividad(DaoActividad daoActividad) {
        this.daoActividad = daoActividad;
    }

    public boolean puedeGestionar(Long id, Authentication authentication) {
        if (id == null || authentication == null || !authentication.isAuthenticated()) {
            return false;
        }

        if (authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"))) {
            return true;
        }

        DtoActividad actividad = daoActividad.consultarPorId(id);
        return actividad != null && authentication.getName().equals(actividad.getIdUsuario());
    }
}
