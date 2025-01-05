package io.github.eealba.payper.invoices.v2.internal;

import io.github.eealba.payper.core.Payper;
import io.github.eealba.payper.core.RequestSpecsFactory;
import io.github.eealba.payper.core.Spec;
import io.github.eealba.payper.invoices.v2.api.InvoicesApi;
import io.github.eealba.payper.invoices.v2.model.ErrorDefault;
import io.github.eealba.payper.invoices.v2.model.Invoice;
import io.github.eealba.payper.invoices.v2.model.Invoices;
import io.github.eealba.payper.invoices.v2.model.LinkDescription;

class InvoicesApiImpl implements InvoicesApi {
    private final Payper payper;

    InvoicesApiImpl(Payper payper) {
        this.payper = payper;
    }
    @Override
    public CreateInvoice create() {
        var spec = new Spec<>(CreateInvoice.class, payper,"/v2/invoicing/invoices", Invoice.class,
                ErrorDefault.class);
        return RequestSpecsFactory.getInstance().post(spec);
    }

    @Override
    public ListInvoices list() {
        var spec = new Spec<>(ListInvoices.class, payper,"/v2/invoicing/invoices", Invoices.class,
                ErrorDefault.class);
        return RequestSpecsFactory.getInstance().get(spec);
    }

    @Override
    public SendInvoice send() {
        var spec = new Spec<>(SendInvoice.class, payper,"/v2/invoicing/invoices/{id}/send", LinkDescription.class,
                ErrorDefault.class);
        return RequestSpecsFactory.getInstance().post(spec);
    }

    @Override
    public RemindInvoice remind() {
        var spec = new Spec<>(RemindInvoice.class, payper,"/v2/invoicing/invoices/{id}/remind", Void.class,
                ErrorDefault.class);
        return RequestSpecsFactory.getInstance().post(spec);

    }

    @Override
    public CancelInvoice cancel() {
        var spec = new Spec<>(CancelInvoice.class, payper,"/v2/invoicing/invoices/{id}/cancel", Void.class,
                ErrorDefault.class);
        return RequestSpecsFactory.getInstance().post(spec);
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


}
