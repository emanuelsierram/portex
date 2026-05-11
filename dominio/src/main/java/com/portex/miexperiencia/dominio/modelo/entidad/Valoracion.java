package com.portex.miexperiencia.dominio.modelo.entidad;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Valoracion {
    private Long id;
    private String comentario;
    private Integer valoracion;

    public Valoracion(String comentario, Integer valoracion) {
        this.comentario = comentario;
        this.valoracion = valoracion;
    }

    public Valoracion(Long id, String comentario, Integer valoracion) {
        this.id = id;
        this.comentario = comentario;
        this.valoracion = valoracion;
    }
}
