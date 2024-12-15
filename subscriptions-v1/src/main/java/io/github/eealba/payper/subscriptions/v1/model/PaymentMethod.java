package io.github.eealba.payper.subscriptions.v1.model;


import io.github.eealba.jasoner.JasonerProperty;

/**
 * The customer and merchant payment preferences.
 */
public class PaymentMethod {


    @JasonerProperty("payer_selected")
    private final String payerSelected;
    @JasonerProperty("payee_preferred")
    private final PayeePaymentMethodPreference payeePreferred;
    @JasonerProperty("standard_entry_class_code")
    private final StandardEntryClassCode standardEntryClassCode;

    private PaymentMethod(Builder builder) {
        payerSelected = builder.payerSelected;
        payeePreferred = builder.payeePreferred;
        standardEntryClassCode = builder.standardEntryClassCode;

    }

    /**
     * The customer-selected payment method on the merchant site.
     */
    @JasonerProperty("payer_selected")
    public String payerSelected() {
        return payerSelected;
    }

    /**
     * payeePreferred
     */
    @JasonerProperty("payee_preferred")
    public PayeePaymentMethodPreference payeePreferred() {
        return payeePreferred;
    }

    /**
     * NACHA (the regulatory body governing the ACH network) requires that API callers (merchants, partners) obtain 
the consumer’s explicit authorization before initiating a transaction. To stay compliant, you’ll need to make 
sure that you retain a compliant authorization for each transaction that you originate to the ACH Network 
using this API. ACH transactions are categorized (using SEC codes) by how you capture authorization from the 
Receiver (the person whose bank account is being debited or credited). PayPal supports the following SEC 
codes.
     */
    @JasonerProperty("standard_entry_class_code")
    public StandardEntryClassCode standardEntryClassCode() {
        return standardEntryClassCode;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String payerSelected;
        private PayeePaymentMethodPreference payeePreferred;
        private StandardEntryClassCode standardEntryClassCode;

        /**
         * The customer-selected payment method on the merchant site.
         */
        @JasonerProperty("payer_selected")
        public Builder payerSelected(String value) {
            this.payerSelected = value;
            return this;
        }

        /**
         * payeePreferred
         */
        @JasonerProperty("payee_preferred")
        public Builder payeePreferred(PayeePaymentMethodPreference value) {
            this.payeePreferred = value;
            return this;
        }

        /**
         * NACHA (the regulatory body governing the ACH network) requires that API callers (merchants, partners) obtain 
the consumer’s explicit authorization before initiating a transaction. To stay compliant, you’ll need to make 
sure that you retain a compliant authorization for each transaction that you originate to the ACH Network 
using this API. ACH transactions are categorized (using SEC codes) by how you capture authorization from the 
Receiver (the person whose bank account is being debited or credited). PayPal supports the following SEC 
codes.
         */
        @JasonerProperty("standard_entry_class_code")
        public Builder standardEntryClassCode(StandardEntryClassCode value) {
            this.standardEntryClassCode = value;
            return this;
        }

        public PaymentMethod build() {
            return new PaymentMethod(this);
        }

    }
    /**
     * NACHA (the regulatory body governing the ACH network) requires that API callers (merchants, partners) obtain the consumer’s explicit authorization before initiating a transaction. To stay compliant, you’ll need to make sure that you retain a compliant authorization for each transaction that you originate to the ACH Network using this API. ACH transactions are categorized (using SEC codes) by how you capture authorization from the Receiver (the person whose bank account is being debited or credited). PayPal supports the following SEC codes.
     */
    public enum StandardEntryClassCode {
        TEL,
        WEB,
        CCD,
        PPD
    }
}

