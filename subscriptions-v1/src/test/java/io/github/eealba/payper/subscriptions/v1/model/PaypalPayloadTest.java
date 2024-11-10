package io.github.eealba.payper.subscriptions.v1.model;

import io.github.eealba.jasoner.Jasoner;
import io.github.eealba.jasoner.JasonerBuilder;
import io.github.eealba.jasoner.JasonerConfig;
import io.github.eealba.jasoner.NamingStrategy;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class PaypalPayloadTest {
    private static final Jasoner JASONER = JasonerBuilder.create(JasonerConfig.builder()
            .namingStrategy(NamingStrategy.SNAKE_CASE)
            .pretty(true)
            .build());
    private static final String EXAMPLES = "/examples/";

    @Test
    void should_serialize_and_deserialize_paypal_patch_request() throws IOException, JSONException {
        executeAndCompare(readResource(EXAMPLES + "patch_request.json"), PatchRequest.class);
    }

    @Test
    void should_serialize_and_deserialize_paypal_plan() throws IOException, JSONException {
        executeAndCompare(readResource(EXAMPLES + "plan.json"), Plan.class);
    }
    @Test
    void should_serialize_and_deserialize_paypal_plan_request_POST() throws IOException, JSONException {
        executeAndCompare(readResource(EXAMPLES + "plan_request_POST.json"), PlanRequestPOST.class);
    }
    @Test
    void should_serialize_and_deserialize_paypal_subscription() throws IOException, JSONException {
        executeAndCompare(readResource(EXAMPLES + "subscription.json"), Subscription.class);
    }
    @Test
    void should_serialize_and_deserialize_paypal_subscription_activate_request() throws IOException, JSONException {
        executeAndCompare(readResource(EXAMPLES + "subscription_activate_request.json"),
                SubscriptionActivateRequest.class);
    }
    @Test
    void should_serialize_and_deserialize_paypal_subscription_cancel_request() throws IOException, JSONException {
        executeAndCompare(readResource(EXAMPLES + "subscription_cancel_request.json"),
                SubscriptionCancelRequest.class);
    }
    @Test
    void should_serialize_and_deserialize_paypal_subscription_capture_request() throws IOException, JSONException {
        executeAndCompare(readResource(EXAMPLES + "subscription_capture_request.json"),
                SubscriptionCaptureRequest.class);
    }
    @Test
    void should_serialize_and_deserialize_paypal_subscription_request_post() throws IOException, JSONException {
        executeAndCompare(readResource(EXAMPLES + "subscription_request_post.json"),
                SubscriptionRequestPost.class);
    }

    @Test
    void should_serialize_and_deserialize_paypal_subscription_revise_request() throws IOException, JSONException {
        executeAndCompare(readResource(EXAMPLES + "subscription_revise_request.json"),
                SubscriptionReviseRequest.class);
    }
    @Test
    void should_serialize_and_deserialize_paypal_subscription_revise_response() throws IOException, JSONException {
        executeAndCompare(readResource(EXAMPLES + "subscription_revise_response.json"),
                SubscriptionReviseResponse.class);
    }
    @Test
    void should_serialize_and_deserialize_paypal_subscription_suspend_request() throws IOException, JSONException {
        executeAndCompare(readResource(EXAMPLES + "subscription_suspend_request.json"),
                SubscriptionSuspendRequest.class);
    }

    @Test
    void should_serialize_and_deserialize_paypal_update_pricing_schemes_list_request() throws IOException, JSONException {
        executeAndCompare(readResource(EXAMPLES + "update_pricing_schemes_list_request.json"),
                UpdatePricingSchemesListRequest.class);
    }

    private static void executeAndCompare(String json, Class<?> clazz) throws JSONException {
        var subscription = JASONER.fromJson(json, clazz);
        assertNotNull(subscription);
        String newJson = JASONER.toJson(subscription);
        JSONAssert.assertEquals(json, newJson, true);
    }

    private static String readResource(String path) throws IOException {
        try (var inputStream = PaypalPayloadTest.class.getResourceAsStream(path)) {
            return new String(Objects.requireNonNull(inputStream).readAllBytes(), StandardCharsets.UTF_8);
        }
    }


}