# Subscriptions API

The Subscriptions API v1 enables you to create products, billing plans, and manage recurring subscriptions.

**Official Documentation**: [PayPal Subscriptions API v1](https://developer.paypal.com/docs/api/subscriptions/v1/)

---

## Installation

Add the Subscriptions API dependency to your project:

=== "Maven"

    ```xml
    <dependency>
        <groupId>io.github.eealba.payper</groupId>
        <artifactId>payper-subscriptions-v1</artifactId>
        <version>1.0.0</version>
    </dependency>
    ```

=== "Gradle (Groovy)"

    ```groovy
    implementation 'io.github.eealba.payper:payper-subscriptions-v1:1.0.0'
    ```

=== "Gradle (Kotlin)"

    ```kotlin
    implementation("io.github.eealba.payper:payper-subscriptions-v1:1.0.0")
    ```

---

## Creating the Client

```java
import io.github.eealba.payper.subscriptions.v1.api.SubscriptionsApiClient;

public class Example {
    public static void main(String[] args) {
        // Create client (uses environment variables for credentials)
        var client = SubscriptionsApiClient.create();
        
        // Access various APIs
        var productsApi = client.products();
        var billingPlansApi = client.billingPlans();
        var subscriptionsApi = client.subscriptions();
    }
}
```

---

## Common Operations

### Create a Product

Create a product that can be associated with billing plans:

```java
import io.github.eealba.payper.subscriptions.v1.api.SubscriptionsApiClient;
import io.github.eealba.payper.subscriptions.v1.model.*;

public class CreateProductExample {
    public static void main(String[] args) {
        var client = SubscriptionsApiClient.create();
        
        // Build the product request
        var productRequest = ProductRequest.builder()
                .name("Premium Membership")
                .description("Access to premium features and content")
                .type(Product.Type.SERVICE)
                .category(Product.Category.SOFTWARE)
                .imageUrl("https://example.com/premium.jpg")
                .homeUrl("https://example.com")
                .build();
        
        // Create the product
        var product = client.products()
                .create()
                .withBody(productRequest)
                .retrieve()
                .toEntity();
        
        System.out.println("Product ID: " + product.id());
        System.out.println("Product Name: " + product.name());
    }
}
```

**Expected Output**:
```
Product ID: PROD-6XB24663H4094933M
Product Name: Premium Membership
```

---

### Create a Billing Plan

Create a billing plan with pricing and billing cycles:

```java
import io.github.eealba.payper.subscriptions.v1.api.SubscriptionsApiClient;
import io.github.eealba.payper.subscriptions.v1.model.*;

import java.util.List;

public class CreateBillingPlanExample {
    public static void main(String[] args) {
        var client = SubscriptionsApiClient.create();
        
        // Build the billing plan
        var planRequest = PlanRequest.builder()
                .productId("PROD-6XB24663H4094933M")
                .name("Premium Membership Plan")
                .description("Monthly subscription to premium features")
                .billingCycles(List.of(
                    BillingCycle.builder()
                            .frequency(Frequency.builder()
                                    .intervalUnit(Frequency.IntervalUnit.MONTH)
                                    .intervalCount(1)
                                    .build())
                            .tenureType(BillingCycle.TenureType.REGULAR)
                            .sequence(1)
                            .totalCycles(0) // 0 means infinite
                            .pricingScheme(PricingScheme.builder()
                                    .fixedPrice(Money.builder()
                                            .currencyCode("USD")
                                            .value("19.99")
                                            .build())
                                    .build())
                            .build()
                ))
                .paymentPreferences(PaymentPreferences.builder()
                        .autoBillOutstanding(true)
                        .setupFeeFailureAction(PaymentPreferences.SetupFeeFailureAction.CONTINUE)
                        .paymentFailureThreshold(3)
                        .build())
                .build();
        
        // Create the plan
        var plan = client.billingPlans()
                .create()
                .withBody(planRequest)
                .retrieve()
                .toEntity();
        
        System.out.println("Plan ID: " + plan.id());
        System.out.println("Plan Name: " + plan.name());
        System.out.println("Status: " + plan.status());
    }
}
```

---

### Create a Subscription

Create a subscription for a customer:

```java
import io.github.eealba.payper.subscriptions.v1.api.SubscriptionsApiClient;
import io.github.eealba.payper.subscriptions.v1.model.*;

import java.time.ZonedDateTime;

public class CreateSubscriptionExample {
    public static void main(String[] args) {
        var client = SubscriptionsApiClient.create();
        
        // Build the subscription request
        var subscriptionRequest = SubscriptionRequest.builder()
                .planId("P-5ML4271244454362WXNWU5NQ")
                .startTime(ZonedDateTime.now().plusDays(1).toString())
                .subscriber(Subscriber.builder()
                        .name(Name.builder()
                                .givenName("John")
                                .surname("Doe")
                                .build())
                        .emailAddress("john.doe@example.com")
                        .build())
                .applicationContext(ApplicationContext.builder()
                        .brandName("Example Store")
                        .locale("en-US")
                        .shippingPreference(ApplicationContext.ShippingPreference.NO_SHIPPING)
                        .userAction(ApplicationContext.UserAction.SUBSCRIBE_NOW)
                        .returnUrl("https://example.com/return")
                        .cancelUrl("https://example.com/cancel")
                        .build())
                .build();
        
        // Create the subscription
        var subscription = client.subscriptions()
                .create()
                .withBody(subscriptionRequest)
                .retrieve()
                .toEntity();
        
        System.out.println("Subscription ID: " + subscription.id());
        System.out.println("Status: " + subscription.status());
        
        // Get approval URL
        var approvalUrl = subscription.links().stream()
                .filter(link -> "approve".equals(link.rel()))
                .findFirst()
                .map(LinkDescription::href)
                .orElse("N/A");
        System.out.println("Approval URL: " + approvalUrl);
    }
}
```

---

### Get Subscription Details

Retrieve details of an existing subscription:

```java
import io.github.eealba.payper.subscriptions.v1.api.SubscriptionsApiClient;

public class GetSubscriptionExample {
    public static void main(String[] args) {
        var client = SubscriptionsApiClient.create();
        
        // Get subscription by ID
        var subscription = client.subscriptions()
                .get()
                .withId("I-BW452GLLEP1G")
                .retrieve()
                .toEntity();
        
        System.out.println("Subscription ID: " + subscription.id());
        System.out.println("Status: " + subscription.status());
        System.out.println("Plan ID: " + subscription.planId());
        
        // Get subscriber info
        var subscriber = subscription.subscriber();
        System.out.println("Subscriber: " + 
            subscriber.name().givenName() + " " + 
            subscriber.name().surname());
    }
}
```

---

### Suspend a Subscription

Temporarily suspend a subscription:

```java
import io.github.eealba.payper.subscriptions.v1.api.SubscriptionsApiClient;
import io.github.eealba.payper.subscriptions.v1.model.SuspendRequest;

public class SuspendSubscriptionExample {
    public static void main(String[] args) {
        var client = SubscriptionsApiClient.create();
        
        // Suspend subscription
        var suspendRequest = SuspendRequest.builder()
                .reason("Customer requested suspension")
                .build();
        
        client.subscriptions()
                .suspend()
                .withId("I-BW452GLLEP1G")
                .withBody(suspendRequest)
                .retrieve()
                .toVoid();
        
        System.out.println("Subscription suspended successfully");
    }
}
```

---

### Activate a Subscription

Reactivate a suspended subscription:

```java
import io.github.eealba.payper.subscriptions.v1.api.SubscriptionsApiClient;
import io.github.eealba.payper.subscriptions.v1.model.ActivateRequest;

public class ActivateSubscriptionExample {
    public static void main(String[] args) {
        var client = SubscriptionsApiClient.create();
        
        // Activate subscription
        var activateRequest = ActivateRequest.builder()
                .reason("Customer requested reactivation")
                .build();
        
        client.subscriptions()
                .activate()
                .withId("I-BW452GLLEP1G")
                .withBody(activateRequest)
                .retrieve()
                .toVoid();
        
        System.out.println("Subscription activated successfully");
    }
}
```

---

### Cancel a Subscription

Cancel a subscription:

```java
import io.github.eealba.payper.subscriptions.v1.api.SubscriptionsApiClient;
import io.github.eealba.payper.subscriptions.v1.model.CancelRequest;

public class CancelSubscriptionExample {
    public static void main(String[] args) {
        var client = SubscriptionsApiClient.create();
        
        // Cancel subscription
        var cancelRequest = CancelRequest.builder()
                .reason("Customer requested cancellation")
                .build();
        
        client.subscriptions()
                .cancel()
                .withId("I-BW452GLLEP1G")
                .withBody(cancelRequest)
                .retrieve()
                .toVoid();
        
        System.out.println("Subscription cancelled successfully");
    }
}
```

---

## Async Operations

All operations support asynchronous execution:

```java
import io.github.eealba.payper.subscriptions.v1.api.SubscriptionsApiClient;
import io.github.eealba.payper.subscriptions.v1.model.*;

import java.util.concurrent.CompletableFuture;

public class AsyncSubscriptionExample {
    public static void main(String[] args) {
        var client = SubscriptionsApiClient.create();
        
        // Create product, plan, and subscription asynchronously
        CompletableFuture<Subscription> subscriptionFuture = 
            createProductAsync(client)
                .thenCompose(product -> createPlanAsync(client, product.id()))
                .thenCompose(plan -> createSubscriptionAsync(client, plan.id()));
        
        // Handle result
        subscriptionFuture.thenAccept(subscription -> {
            System.out.println("Subscription created: " + subscription.id());
        }).exceptionally(ex -> {
            System.err.println("Error: " + ex.getMessage());
            return null;
        });
        
        subscriptionFuture.join();
    }
    
    private static CompletableFuture<Product> createProductAsync(
            SubscriptionsApiClient client) {
        return client.products()
                .create()
                .withBody(ProductRequest.builder()
                        .name("Premium Membership")
                        .type(Product.Type.SERVICE)
                        .build())
                .retrieve()
                .toFuture()
                .thenApply(response -> response.toEntity());
    }
    
    private static CompletableFuture<Plan> createPlanAsync(
            SubscriptionsApiClient client, String productId) {
        // Implementation similar to above
        return CompletableFuture.completedFuture(null); // Placeholder
    }
    
    private static CompletableFuture<Subscription> createSubscriptionAsync(
            SubscriptionsApiClient client, String planId) {
        // Implementation similar to above
        return CompletableFuture.completedFuture(null); // Placeholder
    }
}
```

---

## Error Handling

```java
import io.github.eealba.payper.subscriptions.v1.api.SubscriptionsApiClient;
import io.github.eealba.payper.core.PayperException;

public class ErrorHandlingExample {
    public static void main(String[] args) {
        var client = SubscriptionsApiClient.create();
        
        var response = client.billingSubscriptions()
                .get()
                .withId("INVALID_ID")
                .retrieve()
                .toResponse();
        
        if (response.isSuccessful()) {
            var subscription = response.toEntity();
            System.out.println("Subscription: " + subscription.id());
            System.out.println("Status: " + subscription.status());
        } else {
            System.err.println("API Error - Status: " + response.statusCode());
            
            // Access error details  
            var errorEntity = response.toErrorEntity();
            System.err.println("Error: " + errorEntity.message());
        }
    }
}
```

---

## Related Resources

- **[Official PayPal Subscriptions API Documentation](https://developer.paypal.com/docs/api/subscriptions/v1/)**
- **[Subscriptions Example](../examples/subscriptions.md)** - Complete subscription workflow
- **[Authentication Guide](../getting-started/authentication.md)** - Configure credentials
- **[Async Operations](../advanced/async.md)** - Learn more about async patterns
