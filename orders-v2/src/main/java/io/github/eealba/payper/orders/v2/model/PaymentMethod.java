package io.github.eealba.payper.orders.v2.model;


import io.github.eealba.jasoner.JasonerProperty;

/**
 * The customer and merchant payment preferences.
 */
public class PaymentMethod {


    @JasonerProperty("payee_preferred")
    private final PayeePaymentMethodPreference payeePreferred;
    @JasonerProperty("standard_entry_class_code")
    private final StandardEntryClassCode standardEntryClassCode;

    private PaymentMethod(Builder builder) {
        payeePreferred = builder.payeePreferred;
        standardEntryClassCode = builder.standardEntryClassCode;

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

        private PayeePaymentMethodPreference payeePreferred;
        private StandardEntryClassCode standardEntryClassCode;

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

