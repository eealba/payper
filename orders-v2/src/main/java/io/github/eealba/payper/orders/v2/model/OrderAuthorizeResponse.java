package io.github.eealba.payper.orders.v2.model;

import io.github.eealba.jasoner.JasonerProperty;

import java.time.Instant;
import java.util.List;

/**
 * The order authorize response.
 */
public class OrderAuthorizeResponse {


    @JasonerProperty("create_time")
    private final Instant createTime;
    @JasonerProperty("update_time")
    private final Instant updateTime;
    
    private final String id;
    @JasonerProperty("payment_source")
    private final PaymentSourceResponse paymentSource;
    
    private final CheckoutPaymentIntent intent;
    @JasonerProperty("processing_instruction")
    private final ProcessingInstruction processingInstruction;
    
    private final Payer payer;
    @JasonerProperty("purchase_units")
    private final List<PurchaseUnit> purchaseUnits;
    
    private final OrderStatus status;
    
    private final List<LinkDescription> links;

    private OrderAuthorizeResponse(Builder builder) {
        createTime = builder.createTime;
        updateTime = builder.updateTime;
        id = builder.id;
        paymentSource = builder.paymentSource;
        intent = builder.intent;
        processingInstruction = builder.processingInstruction;
        payer = builder.payer;
        purchaseUnits = builder.purchaseUnits;
        status = builder.status;
        links = builder.links;

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
     * The ID of the order.
     */
    
    public String id() {
        return id;
    }

    /**
     * paymentSource
     */
    @JasonerProperty("payment_source")
    public PaymentSourceResponse paymentSource() {
        return paymentSource;
    }

    /**
     * intent
     */
    
    public CheckoutPaymentIntent intent() {
        return intent;
    }

    /**
     * processingInstruction
     */
    @JasonerProperty("processing_instruction")
    public ProcessingInstruction processingInstruction() {
        return processingInstruction;
    }

    /**
     * payer
     */
    
    public Payer payer() {
        return payer;
    }

    /**
     * An array of purchase units. Each purchase unit establishes a contract between a customer and merchant. Each 
purchase unit represents either a full or partial order that the customer intends to purchase from the 
merchant.
     */
    @JasonerProperty("purchase_units")
    public List<PurchaseUnit> purchaseUnits() {
        return purchaseUnits;
    }

    /**
     * status
     */
    
    public OrderStatus status() {
        return status;
    }

    /**
     * An array of request-related HATEOAS links. To complete payer approval, use the `approve` link to redirect the 
payer. The API caller has 3 hours (default setting, this which can be changed by your account manager to 
24/48/72 hours to accommodate your use case) from the time the order is created, to redirect your payer. Once 
redirected, the API caller has 3 hours for the payer to approve the order and either authorize or capture the 
order. If you are not using the PayPal JavaScript SDK to initiate PayPal Checkout (in context) ensure that you 
include `application_context.return_url` is specified or you will get "We're sorry, Things don't appear to be 
working at the moment" after the payer approves the payment.
     */
    
    public List<LinkDescription> links() {
        return links;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private Instant createTime;
        private Instant updateTime;
        private String id;
        private PaymentSourceResponse paymentSource;
        private CheckoutPaymentIntent intent;
        private ProcessingInstruction processingInstruction;
        private Payer payer;
        private List<PurchaseUnit> purchaseUnits;
        private OrderStatus status;
        private List<LinkDescription> links;

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
         * The ID of the order.
         */
        
        public Builder id(String value) {
            this.id = value;
            return this;
        }

        /**
         * paymentSource
         */
        @JasonerProperty("payment_source")
        public Builder paymentSource(PaymentSourceResponse value) {
            this.paymentSource = value;
            return this;
        }

        /**
         * intent
         */
        
        public Builder intent(CheckoutPaymentIntent value) {
            this.intent = value;
            return this;
        }

        /**
         * processingInstruction
         */
        @JasonerProperty("processing_instruction")
        public Builder processingInstruction(ProcessingInstruction value) {
            this.processingInstruction = value;
            return this;
        }

        /**
         * payer
         */
        
        public Builder payer(Payer value) {
            this.payer = value;
            return this;
        }

        /**
         * An array of purchase units. Each purchase unit establishes a contract between a customer and merchant. Each 
purchase unit represents either a full or partial order that the customer intends to purchase from the 
merchant.
         */
        @JasonerProperty("purchase_units")
        public Builder purchaseUnits(List<PurchaseUnit> value) {
            this.purchaseUnits = value;
            return this;
        }

        /**
         * status
         */
        
        public Builder status(OrderStatus value) {
            this.status = value;
            return this;
        }

        /**
         * An array of request-related HATEOAS links. To complete payer approval, use the `approve` link to redirect the 
payer. The API caller has 3 hours (default setting, this which can be changed by your account manager to 
24/48/72 hours to accommodate your use case) from the time the order is created, to redirect your payer. Once 
redirected, the API caller has 3 hours for the payer to approve the order and either authorize or capture the 
order. If you are not using the PayPal JavaScript SDK to initiate PayPal Checkout (in context) ensure that you 
include `application_context.return_url` is specified or you will get "We're sorry, Things don't appear to be 
working at the moment" after the payer approves the payment.
         */
        
        public Builder links(List<LinkDescription> value) {
            this.links = value;
            return this;
        }

        public OrderAuthorizeResponse build() {
            return new OrderAuthorizeResponse(this);
        }

    }

}

