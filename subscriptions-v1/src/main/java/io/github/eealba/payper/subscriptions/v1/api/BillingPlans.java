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


    interface CreatePlan extends RequestSpec.PostRequestSpec<CreatePlan, PlanRequestPOST, Plan, ErrorDefault> {

    }

    interface ListPlans extends RequestSpec<ListPlans, PlanCollection, ErrorDefault> {
        ListPlans withProductId(String productId);

        ListPlans withPlanIds(String planIds);

        ListPlans withPageSize(int pageSize);

        ListPlans withPage(int page);

        ListPlans withTotalRequired(boolean totalRequired);
    }

    interface GetPlan extends RequestSpec.GetByIdRequestSpec<GetPlan, Plan, ErrorDefault> {
    }

    interface UpdatePlan extends RequestSpec.PatchRequestSpec<UpdatePlan, PatchRequest, Void, ErrorDefault> {
        UpdatePlan withId(String id);
    }

    interface ActivatePlan extends RequestSpec.PostByIdNoBodyRequestSpec<ActivatePlan, Void, ErrorDefault> {

    }

    interface DeactivatePlan extends RequestSpec.PostByIdNoBodyRequestSpec<DeactivatePlan, Void, ErrorDefault> {

    }

    interface UpdatePricingSchemes extends RequestSpec.PostRequestSpec<UpdatePricingSchemes, UpdatePricingSchemesListRequest,
            Void, ErrorDefault> {
        UpdatePricingSchemes withId(String id);
    }
}