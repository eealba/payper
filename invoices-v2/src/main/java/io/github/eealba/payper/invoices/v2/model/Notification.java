package io.github.eealba.payper.invoices.v2.model;

import io.github.eealba.jasoner.JasonerProperty;

import java.util.List;

/**
 * The email or SMS notification to send to the invoicer or payer on sending an invoice.
 */
public class Notification {


    
    private final String subject;
    
    private final String note;
    @JasonerProperty("send_to_invoicer")
    private final Boolean sendToInvoicer;
    @JasonerProperty("send_to_recipient")
    private final Boolean sendToRecipient;
    @JasonerProperty("additional_recipients")
    private final List<Email> additionalRecipients;

    private Notification(Builder builder) {
        subject = builder.subject;
        note = builder.note;
        sendToInvoicer = builder.sendToInvoicer;
        sendToRecipient = builder.sendToRecipient;
        additionalRecipients = builder.additionalRecipients;

    }

    /**
     * The subject of the email that is sent as a notification to the recipient.
     */
    
    public String subject() {
        return subject;
    }

    /**
     * A note to the payer.
     */
    
    public String note() {
        return note;
    }

    /**
     * Indicates whether to send a copy of the email to the merchant.
     */
    @JasonerProperty("send_to_invoicer")
    public Boolean sendToInvoicer() {
        return sendToInvoicer;
    }

    /**
     * Indicates whether to send a copy of the email to the recipient.
     */
    @JasonerProperty("send_to_recipient")
    public Boolean sendToRecipient() {
        return sendToRecipient;
    }

    /**
     * An array of one or more CC: emails to which notifications are sent. If you omit this parameter, a notification 
is sent to all CC: email addresses that are part of the invoice.<blockquote><strong>Note:</strong> Valid 
values are email addresses in the `additional_recipients` value associated with the invoice.</blockquote>
     */
    @JasonerProperty("additional_recipients")
    public List<Email> additionalRecipients() {
        return additionalRecipients;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String subject;
        private String note;
        private Boolean sendToInvoicer;
        private Boolean sendToRecipient;
        private List<Email> additionalRecipients;

        /**
         * The subject of the email that is sent as a notification to the recipient.
         */
        
        public Builder subject(String value) {
            this.subject = value;
            return this;
        }

        /**
         * A note to the payer.
         */
        
        public Builder note(String value) {
            this.note = value;
            return this;
        }

        /**
         * Indicates whether to send a copy of the email to the merchant.
         */
        @JasonerProperty("send_to_invoicer")
        public Builder sendToInvoicer(Boolean value) {
            this.sendToInvoicer = value;
            return this;
        }

        /**
         * Indicates whether to send a copy of the email to the recipient.
         */
        @JasonerProperty("send_to_recipient")
        public Builder sendToRecipient(Boolean value) {
            this.sendToRecipient = value;
            return this;
        }

        /**
         * An array of one or more CC: emails to which notifications are sent. If you omit this parameter, a notification 
is sent to all CC: email addresses that are part of the invoice.<blockquote><strong>Note:</strong> Valid 
values are email addresses in the `additional_recipients` value associated with the invoice.</blockquote>
         */
        @JasonerProperty("additional_recipients")
        public Builder additionalRecipients(List<Email> value) {
            this.additionalRecipients = value;
            return this;
        }

        public Notification build() {
            return new Notification(this);
        }

    }

}

