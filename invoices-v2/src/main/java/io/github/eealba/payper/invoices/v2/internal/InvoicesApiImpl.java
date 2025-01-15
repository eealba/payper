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
import io.github.eealba.payper.core.client.PayperRequest;
import io.github.eealba.payper.core.client.PayperResponse;
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
                        "/v2/invoicing/invoices", Invoice.class, ErrorDefault.class)
                .method(PayperRequest.Method.POST).build();

        return RequestSpecsFactory.getInstance().requestSpec(spec);
    }

    @Override
    public ListInvoices list() {
        var spec = PayperProvider.provider().createSpecBuilder(ListInvoices.class, payper,
                        "/v2/invoicing/invoices", Invoices.class, ErrorDefault.class)
                .build();
        return RequestSpecsFactory.getInstance().requestSpec(spec);
    }

    @Override
    public SendInvoice send() {
        var spec = PayperProvider.provider().createSpecBuilder(SendInvoice.class, payper,
                        "/v2/invoicing/invoices/{id}/send", LinkDescription.class, ErrorDefault.class)
                .method(PayperRequest.Method.POST)
                .build();
        return RequestSpecsFactory.getInstance().requestSpec(spec);
    }

    @Override
    public RemindInvoice remind() {
        var spec = PayperProvider.provider().createSpecBuilder(RemindInvoice.class, payper,
                        "/v2/invoicing/invoices/{id}/remind", Void.class, ErrorDefault.class)
                .method(PayperRequest.Method.POST)
                .build();
        return RequestSpecsFactory.getInstance().requestSpec(spec);
    }

    @Override
    public CancelInvoice cancel() {
        var spec = PayperProvider.provider().createSpecBuilder(CancelInvoice.class, payper,
                        "/v2/invoicing/invoices/{id}/cancel", Void.class, ErrorDefault.class)
                .method(PayperRequest.Method.POST)
                .build();
        return RequestSpecsFactory.getInstance().requestSpec(spec);
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
        var bodyHandler = PayperResponse.BodyHandlers.fromString(QR::new);

                var spec = PayperProvider.provider().createSpecBuilder(GenerateQRCode.class, payper,
                        "/v2/invoicing/invoices/{id}/generate-qr-code", QR.class, ErrorDefault.class)
                .entityHandler(bodyHandler)
                        .method(PayperRequest.Method.POST)
                .build();
        return RequestSpecsFactory.getInstance().requestSpec(spec);
    }

    @Override
    public UpdateInvoice update() {
        var spec = PayperProvider.provider().createSpecBuilder(UpdateInvoice.class, payper,
                        "/v2/invoicing/invoices/{id}", Invoice.class, ErrorDefault.class)
                .alias("withSendToInvoicer", "query,send_to_recipient")
                .alias("withSendToRecipient", "query,send_to_invoicer")
                .method(PayperRequest.Method.PUT)
                .build();
        return RequestSpecsFactory.getInstance().requestSpec(spec);
    }

    @Override
    public DeleteInvoice delete() {
        var spec = PayperProvider.provider().createSpecBuilder(DeleteInvoice.class, payper,
                        "/v2/invoicing/invoices/{id}", Void.class, ErrorDefault.class)
                .method(PayperRequest.Method.DELETE)
                .build();
        return RequestSpecsFactory.getInstance().requestSpec(spec);
    }

    @Override
    public GetInvoice get() {
        var spec = PayperProvider.provider().createSpecBuilder(GetInvoice.class, payper,
                        "/v2/invoicing/invoices/{id}", Invoice.class, ErrorDefault.class)
                .build();
        return RequestSpecsFactory.getInstance().requestSpec(spec);
    }

    private record PaymentsInvoicesImpl(Payper payper) implements PaymentsInvoices {

        @Override
            public CreatePayment create() {
                var spec = PayperProvider.provider().createSpecBuilder(CreatePayment.class, payper,
                                "/v2/invoicing/invoices/{id}/payments", PaymentReference.class,
                                ErrorDefault.class)
                        .method(PayperRequest.Method.POST)
                        .build();
                return RequestSpecsFactory.getInstance().requestSpec(spec);
            }

            @Override
            public DeletePayment delete() {
                var spec = PayperProvider.provider().createSpecBuilder(DeletePayment.class, payper,
                                "/v2/invoicing/invoices/{id}/payments/{id2}", Void.class, ErrorDefault.class)
                        .alias("withTransactionId", "withId2")
                        .method(PayperRequest.Method.DELETE)
                        .build();
                return RequestSpecsFactory.getInstance().requestSpec(spec);
            }
        }

    private record RefundsInvoicesImpl(Payper payper) implements RefundsInvoices {

        @Override
            public CreateRefund create() {
                var spec = PayperProvider.provider().createSpecBuilder(CreateRefund.class, payper,
                                "/v2/invoicing/invoices/{id}/refunds", RefundReference.class, ErrorDefault.class)
                        .method(PayperRequest.Method.POST)
                        .build();
                return RequestSpecsFactory.getInstance().requestSpec(spec);
            }

            @Override
            public DeleteRefund delete() {
                var spec = PayperProvider.provider().createSpecBuilder(DeleteRefund.class, payper,
                                "/v2/invoicing/invoices/{id}/refunds/{id2}", Void.class, ErrorDefault.class)
                        .alias("withTransactionId", "withId2")
                        .method(PayperRequest.Method.DELETE)
                        .build();
                return RequestSpecsFactory.getInstance().requestSpec(spec);
            }
        }
}