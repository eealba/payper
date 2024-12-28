package io.github.eealba.payper.orders.v2.model;

import io.github.eealba.jasoner.JasonerProperty;

import java.util.List;

/**
 * The level of protection offered as defined by [PayPal Seller Protection for 
Merchants](https://www.paypal.com/us/webapps/mpp/security/seller-protection).
 */
public class SellerProtection {


    
    private final Status status;
    @JasonerProperty("dispute_categories")
    private final List<String> disputeCategories;

    private SellerProtection(Builder builder) {
        status = builder.status;
        disputeCategories = builder.disputeCategories;

    }

    /**
     * Indicates whether the transaction is eligible for seller protection. For information, see [PayPal Seller 
Protection for Merchants](https://www.paypal.com/us/webapps/mpp/security/seller-protection).
     */
    
    public Status status() {
        return status;
    }

    /**
     * An array of conditions that are covered for the transaction.
     */
    @JasonerProperty("dispute_categories")
    public List<String> disputeCategories() {
        return disputeCategories;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Status status;
        private List<String> disputeCategories;

        /**
         * Indicates whether the transaction is eligible for seller protection. For information, see [PayPal Seller 
Protection for Merchants](https://www.paypal.com/us/webapps/mpp/security/seller-protection).
         */
        
        public Builder status(Status value) {
            this.status = value;
            return this;
        }

        /**
         * An array of conditions that are covered for the transaction.
         */
        @JasonerProperty("dispute_categories")
        public Builder disputeCategories(List<String> value) {
            this.disputeCategories = value;
            return this;
        }

        public SellerProtection build() {
            return new SellerProtection(this);
        }

    }
    /**
     * Indicates whether the transaction is eligible for seller protection. For information, see [PayPal Seller Protection for Merchants](https://www.paypal.com/us/webapps/mpp/security/seller-protection).
     */
    public enum Status {
        ELIGIBLE,
        PARTIALLY_ELIGIBLE,
        NOT_ELIGIBLE
    }
}

