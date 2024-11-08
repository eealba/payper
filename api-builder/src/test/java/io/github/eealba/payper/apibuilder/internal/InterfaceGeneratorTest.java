package io.github.eealba.payper.apibuilder.internal;

import io.github.eealba.payper.apibuilder.ClassDef;
import io.github.eealba.payper.apibuilder.ModelDef;
import io.github.eealba.payper.apibuilder.ObjectsFactory;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static io.github.eealba.payper.apibuilder.internal.Helper.SAMPLE_1;

class InterfaceGeneratorTest {

    @Test
    void should_generate_interface() throws URISyntaxException, IOException {
        var classDef = new ClassDef("io.github.eealba.paypal.api", "Customer");
        var apiGenerator = ObjectsFactory.interfaceGenerator(new ModelDef(classDef, SAMPLE_1,
                "This is a customer record"));
        var generatedCode = apiGenerator.process();

        Helper.verify("should_generate_interface", generatedCode,"CustomerInterface.java");
    }

}
