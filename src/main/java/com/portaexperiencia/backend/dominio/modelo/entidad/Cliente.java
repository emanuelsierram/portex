package com.portaexperiencia.backend.dominio.modelo.entidad;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cliente {

    private Long id;
    private String nombre;
    private String apellido;
    private String telefono;
    private String direccion;
    private String email;
    private String ciudad;
    private String barrio;

    public Cliente(Long id, String nombre, String apellido, String telefono, String direccion, String email, String ciudad, String barrio) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.direccion = direccion;
        this.email = email;
        this.ciudad = ciudad;
        this.barrio = barrio;
    }




}
