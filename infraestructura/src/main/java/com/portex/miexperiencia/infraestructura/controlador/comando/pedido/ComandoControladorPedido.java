package com.portex.miexperiencia.infraestructura.controlador.comando.pedido;


import com.portex.compartido.aplicacion.ComandoRespuesta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos")
public class ComandoControladorPedido {


    private final ManejadorCrearPedido manejadorCrearPedido;


    @Autowired
    public ComandoControladorPedido(ManejadorCrearPedido manejadorCrearPedido) {
        this.manejadorCrearPedido = manejadorCrearPedido;
    }



    @PostMapping
    public ComandoRespuesta<Long> crear(@RequestBody ComandoPedido comandoPedido){

        return manejadorCrearPedido.ejecutar(comandoPedido);
    }

      /* @PostMapping
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
    }*/



}
