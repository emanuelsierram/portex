package com.portex.compartido.infraestructura.seguridad;

import java.util.List;

public record ResultadoIntrospeccion(boolean active,
                                     String usuario,
                                     List<String> roles) {
}
