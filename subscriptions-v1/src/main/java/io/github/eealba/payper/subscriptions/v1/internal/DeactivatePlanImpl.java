package io.github.eealba.payper.subscriptions.v1.internal;

import io.github.eealba.payper.core.Payper;
import io.github.eealba.payper.core.web.Method;
import io.github.eealba.payper.subscriptions.v1.api.BillingPlans;
import io.github.eealba.payper.subscriptions.v1.model.ErrorDefault;

class DeactivatePlanImpl extends RequestSpecImpl<BillingPlans.DeactivatePlan, Void, ErrorDefault>
        implements BillingPlans.DeactivatePlan {
    DeactivatePlanImpl(Payper payper) {
        super(payper, "/v1/billing/plans/{id}/deactivate", Void.class, ErrorDefault.class);
    }

    @Override
    public BillingPlans.DeactivatePlan withId(String id) {
        requestBuilder.pathParameter("id", id);
        return this;
    }

    @Override
    public Method getMethod() {
        return Method.POST;
    }
}
