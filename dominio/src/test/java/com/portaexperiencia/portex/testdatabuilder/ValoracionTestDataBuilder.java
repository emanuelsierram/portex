package com.portaexperiencia.portex.testdatabuilder;

import com.portaexperiencia.portex.modelo.entidad.Valoracion;

public class ValoracionTestDataBuilder {

    private Long id;
    private String comentario;
    private Integer valoracion;


    public ValoracionTestDataBuilder(){
        id=1L;
        comentario="Comentario Test";
        valoracion=0;
    }

    public Valoracion build(){
        return new Valoracion(id, comentario,valoracion);
    }
}
