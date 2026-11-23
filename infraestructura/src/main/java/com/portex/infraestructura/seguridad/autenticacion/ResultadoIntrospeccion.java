package com.portex.infraestructura.seguridad.autenticacion;

import java.util.List;

public record ResultadoIntrospeccion(boolean active,
                                     String usuario,
                                     List<String> roles) {
}
