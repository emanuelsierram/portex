package com.portex.compartido.infraestructura.seguridad.controlador;


import com.portex.compartido.dominio.modelo.entidad.Login;
import com.portex.compartido.dominio.servicio.ServicioIniciarSesion;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AuthControloador {

    private final AuthenticationManager authenticationManager;
    private final ServicioIniciarSesion servicioIniciarSesion;

    public AuthControloador(AuthenticationManager authenticationManager, ServicioIniciarSesion servicioIniciarSesion) {
        this.authenticationManager = authenticationManager;
        this.servicioIniciarSesion = servicioIniciarSesion;
    }

    @PostMapping
    public ResponseEntity<Void> login(@RequestBody Login dtoLogin){
        UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(dtoLogin.getUsuario(), dtoLogin.getContrasena());
        Authentication authentication =  this.authenticationManager.authenticate(login);
        String jwt = servicioIniciarSesion.ejecutar(dtoLogin);
        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwt).build();
    }


}
