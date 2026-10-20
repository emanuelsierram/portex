package com.portex.miantorcha.infraestructura.controlador.consulta.estudio;

import com.portex.miantorcha.dominio.modelo.dto.DtoEstudioBiblico;
import com.portex.miantorcha.dominio.modelo.dto.DtoHistoricoLeccion;
import com.portex.miantorcha.dominio.modelo.dto.DtoMiembro;
import com.portex.miantorcha.dominio.puerto.dao.DaoEstudioBiblico;
import com.portex.miantorcha.dominio.puerto.dao.DaoMiembro;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListarEstudioBiblico {

    private final DaoEstudioBiblico daoEstudioBiblico;
    private final DaoMiembro daoMiembro;

    public ManejadorListarEstudioBiblico(DaoEstudioBiblico daoEstudioBiblico, DaoMiembro daoMiembro) {
        this.daoEstudioBiblico = daoEstudioBiblico;
        this.daoMiembro = daoMiembro;
    }

    // Consulta: "mirar mi estudio biblico actual"
    public List<DtoEstudioBiblico> consultarActualesPorMiembro(String usuarioId) {
        DtoMiembro miembro = this.daoMiembro.consultarPorUsuarioId(usuarioId);
        return this.daoEstudioBiblico.consultarActualesPorMiembro(miembro.getId());
    }

    // Consulta: "mirar todos los estudios bíblicos disponibles con estado por hacer (por dar)"
    public List<DtoEstudioBiblico> consultarDisponiblesPorDar() {
        return this.daoEstudioBiblico.consultarDisponiblesPorDar();
    }

    public List<DtoHistoricoLeccion> consultarHistoricoLecciones(long idEstudioBiblico){
        return this.daoEstudioBiblico.consultarHistoricoPorEstudio(idEstudioBiblico);
    }
}