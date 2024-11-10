package io.github.eealba.payper.apibuilder.internal;

import io.github.eealba.payper.apibuilder.CodeGeneratorParameter;
import io.github.eealba.payper.apibuilder.FieldDef;
import io.github.eealba.payper.apibuilder.ModelDef;

import java.util.stream.Collectors;

import static io.github.eealba.payper.apibuilder.Utils.addLinebreaks;

public class PojoDTOGenerator extends AbstractCodeGenerator {
    private static final String CLASS_TEMPLATE = """
                /**
                 * %s
                 */
                %s class %s {
                """;

    private static final String FIELD_TEMPLATE = """
                    /**
                     * %s
                     */
                    %s
                    %s %s %s;
                """;
    private static final String GETTER_TEMPLATE = """
        /**
         * %s
         * @return The current value of %s.
         */
        %s
        %s %s %s() {
            return %s;
        }
    """;
    private static final String SETTER_TEMPLATE = """
        /**
         * %s
         * @param %s New value for %s.
         */
        %s
        %s %s %s(%s %s) {
            %s
        }
    """;
    public PojoDTOGenerator(ModelDef modelDef, CodeGeneratorParameter codeGeneratorParameter) {
        super(modelDef, codeGeneratorParameter);
    }

    @Override
    protected void writeOpenClassCitizens() {
        var res =String.format(CLASS_TEMPLATE, getComments(modelDef.description()),
                codeGeneratorParameter.getClassAccessLevel().toJavaAccessLevel(),
                modelDef.classDef().simpleName());
        sb.append(res);
    }

    @Override
    protected void writeFields() {
        var members = modelDef.fieldDefList().stream().map(this::field).collect(Collectors.joining("\n"));
        sb.append(members);
    }

    private String field(FieldDef fieldDef) {
        return String.format(FIELD_TEMPLATE, getComments(fieldDef.description()),
                getFieldAnnotation(fieldDef, false),
                codeGeneratorParameter.getFieldsAccessLevel().toJavaAccessLevel(),
               getType(fieldDef), fieldDef.name());
    }

    @Override
    protected void writeGetters() {
        var members = modelDef.fieldDefList().stream().map(this::getter).collect(Collectors.joining("\n"));
        sb.append(members);
    }
    private String getter(FieldDef fieldDef){
        String name = fieldDef.name();
        if (codeGeneratorParameter.isAccessorFluent()){
            name = "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
        }
        return String.format(GETTER_TEMPLATE, getComments(fieldDef.description()),
                fieldDef.name(),
                getFieldAnnotation(fieldDef, false),
                codeGeneratorParameter.getMethodAccessLevel().toJavaAccessLevel(),
                getType(fieldDef), name, fieldDef.name());
    }

    private static String getComments(String comments) {
        return addLinebreaks(comments, 110, "\n     * ");
    }

    @Override
    protected void writeSetters() {
        var members = modelDef.fieldDefList().stream().map(this::setter).collect(Collectors.joining("\n"));
        sb.append(members);
    }
    private String setter(FieldDef fieldDef){
        String returnType = codeGeneratorParameter.isAccessorChain() ? modelDef.classDef().simpleName() : "void";
        String code = "this." + fieldDef.name() + " = " + fieldDef.name() + ";";
        if (codeGeneratorParameter.isAccessorChain()){
            code = code + "\n        return this;";
        }
        return String.format(SETTER_TEMPLATE, getComments(fieldDef.description()),
                fieldDef.name(),
                fieldDef.name(),
                getFieldAnnotation(fieldDef, false),
                codeGeneratorParameter.getMethodAccessLevel().toJavaAccessLevel(),
                returnType, fieldDef.name(), getType(fieldDef), fieldDef.name(), code);
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
