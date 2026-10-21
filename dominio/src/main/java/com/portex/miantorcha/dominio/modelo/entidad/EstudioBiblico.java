package com.portex.miantorcha.dominio.modelo.entidad;

import com.portex.compartido.dominio.ValidadorArgumento;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class EstudioBiblico {

    private Long id;
    private String nombrePersona;
    private String telefonoPersona;
    private String direccionPersona;
    private String estado;
    private Integer leccion; // Campo calculado (1-20)
    private Long idUsuarioAsignado;
    private String personaQueReporta;
    private Long idGrupo;
    private String frecuencia;
    private LocalDate fechaInicializacion;
    private LocalDateTime fechaRegistro;

    public EstudioBiblico(Long id, String nombrePersona, String telefonoPersona,
                          String direccionPersona, String estado,
                          Long idUsuarioAsignado, String personaQueReporta,
                          Long idGrupo) {
        this(id, nombrePersona, telefonoPersona, direccionPersona, estado,
                idUsuarioAsignado, personaQueReporta, idGrupo, null, null, null);
    }

    public EstudioBiblico(Long id, String nombrePersona, String telefonoPersona,
                          String direccionPersona, String estado,
                          Long idUsuarioAsignado, String personaQueReporta,
                          Long idGrupo, String frecuencia, LocalDate fechaInicializacion,
                          LocalDateTime fechaRegistro) {

        // Reglas de negocio: Validaciones obligatorias
        ValidadorArgumento.validarObligatorio(nombrePersona, "El nombre de la persona es obligatorio");
        ValidadorArgumento.validarObligatorio(direccionPersona, "La dirección de la persona es obligatoria");

        this.id = id;
        this.nombrePersona = nombrePersona;
        this.telefonoPersona = telefonoPersona;
        this.direccionPersona = direccionPersona;
        this.estado = EstadoEstudioBiblico.desdeValor(estado).getValor();
        this.idUsuarioAsignado = idUsuarioAsignado;
        this.personaQueReporta = personaQueReporta;
        this.idGrupo = idGrupo;
        this.frecuencia = frecuencia == null || frecuencia.trim().isEmpty()
                ? null : FrecuenciaEstudioBiblico.desdeValor(frecuencia).getValor();
        this.fechaInicializacion = fechaInicializacion;
        this.fechaRegistro = fechaRegistro;
    }

    // Método para inyectar el valor calculado de la lección tras aplicar la lógica
    public void asignarLeccionCalculada(Integer leccionCalculada) {
        ValidadorArgumento.validarObligatorio(leccionCalculada, "La lección calculada no puede ser nula");
        if (leccionCalculada < 0 || leccionCalculada > 20) {
            throw new IllegalArgumentException("La lección debe estar entre 0 y 20");
        }
        this.leccion = leccionCalculada;
    }

}