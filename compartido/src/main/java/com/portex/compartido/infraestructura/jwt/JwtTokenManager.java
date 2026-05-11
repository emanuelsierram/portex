package com.portex.compartido.infraestructura.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JwtTokenManager {

    private static String LLAVE_SECRETA="em4nuelsierr417";

    private static Algorithm ALGORITHM= Algorithm.HMAC256(LLAVE_SECRETA);

    public String crear(String username){
        return JWT.create()
                .withSubject(username)
                .withIssuer("emanuelsierra17")
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(15)))
                .sign(ALGORITHM);
    }

    public boolean esValido(String jwt){
        try {
            JWT.require(ALGORITHM)
                    .build()
                    .verify(jwt);
            return true;
        }catch (JWTVerificationException e){
            return false;
        }
    }

    public String obtenerUsuario(String jwt){
        return JWT.require(ALGORITHM)
                .build()
                .verify(jwt)
                .getSubject();
    }


}
