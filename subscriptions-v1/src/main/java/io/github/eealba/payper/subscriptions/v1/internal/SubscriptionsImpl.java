package io.github.eealba.payper.subscriptions.v1.internal;

import io.github.eealba.payper.core.Payper;
import io.github.eealba.payper.core.PayperConfig;
import io.github.eealba.payper.subscriptions.v1.api.BillingPlans;
import io.github.eealba.payper.subscriptions.v1.api.BillingSubscriptions;
import io.github.eealba.payper.subscriptions.v1.api.Subscriptions;

class SubscriptionsImpl extends Subscriptions {
    private final BillingPlans billingPlans;
    private final BillingSubscriptions billingSubscriptions;

    SubscriptionsImpl(PayperConfig config) {
        var payper = Payper.create(config);
        this.billingPlans = new BillingPlansImpl(payper);
        this.billingSubscriptions = new BillingSubscriptionsImpl(payper);
    }

    @Override
    public BillingPlans billingPlans() {
        return billingPlans;
    }

    @Override
    public BillingSubscriptions billingSubscriptions() {
        return billingSubscriptions;
    }

}