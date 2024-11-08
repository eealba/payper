package io.github.eealba.payper.apibuilder.internal;

import io.github.eealba.payper.apibuilder.ClassDef;
import io.github.eealba.payper.apibuilder.FieldDef;
import io.github.eealba.payper.apibuilder.ModelDef;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

class FieldDefImpl implements FieldDef {
    private final ClassDef classDef;
    private final String originalName;
    private final String name;
    private final String description;
    private final boolean required;
    private final boolean array;
    private final Object defaultValue;
    private final String example;
    private final Integer maxLength;
    private final Integer minLength;
    private final BigDecimal maximum;
    private final BigDecimal minimum;
    private final List<?> enumValues;
    private final String pattern;
    private final String format;
    private final String type;
    private final String ref;
    private final ModelDef modelDef;

    private FieldDefImpl(Builder builder) {
        this.classDef = builder.classDef;
        this.originalName = builder.originalName;
        this.name = builder.name;
        this.description = builder.description;
        this.required = builder.required;
        this.array = builder.array;
        this.defaultValue = builder.defaultValue;
        this.example = builder.example;
        this.maxLength = builder.maxLength;
        this.minLength = builder.minLength;
        this.maximum = builder.maximum;
        this.minimum = builder.minimum;
        this.enumValues = builder.enumValues;
        this.pattern = builder.pattern;
        this.format = builder.format;
        this.type = builder.type;
        this.ref = builder.ref;
        this.modelDef = builder.modelDef;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FieldDefImpl fieldDef = (FieldDefImpl) o;
        return Objects.equals(classDef, fieldDef.classDef) && Objects.equals(name, fieldDef.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(classDef, name);
    }

    @Override
    public ClassDef classDef() {
        return classDef;
    }
    @Override
    public String originalName() {
        return originalName;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String description() {
        return description;
    }

    @Override
    public boolean required() {
        return required;
    }

    @Override
    public boolean array() {
        return array;
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
    public Optional<String> pattern() {
        return Optional.ofNullable(pattern).filter(s -> !s.isBlank());
    }

    @Override
    public String format() {
        return format;
    }

    @Override
    public String type() {
        return type;
    }
    @Override
    public String ref() {
        return ref;
    }

    @Override
    public ModelDef modelDef() {
        return modelDef;
    }

    static Builder builder() {
        return new Builder();
    }

    static class Builder {
        private ClassDef classDef;
        private String originalName;
        private String name;
        private String description;
        private boolean required;
        private boolean array;
        private Object defaultValue;
        private String example;
        private Integer maxLength;
        private Integer minLength;
        private BigDecimal maximum;
        private BigDecimal minimum;
        private List<?> enumValues;
        private String pattern;
        private String format;
        private String type;
        private String ref;
        private ModelDef modelDef;
        private Builder() {
        }

        public Builder classDef(ClassDef classDef) {
            this.classDef = classDef;
            return this;
        }

        public Builder originalName(String originalName) {
            this.originalName = originalName;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder required(boolean required) {
            this.required = required;
            return this;
        }

        public Builder array(boolean array) {
            this.array = array;
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
        public Builder type(String type) {
            this.type = type;
            return this;
        }
        public Builder ref(String ref) {
            this.ref = ref;
            return this;
        }
        public FieldDefImpl build() {
            return new FieldDefImpl(this);
        }

        public Builder modelDef(ModelDef modelDef) {
            this.modelDef = modelDef;
            return this;
        }
    }
}