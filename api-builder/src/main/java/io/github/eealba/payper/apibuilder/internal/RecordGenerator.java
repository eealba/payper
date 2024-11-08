package io.github.eealba.payper.apibuilder.internal;

import io.github.eealba.payper.apibuilder.CodeGeneratorParameter;
import io.github.eealba.payper.apibuilder.FieldDef;
import io.github.eealba.payper.apibuilder.ModelDef;
import io.github.eealba.payper.apibuilder.Utils;

import java.util.stream.Collectors;

public class RecordGenerator extends AbstractCodeGenerator {
    private static final String MEMBER_TEMPLATE = "%s %s";
    private static final String MEMBER_TEMPLATE_NOT_NULL =
            """
                    if (%s == null) {
                        throw new IllegalArgumentException("Field %s can`t be null");
                    }
            """;
    private static final String MEMBER_TEMPLATE_WITH_PATTERN =
            """
                    if (!%s.matches("%s")) {
                        throw new IllegalArgumentException("Invalid pattern for field %s");
                    }
            """;

    public RecordGenerator(ModelDef modelDef, CodeGeneratorParameter codeGeneratorParameter) {
        super(modelDef, codeGeneratorParameter);
    }

    @Override
    protected void writeOpenClassCitizens() {
        var description = Utils.addLinebreaks(modelDef.description(), 120);
        sb.append("/**\n * ").append(description).append("\n */\n");
        if (codeGeneratorParameter.getClassAccessLevel() == CodeGeneratorParameter.AccessLevel.PUBLIC) {
            sb.append("public ");
        }
        sb.append("record ").append(modelDef.classDef().simpleName()).append("(");
    }

    @Override
    protected void writeGetters() {
        var members = modelDef.fieldDefList().stream().map(this::getter).collect(Collectors.joining(", "));
        sb.append(members).append(")");
        if (!modelDef.classDef().interfaces().isEmpty()){
            sb.append(" implements ");
            sb.append(String.join(", ", modelDef.classDef().interfaces()));
        }
        sb.append(" {");
        writeConstructorWithPattern();
    }

    private String getter(FieldDef fieldDef) {
        return String.format(MEMBER_TEMPLATE, getType(fieldDef), fieldDef.name());
    }
    protected void writeGroupSeparator(GroupType groupType){
        if (groupType == GroupType.PACKAGE || groupType == GroupType.IMPORT) {
            sb.append('\n').append('\n');
        }
        if ( groupType == GroupType.CLOSE_CLASS_CITIZENS || groupType == GroupType.GETTERS){
            sb.append('\n');
        }
    }
    public void writeConstructorWithPattern() {
        var fieldsWithPattern = modelDef.fieldDefList().stream().filter(fieldDef -> fieldDef.pattern().isPresent())
                .toList();

            sb.append("\n\n    ");
            if (codeGeneratorParameter.getClassAccessLevel() == CodeGeneratorParameter.AccessLevel.PUBLIC) {
                sb.append("public ");
            }

            sb.append(modelDef.classDef().simpleName()).append("(");
            sb.append(modelDef.fieldDefList().stream()
                    .map(fieldDef -> String.format("%s %s", getType(fieldDef), fieldDef.name()))
                    .collect(Collectors.joining(", ")));
            sb.append(") {\n");

        modelDef.fieldDefList().forEach(fieldDef -> {
            String validation = String.format(MEMBER_TEMPLATE_NOT_NULL, fieldDef.name(), fieldDef.name());
            sb.append(validation);
        });

            fieldsWithPattern.forEach(fieldDef -> {
                String escapedPattern = escapePattern(fieldDef.pattern().orElseThrow());
                String validation = String.format(MEMBER_TEMPLATE_WITH_PATTERN, fieldDef.name(),
                        escapedPattern, fieldDef.name());
                sb.append(validation);
            });

            modelDef.fieldDefList().forEach(fieldDef -> {
                sb.append("        this.").append(fieldDef.name()).append(" = ").append(fieldDef.name()).append(";\n");
            });

            sb.append("    }\n");
    }

    private String escapePattern(String pattern) {

        return pattern.replace("\\", "\\\\")
                .replace("\"", "\\\"");
               // .replace("-", "\\-");


    }
    @Override
    protected void writeImports() {
        super.writeImports();
        if (modelDef.classDef().packageName().endsWith("internal")) {
            var packageName = modelDef.classDef().packageName()
                    .substring(0, modelDef.classDef().packageName().lastIndexOf('.'));
            sb.append('\n');
            sb.append("import ").append(packageName).append('.')
                    .append(modelDef.classDef().interfaces().get(0)).append(";");
        }
    }

}
