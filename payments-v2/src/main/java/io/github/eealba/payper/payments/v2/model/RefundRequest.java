package io.github.eealba.payper.payments.v2.model;


import io.github.eealba.jasoner.JasonerProperty;

/**
 * Refunds a captured payment, by ID. For a full refund, include an empty request body. For a partial refund, include an 
<code>amount</code> object in the request body.
 */
public class RefundRequest {


    
    private final Money amount;
    @JasonerProperty("custom_id")
    private final String customId;
    @JasonerProperty("invoice_id")
    private final String invoiceId;
    @JasonerProperty("note_to_payer")
    private final String noteToPayer;
    @JasonerProperty("payment_instruction")
    private final PaymentInstruction2 paymentInstruction;

    private RefundRequest(Builder builder) {
        amount = builder.amount;
        customId = builder.customId;
        invoiceId = builder.invoiceId;
        noteToPayer = builder.noteToPayer;
        paymentInstruction = builder.paymentInstruction;

    }

    /**
     * amount
     */
    
    public Money amount() {
        return amount;
    }

    /**
     * The API caller-provided external ID. Used to reconcile API caller-initiated transactions with PayPal 
transactions. Appears in transaction and settlement reports. The pattern is defined by an external party and 
supports Unicode.
     */
    @JasonerProperty("custom_id")
    public String customId() {
        return customId;
    }

    /**
     * The API caller-provided external invoice ID for this order. The pattern is defined by an external party and 
supports Unicode.
     */
    @JasonerProperty("invoice_id")
    public String invoiceId() {
        return invoiceId;
    }

    /**
     * The reason for the refund. Appears in both the payer's transaction history and the emails that the payer 
receives. The pattern is defined by an external party and supports Unicode.
     */
    @JasonerProperty("note_to_payer")
    public String noteToPayer() {
        return noteToPayer;
    }

    /**
     * paymentInstruction
     */
    @JasonerProperty("payment_instruction")
    public PaymentInstruction2 paymentInstruction() {
        return paymentInstruction;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Money amount;
        private String customId;
        private String invoiceId;
        private String noteToPayer;
        private PaymentInstruction2 paymentInstruction;

        /**
         * amount
         */
        
        public Builder amount(Money value) {
            this.amount = value;
            return this;
        }

        /**
         * The API caller-provided external ID. Used to reconcile API caller-initiated transactions with PayPal 
transactions. Appears in transaction and settlement reports. The pattern is defined by an external party and 
supports Unicode.
         */
        @JasonerProperty("custom_id")
        public Builder customId(String value) {
            this.customId = value;
            return this;
        }

        /**
         * The API caller-provided external invoice ID for this order. The pattern is defined by an external party and 
supports Unicode.
         */
        @JasonerProperty("invoice_id")
        public Builder invoiceId(String value) {
            this.invoiceId = value;
            return this;
        }

        /**
         * The reason for the refund. Appears in both the payer's transaction history and the emails that the payer 
receives. The pattern is defined by an external party and supports Unicode.
         */
        @JasonerProperty("note_to_payer")
        public Builder noteToPayer(String value) {
            this.noteToPayer = value;
            return this;
        }

        /**
         * paymentInstruction
         */
        @JasonerProperty("payment_instruction")
        public Builder paymentInstruction(PaymentInstruction2 value) {
            this.paymentInstruction = value;
            return this;
        }

        public RefundRequest build() {
            return new RefundRequest(this);
        }

    }

}

