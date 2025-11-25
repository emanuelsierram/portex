package com.portaexperiencia.portex.infraestructura.controlador.comando.valoracion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoValoracion {

    private Long id;
    private String comentario;
    private Integer valoracion;
}
