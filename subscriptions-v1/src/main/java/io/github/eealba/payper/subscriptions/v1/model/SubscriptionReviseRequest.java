package io.github.eealba.payper.subscriptions.v1.model;


import io.github.eealba.jasoner.JasonerProperty;

/**
 * The request to update the quantity of the product or service in a subscription. You can also use this method to switch 
the plan and update the `shipping_amount` and `shipping_address` values for the subscription. This type of update 
requires the buyer's consent.
 */
public class SubscriptionReviseRequest {


    @JasonerProperty("plan_id")
    private final String planId;
    
    private final String quantity;
    @JasonerProperty("shipping_amount")
    private final Money shippingAmount;
    @JasonerProperty("shipping_address")
    private final ShippingDetail shippingAddress;
    @JasonerProperty("application_context")
    private final ApplicationContext applicationContext;
    
    private final PlanOverride plan;

    private SubscriptionReviseRequest(Builder builder) {
        planId = builder.planId;
        quantity = builder.quantity;
        shippingAmount = builder.shippingAmount;
        shippingAddress = builder.shippingAddress;
        applicationContext = builder.applicationContext;
        plan = builder.plan;

    }

    /**
     * The unique PayPal-generated ID for the plan.
     */
    @JasonerProperty("plan_id")
    public String planId() {
        return planId;
    }

    /**
     * The quantity of the product or service in the subscription.
     */
    
    public String quantity() {
        return quantity;
    }

    /**
     * shippingAmount
     */
    @JasonerProperty("shipping_amount")
    public Money shippingAmount() {
        return shippingAmount;
    }

    /**
     * shippingAddress
     */
    @JasonerProperty("shipping_address")
    public ShippingDetail shippingAddress() {
        return shippingAddress;
    }

    /**
     * applicationContext
     */
    @JasonerProperty("application_context")
    public ApplicationContext applicationContext() {
        return applicationContext;
    }

    /**
     * plan
     */
    
    public PlanOverride plan() {
        return plan;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String planId;
        private String quantity;
        private Money shippingAmount;
        private ShippingDetail shippingAddress;
        private ApplicationContext applicationContext;
        private PlanOverride plan;

        /**
         * The unique PayPal-generated ID for the plan.
         */
        @JasonerProperty("plan_id")
        public Builder planId(String value) {
            this.planId = value;
            return this;
        }

        /**
         * The quantity of the product or service in the subscription.
         */
        
        public Builder quantity(String value) {
            this.quantity = value;
            return this;
        }

        /**
         * shippingAmount
         */
        @JasonerProperty("shipping_amount")
        public Builder shippingAmount(Money value) {
            this.shippingAmount = value;
            return this;
        }

        /**
         * shippingAddress
         */
        @JasonerProperty("shipping_address")
        public Builder shippingAddress(ShippingDetail value) {
            this.shippingAddress = value;
            return this;
        }

        /**
         * applicationContext
         */
        @JasonerProperty("application_context")
        public Builder applicationContext(ApplicationContext value) {
            this.applicationContext = value;
            return this;
        }

        /**
         * plan
         */
        
        public Builder plan(PlanOverride value) {
            this.plan = value;
            return this;
        }

        public SubscriptionReviseRequest build() {
            return new SubscriptionReviseRequest(this);
        }

    }

}

