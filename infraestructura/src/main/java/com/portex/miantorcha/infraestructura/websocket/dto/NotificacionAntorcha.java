package com.portex.miantorcha.infraestructura.websocket.dto;

import com.portex.miantorcha.dominio.modelo.dto.DtoAntorcha;
import lombok.Getter;

@Getter
public class NotificacionAntorcha {

    private static final String EVENTO_ANTORCHA_ACTUALIZADA = "TORCH_PROGRESS_UPDATED";

    private final String event;
    private final DtoAntorcha data;

    public NotificacionAntorcha(DtoAntorcha data) {
        this.event = EVENTO_ANTORCHA_ACTUALIZADA;
        this.data = data;
    }
}
