package com.portex.miantorcha.infraestructura.websocket;

import com.portex.compartido.infraestructura.seguridad.jwt.JwtTokenManager;
import com.portex.compartido.infraestructura.seguridad.servicio.ServicioSeguridadUsuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AutenticacionStompInterceptorTest {

    private JwtTokenManager jwtTokenManager;
    private ServicioSeguridadUsuario servicioSeguridadUsuario;
    private AutenticacionStompInterceptor interceptor;
    private MessageChannel channel;

    @BeforeEach
    void setUp() {
        jwtTokenManager = new JwtTokenManager();
        servicioSeguridadUsuario = mock(ServicioSeguridadUsuario.class);
        interceptor = new AutenticacionStompInterceptor(jwtTokenManager, servicioSeguridadUsuario);
        channel = mock(MessageChannel.class);
    }

    @Test
    void autenticaConexionConTokenValido() {
        String usuario = "usuario-1";
        String token = jwtTokenManager.crear(usuario);
        UserDetails userDetails = User.withUsername(usuario)
                .password("password")
                .roles("USER")
                .build();
        when(servicioSeguridadUsuario.loadUserByUsername(usuario)).thenReturn(userDetails);

        Message<?> mensaje = mensajeConnect("Bearer " + token);
        Message<?> resultado = interceptor.preSend(mensaje, channel);

        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(resultado);
        assertNotNull(accessor.getUser());
        assertEquals(usuario, accessor.getUser().getName());
    }

    @Test
    void rechazaConexionSinToken() {
        Message<?> mensaje = mensajeConnect(null);

        assertThrows(IllegalArgumentException.class,
                () -> interceptor.preSend(mensaje, channel));
    }

    @Test
    void rechazaConexionConTokenInvalido() {
        Message<?> mensaje = mensajeConnect("Bearer token-invalido");

        assertThrows(IllegalArgumentException.class,
                () -> interceptor.preSend(mensaje, channel));
    }

    private Message<?> mensajeConnect(String authorization) {
        StompHeaderAccessor accessor = StompHeaderAccessor.create(StompCommand.CONNECT);
        if (authorization != null) {
            accessor.addNativeHeader("Authorization", authorization);
        }
        return MessageBuilder.createMessage(new byte[0], accessor.getMessageHeaders());
    }
}
