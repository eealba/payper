package io.github.eealba.payper.apibuilder.internal;

import io.github.eealba.payper.apibuilder.CodeGenerator;
import io.github.eealba.payper.apibuilder.CodeGeneratorParameter;
import io.github.eealba.payper.apibuilder.FieldDef;
import io.github.eealba.payper.apibuilder.ModelDef;
import io.github.eealba.payper.apibuilder.ObjectsFactory;
import io.github.eealba.payper.apibuilder.Utils;

import java.util.Objects;
import java.util.stream.Collectors;

public class RecordGenerator extends AbstractCodeGenerator {
    private static final String MEMBER_TEMPLATE = "%s %s %s";
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
        int maxLineLength = codeGeneratorParameter.getMaxLineLength() > 0 ?
                codeGeneratorParameter.getMaxLineLength() : 120;
        var description = Utils.addLinebreaks(modelDef.description(), maxLineLength);
        sb.append("/**\n * ").append(description).append("\n */\n");
        if (codeGeneratorParameter.getClassAccessLevel() == CodeGeneratorParameter.AccessLevel.PUBLIC) {
            sb.append("public ");
        }
        sb.append("record ").append(modelDef.classDef().simpleName()).append("(");
    }

    @Override
    protected void writeGetters() {
        int maxLineLength = codeGeneratorParameter.getMaxLineLength() > 0 ?
                codeGeneratorParameter.getMaxLineLength() : 120;

        var members = Utils.addLinebreaks(modelDef.fieldDefList().stream()
                .map(this::getter)
                .collect(Collectors.joining(", ")), maxLineLength);
        sb.append(members).append(")");
        if (!modelDef.classDef().interfaces().isEmpty()) {
            sb.append(" implements ");
            sb.append(String.join(", ", modelDef.classDef().interfaces()));
        }
        sb.append(" {");
        writeConstructorWithPattern();
    }

    private String getter(FieldDef fieldDef) {
        String annotation = getFieldAnnotation(fieldDef, false);
        return String.format(MEMBER_TEMPLATE, annotation, getType(fieldDef), fieldDef.name());
    }

    protected void writeGroupSeparator(GroupType groupType) {
        if (groupType == GroupType.PACKAGE || groupType == GroupType.IMPORT) {
            sb.append('\n').append('\n');
        }
        if (groupType == GroupType.CLOSE_CLASS_CITIZENS || groupType == GroupType.GETTERS) {
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
        String constructorParams = modelDef.fieldDefList().stream()
                .map(fieldDef -> String.format("%s %s", getType(fieldDef), fieldDef.name()))
                .collect(Collectors.joining(", "));
        int maxLineLength = codeGeneratorParameter.getMaxLineLength() > 0 ?
                codeGeneratorParameter.getMaxLineLength() : 120;


        sb.append(Utils.addLinebreaks(constructorParams, maxLineLength));

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
        boolean jasonerProperty = modelDef.fieldDefList().stream().anyMatch(f -> !f.originalName().equals(f.name()));
        if (jasonerProperty) {
            sb.append('\n');
            sb.append("import io.github.eealba.jasoner.JasonerProperty;");
        }
    }


    @Override
    protected void writeInnerClasses() {

        writeInnerEnums();
        writeInnerDTOs();

    }

    private void writeInnerEnums() {
        var fieldsEnums = modelDef.fieldDefList().stream().filter(FieldDef::isEnum).map(this::fieldEnum)
                .collect(Collectors.joining("\n"));
        sb.append(fieldsEnums);
    }

    private String fieldEnum(FieldDef fieldDef) {
        var sb = new StringBuilder();
        sb.append("    /**").append('\n');
        sb.append("     * ").append(fieldDef.description()).append('\n');
        sb.append("     */").append('\n');
        sb.append("    public enum ").append(fieldDef.classDef().simpleName()).append(" {").append('\n');
        var values = fieldDef.enumValues().stream().map(v -> "        " + v).collect(Collectors.joining(",\n"));
        sb.append(values).append('\n');
        sb.append("    }");
        return sb.toString();

    }

    private void writeInnerDTOs() {
        var fieldsEnums = modelDef.fieldDefList().stream().map(FieldDef::modelDef)
                .filter(Objects::nonNull).map(this::innerDTO)
                .collect(Collectors.joining("\n"));
        sb.append(fieldsEnums);
    }

    private String innerDTO(ModelDef modelDef) {
        CodeGenerator codeGenerator = modelDef.isRecord() ?
                ObjectsFactory.recordGenerator(modelDef, codeGeneratorParameter) :
                ObjectsFactory.immutableClassWithBuilderGenerator(modelDef, codeGeneratorParameter);
        var result = codeGenerator.process();
        var pos = result.indexOf(" class");
        return "public static" + result.substring(pos);

    }

}
