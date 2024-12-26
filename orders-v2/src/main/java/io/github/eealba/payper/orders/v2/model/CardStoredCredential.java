package io.github.eealba.payper.orders.v2.model;


import io.github.eealba.jasoner.JasonerProperty;

import java.util.Objects;


/**
 * Provides additional details to process a payment using a `card` that has been stored or is intended to be stored (also 
referred to as stored_credential or card-on-file).<br/>Parameter compatibility:<br/><ul><li>`payment_type=ONE_TIME` is 
compatible only with `payment_initiator=CUSTOMER`.</li><li>`usage=FIRST` is compatible only with 
`payment_initiator=CUSTOMER`.</li><li>`previous_transaction_reference` or `previous_network_transaction_reference` is 
compatible only with `payment_initiator=MERCHANT`.</li><li>Only one of the parameters - `previous_transaction_reference` 
and `previous_network_transaction_reference` - can be present in the request.</li></ul>
 */
public class CardStoredCredential {


    @JasonerProperty("payment_initiator")
    private final PaymentInitiator paymentInitiator;
    @JasonerProperty("payment_type")
    private final StoredPaymentSourcePaymentType paymentType;
    
    private final StoredPaymentSourceUsageType usage;
    @JasonerProperty("previous_network_transaction_reference")
    private final NetworkTransactionReference previousNetworkTransactionReference;

    private CardStoredCredential(Builder builder) {
        usage = builder.usage;
        previousNetworkTransactionReference = builder.previousNetworkTransactionReference;
        paymentInitiator = Objects.requireNonNull(builder.paymentInitiator);
        paymentType = Objects.requireNonNull(builder.paymentType);
    }

    /**
     * paymentInitiator
     */
    @JasonerProperty("payment_initiator")
    public PaymentInitiator paymentInitiator() {
        return paymentInitiator;
    }

    /**
     * paymentType
     */
    @JasonerProperty("payment_type")
    public StoredPaymentSourcePaymentType paymentType() {
        return paymentType;
    }

    /**
     * usage
     */
    
    public StoredPaymentSourceUsageType usage() {
        return usage;
    }

    /**
     * previousNetworkTransactionReference
     */
    @JasonerProperty("previous_network_transaction_reference")
    public NetworkTransactionReference previousNetworkTransactionReference() {
        return previousNetworkTransactionReference;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private PaymentInitiator paymentInitiator;
        private StoredPaymentSourcePaymentType paymentType;
        private StoredPaymentSourceUsageType usage;
        private NetworkTransactionReference previousNetworkTransactionReference;

        /**
         * paymentInitiator
         */
        @JasonerProperty("payment_initiator")
        public Builder paymentInitiator(PaymentInitiator value) {
            this.paymentInitiator = value;
            return this;
        }

        /**
         * paymentType
         */
        @JasonerProperty("payment_type")
        public Builder paymentType(StoredPaymentSourcePaymentType value) {
            this.paymentType = value;
            return this;
        }

        /**
         * usage
         */
        
        public Builder usage(StoredPaymentSourceUsageType value) {
            this.usage = value;
            return this;
        }

        /**
         * previousNetworkTransactionReference
         */
        @JasonerProperty("previous_network_transaction_reference")
        public Builder previousNetworkTransactionReference(NetworkTransactionReference value) {
            this.previousNetworkTransactionReference = value;
            return this;
        }

        public CardStoredCredential build() {
            return new CardStoredCredential(this);
        }

    }


}

