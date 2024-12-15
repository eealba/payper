package io.github.eealba.payper.subscriptions.v1.model;

import io.github.eealba.jasoner.JasonerProperty;

import java.util.List;

/**
 * The response to a request to update the quantity of the product or service in a subscription. You can also use this 
method to switch the plan and update the `shipping_amount` and `shipping_address` values for the subscription. This type 
of update requires the buyer's consent.
 */
public class SubscriptionReviseResponse {


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
    @JasonerProperty("plan_overridden")
    private final Boolean planOverridden;
    
    private final List<LinkDescription> links;

    private SubscriptionReviseResponse(Builder builder) {
        planId = builder.planId;
        quantity = builder.quantity;
        shippingAmount = builder.shippingAmount;
        shippingAddress = builder.shippingAddress;
        applicationContext = builder.applicationContext;
        plan = builder.plan;
        planOverridden = builder.planOverridden;
        links = builder.links;

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

    /**
     * Indicates whether the subscription has overridden any plan attributes.
     */
    @JasonerProperty("plan_overridden")
    public Boolean planOverridden() {
        return planOverridden;
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

        private String planId;
        private String quantity;
        private Money shippingAmount;
        private ShippingDetail shippingAddress;
        private ApplicationContext applicationContext;
        private PlanOverride plan;
        private Boolean planOverridden;
        private List<LinkDescription> links;

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

        /**
         * Indicates whether the subscription has overridden any plan attributes.
         */
        @JasonerProperty("plan_overridden")
        public Builder planOverridden(Boolean value) {
            this.planOverridden = value;
            return this;
        }

        /**
         * An array of request-related [HATEOAS links](/docs/api/reference/api-responses/#hateoas-links).
         */
        
        public Builder links(List<LinkDescription> value) {
            this.links = value;
            return this;
        }

        public SubscriptionReviseResponse build() {
            return new SubscriptionReviseResponse(this);
        }

    }

}

