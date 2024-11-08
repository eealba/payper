package io.github.eealba.payper.apibuilder.internal;

import io.github.eealba.payper.apibuilder.CodeGeneratorParameter;
import io.github.eealba.payper.apibuilder.FieldDef;
import io.github.eealba.payper.apibuilder.ModelDef;

import java.util.stream.Collectors;

import static io.github.eealba.payper.apibuilder.Utils.addLinebreaks;

public class LombokDTOGenerator extends AbstractCodeGenerator {
    private static final String CLASS_TEMPLATE = """
                /**
                 * %s
                 */
                @Accessors(chain = true, fluent = true)
                @Data
                public class %s {
                """;

    private static final String FIELD_TEMPLATE = """
                    /**
                     * %s
                     *
                     * @param %s New value for %s.
                     * @return The current value of %s.
                     */
                    private %s %s;
                """;

    public LombokDTOGenerator(ModelDef modelDef) {
        super(modelDef, CodeGeneratorParameter.defaultParameter());
    }

    @Override
    protected void writeOpenClassCitizens() {
        var res =String.format(CLASS_TEMPLATE, addLinebreaks(modelDef.description(),120),
                modelDef.classDef().simpleName());
        sb.append(res);
    }

    @Override
    protected void writeFields() {
        var members = modelDef.fieldDefList().stream().map(this::field).collect(Collectors.joining("\n"));
        sb.append(members);
    }

    private String field(FieldDef fieldDef) {
        return String.format(FIELD_TEMPLATE, fieldDef.description(),
                fieldDef.name(),
                fieldDef.name(),
                fieldDef.name(),
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

    @Override
    protected void writeImports() {
        super.writeImports();
        sb.append("import lombok.Data;\n");
        sb.append("import lombok.experimental.Accessors;\n");
    }
}
