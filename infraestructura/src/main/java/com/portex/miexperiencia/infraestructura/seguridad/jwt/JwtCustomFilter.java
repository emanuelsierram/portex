package com.portex.miexperiencia.infraestructura.seguridad.jwt;

import com.portex.miexperiencia.infraestructura.seguridad.servicio.ServicioSeguridadUsuario;
import com.portex.compartido.infraestructura.jwt.JwtTokenManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtCustomFilter extends OncePerRequestFilter {

    private final JwtTokenManager jwtTokenManager;
    private final ServicioSeguridadUsuario servicioSeguridadUsuario;

    public JwtCustomFilter(JwtTokenManager jwtTokenManager, ServicioSeguridadUsuario servicioSeguridadUsuario) {
        this.jwtTokenManager = jwtTokenManager;
        this.servicioSeguridadUsuario = servicioSeguridadUsuario;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //Validar que sea un Header de Authorization Valido
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authHeader==null || authHeader.isEmpty() || !authHeader.startsWith("Bearer")){
            filterChain.doFilter(request,response);
            return;
        }
        //Validar que el JWT Sea Valido
        String jwt=authHeader.split(" ")[1].trim();
        if(!this.jwtTokenManager.esValido(jwt)){
            filterChain.doFilter(request,response);
            return;
        }
        //Cargar el usuario ServicioUsurioSeguridad
        String usuario = this.jwtTokenManager.obtenerUsuario(jwt);
        User user= (User) this.servicioSeguridadUsuario.loadUserByUsername(usuario);
        //Cargar al Usuario en el contexto de seguridad
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                user.getUsername(),
                user.getPassword(),
                user.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        System.out.println(usernamePasswordAuthenticationToken);
        filterChain.doFilter(request,response);

    }
}
