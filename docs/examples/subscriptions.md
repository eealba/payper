# Subscriptions Example

Learn how to implement recurring billing with the PayPal Subscriptions API v1.

This guide walks you through the **subscriptions-app** example from the [payper-examples repository](https://github.com/eealba/payper-examples/tree/main/subscriptions-app).

---

## Overview

The Subscriptions API enables you to set up recurring payments. This example demonstrates:

1. **Create** a product
2. **Create** a billing plan
3. **Create** a subscription
4. **Manage** subscription lifecycle

The example includes both **synchronous** (`App.java`) and **asynchronous** (`AppAsync.java`) implementations.

---

## Subscription Workflow

```
Create Product → Create Billing Plan → Create Subscription → Customer Approval
     ↓                  ↓                      ↓                    ↓
 Product ID         Plan ID            Subscription ID         ACTIVE Status
```

---

## Prerequisites

- Java 17 or higher
- Maven 3.6+
- PayPal Sandbox credentials
- Understanding of subscription billing concepts

---

## Synchronous Example

### Step 1: Create a Product

```java
import io.github.eealba.payper.subscriptions.v1.api.SubscriptionsApiClient;
import io.github.eealba.payper.subscriptions.v1.model.*;

public class SubscriptionApp {
    public static void main(String[] args) {
        // Create the client
        var client = SubscriptionsApiClient.create();
        
        // Step 1: Create a product
        System.out.println("Step 1: Creating product...");
        var productRequest = ProductRequest.builder()
                .name("Premium Membership")
                .description("Access to premium features and content")
                .type(Product.Type.SERVICE)
                .category(Product.Category.SOFTWARE)
                .imageUrl("https://example.com/premium.jpg")
                .homeUrl("https://example.com")
                .build();
        
        var product = client.products()
                .create()
                .withBody(productRequest)
                .retrieve()
                .toEntity();
        
        System.out.println("✓ Product created");
        System.out.println("  Product ID: " + product.id());
        System.out.println("  Name: " + product.name());
    }
}
```

---

### Step 2: Create a Billing Plan

```java
// Step 2: Create billing plan
System.out.println("\nStep 2: Creating billing plan...");

var planRequest = PlanRequest.builder()
        .productId(product.id())
        .name("Premium Membership - Monthly")
        .description("$19.99 per month subscription")
        .status(PlanStatus.ACTIVE)
        .billingCycles(List.of(
            // Trial period (optional)
            BillingCycle.builder()
                    .frequency(Frequency.builder()
                            .intervalUnit(Frequency.IntervalUnit.DAY)
                            .intervalCount(7)
                            .build())
                    .tenureType(BillingCycle.TenureType.TRIAL)
                    .sequence(1)
                    .totalCycles(1)
                    .pricingScheme(PricingScheme.builder()
                            .fixedPrice(Money.builder()
                                    .currencyCode("USD")
                                    .value("0.00")
                                    .build())
                            .build())
                    .build(),
            // Regular billing
            BillingCycle.builder()
                    .frequency(Frequency.builder()
                            .intervalUnit(Frequency.IntervalUnit.MONTH)
                            .intervalCount(1)
                            .build())
                    .tenureType(BillingCycle.TenureType.REGULAR)
                    .sequence(2)
                    .totalCycles(0) // 0 = unlimited
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

var plan = client.billingPlans()
        .create()
        .withBody(planRequest)
        .retrieve()
        .toEntity();

System.out.println("✓ Billing plan created");
System.out.println("  Plan ID: " + plan.id());
System.out.println("  Name: " + plan.name());
System.out.println("  Status: " + plan.status());
```

---

### Step 3: Create a Subscription

```java
// Step 3: Create subscription
System.out.println("\nStep 3: Creating subscription...");

var subscriptionRequest = SubscriptionRequest.builder()
        .planId(plan.id())
        .startTime(ZonedDateTime.now().plusDays(1).toString())
        .subscriber(Subscriber.builder()
                .name(Name.builder()
                        .givenName("John")
                        .surname("Doe")
                        .build())
                .emailAddress("john.doe@example.com")
                .build())
        .applicationContext(ApplicationContext.builder()
                .brandName("My Store")
                .locale("en-US")
                .shippingPreference(ApplicationContext.ShippingPreference.NO_SHIPPING)
                .userAction(ApplicationContext.UserAction.SUBSCRIBE_NOW)
                .paymentMethod(PaymentMethod.builder()
                        .payerSelected(PaymentMethod.PayerSelected.PAYPAL)
                        .payeePreferred(PaymentMethod.PayeePreferred.IMMEDIATE_PAYMENT_REQUIRED)
                        .build())
                .returnUrl("https://example.com/return")
                .cancelUrl("https://example.com/cancel")
                .build())
        .build();

var subscription = client.subscriptions()
        .create()
        .withBody(subscriptionRequest)
        .retrieve()
        .toEntity();

System.out.println("✓ Subscription created");
System.out.println("  Subscription ID: " + subscription.id());
System.out.println("  Status: " + subscription.status());

// Get approval URL
var approvalUrl = subscription.links().stream()
        .filter(link -> "approve".equals(link.rel()))
        .findFirst()
        .map(LinkDescription::href)
        .orElse(null);

System.out.println("  Approval URL: " + approvalUrl);
System.out.println("\n→ Send customer to approval URL to complete subscription");
```

**Output**:
```
Step 1: Creating product...
✓ Product created
  Product ID: PROD-6XB24663H4094933M
  Name: Premium Membership

Step 2: Creating billing plan...
✓ Billing plan created
  Plan ID: P-5ML4271244454362WXNWU5NQ
  Name: Premium Membership - Monthly
  Status: ACTIVE

Step 3: Creating subscription...
✓ Subscription created
  Subscription ID: I-BW452GLLEP1G
  Status: APPROVAL_PENDING
  Approval URL: https://www.sandbox.paypal.com/...

→ Send customer to approval URL to complete subscription
```

---

## Asynchronous Example

The async version uses `CompletableFuture` to chain operations:

```java
import java.util.concurrent.CompletableFuture;

public class SubscriptionAppAsync {
    public static void main(String[] args) {
        var client = SubscriptionsApiClient.create();
        
        System.out.println("🚀 Starting async subscription creation...\n");
        
        // Chain all operations asynchronously
        createProductAsync(client)
                .thenCompose(product -> {
                    System.out.println("✓ Product created: " + product.id());
                    return createPlanAsync(client, product.id());
                })
                .thenCompose(plan -> {
                    System.out.println("✓ Plan created: " + plan.id());
                    return createSubscriptionAsync(client, plan.id());
                })
                .thenAccept(subscription -> {
                    System.out.println("✓ Subscription created: " + subscription.id());
                    
                    var approvalUrl = subscription.links().stream()
                            .filter(link -> "approve".equals(link.rel()))
                            .findFirst()
                            .map(LinkDescription::href)
                            .orElse(null);
                    
                    System.out.println("\n✅ All done!");
                    System.out.println("Approval URL: " + approvalUrl);
                })
                .exceptionally(ex -> {
                    System.err.println("❌ Error: " + ex.getMessage());
                    ex.printStackTrace();
                    return null;
                })
                .join(); // Wait for completion
        
        System.out.println("\n🎉 Async workflow completed!");
    }
    
    private static CompletableFuture<Product> createProductAsync(
            SubscriptionsApiClient client) {
        return client.products()
                .create()
                .withBody(ProductRequest.builder()
                        .name("Premium Membership Async")
                        .type(Product.Type.SERVICE)
                        .category(Product.Category.SOFTWARE)
                        .build())
                .retrieve()
                .toFuture()
                .thenApply(response -> response.toEntity());
    }
    
    private static CompletableFuture<Plan> createPlanAsync(
            SubscriptionsApiClient client, String productId) {
        return client.billingPlans()
                .create()
                .withBody(PlanRequest.builder()
                        .productId(productId)
                        .name("Premium Plan Async")
                        .status(PlanStatus.ACTIVE)
                        .billingCycles(List.of(
                            BillingCycle.builder()
                                    .frequency(Frequency.builder()
                                            .intervalUnit(Frequency.IntervalUnit.MONTH)
                                            .intervalCount(1)
                                            .build())
                                    .tenureType(BillingCycle.TenureType.REGULAR)
                                    .sequence(1)
                                    .totalCycles(0)
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
                                .build())
                        .build())
                .retrieve()
                .toFuture()
                .thenApply(response -> response.toEntity());
    }
    
    private static CompletableFuture<Subscription> createSubscriptionAsync(
            SubscriptionsApiClient client, String planId) {
        return client.subscriptions()
                .create()
                .withBody(SubscriptionRequest.builder()
                        .planId(planId)
                        .subscriber(Subscriber.builder()
                                .emailAddress("subscriber@example.com")
                                .build())
                        .applicationContext(ApplicationContext.builder()
                                .returnUrl("https://example.com/return")
                                .cancelUrl("https://example.com/cancel")
                                .build())
                        .build())
                .retrieve()
                .toFuture()
                .thenApply(response -> response.toEntity());
    }
}
```

---

## Managing Subscriptions

### Get Subscription Details

```java
var subscription = client.subscriptions()
        .get()
        .withId("I-BW452GLLEP1G")
        .retrieve()
        .toEntity();

System.out.println("Status: " + subscription.status());
System.out.println("Next billing: " + subscription.billingInfo().nextBillingTime());
```

### Suspend a Subscription

```java
client.subscriptions()
        .suspend()
        .withId("I-BW452GLLEP1G")
        .withBody(SuspendRequest.builder()
                .reason("Customer requested pause")
                .build())
        .retrieve()
        .toVoid();

System.out.println("Subscription suspended");
```

### Reactivate a Subscription

```java
client.subscriptions()
        .activate()
        .withId("I-BW452GLLEP1G")
        .withBody(ActivateRequest.builder()
                .reason("Customer requested reactivation")
                .build())
        .retrieve()
        .toVoid();

System.out.println("Subscription reactivated");
```

### Cancel a Subscription

```java
client.subscriptions()
        .cancel()
        .withId("I-BW452GLLEP1G")
        .withBody(CancelRequest.builder()
                .reason("Customer cancelled membership")
                .build())
        .retrieve()
        .toVoid();

System.out.println("Subscription cancelled");
```

---

## Common Patterns

### Yearly with Discount

```java
BillingCycle.builder()
        .frequency(Frequency.builder()
                .intervalUnit(Frequency.IntervalUnit.YEAR)
                .intervalCount(1)
                .build())
        .tenureType(BillingCycle.TenureType.REGULAR)
        .sequence(1)
        .totalCycles(0)
        .pricingScheme(PricingScheme.builder()
                .fixedPrice(Money.builder()
                        .currencyCode("USD")
                        .value("199.00") // Save $40 vs monthly
                        .build())
                .build())
        .build()
```

### Free Trial then Paid

```java
List.of(
    // 14-day free trial
    BillingCycle.builder()
            .frequency(Frequency.builder()
                    .intervalUnit(Frequency.IntervalUnit.DAY)
                    .intervalCount(14)
                    .build())
            .tenureType(BillingCycle.TenureType.TRIAL)
            .sequence(1)
            .totalCycles(1)
            .pricingScheme(PricingScheme.builder()
                    .fixedPrice(Money.builder()
                            .currencyCode("USD")
                            .value("0.00")
                            .build())
                    .build())
            .build(),
    // Then regular monthly billing
    BillingCycle.builder()
            .frequency(/* monthly */)
            .tenureType(BillingCycle.TenureType.REGULAR)
            .sequence(2)
            .totalCycles(0)
            .pricingScheme(/* $19.99 */)
            .build()
)
```

### Setup Fee

```java
PaymentPreferences.builder()
        .autoBillOutstanding(true)
        .setupFee(Money.builder()
                .currencyCode("USD")
                .value("9.99")
                .build())
        .setupFeeFailureAction(PaymentPreferences.SetupFeeFailureAction.CANCEL)
        .build()
```

---

## Testing in Sandbox

1. Create subscription with test credentials
2. Use sandbox buyer account to approve
3. Monitor billing cycles in PayPal Dashboard
4. Test suspension, reactivation, and cancellation

---

## Related Resources

- **[Subscriptions API Documentation](../apis/subscriptions.md)** - Complete API reference
- **[Catalog Products API](../apis/catalog.md)** - Create products for plans
- **[GitHub Example](https://github.com/eealba/payper-examples/tree/main/subscriptions-app)** - Full source code
- **[Async Operations Guide](../advanced/async.md)** - Learn more about async patterns
- **[PayPal Subscriptions Guide](https://developer.paypal.com/docs/api/subscriptions/v1/)** - Official documentation
