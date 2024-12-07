package io.github.eealba.payper.subscriptions.v1.internal;

import io.github.eealba.payper.core.Payper;
import io.github.eealba.payper.subscriptions.v1.api.Subscriptions;
import io.github.eealba.payper.subscriptions.v1.model.ErrorDefault;
import io.github.eealba.payper.subscriptions.v1.model.Plan;

class GetPlanImpl extends RequestSpecImpl<Subscriptions.GetPlan, Plan, ErrorDefault>
        implements Subscriptions.GetPlan {
    GetPlanImpl(Payper payper) {
        super(payper,"/v1/billing/plans/{id}", Plan.class, ErrorDefault.class);
    }

    @Override
    public Subscriptions.GetPlan withFields(String fields) {
        requestBuilder.query("fields", fields);
        return this;
    }

    @Override
    public Subscriptions.GetPlan withId(String id) {
        requestBuilder.pathParameter("id", id);
        return this;
    }

}
