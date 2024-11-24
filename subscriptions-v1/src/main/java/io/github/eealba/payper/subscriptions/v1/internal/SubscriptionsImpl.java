package io.github.eealba.payper.subscriptions.v1.internal;

import io.github.eealba.payper.core.PayperConfig;
import io.github.eealba.payper.subscriptions.v1.api.Subscriptions;

import java.net.http.HttpClient;

public class SubscriptionsImpl implements Subscriptions {
    private final PayperConfig config;
    private final HttpClient httpClient;

    public SubscriptionsImpl(PayperConfig config) {
        this.config = config;
        this.httpClient = HttpClient.newHttpClient();
    }

    @Override
    public BillingPlans billingPlans() {
        return new BillingPlansImpl();
    }

    private class BillingPlansImpl implements BillingPlans {
        @Override
        public CreatePlan create() {
            return new CreatePlanImpl();
        }

        @Override
        public ListPlans list() {
            return null;
        }

        @Override
        public GetPlan get() {
            return null;
        }

        @Override
        public UpdatePlan update() {
            return null;
        }

        @Override
        public ActivatePlan activate() {
            return null;
        }

        @Override
        public DeactivatePlan deactivate() {
            return null;
        }

        @Override
        public Subscriptions.UpdatePricingSchemes updatePricingSchemes() {
            return null;
        }
    }



    // Implement other inner classes (ListPlansImpl, GetPlanImpl, UpdatePlanImpl, ActivatePlanImpl, DeactivatePlanImpl, UpdatePricingSchemesImpl) similarly
}