package io.github.eealba.payper.subscriptions.v1.internal;

import io.github.eealba.payper.core.PayperConfig;
import io.github.eealba.payper.subscriptions.v1.api.Subscriptions;
import io.github.eealba.payper.subscriptions.v1.api.SubscriptionsProvider;

public class SubscriptionsProviderImpl extends  SubscriptionsProvider {
    @Override
    public Subscriptions createSubscriptions(PayperConfig config) {
        return new SubscriptionsImpl(config);
    }
}
