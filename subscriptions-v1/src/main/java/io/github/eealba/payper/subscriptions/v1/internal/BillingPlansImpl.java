package io.github.eealba.payper.subscriptions.v1.internal;

import io.github.eealba.payper.core.Payper;
import io.github.eealba.payper.core.PayperRequest;
import io.github.eealba.payper.subscriptions.v1.api.BillingPlans;
import io.github.eealba.payper.subscriptions.v1.model.ErrorDefault;
import io.github.eealba.payper.subscriptions.v1.model.PatchRequest;
import io.github.eealba.payper.subscriptions.v1.model.Plan;
import io.github.eealba.payper.subscriptions.v1.model.PlanCollection;
import io.github.eealba.payper.subscriptions.v1.model.PlanRequestPOST;
import io.github.eealba.payper.subscriptions.v1.model.UpdatePricingSchemesListRequest;

class BillingPlansImpl implements BillingPlans {

    private final Payper payper;

    BillingPlansImpl(Payper payper) {
        this.payper = payper;
    }

    @Override
    public CreatePlan create() {
        return new CreatePlanImpl(payper);
    }

    @Override
    public ListPlans list() {
        return new ListPlanImpl(payper);
    }

    @Override
    public GetPlan get() {
        return new GetPlanImpl(payper);
    }

    @Override
    public UpdatePlan update() {
        return new UpdatePlanImpl(payper);
    }

    @Override
    public ActivatePlan activate() {
        return new ActivatePlanImpl(payper);
    }

    @Override
    public DeactivatePlan deactivate() {
        return new DeactivatePlanImpl(payper);
    }

    @Override
    public UpdatePricingSchemes updatePricingSchemes() {
        return new UpdatePricingSchemesImpl(payper);
    }

    private static class CreatePlanImpl extends
            RequestSpecImpl<BillingPlans.CreatePlan, PlanRequestPOST, Plan, ErrorDefault> implements
            BillingPlans.CreatePlan {
        private CreatePlanImpl(Payper payper) {
            super(payper, "/v1/billing/plans", Plan.class, ErrorDefault.class);
        }

        @Override
        PayperRequest.Method getMethod() {
            return PayperRequest.Method.POST;
        }
    }

    private static class ListPlanImpl extends
            RequestSpecImpl<BillingPlans.ListPlans, Void, PlanCollection, ErrorDefault>
            implements BillingPlans.ListPlans {
        private ListPlanImpl(Payper payper) {
            super(payper, "/v1/billing/plans", PlanCollection.class, ErrorDefault.class);
        }

        @Override
        public BillingPlans.ListPlans withProductId(String productId) {
            query("product_id", productId);
            return this;
        }

        @Override
        public BillingPlans.ListPlans withPlanIds(String planIds) {
            query("plan_ids", planIds);
            return this;
        }

        @Override
        public BillingPlans.ListPlans withPageSize(int pageSize) {
            query("page_size", String.valueOf(pageSize));
            return this;
        }

        @Override
        public BillingPlans.ListPlans withPage(int page) {
            query("page", String.valueOf(page));
            return this;
        }

        @Override
        public BillingPlans.ListPlans withTotalRequired(boolean totalRequired) {
            query("total_required", String.valueOf(totalRequired));
            return this;
        }

        @Override
        PayperRequest.Method getMethod() {
            return PayperRequest.Method.GET;
        }
    }

    private static class GetPlanImpl extends RequestSpecImpl<BillingPlans.GetPlan, Void, Plan, ErrorDefault>
            implements BillingPlans.GetPlan {
        private GetPlanImpl(Payper payper) {
            super(payper, "/v1/billing/plans/{id}", Plan.class, ErrorDefault.class);
        }


        @Override
        public BillingPlans.GetPlan withFields(String fields) {
            query("fields", fields);
            return this;
        }


        @Override
        PayperRequest.Method getMethod() {
            return PayperRequest.Method.GET;
        }
    }

    private static class UpdatePlanImpl extends
            RequestSpecImpl<BillingPlans.UpdatePlan, PatchRequest, Void, ErrorDefault>
            implements BillingPlans.UpdatePlan {
        private UpdatePlanImpl(Payper payper) {
            super(payper, "/v1/billing/plans/{id}", Void.class, ErrorDefault.class);
        }

        @Override
        public PayperRequest.Method getMethod() {
            return PayperRequest.Method.PATCH;
        }
    }

    private static class ActivatePlanImpl extends RequestSpecImpl<BillingPlans.ActivatePlan, Void, Void, ErrorDefault>
            implements BillingPlans.ActivatePlan {
        private ActivatePlanImpl(Payper payper) {
            super(payper, "/v1/billing/plans/{id}/activate", Void.class, ErrorDefault.class);
        }

        @Override
        public PayperRequest.Method getMethod() {
            return PayperRequest.Method.POST;
        }
    }

    private static class DeactivatePlanImpl extends RequestSpecImpl<BillingPlans.DeactivatePlan, Void, Void, ErrorDefault>
            implements BillingPlans.DeactivatePlan {
        private DeactivatePlanImpl(Payper payper) {
            super(payper, "/v1/billing/plans/{id}/deactivate", Void.class, ErrorDefault.class);
        }

        @Override
        public PayperRequest.Method getMethod() {
            return PayperRequest.Method.POST;
        }
    }

    private static class UpdatePricingSchemesImpl extends RequestSpecImpl<BillingPlans.UpdatePricingSchemes,
            UpdatePricingSchemesListRequest, Void, ErrorDefault>
            implements BillingPlans.UpdatePricingSchemes {
        private UpdatePricingSchemesImpl(Payper payper) {
            super(payper, "/v1/billing/plans/{id}/update-pricing-schemes", Void.class, ErrorDefault.class);
        }

        @Override
        PayperRequest.Method getMethod() {
            return PayperRequest.Method.POST;
        }
    }

}
