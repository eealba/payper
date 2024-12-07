package io.github.eealba.payper.subscriptions.v1.internal;

import io.github.eealba.payper.core.Payper;
import io.github.eealba.payper.subscriptions.v1.api.BillingPlans;
import io.github.eealba.payper.subscriptions.v1.model.ErrorDefault;
import io.github.eealba.payper.subscriptions.v1.model.Plan;
import io.github.eealba.payper.subscriptions.v1.model.PlanRequestPOST;

class CreatePlanImpl extends BodyRequestSpecImpl<BillingPlans.CreatePlan, PlanRequestPOST, Plan, ErrorDefault>
        implements BillingPlans.CreatePlan {
    CreatePlanImpl(Payper payper) {
        super(payper, "/v1/billing/plans", Plan.class, ErrorDefault.class);
    }
}
