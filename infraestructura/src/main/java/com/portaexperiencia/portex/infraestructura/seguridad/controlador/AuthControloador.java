package com.portaexperiencia.portex.infraestructura.seguridad.controlador;


import com.portaexperiencia.portex.modelo.dto.DtoLogin;
import com.portaexperiencia.infraestructura.jwt.JwtTokenManager;
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
    private final JwtTokenManager jwtTokenManager;

    public AuthControloador(AuthenticationManager authenticationManager, JwtTokenManager jwtTokenManager) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenManager = jwtTokenManager;
    }

    @PostMapping
    public ResponseEntity<Void> login(@RequestBody DtoLogin dtoLogin){
        UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(dtoLogin.getUsuario(), dtoLogin.getContrasena());
        Authentication authentication =  this.authenticationManager.authenticate(login);

        String jwt = this.jwtTokenManager.crear(dtoLogin.getUsuario());
        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwt).build();
    }


}
