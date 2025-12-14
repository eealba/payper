# Payments API

The Payments API v2 enables you to capture authorized payments and process refunds.

**Official Documentation**: [PayPal Payments API v2](https://developer.paypal.com/docs/api/payments/v2/)

---

## Installation

Add the Payments API dependency to your project:

=== "Maven"

    ```xml
    <dependency>
        <groupId>io.github.eealba.payper</groupId>
        <artifactId>payper-payments-v2</artifactId>
        <version>1.0.0</version>
    </dependency>
    ```

=== "Gradle (Groovy)"

    ```groovy
    implementation 'io.github.eealba.payper:payper-payments-v2:1.0.0'
    ```

=== "Gradle (Kotlin)"

    ```kotlin
    implementation("io.github.eealba.payper:payper-payments-v2:1.0.0")
    ```

---

## Creating the Client

```java
import io.github.eealba.payper.payments.v2.api.PaymentsApiClient;

public class Example {
    public static void main(String[] args) {
        // Create client (uses environment variables for credentials)
        var client = PaymentsApiClient.create();
        
        // Access various APIs
        var authorizationsApi = client.authorizations();
        var capturesApi = client.captures();
        var refundsApi = client.refunds();
    }
}
```

---

## Common Operations

### Capture an Authorized Payment

Capture funds from a previously authorized payment:

```java
import io.github.eealba.payper.payments.v2.api.PaymentsApiClient;
import io.github.eealba.payper.payments.v2.model.*;

public class CaptureAuthorizationExample {
    public static void main(String[] args) {
        var client = PaymentsApiClient.create();
        
        // Build the capture request
        var captureRequest = CaptureRequest.builder()
                .amount(Money.builder()
                        .currencyCode("USD")
                        .value("50.00")
                        .build())
                .invoiceId("INV-12345")
                .note("Payment for order #12345")
                .finalCapture(true)
                .build();
        
        // Capture the authorization
        var capture = client.authorizations()
                .capture()
                .withId("0VF52814937998046")
                .withBody(captureRequest)
                .retrieve()
                .toEntity();
        
        System.out.println("Capture ID: " + capture.id());
        System.out.println("Status: " + capture.status());
        System.out.println("Amount: " + capture.amount().value() + " " + 
                          capture.amount().currencyCode());
    }
}
```

**Expected Output**:
```
Capture ID: 2GG279541U471931P
Status: COMPLETED
Amount: 50.00 USD
```

---

### Show Capture Details

Retrieve details of a captured payment:

```java
import io.github.eealba.payper.payments.v2.api.PaymentsApiClient;

public class GetCaptureExample {
    public static void main(String[] args) {
        var client = PaymentsApiClient.create();
        
        // Get capture details
        var capture = client.captures()
                .get()
                .withId("2GG279541U471931P")
                .retrieve()
                .toEntity();
        
        System.out.println("Capture ID: " + capture.id());
        System.out.println("Status: " + capture.status());
        System.out.println("Amount: " + capture.amount().value() + " " + 
                          capture.amount().currencyCode());
        System.out.println("Create Time: " + capture.createTime());
        System.out.println("Update Time: " + capture.updateTime());
    }
}
```

---

### Refund a Captured Payment

Issue a full or partial refund for a captured payment:

```java
import io.github.eealba.payper.payments.v2.api.PaymentsApiClient;
import io.github.eealba.payper.payments.v2.model.*;

public class RefundCaptureExample {
    public static void main(String[] args) {
        var client = PaymentsApiClient.create();
        
        // Build the refund request (full refund)
        var refundRequest = RefundRequest.builder()
                .note("Customer requested refund")
                .build();
        
        // Issue the refund
        var refund = client.captures()
                .refund()
                .withId("2GG279541U471931P")
                .withBody(refundRequest)
                .retrieve()
                .toEntity();
        
        System.out.println("Refund ID: " + refund.id());
        System.out.println("Status: " + refund.status());
        System.out.println("Amount: " + refund.amount().value() + " " + 
                          refund.amount().currencyCode());
    }
}
```

**For a partial refund**, specify the amount:

```java
var refundRequest = RefundRequest.builder()
        .amount(Money.builder()
                .currencyCode("USD")
                .value("25.00")
                .build())
        .note("Partial refund for damaged item")
        .build();
```

---

### Show Refund Details

Retrieve details of a refund:

```java
import io.github.eealba.payper.payments.v2.api.PaymentsApiClient;

public class GetRefundExample {
    public static void main(String[] args) {
        var client = PaymentsApiClient.create();
        
        // Get refund details
        var refund = client.refunds()
                .get()
                .withId("1JU08902781691411")
                .retrieve()
                .toEntity();
        
        System.out.println("Refund ID: " + refund.id());
        System.out.println("Status: " + refund.status());
        System.out.println("Amount: " + refund.amount().value() + " " + 
                          refund.amount().currencyCode());
        System.out.println("Create Time: " + refund.createTime());
    }
}
```

---

## Async Operations

All operations support asynchronous execution:

```java
import io.github.eealba.payper.payments.v2.api.PaymentsApiClient;
import io.github.eealba.payper.payments.v2.model.*;

import java.util.concurrent.CompletableFuture;

public class AsyncPaymentExample {
    public static void main(String[] args) {
        var client = PaymentsApiClient.create();
        
        // Capture authorization asynchronously
        CompletableFuture<Capture> captureAsync = client.authorizations()
                .capture()
                .withId("0VF52814937998046")
                .withBody(CaptureRequest.builder()
                        .amount(Money.builder()
                                .currencyCode("USD")
                                .value("50.00")
                                .build())
                        .finalCapture(true)
                        .build())
                .retrieve()
                .toFuture()
                .thenApply(response -> {
                    if (response.statusCode() == 201) {
                        return response.toEntity();
                    }
                    throw new RuntimeException("Capture failed");
                });
        
        // Chain operations
        captureAsync.thenAccept(capture -> {
            System.out.println("Captured: " + capture.id());
            
            // Optionally issue refund
            if (needsRefund()) {
                issueRefund(client, capture.id());
            }
        }).exceptionally(ex -> {
            System.err.println("Error: " + ex.getMessage());
            return null;
        });
        
        captureAsync.join();
    }
    
    private static boolean needsRefund() {
        return false; // Your business logic
    }
    
    private static void issueRefund(PaymentsApiClient client, String captureId) {
        // Refund implementation
    }
}
```

---

## Error Handling

Handle errors gracefully:

```java
import io.github.eealba.payper.payments.v2.api.PaymentsApiClient;
import io.github.eealba.payper.core.PayperException;
import io.github.eealba.payper.payments.v2.model.*;

public class ErrorHandlingExample {
    public static void main(String[] args) {
        var client = PaymentsApiClient.create();
        
        try {
            var capture = client.authorizations()
                    .capture()
                    .withId("INVALID_AUTH_ID")
                    .withBody(CaptureRequest.builder()
                            .amount(Money.builder()
                                    .currencyCode("USD")
                                    .value("50.00")
                                    .build())
                            .build())
                    .retrieve()
                    .toResponse();
            
            if (response.isSuccessful()) {
                var capture = response.toEntity();
                System.out.println("Captured: " + capture.id());
            } else {
                System.err.println("API Error - Status: " + response.statusCode());
                
                // Handle specific error codes
                switch (response.statusCode()) {
                    case 404:
                        System.err.println("Authorization not found");
                        break;
                    case 422:
                        System.err.println("Invalid request parameters");
                        System.err.println("Details: " + response.toErrorEntity().message());
                        break;
                    default:
                        System.err.println("Error: " + response.toErrorEntity().message());
                }
            }
        }
    }
}
```

---

## Common Use Cases

### Delayed Capture Workflow

1. Create an order with `AUTHORIZE` intent
2. Customer completes checkout
3. Fulfill the order
4. Capture the authorized payment

```java
// Step 1: Create order with AUTHORIZE intent (using Orders API)
// Step 2: Customer approves
// Step 3: Fulfill order
// Step 4: Capture payment
var capture = client.authorizations()
        .capture()
        .withId(authorizationId)
        .withBody(CaptureRequest.builder().finalCapture(true).build())
        .retrieve()
        .toEntity();
```

### Partial Captures

Capture only a portion of the authorized amount:

```java
// Authorized: $100.00
// Capture: $75.00
var capture = client.authorizations()
        .capture()
        .withId(authorizationId)
        .withBody(CaptureRequest.builder()
                .amount(Money.builder()
                        .currencyCode("USD")
                        .value("75.00")
                        .build())
                .finalCapture(false) // Allow additional captures
                .build())
        .retrieve()
        .toEntity();
```

### Full and Partial Refunds

```java
// Full refund (no amount specified)
var fullRefund = client.captures()
        .refund()
        .withId(captureId)
        .withBody(RefundRequest.builder()
                .note("Full refund")
                .build())
        .retrieve()
        .toEntity();

// Partial refund
var partialRefund = client.captures()
        .refund()
        .withId(captureId)
        .withBody(RefundRequest.builder()
                .amount(Money.builder()
                        .currencyCode("USD")
                        .value("10.00")
                        .build())
                .note("Partial refund for shipping")
                .build())
        .retrieve()
        .toEntity();
```

---

## Related Resources

- **[Official PayPal Payments API Documentation](https://developer.paypal.com/docs/api/payments/v2/)**
- **[Orders API](orders.md)** - Create and authorize orders before capturing
- **[Authentication Guide](../getting-started/authentication.md)** - Configure credentials
- **[Error Handling](../advanced/error-handling.md)** - Advanced error handling strategies
