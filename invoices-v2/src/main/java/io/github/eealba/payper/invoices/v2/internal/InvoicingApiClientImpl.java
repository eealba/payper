package io.github.eealba.payper.invoices.v2.internal;

import io.github.eealba.payper.core.client.Payper;
import io.github.eealba.payper.core.client.PayperConfig;
import io.github.eealba.payper.core.client.RequestSpecsFactory;
import io.github.eealba.payper.core.client.Spec;
import io.github.eealba.payper.invoices.v2.api.GenerateNextInvoiceNumber;
import io.github.eealba.payper.invoices.v2.api.InvoicesApi;
import io.github.eealba.payper.invoices.v2.api.InvoicingApiClient;
import io.github.eealba.payper.invoices.v2.api.SearchInvoices;
import io.github.eealba.payper.invoices.v2.api.TemplatesApi;
import io.github.eealba.payper.invoices.v2.model.ErrorDefault;
import io.github.eealba.payper.invoices.v2.model.InvoiceNumber;
import io.github.eealba.payper.invoices.v2.model.Invoices;

class InvoicingApiClientImpl extends InvoicingApiClient {
    private final InvoicesApiImpl invoices;
    private final Payper payper;
    private final TemplatesApiImpl templates;


    InvoicingApiClientImpl(PayperConfig config) {
        var payper = Payper.create(config);
        this.payper = payper;
        this.invoices = new InvoicesApiImpl(payper);
        this.templates = new TemplatesApiImpl(payper);
    }

    @Override
    public InvoicesApi invoices() {
        return invoices;
    }

    @Override
    public TemplatesApi templates() {
        return templates;
    }

    @Override
    public GenerateNextInvoiceNumber generateNextInvoiceNumber() {
        var spec = new Spec<>(GenerateNextInvoiceNumber.class, payper,"/v2/invoicing/invoices/next", InvoiceNumber.class,
                ErrorDefault.class);
        return RequestSpecsFactory.getInstance().post(spec);
    }

    @Override
    public SearchInvoices searchInvoices() {
        var spec = new Spec<>(SearchInvoices.class, payper,"/v2/invoicing/search-invoices", Invoices.class,
                ErrorDefault.class);
        return RequestSpecsFactory.getInstance().post(spec);
    }
}
