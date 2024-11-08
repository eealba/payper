package io.github.eealba.payper.apibuilder.internal;

import io.github.eealba.payper.apibuilder.CodeGeneratorParameter;
import io.github.eealba.payper.apibuilder.FieldDef;
import io.github.eealba.payper.apibuilder.ModelDef;

import java.util.stream.Collectors;

public class InterfaceBuilderGenerator extends AbstractCodeGenerator {
    private static final String SETTER_TEMPLATE = """
         /**
          * %s
          */
         public %s %s(%s value);
     """;
    private static final String BUILD_TEMPLATE = """
        /**
         * %s
         */
        public %s build();
    """;

    public InterfaceBuilderGenerator(ModelDef modelDef) {
        super(modelDef, CodeGeneratorParameter.defaultParameter());
    }

    @Override
    protected void writeOpenClassCitizens() {
        sb.append("public interface ").append(modelDef.classDef().simpleName()).append(" {");
    }

    @Override
    protected void writeSetters() {
        var settersArray = modelDef.fieldDefList().stream().map(this::setter).collect(Collectors.toList());

        var name = modelDef.classDef().simpleName().substring(0, modelDef.classDef().simpleName().indexOf("Builder"));

        var builder = String.format(BUILD_TEMPLATE, "The " + name + " instance", name);
        settersArray.add(builder);

        var setters = String.join("\n", settersArray);

        sb.append(setters);
    }

    private String setter(FieldDef fieldDef) {
        return String.format(SETTER_TEMPLATE, fieldDef.description(), modelDef.classDef().simpleName(),
                fieldDef.name(), getType(fieldDef));
    }

    @Override
    protected void writeGroupSeparator(GroupType groupType) {
        if (groupType == GroupType.PACKAGE || groupType == GroupType.IMPORT
            ||groupType == GroupType.OPEN_CLASS_CITIZENS) {
            sb.append('\n').append('\n');
        }
        if (groupType == GroupType.CLOSE_CLASS_CITIZENS  || groupType == GroupType.SETTERS ){
            sb.append('\n');
        }
    }
}
