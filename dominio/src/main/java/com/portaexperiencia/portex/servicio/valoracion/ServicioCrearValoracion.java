package com.portaexperiencia.portex.servicio.valoracion;

import com.portaexperiencia.portex.modelo.entidad.Valoracion;
import com.portaexperiencia.portex.puerto.repositorio.RepositorioValoracion;

public class ServicioCrearValoracion {

    private final RepositorioValoracion repositorioValoracion;


    public ServicioCrearValoracion(RepositorioValoracion repositorioValoracion) {
        this.repositorioValoracion = repositorioValoracion;
    }


    public Long ejecutar(Valoracion valoracion){
        return this.repositorioValoracion.crear(valoracion);
    }
}
