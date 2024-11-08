package io.github.eealba.payper.apibuilder.internal;

import io.github.eealba.payper.apibuilder.ClassDef;
import io.github.eealba.payper.apibuilder.ModelDef;
import io.github.eealba.payper.apibuilder.ObjectsFactory;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

class InterfaceBuilderGeneretorTest {


    @Test
    void should_generate_interface_builder() throws URISyntaxException, IOException {
        var classDef = new ClassDef("io.github.eealba.paypal.api", "CustomerBuilder");
        var apiGenerator = ObjectsFactory.interfaceBuilderGenerator(new ModelDef(classDef, Helper.SAMPLE_1,
                "This is a customer record"));

        String generatedCode = apiGenerator.process();

        Helper.verify("should_generate_interface_builder", generatedCode,"InterfaceBuider.java");
    }

}