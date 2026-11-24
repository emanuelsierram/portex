package com.portex.miantorcha.infraestructura.websocket;

import com.portex.compartido.infraestructura.seguridad.jwt.JwtTokenManager;
import com.portex.compartido.infraestructura.seguridad.servicio.ServicioSeguridadUsuario;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class AutenticacionStompInterceptor implements ChannelInterceptor {

    private static final String AUTHORIZATION = "Authorization";
    private final JwtTokenManager jwtTokenManager;
    private final ServicioSeguridadUsuario servicioSeguridadUsuario;

    public AutenticacionStompInterceptor(
            JwtTokenManager jwtTokenManager,
            ServicioSeguridadUsuario servicioSeguridadUsuario) {
        this.jwtTokenManager = jwtTokenManager;
        this.servicioSeguridadUsuario = servicioSeguridadUsuario;
    }

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);

        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
            String authorization = accessor.getFirstNativeHeader(AUTHORIZATION);
            String token = extraerToken(authorization);
            if (token == null || !jwtTokenManager.esValido(token)) {
                throw new IllegalArgumentException("La conexión WebSocket requiere un token JWT válido");
            }

            String usuario = jwtTokenManager.obtenerUsuario(token);
            UserDetails userDetails = servicioSeguridadUsuario.loadUserByUsername(usuario);
            accessor.setUser(new UsernamePasswordAuthenticationToken(
                    userDetails.getUsername(),
                    userDetails.getPassword(),
                    userDetails.getAuthorities()));
            return MessageBuilder.createMessage(message.getPayload(), accessor.getMessageHeaders());
        }

        return message;
    }

    private String extraerToken(String authorization) {
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            return null;
        }
        String token = authorization.substring("Bearer ".length()).trim();
        return token.isEmpty() ? null : token;
    }
}
