package io.github.eealba.payper.subscriptions.v1.model;

import java.util.List;
import java.util.Objects;
import io.github.eealba.jasoner.JasonerProperty;

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

