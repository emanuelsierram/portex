package com.portaexperiencia.backend.dominio.modelo.entidad;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Imagen {

    private Long id;
    private String nombre;
    private String formato;
    private String ruta;
}
