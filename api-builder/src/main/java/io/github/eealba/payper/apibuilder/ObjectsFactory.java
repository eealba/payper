package io.github.eealba.payper.apibuilder;

import java.io.File;

public class ObjectsFactory {
    private static final String DEFAULT_CODE_PROVIDER = "io.github.eealba.payper.apibuilder.internal.CodeGeneratorFactoryImpl";
    private static final String DEFAULT_OPENAPI_PROVIDER = "io.github.eealba.payper.apibuilder.internal.OpenApiParserFactoryImpl";

    private static final CodeGeneratorFactory CODE_GENERATOR_FACTORY =
            Providers.getProvider(CodeGeneratorFactory.class, DEFAULT_CODE_PROVIDER);
    private static final OpenApiParserFactory OPEN_API_PARSER_FACTORY =
            Providers.getProvider(OpenApiParserFactory.class, DEFAULT_OPENAPI_PROVIDER);

    public static CodeGenerator recordGenerator(ModelDef modelDef) {
        return CODE_GENERATOR_FACTORY.recordGenerator(modelDef);
    }
    public static CodeGenerator recordGenerator(ModelDef modelDef, CodeGeneratorParameter codeGeneratorParameter) {
        return CODE_GENERATOR_FACTORY.recordGenerator(modelDef, codeGeneratorParameter);
    }

    public static CodeGenerator interfaceBuilderGenerator(ModelDef modelDef) {
        return CODE_GENERATOR_FACTORY.interfaceBuilderGenerator(modelDef);
    }

    public static CodeGenerator immutableClassWithBuilderGenerator(ModelDef modelDef,
                                                                   CodeGeneratorParameter codeGeneratorParameter) {
        return CODE_GENERATOR_FACTORY.immutableClassWithBuilderGenerator(modelDef, codeGeneratorParameter);
    }

    public static CodeGenerator interfaceGenerator(ModelDef modelDef) {
        return CODE_GENERATOR_FACTORY.interfaceGenerator(modelDef);
    }
    public static OpenApiParser openApiParser(File openApiSpec, String packageName) {
        return OPEN_API_PARSER_FACTORY.create(openApiSpec, packageName);
    }
    public static OpenApiParser openApiParser(byte[] openApiSpec, String packageName) {
        return OPEN_API_PARSER_FACTORY.create(openApiSpec, packageName);
    }

    public static CodeGenerator enumGenerator(ModelDef modelDef) {
        return CODE_GENERATOR_FACTORY.enumGenerator(modelDef);
    }

    public static CodeGenerator lombokDTOGenerator(ModelDef modelDef) {
        return CODE_GENERATOR_FACTORY.lombokDTOGenerator(modelDef);
    }

    public static CodeGenerator pojoDTOGenerator(ModelDef modelDef, CodeGeneratorParameter codeGeneratorParameter) {
        return CODE_GENERATOR_FACTORY.pojoDTOGenerator(modelDef, codeGeneratorParameter);
    }
}
