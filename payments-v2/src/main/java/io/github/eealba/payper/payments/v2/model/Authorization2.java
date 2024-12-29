package io.github.eealba.payper.payments.v2.model;

import io.github.eealba.jasoner.JasonerProperty;

import java.time.Instant;
import java.util.List;

/**
 * The authorized payment transaction.
 */
public class Authorization2 {


    
    private final Status status;
    @JasonerProperty("status_details")
    private final AuthorizationStatusDetails statusDetails;
    
    private final String id;
    
    private final Money amount;
    @JasonerProperty("invoice_id")
    private final String invoiceId;
    @JasonerProperty("custom_id")
    private final String customId;
    @JasonerProperty("network_transaction_reference")
    private final NetworkTransactionReference networkTransactionReference;
    @JasonerProperty("seller_protection")
    private final SellerProtection sellerProtection;
    @JasonerProperty("expiration_time")
    private final Instant expirationTime;
    
    private final List<LinkDescription> links;
    @JasonerProperty("create_time")
    private final Instant createTime;
    @JasonerProperty("update_time")
    private final Instant updateTime;
    @JasonerProperty("supplementary_data")
    private final SupplementaryData supplementaryData;
    
    private final Payee payee;

    private Authorization2(Builder builder) {
        status = builder.status;
        statusDetails = builder.statusDetails;
        id = builder.id;
        amount = builder.amount;
        invoiceId = builder.invoiceId;
        customId = builder.customId;
        networkTransactionReference = builder.networkTransactionReference;
        sellerProtection = builder.sellerProtection;
        expirationTime = builder.expirationTime;
        links = builder.links;
        createTime = builder.createTime;
        updateTime = builder.updateTime;
        supplementaryData = builder.supplementaryData;
        payee = builder.payee;

    }

    /**
     * The status for the authorized payment.
     */
    
    public Status status() {
        return status;
    }

    /**
     * statusDetails
     */
    @JasonerProperty("status_details")
    public AuthorizationStatusDetails statusDetails() {
        return statusDetails;
    }

    /**
     * The PayPal-generated ID for the authorized payment.
     */
    
    public String id() {
        return id;
    }

    /**
     * amount
     */
    
    public Money amount() {
        return amount;
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
     * The API caller-provided external ID. Used to reconcile API caller-initiated transactions with PayPal 
transactions. Appears in transaction and settlement reports.
     */
    @JasonerProperty("custom_id")
    public String customId() {
        return customId;
    }

    /**
     * networkTransactionReference
     */
    @JasonerProperty("network_transaction_reference")
    public NetworkTransactionReference networkTransactionReference() {
        return networkTransactionReference;
    }

    /**
     * sellerProtection
     */
    @JasonerProperty("seller_protection")
    public SellerProtection sellerProtection() {
        return sellerProtection;
    }

    /**
     * expirationTime
     */
    @JasonerProperty("expiration_time")
    public Instant expirationTime() {
        return expirationTime;
    }

    /**
     * An array of related [HATEOAS links](/docs/api/reference/api-responses/#hateoas-links).
     */
    
    public List<LinkDescription> links() {
        return links;
    }

    /**
     * createTime
     */
    @JasonerProperty("create_time")
    public Instant createTime() {
        return createTime;
    }

    /**
     * updateTime
     */
    @JasonerProperty("update_time")
    public Instant updateTime() {
        return updateTime;
    }

    /**
     * supplementaryData
     */
    @JasonerProperty("supplementary_data")
    public SupplementaryData supplementaryData() {
        return supplementaryData;
    }

    /**
     * payee
     */
    
    public Payee payee() {
        return payee;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Status status;
        private AuthorizationStatusDetails statusDetails;
        private String id;
        private Money amount;
        private String invoiceId;
        private String customId;
        private NetworkTransactionReference networkTransactionReference;
        private SellerProtection sellerProtection;
        private Instant expirationTime;
        private List<LinkDescription> links;
        private Instant createTime;
        private Instant updateTime;
        private SupplementaryData supplementaryData;
        private Payee payee;

        /**
         * The status for the authorized payment.
         */
        
        public Builder status(Status value) {
            this.status = value;
            return this;
        }

        /**
         * statusDetails
         */
        @JasonerProperty("status_details")
        public Builder statusDetails(AuthorizationStatusDetails value) {
            this.statusDetails = value;
            return this;
        }

        /**
         * The PayPal-generated ID for the authorized payment.
         */
        
        public Builder id(String value) {
            this.id = value;
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
         * The API caller-provided external invoice number for this order. Appears in both the payer's transaction 
history and the emails that the payer receives.
         */
        @JasonerProperty("invoice_id")
        public Builder invoiceId(String value) {
            this.invoiceId = value;
            return this;
        }

        /**
         * The API caller-provided external ID. Used to reconcile API caller-initiated transactions with PayPal 
transactions. Appears in transaction and settlement reports.
         */
        @JasonerProperty("custom_id")
        public Builder customId(String value) {
            this.customId = value;
            return this;
        }

        /**
         * networkTransactionReference
         */
        @JasonerProperty("network_transaction_reference")
        public Builder networkTransactionReference(NetworkTransactionReference value) {
            this.networkTransactionReference = value;
            return this;
        }

        /**
         * sellerProtection
         */
        @JasonerProperty("seller_protection")
        public Builder sellerProtection(SellerProtection value) {
            this.sellerProtection = value;
            return this;
        }

        /**
         * expirationTime
         */
        @JasonerProperty("expiration_time")
        public Builder expirationTime(Instant value) {
            this.expirationTime = value;
            return this;
        }

        /**
         * An array of related [HATEOAS links](/docs/api/reference/api-responses/#hateoas-links).
         */
        
        public Builder links(List<LinkDescription> value) {
            this.links = value;
            return this;
        }

        /**
         * createTime
         */
        @JasonerProperty("create_time")
        public Builder createTime(Instant value) {
            this.createTime = value;
            return this;
        }

        /**
         * updateTime
         */
        @JasonerProperty("update_time")
        public Builder updateTime(Instant value) {
            this.updateTime = value;
            return this;
        }

        /**
         * supplementaryData
         */
        @JasonerProperty("supplementary_data")
        public Builder supplementaryData(SupplementaryData value) {
            this.supplementaryData = value;
            return this;
        }

        /**
         * payee
         */
        
        public Builder payee(Payee value) {
            this.payee = value;
            return this;
        }

        public Authorization2 build() {
            return new Authorization2(this);
        }

    }
    /**
     * The status for the authorized payment.
     */
    public enum Status {
        CREATED,
        CAPTURED,
        DENIED,
        PARTIALLY_CAPTURED,
        VOIDED,
        PENDING
    }
}

