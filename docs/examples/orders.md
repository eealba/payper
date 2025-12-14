# Orders Example

Learn how to implement a complete order workflow with the PayPal Orders API v2.

This guide walks you through the **payper-orders-basic** example from the [payper-examples repository](https://github.com/eealba/payper-examples/tree/main/payper-orders-basic).

---

## Overview

The Orders API enables you to create, manage, and capture PayPal orders for processing payments. This example demonstrates the complete order lifecycle:

1. **Create** an order
2. **Retrieve** order details
3. **Confirm** payment source
4. **Capture** the payment

---

## Order Workflow

```
Create Order → Customer Approval → Capture Payment
     ↓              ↓                    ↓
  Order ID    Approval URL          Completed
```

---

## Prerequisites

- Java 17 or higher
- Maven 3.6+
- PayPal Sandbox credentials
- Understanding of PayPal checkout flow

---

## Complete Example

### Step 1: Create an Order

```java
import io.github.eealba.payper.orders.v2.api.OrdersApiClient;
import io.github.eealba.payper.orders.v2.model.*;

import java.util.List;

public class OrdersExample {
    public static void main(String[] args) {
        // Create the client
        var client = OrdersApiClient.create();
        
        // Build the order request
        var orderRequest = OrderRequest.builder()
                .intent(CheckoutPaymentIntent.CAPTURE)
                .purchaseUnits(List.of(
                    PurchaseUnitRequest.builder()
                            .referenceId("default")
                            .amount(AmountWithBreakdown.builder()
                                    .currencyCode("USD")
                                    .value("100.00")
                                    .breakdown(AmountBreakdown.builder()
                                            .itemTotal(Money.builder()
                                                    .currencyCode("USD")
                                                    .value("85.00")
                                                    .build())
                                            .shipping(Money.builder()
                                                    .currencyCode("USD")
                                                    .value("10.00")
                                                    .build())
                                            .taxTotal(Money.builder()
                                                    .currencyCode("USD")
                                                    .value("5.00")
                                                    .build())
                                            .build())
                                    .build())
                            .items(List.of(
                                Item.builder()
                                        .name("Premium Widget")
                                        .description("High-quality widget")
                                        .quantity("1")
                                        .unitAmount(Money.builder()
                                                .currencyCode("USD")
                                                .value("85.00")
                                                .build())
                                        .build()
                            ))
                            .build()
                ))
                .applicationContext(ApplicationContext.builder()
                        .brandName("My Store")
                        .locale("en-US")
                        .returnUrl("https://example.com/return")
                        .cancelUrl("https://example.com/cancel")
                        .userAction(ApplicationContext.UserAction.PAY_NOW)
                        .build())
                .build();
        
        // Create the order
        var order = client.orders()
                .create()
                .withBody(orderRequest)
                .retrieve()
                .toEntity();
        
        System.out.println("✅ Order created successfully!");
        System.out.println("Order ID: " + order.id());
        System.out.println("Status: " + order.status());
        
        // Get the approval URL
        var approvalUrl = order.links().stream()
                .filter(link -> "approve".equals(link.rel()))
                .findFirst()
                .map(LinkDescription::href)
                .orElse(null);
        
        System.out.println("Approval URL: " + approvalUrl);
        System.out.println("\n→ Send customer to approval URL to complete checkout");
    }
}
```

**Output**:
```
✅ Order created successfully!
Order ID: 5O190127TN364715T
Status: CREATED
Approval URL: https://www.sandbox.paypal.com/checkoutnow?token=5O190127TN364715T

→ Send customer to approval URL to complete checkout
```

---

### Step 2: Retrieve Order Details

After the customer approves the order, retrieve the updated details:

```java
public static void retrieveOrder(OrdersApiClient client, String orderId) {
    // Retrieve order
    var order = client.orders()
            .get()
            .withId(orderId)
            .retrieve()
            .toEntity();
    
    System.out.println("\n📋 Order Details:");
    System.out.println("Order ID: " + order.id());
    System.out.println("Status: " + order.status());
    System.out.println("Intent: " + order.intent());
    
    // Display purchase units
    order.purchaseUnits().forEach(unit -> {
        System.out.println("\nPurchase Unit:");
        System.out.println("  Reference ID: " + unit.referenceId());
        System.out.println("  Amount: " + unit.amount().value() + " " + 
                          unit.amount().currencyCode());
        
        // Display items
        if (unit.items() != null) {
            System.out.println("  Items:");
            unit.items().forEach(item -> {
                System.out.println("    - " + item.name() + ": " + 
                    item.unitAmount().value() + " x " + item.quantity());
            });
        }
    });
    
    // Display payer info (if approved)
    if (order.payer() != null) {
        System.out.println("\nPayer:");
        if (order.payer().name() != null) {
            System.out.println("  Name: " + 
                order.payer().name().givenName() + " " + 
                order.payer().name().surname());
        }
        if (order.payer().emailAddress() != null) {
            System.out.println("  Email: " + order.payer().emailAddress());
        }
    }
}
```

---

### Step 3: Capture the Order

Once approved, capture the payment:

```java
public static void captureOrder(OrdersApiClient client, String orderId) {
    // Capture the order
    var order = client.orders()
            .capture()
            .withId(orderId)
            .withBody(OrderCaptureRequest.builder().build())
            .retrieve()
            .toEntity();
    
    System.out.println("\n💰 Order captured successfully!");
    System.out.println("Order ID: " + order.id());
    System.out.println("Status: " + order.status());
    
    // Get capture details
    order.purchaseUnits().forEach(unit -> {
        if (unit.payments() != null && unit.payments().captures() != null) {
            unit.payments().captures().forEach(capture -> {
                System.out.println("\nCapture:");
                System.out.println("  Capture ID: " + capture.id());
                System.out.println("  Status: " + capture.status());
                System.out.println("  Amount: " + capture.amount().value() + " " + 
                                  capture.amount().currencyCode());
                System.out.println("  Create Time: " + capture.createTime());
            });
        }
    });
}
```

**Output**:
```
💰 Order captured successfully!
Order ID: 5O190127TN364715T
Status: COMPLETED

Capture:
  Capture ID: 2GG279541U471931P
  Status: COMPLETED
  Amount: 100.00 USD
  Create Time: 2024-01-15T10:35:00Z
```

---

## Complete Workflow

Here's the complete flow combining all steps:

```java
public class CompleteOrderWorkflow {
    public static void main(String[] args) {
        var client = OrdersApiClient.create();
        
        try {
            // Step 1: Create order
            System.out.println("Step 1: Creating order...");
            var order = createOrder(client);
            String orderId = order.id();
            String approvalUrl = getApprovalUrl(order);
            
            System.out.println("\n→ Customer should approve at: " + approvalUrl);
            System.out.println("→ (In sandbox, you can approve using PayPal test accounts)");
            
            // Simulate customer approval
            System.out.println("\n⏳ Waiting for approval...");
            // In real application, customer would be redirected to approval URL
            // and return to your site after approval
            
            // Step 2: Retrieve order after approval
            System.out.println("\nStep 2: Retrieving order details...");
            retrieveOrder(client, orderId);
            
            // Step 3: Capture payment
            System.out.println("\nStep 3: Capturing payment...");
            captureOrder(client, orderId);
            
            System.out.println("\n✅ Order workflow completed successfully!");
            
        } catch (Exception e) {
            System.err.println("\n❌ Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static Order createOrder(OrdersApiClient client) {
        // Create order implementation (from Step 1)
        // ...
    }
    
    private static String getApprovalUrl(Order order) {
        return order.links().stream()
                .filter(link -> "approve".equals(link.rel()))
                .findFirst()
                .map(LinkDescription::href)
                .orElseThrow(() -> new RuntimeException("Approval URL not found"));
    }
    
    private static void retrieveOrder(OrdersApiClient client, String orderId) {
        // Retrieve order implementation (from Step 2)
        // ...
    }
    
    private static void captureOrder(OrdersApiClient client, String orderId) {
        // Capture order implementation (from Step 3)
        // ...
    }
}
```

---

## Advanced Scenarios

### Authorize Instead of Capture

For delayed capture scenarios (e.g., shipping delay):

```java
// Create order with AUTHORIZE intent
var orderRequest = OrderRequest.builder()
        .intent(CheckoutPaymentIntent.AUTHORIZE)
        // ... other fields
        .build();

// After approval, authorize the payment
var order = client.orders()
        .authorize()
        .withId(orderId)
        .withBody(OrderAuthorizeRequest.builder().build())
        .retrieve()
        .toEntity();

// Later, capture using Payments API
var authorizationId = order.purchaseUnits().get(0)
        .payments()
        .authorizations().get(0)
        .id();
// Use Payments API to capture authorization
```

### Update Order Before Capture

Update order details before capturing:

```java
// Update order amount
var patches = List.of(
    Patch.builder()
            .op(Patch.Op.REPLACE)
            .path("/purchase_units/@reference_id=='default'/amount")
            .value(AmountWithBreakdown.builder()
                    .currencyCode("USD")
                    .value("120.00")
                    .build())
            .build()
);

client.orders()
        .update()
        .withId(orderId)
        .withBody(new PatchRequest(patches))
        .retrieve()
        .toVoid();
```

### Handle Multiple Purchase Units

```java
var orderRequest = OrderRequest.builder()
        .intent(CheckoutPaymentIntent.CAPTURE)
        .purchaseUnits(List.of(
            PurchaseUnitRequest.builder()
                    .referenceId("unit-1")
                    .amount(/* ... */)
                    .build(),
            PurchaseUnitRequest.builder()
                    .referenceId("unit-2")
                    .amount(/* ... */)
                    .build()
        ))
        .build();
```

---

## Testing in Sandbox

### Using PayPal Test Accounts

1. Go to [PayPal Developer Dashboard](https://developer.paypal.com/dashboard/)
2. Navigate to "Sandbox" → "Accounts"
3. Use test buyer account credentials to approve orders
4. Test various scenarios (approved, declined, etc.)

### Test Card Numbers

PayPal provides test card numbers for sandbox:
- **Successful payment**: Use sandbox buyer account
- **Declined payment**: 4000000000000002
- **3D Secure**: 4000000000000028

---

## Error Handling

```java
import io.github.eealba.payper.core.PayperException;

try {
    var order = client.orders()
            .capture()
            .withId(orderId)
            .withBody(OrderCaptureRequest.builder().build())
            .retrieve()
            .toEntity();
    
} catch (PayperException e) {
    switch (e.statusCode()) {
        case 404:
            System.err.println("Order not found");
            break;
        case 422:
            System.err.println("Order cannot be captured (wrong status)");
            break;
        default:
            System.err.println("Error: " + e.getMessage());
    }
}
```

---

## Related Resources

- **[Orders API Documentation](../apis/orders.md)** - Complete API reference
- **[Payments API](../apis/payments.md)** - For authorization capture and refunds
- **[GitHub Example](https://github.com/eealba/payper-examples/tree/main/payper-orders-basic)** - Full source code
- **[PayPal Orders API Guide](https://developer.paypal.com/docs/api/orders/v2/)** - Official documentation
