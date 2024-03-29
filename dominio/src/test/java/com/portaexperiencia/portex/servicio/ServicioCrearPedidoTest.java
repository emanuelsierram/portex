package com.portaexperiencia.portex.servicio;

import com.portaexperiencia.dominio.excepcion.ExcepcionDuplicidad;
import com.portaexperiencia.dominio.excepcion.ExcepcionValorInvalido;
import com.portaexperiencia.portex.BasePrueba;
import com.portaexperiencia.portex.modelo.dto.DtoPedido;
import com.portaexperiencia.portex.modelo.entidad.Pedido;
import com.portaexperiencia.portex.modelo.entidad.Valoracion;
import com.portaexperiencia.portex.puerto.repositorio.RepositorioPedido;
import com.portaexperiencia.portex.servicio.pedido.ServicioCrearPedido;
import com.portaexperiencia.portex.servicio.valoracion.ServicioCrearValoracion;
import com.portaexperiencia.portex.testdatabuilder.PedidoTestDataBuilder;
import com.portaexperiencia.portex.testdatabuilder.ValoracionTestDataBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ServicioCrearPedidoTest {

    private RepositorioPedido repositorioPedido;
    private ServicioCrearPedido servicioCrearPedido;
    private ServicioCrearValoracion servicioCrearValoracion;

    private Pedido pedido;
    private Valoracion valoracion;

    @BeforeEach
    public void setup() throws Exception{
        repositorioPedido= Mockito.mock(RepositorioPedido.class);
        servicioCrearValoracion= Mockito.mock(ServicioCrearValoracion.class);
        servicioCrearPedido = new ServicioCrearPedido(repositorioPedido, servicioCrearValoracion);

        pedido = new PedidoTestDataBuilder().build();
        valoracion = new ValoracionTestDataBuilder().build();
    }

    @Test
    public void validarPedidoConExistenciaPreviaConFechasIguales(){

        Mockito.when(servicioCrearValoracion.ejecutar(valoracion)).thenReturn(1L);

        ArrayList<DtoPedido> listaExistente = new ArrayList<DtoPedido>(){{
            add(new DtoPedido(1L,
                    "Nombre pedido",
                    "Descripcion pedido",
                    "estado",
                    LocalDateTime.now(),
                    LocalDateTime.of(2024,4,1,20,10,20),
                    LocalDateTime.of(2024,4,17,20,10,20),
                    100.0,
                    1L,
                    1L,
                    1L
            ));
        }};
        Mockito.when(repositorioPedido.listarPedidosPorIdTrabajador(pedido.getIdTrabajador())).thenReturn(listaExistente);
        BasePrueba.assertThrows(()->servicioCrearPedido.ejecutar(pedido), ExcepcionDuplicidad.class, "Ya existe pedido en la fecha establecida");

    }


    @Test
    public void validarPedidoConExistenciaPreviaConFechasFueraDelRango(){

        Mockito.when(servicioCrearValoracion.ejecutar(valoracion)).thenReturn(1L);

        ArrayList<DtoPedido> listaExistente = new ArrayList<DtoPedido>(){{
            add(new DtoPedido(1L,
                    "Nombre pedido",
                    "Descripcion pedido",
                    "estado",
                    LocalDateTime.now(),
                    LocalDateTime.of(2024,3,31,11,0),
                    LocalDateTime.of(2024,4,18,11,0),
                    100.0,
                    1L,
                    1L,
                    1L
                    ));
        }};
        Mockito.when(repositorioPedido.listarPedidosPorIdTrabajador(pedido.getIdTrabajador())).thenReturn(listaExistente);
        BasePrueba.assertThrows(()->servicioCrearPedido.ejecutar(pedido), ExcepcionDuplicidad.class, "Ya existe pedido en la fecha establecida");

    }

    @Test
    public void validarPedidoConExistenciaPreviaConFechasDentroDelRango(){

        Mockito.when(servicioCrearValoracion.ejecutar(valoracion)).thenReturn(1L);

        ArrayList<DtoPedido> listaExistente = new ArrayList<DtoPedido>(){{
            add(new DtoPedido(1L,
                    "Nombre pedido",
                    "Descripcion pedido",
                    "estado",
                    LocalDateTime.now(),
                    LocalDateTime.of(2024,4,2,11,0),
                    LocalDateTime.of(2024,4,16,11,0),
                    100.0,
                    1L,
                    1L,
                    1L
            ));
        }};
        Mockito.when(repositorioPedido.listarPedidosPorIdTrabajador(pedido.getIdTrabajador())).thenReturn(listaExistente);
        BasePrueba.assertThrows(()->servicioCrearPedido.ejecutar(pedido), ExcepcionDuplicidad.class, "Ya existe pedido en la fecha establecida");

    }


    @Test
    public void validarPedidoConExistenciaPreviaConFechaInicioDentroDelRangoYFechaFinalFueraDelRango(){

        Mockito.when(servicioCrearValoracion.ejecutar(valoracion)).thenReturn(1L);

        ArrayList<DtoPedido> listaExistente = new ArrayList<DtoPedido>(){{
            add(new DtoPedido(1L,
                    "Nombre pedido",
                    "Descripcion pedido",
                    "estado",
                    LocalDateTime.now(),
                    LocalDateTime.of(2024,4,5,11,0),
                    LocalDateTime.of(2024,4,19,11,0),
                    100.0,
                    1L,
                    1L,
                    1L
            ));
        }};
        Mockito.when(repositorioPedido.listarPedidosPorIdTrabajador(pedido.getIdTrabajador())).thenReturn(listaExistente);
        BasePrueba.assertThrows(()->servicioCrearPedido.ejecutar(pedido), ExcepcionDuplicidad.class, "Ya existe pedido en la fecha establecida");

    }

    @Test
    public void validarPedidoConExistenciaPreviaConFechaFinalDentroDelRangoYFechaInicioFueraDelRango(){

        Mockito.when(servicioCrearValoracion.ejecutar(valoracion)).thenReturn(1L);

        ArrayList<DtoPedido> listaExistente = new ArrayList<DtoPedido>(){{
            add(new DtoPedido(1L,
                    "Nombre pedido",
                    "Descripcion pedido",
                    "estado",
                    LocalDateTime.now(),
                    LocalDateTime.of(2024,3,31,11,0),
                    LocalDateTime.of(2024,4,11,11,0),
                    100.0,
                    1L,
                    1L,
                    1L
            ));
        }};
        Mockito.when(repositorioPedido.listarPedidosPorIdTrabajador(pedido.getIdTrabajador())).thenReturn(listaExistente);
        BasePrueba.assertThrows(()->servicioCrearPedido.ejecutar(pedido), ExcepcionDuplicidad.class, "Ya existe pedido en la fecha establecida");

    }

    @Test
    public void validarIngresoDeFechaInicioNoMenorAFechaActual(){
        PedidoTestDataBuilder pedidoTestDataBuilder = new PedidoTestDataBuilder().conFechaInicio(
                LocalDateTime.of(2023,4,20,13,30)
        );
        BasePrueba.assertThrows(() -> pedidoTestDataBuilder.build(), ExcepcionValorInvalido.class, "La fecha inicial no debe ser menor a la fecha actual");
    }

    @Test
    public void validarIngresoDeFechaInciioNoMayorAFechaFinal(){
        PedidoTestDataBuilder pedidoTestDataBuilder = new PedidoTestDataBuilder().conFechas(
                LocalDateTime.of(2024,4,20,13,30),
                LocalDateTime.of(2024,4,20,11,30)
        );
        BasePrueba.assertThrows(() -> pedidoTestDataBuilder.build(), ExcepcionValorInvalido.class, "La fecha inicial no debe ser mayor a la fecha final");
    }

    @Test
    public void validarNoAngendarDiaSabadoFechaInicial(){
        PedidoTestDataBuilder pedidoTestDataBuilder = new PedidoTestDataBuilder().conFechas(
                LocalDateTime.of(2024,3,30,13,30),
                LocalDateTime.of(2024,4,21,11,30)
        );
        BasePrueba.assertThrows(()-> pedidoTestDataBuilder.build(), ExcepcionValorInvalido.class,"No se puede agendar en día sabado");

    }

    @Test
    public void validarNoAngendarDiaSabadoFechaFinal(){
        PedidoTestDataBuilder pedidoTestDataBuilder = new PedidoTestDataBuilder().conFechas(
                LocalDateTime.of(2024,3,31,13,30),
                LocalDateTime.of(2024,4,20,11,30)
        );
        BasePrueba.assertThrows(()-> pedidoTestDataBuilder.build(), ExcepcionValorInvalido.class,"No se puede agendar en día sabado");

    }
    @Test
    public void validarCreacionPedidoExitoso(){
        ArrayList<DtoPedido> listaExistente = new ArrayList<DtoPedido>(){{
            add(new DtoPedido(1L,
                    "Nombre pedido",
                    "Descripcion pedido",
                    "estado",
                    LocalDateTime.now(),
                    LocalDateTime.of(2024,4,18,11,0),
                    LocalDateTime.of(2024,4,19,11,0),
                    100.0,
                    1L,
                    1L,
                    1L
            ));
        }};
        Pedido pedido = new PedidoTestDataBuilder().build();
        final ArgumentCaptor<Pedido> pedidoArgumentCaptor = ArgumentCaptor.forClass(Pedido.class);
        Mockito.when(repositorioPedido.listarPedidosPorIdTrabajador(pedido.getIdTrabajador())).thenReturn(listaExistente);
        servicioCrearPedido.ejecutar(pedido);
        Mockito.verify(repositorioPedido).crear(pedidoArgumentCaptor.capture());
        Assertions.assertEquals(pedidoArgumentCaptor.getValue().getId(),pedido.getId());
    }





}
