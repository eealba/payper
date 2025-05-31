package io.github.eealba.payper.webhooks.v1;

import io.github.eealba.jasoner.Jasoner;
import io.github.eealba.jasoner.JasonerBuilder;
import io.github.eealba.payper.webhooks.v1.model.Error;
import io.github.eealba.payper.webhooks.v1.model.Event;
import io.github.eealba.payper.webhooks.v1.model.EventList;
import io.github.eealba.payper.webhooks.v1.model.EventResend;
import io.github.eealba.payper.webhooks.v1.model.EventTypeList;
import io.github.eealba.payper.webhooks.v1.model.Patch;
import io.github.eealba.payper.webhooks.v1.model.SimulateEvent;
import io.github.eealba.payper.webhooks.v1.model.VerifyWebhookSignature;
import io.github.eealba.payper.webhooks.v1.model.VerifyWebhookSignatureResponse;
import io.github.eealba.payper.webhooks.v1.model.Webhook;
import io.github.eealba.payper.webhooks.v1.model.WebhookList;
import io.github.eealba.payper.webhooks.v1.model.WebhookLookupList;
import io.github.eealba.payper.webhooks.v1.model.WebhooksLookup;
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

    // webhooks.post (request/response)
    @Test
    void webhooks_post_request() throws IOException, JSONException {
        execute("/examples/webhooks.post_request.json", Webhook.class);
    }
    @Test
    void webhooks_post_response() throws IOException, JSONException {
        execute("/examples/webhooks.post_response.json", Webhook.class);
    }

    // webhooks.list (response)
    @Test
    void webhooks_list_response() throws IOException, JSONException {
        execute("/examples/webhooks.list_response.json", WebhookList.class);
    }

    // webhooks.get (response)
    @Test
    void webhooks_get_response() throws IOException, JSONException {
        execute("/examples/webhooks.get_response.json", Webhook.class);
    }

    // webhooks.update (request/response)
    @Test
    void webhooks_update_request() throws IOException, JSONException {
        execute("/examples/webhooks.update_request.json", Patch[].class);
    }


    @Test
    void webhooks_update_response() throws IOException, JSONException {
        execute("/examples/webhooks.update_response.json", Webhook.class);
    }

    // webhooks.delete (response)
    @Test
    void error() throws IOException, JSONException {
        execute("/examples/error.json", Error.class);
    }

    // event-types.list (response)
    @Test
    void event_types_list_response() throws IOException, JSONException {
        execute("/examples/event-types.list_response.json", EventTypeList.class);
    }

    // webhooks-lookup.post (request/response)

    @Test
    void webhooks_lookup_post_response() throws IOException, JSONException {
        execute("/examples/webhooks-lookup.post_response.json", WebhooksLookup.class);
    }

    // webhooks-lookup.list (response)
    @Test
    void webhooks_lookup_list_response() throws IOException, JSONException {
        execute("/examples/webhooks-lookup.list_response.json", WebhookLookupList.class);
    }

    // webhooks-lookup.get (response)
    @Test
    void webhooks_lookup_get_response() throws IOException, JSONException {
        execute("/examples/webhooks-lookup.get_response.json", WebhooksLookup.class);
    }


    // verify-webhook-signature.post (request/response)
    @Test
    void verify_webhook_signature_post_request() throws IOException, JSONException {
        execute("/examples/verify-webhook-signature.post_request.json", VerifyWebhookSignature.class);
    }
    @Test
    void verify_webhook_signature_post_response() throws IOException, JSONException {
        execute("/examples/verify-webhook-signature.post_response.json", VerifyWebhookSignatureResponse.class);
    }

    // webhooks-event-types.list (response)
    @Test
    void webhooks_event_types_list_response() throws IOException, JSONException {
        execute("/examples/webhooks-event-types.list_response.json", EventTypeList.class);
    }

    // webhooks-events.list (response)
    @Test
    void webhooks_events_list_response() throws IOException, JSONException {
        execute("/examples/webhooks-events.list_response.json", EventList.class);
    }

    // webhooks-events.get (response)
    @Test
    void webhooks_events_get_response() throws IOException, JSONException {
        execute("/examples/webhooks-events.get_response.json", Event.class);
    }

    // webhooks-events.resend (request/response)
    @Test
    void webhooks_events_resend_request() throws IOException, JSONException {
        execute("/examples/webhooks-events.resend_request.json", EventResend.class);
    }
    @Test
    void webhooks_events_resend_response() throws IOException, JSONException {
        execute("/examples/webhooks-events.resend_response.json", Event.class);
    }
    @Test
    void simulate_event() throws IOException, JSONException {
        execute("/examples/simulate-event.json", SimulateEvent.class);
    }

    private static void execute(String resourceControl, Class<?> clazz) throws IOException, JSONException {
        String control = readResource(resourceControl);
        var pojo = jasoner.fromJson(control, clazz);
        var json = jasoner.toJson(pojo);

        JSONAssert.assertEquals(control, json, true);
    }

    private static String readResource(String path) throws IOException {
        try (var inputStream = ModelTest.class.getResourceAsStream(path)) {
            return new String(Objects.requireNonNull(inputStream).readAllBytes(), StandardCharsets.UTF_8);
        }
    }
}
