package io.github.eealba.payper.invoices.v2.internal;

import io.github.eealba.payper.core.Payper;
import io.github.eealba.payper.core.PayperConfig;
import io.github.eealba.payper.invoices.v2.api.GenerateNextInvoiceNumber;
import io.github.eealba.payper.invoices.v2.api.InvoicesApi;
import io.github.eealba.payper.invoices.v2.api.InvoicingApiClient;
import io.github.eealba.payper.invoices.v2.api.SearchInvoices;
import io.github.eealba.payper.invoices.v2.api.Templates;

class InvoicingApiClientImpl extends InvoicingApiClient {
    private final InvoicesApiImpl invoices;

    InvoicingApiClientImpl(PayperConfig config) {
        var payper = Payper.create(config);
        this.invoices = new InvoicesApiImpl(payper);
    }

    @Override
    public InvoicesApi invoices() {
        return invoices;
    }

    @Override
    public Templates templates() {
        return null;
    }

    @Override
    public GenerateNextInvoiceNumber generateNextInvoiceNumber() {
        return null;
    }

    @Override
    public SearchInvoices searchInvoices() {
        return null;
    }
}
