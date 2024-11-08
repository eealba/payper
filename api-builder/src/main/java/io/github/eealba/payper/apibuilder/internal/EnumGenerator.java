package io.github.eealba.payper.apibuilder.internal;

import io.github.eealba.payper.apibuilder.CodeGeneratorParameter;
import io.github.eealba.payper.apibuilder.ModelDef;

import java.util.stream.Collectors;

import static io.github.eealba.payper.apibuilder.Utils.addLinebreaks;

public class EnumGenerator extends AbstractCodeGenerator {
    private static final String CLASS_TEMPLATE = """
                /**
                 * %s
                 */
                public enum %s {
                """;

    public EnumGenerator(ModelDef modelDef) {
        super(modelDef, CodeGeneratorParameter.defaultParameter());
    }
    @Override
    protected void writeOpenClassCitizens() {
        var res =String.format(CLASS_TEMPLATE, addLinebreaks(modelDef.description(),120),
                modelDef.classDef().simpleName());
        sb.append(res);
    }
    @Override
    protected void writeGetters() {
        var members = modelDef.fieldDefList().get(0).enumValues().stream()
                .map(Object::toString).map(String::toUpperCase).collect(Collectors.joining(",\n    "));
        sb.append("    ").append(members);
    }
    @Override
    protected void writeGroupSeparator(GroupType groupType){
        if (groupType == GroupType.PACKAGE
                || groupType == GroupType.GETTERS
                || groupType == GroupType.CLOSE_CLASS_CITIZENS) {
            sb.append('\n');
        }
    }


}
