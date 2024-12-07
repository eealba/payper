package io.github.eealba.payper.subscriptions.v1.internal;

import io.github.eealba.payper.core.Payper;
import io.github.eealba.payper.subscriptions.v1.api.BillingPlans;
import io.github.eealba.payper.subscriptions.v1.model.ErrorDefault;
import io.github.eealba.payper.subscriptions.v1.model.Plan;

class GetPlanImpl extends RequestSpecImpl<BillingPlans.GetPlan, Plan, ErrorDefault>
        implements BillingPlans.GetPlan {
    GetPlanImpl(Payper payper) {
        super(payper,"/v1/billing/plans/{id}", Plan.class, ErrorDefault.class);
    }


    @Override
    public BillingPlans.GetPlan withId(String id) {
        requestBuilder.pathParameter("id", id);
        return this;
    }

}
