/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.eealba.payper.invoices.v2.internal;

import io.github.eealba.payper.core.client.Payper;
import io.github.eealba.payper.core.client.PayperProvider;
import io.github.eealba.payper.core.client.RequestSpecsFactory;
import io.github.eealba.payper.invoices.v2.api.InvoicesApi;
import io.github.eealba.payper.invoices.v2.model.ErrorDefault;
import io.github.eealba.payper.invoices.v2.model.Invoice;
import io.github.eealba.payper.invoices.v2.model.Invoices;
import io.github.eealba.payper.invoices.v2.model.LinkDescription;
import io.github.eealba.payper.invoices.v2.model.PaymentReference;
import io.github.eealba.payper.invoices.v2.model.QR;
import io.github.eealba.payper.invoices.v2.model.RefundReference;


class InvoicesApiImpl implements InvoicesApi {
    private final Payper payper;
    private final RefundsInvoices refundsInvoices;
    private final PaymentsInvoices paymentsInvoices;

    InvoicesApiImpl(Payper payper) {
        this.payper = payper;
        this.refundsInvoices = new RefundsInvoicesImpl(payper);
        this.paymentsInvoices = new PaymentsInvoicesImpl(payper);
    }

    @Override
    public CreateInvoice create() {
        var spec = PayperProvider.provider().createSpecBuilder(CreateInvoice.class, payper,
                        "/v2/invoicing/invoices")
                .entityClass(Invoice.class)
                .errorClass(ErrorDefault.class)
                .build();
        return RequestSpecsFactory.getInstance().post(spec);
    }

    @Override
    public ListInvoices list() {
        var spec = PayperProvider.provider().createSpecBuilder(ListInvoices.class, payper,
                        "/v2/invoicing/invoices")
                .entityClass(Invoices.class)
                .errorClass(ErrorDefault.class)
                .build();
        return RequestSpecsFactory.getInstance().get(spec);
    }

    @Override
    public SendInvoice send() {
        var spec = PayperProvider.provider().createSpecBuilder(SendInvoice.class, payper,
                        "/v2/invoicing/invoices/{id}/send")
                .entityClass(LinkDescription.class)
                .errorClass(ErrorDefault.class)
                .build();
        return RequestSpecsFactory.getInstance().post(spec);
    }

    @Override
    public RemindInvoice remind() {
        var spec = PayperProvider.provider().createSpecBuilder(RemindInvoice.class, payper,
                        "/v2/invoicing/invoices/{id}/remind")
                .entityClass(Void.class)
                .errorClass(ErrorDefault.class)
                .build();
        return RequestSpecsFactory.getInstance().post(spec);
    }

    @Override
    public CancelInvoice cancel() {
        var spec = PayperProvider.provider().createSpecBuilder(CancelInvoice.class, payper,
                        "/v2/invoicing/invoices/{id}/cancel")
                .entityClass(Void.class)
                .errorClass(ErrorDefault.class)
                .build();
        return RequestSpecsFactory.getInstance().post(spec);
    }

    @Override
    public PaymentsInvoices payments() {
        return paymentsInvoices;
    }

    @Override
    public RefundsInvoices refunds() {
        return refundsInvoices;
    }

    @Override
    public GenerateQRCode generateQRCode() {
        var spec = PayperProvider.provider().createSpecBuilder(GenerateQRCode.class, payper,
                        "/v2/invoicing/invoices/{id}/generate-qr-code")
                .entityClass(QR.class)
                .errorClass(ErrorDefault.class)
                .build();
        return RequestSpecsFactory.getInstance().post(spec);
    }

    @Override
    public UpdateInvoice update() {
        var spec = PayperProvider.provider().createSpecBuilder(UpdateInvoice.class, payper,
                        "/v2/invoicing/invoices/{id}")
                .entityClass(Invoice.class)
                .errorClass(ErrorDefault.class)
                .alias("send_to_recipient", "query,send_to_recipient")
                .alias("send_to_invoicer", "query,send_to_invoicer")
                .build();
        return RequestSpecsFactory.getInstance().put(spec);
    }

    @Override
    public DeleteInvoice delete() {
        var spec = PayperProvider.provider().createSpecBuilder(DeleteInvoice.class, payper,
                        "/v2/invoicing/invoices/{id}")
                .entityClass(Void.class)
                .errorClass(ErrorDefault.class)
                .build();
        return RequestSpecsFactory.getInstance().delete(spec);
    }

    @Override
    public GetInvoice get() {
        var spec = PayperProvider.provider().createSpecBuilder(GetInvoice.class, payper, "/v2/invoicing/invoices/{id}")
                .entityClass(Invoice.class)
                .errorClass(ErrorDefault.class)
                .build();
        return RequestSpecsFactory.getInstance().get(spec);
    }

    private record PaymentsInvoicesImpl(Payper payper) implements PaymentsInvoices {

        @Override
            public CreatePayment create() {
                var spec = PayperProvider.provider().createSpecBuilder(CreatePayment.class, payper,
                                "/v2/invoicing/invoices/{id}/payments")
                        .entityClass(PaymentReference.class)
                        .errorClass(ErrorDefault.class)
                        .build();
                return RequestSpecsFactory.getInstance().post(spec);
            }

            @Override
            public DeletePayment delete() {
                var spec = PayperProvider.provider().createSpecBuilder(DeletePayment.class, payper,
                                "/v2/invoicing/invoices/{id}/payments/{id2}")
                        .entityClass(Void.class)
                        .errorClass(ErrorDefault.class)
                        .alias("withTransactionId", "withId2")
                        .build();
                return RequestSpecsFactory.getInstance().delete(spec);
            }
        }

    private record RefundsInvoicesImpl(Payper payper) implements RefundsInvoices {

        @Override
            public CreateRefund create() {
                var spec = PayperProvider.provider().createSpecBuilder(CreateRefund.class, payper,
                                "/v2/invoicing/invoices/{id}/refunds")
                        .entityClass(RefundReference.class)
                        .errorClass(ErrorDefault.class)
                        .build();
                return RequestSpecsFactory.getInstance().post(spec);
            }

            @Override
            public DeleteRefund delete() {
                var spec = PayperProvider.provider().createSpecBuilder(DeleteRefund.class, payper,
                                "/v2/invoicing/invoices/{id}/refunds/{id2}")
                        .entityClass(Void.class)
                        .errorClass(ErrorDefault.class)
                        .alias("withTransactionId", "withId2")
                        .build();
                return RequestSpecsFactory.getInstance().delete(spec);
            }
        }
}