# Authentication

Payper uses PayPal's OAuth 2.0 authentication to secure API requests. This guide explains how to configure your credentials.

## Overview

PayPal REST APIs use OAuth 2.0 access tokens to authenticate requests. Payper automatically handles the entire OAuth flow:

- ✅ Requests access tokens from PayPal
- ✅ Caches tokens for reuse
- ✅ Automatically refreshes expired tokens
- ✅ Includes tokens in API requests

All you need to do is provide your **Client ID** and **Client Secret**.

---

## Sandbox vs. Live Credentials

PayPal provides two environments:

| Environment | Base URL | Purpose |
|-------------|----------|---------|
| **Sandbox** | `https://api-m.sandbox.paypal.com` | Testing and development |
| **Live** | `https://api-m.paypal.com` | Production |

!!! warning "Different Credentials"
    Sandbox and live environments use different credentials. Make sure you're using the correct credentials for your environment.

To get credentials:

1. Visit [PayPal Developer Dashboard](https://developer.paypal.com/dashboard/)
2. Create or select an app
3. Copy your Client ID and Secret from the app details

---

## Method 1: Environment Variables (Recommended)

The easiest and most secure way to provide credentials is through environment variables.

### For Sandbox

```bash
export PAYPAL-CLIENT-ID=YOUR_SANDBOX_CLIENT_ID
export PAYPAL-CLIENT-SECRET=YOUR_SANDBOX_CLIENT_SECRET
export PAYPAL-BASE-URL=https://api-m.sandbox.paypal.com
```

### For Live

```bash
export PAYPAL-CLIENT-ID=YOUR_LIVE_CLIENT_ID
export PAYPAL-CLIENT-SECRET=YOUR_LIVE_CLIENT_SECRET
export PAYPAL-BASE-URL=https://api-m.paypal.com
```

### Usage

When using environment variables, simply create the client without any configuration:

```java
import io.github.eealba.payper.catalog.products.v1.api.CatalogProductsApiClient;

public class Example {
    public static void main(String[] args) {
        // Automatically reads from environment variables
        var client = CatalogProductsApiClient.create();
        
        // Use the client...
    }
}
```

!!! tip "Best Practice"
    Environment variables are the recommended approach because:
    
    - ✅ Credentials are not hard-coded in your source code
    - ✅ Easy to configure per environment (dev, staging, production)
    - ✅ Works with container orchestration and CI/CD pipelines

---

## Method 2: System Properties

You can also provide credentials using Java system properties:

```java
import io.github.eealba.payper.subscriptions.v1.api.SubscriptionsApiClient;

public class Example {
    public static void main(String[] args) {
        // Set system properties
        System.setProperty("PAYPAL-CLIENT-ID", "YOUR_CLIENT_ID");
        System.setProperty("PAYPAL-CLIENT-SECRET", "YOUR_CLIENT_SECRET");
        System.setProperty("PAYPAL-BASE-URL", "https://api-m.sandbox.paypal.com");
        
        // Create the client
        var client = SubscriptionsApiClient.create();
        
        // Use the client...
    }
}
```

You can also pass system properties via command line:

```bash
java -DPAYPAL-CLIENT-ID=YOUR_CLIENT_ID \
     -DPAYPAL-CLIENT-SECRET=YOUR_CLIENT_SECRET \
     -DPAYPAL-BASE-URL=https://api-m.sandbox.paypal.com \
     -jar your-application.jar
```

---

## Method 3: Programmatic Configuration

For more control, you can configure credentials programmatically using `PayperConfig`:

### Sandbox Configuration

```java
import io.github.eealba.payper.core.PayperConfig;
import io.github.eealba.payper.core.PayperAuthenticator;
import io.github.eealba.payper.orders.v2.api.CheckoutOrdersApiClient;

public class Example {
    public static void main(String[] args) {
        // Build configuration for sandbox
        PayperConfig config = PayperConfig.builder()
                .authenticator(PayperAuthenticator.PayperAuthenticators
                        .ofSandBox(
                            () -> "YOUR_CLIENT_ID".toCharArray(),
                            () -> "YOUR_CLIENT_SECRET".toCharArray()
                        ))
                .build();
        
        // Create client with custom configuration
        var client = CheckoutOrdersApiClient.create(config);
        
        // Use the client...
    }
}
```

### Live Configuration

```java
import io.github.eealba.payper.core.PayperConfig;
import io.github.eealba.payper.core.PayperAuthenticator;
import io.github.eealba.payper.payments.v2.api.PaymentsApiClient;

public class Example {
    public static void main(String[] args) {
        // Build configuration for live
        PayperConfig config = PayperConfig.builder()
                .authenticator(PayperAuthenticator.PayperAuthenticators
                        .ofLive(
                            () -> "YOUR_CLIENT_ID".toCharArray(),
                            () -> "YOUR_CLIENT_SECRET".toCharArray()
                        ))
                .build();
        
        // Create client with custom configuration
        var client = PaymentsApiClient.create(config);
        
        // Use the client...
    }
}
```

### Using Supplier Functions

The credential suppliers allow you to retrieve credentials from a secure store:

```java
import io.github.eealba.payper.core.PayperConfig;
import io.github.eealba.payper.core.PayperAuthenticator;
import io.github.eealba.payper.invoices.v2.api.InvoicingApiClient;

public class Example {
    public static void main(String[] args) {
        PayperConfig config = PayperConfig.builder()
                .authenticator(PayperAuthenticator.PayperAuthenticators
                        .ofSandBox(
                            () -> getClientIdFromVault(),
                            () -> getClientSecretFromVault()
                        ))
                .build();
        
        var client = InvoicingApiClient.create(config);
    }
    
    private static char[] getClientIdFromVault() {
        // Retrieve from secure credential store
        return retrieveFromVault("paypal.client.id").toCharArray();
    }
    
    private static char[] getClientSecretFromVault() {
        // Retrieve from secure credential store
        return retrieveFromVault("paypal.client.secret").toCharArray();
    }
    
    private static String retrieveFromVault(String key) {
        // Your secure credential retrieval logic
        return "...";
    }
}
```

---

## Automatic Token Management

Once configured, Payper automatically manages OAuth tokens:

1. **First Request**: Requests a new access token from PayPal
2. **Subsequent Requests**: Reuses the cached token
3. **Token Expiration**: Automatically requests a new token when the current one expires
4. **Thread-Safe**: Token caching is thread-safe and works in concurrent environments

You never need to manually handle tokens!

---

## Security Best Practices

### ✅ Do

- Store credentials in environment variables or secure credential stores
- Use different credentials for sandbox and production
- Rotate credentials regularly
- Use least-privilege access (only grant necessary API permissions)
- Use HTTPS in production (Payper enforces this)

### ❌ Don't

- Hard-code credentials in source code
- Commit credentials to version control
- Share credentials between environments
- Log or print credentials
- Use production credentials in development

---

## Configuration Priority

When multiple configuration methods are used, Payper follows this priority order:

1. **Programmatic configuration** (highest priority)
2. **System properties**
3. **Environment variables** (lowest priority)

This allows you to override settings when needed while maintaining a sensible default.

---

## Related Resources

- **[PayPal Authentication Documentation](https://developer.paypal.com/api/rest/authentication/)** - Official PayPal OAuth 2.0 guide
- **[Advanced Configuration](../advanced/configuration.md)** - Configure HTTP client, timeouts, and proxies
- **[Quick Start Guide](quickstart.md)** - Make your first API call

---

## Troubleshooting

### "Invalid client credentials" Error

- Verify your Client ID and Secret are correct
- Ensure you're using the right credentials for your environment (sandbox vs. live)
- Check that credentials haven't expired or been revoked

### "Unable to obtain access token" Error

- Check your internet connection
- Verify the base URL is correct
- Ensure PayPal API services are operational

### Tokens Not Refreshing

Payper automatically refreshes tokens. If you experience issues:

- Check your credentials are still valid
- Verify you have an active internet connection
- Review application logs for authentication errors
