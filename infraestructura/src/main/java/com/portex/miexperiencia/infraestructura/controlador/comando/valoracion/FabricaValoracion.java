package com.portex.miexperiencia.infraestructura.controlador.comando.valoracion;

import com.portex.miexperiencia.dominio.modelo.entidad.Valoracion;
import org.springframework.stereotype.Component;

@Component
public class FabricaValoracion {

    public Valoracion crear(ComandoValoracion comandoValoracion){
        return new Valoracion(comandoValoracion.getId(),
                comandoValoracion.getComentario(),
                comandoValoracion.getValoracion());
    }

}
