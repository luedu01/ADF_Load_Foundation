package com.example.demo;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

public class ModularityArchTest {

    private final JavaClasses classes = new ClassFileImporter().importPackages("com.example.demo");

    @Test
    void modulesShouldNotHaveCyclicDependencies() {
        ArchRule rule = slices().matching("com.example.demo.(*)..").should().beFreeOfCycles();
        rule.check(classes);
    }

    @Test
    void authModuleShouldOnlyExposeApiPackage() {
        ArchRule rule = noClasses()
                .that().resideOutsideOfPackage("com.example.demo.auth..")
                .should().dependOnClassesThat().resideInAnyPackage(
                        "com.example.demo.auth.application..",
                        "com.example.demo.auth.infrastructure.."
                );
        rule.check(classes);
    }
}

