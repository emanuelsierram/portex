package com.portex.miexperiencia.dominio.servicio.valoracion;

import com.portex.miexperiencia.dominio.modelo.entidad.Valoracion;
import com.portex.miexperiencia.dominio.puerto.repositorio.RepositorioValoracion;

public class ServicioCrearValoracion {

    private final RepositorioValoracion repositorioValoracion;


    public ServicioCrearValoracion(RepositorioValoracion repositorioValoracion) {
        this.repositorioValoracion = repositorioValoracion;
    }


    public Long ejecutar(Valoracion valoracion){
        return this.repositorioValoracion.crear(valoracion);
    }
}
