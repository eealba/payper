package io.github.eealba.payper.apibuilder.internal;

import io.swagger.parser.OpenAPIParser;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.examples.Example;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.parser.core.models.ParseOptions;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class OpenApiExternalParser {
    public static final String MAIN_RESOURCES_OPENAPI = "/Users/enrique/git/paypal-builder/src/main/resources/openapi";
    private final OpenAPI openAPI;

    OpenApiExternalParser(File openApiFile) {
        ParseOptions options = new ParseOptions();
        options.setResolve(true);
        this.openAPI = new OpenAPIParser().readLocation(openApiFile.getAbsolutePath(), null, options).getOpenAPI();
    }
    OpenApiExternalParser(byte[] openApiContent) {
        ParseOptions options = new ParseOptions();
        options.setResolve(true);
        this.openAPI = new OpenAPIParser().readContents(new String(openApiContent, StandardCharsets.UTF_8),
                null, options).getOpenAPI();
    }
    //get components
    Map<String, Schema<?>> getComponents() {
        Map<String, Schema<?>> map = new HashMap<>();
        this.openAPI.getComponents().getSchemas().forEach(map::put);
        return map;
    }
    Map<String, Example> getExamples() {
        return this.openAPI.getComponents().getExamples();
    }
    Map<String, PathItem> getEndpoints() {
        return this.openAPI.getPaths();
    }

    List<Operation> getOperations(String endpoint) {
        PathItem pathItem = this.openAPI.getPaths().get(endpoint);
        List<Operation> operations = new ArrayList<>();
        if (pathItem.getGet() != null) {
            operations.add(pathItem.getGet());
        }
        if (pathItem.getPost() != null) {
            operations.add(pathItem.getPost());
        }
        if (pathItem.getPut() != null) {
            operations.add(pathItem.getPut());
        }
        if (pathItem.getDelete() != null) {
            operations.add(pathItem.getDelete());
        }
        if (pathItem.getPatch() != null) {
            operations.add(pathItem.getPatch());
        }
        return operations;
    }
    List<Parameter> getRequestParameters(String endpoint, String operationId) {
        Operation operation = this.openAPI.getPaths().get(endpoint).readOperationsMap().get(operationId);
        return operation != null ? operation.getParameters() : new ArrayList<>();
    }

    public static void main(String[] args) {
        var openApiFilePath = new File(MAIN_RESOURCES_OPENAPI + "/billing_subscriptions_v1.json");
        // Instantiate the OpenApiParser class with the path to your OpenAPI file
        OpenApiExternalParser parser = new OpenApiExternalParser(openApiFilePath);

        // Use the getEndpoints method to get a map of all the endpoints in the OpenAPI file
        Map<String, PathItem> endpoints = parser.getEndpoints();

        // Print all endpoints
        for (String endpoint : endpoints.keySet()) {
            System.out.println("Endpoint: " + endpoint);

            // Use the getOperations method with a specific endpoint to get a list of all operations for that endpoint
            List<Operation> operations = parser.getOperations(endpoint);

            // Print all operations for the current endpoint
            for (Operation operation : operations) {
                if (operation != null) {
                    System.out.println("  Operation: " + operation.getOperationId());

                    // Use the getRequestParameters method with a specific endpoint and operation ID to get a list of all request parameters for that operation
                    List<Parameter> parameters = parser.getRequestParameters(endpoint, operation.getOperationId());

                    // Print all parameters for the current operation
                    for (Parameter parameter : parameters) {
                        System.out.println("    Parameter: " + parameter.getName());
                    }
                }
            }
        }

        parser.getComponents().forEach((k, v) -> {
            System.out.println("Component: " + k + " - "+ v.getType() + " - " + v.getFormat() + " - " + v.getDescription() + " - example: " + v.getExample());
        });
    }

    void printEndpoints() {
        Map<String, PathItem> endpoints = getEndpoints();
        for (String endpoint : endpoints.keySet()) {
            System.out.println("Endpoint: " + endpoint);
        }
    }

}