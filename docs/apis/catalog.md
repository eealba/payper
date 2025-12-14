# Catalog Products API

The Catalog Products API v1 enables you to create and manage product listings in your PayPal catalog.

**Official Documentation**: [PayPal Catalog Products API v1](https://developer.paypal.com/docs/api/catalog-products/v1/)

---

## Installation

Add the Catalog Products API dependency to your project:

=== "Maven"

    ```xml
    <dependency>
        <groupId>io.github.eealba.payper</groupId>
        <artifactId>payper-catalog-products-v1</artifactId>
        <version>1.0.0</version>
    </dependency>
    ```

=== "Gradle (Groovy)"

    ```groovy
    implementation 'io.github.eealba.payper:payper-catalog-products-v1:1.0.0'
    ```

=== "Gradle (Kotlin)"

    ```kotlin
    implementation("io.github.eealba.payper:payper-catalog-products-v1:1.0.0")
    ```

---

## Creating the Client

```java
import io.github.eealba.payper.catalog.products.v1.api.CatalogProductsApiClient;

public class Example {
    public static void main(String[] args) {
        // Create client (uses environment variables for credentials)
        var client = CatalogProductsApiClient.create();
        
        // Access the products API
        var productsApi = client.products();
    }
}
```

---

## Common Operations

### Create a Product

Create a new product in your catalog:

```java
import io.github.eealba.payper.catalog.products.v1.api.CatalogProductsApiClient;
import io.github.eealba.payper.catalog.products.v1.model.*;

public class CreateProductExample {
    public static void main(String[] args) {
        var client = CatalogProductsApiClient.create();
        
        // Build the product request
        var productRequest = ProductRequestPOST.builder()
                .name("Premium Laptop")
                .description("High-performance laptop for professionals")
                .type(ProductRequestPOST.Type.PHYSICAL)
                .category(ProductCategory.COMPUTERS)
                .imageUrl("https://example.com/laptop.jpg")
                .homeUrl("https://example.com/products/laptop")
                .build();
        
        // Create the product
        var product = client.products()
                .create()
                .withBody(productRequest)
                .retrieve()
                .toEntity();
        
        System.out.println("Product ID: " + product.id());
        System.out.println("Product Name: " + product.name());
        System.out.println("Status: " + product.createTime());
    }
}
```

**Expected Output**:
```
Product ID: PROD-1AB23456CD789012E
Product Name: Premium Laptop
Status: 2024-01-15T10:30:00Z
```

---

### List Products

Retrieve a paginated list of products:

```java
import io.github.eealba.payper.catalog.products.v1.api.CatalogProductsApiClient;

public class ListProductsExample {
    public static void main(String[] args) {
        var client = CatalogProductsApiClient.create();
        
        // List products with pagination
        var productsList = client.products()
                .list()
                .withPage(1)
                .withPageSize(10)
                .withTotalRequired(true)
                .retrieve()
                .toEntity();
        
        System.out.println("Total Products: " + productsList.totalItems());
        System.out.println("Total Pages: " + productsList.totalPages());
        
        // Print each product
        productsList.products().forEach(product -> {
            System.out.println("  - ID: " + product.id());
            System.out.println("    Name: " + product.name());
            System.out.println("    Type: " + product.type());
        });
    }
}
```

---

### Get Product Details

Retrieve details of a specific product:

```java
import io.github.eealba.payper.catalog.products.v1.api.CatalogProductsApiClient;

public class GetProductExample {
    public static void main(String[] args) {
        var client = CatalogProductsApiClient.create();
        
        // Get product by ID
        var product = client.products()
                .get()
                .withId("PROD-1AB23456CD789012E")
                .retrieve()
                .toEntity();
        
        System.out.println("Product ID: " + product.id());
        System.out.println("Name: " + product.name());
        System.out.println("Description: " + product.description());
        System.out.println("Type: " + product.type());
        System.out.println("Category: " + product.category());
        System.out.println("Image URL: " + product.imageUrl());
        System.out.println("Home URL: " + product.homeUrl());
        System.out.println("Created: " + product.createTime());
        System.out.println("Updated: " + product.updateTime());
    }
}
```

---

### Update a Product

Update product details:

```java
import io.github.eealba.payper.catalog.products.v1.api.CatalogProductsApiClient;
import io.github.eealba.payper.catalog.products.v1.model.*;

import java.util.List;

public class UpdateProductExample {
    public static void main(String[] args) {
        var client = CatalogProductsApiClient.create();
        
        // Create patch operations
        var patches = List.of(
            Patch.builder()
                    .op(Patch.Op.REPLACE)
                    .path("/description")
                    .value("Updated: Ultra high-performance laptop")
                    .build(),
            Patch.builder()
                    .op(Patch.Op.REPLACE)
                    .path("/image_url")
                    .value("https://example.com/laptop-new.jpg")
                    .build()
        );
        
        // Update the product
        client.products()
                .update()
                .withId("PROD-1AB23456CD789012E")
                .withBody(new PatchRequest(patches))
                .retrieve()
                .toVoid();
        
        System.out.println("Product updated successfully");
    }
}
```

---

## Product Types

Payper supports the following product types:

| Type | Description | Use Case |
|------|-------------|----------|
| **PHYSICAL** | Physical goods | Merchandise, electronics, books |
| **DIGITAL** | Digital products | Software, e-books, courses |
| **SERVICE** | Services | Consulting, subscriptions, memberships |

Example for each type:

=== "Physical Product"

    ```java
    var product = ProductRequestPOST.builder()
            .name("T-Shirt")
            .type(ProductRequestPOST.Type.PHYSICAL)
            .category(ProductCategory.CLOTHING)
            .build();
    ```

=== "Digital Product"

    ```java
    var product = ProductRequestPOST.builder()
            .name("Online Course")
            .type(ProductRequestPOST.Type.DIGITAL)
            .category(ProductCategory.SOFTWARE)
            .build();
    ```

=== "Service"

    ```java
    var product = ProductRequestPOST.builder()
            .name("Premium Membership")
            .type(ProductRequestPOST.Type.SERVICE)
            .category(ProductCategory.MEMBERSHIPS)
            .build();
    ```

---

## Async Operations

All operations support asynchronous execution:

```java
import io.github.eealba.payper.catalog.products.v1.api.CatalogProductsApiClient;
import io.github.eealba.payper.catalog.products.v1.model.*;

import java.util.concurrent.CompletableFuture;

public class AsyncProductExample {
    public static void main(String[] args) {
        var client = CatalogProductsApiClient.create();
        
        // Create product asynchronously
        CompletableFuture<Product> productFuture = client.products()
                .create()
                .withBody(ProductRequestPOST.builder()
                        .name("Async Product")
                        .type(ProductRequestPOST.Type.PHYSICAL)
                        .category(ProductCategory.GENERAL_MERCHANDISE)
                        .build())
                .retrieve()
                .toFuture()
                .thenApply(response -> {
                    if (response.statusCode() == 201) {
                        return response.toEntity();
                    }
                    throw new RuntimeException("Failed to create product");
                });
        
        // Handle result
        productFuture.thenAccept(product -> {
            System.out.println("Product created: " + product.id());
            System.out.println("Name: " + product.name());
        }).exceptionally(ex -> {
            System.err.println("Error: " + ex.getMessage());
            return null;
        });
        
        productFuture.join();
    }
}
```

---

## Error Handling

Handle errors gracefully:

```java
import io.github.eealba.payper.catalog.products.v1.api.CatalogProductsApiClient;
import io.github.eealba.payper.core.PayperException;

public class ErrorHandlingExample {
    public static void main(String[] args) {
        var client = CatalogProductsApiClient.create();
        
        var response = client.products()
                .get()
                .withId("INVALID_PRODUCT_ID")
                .retrieve()
                .toResponse();
        
        if (response.isSuccessful()) {
            var product = response.toEntity();
            System.out.println("Product: " + product.id());
        } else {
            System.err.println("API Error - Status: " + response.statusCode());
            
            // Handle specific error codes
            switch (response.statusCode()) {
                case 404:
                    System.err.println("Product not found");
                    break;
                case 400:
                    System.err.println("Invalid request");
                    var errorEntity = response.toErrorEntity();
                    System.err.println("Details: " + errorEntity.message());
                    break;
                default:
                    System.err.println("Error: " + response.toErrorEntity().message());
            }
        }
    }
}
```

---

## Common Use Cases

### E-Commerce Product Catalog

Create a catalog of products for your online store:

```java
// Create multiple products
var products = List.of(
    createProduct("Laptop", ProductCategory.COMPUTERS),
    createProduct("Mouse", ProductCategory.COMPUTERS),
    createProduct("T-Shirt", ProductCategory.CLOTHING)
);

private static Product createProduct(String name, ProductCategory category) {
    return client.products()
            .create()
            .withBody(ProductRequestPOST.builder()
                    .name(name)
                    .type(ProductRequestPOST.Type.PHYSICAL)
                    .category(category)
                    .build())
            .retrieve()
            .toEntity();
}
```

### Subscription Products

Create products for subscription billing:

```java
var subscriptionProduct = ProductRequestPOST.builder()
        .name("Premium Subscription")
        .description("Monthly access to premium features")
        .type(ProductRequestPOST.Type.SERVICE)
        .category(ProductCategory.MEMBERSHIPS)
        .build();

var product = client.products()
        .create()
        .withBody(subscriptionProduct)
        .retrieve()
        .toEntity();

// Use this product ID when creating billing plans
System.out.println("Use this product ID for billing plans: " + product.id());
```

---

## Related Resources

- **[Official PayPal Catalog Products API Documentation](https://developer.paypal.com/docs/api/catalog-products/v1/)**
- **[Subscriptions API](subscriptions.md)** - Use products with billing plans
- **[Quick Start Guide](../getting-started/quickstart.md)** - Get started with products
- **[Authentication Guide](../getting-started/authentication.md)** - Configure credentials
