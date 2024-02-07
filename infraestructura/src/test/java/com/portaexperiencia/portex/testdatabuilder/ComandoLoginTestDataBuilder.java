package com.portaexperiencia.portex.testdatabuilder;

import com.portaexperiencia.portex.modelo.dto.DtoLogin;

public class ComandoLoginTestDataBuilder {

    private String usuario;
    private String contrasena;

    public ComandoLoginTestDataBuilder(){
        usuario="cliente";
        contrasena="inteligentes17";
    }

public DtoLogin buid(){return new DtoLogin(usuario,contrasena);}


}
