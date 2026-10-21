package com.portex.miantorcha.dominio.modelo.entidad;

public enum FrecuenciaEstudioBiblico {
    DIARIO("Diario"),
    SEMANAL("Semanal"),
    MENSUAL("Mensual");

    private final String valor;

    FrecuenciaEstudioBiblico(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    public static FrecuenciaEstudioBiblico desdeValor(String valor) {
        for (FrecuenciaEstudioBiblico frecuencia : values()) {
            if (frecuencia.getValor().equalsIgnoreCase(valor.trim())) {
                return frecuencia;
            }
        }
        throw new IllegalArgumentException("Frecuencia de estudio bíblico no válida: " + valor);
    }
}
