# Payper - Unofficial Java SDK for PayPal REST API

[![Coverage](https://raw.githubusercontent.com/eealba/payper/refs/heads/main/.github/badges/jacoco.svg)](https://github.com/eealba/payper/actions/workflows/github_action.yaml)
[![License](https://img.shields.io/badge/License-Apache%202.0-green.svg)](https://opensource.org/licenses/Apache-2.0)
[![Java Version](https://img.shields.io/badge/Java-17%2B-brightgreen)](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
[![Maven Central](https://img.shields.io/maven-central/v/io.github.eealba.payper/payper-core.svg?label=Maven%20Central)](https://central.sonatype.com/search?q=io.github.eealba.payper)

Payper is an unofficial Java SDK for the PayPal REST API. It is tested and fully supported on current Java LTS releases (17, 21 and 25) and compatible with later Java 17+ runtimes. The library is built for multithreaded, high-concurrency environments: it uses immutable domain models and exposes a fluent API for concise, readable code.

---

## ✨ Key Features

| Feature | Description |
|---------|-------------|
| 🧭 **Java LTS Compatibility** | Tested and supported on current Java LTS releases — 17, 21 and 25 — and compatible with Java 17+ runtimes. |
| ⚡ **Modern HTTP Client** | Uses the platform's modern HTTP client for HTTP/2, improved TLS and efficient connection handling. |
| 🔒 **Immutable & Thread-Safe** | Domain objects are immutable and safe to share across threads. |
| ✨ **Fluent API** | Builder patterns and method chaining for concise, readable call flows. |
| 🔑 **Automatic OAuth 2.0** | Automatic acquisition, caching and refresh of OAuth 2.0 tokens. |
| 🚀 **Async Support** | CompletableFuture-based async APIs for non-blocking integrations. |
| ⚙️ **Flexible Configuration** | Configure via env vars, system properties or builders; specify executor, timeouts and proxies. |

---

## 🚀 Quick Start

Get started with Payper in minutes! Here's a simple example of creating a product using the Catalog Products API:

```java
import io.github.eealba.payper.catalog.products.v1.api.CatalogProductsApiClient;
import io.github.eealba.payper.catalog.products.v1.model.ProductCategory;
import io.github.eealba.payper.catalog.products.v1.model.ProductRequestPOST;

public class PayperExample {
    public static void main(String[] args) {
        // Create the client (uses environment variables for credentials)
        var client = CatalogProductsApiClient.create();

        // Build a product request
        var productRequest = ProductRequestPOST.builder()
                .name("Product Name")
                .description("Product Description")
                .type(ProductRequestPOST.Type.PHYSICAL)
                .category(ProductCategory.ACCESSORIES)
                .imageUrl("https://example.com/image.jpg")
                .build();

        // Create the product
        var product = client.products()
                .create()
                .withBody(productRequest)
                .retrieve()
                .toEntity();

        System.out.println("Created product ID: " + product.id());
    }
}
```

---

## 📦 Supported PayPal APIs

Payper provides support for the following PayPal REST APIs:

| API | Version | Description |
|-----|---------|-------------|
| **Orders** | v2 | Create, update, authorize, capture and manage orders |
| **Subscriptions** | v1 | Create products, billing plans and manage subscriptions |
| **Payments** | v2 | Capture authorized payments and process refunds |
| **Invoices** | v2 | Create, send and manage invoices |
| **Catalog Products** | v1 | Create and manage catalog products |
| **Webhooks** | v1 | Configure and manage webhook notifications |

Each API is available as a separate Maven module, allowing you to include only what you need.

---

## 📚 Resources

- **[Installation Guide](getting-started/installation.md)** - Add Payper to your Maven or Gradle project
- **[Quick Start Tutorial](getting-started/quickstart.md)** - Get up and running in 5 minutes
- **[Examples Repository](https://github.com/eealba/payper-examples)** - Runnable examples and demos
- **[API Reference](https://javadoc.io/doc/io.github.eealba.payper)** - Complete Javadoc documentation

---

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request. For major changes, please open an issue first to discuss what you would like to change.

---

## 📄 License

This project is licensed under the Apache License 2.0 - see the [LICENSE](https://github.com/eealba/payper/blob/main/LICENSE) file for details.

---

## ⚠️ Disclaimer

Payper is an **unofficial** SDK and is not affiliated with, maintained, authorized, endorsed or sponsored by PayPal. Use at your own risk.
