package io.github.eealba.payper.invoices.v2.internal;

import io.github.eealba.payper.core.PayperConfig;
import io.github.eealba.payper.invoices.v2.api.GenerateNextInvoiceNumber;
import io.github.eealba.payper.invoices.v2.api.Invoices;
import io.github.eealba.payper.invoices.v2.api.InvoicingApiClient;
import io.github.eealba.payper.invoices.v2.api.SearchInvoices;
import io.github.eealba.payper.invoices.v2.api.Templates;

class InvoicingApiClientImpl extends InvoicingApiClient {
    InvoicingApiClientImpl(PayperConfig config) {
        super();
    }

    @Override
    public Invoices invoices() {
        return null;
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
