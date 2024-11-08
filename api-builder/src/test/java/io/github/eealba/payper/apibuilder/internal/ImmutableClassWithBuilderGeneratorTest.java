package io.github.eealba.payper.apibuilder.internal;

import io.github.eealba.payper.apibuilder.ClassDef;
import io.github.eealba.payper.apibuilder.CodeGeneratorParameter;
import io.github.eealba.payper.apibuilder.ModelDef;
import io.github.eealba.payper.apibuilder.ObjectsFactory;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static io.github.eealba.payper.apibuilder.internal.Helper.SAMPLE_1;
import static io.github.eealba.payper.apibuilder.internal.Helper.SAMPLE_3;

class ImmutableClassWithBuilderGeneratorTest {


    @Test
    void should_generate_class_with_interfaces_and_package_private() throws URISyntaxException, IOException {
        var packagePrivate1 = CodeGeneratorParameter.builder()
                .fieldsAccessLevel(CodeGeneratorParameter.AccessLevel.PRIVATE)
                .methodAccessLevel(CodeGeneratorParameter.AccessLevel.PACKAGE_PRIVATE)
                .classAccessLevel(CodeGeneratorParameter.AccessLevel.PACKAGE_PRIVATE)
                .accessorChain(false)
                .accessorFluent(true)
                .build();


        var classDef = new ClassDef("io.github.eealba.paypal.api", "CustomerDTO",
                List.of("Customer"), List.of("CustomerBuilder"));
        var apiGenerator = ObjectsFactory.immutableClassWithBuilderGenerator(new ModelDef(classDef, SAMPLE_1,
                "This is a customer record"), packagePrivate1);

        String generatedCode = apiGenerator.process();

        Helper.verify("should_generate_class_with_interfaces_and_package_private", generatedCode,
                "CustomerDtoWithBuider.java");
    }
    @Test
    void should_generate_class_without_interfaces_and_public() throws URISyntaxException, IOException {
        var classDef = new ClassDef("io.github.eealba.paypal.api", "CustomerDTO");
        var apiGenerator = ObjectsFactory.immutableClassWithBuilderGenerator(new ModelDef(classDef, SAMPLE_1,
                "This is a customer record"), CodeGeneratorParameter.defaultParameter());

        String generatedCode = apiGenerator.process();

        Helper.verify("should_generate_class_without_interfaces_and_public", generatedCode,
                "CustomerDtoWithBuider2.java");
    }

    @Test
    void should_generate_class_with_enum() throws URISyntaxException, IOException {
        var classDef = new ClassDef("io.github.eealba.paypal.api", "CustomerDTO");
        var apiGenerator = ObjectsFactory.immutableClassWithBuilderGenerator(new ModelDef(classDef, SAMPLE_3,
                "This is a customer record"), CodeGeneratorParameter.defaultParameter());

        String generatedCode = apiGenerator.process();

        Helper.verify("should_generate_class_with_enum", generatedCode,
                "CustomerDtoWithBuider3.java");
    }

}