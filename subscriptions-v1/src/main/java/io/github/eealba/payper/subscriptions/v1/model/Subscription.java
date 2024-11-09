package io.github.eealba.payper.subscriptions.v1.model;

import java.time.Instant;
import java.util.List;
import io.github.eealba.jasoner.JasonerProperty;

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

    
    public Status status() {
        return status;
    }

    @JasonerProperty("status_change_note")
    public String statusChangeNote() {
        return statusChangeNote;
    }

    @JasonerProperty("status_update_time")
    public Instant statusUpdateTime() {
        return statusUpdateTime;
    }

    
    public String id() {
        return id;
    }

    @JasonerProperty("plan_id")
    public String planId() {
        return planId;
    }

    @JasonerProperty("start_time")
    public Instant startTime() {
        return startTime;
    }

    
    public String quantity() {
        return quantity;
    }

    @JasonerProperty("shipping_amount")
    public Money shippingAmount() {
        return shippingAmount;
    }

    
    public Subscriber subscriber() {
        return subscriber;
    }

    @JasonerProperty("billing_info")
    public SubscriptionBillingInfo billingInfo() {
        return billingInfo;
    }

    @JasonerProperty("create_time")
    public Instant createTime() {
        return createTime;
    }

    @JasonerProperty("update_time")
    public Instant updateTime() {
        return updateTime;
    }

    @JasonerProperty("custom_id")
    public String customId() {
        return customId;
    }

    @JasonerProperty("plan_overridden")
    public Boolean planOverridden() {
        return planOverridden;
    }

    
    public Plan plan() {
        return plan;
    }

    
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

        
        public Builder status(Status value) {
            status = value;
            return this;
        }

        @JasonerProperty("status_change_note")
        public Builder statusChangeNote(String value) {
            statusChangeNote = value;
            return this;
        }

        @JasonerProperty("status_update_time")
        public Builder statusUpdateTime(Instant value) {
            statusUpdateTime = value;
            return this;
        }

        
        public Builder id(String value) {
            id = value;
            return this;
        }

        @JasonerProperty("plan_id")
        public Builder planId(String value) {
            planId = value;
            return this;
        }

        @JasonerProperty("start_time")
        public Builder startTime(Instant value) {
            startTime = value;
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

        
        public Builder subscriber(Subscriber value) {
            subscriber = value;
            return this;
        }

        @JasonerProperty("billing_info")
        public Builder billingInfo(SubscriptionBillingInfo value) {
            billingInfo = value;
            return this;
        }

        @JasonerProperty("create_time")
        public Builder createTime(Instant value) {
            createTime = value;
            return this;
        }

        @JasonerProperty("update_time")
        public Builder updateTime(Instant value) {
            updateTime = value;
            return this;
        }

        @JasonerProperty("custom_id")
        public Builder customId(String value) {
            customId = value;
            return this;
        }

        @JasonerProperty("plan_overridden")
        public Builder planOverridden(Boolean value) {
            planOverridden = value;
            return this;
        }

        
        public Builder plan(Plan value) {
            plan = value;
            return this;
        }

        
        public Builder links(List<LinkDescription> value) {
            links = value;
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

