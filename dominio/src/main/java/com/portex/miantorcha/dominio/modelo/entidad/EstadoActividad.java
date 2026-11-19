package com.portex.miantorcha.dominio.modelo.entidad;

public enum EstadoActividad {
    POR_HACER("POR_HACER"),
    EN_PROCESO("EN_PROCESO"),
    HECHO("HECHO");

    private final String valor;

    EstadoActividad(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    public static EstadoActividad desdeValor(String valor) {
        for (EstadoActividad estado : values()) {
            if (estado.valor.equalsIgnoreCase(valor)) {
                return estado;
            }
        }
        throw new IllegalArgumentException("Estado de actividad no válido: " + valor);
    }
}
