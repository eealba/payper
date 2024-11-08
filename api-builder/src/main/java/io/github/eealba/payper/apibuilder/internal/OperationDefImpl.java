package io.github.eealba.payper.apibuilder.internal;

import io.github.eealba.payper.apibuilder.ApiRequestDef;
import io.github.eealba.payper.apibuilder.ApiResponseDef;
import io.github.eealba.payper.apibuilder.HttpMethod;
import io.github.eealba.payper.apibuilder.OperationDef;
import io.github.eealba.payper.apibuilder.ParameterDef;

import java.util.List;
import java.util.Optional;

class OperationDefImpl implements OperationDef {
    private final List<String> tags;
    private final String summary;
    private final String description;
    private final String operationId;
    private final HttpMethod httpMethod;
    private final List<ParameterDef> parameters;
    private final String path;
    private final ApiRequestDef requestModel;
    private final List<ApiResponseDef> responseModel;

    private OperationDefImpl(Builder builder) {
        this.tags = builder.tags;
        this.summary = builder.summary;
        this.description = builder.description;
        this.operationId = builder.operationId;
        this.httpMethod = builder.httpMethod;
        this.parameters = builder.parameters;
        this.path = builder.path;
        this.requestModel = builder.requestModel;
        this.responseModel = builder.responseModel;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public List<String> tags() {
        return tags;
    }

    @Override
    public String summary() {
        return summary;
    }

    @Override
    public String description() {
        return description;
    }

    @Override
    public String operationId() {
        return operationId;
    }

    @Override
    public HttpMethod httpMethod() {
        return httpMethod;
    }

    @Override
    public List<ParameterDef> parameters() {
        return parameters;
    }

    @Override
    public String path() {
        return path;
    }

    @Override
    public Optional<ApiRequestDef> requestModel() {
        return Optional.ofNullable(requestModel);
    }

    @Override
    public List<ApiResponseDef> responseModel() {
        return responseModel;
    }

    static class Builder {
        private List<String> tags;
        private String summary;
        private String description;
        private String operationId;
        private HttpMethod httpMethod;
        private List<ParameterDef> parameters;
        private String path;
        private ApiRequestDef requestModel;
        private List<ApiResponseDef> responseModel;
        private Builder() {
        }

        Builder tags(List<String> tags) {
            this.tags = tags;
            return this;
        }

        Builder summary(String summary) {
            this.summary = summary;
            return this;
        }

        Builder description(String description) {
            this.description = description;
            return this;
        }

        Builder operationId(String operationId) {
            this.operationId = operationId;
            return this;
        }

        Builder httpMethod(HttpMethod httpMethod) {
            this.httpMethod = httpMethod;
            return this;
        }

        Builder parameters(List<ParameterDef> parameters) {
            this.parameters = parameters;
            return this;
        }

        Builder path(String path) {
            this.path = path;
            return this;
        }

        Builder requestModel(ApiRequestDef requestModel) {
            this.requestModel = requestModel;
            return this;
        }

        Builder responseModel(List<ApiResponseDef> responseModel) {
            this.responseModel = responseModel;
            return this;
        }

        OperationDef build() {
            return new OperationDefImpl(this);
        }
    }
}