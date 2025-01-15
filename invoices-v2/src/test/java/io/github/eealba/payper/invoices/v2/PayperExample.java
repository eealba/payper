package io.github.eealba.payper.invoices.v2;
import io.github.eealba.payper.invoices.v2.api.InvoicesApi;
import io.github.eealba.payper.invoices.v2.api.InvoicingApiClient;
import io.github.eealba.payper.invoices.v2.model.Invoice;

public class PayperExample {
    public static void main(String[] args) {
        InvoicesApi invoicesApi = InvoicingApiClient.create().invoices();

        // Create an invoice
        var invoice = invoicesApi.create()
                .withBody(Invoice.builder().build())
                .retrieve()
                .toEntity();

        // List invoices
        var listInvoices = invoicesApi.list()
                .withPage(1)
                .withPageSize(10)
                .withTotalRequired(true)
                .retrieve()
                .toEntity();

        // Get an invoice
        invoice = invoicesApi.get().withId("invoice-id").retrieve().toEntity();

        // Update an invoice
        var updateInvoice = invoicesApi.update().withId("invoice-id")
                .withBody(Invoice.builder().build())
                .retrieve()
                .toEntity();

        // Delete an invoice
        invoicesApi.delete().withId("invoice-id").retrieve().toVoid();
    }
}