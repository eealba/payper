package io.github.eealba.payper.subscriptions.v1.model;

import io.github.eealba.jasoner.JasonerProperty;

import java.time.Instant;
import java.util.Objects;

/**
 * The create subscription request details.
 */
public class SubscriptionRequestPost {


    @JasonerProperty("plan_id")
    private final String planId;
    @JasonerProperty("start_time")
    private final Instant startTime;
    
    private final String quantity;
    @JasonerProperty("shipping_amount")
    private final Money shippingAmount;
    
    private final SubscriberRequest subscriber;
    @JasonerProperty("auto_renewal")
    private final Boolean autoRenewal;
    @JasonerProperty("application_context")
    private final ApplicationContext applicationContext;
    @JasonerProperty("custom_id")
    private final String customId;
    
    private final PlanOverride plan;

    private SubscriptionRequestPost(Builder builder) {
        startTime = builder.startTime;
        quantity = builder.quantity;
        shippingAmount = builder.shippingAmount;
        subscriber = builder.subscriber;
        autoRenewal = builder.autoRenewal;
        applicationContext = builder.applicationContext;
        customId = builder.customId;
        plan = builder.plan;
        planId = Objects.requireNonNull(builder.planId);
    }

    /**
     * The ID of the plan.
     */
    @JasonerProperty("plan_id")
    public String planId() {
        return planId;
    }

    /**
     * startTime
     */
    @JasonerProperty("start_time")
    public Instant startTime() {
        return startTime;
    }

    /**
     * The quantity of the product in the subscription.
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
     * subscriber
     */
    
    public SubscriberRequest subscriber() {
        return subscriber;
    }

    /**
     * DEPRECATED. Indicates whether the subscription auto-renews after the billing cycles complete.
     */
    @JasonerProperty("auto_renewal")
    public Boolean autoRenewal() {
        return autoRenewal;
    }

    /**
     * applicationContext
     */
    @JasonerProperty("application_context")
    public ApplicationContext applicationContext() {
        return applicationContext;
    }

    /**
     * The custom id for the subscription. Can be invoice id.
     */
    @JasonerProperty("custom_id")
    public String customId() {
        return customId;
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
        private Instant startTime;
        private String quantity;
        private Money shippingAmount;
        private SubscriberRequest subscriber;
        private Boolean autoRenewal;
        private ApplicationContext applicationContext;
        private String customId;
        private PlanOverride plan;

        /**
         * The ID of the plan.
         */
        @JasonerProperty("plan_id")
        public Builder planId(String value) {
            this.planId = value;
            return this;
        }

        /**
         * startTime
         */
        @JasonerProperty("start_time")
        public Builder startTime(Instant value) {
            this.startTime = value;
            return this;
        }

        /**
         * The quantity of the product in the subscription.
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
         * subscriber
         */
        
        public Builder subscriber(SubscriberRequest value) {
            this.subscriber = value;
            return this;
        }

        /**
         * DEPRECATED. Indicates whether the subscription auto-renews after the billing cycles complete.
         */
        @JasonerProperty("auto_renewal")
        public Builder autoRenewal(Boolean value) {
            this.autoRenewal = value;
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
         * The custom id for the subscription. Can be invoice id.
         */
        @JasonerProperty("custom_id")
        public Builder customId(String value) {
            this.customId = value;
            return this;
        }

        /**
         * plan
         */
        
        public Builder plan(PlanOverride value) {
            this.plan = value;
            return this;
        }

        public SubscriptionRequestPost build() {
            return new SubscriptionRequestPost(this);
        }

    }

}

