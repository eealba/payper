package io.github.eealba.payper.apibuilder.internal;

import io.github.eealba.payper.apibuilder.CodeGenerator;
import io.github.eealba.payper.apibuilder.CodeGeneratorFactory;
import io.github.eealba.payper.apibuilder.CodeGeneratorParameter;
import io.github.eealba.payper.apibuilder.ModelDef;


public class CodeGeneratorFactoryImpl implements CodeGeneratorFactory {

    @Override
    public CodeGenerator interfaceGenerator(ModelDef modelDef) {
        return new InterfaceGenerator(modelDef);
    }

    @Override
    public CodeGenerator recordGenerator(ModelDef modelDef) {
        return new RecordGenerator(modelDef, CodeGeneratorParameter.defaultParameter());
    }
    @Override
    public CodeGenerator recordGenerator(ModelDef modelDef, CodeGeneratorParameter codeGeneratorParameter) {
        return new RecordGenerator(modelDef, codeGeneratorParameter);
    }

    @Override
    public CodeGenerator interfaceBuilderGenerator(ModelDef modelDef) {
        return new InterfaceBuilderGenerator(modelDef);
    }

    @Override
    public CodeGenerator immutableClassWithBuilderGenerator(ModelDef modelDef,
                                                            CodeGeneratorParameter codeGeneratorParameter) {
        return new ImmutableClassWithBuilderGenerator(modelDef, codeGeneratorParameter);
    }

    @Override
    public CodeGenerator enumGenerator(ModelDef modelDef) {
        return new EnumGenerator(modelDef);
    }

    @Override
    public CodeGenerator lombokDTOGenerator(ModelDef modelDef) {
        return new LombokDTOGenerator(modelDef);
    }

    @Override
    public CodeGenerator pojoDTOGenerator(ModelDef modelDef, CodeGeneratorParameter codeGeneratorParameter) {
        return new PojoDTOGenerator(modelDef, codeGeneratorParameter);
    }
}
