package com.portex.miantorcha.infraestructura.websocket.evento;

import com.portex.miantorcha.dominio.modelo.dto.DtoAntorcha;
import lombok.Getter;

@Getter
public class EventoAntorchaActualizada {

    private final String idUsuario;
    private final DtoAntorcha antorcha;

    public EventoAntorchaActualizada(String idUsuario, DtoAntorcha antorcha) {
        this.idUsuario = idUsuario;
        this.antorcha = antorcha;
    }
}
