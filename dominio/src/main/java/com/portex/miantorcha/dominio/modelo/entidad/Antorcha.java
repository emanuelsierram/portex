package com.portex.miantorcha.dominio.modelo.entidad;

import lombok.Getter;

import static com.portex.compartido.dominio.ValidadorArgumento.validarObligatorio;

@Getter
public class Antorcha {

    private static final int NIVEL_MINIMO = 1;
    private static final int NIVEL_MAXIMO = 10;

    private Long id;
    private Integer nivel;
    private String idUsuario;

    public Antorcha(Long id, Integer nivel, String idUsuario) {
        validarObligatorio(nivel, "El nivel de la antorcha es obligatorio");
        validarObligatorio(idUsuario, "El usuario de la antorcha es obligatorio");
        validarNivel(nivel);

        this.id = id;
        this.nivel = nivel;
        this.idUsuario = idUsuario;
    }

    private void validarNivel(Integer nivel) {
        if (nivel < NIVEL_MINIMO || nivel > NIVEL_MAXIMO) {
            throw new IllegalArgumentException("El nivel de la antorcha debe estar entre 1 y 10");
        }
    }
}
