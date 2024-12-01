package io.github.eealba.payper.subscriptions.v1.internal;

import io.github.eealba.payper.core.Payper;
import io.github.eealba.payper.core.web.Method;
import io.github.eealba.payper.subscriptions.v1.api.Subscriptions;
import io.github.eealba.payper.subscriptions.v1.model.ErrorDefault;
import io.github.eealba.payper.subscriptions.v1.model.Plan;

public class GetPlanImpl extends RequestSpecImpl<Subscriptions.GetPlan, Plan, ErrorDefault>
        implements Subscriptions.GetPlan {
    public GetPlanImpl(Payper payper) {
        super(payper,"/v1/billing/plans/{id}");
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

    @Override
    public Method getMethod() {
        return Method.GET;
    }

    @Override
    public Class<Plan> getEntityClass() {
        return Plan.class;
    }

    @Override
    public Class<ErrorDefault> getErrorEntityClass() {
        return ErrorDefault.class;
    }
}
