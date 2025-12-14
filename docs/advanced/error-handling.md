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

### 1. Network Errors

Connection failures, timeouts, DNS issues:

```java
import io.github.eealba.payper.core.PayperException;
import java.net.ConnectException;
import java.net.http.HttpTimeoutException;

try {
    var order = client.orders()
            .get()
            .withId("ORDER-1")
            .retrieve()
            .toEntity();
    
} catch (PayperException ex) {
    Throwable cause = ex.getCause();
    
    if (cause instanceof HttpTimeoutException) {
        System.err.println("Request timed out");
        // Retry or notify user
    } else if (cause instanceof ConnectException) {
        System.err.println("Cannot connect to PayPal");
        // Check network connectivity
    }
}
```

---

### 2. Authentication Errors

Invalid credentials, expired tokens:

```java
try {
    var product = client.products()
            .create()
            .withBody(productRequest)
            .retrieve()
            .toEntity();
    
} catch (PayperException ex) {
    if (ex.statusCode() == 401) {
        System.err.println("Authentication failed");
        System.err.println("Check your Client ID and Secret");
        // Verify credentials
    }
}
```

**Common causes**:
- Wrong Client ID or Secret
- Expired credentials
- Using sandbox credentials with live URL (or vice versa)
- Missing required OAuth scopes

---

### 3. Validation Errors

Invalid request data:

```java
try {
    var order = client.orders()
            .create()
            .withBody(invalidOrderRequest)
            .retrieve()
            .toEntity();
    
} catch (PayperException ex) {
    if (ex.statusCode() == 400) {
        System.err.println("Invalid request data");
        System.err.println("Details: " + ex.getMessage());
        
        // Access full response for details
        if (ex.hasResponse()) {
            System.err.println("Response: " + ex.responseBody());
        }
    }
}
```

**Common validation errors**:
- Missing required fields
- Invalid field values
- Invalid currency codes
- Malformed data

---

### 4. Business Logic Errors

PayPal business rule violations:

```java
try {
    var order = client.orders()
            .capture()
            .withId(orderId)
            .withBody(captureRequest)
            .retrieve()
            .toEntity();
    
} catch (PayperException ex) {
    switch (ex.statusCode()) {
        case 422:
            System.err.println("Cannot process request");
            System.err.println("Reason: " + ex.getMessage());
            // Order may already be captured, cancelled, or expired
            break;
        case 404:
            System.err.println("Order not found");
            // Order ID may be invalid or expired
            break;
    }
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

- `statusCode()` - HTTP status code (or -1 if no response)
- `getMessage()` - Error message
- `getCause()` - Underlying cause (e.g., network error)
- `hasResponse()` - Whether HTTP response is available
- `responseBody()` - Raw response body
- `getResponse()` - Full `PayperResponse` object

---

## Handling Errors

### Basic Error Handling

```java
import io.github.eealba.payper.core.PayperException;

try {
    var order = client.orders()
            .get()
            .withId(orderId)
            .retrieve()
            .toEntity();
    
    System.out.println("Order: " + order.id());
    
} catch (PayperException ex) {
    System.err.println("Error: " + ex.getMessage());
    System.err.println("Status Code: " + ex.statusCode());
}
```

---

### Inspecting HTTP Status Codes

```java
try {
    var product = client.products()
            .create()
            .withBody(productRequest)
            .retrieve()
            .toEntity();
    
} catch (PayperException ex) {
    switch (ex.statusCode()) {
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
            System.err.println("Unexpected error: " + ex.statusCode());
    }
}
```

---

### Extracting Error Details

```java
import io.github.eealba.payper.core.PayperException;

try {
    var order = client.orders()
            .capture()
            .withId(orderId)
            .withBody(captureRequest)
            .retrieve()
            .toEntity();
    
} catch (PayperException ex) {
    System.err.println("Failed to capture order");
    System.err.println("Status: " + ex.statusCode());
    System.err.println("Message: " + ex.getMessage());
    
    // Access raw response for detailed error info
    if (ex.hasResponse()) {
        String responseBody = ex.responseBody();
        System.err.println("Response: " + responseBody);
        
        // Parse response JSON for structured error details
        // (You may want to use a JSON library like Jackson or Gson)
    }
    
    // Access underlying cause
    Throwable cause = ex.getCause();
    if (cause != null) {
        System.err.println("Caused by: " + cause.getClass().getName());
        System.err.println("Cause message: " + cause.getMessage());
    }
}
```

---

### Working with Response Objects

```java
import io.github.eealba.payper.core.PayperResponse;

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
        System.err.println("Body: " + response.body());
    }
    
} catch (Exception ex) {
    System.err.println("Request failed: " + ex.getMessage());
}
```

---

## Retry Strategies

### Simple Retry

```java
public Order getOrderWithRetry(String orderId, int maxRetries) {
    int attempts = 0;
    
    while (attempts < maxRetries) {
        try {
            return client.orders()
                    .get()
                    .withId(orderId)
                    .retrieve()
                    .toEntity();
            
        } catch (PayperException ex) {
            attempts++;
            
            // Retry on transient errors
            if (isRetryable(ex) && attempts < maxRetries) {
                System.err.println("Attempt " + attempts + " failed, retrying...");
                
                try {
                    Thread.sleep(1000 * attempts); // Exponential backoff
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw ex;
                }
            } else {
                throw ex;
            }
        }
    }
    
    throw new RuntimeException("Max retries exceeded");
}

private boolean isRetryable(PayperException ex) {
    int status = ex.statusCode();
    return status == 429 ||  // Rate limit
           status == 500 ||  // Internal server error
           status == 503 ||  // Service unavailable
           status == -1;     // Network error
}
```

---

### Exponential Backoff

```java
import java.time.Duration;

public class RetryHelper {
    public static <T> T executeWithRetry(
            Supplier<T> operation,
            int maxRetries,
            Duration initialDelay) {
        
        int attempts = 0;
        Duration delay = initialDelay;
        
        while (true) {
            try {
                return operation.get();
                
            } catch (PayperException ex) {
                attempts++;
                
                if (!isRetryable(ex) || attempts >= maxRetries) {
                    throw ex;
                }
                
                System.err.println("Attempt " + attempts + " failed, " +
                        "retrying in " + delay.toSeconds() + "s...");
                
                try {
                    Thread.sleep(delay.toMillis());
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw ex;
                }
                
                // Exponential backoff
                delay = delay.multipliedBy(2);
            }
        }
    }
    
    private static boolean isRetryable(PayperException ex) {
        int status = ex.statusCode();
        return status == 429 || status == 500 || 
               status == 503 || status == -1;
    }
}

// Usage
Order order = RetryHelper.executeWithRetry(
    () -> client.orders().get().withId("ORDER-1").retrieve().toEntity(),
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
        .thenApply(response -> response.toEntity())
        .exceptionally(ex -> {
            if (ex.getCause() instanceof PayperException) {
                PayperException payperEx = (PayperException) ex.getCause();
                System.err.println("API Error: " + payperEx.statusCode());
            } else {
                System.err.println("Error: " + ex.getMessage());
            }
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
        .thenApply(response -> response.toEntity())
        .handle((order, ex) -> {
            if (ex != null) {
                System.err.println("Capture failed: " + ex.getMessage());
                // Log, notify, or return default
                return createDefaultOrder();
            }
            return order;
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

try {
    var order = client.orders()
            .capture()
            .withId(orderId)
            .withBody(captureRequest)
            .retrieve()
            .toEntity();
    
    LOGGER.info("Order captured: " + order.id());
    
} catch (PayperException ex) {
    LOGGER.log(Level.SEVERE, 
        "Failed to capture order: " + orderId, ex);
    
    LOGGER.severe("Status: " + ex.statusCode());
    LOGGER.severe("Message: " + ex.getMessage());
    
    if (ex.hasResponse()) {
        LOGGER.severe("Response: " + ex.responseBody());
    }
    
    throw ex; // Re-throw or handle
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
        .thenThrow(new PayperException("Not found", 404));
    
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
    var client = OrdersApiClient.create();
    
    try {
        // Try to capture non-existent order
        client.orders()
                .capture()
                .withId("INVALID-ORDER-ID")
                .withBody(OrderCaptureRequest.builder().build())
                .retrieve()
                .toEntity();
        
        fail("Expected PayperException");
        
    } catch (PayperException ex) {
        assertEquals(404, ex.statusCode());
    }
}
```

---

## Related Resources

- **[Configuration Guide](configuration.md)** - Configure timeouts
- **[Async Operations](async.md)** - Handle async errors
- **[API Documentation](../apis/orders.md)** - Understand API behavior
- **[PayPal Error Reference](https://developer.paypal.com/api/rest/responses/)** - Official error codes
