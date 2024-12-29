package io.github.eealba.payper.payments.v2;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import io.github.eealba.payper.payments.v2.api.PaymentsProvider;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;
import static com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_THROW_GENERIC_EXCEPTIONS;
import static com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_USE_JAVA_UTIL_LOGGING;

@AnalyzeClasses(packages = "io.github.eealba.payper.payments.v2")
public class MyArchitectureTest {
    @ArchTest
    static final ArchRule layer_dependencies_are_respected = layeredArchitecture().consideringOnlyDependenciesInLayers()

            .layer("api").definedBy("..api..")
            .layer("model").definedBy("..model..")
            .layer("internal").definedBy("..internal..")
            .whereLayer("api").mayOnlyBeAccessedByLayers("internal")
            .whereLayer("model").mayNotAccessAnyLayer()
            .whereLayer("internal").mayNotBeAccessedByAnyLayer();


    @ArchTest
    private final ArchRule no_generic_exceptions = NO_CLASSES_SHOULD_THROW_GENERIC_EXCEPTIONS;

    @ArchTest
    private final ArchRule no_java_util_logging = NO_CLASSES_SHOULD_USE_JAVA_UTIL_LOGGING;




    @ArchTest
    static ArchRule all_classes_that_reside_in_package_internal_should_be_package_private =
            classes().that().resideInAPackage("..internal..")
                    .and().areNotAssignableTo(PaymentsProvider.class)
                    .should().bePrivate().orShould().bePackagePrivate();

}
