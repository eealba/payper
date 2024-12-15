package io.github.eealba.payper.subscriptions.v1.model;

import io.github.eealba.jasoner.JasonerProperty;

import java.time.Instant;
import java.util.List;

/**
 * The subscription details.
 */
public class Subscription {


    
    private final Status status;
    @JasonerProperty("status_change_note")
    private final String statusChangeNote;
    @JasonerProperty("status_update_time")
    private final Instant statusUpdateTime;
    
    private final String id;
    @JasonerProperty("plan_id")
    private final String planId;
    @JasonerProperty("start_time")
    private final Instant startTime;
    
    private final String quantity;
    @JasonerProperty("shipping_amount")
    private final Money shippingAmount;
    
    private final Subscriber subscriber;
    @JasonerProperty("billing_info")
    private final SubscriptionBillingInfo billingInfo;
    @JasonerProperty("create_time")
    private final Instant createTime;
    @JasonerProperty("update_time")
    private final Instant updateTime;
    @JasonerProperty("custom_id")
    private final String customId;
    @JasonerProperty("plan_overridden")
    private final Boolean planOverridden;
    
    private final Plan plan;
    
    private final List<LinkDescription> links;

    private Subscription(Builder builder) {
        status = builder.status;
        statusChangeNote = builder.statusChangeNote;
        statusUpdateTime = builder.statusUpdateTime;
        id = builder.id;
        planId = builder.planId;
        startTime = builder.startTime;
        quantity = builder.quantity;
        shippingAmount = builder.shippingAmount;
        subscriber = builder.subscriber;
        billingInfo = builder.billingInfo;
        createTime = builder.createTime;
        updateTime = builder.updateTime;
        customId = builder.customId;
        planOverridden = builder.planOverridden;
        plan = builder.plan;
        links = builder.links;

    }

    /**
     * The status of the subscription.
     */
    
    public Status status() {
        return status;
    }

    /**
     * The reason or notes for the status of the subscription.
     */
    @JasonerProperty("status_change_note")
    public String statusChangeNote() {
        return statusChangeNote;
    }

    /**
     * statusUpdateTime
     */
    @JasonerProperty("status_update_time")
    public Instant statusUpdateTime() {
        return statusUpdateTime;
    }

    /**
     * The PayPal-generated ID for the subscription.
     */
    
    public String id() {
        return id;
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
    
    public Subscriber subscriber() {
        return subscriber;
    }

    /**
     * billingInfo
     */
    @JasonerProperty("billing_info")
    public SubscriptionBillingInfo billingInfo() {
        return billingInfo;
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
     * The custom id for the subscription. Can be invoice id.
     */
    @JasonerProperty("custom_id")
    public String customId() {
        return customId;
    }

    /**
     * Indicates whether the subscription has overridden any plan attributes.
     */
    @JasonerProperty("plan_overridden")
    public Boolean planOverridden() {
        return planOverridden;
    }

    /**
     * plan
     */
    
    public Plan plan() {
        return plan;
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

        private Status status;
        private String statusChangeNote;
        private Instant statusUpdateTime;
        private String id;
        private String planId;
        private Instant startTime;
        private String quantity;
        private Money shippingAmount;
        private Subscriber subscriber;
        private SubscriptionBillingInfo billingInfo;
        private Instant createTime;
        private Instant updateTime;
        private String customId;
        private Boolean planOverridden;
        private Plan plan;
        private List<LinkDescription> links;

        /**
         * The status of the subscription.
         */
        
        public Builder status(Status value) {
            this.status = value;
            return this;
        }

        /**
         * The reason or notes for the status of the subscription.
         */
        @JasonerProperty("status_change_note")
        public Builder statusChangeNote(String value) {
            this.statusChangeNote = value;
            return this;
        }

        /**
         * statusUpdateTime
         */
        @JasonerProperty("status_update_time")
        public Builder statusUpdateTime(Instant value) {
            this.statusUpdateTime = value;
            return this;
        }

        /**
         * The PayPal-generated ID for the subscription.
         */
        
        public Builder id(String value) {
            this.id = value;
            return this;
        }

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
        
        public Builder subscriber(Subscriber value) {
            this.subscriber = value;
            return this;
        }

        /**
         * billingInfo
         */
        @JasonerProperty("billing_info")
        public Builder billingInfo(SubscriptionBillingInfo value) {
            this.billingInfo = value;
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
         * The custom id for the subscription. Can be invoice id.
         */
        @JasonerProperty("custom_id")
        public Builder customId(String value) {
            this.customId = value;
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
         * plan
         */
        
        public Builder plan(Plan value) {
            this.plan = value;
            return this;
        }

        /**
         * An array of request-related [HATEOAS links](/docs/api/reference/api-responses/#hateoas-links).
         */
        
        public Builder links(List<LinkDescription> value) {
            this.links = value;
            return this;
        }

        public Subscription build() {
            return new Subscription(this);
        }

    }
    /**
     * The status of the subscription.
     */
    public enum Status {
        APPROVAL_PENDING,
        APPROVED,
        ACTIVE,
        SUSPENDED,
        CANCELLED,
        EXPIRED
    }
}

