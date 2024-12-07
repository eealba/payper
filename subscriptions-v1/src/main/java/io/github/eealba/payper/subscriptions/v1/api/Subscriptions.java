package io.github.eealba.payper.subscriptions.v1.api;

import io.github.eealba.payper.core.PayperConfig;

public abstract class  Subscriptions {
    public static Subscriptions create() {
        return create(PayperConfig.builder().build());
    }
    public static Subscriptions create(PayperConfig config) {
        return SubscriptionsProvider.provider().createSubscriptions(config);
    }

    public abstract BillingPlans billingPlans();





}
