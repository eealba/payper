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

import io.github.eealba.payper.core.client.RequestSpec;
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

/**
 * Interface for the Payper Invoices API v2.
 * <p>
 * This interface provides methods for creating, listing, retrieving, updating, and deleting invoices.
 * It also supports operations for sending, reminding, and canceling invoices, as well as handling payments and refunds.
 * </p>
 * @since 1.0
 * @version 1.0
 * @author Edgar Alba
 */
public interface InvoicesApi {
    /**
     * Creates a new invoice.
     * @return a {@link CreateInvoice} instance
     */
    CreateInvoice create();

    /**
     * Lists all invoices.
     * @return a {@link ListInvoices} instance
     */
    ListInvoices list();

    /**
     * Sends an invoice.
     * @return a {@link SendInvoice} instance
     */
    SendInvoice send();

    /**
     * Sends a reminder for an invoice.
     * @return a {@link RemindInvoice} instance
     */
    RemindInvoice remind();

    /**
     * Cancels an invoice.
     * @return a {@link CancelInvoice} instance
     */
    CancelInvoice cancel();

    /**
     * Provides access to payment-related operations for invoices.
     * @return a {@link PaymentsInvoices} instance
     */
    PaymentsInvoices payments();

    /**
     * Provides access to refund-related operations for invoices.
     * @return a {@link RefundsInvoices} instance
     */
    RefundsInvoices refunds();

    /**
     * Generates a QR code for an invoice.
     * @return a {@link GenerateQRCode} instance
     */
    GenerateQRCode generateQRCode();

    /**
     * Updates an existing invoice.
     * @return a {@link UpdateInvoice} instance
     */
    UpdateInvoice update();

    /**
     * Deletes an invoice.
     * @return a {@link DeleteInvoice} instance
     */
    DeleteInvoice delete();

    /**
     * Retrieves an invoice by its ID.
     * @return a {@link GetInvoice} instance
     */
    GetInvoice get();

    /**
     * Interface for creating an invoice.
     */
    interface CreateInvoice extends RequestSpec<Invoice, ErrorDefault>,
            RequestSpec.BodySpec<CreateInvoice, Invoice>,
            RequestSpec.PreferSpec<CreateInvoice> {
    }

    /**
     * Interface for listing invoices.
     */
    interface ListInvoices extends RequestSpec<Invoices, ErrorDefault>,
        RequestSpec.FieldsSpec<ListInvoices>,
        RequestSpec.PaginationSpec<ListInvoices>{
    }

    /**
     * Interface for sending an invoice.
     */
    interface SendInvoice extends RequestSpec<LinkDescription, ErrorDefault>,
            RequestSpec.IdSpec<SendInvoice>,
            RequestSpec.BodySpec<SendInvoice, Notification> {
    }

    /**
     * Interface for sending a reminder for an invoice.
     */
    interface RemindInvoice extends RequestSpec<Void, ErrorDefault>,
            RequestSpec.IdSpec<RemindInvoice>,
            RequestSpec.BodySpec<RemindInvoice, Notification> {
    }

    /**
     * Interface for canceling an invoice.
     */
    interface CancelInvoice extends RequestSpec<Void, ErrorDefault>,
            RequestSpec.IdSpec<CancelInvoice>,
            RequestSpec.BodySpec<CancelInvoice, Notification> {
    }

    /**
     * Interface for payment-related operations for invoices.
     */
    interface PaymentsInvoices {
        /**
         * Creates a new payment.
         * @return a {@link CreatePayment} instance
         */
        CreatePayment create();

        /**
         * Deletes a payment.
         * @return a {@link DeletePayment} instance
         */
        DeletePayment delete();

        /**
         * Interface for creating a payment.
         */
        interface CreatePayment extends RequestSpec<PaymentReference, ErrorDefault>,
                RequestSpec.IdSpec<CreatePayment>,
                RequestSpec.BodySpec<CreatePayment, PaymentDetail> {
        }

        /**
         * Interface for deleting a payment.
         */
        interface DeletePayment extends RequestSpec<Void, ErrorDefault>,
                RequestSpec.IdSpec<DeletePayment> {
                /**
                 * Sets the transaction ID for the payment to be deleted.
                 * @param transactionId the transaction ID
                 * @return the {@link DeletePayment} instance
                 */
                DeletePayment withTransactionId(String transactionId);
        }
    }

    /**
     * Interface for refund-related operations for invoices.
     */
    interface RefundsInvoices {
        /**
         * Creates a new refund.
         * @return a {@link CreateRefund} instance
         */
        CreateRefund create();

        /**
         * Deletes a refund.
         * @return a {@link DeleteRefund} instance
         */
        DeleteRefund delete();

        /**
         * Interface for creating a refund.
         */
        interface CreateRefund extends RequestSpec<RefundReference, ErrorDefault>,
                RequestSpec.IdSpec<CreateRefund>,
                RequestSpec.BodySpec<CreateRefund, RefundDetail> {
        }

        /**
         * Interface for deleting a refund.
         */
        interface DeleteRefund extends RequestSpec<Void, ErrorDefault>,
                RequestSpec.IdSpec<DeleteRefund> {
                /**
                 * Sets the transaction ID for the refund to be deleted.
                 * @param transactionId the transaction ID
                 * @return the {@link DeleteRefund} instance
                 */
                DeleteRefund withTransactionId(String transactionId);
        }
    }

    /**
     * Interface for generating a QR code for an invoice.
     */
    interface GenerateQRCode extends RequestSpec<QR, ErrorDefault>,
            RequestSpec.IdSpec<GenerateQRCode>,
            RequestSpec.BodySpec<GenerateQRCode, QrConfig> {
    }

    /**
     * Interface for updating an invoice.
     */
    interface UpdateInvoice extends RequestSpec<Invoice, ErrorDefault>,
            RequestSpec.IdSpec<UpdateInvoice>,
            RequestSpec.BodySpec<UpdateInvoice, Invoice> {

        /**
         * Sets whether to send the updated invoice to the recipient.
         * @param boo true to send to recipient, false otherwise
         * @return the {@link UpdateInvoice} instance
         */
        UpdateInvoice withSendToRecipient(boolean boo);

        /**
         * Sets whether to send the updated invoice to the invoicer.
         * @param boo true to send to invoicer, false otherwise
         * @return the {@link UpdateInvoice} instance
         */
        UpdateInvoice withSendToInvoicer(boolean boo);
    }

    /**
     * Interface for deleting an invoice.
     */
    interface DeleteInvoice extends RequestSpec<Void, ErrorDefault>,
            RequestSpec.IdSpec<DeleteInvoice> {
    }

    /**
     * Interface for retrieving an invoice by its ID.
     */
    interface GetInvoice extends RequestSpec<Invoice, ErrorDefault>,
            RequestSpec.IdSpec<GetInvoice> {
    }
}