package io.github.eealba.payper.invoices.v2.internal;

import io.github.eealba.payper.core.Payper;
import io.github.eealba.payper.core.PayperRequest;
import io.github.eealba.payper.core.spec.RequestSpecImpl;
import io.github.eealba.payper.invoices.v2.api.InvoicesApi;
import io.github.eealba.payper.invoices.v2.model.ErrorDefault;
import io.github.eealba.payper.invoices.v2.model.Invoice;
import io.github.eealba.payper.invoices.v2.model.Invoices;

class InvoicesApiImpl implements InvoicesApi {
    private final Payper payper;

    InvoicesApiImpl(Payper payper) {
        this.payper = payper;
    }
    @Override
    public CreateInvoice create() {
        return new CreateInvoiceImpl(payper);
    }

    @Override
    public ListInvoices list() {
        return new ListInvoicesImpl(payper);
    }

    @Override
    public SendInvoice send() {
        return null;
    }

    @Override
    public RemindInvoice remind() {
        return null;
    }

    @Override
    public CancelInvoice cancel() {
        return null;
    }

    @Override
    public PaymentsInvoices payments() {
        return null;
    }

    @Override
    public RefundsInvoices refunds() {
        return null;
    }

    @Override
    public GenerateQRCode generateQRCode() {
        return null;
    }

    @Override
    public UpdateInvoice update() {
        return null;
    }

    @Override
    public DeleteInvoice delete() {
        return null;
    }

    @Override
    public GetInvoice get() {
        return null;
    }

    private static class CreateInvoiceImpl extends RequestSpecImpl<CreateInvoice, Invoice, Invoice, ErrorDefault>
            implements CreateInvoice {
        CreateInvoiceImpl(Payper payper) {
            super(payper, "/v2/invoicing/invoices", Invoice.class, ErrorDefault.class);
        }
        @Override
        protected PayperRequest.Method getMethod() {
            return PayperRequest.Method.POST;
        }
    }
    private static class ListInvoicesImpl extends
            RequestSpecImpl<ListInvoices, Void, Invoices, ErrorDefault>
            implements ListInvoices {
        private ListInvoicesImpl(Payper payper) {
            super(payper, "/v2/invoicing/invoices", Invoices.class, ErrorDefault.class);
        }

        @Override
        public ListInvoices withPageSize(int pageSize) {
            query("page_size", String.valueOf(pageSize));
            return this;
        }

        @Override
        public ListInvoices withPage(int page) {
            query("page", String.valueOf(page));
            return this;
        }

        @Override
        public ListInvoices withTotalRequired(boolean totalRequired) {
            query("total_required", String.valueOf(totalRequired));
            return this;
        }

        @Override
        public ListInvoices withFields(String fields) {
            query("fields", fields);
            return this;
        }

        @Override
        protected PayperRequest.Method getMethod() {
            return PayperRequest.Method.GET;
        }
    }

}
