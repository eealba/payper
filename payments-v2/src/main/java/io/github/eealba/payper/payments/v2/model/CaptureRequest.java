package io.github.eealba.payper.payments.v2.model;


import io.github.eealba.jasoner.JasonerProperty;

/**
 * Captures either a portion or the full authorized amount of an authorized payment.
 */
public class CaptureRequest {


    @JasonerProperty("invoice_id")
    private final String invoiceId;
    @JasonerProperty("note_to_payer")
    private final String noteToPayer;
    
    private final Money amount;
    @JasonerProperty("final_capture")
    private final Boolean finalCapture;
    @JasonerProperty("payment_instruction")
    private final PaymentInstruction paymentInstruction;
    @JasonerProperty("soft_descriptor")
    private final String softDescriptor;

    private CaptureRequest(Builder builder) {
        invoiceId = builder.invoiceId;
        noteToPayer = builder.noteToPayer;
        amount = builder.amount;
        finalCapture = builder.finalCapture;
        paymentInstruction = builder.paymentInstruction;
        softDescriptor = builder.softDescriptor;

    }

    /**
     * The API caller-provided external invoice number for this order. Appears in both the payer's transaction 
history and the emails that the payer receives.
     */
    @JasonerProperty("invoice_id")
    public String invoiceId() {
        return invoiceId;
    }

    /**
     * An informational note about this settlement. Appears in both the payer's transaction history and the emails 
that the payer receives.
     */
    @JasonerProperty("note_to_payer")
    public String noteToPayer() {
        return noteToPayer;
    }

    /**
     * amount
     */
    
    public Money amount() {
        return amount;
    }

    /**
     * Indicates whether you can make additional captures against the authorized payment. Set to `true` if you do not 
intend to capture additional payments against the authorization. Set to `false` if you intend to capture 
additional payments against the authorization.
     */
    @JasonerProperty("final_capture")
    public Boolean finalCapture() {
        return finalCapture;
    }

    /**
     * paymentInstruction
     */
    @JasonerProperty("payment_instruction")
    public PaymentInstruction paymentInstruction() {
        return paymentInstruction;
    }

    /**
     * The payment descriptor on the payer's account statement.
     */
    @JasonerProperty("soft_descriptor")
    public String softDescriptor() {
        return softDescriptor;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String invoiceId;
        private String noteToPayer;
        private Money amount;
        private Boolean finalCapture;
        private PaymentInstruction paymentInstruction;
        private String softDescriptor;

        /**
         * The API caller-provided external invoice number for this order. Appears in both the payer's transaction 
history and the emails that the payer receives.
         */
        @JasonerProperty("invoice_id")
        public Builder invoiceId(String value) {
            this.invoiceId = value;
            return this;
        }

        /**
         * An informational note about this settlement. Appears in both the payer's transaction history and the emails 
that the payer receives.
         */
        @JasonerProperty("note_to_payer")
        public Builder noteToPayer(String value) {
            this.noteToPayer = value;
            return this;
        }

        /**
         * amount
         */
        
        public Builder amount(Money value) {
            this.amount = value;
            return this;
        }

        /**
         * Indicates whether you can make additional captures against the authorized payment. Set to `true` if you do not 
intend to capture additional payments against the authorization. Set to `false` if you intend to capture 
additional payments against the authorization.
         */
        @JasonerProperty("final_capture")
        public Builder finalCapture(Boolean value) {
            this.finalCapture = value;
            return this;
        }

        /**
         * paymentInstruction
         */
        @JasonerProperty("payment_instruction")
        public Builder paymentInstruction(PaymentInstruction value) {
            this.paymentInstruction = value;
            return this;
        }

        /**
         * The payment descriptor on the payer's account statement.
         */
        @JasonerProperty("soft_descriptor")
        public Builder softDescriptor(String value) {
            this.softDescriptor = value;
            return this;
        }

        public CaptureRequest build() {
            return new CaptureRequest(this);
        }

    }

}

