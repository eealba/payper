# Configuration

Learn how to customize Payper's configuration for advanced use cases.

---

## Overview

Payper uses the `PayperConfig` class for configuration. You can customize:

- HTTP client settings
- Executor service for async operations
- Connection and read timeouts
- Proxy configuration
- Base URL override
- Authentication settings

---

## Basic Configuration

### Default Configuration

The simplest way to use Payper is with default configuration:

```java
import io.github.eealba.payper.orders.v2.api.OrdersApiClient;

// Uses environment variables for credentials
var client = OrdersApiClient.create();
```

This automatically:
- Reads credentials from environment variables
- Uses default HTTP client
- Uses default timeouts (30s connect, 60s read)
- Uses ForkJoinPool for async operations

---

## Custom Configuration

### Creating a Custom Configuration

```java
import io.github.eealba.payper.core.PayperConfig;
import io.github.eealba.payper.core.PayperAuthenticator;
import io.github.eealba.payper.orders.v2.api.OrdersApiClient;

import java.time.Duration;

PayperConfig config = PayperConfig.builder()
        .authenticator(PayperAuthenticator.PayperAuthenticators
                .ofSandBox(
                    () -> "CLIENT_ID".toCharArray(),
                    () -> "CLIENT_SECRET".toCharArray()
                ))
        .connectTimeout(Duration.ofSeconds(20))
        .readTimeout(Duration.ofSeconds(90))
        .build();

var client = OrdersApiClient.create(config);
```

---

## Configuration Options

### 1. Custom HTTP Client

Provide your own configured `HttpClient`:

```java
import java.net.http.HttpClient;
import java.time.Duration;

HttpClient httpClient = HttpClient.newBuilder()
        .version(HttpClient.Version.HTTP_2)
        .connectTimeout(Duration.ofSeconds(30))
        .followRedirects(HttpClient.Redirect.NORMAL)
        .build();

PayperConfig config = PayperConfig.builder()
        .httpClient(httpClient)
        .build();
```

**Use cases**:
- Custom SSL/TLS configuration
- Custom redirect policies
- HTTP/1.1 vs HTTP/2 preference
- Connection pooling settings

---

### 2. Custom Executor Service

Customize the thread pool for async operations:

```java
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

ExecutorService executor = Executors.newFixedThreadPool(10);

PayperConfig config = PayperConfig.builder()
        .executor(executor)
        .build();
```

**Use cases**:
- Control thread pool size
- Use virtual threads (Java 21+)
- Custom thread naming
- Resource management

**Example with Virtual Threads (Java 21+)**:

```java
ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

PayperConfig config = PayperConfig.builder()
        .executor(executor)
        .build();
```

---

### 3. Connection Timeout

Set how long to wait when establishing a connection:

```java
import java.time.Duration;

PayperConfig config = PayperConfig.builder()
        .connectTimeout(Duration.ofSeconds(15))
        .build();
```

**Default**: 30 seconds

**Recommendations**:
- **Fast networks**: 10-15 seconds
- **Standard**: 20-30 seconds
- **Slow/unstable networks**: 45-60 seconds

---

### 4. Read Timeout

Set how long to wait for a response after connection:

```java
PayperConfig config = PayperConfig.builder()
        .readTimeout(Duration.ofSeconds(120))
        .build();
```

**Default**: 60 seconds

**Recommendations**:
- **Simple operations**: 30-60 seconds
- **Complex operations**: 90-120 seconds
- **Long-running operations**: 180+ seconds

---

### 5. Proxy Configuration

Route requests through a proxy:

```java
import java.net.InetSocketAddress;
import java.net.ProxySelector;
import java.net.http.HttpClient;

ProxySelector proxySelector = ProxySelector.of(
    new InetSocketAddress("proxy.example.com", 8080)
);

HttpClient httpClient = HttpClient.newBuilder()
        .proxy(proxySelector)
        .build();

PayperConfig config = PayperConfig.builder()
        .httpClient(httpClient)
        .build();
```

**With Authentication**:

```java
import java.net.Authenticator;
import java.net.PasswordAuthentication;

Authenticator proxyAuth = new Authenticator() {
    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(
            "proxyUser",
            "proxyPass".toCharArray()
        );
    }
};

HttpClient httpClient = HttpClient.newBuilder()
        .proxy(proxySelector)
        .authenticator(proxyAuth)
        .build();
```

---

### 6. Base URL Override

Override the PayPal API base URL:

```java
PayperConfig config = PayperConfig.builder()
        .baseUrl("https://api-m.sandbox.paypal.com")
        .build();
```

**Use cases**:
- Switch between sandbox and production
- Use regional endpoints (if available)
- Testing with mock servers

**Environments**:
- **Sandbox**: `https://api-m.sandbox.paypal.com`
- **Live**: `https://api-m.paypal.com`

---

### 7. Authentication Configuration

Configure authentication credentials:

=== "Sandbox"

    ```java
    PayperConfig config = PayperConfig.builder()
            .authenticator(PayperAuthenticator.PayperAuthenticators
                    .ofSandBox(
                        () -> getSandboxClientId(),
                        () -> getSandboxClientSecret()
                    ))
            .build();
    ```

=== "Live"

    ```java
    PayperConfig config = PayperConfig.builder()
            .authenticator(PayperAuthenticator.PayperAuthenticators
                    .ofLive(
                        () -> getLiveClientId(),
                        () -> getLiveClientSecret()
                    ))
            .build();
    ```

=== "Custom Base URL"

    ```java
    PayperConfig config = PayperConfig.builder()
            .authenticator(PayperAuthenticator.PayperAuthenticators
                    .of(
                        "https://custom-api.example.com",
                        () -> getClientId(),
                        () -> getClientSecret()
                    ))
            .build();
    ```

---

## Complete Configuration Example

```java
import io.github.eealba.payper.core.PayperConfig;
import io.github.eealba.payper.core.PayperAuthenticator;
import io.github.eealba.payper.orders.v2.api.OrdersApiClient;

import java.net.http.HttpClient;
import java.time.Duration;
import java.util.concurrent.Executors;

public class ConfigurationExample {
    public static void main(String[] args) {
        // Custom HTTP client
        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(25))
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();
        
        // Custom executor (virtual threads on Java 21+)
        var executor = Executors.newVirtualThreadPerTaskExecutor();
        
        // Build comprehensive configuration
        PayperConfig config = PayperConfig.builder()
                .authenticator(PayperAuthenticator.PayperAuthenticators
                        .ofSandBox(
                            () -> getClientIdFromVault(),
                            () -> getClientSecretFromVault()
                        ))
                .httpClient(httpClient)
                .executor(executor)
                .connectTimeout(Duration.ofSeconds(25))
                .readTimeout(Duration.ofSeconds(90))
                .baseUrl("https://api-m.sandbox.paypal.com")
                .build();
        
        // Create client with custom configuration
        var client = OrdersApiClient.create(config);
        
        // Use the client
        // ...
    }
    
    private static char[] getClientIdFromVault() {
        // Retrieve from secure vault
        return System.getenv("PAYPAL-CLIENT-ID").toCharArray();
    }
    
    private static char[] getClientSecretFromVault() {
        // Retrieve from secure vault
        return System.getenv("PAYPAL-CLIENT-SECRET").toCharArray();
    }
}
```

---

## Best Practices

### ✅ Do

- **Reuse client instances** - Create once, use many times
- **Use environment-specific configs** - Different settings for dev/staging/prod
- **Set appropriate timeouts** - Based on your network and use case
- **Use virtual threads** - On Java 21+ for better resource efficiency
- **Externalize credentials** - Never hard-code in configuration
- **Monitor performance** - Adjust settings based on metrics

### ❌ Don't

- **Create clients repeatedly** - Expensive operation, reuse instead
- **Use tiny timeouts** - Can cause false failures
- **Set unlimited timeouts** - Can cause resource exhaustion
- **Share executors carelessly** - May cause thread starvation
- **Ignore connection pooling** - Use HTTP/2 for better performance

---

## Environment-Specific Configuration

### Development

```java
PayperConfig devConfig = PayperConfig.builder()
        .authenticator(PayperAuthenticator.PayperAuthenticators.ofSandBox(
            () -> getDevClientId(),
            () -> getDevClientSecret()
        ))
        .connectTimeout(Duration.ofSeconds(30))
        .readTimeout(Duration.ofSeconds(60))
        .build();
```

### Staging

```java
PayperConfig stagingConfig = PayperConfig.builder()
        .authenticator(PayperAuthenticator.PayperAuthenticators.ofSandBox(
            () -> getStagingClientId(),
            () -> getStagingClientSecret()
        ))
        .connectTimeout(Duration.ofSeconds(20))
        .readTimeout(Duration.ofSeconds(90))
        .build();
```

### Production

```java
PayperConfig prodConfig = PayperConfig.builder()
        .authenticator(PayperAuthenticator.PayperAuthenticators.ofLive(
            () -> getProdClientId(),
            () -> getProdClientSecret()
        ))
        .httpClient(HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build())
        .executor(Executors.newVirtualThreadPerTaskExecutor()) // Java 21+
        .connectTimeout(Duration.ofSeconds(15))
        .readTimeout(Duration.ofSeconds(120))
        .build();
```

---

## Performance Tuning

### High-Throughput Scenarios

```java
// Optimize for many concurrent requests
HttpClient httpClient = HttpClient.newBuilder()
        .version(HttpClient.Version.HTTP_2)
        .build();

var executor = Executors.newFixedThreadPool(
    Runtime.getRuntime().availableProcessors() * 2
);

PayperConfig config = PayperConfig.builder()
        .httpClient(httpClient)
        .executor(executor)
        .connectTimeout(Duration.ofSeconds(10))
        .readTimeout(Duration.ofSeconds(45))
        .build();
```

### Low-Latency Scenarios

```java
// Optimize for fast responses
PayperConfig config = PayperConfig.builder()
        .connectTimeout(Duration.ofSeconds(5))
        .readTimeout(Duration.ofSeconds(15))
        .build();
```

### Resource-Constrained Environments

```java
// Optimize for limited resources
var executor = Executors.newFixedThreadPool(2);

PayperConfig config = PayperConfig.builder()
        .executor(executor)
        .connectTimeout(Duration.ofSeconds(45))
        .readTimeout(Duration.ofSeconds(120))
        .build();
```

---

## Troubleshooting

### Connection Timeouts

If experiencing frequent connection timeouts:
- Increase `connectTimeout`
- Check network connectivity
- Verify firewall rules
- Consider using a proxy

### Read Timeouts

If experiencing read timeouts:
- Increase `readTimeout`
- Check PayPal API status
- Reduce request complexity
- Consider async operations

### Thread Starvation

If async operations are slow:
- Increase executor thread pool size
- Use virtual threads (Java 21+)
- Monitor thread usage
- Consider separate executors for different operations

---

## Related Resources

- **[Authentication Guide](../getting-started/authentication.md)** - Configure credentials
- **[Async Operations](async.md)** - Learn about async patterns
- **[Error Handling](error-handling.md)** - Handle configuration errors
