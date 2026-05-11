package com.portex.miexperiencia.dominio.puerto.repositorio;

import com.portex.miexperiencia.dominio.modelo.entidad.Valoracion;

public interface RepositorioValoracion {

    Long crear(Valoracion valoracion);

    void actualizar(Valoracion valoracion);
}
