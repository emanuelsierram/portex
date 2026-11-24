package com.portex.miantorcha.infraestructura.websocket;

import com.portex.miantorcha.dominio.modelo.dto.DtoAntorcha;
import com.portex.miantorcha.dominio.puerto.notificador.PublicadorAntorcha;
import com.portex.miantorcha.infraestructura.websocket.evento.EventoAntorchaActualizada;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class PublicadorEventoAntorcha implements PublicadorAntorcha {

    private final ApplicationEventPublisher eventPublisher;

    public PublicadorEventoAntorcha(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void publicar(DtoAntorcha antorcha) {
        eventPublisher.publishEvent(new EventoAntorchaActualizada(
                antorcha.getIdUsuario(),
                antorcha));
    }
}
