package io.github.eealba.payper.subscriptions.v1.api;

import io.github.eealba.payper.core.spec.RequestSpec;
import io.github.eealba.payper.subscriptions.v1.model.ErrorDefault;
import io.github.eealba.payper.subscriptions.v1.model.PatchRequest;
import io.github.eealba.payper.subscriptions.v1.model.Plan;
import io.github.eealba.payper.subscriptions.v1.model.PlanCollection;
import io.github.eealba.payper.subscriptions.v1.model.PlanRequestPOST;
import io.github.eealba.payper.subscriptions.v1.model.UpdatePricingSchemesListRequest;

public interface BillingPlans {
    CreatePlan create();

    ListPlans list();

    GetPlan get();

    UpdatePlan update();

    ActivatePlan activate();

    DeactivatePlan deactivate();

    UpdatePricingSchemes updatePricingSchemes();


    interface CreatePlan extends RequestSpec<Plan, ErrorDefault>,
            RequestSpec.BodySpec<CreatePlan,PlanRequestPOST>,
             RequestSpec.PreferSpec<CreatePlan>,
            RequestSpec.PaypalRequestIdSpec<CreatePlan> {

    }

    interface ListPlans extends RequestSpec<PlanCollection, ErrorDefault>, RequestSpec.PreferSpec<ListPlans> {
        ListPlans withProductId(String productId);

        ListPlans withPlanIds(String planIds);

        ListPlans withPageSize(int pageSize);

        ListPlans withPage(int page);

        ListPlans withTotalRequired(boolean totalRequired);
    }

    interface GetPlan extends RequestSpec<Plan, ErrorDefault>, RequestSpec.IdSpec<GetPlan> {
        GetPlan withFields(String fields);
    }

    interface UpdatePlan extends RequestSpec<Void, ErrorDefault>, RequestSpec.BodySpec<UpdatePlan, PatchRequest>,
            RequestSpec.IdSpec<UpdatePlan> {
    }

    interface ActivatePlan extends RequestSpec<Void, ErrorDefault>, RequestSpec.IdSpec<ActivatePlan> {
    }

    interface DeactivatePlan extends RequestSpec<Void, ErrorDefault>, RequestSpec.IdSpec<DeactivatePlan> {
    }

    interface UpdatePricingSchemes  extends RequestSpec<Void, ErrorDefault>,
            RequestSpec.BodySpec<UpdatePricingSchemes, UpdatePricingSchemesListRequest>,
            RequestSpec.IdSpec<UpdatePricingSchemes> {
    }
}