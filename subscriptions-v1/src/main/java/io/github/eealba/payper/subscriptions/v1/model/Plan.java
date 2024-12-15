package io.github.eealba.payper.subscriptions.v1.model;

import io.github.eealba.jasoner.JasonerProperty;

import java.time.Instant;
import java.util.List;

/**
 * The plan details.
 */
public class Plan {


    
    private final String id;
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
    @JasonerProperty("create_time")
    private final Instant createTime;
    @JasonerProperty("update_time")
    private final Instant updateTime;
    
    private final List<LinkDescription> links;

    private Plan(Builder builder) {
        id = builder.id;
        productId = builder.productId;
        name = builder.name;
        status = builder.status;
        description = builder.description;
        billingCycles = builder.billingCycles;
        paymentPreferences = builder.paymentPreferences;
        taxes = builder.taxes;
        quantitySupported = builder.quantitySupported;
        createTime = builder.createTime;
        updateTime = builder.updateTime;
        links = builder.links;

    }

    /**
     * The unique PayPal-generated ID for the plan.
     */
    
    public String id() {
        return id;
    }

    /**
     * The ID for the product.
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
     * The plan status.
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
     * An array of request-related [HATEOAS links](/docs/api/reference/api-responses/#hateoas-links).
     */
    
    public List<LinkDescription> links() {
        return links;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String id;
        private String productId;
        private String name;
        private Status status;
        private String description;
        private List<BillingCycle> billingCycles;
        private PaymentPreferences paymentPreferences;
        private Taxes taxes;
        private Boolean quantitySupported;
        private Instant createTime;
        private Instant updateTime;
        private List<LinkDescription> links;

        /**
         * The unique PayPal-generated ID for the plan.
         */
        
        public Builder id(String value) {
            this.id = value;
            return this;
        }

        /**
         * The ID for the product.
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
         * The plan status.
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
         * An array of request-related [HATEOAS links](/docs/api/reference/api-responses/#hateoas-links).
         */
        
        public Builder links(List<LinkDescription> value) {
            this.links = value;
            return this;
        }

        public Plan build() {
            return new Plan(this);
        }

    }
    /**
     * The plan status.
     */
    public enum Status {
        CREATED,
        INACTIVE,
        ACTIVE
    }
}

