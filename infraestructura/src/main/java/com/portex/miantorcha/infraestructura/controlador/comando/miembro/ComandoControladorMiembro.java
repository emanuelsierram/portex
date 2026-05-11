package com.portex.miantorcha.infraestructura.controlador.comando.miembro;

import com.portex.compartido.aplicacion.ComandoRespuesta;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/miembros")
public class ComandoControladorMiembro {

    private final ManejadorCrearMiembro manejadorCrearMiembro;
    private final ManejadorActualizarMiembro manejadorActualizarMiembro;
    private final ManejadorEliminarMiembro manejadorEliminarMiembro;

    public ComandoControladorMiembro(ManejadorCrearMiembro manejadorCrearMiembro,
                                     ManejadorActualizarMiembro manejadorActualizarMiembro,
                                     ManejadorEliminarMiembro manejadorEliminarMiembro) {
        this.manejadorCrearMiembro = manejadorCrearMiembro;
        this.manejadorActualizarMiembro = manejadorActualizarMiembro;
        this.manejadorEliminarMiembro = manejadorEliminarMiembro;
    }

    @PostMapping
    public ComandoRespuesta<Long> crear(@RequestBody ComandoMiembro comando) {
        return manejadorCrearMiembro.ejecutar(comando);
    }

    @PutMapping("/{id}")
    public void actualizar(@PathVariable Long id, @RequestBody ComandoMiembro comando) {
        comando.setId(id);
        manejadorActualizarMiembro.ejecutar(comando);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        manejadorEliminarMiembro.ejecutar(id);
    }
}