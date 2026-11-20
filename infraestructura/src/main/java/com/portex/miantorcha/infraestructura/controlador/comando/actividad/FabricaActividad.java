package com.portex.miantorcha.infraestructura.controlador.comando.actividad;

import com.portex.miantorcha.dominio.modelo.dto.DtoActividad;
import com.portex.miantorcha.dominio.modelo.dto.DtoTipoActividad;
import com.portex.miantorcha.dominio.modelo.entidad.Actividad;
import com.portex.miantorcha.dominio.modelo.entidad.EstadoActividad;
import com.portex.miantorcha.dominio.puerto.dao.DaoActividad;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class FabricaActividad {

    private final DaoActividad daoActividad;

    public FabricaActividad(DaoActividad daoActividad) {
        this.daoActividad = daoActividad;
    }

    public Actividad crear(ComandoActividad comando, String idUsuario) {
        DtoTipoActividad tipo = tipoObligatorio(comando.getIdTipoActividad());
        return new Actividad(null, idUsuario, tipo.getId(), tipo.getCodigo(),
                comando.getDescripcionOtro(), estadoPorFecha(comando.getFechaRealizacion()),
                comando.getFechaRealizacion(), comando.getInformarObra(),
                comando.getObservaciones(), tipo.getPuntos());
    }

    public Actividad actualizar(ComandoActividad comando, Long id, String idUsuario) {
        DtoTipoActividad tipo = tipoObligatorio(comando.getIdTipoActividad());
        return new Actividad(id, idUsuario, tipo.getId(), tipo.getCodigo(),
                comando.getDescripcionOtro(), estadoPorFecha(comando.getFechaRealizacion()),
                comando.getFechaRealizacion(), comando.getInformarObra(),
                comando.getObservaciones(), tipo.getPuntos());
    }

    public Actividad cambiarEstado(Long id, String idUsuario, EstadoActividad estado) {
        DtoActividad actual = actividadPropia(id, idUsuario);
        DtoTipoActividad tipo = tipoObligatorio(actual.getIdTipoActividad());
        LocalDate fecha = estado == EstadoActividad.HECHO
                && actual.getFechaRealizacion().isAfter(LocalDate.now())
                ? LocalDate.now() : actual.getFechaRealizacion();
        return new Actividad(id, idUsuario, tipo.getId(), tipo.getCodigo(),
                actual.getDescripcionOtro(), estado, fecha, actual.getInformarObra(),
                actual.getObservaciones(), tipo.getPuntos());
    }

    public Actividad consultar(Long id, String idUsuario) {
        DtoActividad actual = actividadPropia(id, idUsuario);
        DtoTipoActividad tipo = tipoObligatorio(actual.getIdTipoActividad());
        return new Actividad(actual.getId(), idUsuario, tipo.getId(), tipo.getCodigo(),
                actual.getDescripcionOtro(), EstadoActividad.desdeValor(actual.getEstado()),
                actual.getFechaRealizacion(), actual.getInformarObra(),
                actual.getObservaciones(), tipo.getPuntos());
    }

    private DtoActividad actividadPropia(Long id, String idUsuario) {
        DtoActividad actividad = daoActividad.consultarPorId(id);
        if (actividad == null || !idUsuario.equals(actividad.getIdUsuario())) {
            throw new IllegalArgumentException("La actividad no existe para el usuario autenticado");
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

    private EstadoActividad estadoPorFecha(LocalDate fecha) {
        return LocalDate.now().equals(fecha)
                ? EstadoActividad.EN_PROCESO : EstadoActividad.POR_HACER;
    }
}
