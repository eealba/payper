package io.github.eealba.payper.subscriptions.v1.internal;

import io.github.eealba.payper.subscriptions.v1.api.Subscriptions;
import io.github.eealba.payper.subscriptions.v1.model.ErrorDefault;
import io.github.eealba.payper.subscriptions.v1.model.Plan;
import io.github.eealba.payper.subscriptions.v1.model.PlanRequestPOST;

class CreatePlanImpl
        extends BodyRequestSpecImpl<Subscriptions.CreatePlan, PlanRequestPOST, Plan, ErrorDefault>
        implements Subscriptions.CreatePlan {
}
