package io.github.eealba.payper.apibuilder.internal;

import io.github.eealba.payper.apibuilder.ParameterDef;
import io.github.eealba.payper.apibuilder.ParameterInType;
import io.github.eealba.payper.apibuilder.SchemaDef;

class ParameterDefImpl implements ParameterDef {
    private final String name;
    private final ParameterInType in;
    private final boolean required;
    private final String description;
    private final SchemaDef schema;

    private ParameterDefImpl(Builder builder) {
        this.name = builder.name;
        this.in = builder.in;
        this.required = builder.required;
        this.description = builder.description;
        this.schema = builder.schema;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public ParameterInType in() {
        return in;
    }

    @Override
    public boolean required() {
        return required;
    }

    @Override
    public String description() {
        return description;
    }

    @Override
    public SchemaDef schema() {
        return schema;
    }

    static Builder builder() {
        return new Builder();
    }

    static class Builder {
        private String name;
        private ParameterInType in;
        private boolean required;
        private String description;
        private SchemaDef schema;
        private Builder() {}

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder in(ParameterInType in) {
            this.in = in;
            return this;
        }

        public Builder required(boolean required) {
            this.required = required;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder schema(SchemaDef schema) {
            this.schema = schema;
            return this;
        }

        public ParameterDef build() {
            return new ParameterDefImpl(this);
        }
    }
}