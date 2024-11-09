package io.github.eealba.payper.subscriptions.v1.model;

import java.util.List;
import io.github.eealba.jasoner.JasonerProperty;

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

    @JasonerProperty("plan_id")
    public String planId() {
        return planId;
    }

    
    public String quantity() {
        return quantity;
    }

    @JasonerProperty("shipping_amount")
    public Money shippingAmount() {
        return shippingAmount;
    }

    @JasonerProperty("shipping_address")
    public ShippingDetail shippingAddress() {
        return shippingAddress;
    }

    @JasonerProperty("application_context")
    public ApplicationContext applicationContext() {
        return applicationContext;
    }

    
    public PlanOverride plan() {
        return plan;
    }

    @JasonerProperty("plan_overridden")
    public Boolean planOverridden() {
        return planOverridden;
    }

    
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

        @JasonerProperty("plan_id")
        public Builder planId(String value) {
            planId = value;
            return this;
        }

        
        public Builder quantity(String value) {
            quantity = value;
            return this;
        }

        @JasonerProperty("shipping_amount")
        public Builder shippingAmount(Money value) {
            shippingAmount = value;
            return this;
        }

        @JasonerProperty("shipping_address")
        public Builder shippingAddress(ShippingDetail value) {
            shippingAddress = value;
            return this;
        }

        @JasonerProperty("application_context")
        public Builder applicationContext(ApplicationContext value) {
            applicationContext = value;
            return this;
        }

        
        public Builder plan(PlanOverride value) {
            plan = value;
            return this;
        }

        @JasonerProperty("plan_overridden")
        public Builder planOverridden(Boolean value) {
            planOverridden = value;
            return this;
        }

        
        public Builder links(List<LinkDescription> value) {
            links = value;
            return this;
        }

        public SubscriptionReviseResponse build() {
            return new SubscriptionReviseResponse(this);
        }

    }

}

