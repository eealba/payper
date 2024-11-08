package io.github.eealba.payper.apibuilder;


public interface CodeGeneratorFactory {
    CodeGenerator interfaceGenerator(ModelDef modelDef);

    CodeGenerator recordGenerator(ModelDef modelDef);

    CodeGenerator recordGenerator(ModelDef modelDef, CodeGeneratorParameter codeGeneratorParameter);

    CodeGenerator interfaceBuilderGenerator(ModelDef modelDef);

    CodeGenerator immutableClassWithBuilderGenerator(ModelDef modelDef, CodeGeneratorParameter codeGeneratorParameter);

    CodeGenerator enumGenerator(ModelDef modelDef);

    CodeGenerator lombokDTOGenerator(ModelDef modelDef);

    CodeGenerator pojoDTOGenerator(ModelDef modelDef, CodeGeneratorParameter codeGeneratorParameter);

}
