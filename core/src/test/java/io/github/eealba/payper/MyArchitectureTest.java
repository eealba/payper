package io.github.eealba.payper;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import io.github.eealba.payper.core.client.PayperProvider;
import io.github.eealba.payper.core.json.internal.JsonProviderImpl;
import io.github.eealba.payper.core.web.internal.WebProviderImpl;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;
import static com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_THROW_GENERIC_EXCEPTIONS;
import static com.tngtech.archunit.library.GeneralCodingRules.NO_CLASSES_SHOULD_USE_JAVA_UTIL_LOGGING;

@AnalyzeClasses(packages = "io.github.eealba.payper.core")
public class MyArchitectureTest {
    @ArchTest
    static final ArchRule layer_dependencies_are_respected = layeredArchitecture()
            .consideringOnlyDependenciesInLayers()
//            .layer("java").definedBy("java..")
            .layer("client").definedBy("..client..")
            .layer("json").definedBy("..json..")
            .layer("web").definedBy("..web..")
            .layer("util").definedBy("..util..")
            .layer("exceptions").definedBy("..exceptions..")
            .layer("internal").definedBy("..internal..")
            .whereLayer("util").mayNotAccessAnyLayer()
            .whereLayer("web").mayOnlyAccessLayers("util", "exceptions")
            .whereLayer("json").mayOnlyAccessLayers("util", "exceptions")
            .whereLayer("client").mayOnlyAccessLayers("util", "exceptions", "json", "web")
            .whereLayer("internal").mayNotBeAccessedByAnyLayer()
            ;


    @ArchTest
    private final ArchRule no_generic_exceptions = NO_CLASSES_SHOULD_THROW_GENERIC_EXCEPTIONS;

    @ArchTest
    private final ArchRule no_java_util_logging = NO_CLASSES_SHOULD_USE_JAVA_UTIL_LOGGING;




    @ArchTest
    static ArchRule all_classes_that_reside_in_package_internal_should_be_package_private =
            classes().that().resideInAPackage("..internal..")
                    .and().areNotAssignableTo(PayperProvider.class)
                    .and().areNotAssignableTo(JsonProviderImpl.class)
                    .and().areNotAssignableTo(WebProviderImpl.class)
                    .should().bePrivate().orShould().bePackagePrivate();


}
