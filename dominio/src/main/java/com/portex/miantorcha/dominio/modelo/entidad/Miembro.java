package com.portex.miantorcha.dominio.modelo.entidad;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import static com.portex.compartido.dominio.ValidadorArgumento.validarObligatorio;
import static com.portex.compartido.dominio.ValidadorArgumento.validarRegex;

@Getter
@Setter
public class Miembro {

    private static final String SE_DEBE_INGRESAR_NOMBRE = "Se debe ingresar el nombre";
    private static final String SE_DEBE_INGRESAR_APELLIDO = "Se debe ingresar el apellido";
    private static final String SE_DEBE_INGRESAR_EMAIL = "Se debe ingresar el email";
    private static final String SE_DEBE_INGRESAR_EMAIL_VALIDO = "Se debe ingresar un email valido";
    private static final String REGEX_EMAIL_COMPLETO = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    private Long id;
    private String usuarioId;
    private String nombres;
    private String apellidos;
    private String email;
    private String telefono;
    private String perfil;
    private Long idGrupoPequeno;
    private Long idAnciano;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;

    public Miembro(Long id, String usuarioId, String nombres, String apellidos, String email, String telefono, String perfil, Long idGrupoPequeno, Long idAnciano) {

        validarObligatorio(nombres, SE_DEBE_INGRESAR_NOMBRE);
        validarObligatorio(apellidos, SE_DEBE_INGRESAR_APELLIDO);
        validarObligatorio(email, SE_DEBE_INGRESAR_EMAIL);
        validarRegex(email,REGEX_EMAIL_COMPLETO, SE_DEBE_INGRESAR_EMAIL_VALIDO);

        this.id = id;
        this.usuarioId = usuarioId;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
        this.telefono = telefono;
        this.perfil = perfil;
        this.idGrupoPequeno = idGrupoPequeno;
        this.idAnciano = idAnciano;
        this.fechaCreacion = LocalDateTime.now();
        this.fechaActualizacion = LocalDateTime.now();
    }
}
