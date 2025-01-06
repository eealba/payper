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
import io.github.eealba.payper.core.client.RequestSpecsFactory;
import io.github.eealba.payper.core.client.Spec;
import io.github.eealba.payper.invoices.v2.api.InvoicesApi;
import io.github.eealba.payper.invoices.v2.model.ErrorDefault;
import io.github.eealba.payper.invoices.v2.model.Invoice;
import io.github.eealba.payper.invoices.v2.model.Invoices;
import io.github.eealba.payper.invoices.v2.model.LinkDescription;
import io.github.eealba.payper.invoices.v2.model.PaymentReference;
import io.github.eealba.payper.invoices.v2.model.QR;
import io.github.eealba.payper.invoices.v2.model.RefundReference;

import java.util.HashMap;

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
        return paymentsInvoices;
    }

    @Override
    public RefundsInvoices refunds() {
        return refundsInvoices;
    }

    @Override
    public GenerateQRCode generateQRCode() {
        var spec = new Spec<>(GenerateQRCode.class, payper,"/v2/invoicing/invoices/{id}/generate_gr_code",
                QR.class, ErrorDefault.class);
        return RequestSpecsFactory.getInstance().post(spec);
    }

    @Override
    public UpdateInvoice update() {
        var spec = new Spec<>(UpdateInvoice.class, payper,"/v2/invoicing/invoices/{id}", Invoice.class,
                ErrorDefault.class);
        var map = new HashMap<String, String>();
        map.put("send_to_recipient", "query,send_to_recipient");
        map.put("send_to_invoicer", "query,send_to_invoicer");
        return RequestSpecsFactory.getInstance().put(spec, map);
    }

    @Override
    public DeleteInvoice delete() {
        var spec = new Spec<>(DeleteInvoice.class, payper,"/v2/invoicing/invoices/{id}", Void.class,
                ErrorDefault.class);
        return RequestSpecsFactory.getInstance().delete(spec);
    }

    @Override
    public GetInvoice get() {
        var spec = new Spec<>(GetInvoice.class, payper,"/v2/invoicing/invoices/{id}", Invoice.class,
                ErrorDefault.class);
        return RequestSpecsFactory.getInstance().get(spec);
    }

    private static class PaymentsInvoicesImpl implements PaymentsInvoices {
        private final Payper payper;

        PaymentsInvoicesImpl(Payper payper) {
            this.payper = payper;
        }

        @Override
        public CreatePayment create() {
            var spec = new Spec<>(CreatePayment.class, payper,"/v2/invoicing/invoices/{id}/payments",
                    PaymentReference.class, ErrorDefault.class);
            return RequestSpecsFactory.getInstance().post(spec);
        }

        @Override
        public DeletePayment delete() {
            var spec = new Spec<>(DeletePayment.class, payper,"/v2/invoicing/invoices/{id}/payments/{id2}",
                    Void.class, ErrorDefault.class);
            var map = new HashMap<String, String>();
            map.put("withTransactionId", "id2");
            return RequestSpecsFactory.getInstance().delete(spec, map);
        }
    }

    private static class RefundsInvoicesImpl implements RefundsInvoices {
        private final Payper payper;

        RefundsInvoicesImpl(Payper payper) {
            this.payper = payper;
        }

        @Override
        public CreateRefund create() {
            var spec = new Spec<>(CreateRefund.class, payper,"/v2/invoicing/invoices/{id}/refunds",
                    RefundReference.class, ErrorDefault.class);
            return RequestSpecsFactory.getInstance().post(spec);
        }

        @Override
        public DeleteRefund delete() {
            var spec = new Spec<>(DeleteRefund.class, payper,"/v2/invoicing/invoices/{id}/refunds/{id2}",
                    Void.class, ErrorDefault.class);
            var map = new HashMap<String, String>();
            map.put("withTransactionId", "id2");
            return RequestSpecsFactory.getInstance().delete(spec, map);
        }
    }
}
