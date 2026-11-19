package com.portex.miantorcha.dominio.servicio.actividad;

import com.portex.miantorcha.dominio.modelo.dto.DtoActividad;
import com.portex.miantorcha.dominio.modelo.dto.DtoAntorcha;
import com.portex.miantorcha.dominio.modelo.dto.DtoTipoActividad;
import com.portex.miantorcha.dominio.modelo.entidad.Actividad;
import com.portex.miantorcha.dominio.modelo.entidad.Antorcha;
import com.portex.miantorcha.dominio.modelo.entidad.EstadoActividad;
import com.portex.miantorcha.dominio.puerto.dao.DaoActividad;
import com.portex.miantorcha.dominio.puerto.dao.DaoAntorcha;
import com.portex.miantorcha.dominio.puerto.repositorio.RepositorioActividad;
import com.portex.miantorcha.dominio.servicio.antorcha.ServicioActualizarAntorcha;

import java.time.LocalDate;

public class ServicioGestionarActividad {

    private final DaoActividad daoActividad;
    private final DaoAntorcha daoAntorcha;
    private final RepositorioActividad repositorioActividad;
    private final ServicioActualizarAntorcha servicioActualizarAntorcha;

    public ServicioGestionarActividad(DaoActividad daoActividad, DaoAntorcha daoAntorcha,
                                      RepositorioActividad repositorioActividad,
                                      ServicioActualizarAntorcha servicioActualizarAntorcha) {
        this.daoActividad = daoActividad;
        this.daoAntorcha = daoAntorcha;
        this.repositorioActividad = repositorioActividad;
        this.servicioActualizarAntorcha = servicioActualizarAntorcha;
    }

    public Long crear(String idUsuario, Long idTipoActividad, String descripcionOtro,
                      LocalDate fechaRealizacion, Boolean informarObra, String observaciones) {
        DtoTipoActividad tipo = tipoObligatorio(idTipoActividad);
        Actividad actividad = new Actividad(null, idUsuario, tipo.getId(), tipo.getCodigo(),
                descripcionOtro, EstadoActividad.POR_HACER, fechaRealizacion, informarObra,
                observaciones, tipo.getPuntos());
        return repositorioActividad.crear(actividad);
    }

    public void marcarHecha(Long id, String idUsuario) {
        DtoActividad actual = actividadPropia(id, idUsuario);
        if (EstadoActividad.HECHO.getValor().equals(actual.getEstado())) {
            return;
        }
        LocalDate fecha = actual.getFechaRealizacion().isAfter(LocalDate.now())
                ? LocalDate.now() : actual.getFechaRealizacion();
        actualizar(actual, EstadoActividad.HECHO.getValor(), fecha);
        recalcularAntorcha(idUsuario);
    }

    public void eliminar(Long id, String idUsuario) {
        DtoActividad actual = actividadPropia(id, idUsuario);
        repositorioActividad.eliminar(id);
        if (EstadoActividad.HECHO.getValor().equals(actual.getEstado())) {
            recalcularAntorcha(idUsuario);
        }
    }

    private void actualizar(DtoActividad actual, String estado, LocalDate fecha) {
        DtoTipoActividad tipo = tipoObligatorio(actual.getIdTipoActividad());
        Actividad entidad = new Actividad(actual.getId(), actual.getIdUsuario(),
                actual.getIdTipoActividad(), tipo.getCodigo(), actual.getDescripcionOtro(),
                EstadoActividad.desdeValor(estado), fecha, actual.getInformarObra(),
                actual.getObservaciones(), tipo.getPuntos());
        repositorioActividad.actualizar(entidad);
    }

    private void recalcularAntorcha(String idUsuario) {
        DtoAntorcha antorcha = daoAntorcha.consultarPorUsuarioId(idUsuario);
        if (antorcha == null) {
            throw new IllegalStateException("El usuario no tiene una antorcha");
        }
        int total = daoActividad.totalPuntosHechos(idUsuario);
        int nivel = daoActividad.nivelPorPuntos(total);
        Integer siguiente = daoActividad.siguienteNivelPuntos(nivel);
        int progreso = siguiente == null ? 100
                : Math.min(100, Math.max(0, (total - daoActividad.puntosMinimosNivel(nivel)) * 100
                / (siguiente - daoActividad.puntosMinimosNivel(nivel))));
        servicioActualizarAntorcha.ejecutar(new Antorcha(antorcha.getId(), nivel, idUsuario),
                total, progreso, siguiente);
    }

    private DtoActividad actividadPropia(Long id, String idUsuario) {
        DtoActividad actividad = daoActividad.consultarPorId(id);
        if (actividad == null || !idUsuario.equals(actividad.getIdUsuario())) {
            throw new IllegalArgumentException("La actividad no existe para el usuario autenticado");
        }
        if (EstadoActividad.HECHO.getValor().equals(actividad.getEstado())) {
            return actividad;
        }
        return actividad;
    }

    private DtoTipoActividad tipoObligatorio(Long id) {
        DtoTipoActividad tipo = daoActividad.consultarTipoPorId(id);
        if (tipo == null || !Boolean.TRUE.equals(tipo.getActivo())) {
            throw new IllegalArgumentException("El tipo de actividad no existe o está inactivo");
        }
        return tipo;
    }
}
