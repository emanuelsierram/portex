package com.portex.miantorcha.dominio.modelo.entidad;

import com.portex.compartido.dominio.excepcion.ExcepcionValorObligatorio;
import com.portex.miantorcha.testdatabuilder.EstudioBiblicoTestDataBuilder;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EstudioBiblicoTest {

    @Test
    void crearEstudioBiblicoExitoso() {
        // Arrange & Act
        EstudioBiblico estudio = new EstudioBiblicoTestDataBuilder().build();

        // Assert
        assertNotNull(estudio);
        assertEquals("Juan Perez", estudio.getNombrePersona());
        assertEquals("por dar", estudio.getEstado());
    }

    @Test
    void crearEstudioBiblicoConFrecuenciaYFechas() {
        LocalDate fechaInicializacion = LocalDate.of(2026, 11, 3);

        EstudioBiblico estudio = new EstudioBiblicoTestDataBuilder()
                .conFrecuencia("Semanal")
                .conFechaInicializacion(fechaInicializacion)
                .build();

        assertEquals("Semanal", estudio.getFrecuencia());
        assertEquals(fechaInicializacion, estudio.getFechaInicializacion());
        assertNull(estudio.getFechaRegistro());
    }

    @Test
    void normalizarFrecuenciaIgnorandoMayusculasYEspacios() {
        EstudioBiblico estudio = new EstudioBiblicoTestDataBuilder()
                .conFrecuencia("  diario ")
                .build();

        assertEquals("Diario", estudio.getFrecuencia());
    }

    @Test
    void validarFrecuenciaNoPermitida() {
        EstudioBiblicoTestDataBuilder builder = new EstudioBiblicoTestDataBuilder()
                .conFrecuencia("Quincenal");

        IllegalArgumentException excepcion = assertThrows(IllegalArgumentException.class, builder::build);

        assertEquals("Frecuencia de estudio bíblico no válida: Quincenal", excepcion.getMessage());
    }

    @Test
    void validarNombreObligatorio() {
        // Arrange
        EstudioBiblicoTestDataBuilder builder = new EstudioBiblicoTestDataBuilder().conNombrePersona(null);

        // Act & Assert
        ExcepcionValorObligatorio excepcion = assertThrows(ExcepcionValorObligatorio.class, builder::build);
        assertEquals("El nombre de la persona es obligatorio", excepcion.getMessage());
    }

    @Test
    void validarDireccionObligatoria() {
        // Arrange
        EstudioBiblicoTestDataBuilder builder = new EstudioBiblicoTestDataBuilder().conDireccionPersona(null);

        // Act & Assert
        ExcepcionValorObligatorio excepcion = assertThrows(ExcepcionValorObligatorio.class, builder::build);
        assertEquals("La dirección de la persona es obligatoria", excepcion.getMessage());
    }

    @Test
    void asignarEstadoPorDefectoSiVieneVacio() {
        // Arrange & Act
        EstudioBiblico estudio = new EstudioBiblicoTestDataBuilder().conEstado("   ").build();

        // Assert
        assertEquals("por dar", estudio.getEstado(), "Debería asignar 'por dar' por defecto");
    }

    @Test
    void asignarLeccionCalculadaExitosa() {
        // Arrange
        EstudioBiblico estudio = new EstudioBiblicoTestDataBuilder().build();

        // Act
        estudio.asignarLeccionCalculada(5);

        // Assert
        assertEquals(5, estudio.getLeccion());
    }

    @Test
    void fallaAlAsignarLeccionInvalida() {
        // Arrange
        EstudioBiblico estudio = new EstudioBiblicoTestDataBuilder().build();

        // Act & Assert
        IllegalArgumentException excepcionMenor = assertThrows(IllegalArgumentException.class, () -> estudio.asignarLeccionCalculada(-1));
        assertEquals("La lección debe estar entre 0 y 20", excepcionMenor.getMessage());

        IllegalArgumentException excepcionMayor = assertThrows(IllegalArgumentException.class, () -> estudio.asignarLeccionCalculada(21));
        assertEquals("La lección debe estar entre 0 y 20", excepcionMayor.getMessage());
    }
}