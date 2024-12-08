package io.github.eealba.payper.subscriptions.v1.internal;

import io.github.eealba.payper.core.Payper;
import io.github.eealba.payper.core.PayperConfig;
import io.github.eealba.payper.subscriptions.v1.api.BillingPlans;
import io.github.eealba.payper.subscriptions.v1.api.Subscriptions;

class SubscriptionsImpl extends Subscriptions {
    private final Payper payper;
    private final BillingPlans billingPlans;

    SubscriptionsImpl(PayperConfig config) {
        this.payper = Payper.create(config);
        this.billingPlans = new BillingPlansImpl();
    }

    @Override
    public BillingPlans billingPlans() {
        return billingPlans;
    }

    private class BillingPlansImpl implements BillingPlans {
        @Override
        public CreatePlan create() {
            return new CreatePlanImpl(payper);
        }

        @Override
        public ListPlans list() {
            return new ListPlanImpl(payper);
        }

        @Override
        public GetPlan get() {
            return new GetPlanImpl(payper);
        }

        @Override
        public UpdatePlan update() {
            return new UpdatePlanImpl(payper);
        }

        @Override
        public ActivatePlan activate() {
            return new ActivatePlanImpl(payper);
        }

        @Override
        public DeactivatePlan deactivate() {
            return new DeactivatePlanImpl(payper);
        }

        @Override
        public UpdatePricingSchemes updatePricingSchemes() {
            return new UpdatePricingSchemesImpl(payper);
        }
    }
}