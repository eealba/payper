package io.github.eealba.payper.apibuilder.internal;

import io.github.eealba.payper.apibuilder.SchemaDef;

import java.math.BigDecimal;
import java.util.List;

class SchemaDefImpl implements SchemaDef {
    private final String type;
    private final Object defaultValue;
    private final String example;
    private final Integer maxLength;
    private final Integer minLength;
    private final BigDecimal maximum;
    private final BigDecimal minimum;
    private final List<?> enumValues;
    private final String pattern;
    private final String format;

    private SchemaDefImpl(Builder builder) {
        this.type = builder.type;
        this.defaultValue = builder.defaultValue;
        this.example = builder.example;
        this.maxLength = builder.maxLength;
        this.minLength = builder.minLength;
        this.maximum = builder.maximum;
        this.minimum = builder.minimum;
        this.enumValues = builder.enumValues;
        this.pattern = builder.pattern;
        this.format = builder.format;
    }

    @Override
    public String type() {
        return type;
    }

    @Override
    public Object defaultValue() {
        return defaultValue;
    }

    @Override
    public String example() {
        return example;
    }

    @Override
    public Integer maxLength() {
        return maxLength;
    }

    @Override
    public Integer minLength() {
        return minLength;
    }

    @Override
    public BigDecimal maximum() {
        return maximum;
    }

    @Override
    public BigDecimal minimum() {
        return minimum;
    }

    @Override
    public List<?> enumValues() {
        return enumValues;
    }

    @Override
    public String pattern() {
        return pattern;
    }

    @Override
    public String format() {
        return format;
    }

    static Builder builder() {
        return new Builder();
    }

    static class Builder {
        private String type;
        private Object defaultValue;
        private String example;
        private Integer maxLength;
        private Integer minLength;
        private BigDecimal maximum;
        private BigDecimal minimum;
        private List<?> enumValues;
        private String pattern;
        private String format;

        private Builder() {}

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder defaultValue(Object defaultValue) {
            this.defaultValue = defaultValue;
            return this;
        }

        public Builder example(String example) {
            this.example = example;
            return this;
        }

        public Builder maxLength(Integer maxLength) {
            this.maxLength = maxLength;
            return this;
        }

        public Builder minLength(Integer minLength) {
            this.minLength = minLength;
            return this;
        }

        public Builder maximum(BigDecimal maximum) {
            this.maximum = maximum;
            return this;
        }

        public Builder minimum(BigDecimal minimum) {
            this.minimum = minimum;
            return this;
        }

        public Builder enumValues(List<?> enumValues) {
            this.enumValues = enumValues;
            return this;
        }

        public Builder pattern(String pattern) {
            this.pattern = pattern;
            return this;
        }

        public Builder format(String format) {
            this.format = format;
            return this;
        }

        public SchemaDefImpl build() {
            return new SchemaDefImpl(this);
        }
    }
}