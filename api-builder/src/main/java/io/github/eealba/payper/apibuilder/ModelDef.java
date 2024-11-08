package io.github.eealba.payper.apibuilder;

import java.util.List;
import java.util.Objects;

public record ModelDef(ClassDef classDef, List<FieldDef> fieldDefList, String description) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModelDef modelDef = (ModelDef) o;
        return Objects.equals(classDef, modelDef.classDef);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(classDef);
    }

    public boolean isEnum() {
        return fieldDefList.size() == 1 && fieldDefList.get(0).enumValues() != null;
    }
    public boolean isRecord() {
        if (isEnum()){
            return false;
        }
        for (FieldDef f : fieldDefList) {
            if (!f.required()) {
                return false;
            }
        }
        return true;
    }
}
