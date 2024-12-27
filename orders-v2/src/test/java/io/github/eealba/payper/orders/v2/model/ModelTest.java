package io.github.eealba.payper.orders.v2.model;

import io.github.eealba.jasoner.Jasoner;
import io.github.eealba.jasoner.JasonerBuilder;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class ModelTest {
    static Jasoner jasoner;
    @BeforeAll
    static void setup() {
        jasoner = JasonerBuilder.create();
    }


    @Test
    void order_request_serialize_deserialize() throws IOException, JSONException {
        execute("/examples/order_request.json", OrderRequest.class);
    }
    @Test
    void order_serialize_deserialize() throws IOException, JSONException {
        execute("/examples/order.json", Order.class);
    }
    @Test
    void patch_request_serialize_deserialize() throws IOException, JSONException {
        execute("/examples/patch_request.json", PatchRequest.class);
    }
    @Test
    void confirm_order_request_serialize_deserialize() throws IOException, JSONException {
        execute("/examples/confirm_order_request.json", ConfirmOrderRequest.class);
    }
    @Test
    void order_authorize_request_serialize_deserialize() throws IOException, JSONException {
        execute("/examples/order_authorize_request.json", OrderAuthorizeRequest.class);
    }
    @Test
    void order_capture_request_serialize_deserialize() throws IOException, JSONException {
        execute("/examples/order_capture_request.json", OrderCaptureRequest.class);
    }
    @Test
    void order_tracker_request_serialize_deserialize() throws IOException, JSONException {
        execute("/examples/order_tracker_request.json", OrderTrackerRequest.class);
    }
    @Test
    void order_authorize_response_serialize_deserialize() throws IOException, JSONException {
        execute("/examples/order_authorize_response.json", OrderAuthorizeResponse.class);
    }

    private static void execute(String resourceControl, Class<?> clazz) throws IOException, JSONException {
        String control = readResource(resourceControl);
        var pojo = jasoner.fromJson(control, clazz);
        var json = jasoner.toJson(pojo);

        JSONAssert.assertEquals(control, json, true);
    }

    public static String readResource(String path) throws IOException {
        try (var inputStream = ModelTest.class.getResourceAsStream(path)) {
            return new String(Objects.requireNonNull(inputStream).readAllBytes(), StandardCharsets.UTF_8);
        }
    }
}
