package com.portaexperiencia.backend.infraestructura.controlador;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.portaexperiencia.backend.dominio.modelo.entidad.Pedido;
import com.portaexperiencia.backend.dominio.servicio.ServicioCrearPedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos")
public class ControladorPedido {


    private final ServicioCrearPedido servicioCrearPedido;


    @Autowired
    public ControladorPedido(ServicioCrearPedido servicioCrearPedido) {
        this.servicioCrearPedido = servicioCrearPedido;
    }


    @PostMapping
    public ResponseEntity<String> crear(@RequestBody Pedido pedido){
        ObjectMapper objectMapper= new ObjectMapper();
        String json=null;
        ResponseEntity mensaje;
        try {
            json=objectMapper.writeValueAsString(servicioCrearPedido.ejecutar(pedido));
            mensaje=ResponseEntity.ok(json);
        }catch (Exception e){
            mensaje= ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

        return mensaje;
    }



}
