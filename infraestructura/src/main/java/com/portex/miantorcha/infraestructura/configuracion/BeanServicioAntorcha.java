package com.portex.miantorcha.infraestructura.configuracion;

import com.portex.miantorcha.dominio.puerto.dao.DaoEstudioBiblico;
import com.portex.miantorcha.dominio.puerto.dao.DaoMiembro;
import com.portex.miantorcha.dominio.puerto.repositorio.RepositorioEstudioBiblico;
import com.portex.miantorcha.dominio.puerto.repositorio.RepositorioMiembro;
import com.portex.miantorcha.dominio.servicio.estudio.ServicioActualizarEstudioBiblico;
import com.portex.miantorcha.dominio.servicio.estudio.ServicioCrearEstudioBiblico;
import com.portex.miantorcha.dominio.servicio.estudio.ServicioEliminarEstudioBiblico;
import com.portex.miantorcha.dominio.servicio.estudio.ServicioRegistrarLeccionEstudio;
import com.portex.miantorcha.dominio.servicio.miembro.ServicioActualizarMiembro;
import com.portex.miantorcha.dominio.servicio.miembro.ServicioCrearMiembro;
import com.portex.miantorcha.dominio.servicio.miembro.ServicioEliminarMiembro;
import com.portex.miantorcha.dominio.servicio.antorcha.ServicioActualizarAntorcha;
import com.portex.miantorcha.dominio.servicio.actividad.ServicioGestionarActividad;
import com.portex.miantorcha.dominio.puerto.dao.DaoActividad;
import com.portex.miantorcha.dominio.puerto.notificador.PublicadorAntorcha;
import com.portex.miantorcha.dominio.puerto.repositorio.RepositorioAntorcha;
import com.portex.miantorcha.dominio.modelo.fabrica.FabricaAntorcha;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicioAntorcha {

    @Bean
    public ServicioActualizarAntorcha servicioActualizarAntorcha(
            RepositorioAntorcha repositorioAntorcha,
            PublicadorAntorcha publicadorAntorcha) {
        return new ServicioActualizarAntorcha(repositorioAntorcha, publicadorAntorcha);
    }

    @Bean
    public FabricaAntorcha fabricaAntorcha() {
        return new FabricaAntorcha();
    }

    @Bean
    public ServicioGestionarActividad servicioGestionarActividad(
            DaoActividad daoActividad,
            com.portex.miantorcha.dominio.puerto.dao.DaoAntorcha daoAntorcha,
            com.portex.miantorcha.dominio.puerto.repositorio.RepositorioActividad repositorioActividad,
            ServicioActualizarAntorcha servicioActualizarAntorcha,
            FabricaAntorcha fabricaAntorcha) {
        return new ServicioGestionarActividad(daoActividad, daoAntorcha, repositorioActividad,
                servicioActualizarAntorcha, fabricaAntorcha);
    }

    @Bean
    public ServicioCrearMiembro servicioCrearMiembro(RepositorioMiembro repositorioMiembro) {
        return new ServicioCrearMiembro(repositorioMiembro);
    }
    @Bean
    public ServicioActualizarMiembro servicioActualizarMiembro(RepositorioMiembro repositorioMiembro) {
        return new ServicioActualizarMiembro(repositorioMiembro);
    }
    @Bean
    public ServicioEliminarMiembro servicioEliminarMiembro(RepositorioMiembro repositorioMiembro) {
        return new ServicioEliminarMiembro(repositorioMiembro);
    }

    @Bean
    public ServicioCrearEstudioBiblico servicioCrearEstudioBiblico(RepositorioEstudioBiblico repositorioEstudioBiblico, DaoEstudioBiblico daoEstudioBiblico, DaoMiembro daoMiembro) {
        return new ServicioCrearEstudioBiblico(daoEstudioBiblico, daoMiembro, repositorioEstudioBiblico);
    }

    @Bean
    public ServicioActualizarEstudioBiblico servicioActualizarEstudioBiblico(RepositorioEstudioBiblico repositorioEstudioBiblico, DaoEstudioBiblico daoEstudioBiblico) {
        return new ServicioActualizarEstudioBiblico(repositorioEstudioBiblico, daoEstudioBiblico);
    }

    @Bean
    public ServicioEliminarEstudioBiblico servicioEliminarEstudioBiblico(RepositorioEstudioBiblico repositorioEstudioBiblico, DaoEstudioBiblico daoEstudioBiblico) {
        return new ServicioEliminarEstudioBiblico(repositorioEstudioBiblico, daoEstudioBiblico);
    }

    @Bean
    public ServicioRegistrarLeccionEstudio servicioRegistrarLeccionEstudio(RepositorioEstudioBiblico repositorioEstudioBiblico, DaoEstudioBiblico daoEstudioBiblico) {
        return new ServicioRegistrarLeccionEstudio(repositorioEstudioBiblico, daoEstudioBiblico);
    }
}
