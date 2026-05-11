package com.portex.miexperiencia.dominio.modelo.entidad;

import lombok.Getter;
import lombok.Setter;

import static com.portex.compartido.dominio.ValidadorArgumento.validarObligatorio;

@Getter
@Setter
public class Trabajador {


    private static final String SE_DEBE_INGRESAR_NOMBRE="Se debe ingresar el nombre del trabajador";
    private static final String SE_DEBE_INGRESAR_APELLIDOS="Se debe ingresar los apellidos del trabajador";
    private static final String SE_DEBE_INGRESAR_TELEFONO="Se debe ingresar el telefono del trabajador";
    private static final String SE_DEBE_INGRESAR_EMAIL="Se debe ingresar el email del trabajador";
    private static final String SE_DEBE_INGRESAR_CEDULA="Se debe ingresar la cedula del trabajador";
    private static final String SE_DEBE_INGRESAR_PROFESION="Se debe ingresar la profesión del trabajador";


    private Long id;
    private String nombres;
    private String apellidos;
    private String telefono;
    private String email;
    private String cedula;
    private String profesion;
    private String estado;
    private String estado_servicio;
    private Long idCartera;

    public Trabajador(Long id, String nombres, String apellidos, String telefono, String email, String cedula, String profesion, String estado, String estado_servicio, Long idCartera) {

        validarObligatorio(nombres,SE_DEBE_INGRESAR_NOMBRE);
        validarObligatorio(apellidos,SE_DEBE_INGRESAR_APELLIDOS);
        validarObligatorio(telefono,SE_DEBE_INGRESAR_TELEFONO);
        validarObligatorio(email,SE_DEBE_INGRESAR_EMAIL);
        validarObligatorio(cedula,SE_DEBE_INGRESAR_CEDULA);
        validarObligatorio(profesion,SE_DEBE_INGRESAR_PROFESION);
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.email = email;
        this.cedula = cedula;
        this.profesion = profesion;
        this.estado = estado;
        this.estado_servicio = estado_servicio;
        this.idCartera = idCartera;
    }
}



