package io.github.eealba.payper.apibuilder.internal;

import io.github.eealba.payper.apibuilder.ApiResponseDef;
import io.github.eealba.payper.apibuilder.ModelDef;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

class ApiResponseDefImpl implements ApiResponseDef {
    private final int statusCode;
    private final String description;
    private final ModelDef model;
    private final List<ModelDef> refModels;

    private ApiResponseDefImpl(Builder builder) {
        this.statusCode = builder.statusCode;
        this.description = builder.description;
        this.model = builder.model;
        this.refModels = Optional.ofNullable(builder.refModels).map(Collections::unmodifiableList)
                .orElse(Collections.emptyList());
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public int statusCode() {
        return statusCode;
    }

    @Override
    public String description() {
        return description;
    }

    @Override
    public ModelDef model() {
        return model;
    }

    @Override
    public List<ModelDef> refModels() {
        return refModels;
    }

    static class Builder {
        private List<ModelDef> refModels;
        private int statusCode;
        private String description;
        private ModelDef model;
        private Builder() {}

        Builder statusCode(int statusCode) {
            this.statusCode = statusCode;
            return this;
        }

        Builder description(String description) {
            this.description = description;
            return this;
        }

        Builder model(ModelDef model) {
            this.model = model;
            return this;
        }

        Builder refModels(List<ModelDef> refModels) {
            this.refModels = refModels;
            return this;
        }
        ApiResponseDef build() {
            return new ApiResponseDefImpl(this);
        }
    }
}
