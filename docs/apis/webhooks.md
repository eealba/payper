# Webhooks Management API

The Webhooks Management API v1 enables you to configure and manage webhook notifications for PayPal events.

**Official Documentation**: [PayPal Webhooks API v1](https://developer.paypal.com/docs/api/webhooks/v1/)

---

## Installation

Add the Webhooks API dependency to your project:

=== "Maven"

    ```xml
    <dependency>
        <groupId>io.github.eealba.payper</groupId>
        <artifactId>payper-webhooks-v1</artifactId>
        <version>1.0.0</version>
    </dependency>
    ```

=== "Gradle (Groovy)"

    ```groovy
    implementation 'io.github.eealba.payper:payper-webhooks-v1:1.0.0'
    ```

=== "Gradle (Kotlin)"

    ```kotlin
    implementation("io.github.eealba.payper:payper-webhooks-v1:1.0.0")
    ```

---

## Creating the Client

```java
import io.github.eealba.payper.webhooks.v1.api.WebhooksApiClient;

public class Example {
    public static void main(String[] args) {
        // Create client (uses environment variables for credentials)
        var client = WebhooksApiClient.create();
        
        // Access the webhooks API
        var webhooksApi = client.webhooks();
    }
}
```

---

## Common Operations

### Create a Webhook

Register a webhook endpoint to receive event notifications:

```java
import io.github.eealba.payper.webhooks.v1.api.WebhooksApiClient;
import io.github.eealba.payper.webhooks.v1.model.*;

import java.util.List;

public class CreateWebhookExample {
    public static void main(String[] args) {
        var client = WebhooksApiClient.create();
        
        // Define event types to subscribe to
        var eventTypes = List.of(
            EventType.builder().name("PAYMENT.CAPTURE.COMPLETED").build(),
            EventType.builder().name("PAYMENT.CAPTURE.DENIED").build(),
            EventType.builder().name("PAYMENT.CAPTURE.REFUNDED").build()
        );
        
        // Build webhook request
        var webhookRequest = Webhook.builder()
                .url("https://example.com/webhooks/paypal")
                .eventTypes(eventTypes)
                .build();
        
        // Create the webhook
        var webhook = client.webhooks()
                .create()
                .withBody(webhookRequest)
                .retrieve()
                .toEntity();
        
        System.out.println("Webhook ID: " + webhook.id());
        System.out.println("URL: " + webhook.url());
        System.out.println("Event Types: " + webhook.eventTypes().size());
    }
}
```

**Expected Output**:
```
Webhook ID: 8PT597110X687430LKGECATA
URL: https://example.com/webhooks/paypal
Event Types: 3
```

---

### List Webhooks

Retrieve all registered webhooks:

```java
import io.github.eealba.payper.webhooks.v1.api.WebhooksApiClient;

public class ListWebhooksExample {
    public static void main(String[] args) {
        var client = WebhooksApiClient.create();
        
        // List all webhooks
        var webhooksList = client.webhooks()
                .list()
                .retrieve()
                .toEntity();
        
        System.out.println("Total Webhooks: " + webhooksList.webhooks().size());
        
        // Print each webhook
        webhooksList.webhooks().forEach(webhook -> {
            System.out.println("  - ID: " + webhook.id());
            System.out.println("    URL: " + webhook.url());
            System.out.println("    Events: " + webhook.eventTypes().size());
        });
    }
}
```

---

### Get Webhook Details

Retrieve details of a specific webhook:

```java
import io.github.eealba.payper.webhooks.v1.api.WebhooksApiClient;

public class GetWebhookExample {
    public static void main(String[] args) {
        var client = WebhooksApiClient.create();
        
        // Get webhook by ID
        var webhook = client.webhooks()
                .get()
                .withId("8PT597110X687430LKGECATA")
                .retrieve()
                .toEntity();
        
        System.out.println("Webhook ID: " + webhook.id());
        System.out.println("URL: " + webhook.url());
        
        // Print subscribed event types
        System.out.println("Subscribed Events:");
        webhook.eventTypes().forEach(eventType -> {
            System.out.println("  - " + eventType.name());
            System.out.println("    Description: " + eventType.description());
        });
    }
}
```

---

### Update a Webhook

Update webhook URL or subscribed events:

```java
import io.github.eealba.payper.webhooks.v1.api.WebhooksApiClient;
import io.github.eealba.payper.webhooks.v1.model.*;

import java.util.List;

public class UpdateWebhookExample {
    public static void main(String[] args) {
        var client = WebhooksApiClient.create();
        
        // Create patch operations
        var patches = List.of(
            // Update URL
            Patch.builder()
                    .op(Patch.Op.REPLACE)
                    .path("/url")
                    .value("https://example.com/webhooks/paypal-new")
                    .build(),
            // Add new event type
            Patch.builder()
                    .op(Patch.Op.ADD)
                    .path("/event_types")
                    .value(EventType.builder()
                            .name("BILLING.SUBSCRIPTION.CREATED")
                            .build())
                    .build()
        );
        
        // Update the webhook
        var webhook = client.webhooks()
                .update()
                .withId("8PT597110X687430LKGECATA")
                .withBody(new PatchRequest(patches))
                .retrieve()
                .toEntity();
        
        System.out.println("Webhook updated");
        System.out.println("New URL: " + webhook.url());
    }
}
```

---

### Delete a Webhook

Remove a webhook:

```java
import io.github.eealba.payper.webhooks.v1.api.WebhooksApiClient;

public class DeleteWebhookExample {
    public static void main(String[] args) {
        var client = WebhooksApiClient.create();
        
        // Delete webhook
        client.webhooks()
                .delete()
                .withId("8PT597110X687430LKGECATA")
                .retrieve()
                .toVoid();
        
        System.out.println("Webhook deleted successfully");
    }
}
```

---

### List Available Event Types

Get all available PayPal event types:

```java
import io.github.eealba.payper.webhooks.v1.api.WebhooksApiClient;

public class ListEventTypesExample {
    public static void main(String[] args) {
        var client = WebhooksApiClient.create();
        
        // List all available event types
        var eventTypes = client.webhooks()
                .listAllEventTypes()
                .retrieve()
                .toEntity();
        
        System.out.println("Available Event Types:");
        eventTypes.eventTypes().forEach(eventType -> {
            System.out.println("  - " + eventType.name());
            System.out.println("    " + eventType.description());
        });
    }
}
```

---

### List Event Types for a Webhook

Get event types subscribed to by a specific webhook:

```java
import io.github.eealba.payper.webhooks.v1.api.WebhooksApiClient;

public class ListWebhookEventTypesExample {
    public static void main(String[] args) {
        var client = WebhooksApiClient.create();
        
        // List event types for specific webhook
        var eventTypes = client.webhooks()
                .listEventTypes()
                .withId("8PT597110X687430LKGECATA")
                .retrieve()
                .toEntity();
        
        System.out.println("Event Types for Webhook:");
        eventTypes.eventTypes().forEach(eventType -> {
            System.out.println("  - " + eventType.name());
        });
    }
}
```

---

## Common Event Types

Here are some commonly used PayPal event types:

| Category | Event Type | Description |
|----------|------------|-------------|
| **Payments** | `PAYMENT.CAPTURE.COMPLETED` | Payment capture completed |
| | `PAYMENT.CAPTURE.DENIED` | Payment capture denied |
| | `PAYMENT.CAPTURE.REFUNDED` | Payment was refunded |
| **Orders** | `CHECKOUT.ORDER.COMPLETED` | Order completed |
| | `CHECKOUT.ORDER.APPROVED` | Order approved |
| **Subscriptions** | `BILLING.SUBSCRIPTION.CREATED` | Subscription created |
| | `BILLING.SUBSCRIPTION.ACTIVATED` | Subscription activated |
| | `BILLING.SUBSCRIPTION.CANCELLED` | Subscription cancelled |
| **Invoices** | `INVOICING.INVOICE.PAID` | Invoice paid |
| | `INVOICING.INVOICE.CANCELLED` | Invoice cancelled |

---

## Webhook Security

### Verifying Webhook Signatures

PayPal signs webhook events for security. Verify signatures to ensure authenticity:

!!! warning "Signature Verification"
    Always verify webhook signatures in production to prevent unauthorized access and data tampering.

PayPal includes the following headers in webhook requests:

- `PAYPAL-TRANSMISSION-ID` - Unique ID for the webhook event
- `PAYPAL-TRANSMISSION-TIME` - Timestamp of the transmission
- `PAYPAL-TRANSMISSION-SIG` - Signature for verification
- `PAYPAL-CERT-URL` - URL to PayPal's certificate
- `PAYPAL-AUTH-ALGO` - Algorithm used for signing

**Verification process** (implement in your webhook endpoint):

1. Retrieve the webhook event body
2. Extract signature headers
3. Verify signature using PayPal's certificate
4. Process event only if signature is valid

---

## Async Operations

All operations support asynchronous execution:

```java
import io.github.eealba.payper.webhooks.v1.api.WebhooksApiClient;
import io.github.eealba.payper.webhooks.v1.model.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class AsyncWebhookExample {
    public static void main(String[] args) {
        var client = WebhooksApiClient.create();
        
        // Create webhook asynchronously
        CompletableFuture<Webhook> webhookFuture = client.webhooks()
                .create()
                .withBody(Webhook.builder()
                        .url("https://example.com/webhooks/paypal")
                        .eventTypes(List.of(
                            EventType.builder()
                                    .name("PAYMENT.CAPTURE.COMPLETED")
                                    .build()
                        ))
                        .build())
                .retrieve()
                .toFuture()
                .thenApply(response -> {
                    if (response.statusCode() == 201) {
                        return response.toEntity();
                    }
                    throw new RuntimeException("Failed to create webhook");
                });
        
        // Handle result
        webhookFuture.thenAccept(webhook -> {
            System.out.println("Webhook created: " + webhook.id());
        }).exceptionally(ex -> {
            System.err.println("Error: " + ex.getMessage());
            return null;
        });
        
        webhookFuture.join();
    }
}
```

---

## Error Handling

```java
import io.github.eealba.payper.webhooks.v1.api.WebhooksApiClient;
import io.github.eealba.payper.core.PayperException;

public class ErrorHandlingExample {
    public static void main(String[] args) {
        var client = WebhooksApiClient.create();
        
        try {
            var webhook = client.webhooks()
                    .get()
                    .withId("INVALID_WEBHOOK_ID")
                    .retrieve()
                    .toEntity();
            
        } catch (PayperException ex) {
            System.err.println("API Error: " + ex.getMessage());
            System.err.println("Status Code: " + ex.statusCode());
            
            if (ex.statusCode() == 404) {
                System.err.println("Webhook not found");
            }
        }
    }
}
```

---

## Best Practices

### ✅ Do

- Use HTTPS URLs for webhook endpoints
- Verify webhook signatures
- Respond quickly (within 30 seconds)
- Process events asynchronously
- Implement retry logic for failed processing
- Log all webhook events
- Handle duplicate events (idempotency)

### ❌ Don't

- Use HTTP (non-secure) URLs
- Perform long-running operations in webhook handler
- Trust webhook data without signature verification
- Expose webhook URLs publicly
- Process events synchronously

---

## Related Resources

- **[Official PayPal Webhooks API Documentation](https://developer.paypal.com/docs/api/webhooks/v1/)**
- **[Webhook Events Reference](https://developer.paypal.com/api/rest/webhooks/)** - Complete list of events
- **[Authentication Guide](../getting-started/authentication.md)** - Configure credentials
- **[Error Handling](../advanced/error-handling.md)** - Advanced error handling strategies
