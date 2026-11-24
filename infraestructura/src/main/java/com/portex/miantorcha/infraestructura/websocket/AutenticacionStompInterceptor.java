package com.portex.miantorcha.infraestructura.websocket;

import com.portex.compartido.infraestructura.seguridad.FiltroIntrospeccionJwt;
import com.portex.compartido.infraestructura.seguridad.ResultadoIntrospeccion;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;

import java.util.List;
import java.util.Locale;

@Component
public class AutenticacionStompInterceptor implements ChannelInterceptor {

    private static final String AUTHORIZATION = "Authorization";
    private final FiltroIntrospeccionJwt filtroIntrospeccionJwt;

    public AutenticacionStompInterceptor(FiltroIntrospeccionJwt filtroIntrospeccionJwt) {
        this.filtroIntrospeccionJwt = filtroIntrospeccionJwt;
    }

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);

        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
            String authorization = accessor.getFirstNativeHeader(AUTHORIZATION);
            if (authorization == null || !authorization.startsWith("Bearer ")
                    || authorization.substring("Bearer ".length()).trim().isEmpty()) {
                throw new IllegalArgumentException("La conexión WebSocket requiere un token JWT válido");
            }

            ResultadoIntrospeccion resultado;
            try {
                resultado = filtroIntrospeccionJwt.introspectar(authorization);
            } catch (RestClientException exception) {
                throw new IllegalArgumentException(
                        "No fue posible validar el token con el servicio de usuarios",
                        exception
                );
            }

            if (resultado == null || !resultado.active() || resultado.usuario() == null) {
                throw new IllegalArgumentException("La conexión WebSocket requiere un token JWT válido");
            }

            List<SimpleGrantedAuthority> authorities = (resultado.roles() == null
                    ? List.<String>of()
                    : resultado.roles()).stream()
                    .filter(rol -> rol != null && !rol.isBlank())
                    .map(rol -> rol.toUpperCase(Locale.ROOT).replace("ROLE_", ""))
                    .map(rol -> new SimpleGrantedAuthority("ROLE_" + rol))
                    .distinct()
                    .toList();
            accessor.setUser(new UsernamePasswordAuthenticationToken(
                    resultado.usuario(),
                    null,
                    authorities
            ));
            return MessageBuilder.createMessage(message.getPayload(), accessor.getMessageHeaders());
        }

        return message;
    }

}
