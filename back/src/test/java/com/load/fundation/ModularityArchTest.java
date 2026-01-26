package com.load.fundation;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

public class ModularityArchTest {

    private final JavaClasses classes = new ClassFileImporter().importPackages("com.load.fundation");

    @Test
    void modulesShouldNotHaveCyclicDependencies() {
        ArchRule rule = slices().matching("com.load.fundation.(*)..").should().beFreeOfCycles();
        rule.check(classes);
    }

    @Test
    void domainsShouldBeIndependent() {
        ArchRule rule = noClasses()
                .that().resideInAPackage("..auth..")
                .should().dependOnClassesThat().resideInAnyPackage("..user..");
        rule.check(classes);
    }
}
