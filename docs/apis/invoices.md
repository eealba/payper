# Invoices API

The Invoices API v2 enables you to create, send, and manage invoices for your customers.

**Official Documentation**: [PayPal Invoicing API v2](https://developer.paypal.com/docs/api/invoicing/v2/)

---

## Installation

Add the Invoices API dependency to your project:

=== "Maven"

    ```xml
    <dependency>
        <groupId>io.github.eealba.payper</groupId>
        <artifactId>payper-invoices-v2</artifactId>
        <version>1.0.0</version>
    </dependency>
    ```

=== "Gradle (Groovy)"

    ```groovy
    implementation 'io.github.eealba.payper:payper-invoices-v2:1.0.0'
    ```

=== "Gradle (Kotlin)"

    ```kotlin
    implementation("io.github.eealba.payper:payper-invoices-v2:1.0.0")
    ```

---

## Creating the Client

```java
import io.github.eealba.payper.invoices.v2.api.InvoicingApiClient;

public class Example {
    public static void main(String[] args) {
        // Create client (uses environment variables for credentials)
        var client = InvoicingApiClient.create();
        
        // Access the invoices API
        var invoicesApi = client.invoices();
    }
}
```

---

## Common Operations

### Create a Draft Invoice

Create a new draft invoice:

```java
import io.github.eealba.payper.invoices.v2.api.InvoicingApiClient;
import io.github.eealba.payper.invoices.v2.model.*;

import java.time.LocalDate;
import java.util.List;

public class CreateInvoiceExample {
    public static void main(String[] args) {
        var client = InvoicingApiClient.create();
        
        // Build the invoice
        var invoice = Invoice.builder()
                .detail(InvoiceDetail.builder()
                        .invoiceNumber("INV-" + System.currentTimeMillis())
                        .invoiceDate(LocalDate.now().toString())
                        .currencyCode("USD")
                        .note("Thank you for your business")
                        .build())
                .invoicer(InvoicerInfo.builder()
                        .name(Name.builder()
                                .givenName("John")
                                .surname("Merchant")
                                .build())
                        .emailAddress("merchant@example.com")
                        .build())
                .primaryRecipients(List.of(
                    RecipientInfo.builder()
                            .billingInfo(BillingInfo.builder()
                                    .name(Name.builder()
                                            .givenName("Jane")
                                            .surname("Customer")
                                            .build())
                                    .emailAddress("customer@example.com")
                                    .build())
                            .build()
                ))
                .items(List.of(
                    Item.builder()
                            .name("Web Development Services")
                            .description("5 hours of web development")
                            .quantity("5")
                            .unitAmount(Money.builder()
                                    .currencyCode("USD")
                                    .value("100.00")
                                    .build())
                            .build()
                ))
                .build();
        
        // Create the invoice
        var createdInvoice = client.invoices()
                .create()
                .withBody(invoice)
                .retrieve()
                .toEntity();
        
        System.out.println("Invoice ID: " + createdInvoice.id());
        System.out.println("Invoice Number: " + 
            createdInvoice.detail().invoiceNumber());
        System.out.println("Status: " + createdInvoice.status());
    }
}
```

**Expected Output**:
```
Invoice ID: INV2-ABCD-1234-EFGH-5678
Invoice Number: INV-1702569800000
Status: DRAFT
```

---

### List Invoices

Retrieve a list of invoices with pagination:

```java
import io.github.eealba.payper.invoices.v2.api.InvoicingApiClient;

public class ListInvoicesExample {
    public static void main(String[] args) {
        var client = InvoicingApiClient.create();
        
        // List invoices
        var invoicesList = client.invoices()
                .list()
                .withPage(1)
                .withPageSize(10)
                .withTotalRequired(true)
                .retrieve()
                .toEntity();
        
        System.out.println("Total Invoices: " + invoicesList.totalItems());
        System.out.println("Page: " + invoicesList.totalPages());
        
        // Print each invoice
        invoicesList.items().forEach(invoice -> {
            System.out.println("  - ID: " + invoice.id());
            System.out.println("    Number: " + invoice.detail().invoiceNumber());
            System.out.println("    Status: " + invoice.status());
        });
    }
}
```

---

### Get Invoice Details

Retrieve details of a specific invoice:

```java
import io.github.eealba.payper.invoices.v2.api.InvoicingApiClient;

public class GetInvoiceExample {
    public static void main(String[] args) {
        var client = InvoicingApiClient.create();
        
        // Get invoice by ID
        var invoice = client.invoices()
                .get()
                .withId("INV2-ABCD-1234-EFGH-5678")
                .retrieve()
                .toEntity();
        
        System.out.println("Invoice ID: " + invoice.id());
        System.out.println("Status: " + invoice.status());
        System.out.println("Currency: " + invoice.detail().currencyCode());
        
        // Print items
        System.out.println("Items:");
        invoice.items().forEach(item -> {
            System.out.println("  - " + item.name() + ": " + 
                item.unitAmount().value() + " x " + item.quantity());
        });
        
        // Print total
        if (invoice.amount() != null) {
            System.out.println("Total: " + invoice.amount().value() + " " + 
                              invoice.amount().currencyCode());
        }
    }
}
```

---

### Update an Invoice

Update a draft invoice:

```java
import io.github.eealba.payper.invoices.v2.api.InvoicingApiClient;
import io.github.eealba.payper.invoices.v2.model.*;

import java.util.List;

public class UpdateInvoiceExample {
    public static void main(String[] args) {
        var client = InvoicingApiClient.create();
        
        // Build updated invoice
        var updatedInvoice = Invoice.builder()
                .detail(InvoiceDetail.builder()
                        .note("Updated: Payment due within 30 days")
                        .build())
                .items(List.of(
                    Item.builder()
                            .name("Web Development Services")
                            .description("Updated: 8 hours of web development")
                            .quantity("8")
                            .unitAmount(Money.builder()
                                    .currencyCode("USD")
                                    .value("100.00")
                                    .build())
                            .build()
                ))
                .build();
        
        // Update the invoice
        var invoice = client.invoices()
                .update()
                .withId("INV2-ABCD-1234-EFGH-5678")
                .withBody(updatedInvoice)
                .retrieve()
                .toEntity();
        
        System.out.println("Invoice updated: " + invoice.id());
        System.out.println("Status: " + invoice.status());
    }
}
```

---

### Send an Invoice

Send an invoice to the recipient:

```java
import io.github.eealba.payper.invoices.v2.api.InvoicingApiClient;
import io.github.eealba.payper.invoices.v2.model.SendInvoiceRequest;

public class SendInvoiceExample {
    public static void main(String[] args) {
        var client = InvoicingApiClient.create();
        
        // Send invoice
        var sendRequest = SendInvoiceRequest.builder()
                .sendToInvoicer(true)
                .subject("Invoice for services rendered")
                .note("Please remit payment at your earliest convenience")
                .build();
        
        client.invoices()
                .send()
                .withId("INV2-ABCD-1234-EFGH-5678")
                .withBody(sendRequest)
                .retrieve()
                .toVoid();
        
        System.out.println("Invoice sent successfully");
    }
}
```

---

### Delete an Invoice

Delete a draft invoice:

```java
import io.github.eealba.payper.invoices.v2.api.InvoicingApiClient;

public class DeleteInvoiceExample {
    public static void main(String[] args) {
        var client = InvoicingApiClient.create();
        
        // Delete invoice
        client.invoices()
                .delete()
                .withId("INV2-ABCD-1234-EFGH-5678")
                .retrieve()
                .toVoid();
        
        System.out.println("Invoice deleted successfully");
    }
}
```

!!! warning "Delete Restrictions"
    You can only delete invoices in DRAFT status. Sent or paid invoices cannot be deleted.

---

## Async Operations

All operations support asynchronous execution:

```java
import io.github.eealba.payper.invoices.v2.api.InvoicingApiClient;
import io.github.eealba.payper.invoices.v2.model.*;

import java.util.concurrent.CompletableFuture;

public class AsyncInvoiceExample {
    public static void main(String[] args) {
        var client = InvoicingApiClient.create();
        
        // Create invoice asynchronously
        CompletableFuture<Invoice> invoiceFuture = client.invoices()
                .create()
                .withBody(Invoice.builder()
                        .detail(InvoiceDetail.builder()
                                .invoiceNumber("INV-ASYNC-001")
                                .currencyCode("USD")
                                .build())
                        .build())
                .retrieve()
                .toFuture()
                .thenApply(response -> {
                    if (response.statusCode() == 201) {
                        return response.toEntity();
                    }
                    throw new RuntimeException("Failed to create invoice");
                });
        
        // Chain operations: create then send
        invoiceFuture
                .thenCompose(invoice -> sendInvoiceAsync(client, invoice.id()))
                .thenAccept(v -> System.out.println("Invoice created and sent"))
                .exceptionally(ex -> {
                    System.err.println("Error: " + ex.getMessage());
                    return null;
                });
        
        invoiceFuture.join();
    }
    
    private static CompletableFuture<Void> sendInvoiceAsync(
            InvoicingApiClient client, String invoiceId) {
        return client.invoices()
                .send()
                .withId(invoiceId)
                .withBody(SendInvoiceRequest.builder().build())
                .retrieve()
                .toFuture()
                .thenApply(response -> null);
    }
}
```

---

## Error Handling

Handle errors gracefully:

```java
import io.github.eealba.payper.invoices.v2.api.InvoicingApiClient;
import io.github.eealba.payper.core.PayperException;

public class ErrorHandlingExample {
    public static void main(String[] args) {
        var client = InvoicingApiClient.create();
        
        try {
            var invoice = client.invoices()
                    .get()
                    .withId("INVALID_INVOICE_ID")
                    .retrieve()
                    .toEntity();
            
        } catch (PayperException ex) {
            System.err.println("API Error: " + ex.getMessage());
            System.err.println("Status Code: " + ex.statusCode());
            
            // Handle specific errors
            if (ex.statusCode() == 404) {
                System.err.println("Invoice not found");
            } else if (ex.statusCode() == 422) {
                System.err.println("Invalid invoice data");
            }
        }
    }
}
```

---

## Invoice Lifecycle

```
DRAFT → SENT → PAID
  ↓       ↓      ↓
DELETE  CANCEL REFUNDED
```

| Status | Description | Allowed Actions |
|--------|-------------|-----------------|
| **DRAFT** | Invoice is being prepared | Update, Delete, Send |
| **SENT** | Invoice sent to customer | Cancel, Record Payment |
| **PAID** | Invoice has been paid | Refund, Send Receipt |
| **CANCELLED** | Invoice was cancelled | None |
| **REFUNDED** | Payment was refunded | None |

---

## Related Resources

- **[Official PayPal Invoicing API Documentation](https://developer.paypal.com/docs/api/invoicing/v2/)**
- **[Authentication Guide](../getting-started/authentication.md)** - Configure credentials
- **[Async Operations](../advanced/async.md)** - Learn more about async patterns
- **[Error Handling](../advanced/error-handling.md)** - Advanced error handling strategies
