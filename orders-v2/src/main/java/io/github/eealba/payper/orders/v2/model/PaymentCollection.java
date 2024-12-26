package io.github.eealba.payper.orders.v2.model;

import java.util.List;

/**
 * The collection of payments, or transactions, for a purchase unit in an order. For example, authorized payments, captured 
payments, and refunds.
 */
public class PaymentCollection {


    
    private final List<AuthorizationWithAdditionalData> authorizations;
    
    private final List<Capture> captures;
    
    private final List<Refund> refunds;

    private PaymentCollection(Builder builder) {
        authorizations = builder.authorizations;
        captures = builder.captures;
        refunds = builder.refunds;

    }

    /**
     * An array of authorized payments for a purchase unit. A purchase unit can have zero or more authorized 
payments.
     */
    
    public List<AuthorizationWithAdditionalData> authorizations() {
        return authorizations;
    }

    /**
     * An array of captured payments for a purchase unit. A purchase unit can have zero or more captured payments.
     */
    
    public List<Capture> captures() {
        return captures;
    }

    /**
     * An array of refunds for a purchase unit. A purchase unit can have zero or more refunds.
     */
    
    public List<Refund> refunds() {
        return refunds;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private List<AuthorizationWithAdditionalData> authorizations;
        private List<Capture> captures;
        private List<Refund> refunds;

        /**
         * An array of authorized payments for a purchase unit. A purchase unit can have zero or more authorized 
payments.
         */
        
        public Builder authorizations(List<AuthorizationWithAdditionalData> value) {
            this.authorizations = value;
            return this;
        }

        /**
         * An array of captured payments for a purchase unit. A purchase unit can have zero or more captured payments.
         */
        
        public Builder captures(List<Capture> value) {
            this.captures = value;
            return this;
        }

        /**
         * An array of refunds for a purchase unit. A purchase unit can have zero or more refunds.
         */
        
        public Builder refunds(List<Refund> value) {
            this.refunds = value;
            return this;
        }

        public PaymentCollection build() {
            return new PaymentCollection(this);
        }

    }


}

