package com.portex.miexperiencia.testdatabuilder;

import com.portex.miexperiencia.dominio.modelo.entidad.Login;

public class LoginTestDataBuilder {

    private String usuario;
    private String contrasena;

    public LoginTestDataBuilder(){
        usuario="admin";
        contrasena="admin";
    }

public Login buid(){return new Login(usuario,contrasena);}


}
