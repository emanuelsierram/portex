package com.portex.miexperiencia.dominio.modelo.entidad;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Servicio {

    private Long id;
    private String nombre;
    private String descripcion;
    private Long idCategoria;
    private Long idTrabajador;

    public Servicio(String nombre, String descripcion, Long idCategoria, Long idTrabajador) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.idCategoria = idCategoria;
        this.idTrabajador = idTrabajador;
    }



}
