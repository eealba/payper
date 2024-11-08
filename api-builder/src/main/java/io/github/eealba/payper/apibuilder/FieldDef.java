package io.github.eealba.payper.apibuilder;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface FieldDef {
    ClassDef classDef();
    String originalName();
    String name();
    String description();
    boolean required();
    boolean array();
    Object defaultValue();
    String example();
    Integer maxLength();
    Integer minLength();
    BigDecimal maximum();
    BigDecimal minimum();
    List<?> enumValues();
    Optional<String> pattern();
    String format();
    String type();
    String ref();
    ModelDef modelDef();
    default boolean isEnum() {
        return enumValues() != null && !enumValues().isEmpty();
    }
}
