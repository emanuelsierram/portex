package com.portex.miantorcha.infraestructura.controlador.miembro;

import com.portex.compartido.aplicacion.ComandoRespuesta;
import com.portex.miantorcha.infraestructura.adaptador.cliente.usuario.ClienteUsuario;
import com.portex.miantorcha.infraestructura.adaptador.cliente.usuario.ComandoCrearUsuario;
import com.portex.miantorcha.dominio.modelo.entidad.Miembro;
import com.portex.miantorcha.dominio.servicio.miembro.ServicioCrearMiembro;
import com.portex.miantorcha.infraestructura.controlador.comando.miembro.ComandoMiembro;
import com.portex.miantorcha.infraestructura.controlador.comando.miembro.FabricaMiembro;
import com.portex.miantorcha.infraestructura.controlador.comando.miembro.ManejadorCrearMiembro;
import com.portex.miantorcha.testdatabuilder.ComandoMiembroTestDataBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.Mockito;
public class ManejadorCrearMiembroTest {

    private ServicioCrearMiembro servicioCrearMiembro;
    private ClienteUsuario clienteUsuario;
    private ManejadorCrearMiembro manejadorCrearMiembro;

    private static final String TELEFONO = "3109876543";
    private static final String PASS = "secreta123";

    @BeforeEach
    public void setup() {
        servicioCrearMiembro = Mockito.mock(ServicioCrearMiembro.class);
        clienteUsuario = Mockito.mock(ClienteUsuario.class);

        FabricaMiembro fabricaMiembro = new FabricaMiembro();

        manejadorCrearMiembro = new ManejadorCrearMiembro(
                servicioCrearMiembro,
                fabricaMiembro,
                clienteUsuario
                );
    }

    @Test
    public void validarOrquestacionAlCrearMiembro() {
        ComandoMiembro comando = new ComandoMiembroTestDataBuilder().build();
        String telefonoLimpio = TELEFONO;

        Mockito.when(servicioCrearMiembro.ejecutar(Mockito.any(Miembro.class))).thenReturn(10L);

        // Act
        ComandoRespuesta<Long> respuesta = manejadorCrearMiembro.ejecutar(comando);

        // Assert
        ArgumentCaptor<ComandoCrearUsuario> captorUsuario = ArgumentCaptor.forClass(ComandoCrearUsuario.class);
        Mockito.verify(clienteUsuario).crear(captorUsuario.capture());
        Assertions.assertEquals(telefonoLimpio, captorUsuario.getValue().usuario());
        Assertions.assertEquals(PASS, captorUsuario.getValue().contrasena());

        Mockito.verify(clienteUsuario).asignarRol(telefonoLimpio, "USER");

        Mockito.verify(servicioCrearMiembro).ejecutar(Mockito.any(Miembro.class));
        Mockito.verify(clienteUsuario, Mockito.never()).eliminar(Mockito.anyString());
        Assertions.assertEquals(10L, respuesta.getValor());
    }

    @Test
    public void eliminarUsuarioSiFallaLaCreacionDelMiembro() {
        ComandoMiembro comando = new ComandoMiembroTestDataBuilder().build();
        RuntimeException errorCreacion = new RuntimeException("El correo ya existe");
        Mockito.when(servicioCrearMiembro.ejecutar(Mockito.any(Miembro.class)))
                .thenThrow(errorCreacion);

        RuntimeException errorLanzado = Assertions.assertThrows(
                RuntimeException.class,
                () -> manejadorCrearMiembro.ejecutar(comando)
        );

        Assertions.assertSame(errorCreacion, errorLanzado);
        InOrder orden = Mockito.inOrder(clienteUsuario);
        orden.verify(clienteUsuario).eliminarRol(TELEFONO, "USER");
        orden.verify(clienteUsuario).eliminar(TELEFONO);
    }

    @Test
    public void conservaErrorDeCreacionSiFallaLaEliminacionDelRol() {
        ComandoMiembro comando = new ComandoMiembroTestDataBuilder().build();
        RuntimeException errorCreacion = new RuntimeException("El correo ya existe");
        RuntimeException errorEliminacion = new RuntimeException("No se pudo eliminar el rol");
        Mockito.when(servicioCrearMiembro.ejecutar(Mockito.any(Miembro.class)))
                .thenThrow(errorCreacion);
        Mockito.doThrow(errorEliminacion).when(clienteUsuario).eliminarRol(TELEFONO, "USER");

        RuntimeException errorLanzado = Assertions.assertThrows(
                RuntimeException.class,
                () -> manejadorCrearMiembro.ejecutar(comando)
        );

        Assertions.assertSame(errorCreacion, errorLanzado);
        Assertions.assertArrayEquals(
                new Throwable[]{errorEliminacion},
                errorLanzado.getSuppressed()
        );
        Mockito.verify(clienteUsuario, Mockito.never()).eliminar(Mockito.anyString());
    }
}
