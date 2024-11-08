package io.github.eealba.payper.apibuilder.internal;

import io.github.eealba.payper.apibuilder.HttpMethod;
import io.github.eealba.payper.apibuilder.OpenApiParser;
import io.github.eealba.payper.apibuilder.OperationDef;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.examples.Example;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class OpenApiParserImpl implements OpenApiParser {
    private final OpenApiExternalParser parser;
    private final String packageName;
    private final OpenApiMapper mapper = new OpenApiMapperImpl();

    OpenApiParserImpl(File openApiFile, String packageName) {
        this.parser = new OpenApiExternalParser(openApiFile);
        this.packageName = packageName;
    }
    OpenApiParserImpl(byte[] openApiContent, String packageName) {
        this.parser = new OpenApiExternalParser(openApiContent);
        this.packageName = packageName;
    }

    @Override
    public List<OperationDef> getOperations() {
        List<OperationDef> res = new ArrayList<>();
        var components = parser.getComponents();
        var endpoints = parser.getEndpoints();
        endpoints.forEach((String endpoint, PathItem pathItem) -> {
            if (pathItem.getGet() != null) {
                res.add(mapper.mapOperation(new OperationParameter(pathItem.getGet(), endpoint, HttpMethod.GET,
                        components, packageName)));
            }
            if (pathItem.getPost() != null) {
                res.add(mapper.mapOperation(new OperationParameter(pathItem.getPost(), endpoint, HttpMethod.POST,
                        components, packageName)));
            }
            if (pathItem.getPut() != null) {
                res.add(mapper.mapOperation(new OperationParameter(pathItem.getPut(), endpoint, HttpMethod.PUT,
                        components, packageName)));
            }
            if (pathItem.getDelete() != null) {
                res.add(mapper.mapOperation(new OperationParameter(pathItem.getDelete(), endpoint, HttpMethod.DELETE,
                        components, packageName)));
            }
            if (pathItem.getPatch() != null) {
                res.add(mapper.mapOperation(new OperationParameter(pathItem.getPatch(), endpoint, HttpMethod.PATCH,
                        components, packageName)));
            }
        });
        return res;
    }

    @Override
    public Map<String, String> getExamples() {
        Map<String, String> tmp = new HashMap<>();
        parser.getExamples().forEach((String key, Example value) -> {
            tmp.put(key, value.getValue().toString());
        });
        return tmp;
    }

}