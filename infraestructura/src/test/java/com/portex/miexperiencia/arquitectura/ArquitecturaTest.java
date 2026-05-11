package com.portex.miexperiencia.arquitectura;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.junit.jupiter.api.Test;


public class ArquitecturaTest {
    @Test
    public void verificarNomenclaturaServicios() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("com.portex.miexperiencia");

        ArchRule rule = ArchRuleDefinition.classes()
                .that().resideInAPackage("..servicio..")
                .should().haveSimpleNameStartingWith("Servicio");

        rule.check(importedClasses);
    }

    @Test
    public void verificarQueLosServiciosSoloDebenSerAccedidosPorControladoresUOtrosServciosOConfiguraciones() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("com.portex.miexperiencia");

        ArchRule rule = ArchRuleDefinition.classes()
                .that().resideInAPackage("..servicio..")
                .should().onlyBeAccessed().byAnyPackage("..controlador..", "..servicio..", "..configuracion..", "..seguridad..");

        rule.check(importedClasses);
    }
}
