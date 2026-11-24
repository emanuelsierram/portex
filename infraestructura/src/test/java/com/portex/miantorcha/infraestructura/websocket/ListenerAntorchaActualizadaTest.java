package com.portex.miantorcha.infraestructura.websocket;

import com.portex.miantorcha.dominio.modelo.dto.DtoAntorcha;
import com.portex.miantorcha.infraestructura.websocket.dto.NotificacionAntorcha;
import com.portex.miantorcha.infraestructura.websocket.evento.EventoAntorchaActualizada;
import com.portex.miantorcha.infraestructura.websocket.listener.ListenerAntorchaActualizada;
import org.junit.jupiter.api.Test;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ListenerAntorchaActualizadaTest {

    @Test
    void enviaLaActualizacionSoloAlUsuarioDelEvento() {
        SimpMessagingTemplate messagingTemplate = mock(SimpMessagingTemplate.class);
        ListenerAntorchaActualizada listener = new ListenerAntorchaActualizada(messagingTemplate);
        DtoAntorcha antorcha = new DtoAntorcha(1L, 2, "usuario-1");

        listener.manejar(new EventoAntorchaActualizada("usuario-1", antorcha));

        verify(messagingTemplate).convertAndSendToUser(
                eq("usuario-1"),
                eq("/queue/antorcha"),
                any(NotificacionAntorcha.class));
    }

    @Test
    void construyeEventoConFormatoEsperado() {
        DtoAntorcha antorcha = new DtoAntorcha(1L, 2, "usuario-1");

        NotificacionAntorcha notificacion = new NotificacionAntorcha(antorcha);

        assertEquals("TORCH_PROGRESS_UPDATED", notificacion.getEvent());
        assertEquals(antorcha, notificacion.getData());
    }
}
