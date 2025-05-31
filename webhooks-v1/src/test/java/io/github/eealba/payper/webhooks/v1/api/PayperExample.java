package io.github.eealba.payper.webhooks.v1.api;

import io.github.eealba.payper.webhooks.v1.model.EventType;
import io.github.eealba.payper.webhooks.v1.model.Patch;
import io.github.eealba.payper.webhooks.v1.model.PatchRequest;
import io.github.eealba.payper.webhooks.v1.model.Webhook;

import java.util.List;

import static io.github.eealba.payper.webhooks.v1.model.Patch.Op.REPLACE;

public class PayperExample {
    public static void main(String[] args) {
        var webhooksApi = WebhooksApiClient.create().webhooks();

        // Create a webhook
        EventType eventType = EventType.builder().name("PAYMENT.AUTHORIZATION.CREATED").build();
        Webhook webhookRequest = Webhook.builder().url("https://example.com/webhook")
                                        .eventTypes(List.of(eventType)).build();

        var webhook = webhooksApi.create().withBody(webhookRequest).retrieve().toEntity();
        System.out.println("Created webhook ID: " + webhook.id());

        // List webhooks
        var webhooksList = webhooksApi.list().retrieve().toEntity();
        System.out.println("First webhook ID: " + webhooksList.webhooks().get(0).id());

        // Get a webhook
        var webhookDetails = webhooksApi.get().withId("WH-1234567890").retrieve().toEntity();
        System.out.println("Webhook URL: " + webhookDetails.url());

        // Update a webhook
        Patch patch = Patch.builder().op(REPLACE).path("/url").value("https://example" + ".com/new-webhook").build();
        PatchRequest patchRequest = new PatchRequest(List.of(patch));
        var updatedWebhook = webhooksApi.update().withId("WH-1234567890").withBody(patchRequest).retrieve().toEntity();
        System.out.println("Updated webhook URL: " + updatedWebhook.url());

        // Delete a webhook
        webhooksApi.delete().withId("WH-1234567890").retrieve().toVoid();

        // List event types for a webhook
        var eventTypes = webhooksApi.listEventTypes().withId("WH-1234567890").retrieve().toEntity();

        System.out.println("First event type: " + eventTypes.eventTypes().get(0).name());
    }
}
