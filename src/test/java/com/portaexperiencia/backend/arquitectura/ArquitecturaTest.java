package com.portaexperiencia.backend.arquitectura;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;


public class ArquitecturaTest {
    @Test
    public void verificarNomenclaturaServicios() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("com.portaexperiencia.backend");

        ArchRule rule = classes()
                .that().resideInAPackage("..servicio..")
                .should().haveSimpleNameStartingWith("Servicio");

        rule.check(importedClasses);
    }

    @Test
    public void verificarQueLosServiciosSoloDebenSerAccedidosPorControladoresUOtrosServciosOConfiguraciones() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("com.portaexperiencia.backend");

        ArchRule rule = classes()
                .that().resideInAPackage("..servicio..")
                .should().onlyBeAccessed().byAnyPackage("..controlador..", "..servicio..", "..configuracion..");

        rule.check(importedClasses);
    }
}
