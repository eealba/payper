package io.github.eealba.payper.subscriptions.v1.api;

import io.github.eealba.payper.core.PayperConfig;
import io.github.eealba.payper.core.rest.GetByIdRequestSpec;
import io.github.eealba.payper.core.rest.PatchRequestSpec;
import io.github.eealba.payper.core.rest.PostRequestSpec;
import io.github.eealba.payper.core.rest.RequestSpec;
import io.github.eealba.payper.subscriptions.v1.model.ErrorDefault;
import io.github.eealba.payper.subscriptions.v1.model.PatchRequest;
import io.github.eealba.payper.subscriptions.v1.model.Plan;
import io.github.eealba.payper.subscriptions.v1.model.PlanRequestPOST;
import io.github.eealba.payper.subscriptions.v1.model.UpdatePricingSchemesListRequest;

import java.util.List;

public interface Subscriptions {
    static Subscriptions create(PayperConfig config) {
        return SubscriptionsProvider.provider().createSubscriptions(config);
    }
    BillingPlans billingPlans();

    interface BillingPlans {
        CreatePlan create();
        ListPlans list();
        GetPlan get();
        UpdatePlan update();
        ActivatePlan activate();
        DeactivatePlan deactivate();
        UpdatePricingSchemes updatePricingSchemes();
    }
    interface CreatePlan extends PostRequestSpec<CreatePlan, PlanRequestPOST, Plan, ErrorDefault> {

    }
    interface ListPlans extends RequestSpec<ListPlans, List<Plan>, ErrorDefault> {
        ListPlans withProductId(String productId);
        ListPlans withPlanIds(String planIds);
        ListPlans withPageSize(int pageSize);
        ListPlans withPage(int page);
        ListPlans withTotalRequired(boolean totalRequired);
        //TODO create enum STATUSES
        ListPlans withStatuses(String status);
    }
    interface GetPlan extends GetByIdRequestSpec<GetPlan,Plan, ErrorDefault> {
        GetPlan withFields(String fields);
    }
    interface UpdatePlan extends PatchRequestSpec<UpdatePlan, PatchRequest,Void, ErrorDefault> {
        UpdatePlan withId(String id);
    }
    interface ActivatePlan extends PostRequestSpec<ActivatePlan,Void, Void, ErrorDefault> {
        ActivatePlan withId(String id);
    }
    interface DeactivatePlan extends PostRequestSpec<DeactivatePlan,Void, Void, ErrorDefault> {
        DeactivatePlan withId(String id);
    }
    interface UpdatePricingSchemes extends PostRequestSpec<UpdatePricingSchemes, UpdatePricingSchemesListRequest,
                Void, ErrorDefault> {
        UpdatePricingSchemes withId(String id);
    }
}
