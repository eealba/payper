# Orders API

The Orders API v2 enables you to create, update, retrieve, authorize, and capture PayPal orders.

**Official Documentation**: [PayPal Orders API v2](https://developer.paypal.com/docs/api/orders/v2/)

---

## Installation

Add the Orders API dependency to your project:

=== "Maven"

    ```xml
    <dependency>
        <groupId>io.github.eealba.payper</groupId>
        <artifactId>payper-orders-v2</artifactId>
        <version>1.0.0</version>
    </dependency>
    ```

=== "Gradle (Groovy)"

    ```groovy
    implementation 'io.github.eealba.payper:payper-orders-v2:1.0.0'
    ```

=== "Gradle (Kotlin)"

    ```kotlin
    implementation("io.github.eealba.payper:payper-orders-v2:1.0.0")
    ```

---

## Creating the Client

```java
import io.github.eealba.payper.orders.v2.api.CheckoutOrdersApiClient;

public class Example {
    public static void main(String[] args) {
        // Create client (uses environment variables for credentials)
        var client = CheckoutOrdersApiClient.create();
        
        // Access the orders API
        var ordersApi = client.orders();
    }
}
```

---

## Common Operations

### Create an Order

Create a new order with purchase units:

```java
import io.github.eealba.payper.orders.v2.api.CheckoutOrdersApiClient;
import io.github.eealba.payper.orders.v2.model.*;

public class CreateOrderExample {
    public static void main(String[] args) {
        var client = CheckoutOrdersApiClient.create();
        
        // Build the order request
        var orderRequest = OrderRequest.builder()
                .intent(CheckoutPaymentIntent.CAPTURE)
                .purchaseUnits(List.of(
                    PurchaseUnitRequest.builder()
                            .amount(AmountWithBreakdown.builder()
                                    .currencyCode("USD")
                                    .value("100.00")
                                    .build())
                            .build()
                ))
                .build();
        
        // Create the order
        var order = client.orders()
                .create()
                .withBody(orderRequest)
                .retrieve()
                .toEntity();
        
        System.out.println("Order ID: " + order.id());
        System.out.println("Status: " + order.status());
        System.out.println("Approval URL: " + 
            order.links().stream()
                .filter(link -> "approve".equals(link.rel()))
                .findFirst()
                .map(LinkDescription::href)
                .orElse("N/A"));
    }
}
```

**Expected Output**:
```
Order ID: 5O190127TN364715T
Status: CREATED
Approval URL: https://www.sandbox.paypal.com/checkoutnow?token=5O190127TN364715T
```

---

### Retrieve an Order

Get details of an existing order:

```java
import io.github.eealba.payper.orders.v2.api.CheckoutOrdersApiClient;

public class GetOrderExample {
    public static void main(String[] args) {
        var client = CheckoutOrdersApiClient.create();
        
        // Retrieve order by ID
        var order = client.orders()
                .get()
                .withId("5O190127TN364715T")
                .retrieve()
                .toEntity();
        
        System.out.println("Order ID: " + order.id());
        System.out.println("Status: " + order.status());
        System.out.println("Amount: " + 
            order.purchaseUnits().get(0).amount().value() + " " +
            order.purchaseUnits().get(0).amount().currencyCode());
    }
}
```

---

### Update an Order

Update order details before it's authorized or captured:

```java
import io.github.eealba.payper.orders.v2.api.CheckoutOrdersApiClient;
import io.github.eealba.payper.orders.v2.model.*;

public class UpdateOrderExample {
    public static void main(String[] args) {
        var client = CheckoutOrdersApiClient.create();
        
        // Create patch operations
        var patches = List.of(
            Patch.builder()
                    .op(Patch.Op.REPLACE)
                    .path("/purchase_units/@reference_id=='default'/amount")
                    .value(AmountWithBreakdown.builder()
                            .currencyCode("USD")
                            .value("150.00")
                            .build())
                    .build()
        );
        
        // Update the order
        client.orders()
                .update()
                .withId("5O190127TN364715T")
                .withBody(new PatchRequest(patches))
                .retrieve()
                .toVoid();
        
        System.out.println("Order updated successfully");
    }
}
```

---

### Authorize Payment

Authorize payment for an order:

```java
import io.github.eealba.payper.orders.v2.api.CheckoutOrdersApiClient;
import io.github.eealba.payper.orders.v2.model.OrderAuthorizeRequest;

public class AuthorizeOrderExample {
    public static void main(String[] args) {
        var client = CheckoutOrdersApiClient.create();
        
        // Authorize the order
        var order = client.orders()
                .authorize()
                .withId("5O190127TN364715T")
                .withBody(OrderAuthorizeRequest.builder().build())
                .retrieve()
                .toEntity();
        
        System.out.println("Order ID: " + order.id());
        System.out.println("Status: " + order.status());
        
        // Get authorization ID
        var authorizationId = order.purchaseUnits().get(0)
                .payments()
                .authorizations().get(0)
                .id();
        System.out.println("Authorization ID: " + authorizationId);
    }
}
```

---

### Capture an Order

Capture payment for an order:

```java
import io.github.eealba.payper.orders.v2.api.CheckoutOrdersApiClient;
import io.github.eealba.payper.orders.v2.model.OrderCaptureRequest;

public class CaptureOrderExample {
    public static void main(String[] args) {
        var client = CheckoutOrdersApiClient.create();
        
        // Capture the order
        var order = client.orders()
                .capture()
                .withId("5O190127TN364715T")
                .withBody(OrderCaptureRequest.builder().build())
                .retrieve()
                .toEntity();
        
        System.out.println("Order ID: " + order.id());
        System.out.println("Status: " + order.status());
        
        // Get capture details
        var capture = order.purchaseUnits().get(0)
                .payments()
                .captures().get(0);
        System.out.println("Capture ID: " + capture.id());
        System.out.println("Amount: " + capture.amount().value() + " " + 
                          capture.amount().currencyCode());
    }
}
```

**Expected Output**:
```
Order ID: 5O190127TN364715T
Status: COMPLETED
Capture ID: 2GG279541U471931P
Amount: 100.00 USD
```

---

## Async Operations

All operations support asynchronous execution using `CompletableFuture`:

```java
import io.github.eealba.payper.orders.v2.api.CheckoutOrdersApiClient;
import io.github.eealba.payper.orders.v2.model.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class AsyncOrderExample {
    public static void main(String[] args) {
        var client = CheckoutOrdersApiClient.create();
        
        // Create order asynchronously
        CompletableFuture<Order> futureOrder = client.orders()
                .create()
                .withBody(OrderRequest.builder()
                        .intent(CheckoutPaymentIntent.CAPTURE)
                        .purchaseUnits(List.of(
                            PurchaseUnitRequest.builder()
                                    .amount(AmountWithBreakdown.builder()
                                            .currencyCode("USD")
                                            .value("100.00")
                                            .build())
                                    .build()
                        ))
                        .build())
                .retrieve()
                .toFuture()
                .thenApply(response -> {
                    if (response.statusCode() == 201) {
                        return response.toEntity();
                    }
                    throw new RuntimeException("Failed to create order");
                });
        
        // Handle the result
        futureOrder.thenAccept(order -> {
            System.out.println("Order created: " + order.id());
        }).exceptionally(ex -> {
            System.err.println("Error: " + ex.getMessage());
            return null;
        });
        
        // Wait for completion (in real app, you wouldn't block)
        futureOrder.join();
    }
}
```

---

## Error Handling

Handle errors gracefully using `PayperResponse`:

```java
import io.github.eealba.payper.orders.v2.api.CheckoutOrdersApiClient;
import io.github.eealba.payper.core.client.PayperResponse;

public class ErrorHandlingExample {
    public static void main(String[] args) {
        var client = CheckoutOrdersApiClient.create();
        
        var response = client.orders()
                .get()
                .withId("INVALID_ORDER_ID")
                .retrieve()
                .toResponse();
        
        if (response.isSuccessful()) {
            var order = response.toEntity();
            System.out.println("Order: " + order.id());
        } else {
            System.err.println("API Error - Status: " + response.statusCode());
            
            // Access structured error details
            var errorEntity = response.toErrorEntity();
            System.err.println("Error message: " + errorEntity.message());
        }
    }
}
```

---

## Related Resources

- **[Official PayPal Orders API Documentation](https://developer.paypal.com/docs/api/orders/v2/)**
- **[Orders Example](../examples/orders.md)** - Complete order workflow example
- **[Authentication Guide](../getting-started/authentication.md)** - Configure credentials
- **[Async Operations](../advanced/async.md)** - Learn more about async patterns
- **[Error Handling](../advanced/error-handling.md)** - Advanced error handling strategies
