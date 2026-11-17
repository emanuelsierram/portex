package com.portex.miantorcha.dominio.puerto.notificador;

import com.portex.miantorcha.dominio.modelo.dto.DtoAntorcha;

public interface PublicadorAntorcha {

    void publicar(DtoAntorcha antorcha);
}
