package io.github.eealba.payper.apibuilder.internal;

import io.github.eealba.payper.apibuilder.CodeGenerator;
import io.github.eealba.payper.apibuilder.CodeGeneratorParameter;
import io.github.eealba.payper.apibuilder.FieldDef;
import io.github.eealba.payper.apibuilder.ModelDef;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public abstract class AbstractCodeGenerator implements CodeGenerator {
    protected final ModelDef modelDef;
    protected final CodeGeneratorParameter codeGeneratorParameter;
    protected final StringBuilder sb = new StringBuilder(2000);

    @Override
    public String process() {
        writePackage();
        writeGroupSeparator(GroupType.PACKAGE);
        writeImports();
        writeGroupSeparator(GroupType.IMPORT);
        writeOpenClassCitizens();
        writeGroupSeparator(GroupType.OPEN_CLASS_CITIZENS);
        writeFields();
        writeGroupSeparator(GroupType.FIELDS);
        writeConstructors();
        writeGroupSeparator(GroupType.CONTRUCTORS);
        writeGetters();
        writeGroupSeparator(GroupType.GETTERS);
        writeSetters();
        writeGroupSeparator(GroupType.SETTERS);
        writeStaticMethods();
        writeGroupSeparator(GroupType.STATIC_METHODS);
        writeInnerClasses();
        writeGroupSeparator(GroupType.INNER_CLASSES);
        writeCloseClassCitizens();
        writeGroupSeparator(GroupType.CLOSE_CLASS_CITIZENS);
        return sb.toString();
    }

    protected void writePackage() {
        sb.append("package ").append(modelDef.classDef().packageName()).append(';');
    }

    protected void writeGroupSeparator(GroupType groupType){
        //Empty by Default
    }

    protected void writeImports() {
        sb.append(modelDef.fieldDefList()
                .stream()
                .map(this::getImport)
                .flatMap(List::stream)
                .sorted()
                .distinct()
                .collect(Collectors.joining("\n")));
    }

    private List<String> getImport(FieldDef fieldDef) {
        if (fieldDef.classDef().packageName().startsWith("java.lang")) {
            return List.of();
        }
        List<String> imports = new ArrayList<>();
        if (fieldDef.array()) {
            imports.add("import java.util.List;");
        }
        String imp = String.format("import %s;", fieldDef.classDef().name());
        if (!fieldDef.classDef().packageName().equals(modelDef.classDef().packageName())){
            imports.add(imp);
        }
        return imports;
    }

    protected abstract void writeOpenClassCitizens();

    protected void writeFields() {
        // Empty by default
    }

    protected void writeConstructors() {
        // Empty by default
    }

    protected void writeGetters(){
        // Empty by default

    }
    protected void writeSetters(){
        // Empty by default

    }

    protected void writeStaticMethods() {
        // Empty by default
    }

    protected void writeInnerClasses() {
        // Empty by default
    }

    protected void writeCloseClassCitizens() {
        sb.append('}');
    }
    protected String getType(FieldDef fieldDef) {
        if (fieldDef.array()) {
            return "List<" + fieldDef.classDef().simpleName() + ">";
        }
        return fieldDef.classDef().simpleName();
    }
    protected String getFieldAnnotation(FieldDef fieldDef, boolean addOverride) {
        StringBuilder sb = new StringBuilder();
        if (addOverride) {
            sb.append("@Override");
        }
        if (codeGeneratorParameter.isUseJasonerProperty() && !fieldDef.originalName().equals(fieldDef.name())) {
            if(!sb.isEmpty()){
                sb.append(' ');
            }
            sb.append("@JasonerProperty(\"").append(fieldDef.originalName()).append("\")");

        }
        return sb.toString();
    }


}
