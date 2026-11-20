package com.portex.miantorcha.infraestructura.controlador.consulta.actividad;

import com.portex.miantorcha.dominio.modelo.dto.DtoActividad;
import com.portex.miantorcha.dominio.modelo.dto.DtoTipoActividad;
import com.portex.miantorcha.dominio.puerto.dao.DaoActividad;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorConsultarActividad {
    private final DaoActividad daoActividad;

    public ManejadorConsultarActividad(DaoActividad daoActividad) {
        this.daoActividad = daoActividad;
    }

    public List<DtoTipoActividad> listarTipos() {
        return daoActividad.listarTiposActivos();
    }

    public List<DtoActividad> listarPorUsuario(String idUsuario) {
        return daoActividad.listarPorUsuario(idUsuario);
    }

    public DtoActividad consultarPorId(Long id, String idUsuario) {
        DtoActividad actividad = daoActividad.consultarPorId(id);
        if (actividad == null || !idUsuario.equals(actividad.getIdUsuario())) {
            return null;
        }
        return actividad;
    }
}
