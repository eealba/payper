package io.github.eealba.payper.apibuilder.internal;

import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.RequestBody;

import java.util.Map;

public record ApiRequestParameter(RequestBody requestBody, Map<String, Schema<?>> components, String packageName) {
}