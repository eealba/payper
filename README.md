# Payper - Unofficial Java Client for PayPal REST API
[![Coverage](.github/badges/jacoco.svg)](https://github.com/eealba/payper/actions/workflows/github_action.yaml)
[![License](https://img.shields.io/badge/License-Apache%202.0-green.svg)](https://opensource.org/licenses/Apache-2.0)
[![Java Version](https://img.shields.io/badge/Java-17%2B-brightgreen)](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
[![Build Status](https://github.com/eealba/payper/actions/workflows/github_action.yaml/badge.svg)](https://github.com/eealba/payper/actions)


Payper is an unofficial Java client for the PayPal REST API. It is designed to be used with Java 17 and is prepared 
for multithreading and high concurrency. Payper uses immutable objects and provides a fluent API for ease of use.

## Features

- **Java 17+**: Built with Java 17+.
- **Multithreading and High Concurrency**: Designed to handle high concurrency and multithreading scenarios.
- **Immutable Objects**: Ensures thread safety and reliability with immutable objects.
- **Fluent API**: Provides a fluent API for intuitive and readable code.
- **Fast**: Optimized for performance.
- **Easy Configuration**: Simple setup with minimal configuration required.
- **OAuth 2.0**: Supports OAuth 2.0 for authentication.
- **Automatic Token Refresh**: Automatically refreshes OAuth 2.0 access tokens.
- **Asynchronous Support**: Supports asynchronous requests with completable futures.

## Supported PayPal REST APIs

| API Name              | Version | Links                                                                       |
|-----------------------|---------|-----------------------------------------------------------------------------|
| Catalog Products      | v1      | [API Reference](https://developer.paypal.com/docs/api/catalog-products/v1/) |  
| Subscriptions         | v1      | [API Reference](https://developer.paypal.com/docs/api/subscriptions/v1/)    |
| Orders                | v2      | [API Reference](https://developer.paypal.com/docs/api/orders/v2/)           |
| Payments              | v2      | [API Reference](https://developer.paypal.com/docs/api/payments/v2/)         |

## Installation

To use Payper in your project, add the appropriate dependency for the paypal service you want to consume
in your `pom.xml`, in the table below the corresponding payper module appears for each service:


### Subscriptions API
[![Maven Central](https://img.shields.io/maven-central/v/io.github.eealba.payper/payper-subscriptions-v1.svg?label=Maven%20Central)](https://central.sonatype.com/artifact/io.github.eealba.payper/payper-subscriptions-v1)
[![Javadoc](https://javadoc.io/badge2/io.github.eealba.payper/payper-subscriptions-v1/javadoc.io.svg)](https://javadoc.io/doc/io.github.eealba.payper/payper-subscriptions-v1)

```xml
<dependency>
    <groupId>io.github.eealba.payper</groupId>
    <artifactId>payper-subscriptions-v1</artifactId>
    <version>0.4.0</version>
</dependency>
```
### Catalog Products API
[![Maven Central](https://img.shields.io/maven-central/v/io.github.eealba.payper/payper-catalog-products-v1.svg?label=Maven%20Central)](https://central.sonatype.com/artifact/io.github.eealba.payper/payper-catalog-products-v1)
[![Javadoc](https://javadoc.io/badge2/io.github.eealba.payper/payper-catalog-products-v1/javadoc.io.svg)](https://javadoc.io/doc/io.github.eealba.payper/payper-catalog-products-v1)
```xml
<dependency>
    <groupId>io.github.eealba.payper</groupId>
    <artifactId>payper-catalog-products-v1</artifactId>
    <version>0.4.0</version>
</dependency>
```

### Orders API
[![Maven Central](https://img.shields.io/maven-central/v/io.github.eealba.payper/payper-orders-v2.svg?label=Maven%20Central)](https://central.sonatype.com/artifact/io.github.eealba.payper/payper-orders-v2)
[![Javadoc](https://javadoc.io/badge2/io.github.eealba.payper/payper-orders-v2/javadoc.io.svg)](https://javadoc.io/doc/io.github.eealba.payper/payper-orders-v2)
```xml
<dependency>
    <groupId>io.github.eealba.payper</groupId>
    <artifactId>payper-orders-v2</artifactId>
    <version>0.4.0</version>
</dependency>
```

### Payments API
[![Maven Central](https://img.shields.io/maven-central/v/io.github.eealba.payper/payper-payments-v2.svg?label=Maven%20Central)](https://central.sonatype.com/artifact/io.github.eealba.payper/payper-payments-v2)
[![Javadoc](https://javadoc.io/badge2/io.github.eealba.payper/payper-payments-v2/javadoc.io.svg)](https://javadoc.io/doc/io.github.eealba.payper/payper-payments-v2)
```xml
<dependency>
    <groupId>io.github.eealba.payper</groupId>
    <artifactId>payper-payments-v2</artifactId>
    <version>0.4.0</version>
</dependency>
```



## Coming Soon
| API Name              | Version | Links                                                                                  |
|-----------------------|---------|----------------------------------------------------------------------------------------|
| Add Tracking          | v1      | [API Reference](https://developer.paypal.com/docs/api/tracking/v1/)                    |
| Disputes              | v1      | [API Reference](https://developer.paypal.com/docs/api/customer-disputes/v1/)           |
| Invoices              | v2      | [API Reference](https://developer.paypal.com/docs/api/invoicing/v2/)                   |
| Partner Referrals     | v2      | [API Reference](https://developer.paypal.com/docs/api/partner-referrals/v2/)           |
| Payment Experience    | v1      | [API Reference](https://developer.paypal.com/docs/api/payment-experience/v1/)          |
| Payment Method Tokens | v3      | [API Reference](https://developer.paypal.com/docs/api/payment-tokens/v3/)              |
| Payouts               | v1      | [API Reference](https://developer.paypal.com/docs/api/payments.payouts-batch/v1/)      |
| Transaction Search    | v1      | [API Reference](https://developer.paypal.com/docs/api/transaction-search/v1/)          |
| Webhooks Management   | v1      | [API Reference](https://developer.paypal.com/docs/api/webhooks/v1/)                    |

## Usage

### Creating a Product

```java
import io.github.eealba.payper.catalog.products.v1.api.CatalogProductsApiClient;
import io.github.eealba.payper.catalog.products.v1.model.ProductCategory;
import io.github.eealba.payper.catalog.products.v1.model.ProductRequestPOST;

public class PayperExample {
    public static void main(String[] args) {

        var catalogProductsApiClient = CatalogProductsApiClient.create();

        var productRequest = ProductRequestPOST.builder()
                .name("Product Name")
                .description("Product Description")
                .type(ProductRequestPOST.Type.PHYSICAL)
                .category(ProductCategory.ACCESSORIES)
                .imageUrl("https://example.com/image.jpg")
                .build();

        var product = catalogProductsApiClient.products()
                .create()
                .withBody(productRequest)
                .retrieve()
                .toEntity();

        System.out.println("Created product ID: " + product.id());
    }
}
```

### Retrieving a Product

```java
import io.github.eealba.payper.catalog.products.v1.api.CatalogProductsApiClient;

public class PayperExample {
    public static void main(String[] args) {
        var catalogProductsApiClient = CatalogProductsApiClient.create();
        
        var product = catalogProductsApiClient.products()
                .get()
                .withId("1")
                .retrieve()
                .toEntity();

        System.out.println("Retrieved product ID: " + product.id());
    }
}
```


### Retrieving a Billing Plan

```java
import io.github.eealba.payper.subscriptions.v1.api.SubscriptionsApiClient;

public class PayperExample {
    public static void main(String[] args) {
        var subscriptionsApiClient = SubscriptionsApiClient.create();
        var plan = subscriptionsApiClient.billingPlans()
                .get()
                .withId("1")
                .retrieve()
                .toEntity();

        System.out.println("Retrieved plan ID: " + plan.id());
    }
}
```
### Retrieving a Billing Plan Async with future

```java
import io.github.eealba.payper.subscriptions.v1.api.SubscriptionsApiClient;
public class PayperExample {
    public static void main(String[] args) {
        var subscriptionsApiClient = SubscriptionsApiClient.create();
        
        var futurePlan = subscriptionsApiClient.billingPlans()
                .get()
                .withId("1")
                .retrieve()
                .toFuture();
        
        futurePlan.thenAccept( response  -> {
            if (response.statusCode() == 200) {
                System.out.println("Plan retrieved successfully");
            } else {
                System.out.println("Failed to retrieve plan");
            }
        });
    }
}
```



## Authentication

PayPal REST APIs use OAuth 2.0 access tokens to authenticate requests. Your access token authorizes you
to use the PayPal REST API server.

Payper is able to obtain the OAuth 2.0 access tokens before calling the Paypal Rest API, it is only necessary
to pass the corresponding CLIENT_ID and CLIENT_SECRET.

Refer to the [PayPal Developer Documentation](https://developer.paypal.com/api/rest/authentication/) for more information.

The easy way to pass the CLIENT_ID and CLIENT_SECRET is to set the following environment variables or system properties:

### Environment Variables to set sandbox credentials
```shell
export PAYPAL-CLIENT-ID=YOUR_CLIENT_ID
export PAYPAL-CLIENT-SECRET=YOUR_CLIENT_SECRET
export PAYPAL-BASE-URL=https://api-m.sandbox.paypal.com 
```

### Variables to set live environment credentials
```shell
export PAYPAL-CLIENT-ID=YOUR_CLIENT_ID
export PAYPAL-CLIENT-SECRET=YOUR_CLIENT_SECRET
export PAYPAL-BASE-URL=https://api-m.paypal.com 
```

Otherwise, you can pass the credentials directly to the Payper client using supplier functions:

```java
    public static void main(String[] args) {
        PayperConfig config = PayperConfig.builder()
                .authenticator(PayperAuthenticator.PayperAuthenticators
                        .ofSandBox(() -> "CLIENT_ID".toCharArray(),
                                () -> "CLIENT_SECRET".toCharArray()))
                .build();
        
        var subscriptionsApiClient = SubscriptionsApiClient.create(config);

        var plan = subscriptionsApiClient.billingPlans()
                .get()
                .withId("1")
                .retrieve()
                .toEntity();

        System.out.println("Retrieved plan ID: " + plan.id());

    }
```


