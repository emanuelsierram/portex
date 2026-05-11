package com.portex.miexperiencia.dominio.modelo.entidad;


import com.portex.compartido.dominio.excepcion.ExcepcionValorInvalido;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.Period;

import static com.portex.compartido.dominio.ValidadorArgumento.validarMenorFecha;
import static com.portex.compartido.dominio.ValidadorArgumento.validarObligatorio;
import static com.portex.compartido.dominio.ValidadorArgumento.validarMenor;


@Getter
@Setter
public class Pedido {

    private static final String ESTADO_POR_DEFECTO="solicitado";

    private static final String SE_DEBE_INGRESAR_EL_NOMBRE="Se debe ingresar el nombre del pedido";
    private static final String SE_DEBE_INGRESAR_LA_DESCRIPCION="Se debe ingresar la descripción";
    private static final String SE_DEBE_INGRESAR_LA_FECHA_INICIO="Se debe ingresar la fecha de inicio";
    private static final String SE_DEBE_INGRESAR_LA_FECHA_FINAL="Se debe ingresar la fecha final";
    private static final String SE_DEBE_INGRESAR_EL_PRESUPUESTO="Se debe ingresar el presupuesto";
    private static final String SATURDAY="SATURDAY";
    private static  final String NO_SE_PUEDE_AGENDAR_EN_DIA_SABADO="No se puede agendar en día sabado";
    private static final int DURACION_MAXIMA_PEDIDO=34;
    private static final String DURACION_MAXIMA_DE_UN_MES="La duración del pedido debe ser maximo de un mes";
    private static final String FECHA_INCORRECTA="La fecha inicial no debe ser mayor a la fecha final";
    private static final String FECHA_INCORRECTA_CREACION="La fecha inicial no debe ser menor a la fecha actual";
    private static final String FECHA_INCORRECTA_APLAZADO="La fecha inicial no debe ser mayor a la fecha de aplazo";
    private static final Long DIAS_MAXIMOS_PARA_APLAZAR= 17L;
    private static final String NO_SE_DEBE_APLAZAR_MAS_DE_15_DIAS="No se puede aplazar un pedido más de 15 días";




    private Long id;
    private String nombre;
    private String descripcion;
    private String estado;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFinal;
    private Double presupuesto;
    private Long idCliente;
    private Long idTrabajador;
    private Long idValoracion;



    public Pedido(Long id, String nombre,
                  String descripcion,
                  LocalDateTime fechaInicio,
                  LocalDateTime fechaFinal,
                  Double presupuesto,
                  Long idCliente,
                  Long idTrabajador,
                  Long idValoracion) {

        validarObligatorio(nombre,SE_DEBE_INGRESAR_EL_NOMBRE);
        validarObligatorio(descripcion,SE_DEBE_INGRESAR_LA_DESCRIPCION);
        validarObligatorio(fechaInicio,SE_DEBE_INGRESAR_LA_FECHA_INICIO);
        validarObligatorio(fechaFinal,SE_DEBE_INGRESAR_LA_FECHA_FINAL);
        validarObligatorio(presupuesto,SE_DEBE_INGRESAR_EL_PRESUPUESTO);
        validarMenorFecha(LocalDateTime.now(),fechaInicio,FECHA_INCORRECTA_CREACION);
        validarMenorFecha(fechaInicio,fechaFinal,FECHA_INCORRECTA);
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = ESTADO_POR_DEFECTO;
        this.fechaCreacion=LocalDateTime.now();
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.presupuesto = presupuesto;
        this.idCliente = idCliente;
        this.idTrabajador = idTrabajador;
        this.idValoracion = idValoracion;
        validarNoAgendarDiaSabado();
    }

    private  void validarNoAgendarDiaSabado(){
        if(SATURDAY.equals(this.fechaInicio.getDayOfWeek().name()) || SATURDAY.equals(this.fechaFinal.getDayOfWeek().name()) ) {
            throw new ExcepcionValorInvalido(NO_SE_PUEDE_AGENDAR_EN_DIA_SABADO);
        }
    }

    public void validarFechaAplazado(LocalDateTime nuevaFecha){
        validarMenorFecha(this.fechaInicio,nuevaFecha,FECHA_INCORRECTA_APLAZADO);
        Period periodo = Period.between(
                this.fechaInicio.toLocalDate(),
                nuevaFecha.toLocalDate()
        );
        Long dias= (long) periodo.getDays();
        validarMenor(dias,DIAS_MAXIMOS_PARA_APLAZAR,NO_SE_DEBE_APLAZAR_MAS_DE_15_DIAS);
    }










}
