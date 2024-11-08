package io.github.eealba.payper.apibuilder.internal;

import io.github.eealba.payper.apibuilder.ApiRequestDef;
import io.github.eealba.payper.apibuilder.ModelDef;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

class ApiRequestDefImpl implements ApiRequestDef {
    private final String description;
    private final ModelDef model;
    private final List<ModelDef> refModels;

    private ApiRequestDefImpl(Builder builder) {
        this.description = builder.description;
        this.model = builder.model;
        this.refModels = Optional.ofNullable(builder.refModels).map(Collections::unmodifiableList)
                .orElse(Collections.emptyList());

    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String description() {
        return description;
    }
    @Override
    public List<ModelDef> refModels() {
        return refModels;
    }

    @Override
    public ModelDef model() {
        return model;
    }

    static class Builder {
        private String description;
        private ModelDef model;
        private List<ModelDef> refModels;

        private Builder() {}

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder model(ModelDef model) {
            this.model = model;
            return this;
        }
        Builder refModels(List<ModelDef> refModels) {
            this.refModels = refModels;
            return this;
        }


        public ApiRequestDef build() {
            return new ApiRequestDefImpl(this);
        }
    }
}
