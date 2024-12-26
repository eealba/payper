package io.github.eealba.payper.orders.v2.model;

import io.github.eealba.jasoner.JasonerProperty;

import java.time.Instant;
import java.util.List;


/**
 * A captured payment.
 */
public class Capture {


    
    private final Status status;
    @JasonerProperty("status_details")
    private final CaptureStatusDetails statusDetails;
    
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
    @JasonerProperty("final_capture")
    private final Boolean finalCapture;
    @JasonerProperty("seller_receivable_breakdown")
    private final SellerReceivableBreakdown sellerReceivableBreakdown;
    @JasonerProperty("disbursement_mode")
    private final DisbursementMode disbursementMode;
    
    private final List<LinkDescription> links;
    @JasonerProperty("processor_response")
    private final ProcessorResponse processorResponse;
    @JasonerProperty("create_time")
    private final Instant createTime;
    @JasonerProperty("update_time")
    private final Instant updateTime;

    private Capture(Builder builder) {
        status = builder.status;
        statusDetails = builder.statusDetails;
        id = builder.id;
        amount = builder.amount;
        invoiceId = builder.invoiceId;
        customId = builder.customId;
        networkTransactionReference = builder.networkTransactionReference;
        sellerProtection = builder.sellerProtection;
        finalCapture = builder.finalCapture;
        sellerReceivableBreakdown = builder.sellerReceivableBreakdown;
        disbursementMode = builder.disbursementMode;
        links = builder.links;
        processorResponse = builder.processorResponse;
        createTime = builder.createTime;
        updateTime = builder.updateTime;

    }

    /**
     * The status of the captured payment.
     */
    
    public Status status() {
        return status;
    }

    /**
     * statusDetails
     */
    @JasonerProperty("status_details")
    public CaptureStatusDetails statusDetails() {
        return statusDetails;
    }

    /**
     * The PayPal-generated ID for the captured payment.
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
     * Indicates whether you can make additional captures against the authorized payment. Set to `true` if you do not 
intend to capture additional payments against the authorization. Set to `false` if you intend to capture 
additional payments against the authorization.
     */
    @JasonerProperty("final_capture")
    public Boolean finalCapture() {
        return finalCapture;
    }

    /**
     * sellerReceivableBreakdown
     */
    @JasonerProperty("seller_receivable_breakdown")
    public SellerReceivableBreakdown sellerReceivableBreakdown() {
        return sellerReceivableBreakdown;
    }

    /**
     * disbursementMode
     */
    @JasonerProperty("disbursement_mode")
    public DisbursementMode disbursementMode() {
        return disbursementMode;
    }

    /**
     * An array of related [HATEOAS links](/docs/api/reference/api-responses/#hateoas-links).
     */
    
    public List<LinkDescription> links() {
        return links;
    }

    /**
     * processorResponse
     */
    @JasonerProperty("processor_response")
    public ProcessorResponse processorResponse() {
        return processorResponse;
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

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Status status;
        private CaptureStatusDetails statusDetails;
        private String id;
        private Money amount;
        private String invoiceId;
        private String customId;
        private NetworkTransactionReference networkTransactionReference;
        private SellerProtection sellerProtection;
        private Boolean finalCapture;
        private SellerReceivableBreakdown sellerReceivableBreakdown;
        private DisbursementMode disbursementMode;
        private List<LinkDescription> links;
        private ProcessorResponse processorResponse;
        private Instant createTime;
        private Instant updateTime;

        /**
         * The status of the captured payment.
         */
        
        public Builder status(Status value) {
            this.status = value;
            return this;
        }

        /**
         * statusDetails
         */
        @JasonerProperty("status_details")
        public Builder statusDetails(CaptureStatusDetails value) {
            this.statusDetails = value;
            return this;
        }

        /**
         * The PayPal-generated ID for the captured payment.
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
         * sellerReceivableBreakdown
         */
        @JasonerProperty("seller_receivable_breakdown")
        public Builder sellerReceivableBreakdown(SellerReceivableBreakdown value) {
            this.sellerReceivableBreakdown = value;
            return this;
        }

        /**
         * disbursementMode
         */
        @JasonerProperty("disbursement_mode")
        public Builder disbursementMode(DisbursementMode value) {
            this.disbursementMode = value;
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
         * processorResponse
         */
        @JasonerProperty("processor_response")
        public Builder processorResponse(ProcessorResponse value) {
            this.processorResponse = value;
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

        public Capture build() {
            return new Capture(this);
        }

    }
    /**
     * The status of the captured payment.
     */
    public enum Status {
        COMPLETED,
        DECLINED,
        PARTIALLY_REFUNDED,
        PENDING,
        REFUNDED,
        FAILED
    }

}

