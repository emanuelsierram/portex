package com.portex.miantorcha.dominio.puerto.repositorio;

import com.portex.miantorcha.dominio.modelo.entidad.Actividad;

public interface RepositorioActividad {

    Long crear(Actividad actividad);

    void actualizar(Actividad actividad);

    void eliminar(Long id);
}
