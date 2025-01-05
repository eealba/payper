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
package io.github.eealba.payper.invoices.v2.api;

import io.github.eealba.payper.core.spec.RequestSpec;
import io.github.eealba.payper.invoices.v2.model.ErrorDefault;
import io.github.eealba.payper.invoices.v2.model.Invoice;
import io.github.eealba.payper.invoices.v2.model.Invoices;
import io.github.eealba.payper.invoices.v2.model.LinkDescription;
import io.github.eealba.payper.invoices.v2.model.Notification;
import io.github.eealba.payper.invoices.v2.model.PaymentDetail;
import io.github.eealba.payper.invoices.v2.model.PaymentReference;
import io.github.eealba.payper.invoices.v2.model.QR;
import io.github.eealba.payper.invoices.v2.model.QrConfig;
import io.github.eealba.payper.invoices.v2.model.RefundDetail;
import io.github.eealba.payper.invoices.v2.model.RefundReference;

public interface InvoicesApi {
    CreateInvoice create();
    ListInvoices list();
    SendInvoice send();
    RemindInvoice remind();
    CancelInvoice cancel();

    PaymentsInvoices payments();
    RefundsInvoices refunds();
    GenerateQRCode generateQRCode();

    UpdateInvoice update();
    DeleteInvoice delete();
    GetInvoice get();

    interface CreateInvoice extends RequestSpec<Invoice, ErrorDefault>,
            RequestSpec.BodySpec<CreateInvoice, Invoice> {
    }

    interface ListInvoices extends RequestSpec<Invoices, ErrorDefault>,
        RequestSpec.FieldsSpec<ListInvoices>,
        RequestSpec.PaginationSpec<ListInvoices>{
    }

    interface SendInvoice extends RequestSpec<LinkDescription, ErrorDefault>,
            RequestSpec.IdSpec<SendInvoice>,
            RequestSpec.BodySpec<SendInvoice, Notification> {
    }

    interface RemindInvoice extends RequestSpec<Void, ErrorDefault>,
            RequestSpec.IdSpec<RemindInvoice>,
            RequestSpec.BodySpec<RemindInvoice, Notification> {
    }

    interface CancelInvoice extends RequestSpec<Void, ErrorDefault>,
            RequestSpec.IdSpec<CancelInvoice>,
            RequestSpec.BodySpec<CancelInvoice, Notification> {
    }

    interface PaymentsInvoices {
        CreatePayment create();
        DeletePayment delete();

        interface CreatePayment extends RequestSpec<PaymentReference, ErrorDefault>,
                RequestSpec.IdSpec<CreatePayment>,
                RequestSpec.BodySpec<CreatePayment, PaymentDetail> {
        }

        interface DeletePayment extends RequestSpec<Void, ErrorDefault>,
                RequestSpec.IdSpec<DeletePayment> {
                DeletePayment witTransactionId(String transactionId);
        }
    }

    interface RefundsInvoices {
        CreateRefund create();
        DeleteRefund delete();

        interface CreateRefund extends RequestSpec<RefundReference, ErrorDefault>,
                RequestSpec.IdSpec<CreateRefund>,
                RequestSpec.BodySpec<CreateRefund, RefundDetail> {
        }

        interface DeleteRefund extends RequestSpec<Void, ErrorDefault>,
                RequestSpec.IdSpec<DeleteRefund> {
                DeleteRefund witTransactionId(String transactionId);
        }
    }

    interface GenerateQRCode extends RequestSpec<QR, ErrorDefault>,
            RequestSpec.IdSpec<GenerateQRCode>,
            RequestSpec.BodySpec<GenerateQRCode, QrConfig> {
    }

    interface UpdateInvoice extends RequestSpec<Invoice, ErrorDefault>,
            RequestSpec.IdSpec<UpdateInvoice>,
            RequestSpec.BodySpec<UpdateInvoice, Invoice> {

        UpdateInvoice withSendToRecipient(boolean boo);
        UpdateInvoice withSendToInvoicer(boolean boo);
    }

    interface DeleteInvoice extends RequestSpec<Void, ErrorDefault>,
            RequestSpec.IdSpec<DeleteInvoice> {
    }

    interface GetInvoice extends RequestSpec<Invoice, ErrorDefault>,
            RequestSpec.IdSpec<GetInvoice> {
    }
}
