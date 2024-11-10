package io.github.eealba.payper.subscriptions.v1.model;

import java.time.Instant;
import java.util.List;
import io.github.eealba.jasoner.JasonerProperty;

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

    
    public String id() {
        return id;
    }

    @JasonerProperty("product_id")
    public String productId() {
        return productId;
    }

    
    public String name() {
        return name;
    }

    
    public Status status() {
        return status;
    }

    
    public String description() {
        return description;
    }

    @JasonerProperty("billing_cycles")
    public List<BillingCycle> billingCycles() {
        return billingCycles;
    }

    @JasonerProperty("payment_preferences")
    public PaymentPreferences paymentPreferences() {
        return paymentPreferences;
    }

    
    public Taxes taxes() {
        return taxes;
    }

    @JasonerProperty("quantity_supported")
    public Boolean quantitySupported() {
        return quantitySupported;
    }

    @JasonerProperty("create_time")
    public Instant createTime() {
        return createTime;
    }

    @JasonerProperty("update_time")
    public Instant updateTime() {
        return updateTime;
    }

    
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

        
        public Builder id(String value) {
            this.id = value;
            return this;
        }

        @JasonerProperty("product_id")
        public Builder productId(String value) {
            this.productId = value;
            return this;
        }

        
        public Builder name(String value) {
            this.name = value;
            return this;
        }

        
        public Builder status(Status value) {
            this.status = value;
            return this;
        }

        
        public Builder description(String value) {
            this.description = value;
            return this;
        }

        @JasonerProperty("billing_cycles")
        public Builder billingCycles(List<BillingCycle> value) {
            this.billingCycles = value;
            return this;
        }

        @JasonerProperty("payment_preferences")
        public Builder paymentPreferences(PaymentPreferences value) {
            this.paymentPreferences = value;
            return this;
        }

        
        public Builder taxes(Taxes value) {
            this.taxes = value;
            return this;
        }

        @JasonerProperty("quantity_supported")
        public Builder quantitySupported(Boolean value) {
            this.quantitySupported = value;
            return this;
        }

        @JasonerProperty("create_time")
        public Builder createTime(Instant value) {
            this.createTime = value;
            return this;
        }

        @JasonerProperty("update_time")
        public Builder updateTime(Instant value) {
            this.updateTime = value;
            return this;
        }

        
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

