package com.portex.miantorcha.infraestructura.websocket.listener;

import com.portex.miantorcha.infraestructura.websocket.dto.NotificacionAntorcha;
import com.portex.miantorcha.infraestructura.websocket.evento.EventoAntorchaActualizada;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class ListenerAntorchaActualizada {

    private static final String DESTINO_ANTORCHA = "/queue/antorcha";

    private final SimpMessagingTemplate messagingTemplate;

    public ListenerAntorchaActualizada(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void manejar(EventoAntorchaActualizada evento) {
        messagingTemplate.convertAndSendToUser(
                evento.getIdUsuario(),
                DESTINO_ANTORCHA,
                new NotificacionAntorcha(evento.getAntorcha()));
    }
}
