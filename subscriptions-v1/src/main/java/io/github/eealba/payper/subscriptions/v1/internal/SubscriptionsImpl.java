package io.github.eealba.payper.subscriptions.v1.internal;

import io.github.eealba.payper.core.Payper;
import io.github.eealba.payper.core.PayperConfig;
import io.github.eealba.payper.subscriptions.v1.api.Subscriptions;

public class SubscriptionsImpl implements Subscriptions {
    private final Payper payper;

    public SubscriptionsImpl(PayperConfig config) {
        this.payper = Payper.newPayper(config);
    }

    @Override
    public BillingPlans billingPlans() {
        return new BillingPlansImpl();
    }

    private class BillingPlansImpl implements BillingPlans {
        @Override
        public CreatePlan create() {
            return new CreatePlanImpl(payper);
        }

        @Override
        public ListPlans list() {
            return null;
        }

        @Override
        public GetPlan get() {
            return new GetPlanImpl(payper);
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