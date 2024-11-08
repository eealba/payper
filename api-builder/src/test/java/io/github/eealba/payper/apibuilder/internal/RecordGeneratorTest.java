package io.github.eealba.payper.apibuilder.internal;

import io.github.eealba.payper.apibuilder.ClassDef;
import io.github.eealba.payper.apibuilder.CodeGeneratorParameter;
import io.github.eealba.payper.apibuilder.ModelDef;
import io.github.eealba.payper.apibuilder.ObjectsFactory;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

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

}