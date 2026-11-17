package com.portex.miantorcha.dominio.puerto.repositorio;

import com.portex.miantorcha.dominio.modelo.entidad.Antorcha;

public interface RepositorioAntorcha {

    Long crear(Antorcha antorcha);

    void actualizar(Antorcha antorcha);
}
