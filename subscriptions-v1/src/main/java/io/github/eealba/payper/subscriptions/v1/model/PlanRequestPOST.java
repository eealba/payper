package io.github.eealba.payper.subscriptions.v1.model;

import io.github.eealba.jasoner.JasonerProperty;

import java.util.List;
import java.util.Objects;

/**
 * The create plan request details.
 */
public class PlanRequestPOST {


    @JasonerProperty("product_id")
    private final String productId;
    
    private final String name;
    
    private final Status status;
    
    private final String description;
    @JasonerProperty("billing_cycles")
    private final List<BillingCycle> billingCycles;
    @JasonerProperty("payment_preferences")
    private final PaymentPreferences paymentPreferences;
    
    private final Taxes taxes;
    @JasonerProperty("quantity_supported")
    private final Boolean quantitySupported;

    private PlanRequestPOST(Builder builder) {
        status = builder.status;
        description = builder.description;
        taxes = builder.taxes;
        quantitySupported = builder.quantitySupported;
        productId = Objects.requireNonNull(builder.productId);
        name = Objects.requireNonNull(builder.name);
        billingCycles = Objects.requireNonNull(builder.billingCycles);
        paymentPreferences = Objects.requireNonNull(builder.paymentPreferences);
    }

    /**
     * The ID of the product created through Catalog Products API.
     */
    @JasonerProperty("product_id")
    public String productId() {
        return productId;
    }

    /**
     * The plan name.
     */
    
    public String name() {
        return name;
    }

    /**
     * The initial state of the plan. Allowed input values are CREATED and ACTIVE.
     */
    
    public Status status() {
        return status;
    }

    /**
     * The detailed description of the plan.
     */
    
    public String description() {
        return description;
    }

    /**
     * An array of billing cycles for trial billing and regular billing. A plan can have at most two trial cycles and 
only one regular cycle.
     */
    @JasonerProperty("billing_cycles")
    public List<BillingCycle> billingCycles() {
        return billingCycles;
    }

    /**
     * paymentPreferences
     */
    @JasonerProperty("payment_preferences")
    public PaymentPreferences paymentPreferences() {
        return paymentPreferences;
    }

    /**
     * taxes
     */
    
    public Taxes taxes() {
        return taxes;
    }

    /**
     * Indicates whether you can subscribe to this plan by providing a quantity for the goods or service.
     */
    @JasonerProperty("quantity_supported")
    public Boolean quantitySupported() {
        return quantitySupported;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String productId;
        private String name;
        private Status status;
        private String description;
        private List<BillingCycle> billingCycles;
        private PaymentPreferences paymentPreferences;
        private Taxes taxes;
        private Boolean quantitySupported;

        /**
         * The ID of the product created through Catalog Products API.
         */
        @JasonerProperty("product_id")
        public Builder productId(String value) {
            this.productId = value;
            return this;
        }

        /**
         * The plan name.
         */
        
        public Builder name(String value) {
            this.name = value;
            return this;
        }

        /**
         * The initial state of the plan. Allowed input values are CREATED and ACTIVE.
         */
        
        public Builder status(Status value) {
            this.status = value;
            return this;
        }

        /**
         * The detailed description of the plan.
         */
        
        public Builder description(String value) {
            this.description = value;
            return this;
        }

        /**
         * An array of billing cycles for trial billing and regular billing. A plan can have at most two trial cycles and 
only one regular cycle.
         */
        @JasonerProperty("billing_cycles")
        public Builder billingCycles(List<BillingCycle> value) {
            this.billingCycles = value;
            return this;
        }

        /**
         * paymentPreferences
         */
        @JasonerProperty("payment_preferences")
        public Builder paymentPreferences(PaymentPreferences value) {
            this.paymentPreferences = value;
            return this;
        }

        /**
         * taxes
         */
        
        public Builder taxes(Taxes value) {
            this.taxes = value;
            return this;
        }

        /**
         * Indicates whether you can subscribe to this plan by providing a quantity for the goods or service.
         */
        @JasonerProperty("quantity_supported")
        public Builder quantitySupported(Boolean value) {
            this.quantitySupported = value;
            return this;
        }

        public PlanRequestPOST build() {
            return new PlanRequestPOST(this);
        }

    }
    /**
     * The initial state of the plan. Allowed input values are CREATED and ACTIVE.
     */
    public enum Status {
        CREATED,
        INACTIVE,
        ACTIVE
    }
}

