package io.github.eealba.payper.apibuilder.internal;

import io.github.eealba.payper.apibuilder.ClassDef;
import io.github.eealba.payper.apibuilder.CodeGeneratorParameter;
import io.github.eealba.payper.apibuilder.ModelDef;
import io.github.eealba.payper.apibuilder.ObjectsFactory;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static io.github.eealba.payper.apibuilder.internal.Helper.SAMPLE_3;

class RecordGeneratorTest {

    @Test
    void should_generate_record_package_private() throws URISyntaxException, IOException {
        var classDef = new ClassDef("io.github.eealba.paypal.api", "CustomerRec");
        var modelDef = new ModelDef(classDef, Helper.SAMPLE_1,"This is a customer record");
        var codeGeneratorParameter = CodeGeneratorParameter.builder()
                .classAccessLevel(CodeGeneratorParameter.AccessLevel.PACKAGE_PRIVATE)
                .build();
        var apiGenerator = ObjectsFactory.recordGenerator(modelDef, codeGeneratorParameter);
        var generatedCode = apiGenerator.process();

        Helper.verify("should_generate_record_package_private", generatedCode,"CustomerRec.java");
    }

    @Test
    void should_generate_public_record() throws URISyntaxException, IOException {
        var classDef = new ClassDef("io.github.eealba.paypal.api", "CustomerRec");
        var modelDef = new ModelDef(classDef, Helper.SAMPLE_1,"This is a customer record");
        var codeGeneratorParameter = CodeGeneratorParameter.defaultParameter();
        var apiGenerator = ObjectsFactory.recordGenerator(modelDef, codeGeneratorParameter);
        var generatedCode = apiGenerator.process();

        Helper.verify("should_generate_public_record", generatedCode,"CustomerRecPublic.java");
    }
    @Test
    void should_generate_public_record_with_enum() throws URISyntaxException, IOException {
        var classDef = new ClassDef("io.github.eealba.paypal.api", "CustomerRec");
        var modelDef = new ModelDef(classDef, Helper.SAMPLE_3,"This is a customer record");
        var codeGeneratorParameter = CodeGeneratorParameter.defaultParameter();
        var apiGenerator = ObjectsFactory.recordGenerator(modelDef, codeGeneratorParameter);
        var generatedCode = apiGenerator.process();

        Helper.verify("should_generate_public_record_with_enum", generatedCode,"CustomerRecPublic3.java");
    }
    @Test
    void should_generate_public_record_with_JasonerProperty() throws URISyntaxException, IOException {
        var classDef = new ClassDef("io.github.eealba.paypal.api", "CustomerRec");
        var modelDef = new ModelDef(classDef, Helper.SAMPLE_1,"This is a customer record");
        var codeGeneratorParameter = CodeGeneratorParameter.defaultParameter();
        var apiGenerator = ObjectsFactory.recordGenerator(modelDef, codeGeneratorParameter);
        var generatedCode = apiGenerator.process();

        Helper.verify("should_generate_public_record", generatedCode,"CustomerRecPublic2.java");
    }

}