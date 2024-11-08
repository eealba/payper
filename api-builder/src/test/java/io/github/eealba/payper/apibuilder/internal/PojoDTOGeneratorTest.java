package io.github.eealba.payper.apibuilder.internal;

import io.github.eealba.payper.apibuilder.ClassDef;
import io.github.eealba.payper.apibuilder.CodeGeneratorParameter;
import io.github.eealba.payper.apibuilder.ModelDef;
import io.github.eealba.payper.apibuilder.ObjectsFactory;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static io.github.eealba.payper.apibuilder.internal.Helper.SAMPLE_1;

class PojoDTOGeneratorTest {

    @Test
    void should_return_class_pojo_bean() throws URISyntaxException, IOException {

        var modelDef = modelDef();
        var generatedCode = ObjectsFactory.pojoDTOGenerator(modelDef,
                        CodeGeneratorParameter.builder()
                                .classAccessLevel(CodeGeneratorParameter.AccessLevel.PUBLIC)
                                .methodAccessLevel(CodeGeneratorParameter.AccessLevel.PUBLIC)
                                .fieldsAccessLevel(CodeGeneratorParameter.AccessLevel.PRIVATE)
                                .accessorChain(false)
                                .accessorFluent(false)
                                .build())
                .process();

        Helper.verify("should_return_class_pojo_bean", generatedCode,"CustomerPojoBean.java");

    }


    @Test
    void should_return_class_pojo_fluent() throws URISyntaxException, IOException {
        var modelDef = modelDef();
        var generatedCode = ObjectsFactory.pojoDTOGenerator(modelDef,CodeGeneratorParameter.defaultParameter())
                .process();

        Helper.verify("should_return_class_pojo_fluent", generatedCode,"CustomerPojoFluent.java");
    }

    private static ModelDef modelDef() {
        var classDef = new ClassDef("io.github.eealba.paypal.api", "Customer");
        return new ModelDef(classDef, SAMPLE_1,"This is a customer Pojo");
    }

}
