package com.portex.miantorcha.dominio.modelo.entidad;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ActividadTest {

    @Test
    void permiteActividadEnProceso() {
        assertDoesNotThrow(() -> new Actividad(
                null, "usuario", 1L, "OTRO", "Actividad propia",
                EstadoActividad.EN_PROCESO, LocalDate.now(), false, null, 1));
    }

    @Test
    void exigeObservacionesCuandoInformaAObra() {
        assertThrows(IllegalArgumentException.class, () -> new Actividad(
                null, "usuario", 1L, "OTRO", "Actividad propia",
                EstadoActividad.POR_HACER, LocalDate.now(), true, null, 1));
    }

    @Test
    void exigeDescripcionParaOtro() {
        assertThrows(IllegalArgumentException.class, () -> new Actividad(
                null, "usuario", 1L, "OTRO", null,
                EstadoActividad.POR_HACER, LocalDate.now(), false, null, 1));
    }
}
