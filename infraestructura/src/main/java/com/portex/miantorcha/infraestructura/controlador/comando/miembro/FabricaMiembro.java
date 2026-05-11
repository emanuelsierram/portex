package com.portex.miantorcha.infraestructura.controlador.comando.miembro;

import com.portex.miantorcha.dominio.modelo.entidad.Miembro;
import org.springframework.stereotype.Component;

@Component
public class FabricaMiembro {

    public Miembro crear(ComandoMiembro comandoMiembro){
        return new Miembro(comandoMiembro.getId(),
                comandoMiembro.getTelefono(),
                comandoMiembro.getNombres(),
                comandoMiembro.getApellidos(),
                comandoMiembro.getEmail(),
                comandoMiembro.getTelefono(),
                comandoMiembro.getPerfil(),
                comandoMiembro.getIdGrupoPequeno(),
                comandoMiembro.getIdAnciano());
    }
}
