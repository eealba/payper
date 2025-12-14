# 5 Minutes Start

Validate your PayPal sandbox credentials and make your first API call in just 5 minutes!

This tutorial walks you through the **payper-5-minutes** example from the [payper-examples repository](https://github.com/eealba/payper-examples/tree/main/payper-5-minutes).

---

## Goal

By the end of this tutorial, you will:

- ✅ Validate your PayPal sandbox credentials
- ✅ Create a product using the Catalog Products API
- ✅ Understand the basic Payper SDK workflow

---

## Prerequisites

Before you begin, ensure you have:

- **Java 17 or higher** installed
- **Maven 3.6+** installed
- **PayPal Sandbox credentials** (Client ID and Secret)

!!! tip "Get Sandbox Credentials"
    Don't have credentials yet? Get them in 2 minutes:
    
    1. Go to [PayPal Developer Dashboard](https://developer.paypal.com/dashboard/)
    2. Log in or create an account
    3. Navigate to "Apps & Credentials"
    4. Click "Create App" under Sandbox
    5. Copy your Client ID and Secret

---

## Step 1: Clone the Examples Repository

```bash
git clone https://github.com/eealba/payper-examples.git
cd payper-examples/payper-5-minutes
```

---

## Step 2: Configure Your Credentials

Set your PayPal sandbox credentials as environment variables:

=== "Linux/macOS"

    ```bash
    export PAYPAL-CLIENT-ID=YOUR_CLIENT_ID
    export PAYPAL-CLIENT-SECRET=YOUR_CLIENT_SECRET
    export PAYPAL-BASE-URL=https://api-m.sandbox.paypal.com
    ```

=== "Windows (PowerShell)"

    ```powershell
    $env:PAYPAL-CLIENT-ID="YOUR_CLIENT_ID"
    $env:PAYPAL-CLIENT-SECRET="YOUR_CLIENT_SECRET"
    $env:PAYPAL-BASE-URL="https://api-m.sandbox.paypal.com"
    ```

=== "Windows (Command Prompt)"

    ```cmd
    set PAYPAL-CLIENT-ID=YOUR_CLIENT_ID
    set PAYPAL-CLIENT-SECRET=YOUR_CLIENT_SECRET
    set PAYPAL-BASE-URL=https://api-m.sandbox.paypal.com
    ```

!!! warning "Replace Placeholder Values"
    Make sure to replace `YOUR_CLIENT_ID` and `YOUR_CLIENT_SECRET` with your actual PayPal sandbox credentials!

---

## Step 3: Run the Example

Execute the example using Maven:

```bash
mvn clean compile exec:java
```

---

## Expected Output

If everything is configured correctly, you should see output similar to:

```
[INFO] ------------------------------------------------------------------------
[INFO] Building payper-5-minutes 1.0.0
[INFO] ------------------------------------------------------------------------
[INFO] 
[INFO] --- exec-maven-plugin:3.1.0:java (default-cli) @ payper-5-minutes ---

🚀 Payper 5-Minute Quick Start
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

Step 1: Creating API client...
✓ Client created successfully

Step 2: Creating a test product...
✓ Product created successfully!

Product Details:
  ID: PROD-12A34567BC890123D
  Name: 5-Minute Test Product
  Type: DIGITAL
  Category: SOFTWARE
  Created: 2024-01-15T10:30:00Z

━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
✅ Success! Your PayPal credentials are working correctly.

Next Steps:
  1. Try the Orders API example
  2. Explore the Subscriptions example
  3. Check out the full documentation

[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
```

---

## Understanding the Code

Let's break down what's happening in the example:

### 1. Creating the Client

```java
var client = CatalogProductsApiClient.create();
```

This line creates a new API client that:
- Automatically reads credentials from environment variables
- Manages OAuth 2.0 token acquisition and refresh
- Configures the HTTP client for optimal performance

### 2. Building the Request

```java
var productRequest = ProductRequestPOST.builder()
        .name("5-Minute Test Product")
        .description("Created by Payper 5-minute quickstart")
        .type(ProductRequestPOST.Type.DIGITAL)
        .category(ProductCategory.SOFTWARE)
        .build();
```

The builder pattern provides:
- Type-safe request construction
- Fluent, readable API
- Compile-time validation

### 3. Making the API Call

```java
var product = client.products()
        .create()
        .withBody(productRequest)
        .retrieve()
        .toEntity();
```

This fluent chain:
1. `products()` - Access the products resource
2. `create()` - Specify the create operation
3. `withBody()` - Provide the request body
4. `retrieve()` - Execute the HTTP request
5. `toEntity()` - Parse response to domain object

### 4. Using the Response

```java
System.out.println("Product ID: " + product.id());
System.out.println("Name: " + product.name());
```

Response objects are:
- Immutable and thread-safe
- Strongly typed with getters
- Easy to work with

---

## Troubleshooting

### Authentication Failed

**Error**: `401 Unauthorized` or `Authentication failed`

**Solutions**:
- Verify your Client ID and Secret are correct
- Ensure environment variables are set correctly
- Check you're using sandbox credentials with sandbox base URL
- Confirm your PayPal app has the necessary permissions

### Connection Timeout

**Error**: `Connection timeout` or `Unable to connect`

**Solutions**:
- Check your internet connection
- Verify firewall settings allow HTTPS connections
- Ensure the base URL is correct
- Try again (PayPal services may be temporarily unavailable)

### Java Version Error

**Error**: `Unsupported class file major version` or `java.lang.UnsupportedClassVersionError`

**Solution**:
- Verify you're using Java 17 or higher:
  ```bash
  java -version
  ```
- Update Java if necessary

### Maven Build Error

**Error**: Maven fails to build

**Solutions**:
- Ensure Maven 3.6+ is installed:
  ```bash
  mvn -version
  ```
- Clear Maven cache:
  ```bash
  mvn clean
  ```
- Try with `-U` flag to force updates:
  ```bash
  mvn clean compile exec:java -U
  ```

---

## What's Next?

Congratulations! 🎉 You've successfully made your first PayPal API call with Payper.

### Continue Learning

1. **[Orders API Example](orders.md)** - Learn how to process payments
2. **[Subscriptions Example](subscriptions.md)** - Set up recurring billing
3. **[API Documentation](../apis/catalog.md)** - Explore all Catalog Products API features

### Explore More Examples

- **[payper-orders-basic](https://github.com/eealba/payper-examples/tree/main/payper-orders-basic)** - Complete order workflow
- **[subscriptions-app](https://github.com/eealba/payper-examples/tree/main/subscriptions-app)** - Subscription management
- **[webstore](https://github.com/eealba/payper-examples/tree/main/webstore)** - Full Spring Boot integration

### Learn Advanced Topics

- **[Configuration Guide](../advanced/configuration.md)** - Customize HTTP client, timeouts, and proxies
- **[Async Operations](../advanced/async.md)** - Use CompletableFuture for non-blocking calls
- **[Error Handling](../advanced/error-handling.md)** - Handle errors gracefully

---

## Full Code Example

Here's the complete code from the 5-minute example:

```java
package io.github.eealba.example;

import io.github.eealba.payper.catalog.products.v1.api.CatalogProductsApiClient;
import io.github.eealba.payper.catalog.products.v1.model.ProductCategory;
import io.github.eealba.payper.catalog.products.v1.model.ProductRequestPOST;

public class QuickStart {
    public static void main(String[] args) {
        System.out.println("🚀 Payper 5-Minute Quick Start");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        
        try {
            // Step 1: Create the client
            System.out.println("\nStep 1: Creating API client...");
            var client = CatalogProductsApiClient.create();
            System.out.println("✓ Client created successfully");
            
            // Step 2: Create a product
            System.out.println("\nStep 2: Creating a test product...");
            var productRequest = ProductRequestPOST.builder()
                    .name("5-Minute Test Product")
                    .description("Created by Payper 5-minute quickstart")
                    .type(ProductRequestPOST.Type.DIGITAL)
                    .category(ProductCategory.SOFTWARE)
                    .build();
            
            var product = client.products()
                    .create()
                    .withBody(productRequest)
                    .retrieve()
                    .toEntity();
            
            System.out.println("✓ Product created successfully!");
            
            // Step 3: Display results
            System.out.println("\nProduct Details:");
            System.out.println("  ID: " + product.id());
            System.out.println("  Name: " + product.name());
            System.out.println("  Type: " + product.type());
            System.out.println("  Category: " + product.category());
            System.out.println("  Created: " + product.createTime());
            
            System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            System.out.println("✅ Success! Your PayPal credentials are working correctly.");
            System.out.println("\nNext Steps:");
            System.out.println("  1. Try the Orders API example");
            System.out.println("  2. Explore the Subscriptions example");
            System.out.println("  3. Check out the full documentation");
            
        } catch (Exception e) {
            System.err.println("\n❌ Error: " + e.getMessage());
            System.err.println("\nPlease check:");
            System.err.println("  1. Your credentials are correct");
            System.err.println("  2. Environment variables are set");
            System.err.println("  3. You have internet connectivity");
        }
    }
}
```

---

## Need Help?

If you encounter any issues:

1. Review the [Troubleshooting](#troubleshooting) section above
2. Check the [Authentication Guide](../getting-started/authentication.md)
3. Visit the [payper-examples repository](https://github.com/eealba/payper-examples)
4. Open an issue on GitHub

Happy coding! 🚀
