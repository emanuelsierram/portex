package com.portex.login;

import com.portex.compartido.dominio.modelo.entidad.Login;

public class LoginTestDataBuilder {

    private String usuario;
    private String contrasena;

    public LoginTestDataBuilder(){
        usuario="admin";
        contrasena="admin";
    }

public Login buid(){return new Login(usuario,contrasena);}


}
