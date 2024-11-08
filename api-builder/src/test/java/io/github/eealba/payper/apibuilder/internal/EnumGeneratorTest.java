package io.github.eealba.payper.apibuilder.internal;

import io.github.eealba.payper.apibuilder.ClassDef;
import io.github.eealba.payper.apibuilder.ModelDef;
import io.github.eealba.payper.apibuilder.ObjectsFactory;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

class EnumGeneratorTest {

    @Test
    void should_generate_enum() throws URISyntaxException, IOException {
        var classDef = new ClassDef("io.github.eealba.paypal.api", "CardBrand");
        var apiGenerator = ObjectsFactory.enumGenerator(new ModelDef(classDef, Helper.SAMPLE_2,
                "This is a card brand"));
        var generatedCode = apiGenerator.process();

        Helper.verify("should_generate_enum", generatedCode,"CardBrand.java");
    }

}