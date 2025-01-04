/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.eealba.payper.subscriptions.v1.internal;

import io.github.eealba.payper.core.Payper;
import io.github.eealba.payper.core.PayperRequest;
import io.github.eealba.payper.core.spec.RequestSpecImpl;
import io.github.eealba.payper.subscriptions.v1.api.BillingPlans;
import io.github.eealba.payper.subscriptions.v1.model.ErrorDefault;
import io.github.eealba.payper.subscriptions.v1.model.PatchRequest;
import io.github.eealba.payper.subscriptions.v1.model.Plan;
import io.github.eealba.payper.subscriptions.v1.model.PlanCollection;
import io.github.eealba.payper.subscriptions.v1.model.PlanRequestPOST;
import io.github.eealba.payper.subscriptions.v1.model.UpdatePricingSchemesListRequest;
/**
 * Implementation of the BillingPlans API
 * @see BillingPlans
 *
 * @since 1.0.0
 * @version 1.0.0
 * @author Edgar Alba
 */
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
        protected PayperRequest.Method getMethod() {
            return PayperRequest.Method.POST;
        }
    }

    private static class ListPlanImpl extends
            RequestSpecImpl<ListPlans, Void, PlanCollection, ErrorDefault>
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
        protected PayperRequest.Method getMethod() {
            return PayperRequest.Method.GET;
        }
    }

    private static class GetPlanImpl extends RequestSpecImpl<BillingPlans.GetPlan, Void, Plan, ErrorDefault>
            implements BillingPlans.GetPlan {
        private GetPlanImpl(Payper payper) {
            super(payper, "/v1/billing/plans/{id}", Plan.class, ErrorDefault.class);
        }



        @Override
        protected PayperRequest.Method getMethod() {
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
        protected PayperRequest.Method getMethod() {
            return PayperRequest.Method.PATCH;
        }
    }

    private static class ActivatePlanImpl extends RequestSpecImpl<BillingPlans.ActivatePlan, Void, Void, ErrorDefault>
            implements BillingPlans.ActivatePlan {
        private ActivatePlanImpl(Payper payper) {
            super(payper, "/v1/billing/plans/{id}/activate", Void.class, ErrorDefault.class);
        }

        @Override
        protected PayperRequest.Method getMethod() {
            return PayperRequest.Method.POST;
        }
    }

    private static class DeactivatePlanImpl extends RequestSpecImpl<BillingPlans.DeactivatePlan, Void, Void, ErrorDefault>
            implements BillingPlans.DeactivatePlan {
        private DeactivatePlanImpl(Payper payper) {
            super(payper, "/v1/billing/plans/{id}/deactivate", Void.class, ErrorDefault.class);
        }

        @Override
        protected PayperRequest.Method getMethod() {
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
        protected PayperRequest.Method getMethod() {
            return PayperRequest.Method.POST;
        }
    }

}
