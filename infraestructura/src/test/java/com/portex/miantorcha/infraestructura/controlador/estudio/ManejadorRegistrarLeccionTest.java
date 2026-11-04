package com.portex.miantorcha.infraestructura.controlador.estudio;

import com.portex.miantorcha.dominio.modelo.dto.DtoEstudioBiblico;
import com.portex.miantorcha.dominio.modelo.entidad.EstudioBiblico;
import com.portex.miantorcha.dominio.servicio.estudio.ServicioRegistrarLeccionEstudio;
import com.portex.miantorcha.dominio.puerto.dao.DaoEstudioBiblico;
import com.portex.miantorcha.infraestructura.controlador.comando.estudio.ComandoHistoricoLeccion;
import com.portex.miantorcha.infraestructura.controlador.comando.estudio.FabricaEstudioBiblico;
import com.portex.miantorcha.infraestructura.controlador.comando.estudio.FabricaHistoricoLeccion;
import com.portex.miantorcha.infraestructura.controlador.comando.estudio.ManejadorRegistrarLeccion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ManejadorRegistrarLeccionTest {

    private DaoEstudioBiblico daoEstudioBiblico;
    private ServicioRegistrarLeccionEstudio servicioRegistrarLeccionEstudio;
    private ManejadorRegistrarLeccion manejador;

    @BeforeEach
    void setUp() {
        daoEstudioBiblico = mock(DaoEstudioBiblico.class);
        servicioRegistrarLeccionEstudio = mock(ServicioRegistrarLeccionEstudio.class);
        manejador = new ManejadorRegistrarLeccion(
                servicioRegistrarLeccionEstudio,
                daoEstudioBiblico,
                new FabricaEstudioBiblico(),
                new FabricaHistoricoLeccion()
        );
    }

    @Test
    void conservarDatosDelEstudioAlRegistrarLeccion() {
        Long idEstudio = 45L;
        Long idUsuarioAsignado = 100L;
        LocalDate fechaInicializacion = LocalDate.of(2026, 11, 3);

        DtoEstudioBiblico dto = new DtoEstudioBiblico();
        dto.setId(idEstudio);
        dto.setNombrePersona("Juan Perez");
        dto.setDireccionPersona("Calle Falsa 123");
        dto.setEstado("en curso");
        dto.setLeccion(1);
        dto.setIdUsuarioAsignado(idUsuarioAsignado);
        dto.setFrecuencia("Semanal");
        dto.setFechaInicializacion(fechaInicializacion);

        when(daoEstudioBiblico.consultarPorId(idEstudio)).thenReturn(dto);

        ComandoHistoricoLeccion comando = new ComandoHistoricoLeccion();
        comando.setIdEstudioBiblico(idEstudio);
        comando.setContadorSemana(1);
        comando.setFechaEstudio(LocalDateTime.of(2026, 11, 3, 19, 0));
        comando.setIdActividad(99L);

        manejador.ejecutar(comando);

        verify(servicioRegistrarLeccionEstudio).ejecutar(any(), argThat(estudio ->
                idUsuarioAsignado.equals(estudio.getIdUsuarioAsignado())
                        && "Semanal".equals(estudio.getFrecuencia())
                        && fechaInicializacion.equals(estudio.getFechaInicializacion())
                        && Integer.valueOf(1).equals(estudio.getLeccion())
        ));
    }
}
