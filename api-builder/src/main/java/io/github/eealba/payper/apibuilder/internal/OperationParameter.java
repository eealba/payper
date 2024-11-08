package io.github.eealba.payper.apibuilder.internal;

import io.github.eealba.payper.apibuilder.HttpMethod;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.media.Schema;

import java.util.Map;

public record OperationParameter(Operation operation, String path, HttpMethod httpMethod,
                                 Map<String, Schema<?>> components, String packageName) {
}