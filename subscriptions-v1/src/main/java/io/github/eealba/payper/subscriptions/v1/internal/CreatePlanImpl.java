package io.github.eealba.payper.subscriptions.v1.internal;

import io.github.eealba.payper.core.rest.ResponseSpec;
import io.github.eealba.payper.subscriptions.v1.api.Subscriptions;
import io.github.eealba.payper.subscriptions.v1.model.ErrorDefault;
import io.github.eealba.payper.subscriptions.v1.model.Plan;
import io.github.eealba.payper.subscriptions.v1.model.PlanRequestPOST;

public class CreatePlanImpl implements Subscriptions.CreatePlan {
    @Override
    public Subscriptions.CreatePlan withBody(PlanRequestPOST body) {
        return this;
    }

    @Override
    public Subscriptions.CreatePlan withPrefer(String prefer) {
        return null;
    }

    @Override
    public Subscriptions.CreatePlan withPaypalRequestId(String paypalRequestId) {
        return null;
    }

    @Override
    public ResponseSpec<Plan, ErrorDefault> retrieve() {
        return null;
    }
}
