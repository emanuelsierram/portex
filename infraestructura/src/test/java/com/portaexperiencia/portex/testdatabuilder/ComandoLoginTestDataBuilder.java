package com.portaexperiencia.portex.testdatabuilder;

import com.portaexperiencia.portex.modelo.entidad.Login;

public class ComandoLoginTestDataBuilder {

    private String usuario;
    private String contrasena;

    public ComandoLoginTestDataBuilder(){
        usuario="admin";
        contrasena="admin";
    }

public Login buid(){return new Login(usuario,contrasena);}


}
