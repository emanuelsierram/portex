package com.portaexperiencia.portex.testdatabuilder;

import com.portaexperiencia.portex.modelo.entidad.Login;

public class LoginTestDataBuilder {

    private String usuario;
    private String contrasena;

    public LoginTestDataBuilder(){
        usuario="admin";
        contrasena="admin";
    }

public Login buid(){return new Login(usuario,contrasena);}


}
