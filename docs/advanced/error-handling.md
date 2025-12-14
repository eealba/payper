# Error Handling

Learn how to handle errors gracefully when working with the PayPal API.

---

## Overview

Payper provides structured error handling through:

- **Typed exceptions** - `PayperException` for API errors
- **HTTP status codes** - Access response status codes
- **Error details** - Extract error messages and details from responses
- **Response inspection** - Access raw HTTP responses

---

## Types of Errors

### 1. Authentication Errors

Invalid credentials, expired tokens:

```java
// Use toResponse() to check status codes
var response = client.products()
        .create()
        .withBody(productRequest)
        .retrieve()
        .toResponse();

if (response.statusCode() == 401) {
    System.err.println("Authentication failed");
    System.err.println("Check your Client ID and Secret");
    // Access error details
    var errorEntity = response.toErrorEntity();
    System.err.println("Error: " + errorEntity.message());
} else if (response.isSuccessful()) {
    var product = response.toEntity();
    System.out.println("Product created: " + product.id());
} else {
    System.err.println("Unexpected error: " + response.statusCode());
}
```

**Common causes**:
- Wrong Client ID or Secret
- Expired credentials
- Using sandbox credentials with live URL (or vice versa)
- Missing required OAuth scopes

---

### 2. Validation Errors

Invalid request data:

```java
var response = client.orders()
        .create()
        .withBody(orderRequest)
        .retrieve()
        .toResponse();

if (!response.isSuccessful()) {
    System.err.println("Request failed with status: " + response.statusCode());
    
    if (response.statusCode() == 400) {
        System.err.println("Invalid request data");
        var errorEntity = response.toErrorEntity();
        System.err.println("Error details: " + errorEntity.message());
    }
} else {
    var order = response.toEntity();
    System.out.println("Order created: " + order.id());
}
```

**Common validation errors**:
- Missing required fields
- Invalid field values
- Invalid currency codes
- Malformed data

---

### 3. Business Logic Errors

PayPal business rule violations:

```java
var response = client.orders()
        .capture()
        .withId(orderId)
        .withBody(captureRequest)
        .retrieve()
        .toResponse();

switch (response.statusCode()) {
    case 200:
    case 201:
        var order = response.toEntity();
        System.out.println("Order captured: " + order.id());
        break;
    case 422:
        System.err.println("Cannot process request");
        var errorEntity = response.toErrorEntity();
        System.err.println("Reason: " + errorEntity.message());
        // Order may already be captured, cancelled, or expired
        break;
    case 404:
        System.err.println("Order not found");
        // Order ID may be invalid or expired
        break;
    default:
        System.err.println("Unexpected error: " + response.statusCode());
}
```

**Common business errors**:
- Order already captured
- Order expired
- Insufficient funds
- Payment declined
- Subscription already cancelled

---

## Exception Hierarchy

```
Exception
  └── RuntimeException
        └── PayperException
```

`PayperException` is a runtime exception that provides:

- `getMessage()` - Error message
- `getCause()` - Underlying cause (e.g., network error)

For accessing HTTP status codes and response details, use `PayperResponse` directly by calling `toResponse()` or `toFuture()` instead of `toEntity()`.

---

## Handling Errors

### Basic Error Handling

```java
import io.github.eealba.payper.core.client.PayperResponse;

// Get response object to check status
var response = client.orders()
        .get()
        .withId(orderId)
        .retrieve()
        .toResponse();

if (response.isSuccessful()) {
    var order = response.toEntity();
    System.out.println("Order: " + order.id());
} else {
    System.err.println("Error: Status " + response.statusCode());
    
    // Access error details
    var errorEntity = response.toErrorEntity();
    System.err.println("Message: " + errorEntity.message());
}
```

---

### Inspecting HTTP Status Codes

Use `PayperResponse` to access status codes:

```java
import io.github.eealba.payper.core.client.PayperResponse;

try {
    PayperResponse<Product, ?> response = client.products()
            .create()
            .withBody(productRequest)
            .retrieve()
            .toResponse();
    
    switch (response.statusCode()) {
        case 201:
            Product product = response.toEntity();
            System.out.println("Product created: " + product.id());
            break;
        case 400:
            System.err.println("Bad Request - Invalid data");
            break;
        case 401:
            System.err.println("Unauthorized - Check credentials");
            break;
        case 403:
            System.err.println("Forbidden - Insufficient permissions");
            break;
        case 404:
            System.err.println("Not Found - Resource doesn't exist");
            break;
        case 422:
            System.err.println("Unprocessable - Business rule violation");
            break;
        case 429:
            System.err.println("Too Many Requests - Rate limited");
            break;
        case 500:
            System.err.println("Internal Server Error - Try again later");
            break;
        case 503:
            System.err.println("Service Unavailable - PayPal may be down");
            break;
        default:
            System.err.println("Unexpected status: " + response.statusCode());
    }
    
} catch (PayperException ex) {
    System.err.println("Request failed: " + ex.getMessage());
}
```

---

### Extracting Error Details

```java
import io.github.eealba.payper.core.client.PayperResponse;

var response = client.orders()
        .capture()
        .withId(orderId)
        .withBody(captureRequest)
        .retrieve()
        .toResponse();

if (!response.isSuccessful()) {
    System.err.println("Failed to capture order");
    System.err.println("Status: " + response.statusCode());
    
    // Access structured error details
    var errorEntity = response.toErrorEntity();
    System.err.println("Error message: " + errorEntity.message());
    
    // You can also get raw response for detailed analysis
    String rawResponse = response.toRawString();
    System.err.println("Raw response: " + rawResponse);
} else {
    var order = response.toEntity();
    System.out.println("Order captured: " + order.id());
}
```

---

### Working with Response Objects

```java
import io.github.eealba.payper.core.client.PayperResponse;

try {
    PayperResponse response = client.orders()
            .get()
            .withId(orderId)
            .retrieve()
            .toFuture()
            .join();
    
    if (response.statusCode() == 200) {
        Order order = response.toEntity();
        System.out.println("Order: " + order.id());
    } else {
        System.err.println("Error status: " + response.statusCode());
        System.err.println("Body: " + response.toRawString());
    }
    
} catch (Exception ex) {
    System.err.println("Request failed: " + ex.getMessage());
}
```

---

## Retry Strategies

### Simple Retry

```java
import io.github.eealba.payper.core.client.PayperResponse;

public Order getOrderWithRetry(String orderId, int maxRetries) {
    int attempts = 0;
    
    while (attempts < maxRetries) {
        try {
            var response = client.orders()
                    .get()
                    .withId(orderId)
                    .retrieve()
                    .toResponse();
            
            if (response.isSuccessful()) {
                return response.toEntity();
            }
            
            // Check if error is retryable
            if (isRetryable(response.statusCode()) && attempts < maxRetries - 1) {
                attempts++;
                System.err.println("Attempt " + attempts + " failed, retrying...");
                
                try {
                    Thread.sleep(1000 * attempts); // Exponential backoff
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Interrupted during retry", ie);
                }
            } else {
                throw new RuntimeException("Request failed: " + 
                    response.statusCode() + " - " + response.toErrorEntity().message());
            }
            
        } catch (Exception ex) {
            attempts++;
            if (attempts >= maxRetries) {
                throw ex;
            }
        }
    }
    
    throw new RuntimeException("Max retries exceeded");
}

private boolean isRetryable(int statusCode) {
    return statusCode == 429 ||  // Rate limit
           statusCode == 500 ||  // Internal server error
           statusCode == 503;    // Service unavailable
}
```

---

### Exponential Backoff

```java
import java.time.Duration;
import io.github.eealba.payper.core.client.PayperResponse;

public class RetryHelper {
    public static <T> T executeWithRetry(
            Supplier<PayperResponse<T, ?>> operation,
            int maxRetries,
            Duration initialDelay) {
        
        int attempts = 0;
        Duration delay = initialDelay;
        
        while (true) {
            try {
                var response = operation.get();
                
                if (response.isSuccessful()) {
                    return response.toEntity();
                }
                
                attempts++;
                
                if (!isRetryable(response.statusCode()) || attempts >= maxRetries) {
                    throw new RuntimeException("Request failed: " + 
                        response.statusCode() + " - " + response.toErrorEntity().message());
                }
                
                System.err.println("Attempt " + attempts + " failed (status " + 
                        response.statusCode() + "), retrying in " + delay.toSeconds() + "s...");
                
                try {
                    Thread.sleep(delay.toMillis());
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Interrupted during retry", ie);
                }
                
                // Exponential backoff
                delay = delay.multipliedBy(2);
                
            } catch (Exception ex) {
                throw new RuntimeException("Request failed", ex);
            }
        }
    }
    
    private static boolean isRetryable(int statusCode) {
        return statusCode == 429 || statusCode == 500 || 
               statusCode == 503;
    }
}

// Usage
var response = RetryHelper.executeWithRetry(
    () -> client.orders().get().withId("ORDER-1").retrieve().toResponse(),
    3,
    Duration.ofSeconds(1)
);
```

---

### Circuit Breaker Pattern

```java
public class CircuitBreaker {
    private int failureCount = 0;
    private int threshold = 5;
    private long resetTimeout = 60000; // 1 minute
    private long lastFailureTime = 0;
    private State state = State.CLOSED;
    
    enum State { CLOSED, OPEN, HALF_OPEN }
    
    public <T> T execute(Supplier<T> operation) {
        if (state == State.OPEN) {
            if (System.currentTimeMillis() - lastFailureTime > resetTimeout) {
                state = State.HALF_OPEN;
            } else {
                throw new RuntimeException("Circuit breaker is OPEN");
            }
        }
        
        try {
            T result = operation.get();
            
            if (state == State.HALF_OPEN) {
                state = State.CLOSED;
                failureCount = 0;
            }
            
            return result;
            
        } catch (PayperException ex) {
            failureCount++;
            lastFailureTime = System.currentTimeMillis();
            
            if (failureCount >= threshold) {
                state = State.OPEN;
            }
            
            throw ex;
        }
    }
}
```

---

## Error Handling in Async

### Using exceptionally()

```java
client.orders()
        .get()
        .withId(orderId)
        .retrieve()
        .toFuture()
        .thenApply(response -> {
            if (response.isSuccessful()) {
                return response.toEntity();
            } else {
                throw new RuntimeException("Request failed: " + 
                    response.statusCode() + " - " + response.toErrorEntity().message());
            }
        })
        .exceptionally(ex -> {
            System.err.println("Error: " + ex.getMessage());
            return null; // Return fallback value
        })
        .join();
```

### Using handle()

```java
client.orders()
        .capture()
        .withId(orderId)
        .withBody(captureRequest)
        .retrieve()
        .toFuture()
        .handle((response, ex) -> {
            if (ex != null) {
                System.err.println("Capture failed: " + ex.getMessage());
                return createDefaultOrder();
            }
            
            if (response.isSuccessful()) {
                return response.toEntity();
            } else {
                System.err.println("Capture failed with status: " + response.statusCode());
                System.err.println("Error: " + response.toErrorEntity().message());
                return createDefaultOrder();
            }
        })
        .join();
```

---

## Best Practices

### ✅ Do

- **Always handle errors** - Never ignore exceptions
- **Check status codes** - Different codes require different handling
- **Log errors** - Record errors for debugging
- **Provide user feedback** - Inform users about failures
- **Retry transient errors** - Use exponential backoff
- **Validate input** - Catch errors early
- **Use circuit breakers** - Prevent cascading failures
- **Monitor error rates** - Track and alert on anomalies

### ❌ Don't

- **Catch generic Exception** - Be specific with error handling
- **Ignore error details** - Response body contains useful info
- **Retry indefinitely** - Set max retry limits
- **Expose sensitive info** - Don't show API keys or secrets in errors
- **Block on retries** - Use async retries when possible

---

## Common Errors and Solutions

| Error | Status | Cause | Solution |
|-------|--------|-------|----------|
| Authentication failed | 401 | Invalid credentials | Check Client ID/Secret |
| Resource not found | 404 | Invalid ID or deleted resource | Verify resource ID |
| Validation error | 400 | Invalid request data | Check request format |
| Business rule violation | 422 | Cannot process request | Check resource state |
| Rate limit exceeded | 429 | Too many requests | Implement backoff |
| Internal server error | 500 | PayPal server issue | Retry with backoff |
| Service unavailable | 503 | PayPal maintenance | Retry later |
| Connection timeout | -1 | Network issue | Check connectivity |

---

## Logging Errors

```java
import java.util.logging.Logger;
import java.util.logging.Level;

private static final Logger LOGGER = Logger.getLogger(MyClass.class.getName());

var response = client.orders()
        .capture()
        .withId(orderId)
        .withBody(captureRequest)
        .retrieve()
        .toResponse();

if (response.isSuccessful()) {
    var order = response.toEntity();
    LOGGER.info("Order captured: " + order.id());
} else {
    LOGGER.log(Level.SEVERE, "Failed to capture order: " + orderId);
    LOGGER.severe("Status: " + response.statusCode());
    LOGGER.severe("Error message: " + response.toErrorEntity().message());
    LOGGER.severe("Raw response: " + response.toRawString());
}
```

---

## Testing Error Handling

### Mock Errors

```java
// For unit testing
@Test
public void testOrderNotFound() {
    // Mock client to throw PayperException
    when(mockClient.orders().get().withId(any())
            .retrieve().toEntity())
        .thenThrow(new PayperException("Not found"));
    
    // Test error handling
    assertThrows(PayperException.class, () -> {
        service.getOrder("INVALID-ID");
    });
}
```

### Integration Testing

```java
// Test with real PayPal sandbox
@Test
public void testInvalidOrderCapture() {
    var client = CheckoutOrdersApiClient.create();
    
    var response = client.orders()
            .capture()
            .withId("INVALID-ORDER-ID")
            .withBody(OrderCaptureRequest.builder().build())
            .retrieve()
            .toResponse();
    
    // Verify error response
    assertFalse(response.isSuccessful());
    assertEquals(404, response.statusCode());
    
    // Verify error details are available
    var errorEntity = response.toErrorEntity();
    assertNotNull(errorEntity.message());
}
```

---

## Related Resources

- **[Configuration Guide](configuration.md)** - Configure timeouts
- **[Async Operations](async.md)** - Handle async errors
- **[API Documentation](../apis/orders.md)** - Understand API behavior
- **[PayPal Error Reference](https://developer.paypal.com/api/rest/responses/)** - Official error codes
