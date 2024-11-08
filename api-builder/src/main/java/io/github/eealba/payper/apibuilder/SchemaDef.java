package io.github.eealba.payper.apibuilder;

import java.math.BigDecimal;
import java.util.List;

public interface SchemaDef {
    String type();
    Object defaultValue();
    String example();
    Integer maxLength();
    Integer minLength();
    BigDecimal maximum();
    BigDecimal minimum();
    List<?> enumValues();
    String pattern();
    String format();
}
