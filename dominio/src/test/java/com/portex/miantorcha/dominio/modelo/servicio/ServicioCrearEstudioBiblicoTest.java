package com.portex.miantorcha.dominio.modelo.servicio;

import com.portex.compartido.dominio.excepcion.ExcepcionDuplicidad;
import com.portex.miantorcha.dominio.modelo.entidad.EstudioBiblico;
import com.portex.miantorcha.dominio.puerto.dao.DaoEstudioBiblico;
import com.portex.miantorcha.dominio.puerto.dao.DaoMiembro;
import com.portex.miantorcha.dominio.modelo.dto.DtoMiembro;
import com.portex.miantorcha.dominio.puerto.repositorio.RepositorioEstudioBiblico;
import com.portex.miantorcha.dominio.servicio.estudio.ServicioCrearEstudioBiblico;
import com.portex.miantorcha.testdatabuilder.EstudioBiblicoTestDataBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class ServicioCrearEstudioBiblicoTest {

    private DaoEstudioBiblico daoMock;
    private DaoMiembro daoMiembroMock;
    private RepositorioEstudioBiblico repositorioMock;
    private ServicioCrearEstudioBiblico servicio;

    @BeforeEach
    void setUp() {
        daoMock = Mockito.mock(DaoEstudioBiblico.class);
        daoMiembroMock = Mockito.mock(DaoMiembro.class);
        repositorioMock = Mockito.mock(RepositorioEstudioBiblico.class);
        servicio = new ServicioCrearEstudioBiblico(daoMock, daoMiembroMock, repositorioMock);
    }

    @Test
    void crearEstudioSiempreAsignaLeccionUno() {
        // Arrange
        EstudioBiblico estudio = new EstudioBiblicoTestDataBuilder().build();
        Mockito.when(daoMock.existeTelefono(estudio.getTelefonoPersona())).thenReturn(false);
        Mockito.when(daoMiembroMock.consultarPorUsuarioId("usuario-1")).thenReturn(
                new DtoMiembro(20L, "usuario-1", null, null, null, null, null, null, null, null, null)
        );

        // Act
        servicio.ejecutar(estudio, "usuario-1");

        // Assert
        assertEquals(0, estudio.getLeccion(), "Al crear un nuevo estudio bíblico, la lección por defecto debe ser 0");

        verify(repositorioMock, times(1)).crear(estudio);
    }

    @Test
    void crearEstudioFallaSiTelefonoYaExiste() {
        // Arrange
        EstudioBiblico estudio = new EstudioBiblicoTestDataBuilder().build();
        Mockito.when(daoMock.existeTelefono(estudio.getTelefonoPersona())).thenReturn(true);

        // Act + Assert
        ExcepcionDuplicidad excepcion = assertThrows(ExcepcionDuplicidad.class, () -> servicio.ejecutar(estudio, "usuario-1"));

        assertEquals("Ya existe un estudio bíblico registrado con este telefono", excepcion.getMessage());
        verify(repositorioMock, times(0)).crear(estudio);
    }
}