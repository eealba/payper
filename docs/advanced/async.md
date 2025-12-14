# Async Operations

Learn how to use asynchronous, non-blocking operations with Payper using `CompletableFuture`.

---

## Overview

Payper provides full support for asynchronous operations through `CompletableFuture`, enabling:

- **Non-blocking I/O** - Don't wait for responses
- **Parallel execution** - Make multiple requests simultaneously
- **Reactive composition** - Chain and combine operations
- **Better resource utilization** - Handle more requests with fewer threads

---

## Why Use Async Operations?

### Synchronous (Blocking)

```java
// Blocks for ~1 second per request
var order1 = client.orders().get().withId("ORDER-1").retrieve().toEntity();
var order2 = client.orders().get().withId("ORDER-2").retrieve().toEntity();
var order3 = client.orders().get().withId("ORDER-3").retrieve().toEntity();
// Total time: ~3 seconds
```

### Asynchronous (Non-Blocking)

```java
// All requests happen in parallel
var future1 = client.orders().get().withId("ORDER-1").retrieve().toFuture();
var future2 = client.orders().get().withId("ORDER-2").retrieve().toFuture();
var future3 = client.orders().get().withId("ORDER-3").retrieve().toFuture();

CompletableFuture.allOf(future1, future2, future3).join();
// Total time: ~1 second
```

**Result**: 3x faster! ⚡

---

## Basic Async Example

### Making an Async Request

```java
import io.github.eealba.payper.orders.v2.api.OrdersApiClient;
import java.util.concurrent.CompletableFuture;

public class AsyncExample {
    public static void main(String[] args) {
        var client = OrdersApiClient.create();
        
        // Async request returns CompletableFuture
        CompletableFuture<Order> orderFuture = client.orders()
                .get()
                .withId("5O190127TN364715T")
                .retrieve()
                .toFuture()
                .thenApply(response -> response.toEntity());
        
        // Non-blocking: do other work here
        System.out.println("Request sent, doing other work...");
        
        // Wait for result when needed
        orderFuture.thenAccept(order -> {
            System.out.println("Order received: " + order.id());
            System.out.println("Status: " + order.status());
        }).join();
    }
}
```

---

## toFuture() vs toEntity()

### Synchronous - toEntity()

```java
// Blocks until response arrives
var order = client.orders()
        .get()
        .withId("ORDER-1")
        .retrieve()
        .toEntity();

// Code here executes AFTER response received
System.out.println("Order: " + order.id());
```

### Asynchronous - toFuture()

```java
// Returns immediately with CompletableFuture
CompletableFuture<PayperResponse> future = client.orders()
        .get()
        .withId("ORDER-1")
        .retrieve()
        .toFuture();

// Code here executes IMMEDIATELY (non-blocking)
System.out.println("Request sent!");

// Handle response later
future.thenApply(response -> response.toEntity())
      .thenAccept(order -> System.out.println("Order: " + order.id()));
```

---

## Chaining Operations

### Sequential Operations

Chain operations that depend on each other:

```java
import io.github.eealba.payper.catalog.products.v1.api.CatalogProductsApiClient;
import io.github.eealba.payper.subscriptions.v1.api.SubscriptionsApiClient;

public class ChainExample {
    public static void main(String[] args) {
        var catalogClient = CatalogProductsApiClient.create();
        var subsClient = SubscriptionsApiClient.create();
        
        // Create product → Create plan → Create subscription
        catalogClient.products()
                .create()
                .withBody(productRequest)
                .retrieve()
                .toFuture()
                .thenApply(response -> response.toEntity())
                .thenCompose(product -> {
                    // Use product.id() to create plan
                    return subsClient.billingPlans()
                            .create()
                            .withBody(planRequest(product.id()))
                            .retrieve()
                            .toFuture();
                })
                .thenApply(response -> response.toEntity())
                .thenCompose(plan -> {
                    // Use plan.id() to create subscription
                    return subsClient.subscriptions()
                            .create()
                            .withBody(subscriptionRequest(plan.id()))
                            .retrieve()
                            .toFuture();
                })
                .thenApply(response -> response.toEntity())
                .thenAccept(subscription -> {
                    System.out.println("✅ Subscription created: " + subscription.id());
                })
                .exceptionally(ex -> {
                    System.err.println("❌ Error: " + ex.getMessage());
                    return null;
                })
                .join();
    }
}
```

---

## Parallel Operations

### Making Multiple Requests in Parallel

```java
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ParallelExample {
    public static void main(String[] args) {
        var client = OrdersApiClient.create();
        
        List<String> orderIds = List.of("ORDER-1", "ORDER-2", "ORDER-3", "ORDER-4");
        
        // Create futures for all orders
        List<CompletableFuture<Order>> futures = orderIds.stream()
                .map(orderId -> client.orders()
                        .get()
                        .withId(orderId)
                        .retrieve()
                        .toFuture()
                        .thenApply(response -> response.toEntity()))
                .toList();
        
        // Wait for all to complete
        CompletableFuture<Void> allOf = CompletableFuture.allOf(
                futures.toArray(new CompletableFuture[0])
        );
        
        // Process results
        allOf.thenRun(() -> {
            futures.forEach(future -> {
                try {
                    Order order = future.get();
                    System.out.println("Order: " + order.id() + " - " + order.status());
                } catch (Exception e) {
                    System.err.println("Error: " + e.getMessage());
                }
            });
        }).join();
    }
}
```

---

## Combining Multiple Futures

### Combine Results

```java
CompletableFuture<Order> order1Future = getOrderAsync("ORDER-1");
CompletableFuture<Order> order2Future = getOrderAsync("ORDER-2");

// Combine both results
CompletableFuture<String> combined = order1Future.thenCombine(
        order2Future,
        (order1, order2) -> {
            double total = Double.parseDouble(order1.purchaseUnits().get(0).amount().value())
                         + Double.parseDouble(order2.purchaseUnits().get(0).amount().value());
            return "Total: $" + total;
        }
);

combined.thenAccept(System.out::println).join();
```

### First to Complete

```java
List<CompletableFuture<Order>> futures = List.of(
    getOrderAsync("ORDER-1"),
    getOrderAsync("ORDER-2"),
    getOrderAsync("ORDER-3")
);

// Use whichever completes first
CompletableFuture<Object> anyOf = CompletableFuture.anyOf(
        futures.toArray(new CompletableFuture[0])
);

anyOf.thenAccept(result -> {
    Order order = (Order) result;
    System.out.println("First order: " + order.id());
}).join();
```

---

## Error Handling in Async

### Using exceptionally()

```java
client.orders()
        .get()
        .withId("INVALID-ID")
        .retrieve()
        .toFuture()
        .thenApply(response -> response.toEntity())
        .thenAccept(order -> {
            System.out.println("Order: " + order.id());
        })
        .exceptionally(ex -> {
            System.err.println("Error fetching order: " + ex.getMessage());
            return null; // Return default value
        })
        .join();
```

### Using handle()

```java
client.orders()
        .get()
        .withId("ORDER-1")
        .retrieve()
        .toFuture()
        .thenApply(response -> response.toEntity())
        .handle((order, ex) -> {
            if (ex != null) {
                System.err.println("Error: " + ex.getMessage());
                return null;
            } else {
                System.out.println("Order: " + order.id());
                return order;
            }
        })
        .join();
```

### Using whenComplete()

```java
client.orders()
        .get()
        .withId("ORDER-1")
        .retrieve()
        .toFuture()
        .thenApply(response -> response.toEntity())
        .whenComplete((order, ex) -> {
            if (ex != null) {
                System.err.println("Error: " + ex.getMessage());
            } else {
                System.out.println("Success: " + order.id());
            }
        })
        .join();
```

---

## Advanced Patterns

### Timeout Handling

```java
import java.time.Duration;
import java.util.concurrent.TimeUnit;

CompletableFuture<Order> orderFuture = client.orders()
        .get()
        .withId("ORDER-1")
        .retrieve()
        .toFuture()
        .thenApply(response -> response.toEntity())
        .orTimeout(5, TimeUnit.SECONDS) // Timeout after 5 seconds
        .exceptionally(ex -> {
            if (ex instanceof java.util.concurrent.TimeoutException) {
                System.err.println("Request timed out");
            }
            return null;
        });

orderFuture.join();
```

### Retry Logic

```java
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class RetryHelper {
    public static <T> CompletableFuture<T> retryAsync(
            Supplier<CompletableFuture<T>> operation,
            int maxRetries) {
        
        CompletableFuture<T> future = operation.get();
        
        for (int i = 0; i < maxRetries; i++) {
            final int attempt = i;
            future = future.exceptionally(ex -> null)
                    .thenCompose(result -> {
                        if (result == null) {
                            System.out.println("Retry attempt " + (attempt + 1));
                            return operation.get();
                        }
                        return CompletableFuture.completedFuture(result);
                    });
        }
        
        return future;
    }
}

// Usage
CompletableFuture<Order> orderFuture = RetryHelper.retryAsync(
    () -> client.orders().get().withId("ORDER-1").retrieve()
            .toFuture().thenApply(r -> r.toEntity()),
    3
);
```

### Fallback Values

```java
CompletableFuture<Order> orderFuture = client.orders()
        .get()
        .withId("ORDER-1")
        .retrieve()
        .toFuture()
        .thenApply(response -> response.toEntity())
        .exceptionally(ex -> {
            // Return fallback/default order
            return createDefaultOrder();
        });
```

---

## Best Practices

### ✅ Do

- **Use async for I/O operations** - Network calls, file I/O
- **Combine parallel requests** - Fetch multiple resources at once
- **Handle errors properly** - Always use `.exceptionally()` or `.handle()`
- **Set timeouts** - Prevent hanging operations
- **Use appropriate executors** - Configure thread pools for your needs
- **Avoid blocking** - Don't call `.get()` or `.join()` unnecessarily

### ❌ Don't

- **Block in callbacks** - Keep callbacks fast and non-blocking
- **Ignore exceptions** - Always handle errors
- **Create too many threads** - Use thread pools appropriately
- **Mix blocking and async** - Choose one pattern per operation
- **Forget to join/complete** - Ensure futures complete properly

---

## Performance Considerations

### Thread Pool Configuration

```java
import java.util.concurrent.Executors;

// For CPU-intensive tasks
var executor = Executors.newFixedThreadPool(
    Runtime.getRuntime().availableProcessors()
);

// For I/O-intensive tasks (like API calls)
var executor = Executors.newFixedThreadPool(
    Runtime.getRuntime().availableProcessors() * 2
);

// For maximum concurrency (Java 21+)
var executor = Executors.newVirtualThreadPerTaskExecutor();

PayperConfig config = PayperConfig.builder()
        .executor(executor)
        .build();
```

### Memory Management

```java
// Process large result sets in chunks
List<String> orderIds = getLargeOrderIdList(); // e.g., 1000 ids

// Don't create 1000 futures at once!
// Instead, process in batches
int batchSize = 50;
for (int i = 0; i < orderIds.size(); i += batchSize) {
    List<String> batch = orderIds.subList(
        i,
        Math.min(i + batchSize, orderIds.size())
    );
    
    List<CompletableFuture<Order>> futures = batch.stream()
            .map(this::getOrderAsync)
            .toList();
    
    CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
            .join();
    
    // Process batch results
}
```

---

## Complete Example

```java
import io.github.eealba.payper.orders.v2.api.OrdersApiClient;
import io.github.eealba.payper.orders.v2.model.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class AsyncCompleteExample {
    public static void main(String[] args) {
        var client = OrdersApiClient.create();
        
        System.out.println("🚀 Starting async operations...\n");
        
        // 1. Create order
        CompletableFuture<Order> createFuture = createOrderAsync(client);
        
        // 2. After creation, capture it
        CompletableFuture<Order> captureFuture = createFuture
                .thenCompose(order -> {
                    System.out.println("✓ Order created: " + order.id());
                    return captureOrderAsync(client, order.id());
                });
        
        // 3. Handle final result
        captureFuture
                .thenAccept(order -> {
                    System.out.println("✓ Order captured: " + order.id());
                    System.out.println("  Status: " + order.status());
                })
                .exceptionally(ex -> {
                    System.err.println("❌ Error: " + ex.getMessage());
                    return null;
                })
                .join();
        
        System.out.println("\n✅ All async operations completed!");
    }
    
    private static CompletableFuture<Order> createOrderAsync(OrdersApiClient client) {
        return client.orders()
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
                .thenApply(response -> response.toEntity());
    }
    
    private static CompletableFuture<Order> captureOrderAsync(
            OrdersApiClient client, String orderId) {
        return client.orders()
                .capture()
                .withId(orderId)
                .withBody(OrderCaptureRequest.builder().build())
                .retrieve()
                .toFuture()
                .thenApply(response -> response.toEntity());
    }
}
```

---

## Related Resources

- **[Configuration Guide](configuration.md)** - Configure executor services
- **[Error Handling](error-handling.md)** - Handle async errors
- **[Subscriptions Example](../examples/subscriptions.md)** - Real async example
- **[Java CompletableFuture](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/concurrent/CompletableFuture.html)** - Official documentation
