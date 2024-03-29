package com.portaexperiencia.portex.servicio;


import com.portaexperiencia.portex.modelo.entidad.Valoracion;
import com.portaexperiencia.portex.puerto.repositorio.RepositorioValoracion;
import com.portaexperiencia.portex.servicio.valoracion.ServicioCrearValoracion;
import com.portaexperiencia.portex.testdatabuilder.ValoracionTestDataBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;


public class ServicioCrearValoracionTest {

    private RepositorioValoracion repositorioValoracion;
    private ServicioCrearValoracion servicioCrearValoracion;


    @Test
    public void validarCreacionValoracionExitoso(){

        Valoracion valoracion = new ValoracionTestDataBuilder().build();
        repositorioValoracion= Mockito.mock(RepositorioValoracion.class);
        servicioCrearValoracion = new ServicioCrearValoracion(repositorioValoracion);

        final ArgumentCaptor<Valoracion> valoracionArgumentCaptor = ArgumentCaptor.forClass(Valoracion.class);
        Mockito.when(repositorioValoracion.crear(valoracion)).thenReturn(1L);
        servicioCrearValoracion.ejecutar(valoracion);
        Mockito.verify(repositorioValoracion).crear(valoracionArgumentCaptor.capture());

        Assertions.assertEquals(valoracionArgumentCaptor.getValue().getId(),valoracion.getId());
    }
}
