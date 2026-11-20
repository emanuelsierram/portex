package com.portex.miantorcha.infraestructura.configuracion;

import com.portex.miantorcha.dominio.modelo.dto.DtoActividad;
import com.portex.miantorcha.dominio.puerto.dao.DaoActividad;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SeguridadActividadTest {

    @Test
    void permiteAlUsuarioGestionarSuActividad() {
        DaoActividad daoActividad = mock(DaoActividad.class);
        when(daoActividad.consultarPorId(10L)).thenReturn(
                new DtoActividad(10L, "usuario-1", 1L, "OTRO", "Actividad",
                        null, "POR_HACER", null, false, null));
        SeguridadActividad seguridad = new SeguridadActividad(daoActividad);
        Authentication autenticacion = authentication("usuario-1");

        assertTrue(seguridad.puedeGestionar(10L, autenticacion));
    }

    @Test
    void rechazaActividadAjenaAunqueElUsuarioSeaAdmin() {
        DaoActividad daoActividad = mock(DaoActividad.class);
        when(daoActividad.consultarPorId(10L)).thenReturn(
                new DtoActividad(10L, "usuario-1", 1L, "OTRO", "Actividad",
                        null, "POR_HACER", null, false, null));
        SeguridadActividad seguridad = new SeguridadActividad(daoActividad);
        Authentication autenticacion = new UsernamePasswordAuthenticationToken(
                "usuario-2", null, List.of(new SimpleGrantedAuthority("ROLE_ADMIN")));

        assertFalse(seguridad.puedeGestionar(10L, autenticacion));
    }

    @Test
    void rechazaAdministradorAunqueLaActividadTengaSuUsuario() {
        DaoActividad daoActividad = mock(DaoActividad.class);
        when(daoActividad.consultarPorId(10L)).thenReturn(
                new DtoActividad(10L, "usuario-admin", 1L, "OTRO", "Actividad",
                        null, "POR_HACER", null, false, null));
        SeguridadActividad seguridad = new SeguridadActividad(daoActividad);
        Authentication autenticacion = new UsernamePasswordAuthenticationToken(
                "usuario-admin", null, List.of(new SimpleGrantedAuthority("ROLE_ADMIN")));

        assertFalse(seguridad.puedeGestionar(10L, autenticacion));
    }

    private Authentication authentication(String usuario) {
        return new UsernamePasswordAuthenticationToken(
                usuario, null, List.of(new SimpleGrantedAuthority("ROLE_USER")));
    }
}
