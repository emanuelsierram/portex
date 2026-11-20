package com.portex.miantorcha.dominio.servicio.actividad;

import com.portex.miantorcha.dominio.modelo.dto.DtoActividad;
import com.portex.miantorcha.dominio.modelo.dto.DtoAntorcha;
import com.portex.miantorcha.dominio.modelo.dto.DtoProgresoAntorcha;
import com.portex.miantorcha.dominio.modelo.entidad.Actividad;
import com.portex.miantorcha.dominio.modelo.entidad.EstadoActividad;
import com.portex.miantorcha.dominio.modelo.fabrica.FabricaAntorcha;
import com.portex.miantorcha.dominio.puerto.dao.DaoActividad;
import com.portex.miantorcha.dominio.puerto.dao.DaoAntorcha;
import com.portex.miantorcha.dominio.puerto.repositorio.RepositorioActividad;
import com.portex.miantorcha.dominio.servicio.antorcha.ServicioActualizarAntorcha;


public class ServicioGestionarActividad {

    private static final int PORCENTAJE_PROGRESO_MINIMO = 0;
    private static final int PORCENTAJE_PROGRESO_MAXIMO = 100;

    private final DaoActividad daoActividad;
    private final DaoAntorcha daoAntorcha;
    private final RepositorioActividad repositorioActividad;
    private final ServicioActualizarAntorcha servicioActualizarAntorcha;
    private final FabricaAntorcha fabricaAntorcha;

    public ServicioGestionarActividad(DaoActividad daoActividad, DaoAntorcha daoAntorcha,
                                      RepositorioActividad repositorioActividad,
                                      ServicioActualizarAntorcha servicioActualizarAntorcha,
                                      FabricaAntorcha fabricaAntorcha) {
        this.daoActividad = daoActividad;
        this.daoAntorcha = daoAntorcha;
        this.repositorioActividad = repositorioActividad;
        this.servicioActualizarAntorcha = servicioActualizarAntorcha;
        this.fabricaAntorcha = fabricaAntorcha;
    }

    public Long crear(Actividad actividad) {
        return repositorioActividad.crear(actividad);
    }

    public void actualizar(Actividad actividad) {
        DtoActividad actual = actividadPropia(actividad.getId(), actividad.getIdUsuario());
        if (EstadoActividad.HECHO.getValor().equals(actual.getEstado())) {
            throw new IllegalArgumentException("Una actividad HECHA no se puede editar");
        }
        repositorioActividad.actualizar(actividad);
    }

    public void cambiarEstado(Actividad actividad) {
        DtoActividad actual = actividadPropia(actividad.getId(), actividad.getIdUsuario());
        EstadoActividad nuevoEstado = actividad.getEstado();
        EstadoActividad estadoActual = EstadoActividad.desdeValor(actual.getEstado());
        if (estadoActual == EstadoActividad.HECHO) {
            if (nuevoEstado == EstadoActividad.HECHO) {
                return;
            }
            throw new IllegalArgumentException("Una actividad HECHA no puede cambiar de estado");
        }
        repositorioActividad.actualizar(actividad);
        if (nuevoEstado == EstadoActividad.HECHO) {
            recalcularAntorcha(actividad.getIdUsuario());
        }
    }

    public void eliminar(Actividad actividad) {
        DtoActividad actual = actividadPropia(actividad.getId(), actividad.getIdUsuario());
        repositorioActividad.eliminar(actividad.getId());
        if (EstadoActividad.HECHO.getValor().equals(actual.getEstado())) {
            recalcularAntorcha(actividad.getIdUsuario());
        }
    }

    private void recalcularAntorcha(String idUsuario) {
        DtoAntorcha antorcha = daoAntorcha.consultarPorUsuarioId(idUsuario);
        if (antorcha == null) {
            throw new IllegalStateException("El usuario no tiene una antorcha");
        }
        DtoProgresoAntorcha progresoAntorcha = daoAntorcha.consultarProgresoAntorcha(idUsuario);
        int total = progresoAntorcha.getTotalPuntos();
        int nivel = progresoAntorcha.getNivel();
        Integer siguiente = progresoAntorcha.getPuntosSiguienteNivel();
        int progreso = siguiente == null ? PORCENTAJE_PROGRESO_MAXIMO
                : Math.min(PORCENTAJE_PROGRESO_MAXIMO,
                Math.max(PORCENTAJE_PROGRESO_MINIMO, (total - progresoAntorcha.getPuntosMinimosNivel())
                        * PORCENTAJE_PROGRESO_MAXIMO
                / (siguiente - progresoAntorcha.getPuntosMinimosNivel())));
        servicioActualizarAntorcha.ejecutar(fabricaAntorcha.crearActualizacion(
                antorcha, nivel, total, progreso, siguiente));
    }

    private DtoActividad actividadPropia(Long id, String idUsuario) {
        DtoActividad actividad = daoActividad.consultarPorId(id);
        if (actividad == null || !idUsuario.equals(actividad.getIdUsuario())) {
            throw new IllegalArgumentException("La actividad no existe para el usuario autenticado");
        }
        return actividad;
    }

}
