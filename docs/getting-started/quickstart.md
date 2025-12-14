# Quick Start

Get started with Payper in just a few minutes! This guide will walk you through making your first PayPal API call.

## Prerequisites

- Java 17 or higher installed
- PayPal Sandbox account credentials (Client ID and Secret)
- Maven or Gradle project set up

!!! tip "Get PayPal Sandbox Credentials"
    If you don't have PayPal sandbox credentials yet, sign up for a free developer account at [PayPal Developer](https://developer.paypal.com/) and create a sandbox application to get your Client ID and Secret.

---

## Step 1: Add Dependency

Add the Payper dependency for the API you want to use. For this quick start, we'll use the Catalog Products API.

=== "Orders API"

    ```xml
    <dependency>
        <groupId>io.github.eealba.payper</groupId>
        <artifactId>payper-orders-v2</artifactId>
        <version>1.0.0</version>
    </dependency>
    ```

=== "Subscriptions API"

    ```xml
    <dependency>
        <groupId>io.github.eealba.payper</groupId>
        <artifactId>payper-subscriptions-v1</artifactId>
        <version>1.0.0</version>
    </dependency>
    ```

=== "Catalog Products API"

    ```xml
    <dependency>
        <groupId>io.github.eealba.payper</groupId>
        <artifactId>payper-catalog-products-v1</artifactId>
        <version>1.0.0</version>
    </dependency>
    ```

---

## Step 2: Configure Credentials

Set your PayPal credentials as environment variables. This is the recommended approach for security.

```bash
export PAYPAL-CLIENT-ID=YOUR_CLIENT_ID
export PAYPAL-CLIENT-SECRET=YOUR_CLIENT_SECRET
export PAYPAL-BASE-URL=https://api-m.sandbox.paypal.com
```

!!! warning "Never Hard-Code Credentials"
    Never hard-code your credentials in your source code. Always use environment variables or secure configuration management.

---

## Step 3: Make Your First Request

Create a simple Java class to create a product:

```java
import io.github.eealba.payper.catalog.products.v1.api.CatalogProductsApiClient;
import io.github.eealba.payper.catalog.products.v1.model.ProductCategory;
import io.github.eealba.payper.catalog.products.v1.model.ProductRequestPOST;

public class QuickStart {
    public static void main(String[] args) {
        // Create the API client (automatically uses environment variables)
        var client = CatalogProductsApiClient.create();

        // Build a product request
        var productRequest = ProductRequestPOST.builder()
                .name("My First Product")
                .description("A test product created with Payper")
                .type(ProductRequestPOST.Type.PHYSICAL)
                .category(ProductCategory.SOFTWARE)
                .build();

        // Create the product
        var product = client.products()
                .create()
                .withBody(productRequest)
                .retrieve()
                .toEntity();

        // Print the result
        System.out.println("✅ Product created successfully!");
        System.out.println("Product ID: " + product.id());
        System.out.println("Product Name: " + product.name());
    }
}
```

---

## Expected Output

When you run this code, you should see output similar to:

```
✅ Product created successfully!
Product ID: PROD-1234567890ABC
Product Name: My First Product
```

---

## What's Happening?

1. **Client Creation**: `CatalogProductsApiClient.create()` creates a client that automatically:
   - Reads credentials from environment variables
   - Manages OAuth 2.0 tokens (requests, caches, and refreshes them)
   - Configures the HTTP client for optimal performance

2. **Fluent API**: The fluent API pattern makes it easy to build and send requests:
   - `products()` - Access the products resource
   - `create()` - Specify the operation
   - `withBody()` - Provide the request body
   - `retrieve()` - Execute the request
   - `toEntity()` - Convert the response to a domain object

3. **Immutable Models**: All domain objects are immutable and thread-safe, using builders for construction.

---

## Next Steps

Now that you've made your first API call, explore more:

- **[Authentication Guide](authentication.md)** - Learn about different ways to configure credentials
- **[Orders API](../apis/orders.md)** - Create and manage PayPal orders
- **[Subscriptions API](../apis/subscriptions.md)** - Set up recurring payments
- **[Examples Repository](../examples/overview.md)** - Browse more complete examples

---

## Troubleshooting

### Authentication Error

If you get an authentication error, verify that:

- Your environment variables are set correctly
- Your Client ID and Secret are valid
- You're using the correct base URL for your environment (sandbox vs. live)

### Compilation Error

Make sure you're using Java 17 or higher:

```bash
java -version
```

### Connection Error

Check that you have internet connectivity and can reach PayPal's API servers.

!!! tip "Need More Help?"
    Check out the [5 Minutes Start](../examples/5-minutes.md) example for a complete, runnable project that validates your setup.
