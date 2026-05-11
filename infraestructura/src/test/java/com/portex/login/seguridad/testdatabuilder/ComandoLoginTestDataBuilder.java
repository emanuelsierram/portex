package com.portex.login.seguridad.testdatabuilder;

import com.portex.compartido.dominio.modelo.entidad.Login;

public class ComandoLoginTestDataBuilder {

    private String usuario;
    private String contrasena;

    public ComandoLoginTestDataBuilder(){
        usuario="admin";
        contrasena="admin";
    }

public Login buid(){return new Login(usuario,contrasena);}


}
