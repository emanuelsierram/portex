package com.portex.miantorcha.infraestructura.websocket;

import com.portex.compartido.infraestructura.seguridad.FiltroIntrospeccionJwt;
import com.portex.compartido.infraestructura.seguridad.ResultadoIntrospeccion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.security.core.Authentication;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AutenticacionStompInterceptorTest {

    private FiltroIntrospeccionJwt filtroIntrospeccionJwt;
    private AutenticacionStompInterceptor interceptor;
    private MessageChannel channel;

    @BeforeEach
    void setUp() {
        filtroIntrospeccionJwt = mock(FiltroIntrospeccionJwt.class);
        interceptor = new AutenticacionStompInterceptor(filtroIntrospeccionJwt);
        channel = mock(MessageChannel.class);
    }

    @Test
    void autenticaConexionConTokenValido() {
        String usuario = "usuario-1";
        String token = "token-valido";
        when(filtroIntrospeccionJwt.introspectar("Bearer " + token))
                .thenReturn(new ResultadoIntrospeccion(true, usuario, List.of("USER")));

        Message<?> mensaje = mensajeConnect("Bearer " + token);
        Message<?> resultado = interceptor.preSend(mensaje, channel);

        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(resultado);
        assertNotNull(accessor.getUser());
        assertEquals(usuario, accessor.getUser().getName());
        assertEquals(
                List.of("ROLE_USER"),
                ((Authentication) accessor.getUser()).getAuthorities().stream()
                        .map(authority -> authority.getAuthority())
                        .toList()
        );
    }

    @Test
    void rechazaConexionSinToken() {
        Message<?> mensaje = mensajeConnect(null);

        assertThrows(IllegalArgumentException.class,
                () -> interceptor.preSend(mensaje, channel));
    }

    @Test
    void rechazaConexionConTokenInvalido() {
        when(filtroIntrospeccionJwt.introspectar("Bearer token-invalido"))
                .thenReturn(new ResultadoIntrospeccion(false, null, List.of()));
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
