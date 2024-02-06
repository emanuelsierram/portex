package com.portaexperiencia.portex.modelo.entidad;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Categoria {

    private Long id;
    private String nombre;
    private String descripcion;
    private Long idIcono;

    public Categoria(Long id, String nombre, String descripcion, Long idIcono) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.idIcono = idIcono;
    }
}
