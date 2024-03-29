package com.portaexperiencia.portex.puerto.repositorio;

import com.portaexperiencia.portex.modelo.entidad.Valoracion;

public interface RepositorioValoracion {

    Long crear(Valoracion valoracion);

    void actualizar(Valoracion valoracion);
}
