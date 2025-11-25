package com.portaexperiencia.portex.infraestructura.controlador.comando.valoracion;

import com.portaexperiencia.portex.modelo.entidad.Valoracion;
import org.springframework.stereotype.Component;

@Component
public class FabricaValoracion {

    public Valoracion crear(ComandoValoracion comandoValoracion){
        return new Valoracion(comandoValoracion.getId(),
                comandoValoracion.getComentario(),
                comandoValoracion.getValoracion());
    }

}
