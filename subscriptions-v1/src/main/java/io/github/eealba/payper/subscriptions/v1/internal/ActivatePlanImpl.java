package io.github.eealba.payper.subscriptions.v1.internal;

import io.github.eealba.payper.core.Payper;
import io.github.eealba.payper.core.web.Method;
import io.github.eealba.payper.subscriptions.v1.api.BillingPlans;
import io.github.eealba.payper.subscriptions.v1.model.ErrorDefault;

class ActivatePlanImpl extends RequestSpecImpl<BillingPlans.ActivatePlan, Void, ErrorDefault>
        implements BillingPlans.ActivatePlan {
    ActivatePlanImpl(Payper payper) {
        super(payper, "/v1/billing/plans/{id}/activate", Void.class, ErrorDefault.class);
    }

    @Override
    public BillingPlans.ActivatePlan withId(String id) {
        requestBuilder.pathParameter("id", id);
        return this;
    }

    @Override
    public Method getMethod() {
        return Method.POST;
    }
}
