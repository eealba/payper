package io.github.eealba.payper.apibuilder.internal;

import io.github.eealba.payper.apibuilder.CodeGeneratorParameter;
import io.github.eealba.payper.apibuilder.FieldDef;
import io.github.eealba.payper.apibuilder.ModelDef;

import java.util.stream.Collectors;

import static io.github.eealba.payper.apibuilder.Utils.addLinebreaks;

public class InterfaceGenerator extends AbstractCodeGenerator {
    private static final String MEMBER_TEMPLATE = """
                    /**
                     * %s
                     */
                    %s %s();
                """;
    private static final String CLASS_TEMPLATE = """
                /**
                 * %s
                 */
                public interface %s {
                """;

    public InterfaceGenerator(ModelDef modelDef) {
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
        var members = modelDef.fieldDefList().stream().map(this::getter).collect(Collectors.joining("\n"));
        sb.append(members);
    }

    private String getter(FieldDef fieldDef) {
        return String.format(MEMBER_TEMPLATE, addLinebreaks(fieldDef.description(), 110),
                getType(fieldDef), fieldDef.name());
    }

    @Override
    protected void writeGroupSeparator(GroupType groupType) {
        if (groupType == GroupType.PACKAGE || groupType == GroupType.IMPORT) {
            sb.append('\n').append('\n');
        }
        if (groupType == GroupType.OPEN_CLASS_CITIZENS || groupType == GroupType.CLOSE_CLASS_CITIZENS){
            sb.append('\n');
        }
    }

}
