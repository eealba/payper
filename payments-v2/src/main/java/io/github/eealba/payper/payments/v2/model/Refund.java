package io.github.eealba.payper.payments.v2.model;

import io.github.eealba.jasoner.JasonerProperty;

import java.time.Instant;
import java.util.List;

/**
 * The refund information.
 */
public class Refund {


    
    private final Status status;
    @JasonerProperty("status_details")
    private final RefundStatusDetails statusDetails;
    
    private final String id;
    
    private final Money amount;
    @JasonerProperty("invoice_id")
    private final String invoiceId;
    @JasonerProperty("custom_id")
    private final String customId;
    @JasonerProperty("acquirer_reference_number")
    private final String acquirerReferenceNumber;
    @JasonerProperty("note_to_payer")
    private final String noteToPayer;
    @JasonerProperty("seller_payable_breakdown")
    private final SellerPayableBreakdown sellerPayableBreakdown;
    
    private final Payee payer;
    
    private final List<LinkDescription> links;
    @JasonerProperty("create_time")
    private final Instant createTime;
    @JasonerProperty("update_time")
    private final Instant updateTime;

    private Refund(Builder builder) {
        status = builder.status;
        statusDetails = builder.statusDetails;
        id = builder.id;
        amount = builder.amount;
        invoiceId = builder.invoiceId;
        customId = builder.customId;
        acquirerReferenceNumber = builder.acquirerReferenceNumber;
        noteToPayer = builder.noteToPayer;
        sellerPayableBreakdown = builder.sellerPayableBreakdown;
        payer = builder.payer;
        links = builder.links;
        createTime = builder.createTime;
        updateTime = builder.updateTime;

    }

    /**
     * The status of the refund.
     */
    
    public Status status() {
        return status;
    }

    /**
     * statusDetails
     */
    @JasonerProperty("status_details")
    public RefundStatusDetails statusDetails() {
        return statusDetails;
    }

    /**
     * The PayPal-generated ID for the refund.
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
     * Reference ID issued for the card transaction. This ID can be used to track the transaction across processors, 
card brands and issuing banks.
     */
    @JasonerProperty("acquirer_reference_number")
    public String acquirerReferenceNumber() {
        return acquirerReferenceNumber;
    }

    /**
     * The reason for the refund. Appears in both the payer's transaction history and the emails that the payer 
receives.
     */
    @JasonerProperty("note_to_payer")
    public String noteToPayer() {
        return noteToPayer;
    }

    /**
     * The breakdown of the refund.
     */
    @JasonerProperty("seller_payable_breakdown")
    public SellerPayableBreakdown sellerPayableBreakdown() {
        return sellerPayableBreakdown;
    }

    /**
     * payer
     */
    
    public Payee payer() {
        return payer;
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

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Status status;
        private RefundStatusDetails statusDetails;
        private String id;
        private Money amount;
        private String invoiceId;
        private String customId;
        private String acquirerReferenceNumber;
        private String noteToPayer;
        private SellerPayableBreakdown sellerPayableBreakdown;
        private Payee payer;
        private List<LinkDescription> links;
        private Instant createTime;
        private Instant updateTime;

        /**
         * The status of the refund.
         */
        
        public Builder status(Status value) {
            this.status = value;
            return this;
        }

        /**
         * statusDetails
         */
        @JasonerProperty("status_details")
        public Builder statusDetails(RefundStatusDetails value) {
            this.statusDetails = value;
            return this;
        }

        /**
         * The PayPal-generated ID for the refund.
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
         * Reference ID issued for the card transaction. This ID can be used to track the transaction across processors, 
card brands and issuing banks.
         */
        @JasonerProperty("acquirer_reference_number")
        public Builder acquirerReferenceNumber(String value) {
            this.acquirerReferenceNumber = value;
            return this;
        }

        /**
         * The reason for the refund. Appears in both the payer's transaction history and the emails that the payer 
receives.
         */
        @JasonerProperty("note_to_payer")
        public Builder noteToPayer(String value) {
            this.noteToPayer = value;
            return this;
        }

        /**
         * The breakdown of the refund.
         */
        @JasonerProperty("seller_payable_breakdown")
        public Builder sellerPayableBreakdown(SellerPayableBreakdown value) {
            this.sellerPayableBreakdown = value;
            return this;
        }

        /**
         * payer
         */
        
        public Builder payer(Payee value) {
            this.payer = value;
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

        public Refund build() {
            return new Refund(this);
        }

    }
    /**
     * The status of the refund.
     */
    public enum Status {
        CANCELLED,
        FAILED,
        PENDING,
        COMPLETED
    }
public static class SellerPayableBreakdown {


    @JasonerProperty("gross_amount")
    private final Money grossAmount;
    @JasonerProperty("paypal_fee")
    private final Money paypalFee;
    @JasonerProperty("paypal_fee_in_receivable_currency")
    private final Money paypalFeeInReceivableCurrency;
    @JasonerProperty("net_amount")
    private final Money netAmount;
    @JasonerProperty("net_amount_in_receivable_currency")
    private final Money netAmountInReceivableCurrency;
    @JasonerProperty("platform_fees")
    private final List<PlatformFee> platformFees;
    @JasonerProperty("net_amount_breakdown")
    private final List<NetAmountBreakdownItem> netAmountBreakdown;
    @JasonerProperty("total_refunded_amount")
    private final Money totalRefundedAmount;

    private SellerPayableBreakdown(Builder builder) {
        grossAmount = builder.grossAmount;
        paypalFee = builder.paypalFee;
        paypalFeeInReceivableCurrency = builder.paypalFeeInReceivableCurrency;
        netAmount = builder.netAmount;
        netAmountInReceivableCurrency = builder.netAmountInReceivableCurrency;
        platformFees = builder.platformFees;
        netAmountBreakdown = builder.netAmountBreakdown;
        totalRefundedAmount = builder.totalRefundedAmount;

    }

    /**
     * grossAmount
     */
    @JasonerProperty("gross_amount")
    public Money grossAmount() {
        return grossAmount;
    }

    /**
     * paypalFee
     */
    @JasonerProperty("paypal_fee")
    public Money paypalFee() {
        return paypalFee;
    }

    /**
     * paypalFeeInReceivableCurrency
     */
    @JasonerProperty("paypal_fee_in_receivable_currency")
    public Money paypalFeeInReceivableCurrency() {
        return paypalFeeInReceivableCurrency;
    }

    /**
     * netAmount
     */
    @JasonerProperty("net_amount")
    public Money netAmount() {
        return netAmount;
    }

    /**
     * netAmountInReceivableCurrency
     */
    @JasonerProperty("net_amount_in_receivable_currency")
    public Money netAmountInReceivableCurrency() {
        return netAmountInReceivableCurrency;
    }

    /**
     * An array of platform or partner fees, commissions, or brokerage fees for the refund.
     */
    @JasonerProperty("platform_fees")
    public List<PlatformFee> platformFees() {
        return platformFees;
    }

    /**
     * An array of breakdown values for the net amount. Returned when the currency of the refund is different from 
the currency of the PayPal account where the payee holds their funds.
     */
    @JasonerProperty("net_amount_breakdown")
    public List<NetAmountBreakdownItem> netAmountBreakdown() {
        return netAmountBreakdown;
    }

    /**
     * totalRefundedAmount
     */
    @JasonerProperty("total_refunded_amount")
    public Money totalRefundedAmount() {
        return totalRefundedAmount;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Money grossAmount;
        private Money paypalFee;
        private Money paypalFeeInReceivableCurrency;
        private Money netAmount;
        private Money netAmountInReceivableCurrency;
        private List<PlatformFee> platformFees;
        private List<NetAmountBreakdownItem> netAmountBreakdown;
        private Money totalRefundedAmount;

        /**
         * grossAmount
         */
        @JasonerProperty("gross_amount")
        public Builder grossAmount(Money value) {
            this.grossAmount = value;
            return this;
        }

        /**
         * paypalFee
         */
        @JasonerProperty("paypal_fee")
        public Builder paypalFee(Money value) {
            this.paypalFee = value;
            return this;
        }

        /**
         * paypalFeeInReceivableCurrency
         */
        @JasonerProperty("paypal_fee_in_receivable_currency")
        public Builder paypalFeeInReceivableCurrency(Money value) {
            this.paypalFeeInReceivableCurrency = value;
            return this;
        }

        /**
         * netAmount
         */
        @JasonerProperty("net_amount")
        public Builder netAmount(Money value) {
            this.netAmount = value;
            return this;
        }

        /**
         * netAmountInReceivableCurrency
         */
        @JasonerProperty("net_amount_in_receivable_currency")
        public Builder netAmountInReceivableCurrency(Money value) {
            this.netAmountInReceivableCurrency = value;
            return this;
        }

        /**
         * An array of platform or partner fees, commissions, or brokerage fees for the refund.
         */
        @JasonerProperty("platform_fees")
        public Builder platformFees(List<PlatformFee> value) {
            this.platformFees = value;
            return this;
        }

        /**
         * An array of breakdown values for the net amount. Returned when the currency of the refund is different from 
the currency of the PayPal account where the payee holds their funds.
         */
        @JasonerProperty("net_amount_breakdown")
        public Builder netAmountBreakdown(List<NetAmountBreakdownItem> value) {
            this.netAmountBreakdown = value;
            return this;
        }

        /**
         * totalRefundedAmount
         */
        @JasonerProperty("total_refunded_amount")
        public Builder totalRefundedAmount(Money value) {
            this.totalRefundedAmount = value;
            return this;
        }

        public SellerPayableBreakdown build() {
            return new SellerPayableBreakdown(this);
        }

    }

}


}

