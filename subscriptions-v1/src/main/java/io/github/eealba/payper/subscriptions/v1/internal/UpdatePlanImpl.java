package io.github.eealba.payper.subscriptions.v1.internal;

import io.github.eealba.payper.core.Payper;
import io.github.eealba.payper.core.web.Method;
import io.github.eealba.payper.subscriptions.v1.api.BillingPlans;
import io.github.eealba.payper.subscriptions.v1.model.ErrorDefault;
import io.github.eealba.payper.subscriptions.v1.model.PatchRequest;

class UpdatePlanImpl extends BodyRequestSpecImpl<BillingPlans.UpdatePlan, PatchRequest, Void, ErrorDefault>
        implements BillingPlans.UpdatePlan {
    UpdatePlanImpl(Payper payper) {
        super(payper, "/v1/billing/plans/{id}", Void.class, ErrorDefault.class);
    }

    @Override
    public BillingPlans.UpdatePlan withId(String id) {
        requestBuilder.pathParameter("id", id);
        return this;
    }

    @Override
    public Method getMethod() {
        return Method.PATCH;
    }
}
