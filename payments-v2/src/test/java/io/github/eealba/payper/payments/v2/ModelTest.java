package io.github.eealba.payper.payments.v2;

import io.github.eealba.jasoner.Jasoner;
import io.github.eealba.jasoner.JasonerBuilder;
import io.github.eealba.payper.payments.v2.model.Authorization2;
import io.github.eealba.payper.payments.v2.model.Capture2;
import io.github.eealba.payper.payments.v2.model.CaptureRequest;
import io.github.eealba.payper.payments.v2.model.ErrorDefault;
import io.github.eealba.payper.payments.v2.model.ReauthorizeRequest;
import io.github.eealba.payper.payments.v2.model.Refund;
import io.github.eealba.payper.payments.v2.model.RefundRequest;
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
    void authorization_2_serialize_deserialize() throws IOException, JSONException {
        execute("/examples/authorization_2.json", Authorization2.class);
    }

    @Test
    void capture_request_serialize_deserialize() throws IOException, JSONException {
        execute("/examples/capture_request.json", CaptureRequest.class);
    }

    @Test
    void capture_2_serialize_deserialize() throws IOException, JSONException {
        execute("/examples/capture_2.json", Capture2.class);
    }

    @Test
    void error_default_serialize_deserialize() throws IOException, JSONException {
        execute("/examples/error_default.json", ErrorDefault.class);
    }

    @Test
    void reauthorize_request_serialize_deserialize() throws IOException, JSONException {
        execute("/examples/reauthorize_request.json", ReauthorizeRequest.class);
    }

    @Test
    void refund_request_serialize_deserialize() throws IOException, JSONException {
        execute("/examples/refund_request.json", RefundRequest.class);
    }

    @Test
    void refund_serialize_deserialize() throws IOException, JSONException {
        execute("/examples/refund.json", Refund.class);
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
