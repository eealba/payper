package io.github.eealba.payper.orders.v2.model;

import io.github.eealba.jasoner.JasonerProperty;

import java.util.List;
import java.util.Objects;


/**
 * The order request details.
 */
public class OrderRequest {


    
    private final CheckoutPaymentIntent intent;
    
    private final Payer payer;
    @JasonerProperty("purchase_units")
    private final List<PurchaseUnitRequest> purchaseUnits;
    @JasonerProperty("payment_source")
    private final PaymentSource paymentSource;
    @JasonerProperty("application_context")
    private final OrderApplicationContext applicationContext;

    private OrderRequest(Builder builder) {
        payer = builder.payer;
        paymentSource = builder.paymentSource;
        applicationContext = builder.applicationContext;
        intent = Objects.requireNonNull(builder.intent);
        purchaseUnits = Objects.requireNonNull(builder.purchaseUnits);
    }

    /**
     * intent
     */
    
    public CheckoutPaymentIntent intent() {
        return intent;
    }

    /**
     * payer
     */
    
    public Payer payer() {
        return payer;
    }

    /**
     * An array of purchase units. Each purchase unit establishes a contract between a payer and the payee. Each 
purchase unit represents either a full or partial order that the payer intends to purchase from the payee.
     */
    @JasonerProperty("purchase_units")
    public List<PurchaseUnitRequest> purchaseUnits() {
        return purchaseUnits;
    }

    /**
     * paymentSource
     */
    @JasonerProperty("payment_source")
    public PaymentSource paymentSource() {
        return paymentSource;
    }

    /**
     * applicationContext
     */
    @JasonerProperty("application_context")
    public OrderApplicationContext applicationContext() {
        return applicationContext;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private CheckoutPaymentIntent intent;
        private Payer payer;
        private List<PurchaseUnitRequest> purchaseUnits;
        private PaymentSource paymentSource;
        private OrderApplicationContext applicationContext;

        /**
         * intent
         */
        
        public Builder intent(CheckoutPaymentIntent value) {
            this.intent = value;
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
         * An array of purchase units. Each purchase unit establishes a contract between a payer and the payee. Each 
purchase unit represents either a full or partial order that the payer intends to purchase from the payee.
         */
        @JasonerProperty("purchase_units")
        public Builder purchaseUnits(List<PurchaseUnitRequest> value) {
            this.purchaseUnits = value;
            return this;
        }

        /**
         * paymentSource
         */
        @JasonerProperty("payment_source")
        public Builder paymentSource(PaymentSource value) {
            this.paymentSource = value;
            return this;
        }

        /**
         * applicationContext
         */
        @JasonerProperty("application_context")
        public Builder applicationContext(OrderApplicationContext value) {
            this.applicationContext = value;
            return this;
        }

        public OrderRequest build() {
            return new OrderRequest(this);
        }

    }


}

