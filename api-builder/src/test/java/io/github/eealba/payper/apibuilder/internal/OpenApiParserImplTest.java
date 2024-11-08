package io.github.eealba.payper.apibuilder.internal;

import io.github.eealba.payper.apibuilder.HttpMethod;
import io.github.eealba.payper.apibuilder.ObjectsFactory;
import io.github.eealba.payper.apibuilder.OperationDef;
import io.github.eealba.payper.apibuilder.ParameterInType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OpenApiParserImplTest {
    private static List<OperationDef> operations;

    @BeforeAll
    static void prepare() throws URISyntaxException, IOException {
        var openApiFileUrl = OpenApiParserImplTest.class.getResource("/openapi/billing_subscriptions_v1.json");
        assert openApiFileUrl != null;
        var openApiFile = Files.readAllBytes(Path.of(openApiFileUrl.toURI()));
        var parser = ObjectsFactory.openApiParser(openApiFile, "io.github.eealba.paypal.subscriptions.v1.model");
        operations = parser.getOperations();

    }

    @Test
    void getOperations() {
        assertEquals(16, operations.size());
        assertEquals(4, operations.stream().filter(op -> op.httpMethod() == HttpMethod.GET).count());
        assertEquals(10, operations.stream().filter(op -> op.httpMethod() == HttpMethod.POST).count());
        assertEquals(2, operations.stream().filter(op -> op.httpMethod() == HttpMethod.PATCH).count());
    }

    @Test
    void getTags() {
        var tags = operations.stream().map(OperationDef::tags).flatMap(List::stream).distinct().toList();
        assertEquals(2, tags.size());
        assertTrue(tags.contains("subscriptions"));
        assertTrue(tags.contains("plans"));
    }

    @Test
    void getSummary() {
        var summaries = operations.stream().map(OperationDef::summary).distinct().toList();
        assertEquals(16, summaries.size());
        assertTrue(summaries.contains("List plans"));
    }

    @Test
    void getDescription() {
        var descriptions = operations.stream().map(OperationDef::description).distinct().toList();
        assertEquals(16, descriptions.size());
        assertTrue(descriptions.contains("Lists billing plans."));
    }

    @Test
    void getOperationId() {
        var operationIds = operations.stream().map(OperationDef::operationId).distinct().toList();
        assertEquals(16, operationIds.size());
        assertTrue(operationIds.contains("plans.create"));
    }

    @Test
    void getPath() {
        var paths = operations.stream().map(OperationDef::path).distinct().toList();
        assertEquals(13, paths.size());
        assertTrue(paths.contains("/v1/billing/plans/{id}"));
    }

    @Test
    void getParameters() {
        var parameters = operations.stream().filter(op -> op.path().equals("/v1/billing/plans") &&
                        op.httpMethod() == HttpMethod.GET).findFirst().map(OperationDef::parameters)
                .orElse(Collections.emptyList());
        assertEquals(8, parameters.size());
    }

    @Test
    void getParameters_listPlans() {
        var parameters = operations.stream().filter(op -> op.path().equals("/v1/billing/plans") &&
                        op.httpMethod() == HttpMethod.GET).findFirst().map(OperationDef::parameters)
                .orElse(Collections.emptyList());
        assertEquals(1, parameters.stream().filter(p -> p.name().equals("Content-Type")).count());
        assertTrue(parameters.stream().filter(p -> p.name().equals("Content-Type")).findFirst().orElseThrow().required());
        assertEquals(ParameterInType.HEADER, parameters.stream().filter(p -> p.name().equals("Content-Type"))
                .findFirst().orElseThrow().in());

        assertEquals(1, parameters.stream().filter(p -> p.name().equals("Prefer")).count());

        assertEquals(1, parameters.stream().filter(p -> p.name().equals("product_id")).count());
        assertEquals(1, parameters.stream().filter(p -> p.name().equals("plan_ids")).count());
        assertEquals(1, parameters.stream().filter(p -> p.name().equals("statuses")).count());
        assertEquals(1, parameters.stream().filter(p -> p.name().equals("page")).count());
        assertEquals(1, parameters.stream().filter(p -> p.name().equals("page_size")).count());
        assertEquals(1, parameters.stream().filter(p -> p.name().equals("total_required")).count());

        assertEquals(ParameterInType.QUERY, parameters.stream().filter(p -> p.name().equals("total_required"))
                .findFirst().orElseThrow().in());

    }

    @Test
    void should_return_parameter_page_size() {
        var parameter = operations.stream().filter(op -> op.path().equals("/v1/billing/plans") &&
                        op.httpMethod() == HttpMethod.GET).findFirst().map(OperationDef::parameters)
                .orElse(Collections.emptyList()).stream().filter(p -> p.name().equals("page_size")).findFirst().orElseThrow();
        assertFalse(parameter.required());
        assertEquals("The number of items to return in the response.", parameter.description());
        assertEquals(ParameterInType.QUERY, parameter.in());
        assertEquals("integer", parameter.schema().type());
        assertEquals(1, parameter.schema().minimum().intValue());
        assertEquals(20, parameter.schema().maximum().intValue());
        assertEquals(10, ((Integer) parameter.schema().defaultValue()).intValue());

    }

    @Test
    void should_return_parameter_product_id() {
        var parameter = operations.stream().filter(op -> op.path().equals("/v1/billing/plans") &&
                        op.httpMethod() == HttpMethod.GET).findFirst().map(OperationDef::parameters)
                .orElse(Collections.emptyList()).stream().filter(p -> p.name().equals("product_id")).findFirst().orElseThrow();
        assertEquals(ParameterInType.QUERY, parameter.in());
        assertEquals("string", parameter.schema().type());
        assertEquals(6, parameter.schema().minLength());
        assertEquals(50, parameter.schema().maxLength());
    }

    @Test
    void should_return_parameter_statuses() {
        var parameter = operations.stream().filter(op -> op.path().equals("/v1/billing/plans") &&
                        op.httpMethod() == HttpMethod.GET).findFirst().map(OperationDef::parameters)
                .orElse(Collections.emptyList()).stream().filter(p -> p.name().equals("statuses")).findFirst().orElseThrow();
        assertEquals(ParameterInType.QUERY, parameter.in());
        assertEquals("string", parameter.schema().type());
        assertEquals(1, parameter.schema().minLength());
        assertEquals(70, parameter.schema().maxLength());
        assertEquals("^[A-Z_,]+$", parameter.schema().pattern());
        assertEquals(List.of("ACTIVE", "SUSPENDED", "CANCELLED", "EXPIRED"), parameter.schema().enumValues());
    }

    @Test
    void should_not_have_request_object() {
        var requestModel = operations.stream().filter(op -> op.path().equals("/v1/billing/plans") &&
                        op.httpMethod() == HttpMethod.GET).findFirst().map(OperationDef::requestModel).orElseThrow();
        assertTrue(requestModel.isEmpty());
    }

    @Test
    void should_have_request_object() {
        var requestModel = operations.stream().filter(op -> op.path().equals("/v1/billing/plans") &&
                op.httpMethod() == HttpMethod.POST).findFirst().map(OperationDef::requestModel).orElseThrow();
        assertTrue(requestModel.isPresent());
        var apiRequestDef = requestModel.get();
        assertEquals("The create plan request details.",apiRequestDef.description());

        var fieldDefList = apiRequestDef.model().fieldDefList();

        assertEquals(8,fieldDefList.size(), "Should have 8 fields");
        assertEquals(1, fieldDefList.stream().filter(p -> p.name().equals("productId")).count());
        assertEquals(1, fieldDefList.stream().filter(p -> p.name().equals("name")).count());
        assertEquals(1, fieldDefList.stream().filter(p -> p.name().equals("status")).count());
        assertEquals(1, fieldDefList.stream().filter(p -> p.name().equals("description")).count());
        assertEquals(1, fieldDefList.stream().filter(p -> p.name().equals("billingCycles")).count());
        assertEquals(1, fieldDefList.stream().filter(p -> p.name().equals("paymentPreferences")).count());
        assertEquals(1, fieldDefList.stream().filter(p -> p.name().equals("taxes")).count());
        assertEquals(1, fieldDefList.stream().filter(p -> p.name().equals("quantitySupported")).count());



    }

}