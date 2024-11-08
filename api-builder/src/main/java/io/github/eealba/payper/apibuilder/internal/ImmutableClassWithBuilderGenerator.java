package io.github.eealba.payper.apibuilder.internal;

import io.github.eealba.payper.apibuilder.CodeGenerator;
import io.github.eealba.payper.apibuilder.CodeGeneratorParameter;
import io.github.eealba.payper.apibuilder.FieldDef;
import io.github.eealba.payper.apibuilder.ModelDef;
import io.github.eealba.payper.apibuilder.ObjectsFactory;

import java.util.Objects;
import java.util.stream.Collectors;

public class ImmutableClassWithBuilderGenerator extends AbstractCodeGenerator{
    private static final String FIELD_TEMPLATE = "    private final %s %s;";
    private static final String FIELD_BUILDER_TEMPLATE = "        private %s %s;";

    private static final String ASSIGN_FIELD_TEMPLATE = "        %s = builder.%s;";
    private static final String ASSIGN_REQUIRED_FIELD_TEMPLATE = "        %s = Objects.requireNonNull(builder.%s);";
    private static final String GETTER_TEMPLATE_OVERRIDE = """
        @Override
        public %s %s() {
            return %s;
        }
    """;
    private static final String GETTER_TEMPLATE_NORMAL = """
        public %s %s() {
            return %s;
        }
    """;
    private static final String BUILD_TEMPLATE_OVERRIDE = """
            @Override
            public %s build() {
                return new %s(this);
            }
    """;
    private static final String BUILD_TEMPLATE_NORMAL = """
            public %s build() {
                return new %s(this);
            }
    """;
    private static final String SETTER_TEMPLATE_OVERRIDE = """
            @Override
            public Builder %s(%s value) {
                %s = value;
                return this;
            }
    """;
    private static final String SETTER_TEMPLATE_NORMAL = """
            public Builder %s(%s value) {
                %s = value;
                return this;
            }
    """;

    private static final String CLASS_BUILDER_INTERFACE = """
                        private static class Builder implements %s {
                    
                    %s
                    
                    %s
                        }
                    """;
    private static final String CLASS_BUILDER_NORMAL = """
                        public static class Builder {
                    
                    %s
                    
                    %s
                        }
                    """;

    public ImmutableClassWithBuilderGenerator(ModelDef modelDef, CodeGeneratorParameter codeGeneratorParameter) {
        super(modelDef, codeGeneratorParameter);
    }
    @Override
    protected void writeOpenClassCitizens() {
        String accessLevel = codeGeneratorParameter.getClassAccessLevel().toJavaAccessLevel();
        if (!accessLevel.isEmpty()){
            sb.append(codeGeneratorParameter.getClassAccessLevel().toJavaAccessLevel()).append(" ");
        }
        sb.append("class ").append(modelDef.classDef().simpleName());
        if (!modelDef.classDef().interfaces().isEmpty()){
            sb.append(" implements ");
            sb.append(String.join(", ", modelDef.classDef().interfaces()));
        }
        sb.append(" {");
    }

    @Override
    protected void writeFields() {
        var members = modelDef.fieldDefList().stream().map(this::field).collect(Collectors.joining("\n"));
        sb.append(members);
    }

    private String field(FieldDef fieldDef) {
        return String.format(FIELD_TEMPLATE, getType(fieldDef), fieldDef.name());
    }

    @Override
    protected void writeConstructors() {
        sb.append("    private ").append(modelDef.classDef().simpleName()).append("(Builder builder) {").append('\n');
        var optionalFields = modelDef.fieldDefList().stream().filter(fieldDef -> !fieldDef.required())
                .map(this::moveField).collect(Collectors.joining("\n"));
        sb.append(optionalFields).append('\n');
        var requiredFields = modelDef.fieldDefList().stream().filter(FieldDef::required)
                .map(this::moveRequiredField).collect(Collectors.joining("\n"));
        sb.append(requiredFields).append('\n');
        sb.append("    }");
    }

    private String moveField(FieldDef fieldDef) {
        return String.format(ASSIGN_FIELD_TEMPLATE, fieldDef.name(), fieldDef.name());
    }

    private String moveRequiredField(FieldDef fieldDef) {
        return String.format(ASSIGN_REQUIRED_FIELD_TEMPLATE, fieldDef.name(), fieldDef.name());
    }

    @Override
    protected void writeGetters() {
        var members = modelDef.fieldDefList().stream().map(this::getter).collect(Collectors.joining("\n"));
        sb.append(members);
    }

    private String getter(FieldDef fieldDef) {
        return String.format(modelDef.classDef().interfaces().isEmpty()
                        ? GETTER_TEMPLATE_NORMAL: GETTER_TEMPLATE_OVERRIDE,
                getType(fieldDef),
                fieldDef.name(), fieldDef.name());
    }

    @Override
    protected void writeStaticMethods() {
        var builderName = "Builder";
        if(!modelDef.classDef().builderInterfaces().isEmpty()){
            builderName = modelDef.classDef().builderInterfaces().get(0);
        }
        String accessLevel = codeGeneratorParameter.getClassAccessLevel().toJavaAccessLevel();
        String modifier = "static";
        if (!accessLevel.isEmpty()){
            modifier = accessLevel + " "+ modifier;
        }
        sb.append(String.format("""
                        %s %s builder() {
                            return new Builder();
                        }
                    """, modifier, builderName));
    }



    @Override
    protected void writeInnerClasses() {

        writeInnerBuilder();
        writeInnerEnums();
        writeInnerDTOs();

    }

    private void writeInnerBuilder() {
        var fieldsBuilder = modelDef.fieldDefList().stream().map(this::fieldBuilder)
                .collect(Collectors.joining("\n"));

        var settersArray = modelDef.fieldDefList().stream().map(this::setter).collect(Collectors.toList());

        String builder;
        if (modelDef.classDef().interfaces().isEmpty()){
            var name = modelDef.classDef().simpleName();
            builder = String.format(BUILD_TEMPLATE_NORMAL, name, modelDef.classDef().simpleName());
        }else{
            var name = modelDef.classDef().interfaces().get(0);
            builder = String.format(BUILD_TEMPLATE_OVERRIDE, name, modelDef.classDef().simpleName());
        }
        settersArray.add(builder);

        var setters = String.join("\n", settersArray);

        if (modelDef.classDef().builderInterfaces().isEmpty()){
            sb.append(String.format(CLASS_BUILDER_NORMAL, fieldsBuilder, setters));
        }else{
            var impls = String.join(", ", modelDef.classDef().builderInterfaces());
            sb.append(String.format(CLASS_BUILDER_INTERFACE, impls, fieldsBuilder, setters));

        }
    }

    private String fieldBuilder(FieldDef fieldDef) {
        return String.format(FIELD_BUILDER_TEMPLATE, getType(fieldDef), fieldDef.name());
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
        var result =  codeGenerator.process();
        var pos = result.indexOf(" class");
        return "public static" + result.substring(pos);

    }

    private String setter(FieldDef fieldDef) {
        return String.format(modelDef.classDef().interfaces().isEmpty()
                        ? SETTER_TEMPLATE_NORMAL: SETTER_TEMPLATE_OVERRIDE,
                fieldDef.name(),
                getType(fieldDef),
                fieldDef.name());
    }
    @Override
    protected void writeImports() {
        super.writeImports();
        if (modelDef.fieldDefList().stream().anyMatch(FieldDef::required)) {
            sb.append('\n');
            sb.append("import java.util.Objects;");
        }
//        sb.append('\n');
//        sb.append("import io.github.eealba.paypal.core.annotation.Field;");

        if (modelDef.classDef().packageName().endsWith("internal")) {
            var packageName = modelDef.classDef().packageName()
                    .substring(0, modelDef.classDef().packageName().lastIndexOf('.'));
            sb.append('\n');
            sb.append("import ").append(packageName).append('.')
                    .append(modelDef.classDef().builderInterfaces().get(0)).append(";");
            sb.append('\n');
            sb.append("import ").append(packageName).append('.')
                    .append(modelDef.classDef().interfaces().get(0)).append(";");
        }
    }
    @Override
    protected void writeGroupSeparator(GroupType groupType){
        if (groupType == GroupType.PACKAGE
            || groupType == GroupType.IMPORT
            || groupType == GroupType.CONTRUCTORS
            || groupType == GroupType.FIELDS
            || groupType == GroupType.OPEN_CLASS_CITIZENS
            || groupType == GroupType.CLOSE_CLASS_CITIZENS) {
                sb.append('\n').append('\n');
        }
        if (groupType == GroupType.GETTERS
                || groupType == GroupType.STATIC_METHODS
                || groupType == GroupType.INNER_CLASSES) {
                sb.append('\n');
        }
    }


}
